/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.workflow.bnd;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gatherdata.workflow.internal.WorkflowManagerImpl;
import org.gatherdata.workflow.spi.WorkflowManager;

/**
 * Extension of the default OSGi bundle activator
 */
public final class WorkflowActivator
    implements BundleActivator
{
	private static final Log logger = LogFactory.getLog(WorkflowActivator.class);
	
	private final WorkflowManagerImpl workflowManager = new WorkflowManagerImpl();
	
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start( BundleContext bc )
        throws Exception
    {
    	logger.info("STARTING org.gatherdata.workflow" );
    	
        logger.info( "REGISTER org.gatherdata.workflow.Workflow" );

        bc.registerService( WorkflowManager.class.getName(), workflowManager, null );
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop( BundleContext bc )
        throws Exception
    {
    	logger.info( "STOPPING org.gatherdata.workflow" );

    	workflowManager.shutdown();
    }
}

