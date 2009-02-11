package org.gatherdata.workflow.internal;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.activation.MimeType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.core.io.MimeTypes;
import org.gatherdata.core.io.QualifiedType;
import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.model.SpecialEnvelope;
import org.gatherdata.workflow.WorkAssignment;
import org.gatherdata.workflow.spi.WorkflowManager;


/**
 * Internal implementation of Workflow Manager Service.
 */
public class WorkflowManagerImpl implements WorkflowManager
{	
	private static final Log logger = LogFactory.getLog(WorkflowManagerImpl.class);
	
	private AtomicInteger numberReceived = new AtomicInteger(0);
	private AtomicBoolean shouldRun = new AtomicBoolean(true);
		
	private WorkflowDistributor distributor = null;
	private BlockingQueue<Envelope> workQueue = new LinkedBlockingQueue<Envelope>();
	
	private Map<MimeType, WorkAssignment> typeToAssignmentMap = new HashMap<MimeType, WorkAssignment>();
	
	private ExecutorService workforce;
		
	public WorkflowManagerImpl() {		
		workforce = Executors.newCachedThreadPool();
		
		distributor = new WorkflowDistributor();
		distributor.start();
		
		logger.info("GATHER Workflow Manager has started.");
	}
	
	/* (non-Javadoc)
	 * @see org.gatherdata.workflow.internal.WorkflowManager#assign(javax.activation.MimeType, org.gatherdata.workflow.WorkAssignment)
	 */
	public void addAssignment(MimeType typeOfWork, WorkAssignment toAssignment) {
		typeToAssignmentMap.put(typeOfWork, toAssignment);
	}
	
	/* (non-Javadoc)
	 * @see org.gatherdata.workflow.internal.WorkflowManager#accept(org.gatherdata.core.model.Envelope)
	 */
	public void accept(Envelope e) {
		if (shouldRun.get()) {
			numberReceived.incrementAndGet();
	
			try {
				workQueue.put(e);
			} catch (InterruptedException ie) {
				logger.error("WorkflowManager failed to add to queue, because: " + ie);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.gatherdata.workflow.internal.WorkflowManager#getEnvelopesReceived()
	 */
	public int getEnvelopesReceived() {
		return numberReceived.get();
	}

	/**
	 * Attempts an orderly shutdown of the distributor.
	 */
	public void shutdown() {
		
		shouldRun.set(false);
		// send "poison pill" into the queue
		try {
			workQueue.put(SpecialEnvelope.LAST_ENVELOPE);
		} catch (InterruptedException ie) {
			logger.warn("WorkflowManager failed to place special LAST_ENVELOPE in work queue, because: " + ie);
			logger.warn("Interrupting the distributor...");
			distributor.interrupt();
		}
		
	}
	
	private class WorkflowDistributor extends Thread {
		
		@Override
		public void run() {
			while (shouldRun.get()) {
				Envelope workUnit = null;
				try {
					workUnit = workQueue.take();
				} catch (InterruptedException ie) {
					logger.warn("Interrupted waiting for the work queue, because: " + ie);
				}

				if (workUnit != null) {
					if (workUnit.getType().equals(MimeTypes.GATHER_COMMAND)) {
						workforce.shutdown();
					} else {
						WorkAssignment currentAssignment = typeToAssignmentMap.get(workUnit.getType());
						if (currentAssignment != null) {
							WorkflowWorker newWorker = new WorkflowWorker(currentAssignment, workUnit);
							try {
								workforce.submit(newWorker);
							} catch (RejectedExecutionException ree) {
								logger.warn("Worker couldn't be scheduled, because: " + ree);
							}
						}
					}
				}
			}
			
			if (!workforce.isTerminated()) {
				// give workers a chance to finish
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				
				if (!workforce.isTerminated()) {
					logger.warn("Forcibly shutting down workers.");
					workforce.shutdownNow();
				}

			}
		}
	}

    public QualifiedType[] getAcceptableTypes() {
        // TODO Auto-generated method stub
        return null;
    }
	
}

