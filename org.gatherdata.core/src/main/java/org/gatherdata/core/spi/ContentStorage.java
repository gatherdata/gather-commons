package org.gatherdata.core.spi;

import java.net.URI;

/**
 * Content storage is a 
 * 
 */
public interface ContentStorage {
    
    /**
     * The base location of a content storage is the URL.
     * 
     * @return
     */
    public URI getBaseLocation();
    

}
