package org.gatherdata.mock.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.gatherdata.mock.spi.MockEnvelopeService;
import org.gatherdata.mock.spi.support.MockEnvelopeServiceImpl;

/**
 * Extension of the default OSGi bundle activator
 */
public final class MockActivator
    implements BundleActivator
{
	
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
        System.out.println( "STARTING org.gatherdata.mock" );

        System.out.println( "REGISTER org.gatherdata.mock.spi.MockEnvelopeService" );

        // Register our example service implementation in the OSGi service registry
        bc.registerService( MockEnvelopeService.class.getName(), new MockEnvelopeServiceImpl(), null );
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
        System.out.println( "STOPPING org.gatherdata.mock" );

        // no need to unregister our service - the OSGi framework handles it for us
    }
}

