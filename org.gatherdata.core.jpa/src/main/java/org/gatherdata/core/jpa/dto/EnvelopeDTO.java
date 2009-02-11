package org.gatherdata.core.jpa.dto;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.gatherdata.core.model.Envelope;
import org.gatherdata.core.model.Seal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * An Envelope which is suitable for use as a Data Transfer Object, 
 * providing conversion-friendly accessors and JPA markup.
 *
 * @param <T>
 */

@Entity
@Table(name="ENVELOPE")
public class EnvelopeDTO implements Envelope {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1362808883230535488L;

    @Id 
    private String uidAsString;
    
    private boolean voided;
    
	private Serializable contents;

    @Temporal(TemporalType.TIMESTAMP)
	private Calendar dateTimeStamp;

	private String qualifier;

	private byte[] digest;

	@Transient
	private Seal lazySeal;

	private String algorithm;

	private String sourceAsString;

	@Transient
	private URI lazySourceUri;

	@Transient
	private MimeType lazyType;

	private String typeAsString;

	@Transient
	private URI lazyUid;

	public EnvelopeDTO() {
		;
	}
	
	public EnvelopeDTO(Envelope envelopeToDecorate) {
		setContents(envelopeToDecorate.getContents());
		setDateTimeStamp(dateTimeStamp);
		Seal fromSeal = envelopeToDecorate.getSeal();
		setSealAlgorithm(fromSeal.getAlgorithm());
		setSealDigestFromHex(new String(Hex.encodeHex(fromSeal.getDigest())));
		setSource(envelopeToDecorate.getSource());
		setType(envelopeToDecorate.getType());
		setQualifier(envelopeToDecorate.getQualifier());
		setUid(envelopeToDecorate.getUid());	
	}

    public void setVoided(boolean voided) {
        this.voided = voided;
    }

    public boolean isVoided() {
        return voided;
    }
    
	public Serializable getContents() {
		return contents;
	}
	
	public void setContents(Serializable contents) {
		this.contents = contents;
	}

	public Calendar getDateTimeStamp() {
		return dateTimeStamp;
	}
	
	public void setDateTimeStamp(Calendar dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

	public String getQualifier() {
		return this.qualifier;
	}
	
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public Seal getSeal() {
		if (this.lazySeal == null) {
			this.lazySeal = new Seal(this.algorithm, this.digest);
		}
		return this.lazySeal;
	}

	public URI getSource() {
		if (this.lazySourceUri == null) {
			try {
				this.lazySourceUri = new URI(this.sourceAsString);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return this.lazySourceUri;
	}
	
	public void setSource(URI source) {
		setSource(source.toASCIIString());
		lazySourceUri = source;
	}
	
	public String getSourceAsString() {
		return this.sourceAsString;
	}
	
	public void setSource(String uriString) {
		this.sourceAsString = uriString;
		this.lazySourceUri = null;
	}

	public MimeType getType() {
		if (this.lazyType == null) {
			try {
				this.lazyType = new MimeType(this.typeAsString);
			} catch (MimeTypeParseException e) {
				e.printStackTrace();
			}
		}
		return this.lazyType;
	}
	
	public void setType(MimeType type) {
		setType(type.toString());
		this.lazyType = type;
	}
	
	public String getTypeAsString() {
		return this.typeAsString;
	}
	
	public void setType(String mimeTypeString) {
		this.typeAsString = mimeTypeString;
		this.lazyType = null;
	}

	public URI getUid() {
		if (this.lazyUid == null) {
			try {
				this.lazyUid = new URI(uidAsString);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return this.lazyUid;
	}
	
	public void setUid(URI uid) {
		setUid(uid.toASCIIString());
		this.lazyUid = uid;
	}
	
	public String getUidAsString() {
		return getUid().toASCIIString();
	}
	
	public void setUid(String uriString) {
		this.uidAsString = uriString;
		this.lazyUid = null;
	}
	
	public String getSealAlgorithm() {
		return algorithm;
	}
	
	public void setSealAlgorithm(String algorithm) {
		this.algorithm = algorithm;
		this.lazySeal = null;
	}
	
	public String getSealDigestInHex() {
		return new String(Hex.encodeHex(this.digest));
	}
	
	public byte[] getSealDigest() {
		return this.digest;
	}
	
	public void setSealDigest(byte[] digest) {
		this.digest = digest;
		this.lazySeal = null;
	}
	
	public void setSealDigestFromHex(String digestInHex) {
		try {
			setSealDigest(Hex.decodeHex(digestInHex.toCharArray()));
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isValid() {
		boolean envelopeIsSealed = false;
		try {
			envelopeIsSealed = getSeal().validate(getContents());
		} catch (NoSuchAlgorithmException e) {
			;
		}
		return envelopeIsSealed;
	}

	@Override
	public Object clone() {
		return new EnvelopeDTO(this);
	}

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Envelope)) {
            return false;
        }
        Envelope rhs = (Envelope) object;
        Seal rhsSeal = rhs.getSeal();
        return new EqualsBuilder()
            .append(this.uidAsString, rhs.getUid().toASCIIString())
            .append(this.digest, rhsSeal.getDigest())
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.uidAsString.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
            .append("isVoided", this.voided)
            .append("dateTimeStamp", this.dateTimeStamp)
            .append("uidAsString",
                this.uidAsString).append("sealDigestInHex",
                this.getSealDigestInHex()).append("type", this.getType())
                .append("seal", this.getSeal()).append("sourceAsString",
                        this.sourceAsString)
                .append("qualifier", this.qualifier).append("source",
                        this.getSource()).append("sealAlgorithm",
                        this.getSealAlgorithm()).append("sealDigest",
                        this.getSealDigest()).append("contents", this.contents)
                .append("valid", this.isValid()).append("typeAsString",
                        this.typeAsString).append("uid", this.getUid())
                .toString();
    }
	
	
}
