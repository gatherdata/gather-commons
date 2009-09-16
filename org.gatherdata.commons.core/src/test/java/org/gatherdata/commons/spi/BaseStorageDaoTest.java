package org.gatherdata.commons.spi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.gatherdata.commons.junit.ContainsAll.containsAll;
import org.easymock.internal.matchers.Contains;
import org.gatherdata.commons.io.MimeTypes;
import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.net.CbidFactory;
import org.gatherdata.commons.net.GatherUrnFactory;
import org.gatherdata.commons.spi.StorageDao;
import org.hamcrest.core.IsNot;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * Base unit tests for the behavior of ArchiverDao implementations.
 *
 */
public abstract class BaseStorageDaoTest<EntityType extends UniqueEntity, DaoType extends StorageDao<EntityType>> {

    protected DaoType dao;

    protected abstract EntityType createMockEntity();
    protected abstract DaoType createStorageDaoImpl();

    protected abstract void beginTransaction();
    
    protected abstract void endTransaction();
    
    @Before
    public void createDaoUnderTest() {
        dao = createStorageDaoImpl();
    }
    
    /**
     * The entity returned from a save operation should be equal to the
     * entity passed in (though it may not be the same object).
     */
    @Test 
    public void shouldReturnEntityWhichIsEqualToSavedEntity() {
        EntityType entityToSave = createMockEntity();
        EntityType savedEntity = dao.save(entityToSave);
        assertEquals(entityToSave, savedEntity);
    }
    
    /**
     * After saving an entity, the dao should affirm that it exists
     * in the storage.
     */
    @Test
    public void shouldAffirmThatASavedEntityExists() {
        EntityType entityToSave = createMockEntity();
        EntityType savedEntity = dao.save(entityToSave);
        
        assertTrue(dao.exists(entityToSave.getUid()));
    }
    
    @Test
    public void shouldGetAllSavedEntities() {
        final int EXPECTED_NUMBER_OF_ENTITIES = new Random().nextInt(100);
        List<EntityType> entitiesToSave = new ArrayList<EntityType>();
        
        for (int i=0; i< EXPECTED_NUMBER_OF_ENTITIES; i++) {
            EntityType entityToSave = createMockEntity();
            entitiesToSave.add(entityToSave);
            dao.save(entityToSave);
        }
        
        beginTransaction();
        Iterable<EntityType> allEntitiesList = (Iterable<EntityType>) dao.getAll();
        assertThat(allEntitiesList, containsAll(entitiesToSave));
        endTransaction();
    }

    @Test
    public void shouldGetASpecificEntityIdentifiedByUid() throws URISyntaxException {
        final int EXPECTED_NUMBER_OF_ENTITIES = new Random().nextInt(100);
        List<EntityType> entitiesToSave = new ArrayList<EntityType>();
        
        for (int i=0; i< EXPECTED_NUMBER_OF_ENTITIES; i++) {
            EntityType entityToSave = createMockEntity();
            entitiesToSave.add(entityToSave);
            dao.save(entityToSave);
        }
        
        beginTransaction();
        for (EntityType entityToRetrieve : entitiesToSave) {
            URI uidToRetrieve = entityToRetrieve.getUid();
            String uidAsString = uidToRetrieve.toASCIIString();
            uidToRetrieve = new URI(uidAsString);
            EntityType retrievedEntity = dao.get(entityToRetrieve.getUid());            
            assertNotNull(retrievedEntity);
            assertEquals(entityToRetrieve, retrievedEntity);
        }
        endTransaction();
    }
    
    @Test
    public void shouldRemoveSpecificEntityIdentifiedByUid() {
        final int INITIAL_NUMBER_OF_ENTITIES = new Random().nextInt(100);
        final int INDEX_OF_ENTITY_TO_REMOVE = new Random().nextInt(INITIAL_NUMBER_OF_ENTITIES);
        List<EntityType> entitiesToSave = new ArrayList<EntityType>();
        
        EntityType entityToRemove = null;
        for (int i=0; i< INITIAL_NUMBER_OF_ENTITIES; i++) {
            EntityType entityToSave = createMockEntity();
            entitiesToSave.add(entityToSave);
            dao.save(entityToSave);
            if (i == INDEX_OF_ENTITY_TO_REMOVE) {
                entityToRemove = entityToSave;
            }
        }
        
        dao.remove(entityToRemove.getUid());
        
        assertFalse(dao.exists(entityToRemove.getUid()));

        Iterable<EntityType> allEntitiesList = (Iterable<EntityType>) dao.getAll();
        assertThat(allEntitiesList, not(containsAll(entitiesToSave)) );
    }

}
