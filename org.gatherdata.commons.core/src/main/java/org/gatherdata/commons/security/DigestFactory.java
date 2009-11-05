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
import java.util.Collection;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.gatherdata.commons.io.MimeSerializer;

/**
 * Utility factory for calculating digests for Serializable data.
 * 
 */
public class DigestFactory {
	
	public static String MD2_ALGORITHM = "MD2";
	public static String MD5_ALGORITHM = "MD5";
	public static String SHA1_ALGORITHM = "SHA-1";
	public static String SHA256_ALGORITHM = "SHA-256";
	public static String SHA384_ALGORITHM = "SHA-384";
	public static String SHA512_ALGORITHM = "SHA-512";
	
	private static String defaultAlgorithm = SHA1_ALGORITHM;
	
	public static void setDefaultAlgorithm(String algorithm) throws NoSuchAlgorithmException {
		MessageDigest.getInstance(algorithm);
		defaultAlgorithm = algorithm;
	}
	
	public static String getDefaultAlgorithm() {
		return defaultAlgorithm;
	}
	
	public static boolean validate(String algorithm, byte[] expectedDigest, Serializable data) throws NoSuchAlgorithmException {
		boolean isValid = false;

		MessageDigest md;
	
		md = MessageDigest.getInstance(algorithm);

		byte[] dataAsBytes = MimeSerializer.serialize(data);
		byte[] testDigest = md.digest(dataAsBytes);
		EqualsBuilder eb = new EqualsBuilder().append(expectedDigest, testDigest);
		isValid = eb.isEquals();
		
		return isValid;
	}
	
	/**
	 * Factory method to calculate a digest using a named algorithm.
	 * 
	 * @param data data to be sealed
	 * @param usingAlgorithmNamed name of algorithm used to calculate the Seal's digest 
	 * @return a digest for the data
	 * @throws NoSuchAlgorithmException if the named algorithm is not supported on this platform
	 */
	public static byte[] createDigestFor(Serializable data, String usingAlgorithmNamed) throws NoSuchAlgorithmException {
		
		MessageDigest md;
		byte[] dataAsBytes = MimeSerializer.serialize(data);
				
		md = MessageDigest.getInstance(usingAlgorithmNamed);

		byte[] digest = md.digest(dataAsBytes);

		return digest;
	}

	public static byte[] createDigestFor(Collection<? extends Serializable> data, String usingAlgorithmNamed) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance(usingAlgorithmNamed);
		
		for (Serializable element : data) {
			byte[] elementAsBytes = MimeSerializer.serialize(element);
			md.update(elementAsBytes);
		}
		
		byte[] digest = md.digest();

		return digest;
	}
	
	/**
	 * Factory method to calculate a digest using the default algorithm.
	 * 
	 * @param data data to be digested
	 * @return
	 */
	public static byte[] createDigestFor(Serializable data) {
		byte[] digest = null;
		try {
			digest = createDigestFor(data, defaultAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Default algorithm \"" + defaultAlgorithm + "\" not recognized by MessageDigest");
			e.printStackTrace();
		}
		return digest;
	}

	public static byte[] createDigestFor(Collection<? extends Serializable> data) {

		byte[] digest = null;
		try {
			digest = createDigestFor(data, defaultAlgorithm);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Default algorithm \"" + defaultAlgorithm + "\" not recognized by MessageDigest");
			e.printStackTrace();
		}
		return digest;
	}

	
}
