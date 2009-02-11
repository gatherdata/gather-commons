package org.gatherdata.core.spi;

import java.util.Collection;

import org.gatherdata.core.model.Envelope;

/**
 * An EnvelopeTransform produces new Envelopes from a source Envelope. 
 * 
 */
public interface EnvelopeTransformer extends EnvelopeHandler {

	/**
	 * Transforms the contents of the source Envelope, wrapped in 
	 * one or more new Envelope, each linked to the original.
	 * 
	 * @param source
	 * @return collection of transformed products
	 */
	Collection<Envelope> transform(Envelope source);
}
