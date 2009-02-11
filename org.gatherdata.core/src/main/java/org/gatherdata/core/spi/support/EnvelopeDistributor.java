package org.gatherdata.core.spi.support;

import java.util.Vector;

import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.spi.EnvelopeDestination;
import org.gatherdata.core.spi.EnvelopeSource;

/**
 * A thread-safe utility class to help EnvelopeSource implementations keep track
 * of EnvelopeDestinations and distribute envelopes to them.
 *
 */
public class EnvelopeDistributor implements EnvelopeSource {

    private int envelopesSent = 0;
    
    Vector<EnvelopeDestination> destinations = new Vector<EnvelopeDestination>();
    
    public int getEnvelopesSent() {
        return envelopesSent;
    }

    public boolean subscribe(EnvelopeDestination destination) {
        boolean destinationsChanged = false;
        synchronized (destinations) {
            destinationsChanged = this.destinations.add(destination);
        }
        return destinationsChanged;
    }

    public boolean unsubscribe(EnvelopeDestination destination) {
        boolean destinationsChanged = false;
        synchronized (destinations) {
            destinationsChanged = this.destinations.remove(destination);
        }
        return destinationsChanged;
    }

    public void distribute(Envelope envelopeToDistribute) {
        synchronized (destinations) {
            for (EnvelopeDestination destination : destinations) {
                destination.accept(envelopeToDistribute);
            }
            envelopesSent++;
        }
    }
    
}
