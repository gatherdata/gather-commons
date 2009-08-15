package org.gatherdata.core.model;

import java.io.Serializable;
import java.net.URI;

import org.joda.time.DateTime;

/**
 * A Receipt records meta-data about content handled within Gather, regardless
 * of where it is stored or what form it takes.
 * 
 */
public interface Receipt extends Cloneable, Serializable {
    
    /**
     * Returns the unique identifier of the Envelope.
     * 
     * @return
     */
    public URI getUid();

    /**
     * Returns the URL for the ContentStorage which contains the actual data.
     * 
     * @return
     */
    public URI getLocation();

    public DateTime getDateTimeStamp();

    /**
     * Checks whether this Receipt has been voided.
     * 
     * Voiding a Receipt is an administrative decision, indicating that the
     * Receipt or its associated contents are worthless. A voided Envelope can
     * still be valid.
     * 
     * @return
     */
    public boolean isVoided();

    /**
     * Sets the voided status of the Receipt.
     * 
     * 
     * @param isVoided
     *            true to void this envelope, false to restore it
     */
    public void setVoided(boolean isVoided);

    public Object clone();

    /**
     * Returns a hashcode calculated from the Receipt's uid URI.
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode();

    /**
     * Determines whether this object is "equal to" another.
     * 
     * Equivalence is based on matching uid URI.
     * 
     * @param object
     * @return
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object);

}