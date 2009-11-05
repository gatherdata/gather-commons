/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.model.neo4j;

import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;

public class DescribedNodeWrapper extends UniqueNodeWrapper {

    public DescribedNodeWrapper(NeoService neo, Node underlyingNode) {
        super(neo, underlyingNode);
    }
    
    public String getName() {
        return (String) getUnderlyingNode().getProperty(NAME_PROPERTY);
    }

    public void setName(String name) {
        getUnderlyingNode().setProperty(NAME_PROPERTY, name);
        
    }

    public String getDescription() {
        return (String) getUnderlyingNode().getProperty(DESCRIPTION_PROPERTY);
    }
    
    public void setDescription(String description) {
        getUnderlyingNode().setProperty(DESCRIPTION_PROPERTY, description);
    }

}
