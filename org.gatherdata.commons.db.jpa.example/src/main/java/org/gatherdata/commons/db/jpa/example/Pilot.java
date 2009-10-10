/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.jpa.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.joda.time.DateTime;

@Entity
public class Pilot {
    
    @Id
    @GeneratedValue
    private int id;
    
    private String name;
    
    private long lastUpdate = 0;
    
    @Transient
    private DateTime lazyLastUpdate;
    
    private int points = 0;
    
    public Pilot() {
        ;
    }
        
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
