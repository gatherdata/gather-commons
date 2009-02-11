package org.gatherdata.core.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.activation.MimeType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.gatherdata.core.net.CbidFactory;

/**
 * GenericEnvelope is a generic, mutable Envelope implementation. 
 *
 */
public class GenericEnvelope implements Envelope {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4350080765096605346L;
	
	private URI uid;
	private URI source;
	private Serializable content;
	private Calendar dateTimeStamp;
	private Seal seal;
	private MimeType type;
	private String qualifier;
	private boolean voided = false;
	
	/**
	 * Default no-arg constructor to create an empty envelope.
	 */
	public GenericEnvelope() {
		;
	}
	
	/**
	 * Creates a new sealed Envelope. The Seal will be calculated
	 * for the given contents and the timestamp will be now.
	 * The uid of the resulting Envelope generated using {@link CbidFactory#createCbid(Serializable)}.
	 * 
	 * @param uid
	 * @param content
	 * @param type
	 */
	public GenericEnvelope(URI source, Serializable content, MimeType type) {
		this.dateTimeStamp = GregorianCalendar.getInstance();
		this.source = source;
		this.type = type;
		this.content = content;
		this.seal = Seal.createSealFor(content);
		this.uid = CbidFactory.createCbid(seal.getAlgorithm(), seal.getDigest());
	}

	/**
	 * Creates a new fully qualified sealed Envelope.
	 * 
	 * @param uid
	 * @param content
	 * @param type
	 * @param seal
	 * @param datetimestamp
	 */
	public GenericEnvelope(URI uid, URI source, Serializable content, MimeType type, String qualifier, Seal seal, Calendar dateTimeStamp) {
		this.uid = uid;
		this.source = source;
		this.type = type;
		this.qualifier = qualifier;
		this.content = content;
		this.seal = seal;
		this.dateTimeStamp = dateTimeStamp;
	}
	
	public Object clone() {
		GenericEnvelope clonedEnvelope = new GenericEnvelope(uid, source, content, type, qualifier, seal, dateTimeStamp);
		return clonedEnvelope;
	}
	
	public URI getUid() {
		return uid;
	}
	
	public void setuid(URI uid) {
		this.uid = uid;
	}

	public URI getSource() {
		return source;
	}
	
	public void setSource(URI source) {
		this.source = source;
	}

	public Serializable getContents() {
		return content;
	}
	
	public void setContents(Serializable contents) {
		this.content = contents;
	}

	public Calendar getDateTimeStamp() {
		return dateTimeStamp;
	}
	
	public void setDateTimeStamp(Calendar dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

	public Seal getSeal() {
		return seal;
	}

	public void setSeal(Seal seal) {
		this.seal = seal;
	}

	public MimeType getType() {
		return type;
	}

	public void setType(MimeType type) {
		this.type = type;
	}

	public String getQualifier() {
		return qualifier;
	}
	
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

    public boolean isVoided() {
        return voided;
    }
    
    public void setVoided(boolean voided) {
        this.voided = voided;
    }

	public boolean isValid() {
		boolean isValid = false;
		if (seal != null) {
			try {
				isValid = seal.validate(content);
			} catch (Exception e) {
				isValid = false;
			}
		}		
		return isValid;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Envelope)) {
			return false;
		}
		Envelope rhs = (Envelope) object;
		return new EqualsBuilder()
			.append(this.uid, rhs.getUid())
			.append(this.seal, rhs.getSeal())
			.isEquals();
	}

	/**
	 * @see org.gatherdata.core.model.Envelope#hashCode()
	 */
	public int hashCode() {
		return this.uid.toASCIIString().hashCode(); 
	}

}
