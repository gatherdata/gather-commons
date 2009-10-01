/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.model;

import java.io.Serializable;
import java.net.URI;

import org.joda.time.DateTime;

public interface UniqueEntity extends Serializable {

    public URI getUid();
    
    public DateTime getDateCreated();
   
}
