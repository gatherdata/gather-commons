/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.neo4j;

import org.neo4j.api.core.NeoService;
import org.neo4j.util.index.IndexService;

public interface NeoServices {

    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#manualShutdown()
     */
    public abstract void manualShutdown();

    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#addLuceneIndexService()
     */
    public abstract IndexService addLuceneIndexService();

    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#addNeoIndexService()
     */
    public abstract IndexService addNeoIndexService();

    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#addIndexService(org.neo4j.util.index.IndexService)
     */
    public abstract IndexService addIndexService(IndexService indexService);

    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#neo()
     */
    public abstract NeoService neo();

    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#indexService()
     */
    public abstract IndexService indexService();

}