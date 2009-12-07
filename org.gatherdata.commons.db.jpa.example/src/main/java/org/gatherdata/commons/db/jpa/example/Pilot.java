/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.jpa.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

@Entity
@Table(name = "PILOT")
public class Pilot {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    
    private long lastUpdate = 0;
    
    @Transient
    private DateTime lazyLastUpdate;
    
    private int points = 0;
    
    public Pilot() {;}
        
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
        if (lazyLastUpdate == null) {
            lazyLastUpdate = new DateTime(lastUpdate);
        }
        return lazyLastUpdate;
    }
    
    public void setLastUpdate(DateTime lastUpdate) {
        lazyLastUpdate = lastUpdate;
        this.lastUpdate = lastUpdate.getMillis();
    }
    
}
