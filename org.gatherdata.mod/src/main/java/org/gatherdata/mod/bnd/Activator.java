package org.gatherdata.mod.bnd;

import static com.google.inject.Guice.createInjector;
import static org.ops4j.peaberry.Peaberry.osgiModule;

import org.gatherdata.mod.internal.ExampleOsgiListenerImpl;
import org.gatherdata.mod.spi.ExampleOsgiListener;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.*;
import org.osgi.util.tracker.ServiceTracker;

import com.google.inject.Inject;

/**
 * This is an example OSGi Activator, which is the main point of 
 * contact between the framework and the bundle. The Activator
 * receives the context for the bundle, providing the opportunity
 * to participate in the OSGi framework.
 * 
 * Also, the Activator registers a sample service as part of the
 * {@link #start(BundleContext)} bundle lifecycle stage.
 **/
public class Activator implements BundleActivator {
		
	ExampleOsgiListener exampleService = null;
	
	@Inject
	LogService log;
	
	/**
	 * Implements BundleActivator.start().
	 * 
	 * Adds itself as a ServiceListener to the OSGi context and registers a
	 * Service.
	 * 
	 * @param bc the OSGi framework context for the bundle.
	 **/
	public void start(BundleContext bc) {
		
		createInjector(osgiModule(bc), new GuiceImportModule()).injectMembers(this);
		
		log("kolly started");
	}

	/**
	 * Implements BundleActivator.stop(). Prints a message and removes itself
	 * from the bundle context as a service listener.
	 * 
	 * @param context
	 *            the framework context for the bundle.
	 **/
	public void stop(BundleContext context) {
		log("kolly stopped");
		// Note: It is not required that we remove the listener here,
		// since the framework will do it automatically anyway.
		// These are here included for clarity. 
		context.removeServiceListener(exampleService);
		context.removeBundleListener(exampleService);
	}
	
	void log(final String message) {
	      if (log != null) log.log(LogService.LOG_INFO, message);
	      else System.err.println(message);
	    }
}
