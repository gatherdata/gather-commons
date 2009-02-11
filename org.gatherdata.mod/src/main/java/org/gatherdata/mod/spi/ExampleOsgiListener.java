package org.gatherdata.mod.spi;

import org.osgi.framework.BundleListener;
import org.osgi.framework.ServiceListener;

/**
 * Example OSGI Service
 *
 */
public interface ExampleOsgiListener extends ServiceListener, BundleListener {

    /**
     * Sends a notice using this service.
     */
    public boolean notify(String notice);

}
