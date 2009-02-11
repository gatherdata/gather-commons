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