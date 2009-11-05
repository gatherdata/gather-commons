/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.workflow.spi;

import javax.activation.MimeType;

import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.spi.EnvelopeDestination;
import org.gatherdata.workflow.WorkAssignment;

public interface WorkflowManager extends EnvelopeDestination {

	/**
	 * Adds an assignment for handling envelopes of a particular type.
	 * 
	 * @param forTypeOfWork type of Envelopes to pass to the assignment
	 * @param toAssignment assignment to carry out
	 */
	public abstract void addAssignment(MimeType forTypeOfWork, WorkAssignment toAssignment);

	/**
	 * Accepts an Envelope which will be queued up
	 * for handling by available workers. 
	 * 
	 * @param e
	 */
	public abstract void accept(Envelope e);

	public abstract int getEnvelopesReceived();

}