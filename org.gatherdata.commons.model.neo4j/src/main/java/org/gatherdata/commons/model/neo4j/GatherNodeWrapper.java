/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.model.neo4j;

import org.neo4j.util.NodeWrapper;

/**
 * Neo4j node wrapper interface for UniqueEntity instances.
 * 
 */
public interface GatherNodeWrapper extends NodeWrapper {
    
    public static final String GATHER_NODETYPE_PROPERTY = "GatherNodeType";
    
    public static final String UID_PROPERTY = "uid";
    
    public static final String DATE_CREATED_PROPERTY = "dateCreated";

    public static final String NAME_PROPERTY = "name";

    public static final String DESCRIPTION_PROPERTY = "description";

}
