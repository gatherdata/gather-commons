/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
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
