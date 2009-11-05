/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.workflow;

import org.gatherdata.core.model.Envelope;

/**
 * A Workflow is an algorithm for handling an Envelope, 
 * using the facilities provided by a Workplace. 
 * 
 */
public interface Workflow {

	/**
	 * Conducts an Envelope through the workflow algorithm,
	 * using the facilities of a Workplace.
	 * 
	 * Note: this call must be reentrant, as it may be called
	 * simultaneously by multiple threads.
	 * 
	 * @param workUnit envelope upon which to operate
	 * @param atWorkplace Workplace facilities to use for operations
	 */
	void conduct(Envelope workUnit, Workplace atWorkplace);
	
}
