/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.model.util;

import java.net.URI;

import org.gatherdata.commons.model.UniqueEntity;

public interface IdentityGenerator<EntityType extends UniqueEntity> {

    /**
     * Generates an appropriate UID for the given entity.
     * 
     * @param unidentifiedEntity
     * @return unique identity given to the entity
     */
    public URI identify(EntityType unidentifiedEntity);
    
}
