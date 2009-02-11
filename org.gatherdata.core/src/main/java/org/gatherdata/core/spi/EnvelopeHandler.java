package org.gatherdata.core.spi;

import org.gatherdata.core.io.QualifiedType;

/**
 * Common features of service interfaces which handle Envelopes.
 * 
 */
public interface EnvelopeHandler {

	/**
	 * Returns the type of Envelope contents
	 * that will be accepted by this handler.
	 * 
	 * @return
	 */
	QualifiedType[] getAcceptableTypes();

}
