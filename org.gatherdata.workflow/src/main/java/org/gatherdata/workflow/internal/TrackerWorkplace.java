package org.gatherdata.workflow.internal;

import java.net.URI;

import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.spi.EnvelopeDestination;
import org.gatherdata.workflow.Workplace;

/**
 * The TrackerWorkplace act as a proxy for any Gather services
 * which are registered with the OSGi runtime. 
 *
 */
public class TrackerWorkplace implements Workplace {

	/**
	 * Checks whether this Workplace has a destination
	 * available.
	 */
	public boolean hasDestination() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasStorage() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasTransformer() {
		// TODO Auto-generated method stub
		return false;
	}

	public void ship(Envelope workUnit) {
		// TODO Auto-generated method stub

	}

	public void disable() {
		// TODO Auto-generated method stub

	}

	public void enable() {
		// TODO Auto-generated method stub

	}

	public EnvelopeDestination getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getEnvelopesSent() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setDestination(EnvelopeDestination destination) {
		// TODO Auto-generated method stub

	}

	public Envelope get(URI uriOfEnvelope) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(URI uriOfEnvelope) {
		// TODO Auto-generated method stub

	}

	public Envelope save(Envelope envelopeToSave) {
		// TODO Auto-generated method stub
		return null;
	}

	public void accept(Envelope envelope) {
		// TODO Auto-generated method stub

	}

	public int getEnvelopesReceived() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Envelope transform(Envelope original) {
		// TODO Auto-generated method stub
		return null;
	}

}
