package org.gatherdata.mock.spi;

import java.io.Serializable;

import org.gatherdata.core.model.Envelope;

public interface MockEnvelopeFactory<T extends Serializable> {

	Envelope createEnvelope();


}
