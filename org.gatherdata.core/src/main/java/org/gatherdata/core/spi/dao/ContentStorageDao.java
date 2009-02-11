package org.gatherdata.core.spi.dao;

import java.io.Serializable;
import java.net.URI;
import java.util.List;


/**
 * Generic interface for a DAO which supports persistence of Envelope content
 * 
 * @param <ContentType> type of content which can be persisted
 */
public interface ContentStorageDao<ContentType extends Serializable> {

    /**
     * Get all stored content.
     * 
     * @return List of content
     */
    List<? extends ContentType> getAllContents();
    
    /**
     * Gets a list of all the stored content UIDs.
     * 
     * @return
     */
    List<URI> getAllContentUids();

    /**
     * Retrieves specific Content, identified by the
     * uid of its containing Envelope.
     * 
     * @param uidOfEnvelope the uid of the containing Envelope
     * @return the requested Envelope, or null if not available
     */
    ContentType getContent(URI uidOfEnvelope);

    /**
     * Checks for existence of content.
     * 
     * @param uidOfEnvelope
     *            the uid of the containing Envelope
     * @return - true if it exists, false if it doesn't
     */
    boolean contentExists(URI uidOfEnvelope);

    /**
     * Saves content - handles both update and insert.
     * 
     * @param contentToSave
     *            the object to save
     * @return the persisted object
     */
    ContentType save(ContentType contentToSave);
    
    /**
     * Adapts a Serializable instance into a strongly-typed
     * ContentType instance, if possible
     * 
     * @param contentToAdapt
     * @return adapted content, or null if content couldn't be adapted
     */
    ContentType adapt(Serializable contentToAdapt);
    
    /**
     * Save Serializable content by attempting to adapt it to the
     * native ContentType of this dao. 
     * 
     * @param proxiedContentToSave
     * @return saved content, or null if the content could not be adapted
     */
    Serializable saveAdapted(Serializable proxiedContentToSave);
    

    /**
     * Removes content.
     * 
     * @param uidOfEnvelope
     *            the uid of the containing Envelope
     */
    void removeContent(URI uidOfEnvelope);

}
