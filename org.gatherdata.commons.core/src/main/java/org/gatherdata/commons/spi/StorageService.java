/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.spi;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import org.gatherdata.commons.model.UniqueEntity;

/**
 * Interface for services which can store content. 
 *
 * @param <ContentType> the content type handled by this storage
 */
public interface StorageService<ContentType extends UniqueEntity> {
    
    Iterable<? extends ContentType> getAll();
    
    ContentType get(URI uid);
    
    boolean exists(URI uid);
    
    ContentType save(ContentType instance);
    
    void remove(URI uid);
    
}
