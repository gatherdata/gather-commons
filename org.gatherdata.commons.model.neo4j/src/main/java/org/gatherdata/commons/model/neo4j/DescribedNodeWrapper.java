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
