/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.model.neo4j;

import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;

public interface NodeAdapter<EntityType, NodeWrapperType> {

    public EntityType adaptFromNode(Node n, NeoService neo);
    
    public NodeWrapperType deriveInstanceFrom(EntityType template, NeoService neo);

}
