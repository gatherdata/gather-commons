package org.gatherdata.iandt.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public abstract class AbstractGatherBundleIntegrationTests extends
		AbstractConfigurableBundleCreatorTests {
	
	@Override
	protected String[] getTestBundlesNames() {
		List<String> allBundles = new ArrayList<String>();
		allBundles.addAll(getProvionedBundleNames());
	    allBundles.addAll(getTestBundlesNameList());

        return (String[]) allBundles.toArray(new String[allBundles.size()]);
	}

	/**
	 * Adds all the bundles which are common to all integration tests.
	 * 
	 * @return
	 */
	protected Collection<String> getProvionedBundleNames() {
		List<String> provisionedBundles = new ArrayList<String>();
		
		provisionedBundles.add("commons-lang, commons-lang, 2.4");
		provisionedBundles.add("commons-logging, commons-logging, 1.1.1");
		provisionedBundles.add("org.apache.commons, com.springsource.org.apache.commons.codec, 1.3.0");
		provisionedBundles.add("javax.activation, com.springsource.javax.activation, 1.1.1");
		provisionedBundles.add("aopalliance, aopalliance, 1.0.0");
		provisionedBundles.add("com.google.code.guice, guice, snapshot-20090105");
		provisionedBundles.add("org.ops4j.peaberry, peaberry, 1.0-rc1");
		provisionedBundles.add("org.easymock, com.springsource.org.easymock, 2.3.0");

		return provisionedBundles;
	}

	protected abstract Collection<String> getTestBundlesNameList();

}