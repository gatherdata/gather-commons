/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.jpa.example.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.eclipse.persistence.jpa.osgi.PersistenceProvider;
import org.gatherdata.commons.db.jpa.example.Pilot;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


/**
 * Example use of the Db4oService
 * 
 */
public final class ExampleDb4oAccess {

    ExampleDao dao = null;
    
    public void demo(ExampleDao dao) {
        this.dao = dao;
        try {
            dao.open();
            
            storeFirstPilot();
            storeSecondPilot();
            System.out.println("retrieveAllPilots...");
            retrieveAllPilots();
            System.out.println("retrievePilotByName...");
            retrievePilotByName();
            System.out.println("updatePilot...");
            updatePilot();
            System.out.println("delete pilots...");
            deleteFirstPilotByName();
            deleteSecondPilotByName();
            
        } finally {
            dao.close();
        }

    }
    
    public <T> void listResult(List<T> result) {
        for (T entity : result) {
            System.out.println(entity);
        }
    }


    public void storeFirstPilot() {
        Pilot pilot1 = new Pilot("Michael Schumacher", 100);
        dao.store(pilot1);
        System.out.println("Stored " + pilot1);
    }

    public void storeSecondPilot() {
        Pilot pilot2 = new Pilot("Rubens Barrichello", 99);
        dao.store(pilot2);
        System.out.println("Stored " + pilot2);
    }

    public void retrieveAllPilots() {
        List result = dao.getAll(Pilot.class);
        listResult(result);
    }

    public void retrievePilotByName() {
        Pilot found = dao.getPilotNamed("Michael Schumacher");
        System.out.println("retrieved: " + found);
    }

    public void updatePilot() {
        Pilot found = dao.getPilotNamed("Michael Schumacher");
        found.addPoints(11);
        dao.store(found);
        System.out.println("Added 11 points for " + found);
        retrieveAllPilots();
    }

    public void deleteFirstPilotByName() {
        Pilot found = dao.getPilotNamed("Michael Schumacher");
        dao.delete(found);
        System.out.println("Deleted " + found);
        retrieveAllPilots();
    }

    public void deleteSecondPilotByName() {
        Pilot found = dao.getPilotNamed("Rubens Barrichello");
        dao.delete(found);
        System.out.println("Deleted " + found);
        retrieveAllPilots();
    }

}