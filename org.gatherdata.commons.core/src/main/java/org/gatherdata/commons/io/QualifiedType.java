package org.gatherdata.commons.io;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * A QualifiedType pairs a MimeType with an optional qualifier.
 *
 */
public class QualifiedType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8847882684051401821L;
	
	public static QualifiedType ANY_TYPE = new QualifiedType(MimeTypes.ANY, "*");

    
	private String type;
	private String qualifier;

	/**
	 * Constructs an unqualified instance (a qualified
	 * type without a qualifier). 
	 * 
	 * @param ofUnqualifiedType
	 */
	public QualifiedType(String ofUnqualifiedType) {
		this.type = ofUnqualifiedType;
	}
	
	public QualifiedType(String ofType, String qualifiedBy) {
		this.type = ofType;
		this.qualifier = qualifiedBy;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		if (type == null) {
			throw new IllegalArgumentException("type of QualifiedType cannot be null.");
		}
		this.type = type;
	}
	public String getQualifier() {
		return qualifier;
	}
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof QualifiedType)) {
			return false;
		}
		QualifiedType rhs = (QualifiedType) object;
		return new EqualsBuilder()
			.append(this.type, rhs.type)
			.append(this.qualifier, rhs.qualifier)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(637823541, 1290213401)
			.appendSuper(super.hashCode())
			.append(this.type).append(this.qualifier)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("qualifier", this.qualifier)
				.append("type", this.type).toString();
	}
	
}
