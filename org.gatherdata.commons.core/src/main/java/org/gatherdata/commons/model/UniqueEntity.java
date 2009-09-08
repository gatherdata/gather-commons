package org.gatherdata.commons.model;

import java.io.Serializable;
import java.net.URI;

import org.joda.time.DateTime;

public interface UniqueEntity extends Serializable {

    public URI getUid();
    
    public DateTime getDateCreated();

}
