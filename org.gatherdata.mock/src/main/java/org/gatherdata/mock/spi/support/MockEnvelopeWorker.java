package org.gatherdata.mock.spi.support;

import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.spi.EnvelopeDestination;
import org.gatherdata.mock.spi.MockEnvelopeFactory;
import org.osgi.service.log.LogService;

import com.google.inject.Inject;

public class MockEnvelopeWorker extends Thread {
	
	@Inject
	LogService log;

	private EnvelopeDestination destination;
	private MockEnvelopeFactory<?> factoryToUse;
	private int quantityToProduce;
	private int quantityProduced = 0;
	private boolean isRunning = false;
	private boolean threadSuspended;
	private long interval;

	public MockEnvelopeWorker(int quantity, int perSecond,
			MockEnvelopeFactory<?> atFactory,
			EnvelopeDestination forDestination) {
		this.factoryToUse = atFactory;
		this.quantityToProduce = quantity;
		this.destination = forDestination;
		this.interval = (1000 / perSecond);
	}
	
	/**
	 * Stops the worker from producing Envelopes. 
	 */
	public void stopProduction() {
		isRunning = false;
	}
	
	public synchronized void pauseProduction() {
		threadSuspended = true;
	}

	public synchronized void resumeProduction() {
		threadSuspended = false;
	}
	/**
	 * Causes the worker to start the job of producing Envelopes.
	 * 
	 * Called when the worker is started as a Thread.
	 */
	public void run() {
		if (!isRunning) {
			isRunning = true;
			while ((quantityProduced < quantityToProduce) && (isRunning)) {
	            try {
	                Thread.sleep(interval);

	                synchronized(this) {
	                    while (threadSuspended)
	                        wait();
	                }
	            } catch (InterruptedException e){
	            }
	            Envelope producedEnvelope = factoryToUse.createEnvelope();
	            if (destination != null) {
	            	destination.accept(producedEnvelope);
	            }
				quantityProduced++;
				System.out.println("MockEnvelopeWorker shipped product #" + quantityProduced + " to " + destination);
			}
			isRunning = false;
		}
	}

	/**
	 * Gets the number of Envelopes this worker has produced
	 * and distributed to the destination.
	 * 
	 * @return number of Envelopes which have been produced
	 */
	public int getQuantityProduced() {
		return quantityProduced;
	}
	
	/**
	 * Checks whether this worker is finished producing
	 * all the requested Envelopes.
	 * 
	 * @return true if the job is done, false otherwise
	 */
	public boolean isFinished() {
		return (quantityProduced == quantityToProduce);
	}
}
