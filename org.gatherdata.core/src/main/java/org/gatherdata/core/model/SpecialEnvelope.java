package org.gatherdata.core.model;

import org.gatherdata.core.io.MimeTypes;

/**
 * SpecialEnvelopes are used for control flow and other special 
 * purposes within the framework.
 *
 */
public class SpecialEnvelope extends GenericEnvelope {

	/**
	 * 
	 */
	private static final long serialVersionUID = -884024422237377400L;
	
	public static final String LAST_ENVELOPE_QUALIFIER = "LAST_ENVELOPE";
	public static SpecialEnvelope LAST_ENVELOPE;
	
	static {
		LAST_ENVELOPE = new SpecialEnvelope(LAST_ENVELOPE_QUALIFIER);
	}
	
	private SpecialEnvelope(String command) {
		super(null, null, null, MimeTypes.GATHER_COMMAND, command, null, null);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "SpecialEnvelope: " + this.getQualifier();
	}
	
	
}
