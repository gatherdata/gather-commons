package org.gatherdata.commons.model;

import java.net.URI;

import org.joda.time.DateTime;

public class MutableEntity implements UniqueEntity {

    protected URI uid;
    
    protected DateTime dateCreated;
    
    public URI getUid() {
        return this.uid;
    }
    
    public void setUid(URI uid) {
        this.uid = uid;
    }

    public DateTime getDateCreated() {
        return this.dateCreated;
    }
    
    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
