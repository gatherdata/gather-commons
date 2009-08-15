package org.gatherdata.mock.spi;

import java.io.Serializable;

import org.gatherdata.core.model.Receipt;

public interface MockEnvelopeFactory<T extends Serializable> {

	Receipt createEnvelope();


}
