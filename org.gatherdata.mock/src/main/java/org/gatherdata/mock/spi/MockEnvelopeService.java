package org.gatherdata.mock.spi;

import javax.activation.MimeType;

import org.gatherdata.core.spi.EnvelopeSource;

/**
 * An EnvelopeSource which generates and distributes mock Envelopes
 * to a destination on a scheduled basis. 
 */
public interface MockEnvelopeService extends EnvelopeSource {

	void produce(int quantity, MimeType ofType);

	MockEnvelopeFactory<?> getFactoryFor(MimeType ofType);
}

