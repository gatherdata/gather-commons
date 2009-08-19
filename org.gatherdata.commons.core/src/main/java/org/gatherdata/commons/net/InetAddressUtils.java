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
