/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.db4o.model;

import java.net.URI;

import org.gatherdata.commons.model.DescribedEntity;
import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;

public class DescribedEntityDb4o extends UniqueEntityDb4o implements DescribedEntity {

    protected String description;
    protected String name;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DescribedEntityDb4o copy(DescribedEntity template) {
        if (template != null) {
            super.copy(template);
            setDescription(template.getDescription());
            setName(template.getName());
        }
        return this;
    }

    public URI selfIdentify() {
        return CbidFactory.createCbid(getDateCreated() + name + description + Integer.toHexString(hashCode()));
    }

}
