/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.db4o.model;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.model.impl.UniqueEntitySupport;
import org.joda.time.DateTime;

public class UniqueEntityDb4o implements UniqueEntity {

    protected static final UniqueEntitySupport support = new UniqueEntitySupport();

    protected transient DateTime lazyDateCreated;
    protected long dateCreatedMillis;
    protected transient URI lazyUid;
    protected String uidAsAscii;

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
        if ((lazyUid == null) && (uidAsAscii != null)) {
            try {
                lazyUid = new URI(uidAsAscii);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return this.lazyUid;
    }
    
    public void setUid(String uidAsAscii) {
        this.uidAsAscii = uidAsAscii;
    }
    
    public UniqueEntityDb4o copy(UniqueEntity template) {
        if (template != null) {
            URI templateUid = template.getUid();
            if (templateUid != null) {
                setUid(templateUid.toASCIIString());
            }
            DateTime templateDateCreated = template.getDateCreated();
            if (templateDateCreated != null) {
                setDateCreatedMillis(templateDateCreated.getMillis());
            }
        }
        return this;
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
