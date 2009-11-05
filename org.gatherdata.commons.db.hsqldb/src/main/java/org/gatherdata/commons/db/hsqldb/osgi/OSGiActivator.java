/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.hsqldb.osgi;

import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.gatherdata.commons.db.hsqldb.internal.HsqldbServiceImpl;
import org.gatherdata.commons.db.hsqldb.spi.HsqldbService;

/**
 * Extension of the default OSGi bundle activator
 */
public final class OSGiActivator
    implements BundleActivator
{
    private HsqldbService hsqldbService;
    
	/**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
        System.out.println( "STARTING org.gatherdata.db.hsqldb" );

        Dictionary props = new Properties();
        // add specific service properties here...

        System.out.println( "REGISTER HsqldbService" );

        // Register our example service implementation in the OSGi service registry
        this.hsqldbService = new HsqldbServiceImpl();
        this.hsqldbService.start();
        bc.registerService( HsqldbService.class.getName(), hsqldbService, props );
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING org.gatherdata.db.hsqldb" );

        // stop the hsqldb server
        hsqldbService.stop();
    }
}

