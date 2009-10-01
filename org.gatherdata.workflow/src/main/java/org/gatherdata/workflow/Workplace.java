/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
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
