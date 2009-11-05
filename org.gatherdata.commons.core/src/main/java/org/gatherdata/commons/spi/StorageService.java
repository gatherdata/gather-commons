/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
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
