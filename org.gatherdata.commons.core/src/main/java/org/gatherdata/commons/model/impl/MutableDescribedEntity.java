/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.model.impl;

import java.net.URI;

import org.gatherdata.commons.model.DescribedEntity;
import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class MutableDescribedEntity extends MutableEntity implements DescribedEntity {

    protected String description;
    protected String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DescribedEntity copy(DescribedEntity template) {
        if (template != null) {
            super.copy(template);
            setName(template.getName());
            setDescription(template.getDescription());
        }
        return this;
    }

    public DescribedEntity update(DescribedEntity template) {
        if (template != null) {
            super.update(template);
            if (template.getName() != null) {
                this.setName(template.getName());
            }
            if (template.getDescription() != null) {
                this.setDescription(template.getDescription());
            }
        }
        return this;
    }

    public URI selfIdentify() {
        if (dateCreated == null) {
            dateCreated = new DateTime();
        }
        URI selfId = CbidFactory.createCbid(dateCreated + name + description + Integer.toHexString(hashCode()));
        if (this.uid == null) {
            this.uid = selfId;
        }
        return selfId;
    }

}
