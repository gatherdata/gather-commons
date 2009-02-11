package org.gatherdata.core.spi;


import org.gatherdata.core.model.Envelope;

/**
 * An EnvelopeSource packages data into {@link Envelope Envelopes}, which 
 * are distributed to subscribed {@link EnvelopeDestination EnvelopeDestinations}.
 * 
 */
public interface EnvelopeSource {

	/**
	 * Subscribes a destination.
	 * 
	 * @param destination new subscriber
	 * @return true if the destination was successfully added to the subcriber list, false otherwise
	 */
	boolean subscribe(EnvelopeDestination destination);
	
	/**
	 * Unsubscribes a destination.
	 * 
	 * @param destination destination to unsubscribe
	 * @return true if the destination was successfully removed from the subcriber list, false otherwise
	 */
	boolean unsubscribe(EnvelopeDestination destination);

	/**
	 * Gets a count of the number of Envelopes this source has
	 * sent. 
	 * 
	 * @return
	 */
	int getEnvelopesSent();
	
}
