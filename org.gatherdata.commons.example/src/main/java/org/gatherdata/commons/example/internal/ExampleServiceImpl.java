/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.example.internal;

import org.gatherdata.commons.example.ExampleService;

public class ExampleServiceImpl implements ExampleService {

    public String sayHello(String toSubject) {
        return "Hello " + toSubject + "!";
    }

}
