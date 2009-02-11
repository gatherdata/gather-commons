package org.gatherdata.core.spi;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.gatherdata.core.model.Envelope;

/**
 * Interface for EnvelopeHandlers which provide persistent storage for Envelopes.
 *
 * @param <ContentType> the content type handled by this storage
 */
public interface EnvelopeStorage<ContentType extends Serializable> extends EnvelopeHandler {

	/**
	 * Retrieves all stored Envelopes.
	 * 
	 * @return List of Envelopes
	 */
	List<? extends Envelope> retrieveAllEnvelopes();
	
	/**
	 * Returns a specific Envelope, identified by URI.
	 * 
	 * @param uidOfEnvelope uid of envelope to retrieve
	 * @return the corresponding Envelope, or null if not found
	 */
	Envelope retrieveEnvelope(URI uidOfEnvelope);
	
	/**
	 * Saves (or updates) an Envelope.
	 * 
	 * @param envelopeToSave Envelope instance to save
	 * @return saved Envelope instance
	 */
	Envelope save(Envelope envelopeToSave);
	
	/**
	 * Returns the total number of Envelopes stored in
	 * this EnvelopeStorage. 
	 * 
	 * This may be larger than the total number of unique
	 * Envelopes, since multiple storage dao mechanisms may
	 * be in use which could have duplicates. This number
	 * is the grand total from all available storage.
	 * 
	 * @return number of stored Envelopes
	 */
	long getTotalNumberOfStoredEnvelopes();
	
	/**
	 * Gets all the contents from within
	 * all stored Envelopes.
	 * 
	 * @return List of envelope contents
	 */
	List<? extends ContentType> getAllContents();
	
	/**
	 * Gets the contents of a particular Envelope.
	 * 
	 * @param uidOfContainingEnvelope
	 * @return
	 */
	ContentType getContent(URI uidOfContainingEnvelope);
}
