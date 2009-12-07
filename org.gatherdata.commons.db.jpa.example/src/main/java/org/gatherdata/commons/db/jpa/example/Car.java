/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.db.jpa.example;


import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CAR")
public class Car {

    @Id
    @GeneratedValue
    private long id;
    
    @Basic
    @Column(name = "MODEL")
    private String model;
    
    @OneToOne 
    private Pilot pilot;
    
    public Car() {;}
    
    public Car(String model) {
        this.model=model;
        this.pilot=null;
    }
      
    public Pilot getPilot() {
        return pilot;
    }
    
    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }
    
    public String getModel() {
        return model;
    }
    
    public String toString() {
        return model+"["+pilot+"]";
    }
}
