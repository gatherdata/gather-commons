/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.db4o.model;

import org.gatherdata.commons.model.DescribedEntity;
import org.gatherdata.commons.model.UniqueEntity;
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


}
