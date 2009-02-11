package org.gatherdata.core.model;

import java.io.Serializable;
import java.net.URI;
import java.util.Calendar;

import javax.activation.MimeType;

/**
 * An Envelope is a sealed wrapper for data, the contents of which
 * can not be changed.
 * 
 */
public interface Envelope extends Cloneable, Serializable {

	/**
	 * Returns the unique identifier of the Envelope.
	 * 
	 * @return
	 */
	public URI getUid();
	
	/**
	 * Returns the identifier of the source for this Envelope.
	 * 
	 *  The source is either the external entity which provided
	 *  the original content, or an Envelope from which the
	 *  contents of this Envelope was derived.
	 *  
	 * @return
	 */
	public URI getSource();

	public Serializable getContents();

	public Calendar getDateTimeStamp();

	public Seal getSeal();

	public MimeType getType();
	
	/**
	 * Returns the qualifier, which refines the type of the contents 
	 * indicated by {@link #getType()}, in a scheme specific way. 
	 * 
	 * Some example qualifiers:
	 * <table>
	 * <th>
	 * 		<td>Mime Type</td><td>interpretation of qualifier</td><td>example qualifier value</td>
	 * </th>
	 * <tr>
	 * 		<td>TEXT/XML</td><td>XML namespace</td><td>http://www.w3.org/2002/xforms</td>
	 * 		<td>APPLICATION/JAVA-OBJECT</td><td>fully qualified classname</td><td>org.gatherdata.model.Seal</td>
	 *  </tr>
	 *  </table>
	 * </ul>
	 * 
	 * @return type qualifier, or null
	 */
	public String getQualifier();

	/**
	 * Checks whether the seal on this Envelope validates
	 * the contents of the Envelope.
	 * 
	 * The validity of an Envelope is a logical condition.
	 * 
	 * @return true if the seal validates the contents, false otherwise
	 */
	public boolean isValid();
	
	/**
	 * Checks whether this Envelope has been voided.
	 *  
	 * Voiding an Envelope is an administrative decision, indicating 
	 * that the Envelope or its contents are worthless. A voided
	 * Envelope can still be valid.                                                          
	 * 
	 * @return
	 */
	public boolean isVoided();
	
	/**
	 * Toggles the voided status of the Envelope.
	 * 
	 * 
	 * @param isVoided true to void this envelope, false to restore it
	 */
	public void setVoided(boolean isVoided);
	
	public Object clone();
	
	/**
	 * Returns a hashcode calculated from the US-ASCII representation
	 * of the Envelope's uid URI.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode();
	
	/**
	 * Determines whether this object is "equal to" another.
	 * 
	 * Equivalence is based on matching uid URI and seal.
	 * 
	 * @param object
	 * @return
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object);
	
}