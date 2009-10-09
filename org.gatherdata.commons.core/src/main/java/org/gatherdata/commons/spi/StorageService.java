/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.spi;

import java.net.URI;

import org.gatherdata.commons.model.UniqueEntity;

/**
 * Interface for services which can store content. 
 *
 * @param <ContentType> the content type handled by this storage
 */
public interface StorageService<ContentType extends UniqueEntity> {
    
    public Iterable<? extends ContentType> getAll();
    
    public ContentType get(URI uid);
    
    public boolean exists(URI uid);
    
    public ContentType save(ContentType instance);
    
    public void remove(URI uid);
    
    /**
     * Save the non-null values from the entity.
     * 
     * @param toEntity
     */
    public void update(ContentType instance);

}
