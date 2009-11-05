/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.model.impl;

import java.net.URI;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.gatherdata.commons.model.UniqueEntity;

public class UniqueEntitySupport {

    public int hashCode(UniqueEntity entity) {
        final URI uid = entity.getUid();
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    public boolean equals(UniqueEntity lhs, UniqueEntity rhs) {
        if (lhs == rhs)
            return true;
        if (rhs == null)
            return false;
        if (lhs.getUid() == null) {
            if (rhs.getUid() != null)
                return false;
        } else if (!lhs.getUid().equals(rhs.getUid()))
            return false;
        
        return true;
    }
    
    public static boolean deepEquals(UniqueEntity lhs, UniqueEntity rhs) {
        boolean areEqual = true;
        if (lhs != rhs) {
            areEqual = new EqualsBuilder().append(lhs.getUid(), rhs.getUid())
                .append(lhs.getDateCreated(), rhs.getDateCreated())
                    .isEquals();
        }
        return areEqual;
    }


    
}
