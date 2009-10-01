/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
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
