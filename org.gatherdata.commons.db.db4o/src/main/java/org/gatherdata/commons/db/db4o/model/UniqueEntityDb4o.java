/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.db4o.model;

import java.net.URI;
import java.net.URISyntaxException;

import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.model.impl.UniqueEntitySupport;
import org.gatherdata.commons.net.CbidFactory;
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
    
    public void setDateCreated(DateTime dateCreated) {
        this.lazyDateCreated = dateCreated;
        dateCreatedMillis = dateCreated.getMillis();
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
    
    public void setUid(URI uid) {
        this.lazyUid = uid;
        this.uidAsAscii = uid.toASCIIString();
    }
    
    public void copy(UniqueEntity template) {
        if (template != null) {
            URI templateUid = template.getUid();
            if (templateUid != null) {
                setUid(templateUid.toASCIIString());
            } else {
                setUid((String)null);
            }
            DateTime templateDateCreated = template.getDateCreated();
            if (templateDateCreated != null) {
                setDateCreatedMillis(templateDateCreated.getMillis());
            } else {
                setDateCreatedMillis(0);
            }
        }
        //return this;
    }
    
    public void update(UniqueEntity template) {
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

    public URI selfIdentify() {
        return CbidFactory.createCbid(getDateCreated().toString() + Integer.toHexString(hashCode()));
    }    

}
