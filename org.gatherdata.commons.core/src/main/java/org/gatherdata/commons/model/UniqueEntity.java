package org.gatherdata.commons.model;

import java.io.Serializable;
import java.net.URI;

public interface UniqueEntity extends Serializable {

    public URI getUid();
    
}
