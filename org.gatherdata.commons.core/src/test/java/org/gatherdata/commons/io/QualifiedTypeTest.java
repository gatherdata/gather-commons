/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
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
