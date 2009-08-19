package org.gatherdata.commons.io;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.SerializationUtils;

/**
 * The MimeSerializer is a utility class for performing
 * serialization of data based on MimeType.
 *
 */
public class MimeSerializer {

	private static String UTF_8_CHARSET = "UTF-8";

	/**
	 * Convert Serializable data into a byte array by presuming 
	 * a MimeType mapping for certain object classes.
	 * 
	 * <ul>
	 * <li>String: text/plain
	 * <li>default: application/java-serialized-object
	 * </ul>
	 * 
	 * For more explicit control over Serialization, use one
	 * of the method calls that accepts a MimeType parameter.
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] serialize(Serializable data) {
		byte[] serialized = null;
		if (data instanceof String) {
			serialized = serialize(MimeTypes.TEXT_PLAIN, data);
		} else if (data instanceof byte[]) {
			serialized = serialize(MimeTypes.OCTET_STREAM, data);
		} else {
			serialized = serialize(MimeTypes.JAVA_SERIALIZED, data);
		}
		return serialized;
	}

	public static byte[] serialize(String mimeType, Serializable data) {
		byte[] serialized = null;
		
		if (mimeType != null) {
			if (mimeType.equals(MimeTypes.TEXT_PLAIN)) {
				try {
					serialized = ((String)data).getBytes(UTF_8_CHARSET);
				} catch (UnsupportedEncodingException e) {
					// UTF-8 is a required encoding for all platforms,
					// so getting this exception is a fatal problem for
					// the platform.
					throw new RuntimeException("UTF-8 encoding not supported on platform.", e);
				}		
			} else if (mimeType.equals(MimeTypes.OCTET_STREAM)){
				serialized = (byte[])data;
			} else {
				serialized = SerializationUtils.serialize(data);
			}
		}

		return serialized;
	}
}
