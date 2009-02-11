package org.gatherdata.mock.internal;

import static org.junit.Assert.assertEquals;

import org.gatherdata.core.spi.EnvelopeDestination;
import org.gatherdata.mock.factories.MockTextEnvelopeFactory;
import org.gatherdata.mock.spi.MockEnvelopeFactory;
import org.gatherdata.mock.spi.support.MockEnvelopeDestination;
import org.gatherdata.mock.spi.support.MockEnvelopeWorker;
import org.junit.Test;

public class MockEnvelopeWorkerTest {

	@Test
	public void shouldProduceEnvelopesWithoutDestination() {
		final int EXPECTED_QUANTITY = 10; 
		final int PER_SECOND = 100;
		MockEnvelopeFactory<String> factoryToUse = new MockTextEnvelopeFactory();
		EnvelopeDestination expectedDestination = null;
		MockEnvelopeWorker workerUT = new MockEnvelopeWorker(EXPECTED_QUANTITY, PER_SECOND, 
				factoryToUse, expectedDestination);
		workerUT.run();
		
		assertEquals(EXPECTED_QUANTITY, workerUT.getQuantityProduced());
		
	}
	
	@Test 
	public void shouldSendEnvelopesToDestination() {
		final int EXPECTED_QUANTITY = 10; 
		final int PER_SECOND = 100;
		MockEnvelopeFactory<String> factoryToUse = new MockTextEnvelopeFactory();
		EnvelopeDestination expectedDestination = new MockEnvelopeDestination();
		MockEnvelopeWorker workerUT = new MockEnvelopeWorker(EXPECTED_QUANTITY, PER_SECOND, 
				factoryToUse, expectedDestination);
		workerUT.run();
		
		assertEquals(EXPECTED_QUANTITY, expectedDestination.getEnvelopesReceived());
	}
}
