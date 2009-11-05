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
 * A WorkAssignment associates a Workflow with the Workplace 
 * where it should be carried out.
 * 
 */
public class WorkAssignment {

	private Workflow workflow;
	private Workplace workplace;
	
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	public Workplace getWorkplace() {
		return workplace;
	}
	public void setWorkplace(Workplace workplace) {
		this.workplace = workplace;
	}
	public void conduct(Envelope workUnit) {
		workflow.conduct(workUnit, workplace);
	}

	
	
}
