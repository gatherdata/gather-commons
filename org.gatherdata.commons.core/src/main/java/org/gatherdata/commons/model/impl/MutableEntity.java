/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.model.impl;

import java.net.URI;

import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

@SuppressWarnings("serial")
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
    
    public UniqueEntity copy(UniqueEntity template) {
        if (template != null) {
            this.setUid(template.getUid());
            this.setDateCreated(template.getDateCreated());
        }
        return this;
    }

    public UniqueEntity update(UniqueEntity template) {
        if (template != null) {
            if (template.getUid() != null) {
                this.setUid(template.getUid());
            }
            if (template.getDateCreated() != null) {
                this.setDateCreated(template.getDateCreated());
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

    @SuppressWarnings("unchecked")
    public MutableEntity workingCopy() {
        return this;
    }

    public URI selfIdentify() {
        if (dateCreated == null) {
            dateCreated = new DateTime();
        }
        URI selfId = CbidFactory.createCbid(dateCreated.toString() + Integer.toHexString(hashCode()));
        if (uid == null) uid = selfId;
        return selfId;
    }

}
