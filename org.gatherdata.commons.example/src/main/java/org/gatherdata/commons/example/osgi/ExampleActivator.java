/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.example.osgi;

import java.util.Dictionary;
import java.util.Properties;

import org.gatherdata.commons.example.ExampleService;
import org.ops4j.peaberry.Export;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.google.inject.Inject;

import static org.ops4j.peaberry.Peaberry.osgiModule;
import static com.google.inject.Guice.createInjector;

/**
 * Extension of the default OSGi bundle activator
 */
public final class ExampleActivator
    implements BundleActivator
{
	/**
	 * Though not used in the activator, the example service must
	 * be injected into a type qualified export to be made available
	 * using Peaberry.
	 */
    @Inject
    Export<ExampleService> exampleExportedService;

    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
        System.out.println( "STARTING Example Bundle" );
     
        // create a Google Guice injector, using Peaberry and our custom GuiceBindingModule
        createInjector(osgiModule(bc), new GuiceBindingModule()).injectMembers(this);
        
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING Example Bundle" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

