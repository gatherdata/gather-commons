/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.net;

import java.net.URI;
import java.net.URISyntaxException;


public class GatherUrnFactory {
	
	public static final String GATHER_SCHEME = "gather";

	public URI getLocalUrn() {
		URI localUrn = null;
		try {
			localUrn = new URI(GATHER_SCHEME, InetAddressUtils.getHostName(), null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return localUrn;
	}
}
