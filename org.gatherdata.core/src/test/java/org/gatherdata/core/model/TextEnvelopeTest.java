package org.gatherdata.core.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

import javax.activation.MimeType;

import org.gatherdata.core.io.MimeTypes;
import org.junit.Test;


public class TextEnvelopeTest extends BaseEnvelopeTest<String> {

	Integer uidCounter = 0;
	Integer contentsCounter = 0;
	
	@Override
	Envelope createEnvelope(URI uid, URI source, String contents,
			MimeType type, String qualifier, Seal seal, Calendar dateTimeStamp) {
		return new TextEnvelope(uid, source, contents, type, qualifier, seal, dateTimeStamp);
	}

	@Override
	Envelope createFilledEnvelopeUnderTest() {
		URI uid = null;
		try {
			uid = new URI("mock", uidCounter.toString(), null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new TextEnvelope(uid, createMockContents(), MimeTypes.TEXT_PLAIN);
	}

	@Override
	String createMockContents() {
		return "mock contents #" + contentsCounter++;
	}

	@Override
	Envelope duplicate(Envelope source) {
		TextEnvelope duplicate = new TextEnvelope(
				source.getUid(),
				source.getSource(),
				(String) source.getContents(),
				source.getType(),
				source.getQualifier(),
				source.getSeal(),
				source.getDateTimeStamp());
		return duplicate;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void shouldOnlyAcceptTextMimeTypes() {
		TextEnvelope normalEnvelope = (TextEnvelope) createFilledEnvelopeUnderTest();
		@SuppressWarnings("unused")
		TextEnvelope badEnvelope = new TextEnvelope(normalEnvelope.getUid(), normalEnvelope.getSource(), (String) normalEnvelope.getContents(), MimeTypes.OCTET_STREAM, 
				normalEnvelope.getQualifier(), normalEnvelope.getSeal(), normalEnvelope.getDateTimeStamp());
	}
	
}
