package org.gatherdata.commons.io;

import static org.junit.Assert.assertArrayEquals;

import org.gatherdata.commons.io.MimeSerializer;
import org.junit.Test;


public class MimeSerializerTest {

	/**
	 * Serialized Strings should be just converted into a byte
	 * array, instead of being converted into an object. 
	 * 
	 */
	@Test
	public void shouldSerializeStringsAsTextPlain() {
		String originalString = "this should be sealed.\n";
		
		byte[] EXPECTED_BYTES = new byte[] { 
				0x74,  0x68,  0x69,  0x73,  0x20,  0x73,  0x68,  0x6f,
				0x75,  0x6c,  0x64,  0x20,  0x62,  0x65,  0x20,  0x73,
				0x65,  0x61,  0x6c,  0x65,  0x64,  0x2e,  0x0a
		};
		
		byte[] actualBytes = MimeSerializer.serialize(originalString);
		
		assertArrayEquals(EXPECTED_BYTES, actualBytes);
	}
	
	/**
	 * A serialized byte-array should be serialized as
	 * the un-altered byte array, presumed to be the mime-type
	 * application/octet-stream.
	 * 
	 */
	@Test
	public void shouldSerializeByteArrayAsOctetStream() {
		byte[] EXPECTED_BYTES = new byte[] { 
				0x74,  0x68,  0x69,  0x73,  0x20,  0x73,  0x68,  0x6f,
				0x75,  0x6c,  0x64,  0x20,  0x62,  0x65,  0x20,  0x73,
				0x65,  0x61,  0x6c,  0x65,  0x64,  0x2e,  0x0a
		};
		
		byte[] actualBytes = MimeSerializer.serialize(EXPECTED_BYTES);
		
		assertArrayEquals(EXPECTED_BYTES, actualBytes);
		
	}
}
