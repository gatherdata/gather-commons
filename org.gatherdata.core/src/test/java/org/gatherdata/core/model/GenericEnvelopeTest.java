package org.gatherdata.core.model;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;

import javax.activation.MimeType;

import org.gatherdata.core.io.MimeTypes;


/**
 * Unit testing for the behavior of GenericEnvelope.
 *
 */
@SuppressWarnings("unchecked")
public class GenericEnvelopeTest extends BaseEnvelopeTest {

	Integer uidCounter = 0;
	Integer contentsCounter = 0;
	
	@Override
	Envelope createEnvelope(URI uid, URI source,
			Serializable contents, MimeType type, String qualifier, Seal seal, Calendar dateTimeStamp) {
		return new GenericEnvelope(uid, source, contents, type, qualifier, seal, dateTimeStamp);
	}

	@Override
	Envelope createFilledEnvelopeUnderTest() {
		URI uid = null;
		try {
			uid = new URI("mock", uidCounter.toString(), null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new GenericEnvelope(uid, createMockContents(), MimeTypes.TEXT_PROPERTY);
	}

	@Override
	Serializable createMockContents() {
		return "some.value=test" + contentsCounter++;
	}

	@Override
	Envelope duplicate(Envelope source) {
		GenericEnvelope duplicate = new GenericEnvelope(
				source.getUid(),
				source.getSource(),
				source.getContents(),
				source.getType(),
				source.getQualifier(),
				source.getSeal(),
				source.getDateTimeStamp());
		return duplicate;
	}
	

}
