package org.gatherdata.mock.factories;

import org.gatherdata.core.io.MimeTypes;
import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.model.TextEnvelope;
import org.gatherdata.core.net.GatherUrnFactory;
import org.gatherdata.mock.spi.MockEnvelopeFactory;

public class MockTextEnvelopeFactory implements MockEnvelopeFactory<String> {

	GatherUrnFactory urnFactory = new GatherUrnFactory();
	
	private String plainContentsBase = "plain contents";
	private int plainContentsCounter = 0;
	
	public Envelope createEnvelope() {
		TextEnvelope mockEnvelope = new TextEnvelope(urnFactory.getLocalUrn(), 
				createPlainContents(), 
				MimeTypes.TEXT_PLAIN);
		return mockEnvelope;
	}

	public String createPlainContents() {
		return plainContentsBase + plainContentsCounter++;
	}

}
