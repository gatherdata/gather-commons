package org.gatherdata.core.io;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.apache.commons.lang.builder.EqualsBuilder;

public class MimeTypes {

	public static MimeType GATHER_COMMAND = null;

	public static MimeType TEXT_PLAIN = null;
	
	public static MimeType TEXT_MAP = null;

	public static MimeType TEXT_PROPERTY = null;
	
	public static MimeType TEXT_XML = null;
	
	public static MimeType TEXT_JSON = null;
	
	public static MimeType TEXT_YAML = null;
	
	public static MimeType JAVA_SERIALIZED = null;
	
	public static MimeType JAVA_OBJECT = null;
	
	public static MimeType OCTET_STREAM = null;
	
	static {
		try {
			TEXT_PLAIN = new MimeType("text", "plain");
			TEXT_PROPERTY = new MimeType("text", "property");
			TEXT_XML = new MimeType("text", "xml");
			TEXT_JSON = new MimeType("text", "json");
			TEXT_YAML = new MimeType("text", "yaml");
			TEXT_MAP = new MimeType("text", "map");
			JAVA_SERIALIZED = new MimeType("application", "java-serialized-object");
			JAVA_OBJECT = new MimeType("applicaiton", "java-object");
			OCTET_STREAM = new MimeType("application", "octet-stream");
			GATHER_COMMAND = new MimeType("application", "vnd.gatherdata-command");
		} catch (MimeTypeParseException e) {
			e.printStackTrace();
		}
	}

    public static boolean equals(MimeType lhs, MimeType rhs) {
        boolean areEqual = false;
        if (lhs == rhs) {
            areEqual = true;
        } else if ((lhs == null) || (rhs == null)) {
            areEqual = false;
        } else {
            areEqual = lhs.getBaseType().equals(rhs.getBaseType());
        }
        return areEqual;
    }
    
    
}
