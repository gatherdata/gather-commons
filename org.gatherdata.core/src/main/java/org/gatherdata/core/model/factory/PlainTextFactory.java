package org.gatherdata.core.model.factory;

import java.net.URI;

import org.gatherdata.core.io.MimeTypes;
import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.model.TextEnvelope;
import org.gatherdata.core.net.DnsUrlFactory;

/**
 * A factory for stuffing plain text content into a sealed Envelope<String>.
 * 
 */
public class PlainTextFactory {

	/**
	 * Stuffs content into a sealed Envelope, presuming the localhost
	 * as the source.
	 * 
	 * @param content plain text to be stuffed
	 * @return a sealed Envelope containing the content
	 */
	public static Envelope stuff(String content) {

		return stuff(content, DnsUrlFactory.getLocalUri());	
	}
	
	public static Envelope stuff(String content, URI fromSource) {
		return new TextEnvelope(fromSource, content, MimeTypes.TEXT_PLAIN);		
	}
}
