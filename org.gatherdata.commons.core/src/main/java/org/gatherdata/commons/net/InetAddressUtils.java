/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressUtils {

	/**
	 * Gets the hostname of this system.
	 * 
	 * @return hostname, or "localhost" if hostname could not be determined
	 */
	public static String getHostName() {
		String hostname = null;
		try {
	        InetAddress addr = InetAddress.getLocalHost();
	        hostname = addr.getHostName();
	    } catch (UnknownHostException e) {
	    	hostname = "localhost";
	    }
		return hostname;
	}
}
