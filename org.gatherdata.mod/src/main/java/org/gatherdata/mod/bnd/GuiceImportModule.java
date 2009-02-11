package org.gatherdata.mod.bnd;

import static org.ops4j.peaberry.Peaberry.registration;
import static org.ops4j.peaberry.Peaberry.service;
import static org.ops4j.peaberry.util.TypeLiterals.export;

import com.google.inject.AbstractModule;
import com.google.inject.Key;

import org.osgi.service.log.LogService;

/**
 * The GuiceImportModule specifies bindings to provided and
 * consumed services for this bundle.
 *
 */
public class GuiceImportModule extends AbstractModule {

	@Override 
	protected void configure() {
		// get a LogService
		bind(LogService.class).toProvider(service(LogService.class).single());

	}
}
