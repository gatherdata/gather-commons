package org.gatherdata.mod.spi;

public interface ExampleOsgiService {

	public abstract String get(String key);

	public abstract String put(String key, String value);

	public abstract String remove(String key);

	public abstract int size();

	public abstract String makeUniqueKey();

}