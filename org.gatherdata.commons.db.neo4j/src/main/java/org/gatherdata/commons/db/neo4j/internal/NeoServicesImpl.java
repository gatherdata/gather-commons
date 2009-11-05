/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.neo4j.internal;

import org.gatherdata.commons.db.neo4j.NeoServices;
import org.neo4j.api.core.NeoService;
import org.neo4j.util.index.IndexService;
import org.neo4j.util.index.LuceneIndexService;
import org.neo4j.util.index.NeoIndexService;

/**
 * A copy of the official NeoServiceLifecycle, until that gets refactored into
 * an interface/implementation pair.
 * 
 * Manages the life cycle of a {@link NeoService} as well as other components.
 * Removes the tedious work of having to think about shutting down components
 * and the {@link NeoService} when the JVM exists, in the right order as well.
 */
public class NeoServicesImpl implements NeoServices 
{
    /**
     * Field not final since it's nulled in the shutdown process (to be able
     * to support multiple calls to shutdown).
     */
    private NeoService neoService;
    private IndexService indexService;
    
    /**
     * Constructs a new {@link NeoServiceLifecycle} instance with {@code neo}
     * as the {@link NeoService}. Other components can be instantiated using
     * methods, f.ex. {@link #addIndexService(IndexService)}.
     * 
     * @param neo the {@link NeoService} instance to manage.
     */
    public NeoServicesImpl( NeoService neo )
    {
        this.neoService = neo;
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
                runJvmShutdownHook();
            }
        } );
    }
    
    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#manualShutdown()
     */
    /* (non-Javadoc)
     * @see org.gatherdata.commons.db.neo4j.internal.NeoServices#manualShutdown()
     */
    public void manualShutdown()
    {
        runShutdown();
    }
    
    /**
     * Runs the shutdown process of all started services. Supports multiple
     * calls to it (if such would accidentally be done).
     */
    protected void runShutdown()
    {
        if ( this.indexService != null )
        {
            this.indexService.shutdown();
            this.indexService = null;
        }
        
        if ( this.neoService != null )
        {
            this.neoService.shutdown();
            this.neoService = null;
        }
    }
    
    /**
     * Called right before the JVM exists. It's called from a thread registered
     * with {@link Runtime#addShutdownHook(Thread)}.
     */
    protected void runJvmShutdownHook()
    {
        runShutdown();
    }
    
    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#addLuceneIndexService()
     */
    /* (non-Javadoc)
     * @see org.gatherdata.commons.db.neo4j.internal.NeoServices#addLuceneIndexService()
     */
    public IndexService addLuceneIndexService()
    {
        assertIndexServiceNotInstantiated();
        return addIndexService( new LuceneIndexService( this.neoService ) );
    }
    
    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#addNeoIndexService()
     */
    /* (non-Javadoc)
     * @see org.gatherdata.commons.db.neo4j.internal.NeoServices#addNeoIndexService()
     */
    public IndexService addNeoIndexService()
    {
        assertIndexServiceNotInstantiated();
        return addIndexService( new NeoIndexService( this.neoService ) );
    }
    
    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#addIndexService(org.neo4j.util.index.IndexService)
     */
    /* (non-Javadoc)
     * @see org.gatherdata.commons.db.neo4j.internal.NeoServices#addIndexService(org.neo4j.util.index.IndexService)
     */
    public IndexService addIndexService( IndexService indexService )
    {
        assertIndexServiceNotInstantiated();
        this.indexService = indexService;
        return indexService;
    }
    
    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#neo()
     */
    /* (non-Javadoc)
     * @see org.gatherdata.commons.db.neo4j.internal.NeoServices#neo()
     */
    public NeoService neo()
    {
        return this.neoService;
    }
    
    /* (non-Javadoc)
     * @see org.neo4j.util.INeoServiceLifecycle#indexService()
     */
    /* (non-Javadoc)
     * @see org.gatherdata.commons.db.neo4j.internal.NeoServices#indexService()
     */
    public IndexService indexService()
    {
        return this.indexService;
    }

    private void assertIndexServiceNotInstantiated()
    {
        if ( this.indexService != null )
        {
            throw new UnsupportedOperationException( "This utility class " +
                "only supports zero or one IndexService, there's already a " +
                this.indexService + " instantiated" );
        }
    }
}
