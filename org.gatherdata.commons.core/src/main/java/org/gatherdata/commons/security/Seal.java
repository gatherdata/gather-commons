/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.security;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * A Seal contains a message digest calculated with an algorithm recognized by the 
 * <a href="http://java.sun.com/j2se/1.4.2/docs/guide/security/CryptoSpec.html#AppA">Java Cryptography Architecture</a>. 
 * 
 * Note that because the Seal operates on Serializable data rather than a raw byte stream, the calculated digest will
 * be different than the equivalent calculation for primtitive types and strings because it includes object de-serialization
 * information.
 *
 * @author <a href="mailto:akollegger@tembopublic.org">Andreas Kollegger</a>
 */
public class Seal implements Cloneable {
	
	public static String MD2_ALGORITHM = "MD2";
	public static String MD5_ALGORITHM = "MD5";
	public static String SHA1_ALGORITHM = "SHA-1";
	public static String SHA256_ALGORITHM = "SHA-256";
	public static String SHA384_ALGORITHM = "SHA-384";
	public static String SHA512_ALGORITHM = "SHA-512";
	
	private static String defaultAlgorithm = SHA1_ALGORITHM;
	
	/**
	 * The common name of the digest function. This should be recognized by {@link MessageDigest.getInstance} 
	 */
	private String algorithm;
	
	/**
	 * The calculated digest value.
	 */
	private byte[] digest;
	
	public Seal(String algorithm, byte[] digest) {
		this.algorithm = algorithm;
		this.digest = digest;
	}

	/**
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * @return the digest
	 */
	public byte[] getDigest() {
		return digest;
	}
	
	public boolean validate(Serializable data) throws NoSuchAlgorithmException {
		boolean isValid = false;
		
		byte[] testDigest = DigestFactory.createDigestFor(data, algorithm); 
		EqualsBuilder eb = new EqualsBuilder().append(digest, testDigest);
		isValid = eb.isEquals();
		
		return isValid;
	}
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Seal)) {
			return false;
		}
		Seal rhs = (Seal) object;
		return new EqualsBuilder()
			.append(this.digest, rhs.digest)
			.append(this.algorithm, rhs.algorithm)
			.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder()
			.append(digest)
			.toHashCode();
	}

	/**
	 * A shallow clone of the Seal.
	 */
	public Object clone() {
		return new Seal(this.algorithm, this.digest);
	}
	
	public String toString() {
		return this.algorithm + "=" + new String(Hex.encodeHex(this.digest)); 
	}
	
	/**
	 * Factory method to construct a new Seal using a named algorithm.
	 * 
	 * @param data data to be sealed
	 * @param usingAlgorithmNamed name of algorithm used to calculate the Seal's digest 
	 * @return a Seal for the data
	 * @throws NoSuchAlgorithmException if the named algorithm is not supported on this platform
	 */
	public static Seal createSealFor(Serializable data, String usingAlgorithmNamed) throws NoSuchAlgorithmException {
		Seal createdSeal = null;
		
		byte[] digest = DigestFactory.createDigestFor(data, usingAlgorithmNamed); 
		createdSeal = new Seal(usingAlgorithmNamed, digest);
		
		return createdSeal;
	}
	
	/**
	 * Factory method to construct a new Seal using the default algorithm.
	 * 
	 * @param data data to be sealed
	 * @return
	 */
	public static Seal createSealFor(Serializable data) {
		Seal dataSeal = null;
		try {
			dataSeal = createSealFor(data, defaultAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Default algorithm \"" + defaultAlgorithm + "\" not recognized by MessageDigest");
			e.printStackTrace();
		}
		return dataSeal;
	}

	
}
