package org.gatherdata.mod.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.gatherdata.mod.spi.ExampleOsgiService;

public class ExampleOsgiServiceImpl implements ExampleOsgiService {

	private final Map<String, String> stringToString;
	
	private String uniqueKeyBase = "foo";
	
	private AtomicInteger uniqueKeyCounter = new AtomicInteger(1);
	
	public ExampleOsgiServiceImpl() {
		stringToString = Collections.synchronizedMap(new HashMap<String, String>());
	}

	/* (non-Javadoc)
	 * @see org.gatherdata.archetype.internal.ExampleOsgiService#get(java.lang.String)
	 */
	public String get(String key) {
		return stringToString.get(key);
	}

	/* (non-Javadoc)
	 * @see org.gatherdata.archetype.internal.ExampleOsgiService#put(java.lang.String, java.lang.String)
	 */
	public String put(String key, String value) {
		return stringToString.put(key, value);
	}

	/* (non-Javadoc)
	 * @see org.gatherdata.archetype.internal.ExampleOsgiService#remove(java.lang.String)
	 */
	public String remove(String key) {
		return stringToString.remove(key);
	}

	/* (non-Javadoc)
	 * @see org.gatherdata.archetype.internal.ExampleOsgiService#size()
	 */
	public int size() {
		return stringToString.size();
	}
	
	/* (non-Javadoc)
	 * @see org.gatherdata.archetype.internal.ExampleOsgiService#makeUniqueKey()
	 */
	public String makeUniqueKey() {
		return uniqueKeyBase + uniqueKeyCounter.getAndIncrement();
	}
	
}