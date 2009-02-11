package org.gatherdata.core.io;

import static org.junit.Assert.assertNotNull;

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
