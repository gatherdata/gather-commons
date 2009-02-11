package org.gatherdata.mod.internal;

import org.gatherdata.mod.spi.ExampleOsgiListener;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.ServiceEvent;

public class ExampleOsgiListenerImpl implements ExampleOsgiListener {

	

	/**
	 * Implementation of GatherService.notify().
	 * 
	 */
	public boolean notify(String notice) {
		System.out.println(notice);
		return true;
	}

	/**
	 * Implementation of ServiceListener.serviceChanged(), which 
	 * prints out service event details.
	 * 
	 * @param event
	 *            the fired service event.
	 **/
	public void serviceChanged(ServiceEvent event) {
		String[] objectClass = (String[]) event.getServiceReference()
				.getProperty("objectClass");

		if (event.getType() == ServiceEvent.REGISTERED) {
			System.out.println("Gather Listener: Service of type " + objectClass[0]
					+ " registered.");
		} else if (event.getType() == ServiceEvent.UNREGISTERING) {
			System.out.println("Gather Listener: Service of type " + objectClass[0]
					+ " unregistered.");
		} else if (event.getType() == ServiceEvent.MODIFIED) {
			System.out.println("Gather Listener: Service of type " + objectClass[0]
					+ " modified.");
		}
	}

	/**
	 * Implementation of BundleListener.bundleChanged(), which 
	 * prints out bundle event details.
	 * 
	 * @param event
	 *            the fired service event.
	 **/
	public void bundleChanged(BundleEvent event) {
		Bundle bundle = event.getBundle();
		if (event.getType() == BundleEvent.INSTALLED) {
			System.out.println("Gather Listener: Bundle \"" + bundle.getSymbolicName()
					+ "\" installed.");
		} else if (event.getType() == BundleEvent.STARTED) {
			System.out.println("Gather Listener: Bundle \"" + bundle.getSymbolicName()
					+ "\" started.");
		} else if (event.getType() == BundleEvent.STOPPED) {
			System.out.println("Gather Listener: Bundle \"" + bundle.getSymbolicName()
					+ "\" stopped.");
		} else if (event.getType() == BundleEvent.UPDATED) {
			System.out.println("Gather Listener: Bundle \"" + bundle.getSymbolicName()
					+ "\" updated.");
		}
	}
}
