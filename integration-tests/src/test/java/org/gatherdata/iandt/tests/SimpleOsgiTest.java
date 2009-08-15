package org.gatherdata.iandt.tests;

import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Enumeration;

import org.springframework.osgi.util.OsgiStringUtils;
import org.gatherdata.core.io.MimeTypes;
import org.osgi.framework.Constants;
import org.osgi.framework.Bundle;

public class SimpleOsgiTest extends AbstractGatherBundleIntegrationTests {

	@Override
	protected Collection<String> getTestBundlesNameList() {
		return Collections.emptyList();
	}
	
    public void testOsgiPlatformStarts() throws Exception {
    	System.out.println("Framework details...");
        System.out.println(bundleContext.getProperty(Constants.FRAMEWORK_VENDOR));
        System.out.println(bundleContext.getProperty(Constants.FRAMEWORK_VERSION));
        System.out.println(bundleContext.getProperty(Constants.FRAMEWORK_EXECUTIONENVIRONMENT));
    }

    public void testOsgiEnvironment() throws Exception {
    	System.out.println("Installed bundles...");
        Bundle[] bundles = bundleContext.getBundles();
        for (int i = 0; i < bundles.length; i++) {
            System.out.println("Bundle: " + OsgiStringUtils.nullSafeName(bundles[i]));
            Dictionary headers = bundles[i].getHeaders();
            Enumeration keys = headers.keys();
            while (keys.hasMoreElements()) {
            	Object key = keys.nextElement();
            	System.out.println(key + " : " + headers.get(key));
            }
            System.out.println();
        }
    }
    
}

