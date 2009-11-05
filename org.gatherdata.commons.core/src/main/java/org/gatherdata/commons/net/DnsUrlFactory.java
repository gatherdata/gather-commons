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

/**
 * A Factory which produces <a href="http://www.rfc-editor.org/rfc/rfc4501.txt">RFC-4501</a>
 * compliant DNS URLs.  
 *
 */
public class DnsUrlFactory {

	public static URI getLocalUri() {
		String localName = InetAddressUtils.getHostName();
		return createDnsUri(null, localName, null);
	}
	
	public static URI createDnsUri(String dnsAuthority, String dnsName, String dnsQuery) {
		if (dnsAuthority == null) dnsAuthority = "";
		if (dnsName == null) throw new IllegalArgumentException("dnsName can not be null");
		
		String ssp = "//" + dnsAuthority + "/" + dnsName;
		
		if (dnsQuery != null) {
			ssp = ssp + "?" + dnsQuery;
		}
		URI dnsUrl = null;
		try {
			dnsUrl = new URI("dns", ssp, null);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return dnsUrl;
	}
}
