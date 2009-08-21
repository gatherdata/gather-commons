package org.gatherdata.commons.spi;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

/**
 * Interface for services which can store content. 
 *
 * @param <ContentType> the content type handled by this storage
 */
public interface StorageService<ContentType extends Serializable> {

    Iterable<ContentType> getAll();
    
    ContentType get(URI uid);
    
    boolean exists(URI uid);
    
    ContentType save(ContentType instance);
    
    void remove(URI uid);
    
}
