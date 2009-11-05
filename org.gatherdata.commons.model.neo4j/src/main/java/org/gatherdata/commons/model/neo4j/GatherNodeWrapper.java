/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
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
