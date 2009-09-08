package org.gatherdata.commons.db.neo4j.osgi;

import java.util.Dictionary;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.gatherdata.commons.db.neo4j.NeoServices;
import org.gatherdata.commons.db.neo4j.internal.NeoServicesImpl;
import org.neo4j.api.core.EmbeddedNeo;
import org.neo4j.api.core.NeoService;
import org.neo4j.util.index.IndexService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Extension of the default OSGi bundle activator
 */
public final class OSGiActivator implements BundleActivator {
    
    Logger log = Logger.getLogger(OSGiActivator.class.getName());
    
    NeoServices neo;
    
    /**
     * Called whenever the OSGi framework starts our bundle
     */
    public void start(BundleContext bc) throws Exception {

        NeoService innerNeo = new EmbeddedNeo("neo");
        neo = new NeoServicesImpl(innerNeo);
        IndexService indexService = neo.addLuceneIndexService();
        
        if (indexService == null) {
            log.log(Level.SEVERE, "IndexService was not added to NeoServices");
        }

        bc.registerService(NeoServices.class.getName(), neo, null);
    }

    /**
     * Called whenever the OSGi framework stops our bundle
     */
    public void stop(BundleContext bc) throws Exception {
        neo.manualShutdown();
    }
}
