/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.jpa.example.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.osgi.PersistenceProvider;
import org.gatherdata.commons.db.jpa.example.Pilot;

public class ExampleDao {

    EntityManagerFactory emf = null;
    EntityManager em = null;

    public void open() {
        if (em == null) {
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("eclipselink.ddl-generation", "drop-and-create-tables");
            properties.put("eclipselink.ddl-generation.output-mode", "database");
            // properties.put("eclipselink.classloader",
            // this.getClass().getClassLoader());
            emf = new PersistenceProvider().createEntityManagerFactory("pilot-example", properties);
            em = emf.createEntityManager();
            em.getTransaction().begin();

            System.out.println("EntityManger prepared");
        }
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    public void store(Pilot pilot) {
        em.persist(pilot);
    }

    public List getAll(Class entityClass) {
        return em.createQuery("select obj from " + entityClass.getSimpleName() + " obj").getResultList();
    }

    public Pilot getPilotNamed(String pilotName) {
        Pilot foundPilot = null;
        Query q = em.createQuery("select obj from Pilot obj  WHERE obj.name = :pilotName");
        q.setParameter("pilotName", pilotName);

        foundPilot = (Pilot)q.getSingleResult();
        
        return foundPilot;
        
    }

    public void delete(Pilot pilotToRemove) {
        em.remove(pilotToRemove);
        
    }
}
