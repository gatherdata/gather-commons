package org.gatherdata.core.model;

import java.net.URI;
import java.util.Calendar;

import javax.activation.MimeType;

import org.gatherdata.core.net.CbidFactory;

public class TextEnvelope extends GenericEnvelope implements Envelope {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2352633578582812859L;

	/**
	 * Construct a new TextEnvelope with minimal information.
	 * 
	 * The uid will be set using {@link CbidFactory#createCbid(java.io.Serializable)}.
	 * The seal will be calculated.
	 * The dateTimeStamp will be presumed to be now. 
	 * 
	 * @param source URI of the source which provided the content
	 * @param contents
	 * @param mimeType
	 */
	public TextEnvelope(URI source, String contents, MimeType mimeType) {
		super(source, contents, mimeType);
		
		verifyType(mimeType);
	}

	public TextEnvelope(URI uid, URI source, String content, MimeType mimeType, String qualifier, Seal seal, Calendar dateTimeStamp) {
		super(uid, source, content, mimeType, qualifier, seal, dateTimeStamp);
		
		verifyType(mimeType);
	}
	
	private void verifyType(MimeType mimeType) {
		if (!mimeType.getPrimaryType().equals("text")) {
			throw new IllegalArgumentException("TextEnvelopes must be a \"text\" mime primary type");
		}
	}
	
}