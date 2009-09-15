package org.gatherdata.commons.db.db4o.example.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gatherdata.commons.db.db4o.DateTimeHandler;
import org.gatherdata.commons.db.db4o.DateTimeHandlerPredicate;
import org.gatherdata.commons.db.db4o.example.Pilot;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.osgi.Db4oService;

/**
 * Example use of the Db4oService
 * 
 */
public final class ExampleDb4oAccess {

    public void demo(BundleContext bc) {
        ServiceReference sRef = bc.getServiceReference(Db4oService.class.getName());
        Db4oService dbs = (Db4oService) bc.getService(sRef);

        new File("db4o").mkdir();
        
        Configuration config = dbs.newConfiguration();
        config.registerTypeHandler(new DateTimeHandlerPredicate(), new DateTimeHandler());
        ObjectContainer db = dbs.openFile(config, "db4o/tutorial.osgi");

        try {
            storeFirstPilot(db);
            storeSecondPilot(db);
            System.out.println("retrieveAllPilots...");
            retrieveAllPilots(db);
            System.out.println("retrievePilotByName...");
            retrievePilotByName(db);
            System.out.println("retrievePilotByExactPoints...");
            retrievePilotByExactPoints(db);
            System.out.println("updatePilot...");
            updatePilot(db);
            System.out.println("delete pilots...");
            deleteFirstPilotByName(db);
            deleteSecondPilotByName(db);
        } finally {
            db.close();
        }

    }

    private <T> void listResult(List<T> result) {
        for (T entity : result) {
            System.out.println(entity);
        }
    }

    public void storeFirstPilot(ObjectContainer db) {
        Pilot pilot1 = new Pilot("Michael Schumacher", 100);
        db.store(pilot1);
        System.out.println("Stored " + pilot1);
    }

    public void storeSecondPilot(ObjectContainer db) {
        Pilot pilot2 = new Pilot("Rubens Barrichello", 99);
        db.store(pilot2);
        System.out.println("Stored " + pilot2);
    }

    public void retrieveAllPilotQBE(ObjectContainer db) {
        Pilot proto = new Pilot(null, 0);
        ObjectSet result = db.queryByExample(proto);
        listResult(result);
    }

    public void retrieveAllPilots(ObjectContainer db) {
        ObjectSet result = db.queryByExample(Pilot.class);
        listResult(result);
    }

    public void retrievePilotByName(ObjectContainer db) {
        Pilot proto = new Pilot("Michael Schumacher", 0);
        ObjectSet result = db.queryByExample(proto);
        listResult(result);
    }

    public void retrievePilotByExactPoints(ObjectContainer db) {
        Pilot proto = new Pilot(null, 100);
        ObjectSet result = db.queryByExample(proto);
        listResult(result);
    }

    public void updatePilot(ObjectContainer db) {
        ObjectSet result = db.queryByExample(new Pilot("Michael Schumacher", 0));
        Pilot found = (Pilot) result.next();
        found.addPoints(11);
        db.store(found);
        System.out.println("Added 11 points for " + found);
        retrieveAllPilots(db);
    }

    public void deleteFirstPilotByName(ObjectContainer db) {
        ObjectSet result = db.queryByExample(new Pilot("Michael Schumacher", 0));
        Pilot found = (Pilot) result.next();
        db.delete(found);
        System.out.println("Deleted " + found);
        retrieveAllPilots(db);
    }

    public void deleteSecondPilotByName(ObjectContainer db) {
        ObjectSet result = db
                .queryByExample(new Pilot("Rubens Barrichello", 0));
        Pilot found = (Pilot) result.next();
        db.delete(found);
        System.out.println("Deleted " + found);
        retrieveAllPilots(db);
    }

}
