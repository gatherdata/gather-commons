/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.db4o.example;

import org.joda.time.DateTime;

public class Pilot {
    private String name;
    private int points;
    
    private transient DateTime lastUpdate;
    
    private long lastUpdateMillis;
    
    public Pilot(String name,int points) {
        this.name=name;
        this.points=points;
    }
        
    public int getPoints() {
        return points;
    }
    
    public void addPoints(int points) {
        this.points+=points;
        setLastUpdate(new DateTime());
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return name+"/"+points + " (" + getLastUpdate() + ")";
    }

    public DateTime getLastUpdate() {
        if (lastUpdate == null) {
            lastUpdate = new DateTime(lastUpdateMillis);
        }
        return lastUpdate;
    }
    
    public void setLastUpdate(DateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        this.lastUpdateMillis = lastUpdate.getMillis();
    }
    
    public long getLastUpdateMillis() {
        return lastUpdateMillis;
    }

    public void setLastUpdateMillis(long millis) {
        this.lastUpdateMillis = millis;
        this.lastUpdate = null;
    }
}
