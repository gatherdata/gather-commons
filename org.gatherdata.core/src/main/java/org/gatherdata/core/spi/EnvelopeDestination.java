package org.gatherdata.core.spi;


import org.gatherdata.core.model.Envelope;

/**
 * An EnvelopeDestination accepts Envelopes.
 * 
 *  @param <T>
 */
public interface EnvelopeDestination extends EnvelopeHandler {

    /**
     * Accept an Envelope for handling. 
     * 
     * This call should be thread-safe and return immediately.
     * 
     * @param envelope
     */
	void accept(Envelope envelope);

	int getEnvelopesReceived();
	
}
