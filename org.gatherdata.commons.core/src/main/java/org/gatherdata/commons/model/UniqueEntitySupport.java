package org.gatherdata.commons.model;

import java.net.URI;

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
    
    
}
