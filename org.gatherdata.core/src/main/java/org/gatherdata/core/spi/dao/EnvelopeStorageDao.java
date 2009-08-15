package org.gatherdata.core.spi.dao;

import java.net.URI;
import java.util.List;

import org.gatherdata.core.model.Receipt;

/**
 * Generic interface for a DAO which supports persistence of Envelopes.
 * 
 */
public interface EnvelopeStorageDao {

    /**
     * Get all stored Envelopes.
     * 
     * @return List of Envelopes
     */
    List<? extends Receipt> getAllEnvelopes();

    /**
     * Retrieves a specific Envelope, based on its uid.
     * 
     * @param identifiedByUid
     *            the uid of the Envelope
     * @return the requested Envelope, or null if not available
     */
    Receipt getEnvelope(URI identifiedByUid);

    /**
     * Checks for existence of an Envelope.
     * 
     * @param identifiedByUid
     *            the uid of the Envelope
     * @return - true if it exists, false if it doesn't
     */
    boolean envelopeExists(URI identifiedByUid);

    /**
     * Saves an Envelope - handles both update and insert.
     * 
     * @param object
     *            the object to save
     * @return the persisted object
     */
    Receipt save(Receipt envelopeToSave);

    /**
     * Removes an Envelope.
     * 
     * @param identifiedByUid
     *            the uid of the Envelope
     */
    void removeEnvelope(URI identifiedByUid);

    /**
     * Returns the number of Envelopes stored by this dao.
     * 
     * @return
     */
    long getNumberOfEnvelopes();

}
