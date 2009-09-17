package org.gatherdata.commons.model;

import java.net.URI;

import org.joda.time.DateTime;

public class MutableEntity implements UniqueEntity {

    protected static final UniqueEntitySupport support = new UniqueEntitySupport();

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
    
    public void copy(UniqueEntity template) {
        if (template != null) {
            this.setUid(template.getUid());
            this.setDateCreated(template.getDateCreated());
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UniqueEntity)) return false;
        UniqueEntity rhs = (UniqueEntity)obj;
        return support.equals(this, rhs);
    }

    @Override
    public int hashCode() {
        return support.hashCode(this);
    }    

}
