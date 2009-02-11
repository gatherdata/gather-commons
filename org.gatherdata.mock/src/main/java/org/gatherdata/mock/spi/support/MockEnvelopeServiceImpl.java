package org.gatherdata.mock.spi.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.activation.MimeType;

import org.gatherdata.core.io.MimeTypes;
import org.gatherdata.core.spi.EnvelopeDestination;
import org.gatherdata.mock.factories.MockTextEnvelopeFactory;
import org.gatherdata.mock.spi.MockEnvelopeFactory;
import org.gatherdata.mock.spi.MockEnvelopeService;

/**
 * Internal implementation of the MockEnvelopeFactory.
 */
public final class MockEnvelopeServiceImpl
    implements MockEnvelopeService
{
	public static final int DEFAULT_UNITS_PER_SECOND = 10;
	
	EnvelopeDestination destination;
	
	boolean isEnabled = true;

	private Collection<MockEnvelopeWorker> workers = Collections.synchronizedCollection(new ArrayList<MockEnvelopeWorker>());

	private int unitsPerSecond = DEFAULT_UNITS_PER_SECOND;

	public EnvelopeDestination getDestination() {
		return destination;
	}

	public boolean subscribe(EnvelopeDestination destination) {
		// ABKTODO: implement this
		return false;		
	}
	
	public boolean unsubscribe(EnvelopeDestination destination) {
		// ABKTODO: implement this
		return false;
	}
	
	public void enable() {
		synchronized(workers) {
			for (MockEnvelopeWorker worker : workers) {
				if (!worker.isFinished()) {
					worker.resumeProduction();
				} 
			}
		}
	}

	public void disable() {
		synchronized(workers) {
			for (MockEnvelopeWorker worker : workers) {
				worker.pauseProduction();
			}
		}
	}
	
	public int getEnvelopesSent() {
		int totalSent = 0;

		synchronized(workers) {
			for (MockEnvelopeWorker worker : workers) {
				totalSent += worker.getQuantityProduced();
			}
		}
		return totalSent;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void produce(int quantity, MimeType ofType) {
		MockEnvelopeFactory<?> factoryForTheJob = getFactoryFor(ofType);
		MockEnvelopeWorker workerForTheJob = new MockEnvelopeWorker(quantity, unitsPerSecond, 
				factoryForTheJob, destination);
		workers.add(workerForTheJob);
		if (isEnabled) {
			workerForTheJob.start();
		}
	}

	public MockEnvelopeFactory<?> getFactoryFor(MimeType ofType) {
		MockEnvelopeFactory<?> factoryToUse = null;
		if (ofType != null) {
			if (ofType.equals(MimeTypes.TEXT_PLAIN)) {
				factoryToUse = new MockTextEnvelopeFactory();
			} else if (ofType.equals(MimeTypes.TEXT_JSON)) {
				;
			} else if (ofType.equals(MimeTypes.TEXT_XML)) {
				;
			} else if (ofType.equals(MimeTypes.TEXT_YAML)) {
				;
			}
		}
		return factoryToUse;
	}

}

