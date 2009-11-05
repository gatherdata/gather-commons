/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.net;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.apache.commons.codec.binary.Hex;
import org.gatherdata.commons.security.DigestFactory;

/**
 * A factory which produces URI's based on content hash. The resulting URI is a URN
 * with the namespace {@link #NAMESPACE_NID}. The NSS element following the namespace
 * identifies the algorithm used to create the digest, which is appended as a URI fragment. 
 * 
 * <CBID> = "urn:cbid:<algorithm>:<digest>"
 * 
 */
public class CbidFactory {

	public static String NAMESPACE_NID = "cbid";
	
	public static URI createCbid(String algorithm, byte[] digest) {
		
		URI cbid = null;
		try {
			cbid = new URI("urn", NAMESPACE_NID + ":" + algorithm + ":" + new String(Hex.encodeHex(digest)), null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return cbid;
	}
	
	public static URI createCbid(Serializable content) {
		return createCbid(DigestFactory.getDefaultAlgorithm(), DigestFactory.createDigestFor(content)); 
	}
	
	public static URI createCbid(Collection<? extends Serializable> contentCollection) {
		return createCbid(DigestFactory.getDefaultAlgorithm(), DigestFactory.createDigestFor(contentCollection));
	}
}
