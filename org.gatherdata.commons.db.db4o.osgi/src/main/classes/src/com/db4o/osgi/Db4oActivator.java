/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
public class Db4oActivator implements BundleActivator {

	public final static String BUNDLE_ID = "db4o_osgi";
	
	/**
	 * This method is called when the bundle is started by the Framework. 
	 * The method registers Db4oService, making it available for clients.
	 * @param context The execution context of the bundle being started.
	 * @throws java.lang.Exception If this method throws an exception, this
	 *         bundle is marked as stopped and the Framework will remove this
	 *         bundle's listeners, unregister all services registered by this
	 *         bundle, and release all services used by this bundle. 
	 */
	public void start(BundleContext context) throws Exception {
		context.registerService(
				Db4oService.class.getName(),
				new Db4oServiceFactory(), 
				new Hashtable());		
	}
	
	/**
	 * This method is called when the bundle is stopped by the Framework.
	 * @param context The execution context of the bundle being stopped.
	 * @throws java.lang.Exception If this method throws an exception, the
	 *         bundle is still marked as stopped, and the Framework will remove
	 *         the bundle's listeners, unregister all services registered by the
	 *         bundle, and release all services used by the bundle.
	 */
	public void stop(BundleContext context) throws Exception {
	}

}
