package org.gatherdata.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.net.URI;
import java.util.Calendar;

import javax.activation.MimeType;

import org.junit.Test;

/**
 * A base set of tests for the expected behavior of an Envelope implementation.
 * 
 */
public abstract class BaseEnvelopeTest<X extends Serializable> {

	abstract X createMockContents();
	abstract Envelope createFilledEnvelopeUnderTest();
	abstract Envelope createEnvelope(URI uid, URI source,
			X contents, MimeType type, String qualifier, Seal seal, Calendar dateTimeStamp);
	abstract Envelope duplicate(Envelope source);
	
	/**
	 * The factory method used for testing subclasses of Envelope
	 * should be able to construct an instance of the Envelope under test.
	 */
	@Test
	public void factoryShouldProvideMockEnvelopeInstances() {
		Envelope testEnvelope = createFilledEnvelopeUnderTest();
	
		assertNotNull(testEnvelope);
	}
	

	/**
	 * Repeated calls to the {@link #createFilledEnvelopeUnderTest() factory method
	 * should provide instances which are different.
	 */
	@Test
	public void factoryShouldProvideDifferentInstances() {
		Envelope testEnvelopeA = createFilledEnvelopeUnderTest();
		Envelope testEnvelopeB = createFilledEnvelopeUnderTest();
		Envelope testEnvelopeC = createFilledEnvelopeUnderTest();
		
		assertFalse(testEnvelopeA.equals(testEnvelopeB));
		assertFalse(testEnvelopeB.equals(testEnvelopeC));
		assertFalse(testEnvelopeC.equals(testEnvelopeA));
	}
	
	/**
	 * Repeated calls to the {@link #createMockContents()} factory
	 * method should provide different instances of content.
	 */
	@Test
	public void mockContentInstancesShouldNotBeSameObject() {
		X contentsA = createMockContents();
		X contentsB = createMockContents();
		X contentsC = createMockContents();
		
		assertFalse(contentsA == contentsB);
		assertFalse(contentsB == contentsC);
		assertFalse(contentsC == contentsA);
	}

	/**
	 * Envelopes are expected to be Cloneable, such that it is true
	 * that <code>x.clone().equals(x)</code>. 
	 */
	@Test
	public void shouldEqualClone() {
		Envelope testEnvelope = createFilledEnvelopeUnderTest();
		Envelope clonedEnvelope = (Envelope) testEnvelope.clone();
		
		assertNotSame(testEnvelope, clonedEnvelope);
		assertEquals(testEnvelope, clonedEnvelope);
	}
	
	@Test
	public void cloneShouldHashToSameValue() {
		Envelope testEnvelope = createFilledEnvelopeUnderTest();
		Envelope clonedEnvelope = (Envelope) testEnvelope.clone();
		
		assertEquals(testEnvelope.hashCode(), clonedEnvelope.hashCode());
	}
	
	@Test
	public void shouldEqualEnvelopeWithSameContent() {
		Envelope testEnvelope = createFilledEnvelopeUnderTest();
		Envelope duplicateEnvelope = duplicate(testEnvelope);
		
		assertEquals(testEnvelope, duplicateEnvelope);
	}
	

	/**
	 * The Envelope should validate that the Seal
	 * is "un-broken" -- the contents should 
	 * validate against the Seal.
	 */
	@Test
	public void shouldValidateUnchangedContents() {
		Envelope testEnvelope = createFilledEnvelopeUnderTest();
		
		assertNotNull(testEnvelope.getContents());
		assertTrue(testEnvelope.isValid());
	}
	
	/**
	 * Changing the contents of an Envelope should
	 * render it invalid.
	 * 
	 */
	@Test
	public void shouldInvalidateChangedContents() {
		Envelope testEnvelope = createFilledEnvelopeUnderTest();
		
		// since Envelopes are immutable, create a new Envelope
		// filled with different contents
		X differentContents = createMockContents();
		Envelope modifiedDuplicateEnvelope = createEnvelope(
				testEnvelope.getUid(),
				testEnvelope.getSource(),
				differentContents,
				testEnvelope.getType(),
				testEnvelope.getQualifier(),
				testEnvelope.getSeal(),
				testEnvelope.getDateTimeStamp());
		
		assertNotNull(modifiedDuplicateEnvelope.getContents());
		assertFalse(modifiedDuplicateEnvelope.isValid());
	}
	
}
