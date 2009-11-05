/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.spi;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.transaction.Transaction;

import org.gatherdata.commons.model.UniqueEntity;


/**
 * Generic interface for a DAO which supports persistence of content
 * 
 * @param <ContentType> type of content which can be persisted
 */
public interface StorageDao<ContentType extends UniqueEntity> {

    void beginTransaction();
    
    void endTransaction();

    Iterable<? extends ContentType> getAll();
    
    int getCount();
    
    ContentType get(URI uid);
    
    boolean exists(URI uid);
    
    ContentType save(ContentType instance);
    
    void remove(URI uid);

}
