/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.joda.time.DateTime;

/**
 * GenericReceipt is a generic, mutable Receipt implementation. 
 *
 */
public class GenericReceipt implements Receipt {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4350080765096605346L;
	
	private URI uid;
	private URI location;
	private DateTime dateTimeStamp;
	private boolean voided = false;

    private Map<String, Object> properties = new HashMap<String, Object>();
	
	/**
	 * Default no-arg constructor to create an empty instance.
	 */
	public GenericReceipt() {
		;
	}

	/**
	 * Creates a new fully qualified GatherInfo.
	 * 
	 * @param uid unique id of the content
	 * @param location URI of the content storage
	 * @param datetimestamp
	 */
	public GenericReceipt(URI uid, URI location, DateTime dateTimeStamp) {
		this.uid = uid;
		this.location = location;
		this.dateTimeStamp = dateTimeStamp;
	}
	
	public Object clone() {
		GenericReceipt clonedEnvelope = new GenericReceipt(uid, location, dateTimeStamp);
		clonedEnvelope.properties.putAll(this.properties);
		return clonedEnvelope;
	}
	
	public URI getUid() {
		return uid;
	}
	
	public void setUid(URI uid) {
		this.uid = uid;
	}

	public URI getLocation() {
		return location;
	}
	
	public void setLocation(URI location) {
		this.location = location;
	}
	
	public DateTime getDateTimeStamp() {
		return dateTimeStamp;
	}
	
	public void setDateTimeStamp(DateTime dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}

    public boolean isVoided() {
        return voided;
    }
    
    public void setVoided(boolean voided) {
        this.voided = voided;
    }
	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Receipt)) {
			return false;
		}
		Receipt rhs = (Receipt) object;
		return new EqualsBuilder()
			.append(this.uid, rhs.getUid())
			.isEquals();
	}

	/**
	 * @see org.gatherdata.commons.util.Receipt#hashCode()
	 */
	public int hashCode() {
		return this.uid.toASCIIString().hashCode(); 
	}

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public Object getProperty(String key, Object defaultValue) {
        Object propertyValue = defaultValue;
        if (properties.containsKey(key)) {
            propertyValue = properties.get(key);
        }
        return propertyValue;
    }

    public Iterable<String> getPropertyKeys() {
        return properties.keySet();
    }

    public Iterable<Object> getPropertyValues() {
        return properties.values();
    }

    public boolean hasProperty(String key) {
        return properties.containsKey(key);
    }

    public Object removeProperty(String key) {
        return properties.remove(key);
    }

    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }


}
