package org.gatherdata.commons.db.db4o.example.internal;

import java.util.Dictionary;
import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Extension of the default OSGi bundle activator
 */
public final class ExampleActivator
    implements BundleActivator
{
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
        System.out.println( "STARTING org.gatherdata.commons.db.db4o.example" );
        
        ExampleDb4oAccess exampleDb4oAccess = new ExampleDb4oAccess();
        
        exampleDb4oAccess.demo(bc);
        
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING org.gatherdata.commons.db.db4o.example" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

