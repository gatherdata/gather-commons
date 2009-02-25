package org.gatherdata.core.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.junit.Test;


public class MimeTypesTest {

    @Test
    public void shouldEqualBasedOnTypeInformation() {
        MimeType staticTextPlain = MimeTypes.TEXT_PLAIN;
        MimeType instanceTextPlain = null;
        try {
            instanceTextPlain = new MimeType("text","plain");
        } catch (MimeTypeParseException e) {
            e.printStackTrace();
        }
        assertNotNull(instanceTextPlain);
        assertTrue(MimeTypes.equals(instanceTextPlain, staticTextPlain));
    }
}
