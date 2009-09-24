package org.gatherdata.commons.model.impl;

import org.gatherdata.commons.model.DescribedEntity;

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

    public void copy(DescribedEntity template) {
        super.copy(template);
        if (template != null) {
            setName(template.getName());
            setDescription(template.getDescription());
        }
    }
    
}
