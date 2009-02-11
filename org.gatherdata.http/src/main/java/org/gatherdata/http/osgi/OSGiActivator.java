package org.gatherdata.http.osgi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


/**
 * The OSGi activator for the Gather Archiver Storage bundle.
 * 
 **/
public class OSGiActivator implements BundleActivator {
	
	Log log = LogFactory.getLog(OSGiActivator.class);
	
	/**
	 * Implements BundleActivator.start().
	 * 
	 * Adds itself as a ServiceListener to the OSGi context and registers a
	 * Service.
	 * 
	 * @param bc the OSGi framework context for the bundle.
	 **/
	public void start(BundleContext bc) throws Exception {
		
		System.out.println("Gather HTTP Library started");
	}

	/**
	 * Implements BundleActivator.stop(). Prints a message and removes itself
	 * from the bundle context as a service listener.
	 * 
	 * @param context
	 *            the framework context for the bundle.
	 **/
	public void stop(BundleContext context) {
	    System.out.println("Gather HTTP Library stopped");
	}
	
}
