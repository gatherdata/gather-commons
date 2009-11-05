/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.example.osgi;

import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.Attributes.properties;
import static org.ops4j.peaberry.util.TypeLiterals.export;
import static org.ops4j.peaberry.util.TypeLiterals.iterable;

import org.gatherdata.commons.example.ExampleService;
import org.gatherdata.commons.example.internal.ExampleServiceImpl;
import org.ops4j.peaberry.Export;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class GuiceBindingModule extends AbstractModule {

    @Inject
    Export<ExampleService> exampleService;

    @Override
    protected void configure() {
        bind(export(ExampleService.class)).toProvider(service(ExampleServiceImpl.class).export());
        bind(ExampleServiceImpl.class).in(Singleton.class); // make it a singleton
    }
}
