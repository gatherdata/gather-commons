/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
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
