package org.gatherdata.commons.spi.dao;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.transaction.Transaction;


/**
 * Generic interface for a DAO which supports persistence of content
 * 
 * @param <ContentType> type of content which can be persisted
 */
public interface StorageDao<ContentType extends Serializable> {

    void beginTransaction();
    
    void endTransaction();

    Iterable<ContentType> getAll();
    
    ContentType get(URI uid);
    
    boolean exists(URI uid);
    
    ContentType save(ContentType instance);
    
    void remove(URI uid);

}
