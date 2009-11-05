/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.gatherdata.commons.security.Seal;
import org.junit.Test;


/**
 * Unit tests to verify the behavior of Seal.
 */
public class SealTest {

	
	/**
	 * Seal should be able to create an instance from aribtrary
	 * serializable data using a named algorithm.
	 * 
	 * @throws NoSuchAlgorithmException will be thrown if the named algorithm not supported 
	 *         on the test platform 
	 */
	@Test
	public void shouldCreateSealForSerializableDataUsingANamedAlgorithm() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.";
		Seal testSeal = Seal.createSealFor(serializableData, "SHA-1");
		
		assertNotNull(testSeal);
		assertTrue(testSeal.validate(serializableData));
	}
	
	/**
	 * Seal should be able to create an instance with a
	 * MD2 digest of arbitrary serializable data.
	 * 
	 * @throws NoSuchAlgorithmException will be thrown if MD2 is not a supported 
	 *         algorithm on the test platform 
	 */
	@Test
	public void shouldCreateMd2SealForSerializableData() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.\n";
		Seal testSeal = Seal.createSealFor(serializableData, Seal.MD2_ALGORITHM);
		
		assertNotNull(testSeal);
		assertTrue(testSeal.validate(serializableData));
		assertArrayEquals(
				"6dc1fe3eed366a5537e3b8ff1bbac329".toCharArray(),
				Hex.encodeHex(testSeal.getDigest()));
	}
	
	/**
	 * Seal should be able to create an instance with a
	 * MD5 digest of arbitrary serializable data.
	 * 
	 * @throws NoSuchAlgorithmException will be thrown if MD5 is not a supported 
	 *         algorithm on the test platform 
	 */
	@Test
	public void shouldCreateMd5SealForSerializableData() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.\n";
		Seal testSeal = Seal.createSealFor(serializableData, Seal.MD5_ALGORITHM);
		
		assertNotNull(testSeal);
		assertTrue(testSeal.validate(serializableData));
		assertArrayEquals(
				"2eafe7a5656fd18371235af225b6b783".toCharArray(),
				Hex.encodeHex(testSeal.getDigest()));
	}
	
	/**
	 * Seal should be able to create an instance with a
	 * SHA-1 digest of arbitrary serializable data.
	 * 
	 * @throws NoSuchAlgorithmException will be thrown if SHA-1 is not a supported 
	 *         algorithm on the test platform 
	 */
	@Test
	public void shouldCreateSha1SealForSerializableData() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.\n";
		Seal testSeal = Seal.createSealFor(serializableData, Seal.SHA1_ALGORITHM);
		
		assertNotNull(testSeal);
		assertTrue(testSeal.validate(serializableData));
		assertArrayEquals(
				"1e9efba5f374cc9ab434a2141a8683c17105232d".toCharArray(),
				Hex.encodeHex(testSeal.getDigest()));
	}
	
	/**
	 * Seal should be able to create an instance with a
	 * SHA-256 digest of arbitrary serializable data.
	 * 
	 * @throws NoSuchAlgorithmException will be thrown if SHA-256 is not a supported 
	 *         algorithm on the test platform 
	 */
	@Test
	public void shouldCreateSha256SealForSerializableData() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.\n";
		Seal testSeal = Seal.createSealFor(serializableData, Seal.SHA256_ALGORITHM);
		
		assertNotNull(testSeal);
		assertTrue(testSeal.validate(serializableData));
		assertArrayEquals(
				"ae95f7563f3453e1858261bc127eef9d421f364cd0e77fc833840d5341a48ab4".toCharArray(),
				Hex.encodeHex(testSeal.getDigest()));
	}
	
	/**
	 * Seal should be able to create an instance with a
	 * SHA-384 digest of arbitrary serializable data.
	 * 
	 * @throws NoSuchAlgorithmException will be thrown if SHA-384 is not a supported 
	 *         algorithm on the test platform 
	 */
	@Test
	public void shouldCreateSha384SealForSerializableData() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.\n";
		Seal testSeal = Seal.createSealFor(serializableData, Seal.SHA384_ALGORITHM);
		
		assertNotNull(testSeal);
		assertTrue(testSeal.validate(serializableData));
		assertArrayEquals(
				"27d2a998b2f47580baaff0e1c0f3ea1cdcc51bc9ae0144d215bfd76aff751c2b1a5d6b039092d9794fee7528f0881600".toCharArray(),
				Hex.encodeHex(testSeal.getDigest()));
	}
	
	/**
	 * Seal should be able to create an instance with a
	 * SHA-512 digest of arbitrary serializable data.
	 * 
	 * @throws NoSuchAlgorithmException will be thrown if SHA-384 is not a supported 
	 *         algorithm on the test platform 
	 */
	@Test
	public void shouldCreateSha512SealForSerializableData() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.\n";
		Seal testSeal = Seal.createSealFor(serializableData, Seal.SHA512_ALGORITHM);
		
		assertNotNull(testSeal);
		assertTrue(testSeal.validate(serializableData));
		assertArrayEquals(
				"7b69169ebe26c9fe0edc3667ec3657ecd1137f8a5b8e464a3defd1b04cc09a0aae6621c3ddcc871a48d4cfa5cb84efed020cddf1c8fde2708c176ea8985007c1".toCharArray(),
				Hex.encodeHex(testSeal.getDigest()));
	}
	
	/**
	 * All Seals created with the same algorithm for the same content should be equal.
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	@Test
	public void shouldEqualDuplicateSeal() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.\n";
		Seal originalSeal = Seal.createSealFor(serializableData, Seal.SHA384_ALGORITHM);
		Seal duplicateSeal = Seal.createSealFor(serializableData, Seal.SHA384_ALGORITHM);
		
		assertNotSame(originalSeal, duplicateSeal);
		assertEquals(originalSeal, duplicateSeal);
	}

	/**
	 * A Cloned Seal should be equal to the original,
	 * such that x.clone().equals(x) is true.
	 * @throws NoSuchAlgorithmException 
	 */
	@Test
	public void shouldEqualClone() throws NoSuchAlgorithmException {
		String serializableData = "this should be sealed.\n";
		Seal originalSeal = Seal.createSealFor(serializableData, Seal.SHA384_ALGORITHM);
		Seal clonedSeal = (Seal) originalSeal.clone();

		assertEquals(originalSeal, clonedSeal);
	}
}
