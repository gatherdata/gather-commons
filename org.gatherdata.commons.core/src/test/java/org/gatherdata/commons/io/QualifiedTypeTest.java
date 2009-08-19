package org.gatherdata.commons.io;

import static org.junit.Assert.assertNotNull;

import org.gatherdata.commons.io.QualifiedType;
import org.junit.Test;

/**
 * Unit tests for QualifiedTest.
 *
 */
public class QualifiedTypeTest {

    @Test
    public void shouldProvideStaticInstanceCalledAnyType() {
        assertNotNull(QualifiedType.ANY_TYPE);
    }
}
