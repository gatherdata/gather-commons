/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.db4o.example;

import org.joda.time.DateTime;

public class Pilot {
    private String name;
    private int points = 0;
    
    private transient DateTime lastUpdate;
        
    public Pilot(String name,int points) {
        this.name=name;
        addPoints(points);
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
        return lastUpdate;
    }
    
    public void setLastUpdate(DateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
}
