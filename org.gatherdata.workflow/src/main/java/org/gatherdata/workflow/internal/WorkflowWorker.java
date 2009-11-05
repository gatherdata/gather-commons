/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.workflow.internal;

import org.gatherdata.core.model.Envelope;
import org.gatherdata.workflow.WorkAssignment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Worker thread which submits an Envelope to handling
 * by a Workflow, enabling concurrent operation.
 * 
 */
public class WorkflowWorker
    implements Runnable
{
	private static final Log logger = LogFactory.getLog(WorkflowWorker.class);

	private WorkAssignment assignment;
	private Envelope workUnit;

	public WorkflowWorker(WorkAssignment assignment, Envelope workUnit) {
		this.assignment = assignment;
		this.workUnit = workUnit;
	}

	public void run() {
		if (logger.isDebugEnabled()) {
			logger.debug("Workflow worker conducting {" + 
					workUnit + "} through {" +
					assignment + "}");
		}
		assignment.conduct(workUnit);	
	}
	
}

