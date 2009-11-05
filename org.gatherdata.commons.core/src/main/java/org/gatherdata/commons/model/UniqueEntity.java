/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.model;

import java.io.Serializable;
import java.net.URI;

import org.joda.time.DateTime;

public interface UniqueEntity extends Serializable {

    public URI getUid();
    
    public DateTime getDateCreated();
    
    /**
     * Generates an unique id for this entity,
     * which the entity adopts if it does not
     * already have a UID. 
     * 
     * @return self-identified uid
     */
    public URI selfIdentify();
          
}
