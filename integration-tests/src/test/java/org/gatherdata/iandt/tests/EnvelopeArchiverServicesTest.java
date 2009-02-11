package org.gatherdata.iandt.tests;

import static org.easymock.EasyMock.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;
import org.springframework.osgi.util.OsgiStringUtils;
import org.springframework.util.FileSystemUtils;
import org.gatherdata.core.io.MimeTypes;
import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.model.TextEnvelope;
import org.gatherdata.core.model.factory.PlainTextFactory;
import org.gatherdata.storage.archiver.spi.ArchiverDao;
import org.gatherdata.storage.archiver.spi.EnvelopeArchiver;
import org.osgi.framework.Constants;
import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * EnvelopArchiver integration tests.
 * 
 * Note: remember that fixes to bundles under test must be re-installed to 
 * the local repository because that's what is used to populate the runtime.
 *
 */
public class EnvelopeArchiverServicesTest extends AbstractGatherBundleIntegrationTests {

	@Override
	protected Collection<String> getTestBundlesNameList() {
		List<String> testContextBundles = new ArrayList<String>();
		testContextBundles.add("org.gatherdata.gather-common, org.gatherdata.core, 1.0-SNAPSHOT");
		testContextBundles.add("org.gatherdata.gather-common, org.gatherdata.storage.archiver, 1.0-SNAPSHOT");
		return testContextBundles;
	}

    public void testEnvelopeArchiverIsRegisteredAsService() throws Exception {
    	ServiceReference envelopeArchiverRef = bundleContext.getServiceReference(EnvelopeArchiver.class.getName());
    	EnvelopeArchiver envelopeArchiver = (EnvelopeArchiver)bundleContext.getService(envelopeArchiverRef);
    	assertNotNull(envelopeArchiver);
    }

    /**
     * Tests that the EnvelopeArchiver use the injected ArchiverDao to save an Envelope.
     */
    public void testEnvelopeArchiverUsesInjectedDaoForSave() {
    	ArchiverDao mockDao = registerMockArchiverDaoService();
    			
    	// get the EnvelopeArchiver service
    	ServiceReference envelopeArchiverRef = bundleContext.getServiceReference(EnvelopeArchiver.class.getName());
    	EnvelopeArchiver envelopeArchiver = (EnvelopeArchiver)bundleContext.getService(envelopeArchiverRef);

    	// create a test envelope
    	Envelope<? extends Serializable> testEnvelope = PlainTextFactory.stuff("This is a test.");
    	
    	// establish expected behavior
    	expect(mockDao.save(testEnvelope)).andReturn(null);
    	replay(mockDao);
    	
    	// save the Envelope
    	envelopeArchiver.save(testEnvelope);
    	
    	// check that the mock dao was called
    	verify(mockDao);
    }
    
    /**
     * Tests that the EnvelopeArchiver use the injected ArchiverDao to get an Envelope.
     */
    public void testEnvelopeArchiverUsesInjectedDaoForGet() {
    	ArchiverDao mockDao = registerMockArchiverDaoService();
    			
    	// get the EnvelopeArchiver service
    	ServiceReference envelopeArchiverRef = bundleContext.getServiceReference(EnvelopeArchiver.class.getName());
    	EnvelopeArchiver envelopeArchiver = (EnvelopeArchiver)bundleContext.getService(envelopeArchiverRef);

    	// create a test envelope
    	Envelope<? extends Serializable> testEnvelope = PlainTextFactory.stuff("This is a test.");
    	
    	// establish expected behavior
    	expect(mockDao.getEnvelope(testEnvelope.getUid())).andReturn(null);
    	replay(mockDao);
    	
    	// get the Envelope
    	envelopeArchiver.getEnvelope(testEnvelope.getUid());
    	
    	// check that the mock dao was called
    	verify(mockDao);
    }


    /**
     * Tests that the EnvelopeArchiver use the injected ArchiverDao to remove an Envelope.
     */
    public void testEnvelopeArchiverUsesInjectedDaoForRemove() {
    	ArchiverDao mockDao = registerMockArchiverDaoService();
    			
    	// get the EnvelopeArchiver service
    	ServiceReference envelopeArchiverRef = bundleContext.getServiceReference(EnvelopeArchiver.class.getName());
    	EnvelopeArchiver envelopeArchiver = (EnvelopeArchiver)bundleContext.getService(envelopeArchiverRef);

    	// create a test envelope
    	Envelope<? extends Serializable> testEnvelope = PlainTextFactory.stuff("This is a test.");
    	
    	// establish expected behavior
    	mockDao.remove(testEnvelope.getUid());
    	replay(mockDao);
    	
    	// remove the Envelope
    	envelopeArchiver.remove(testEnvelope.getUid());
    	
    	// check that the mock dao was called
    	verify(mockDao);
    }
    
	private ArchiverDao registerMockArchiverDaoService() {
		Hashtable<String,Object> properties = new Hashtable<String, Object>();
	      properties.put(Constants.SERVICE_PID,
	          "org.gatherdata.iandt.tests.archiver.dao.mock");
	      properties.put(Constants.SERVICE_DESCRIPTION, "Mock ArchiverDao. ");
	      properties.put(Constants.SERVICE_VENDOR, "gather");
	      properties.put(Constants.SERVICE_RANKING, new Integer(
	          1000));
	      ArchiverDao mockDao = createMock(ArchiverDao.class);
	      ServiceRegistration registration = bundleContext.registerService(ArchiverDao.class
	          .getName(), mockDao, properties);
	      return mockDao;
		
	}
}

