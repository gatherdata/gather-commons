/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.workflow;

import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.spi.EnvelopeDestination;
import org.gatherdata.core.spi.EnvelopeSource;
import org.gatherdata.core.spi.EnvelopeStorage;
import org.gatherdata.core.spi.EnvelopeTransformer;

/** 
 * A Workplace is a facade for the Gather framework core services. It has a simplified sub-set
 * of the interfaces, focusing on operations needed for a typical workflow. 
 * 
 */
public interface Workplace {

	/**
	 * Returns whether this Workplace has any storage available.
	 * 
	 * @return true if storage is available, false otherwise
	 */
	boolean hasStorage();

	/**
	 * Returns whether this Workplace has a destination defined.
	 *
	 * @return true if a destination has been defined, false otherwise
	 */
	boolean hasDestination();

	/**
	 * Returns whether this Workplace has a transformer available.
	 * 
	 * @return true if a transformer is available, false otherwise
	 */
	boolean hasTransformer();

	/**
	 * Sends the workunit to the destination defined for this workplace.
	 * 
	 * @param workUnit
	 */
	void ship(Envelope workUnit);

}
