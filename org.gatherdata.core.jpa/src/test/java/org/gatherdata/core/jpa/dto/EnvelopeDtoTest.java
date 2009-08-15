package org.gatherdata.core.jpa.dto;


import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.gatherdata.core.io.MimeTypes;
import org.gatherdata.core.model.GenericEnvelope;
import org.gatherdata.core.model.TextEnvelope;
import org.gatherdata.core.net.CbidFactory;
import org.gatherdata.core.net.GatherUrnFactory;
import org.junit.Before;
import org.junit.Test;

public class EnvelopeDtoTest {
	
	EntityManagerFactory emf;
	
	EntityManager em = null;
	
	GatherUrnFactory urnFactory;
	
	@Before
	public void setupEntityManager() {
		emf = Persistence.createEntityManagerFactory("GatherCoreJpaPersistenceUnit");

		em = emf.createEntityManager();
		
		urnFactory = new GatherUrnFactory();
	}
	
	@Test
	public void shouldSaveToGenericJpaEntityManager() {
        final String content = "foo";

        EnvelopeDTO sampleDTO = new EnvelopeDTO();
	    sampleDTO.setContents(content);
	    sampleDTO.setDateTimeStamp(GregorianCalendar.getInstance());
	    sampleDTO.setSource(urnFactory.getLocalUrn());
	    sampleDTO.setUid(CbidFactory.createCbid(content));
		em.persist(sampleDTO);
	}
	
	@Test
	public void shouldEqualEnvelopeFromWhichItWasDerived() {
	    final String content = "generic content of an envelope";
	    GenericEnvelope sourceEnvelope = new GenericEnvelope(urnFactory.getLocalUrn(), content, MimeTypes.TEXT_PLAIN);
	    EnvelopeDTO dtoOfEnvelope = new EnvelopeDTO(sourceEnvelope);
	    assertEquals(sourceEnvelope, dtoOfEnvelope);
	    assertEquals(dtoOfEnvelope, sourceEnvelope);
	}
	
	@Test
	public void shouldPreserveQualifierOfEnvelopeFromWhichItWasDerived() {
	    TextEnvelope sourceEnvelope = new TextEnvelope(urnFactory.getLocalUrn(), "plain text in english", MimeTypes.TEXT_PLAIN);
	    sourceEnvelope.setQualifier("en_US");
	    EnvelopeDTO dtoOfEnvelope = new EnvelopeDTO(sourceEnvelope);
	    assertEquals(sourceEnvelope.getQualifier(), dtoOfEnvelope.getQualifier());
	}
}
