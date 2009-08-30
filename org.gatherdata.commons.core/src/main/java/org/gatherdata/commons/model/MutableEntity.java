package org.gatherdata.commons.model;

import java.net.URI;

public class MutableEntity implements UniqueEntity {

    protected URI uid;
    
    public URI getUid() {
        return this.uid;
    }
    
    public void setUid(URI uid) {
        this.uid = uid;
    }

}
