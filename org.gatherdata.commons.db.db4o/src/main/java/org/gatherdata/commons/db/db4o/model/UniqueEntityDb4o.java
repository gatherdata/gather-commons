package org.gatherdata.commons.db.db4o.model;

import java.net.URI;

import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.model.UniqueEntitySupport;
import org.joda.time.DateTime;

public class UniqueEntityDb4o implements UniqueEntity {

    protected static final UniqueEntitySupport support = new UniqueEntitySupport();

    protected transient DateTime lazyDateCreated;
    protected long dateCreatedMillis;
    protected URI uid;

    public DateTime getDateCreated() {
        if (lazyDateCreated == null) {
            lazyDateCreated = new DateTime(dateCreatedMillis);
        }
        return lazyDateCreated;
    }

    public long getDateCreatedMillis() {
        return dateCreatedMillis;
    }

    public void setDateCreatedMillis(long dateCreatedMillis) {
        this.dateCreatedMillis = dateCreatedMillis;
    }

    public URI getUid() {
        return this.uid;
    }
    
    public void setUid(URI uid) {
        this.uid = uid;
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
