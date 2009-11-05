/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.spi;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;


import org.gatherdata.commons.model.UniqueEntity;
import org.junit.Test;

public abstract class BaseStorageServiceTest<EntityType extends UniqueEntity, DaoType extends StorageDao<EntityType>, StorageServiceType extends StorageService<EntityType>> {

    protected int mockPlainTextCount = 0;

    protected abstract EntityType createMockEntity();
    
    protected abstract DaoType createMockDao();
    
    protected abstract StorageServiceType createStorageServiceImpl();
    
    protected abstract void injectDaoIntoService(DaoType dao, StorageServiceType serviceImpl);

    @Test
    public void shouldUseDaoToSave()
    {
        DaoType mockStorageDao = createMockDao();
        StorageServiceType serviceImpl = createStorageServiceImpl();
        injectDaoIntoService(mockStorageDao, serviceImpl);
        
        EntityType mockEntity = createMockEntity();

        mockStorageDao.beginTransaction();
        expect(mockStorageDao.save(mockEntity)).andReturn(mockEntity);
        mockStorageDao.endTransaction();
        
        replay(mockStorageDao);
        
        serviceImpl.save(mockEntity);
        
        verify(mockStorageDao);
        
    }
    
    @Test
    public void shouldUseDaoToGet()
    {
        DaoType mockStorageDao = createMockDao();
        StorageServiceType serviceImpl = createStorageServiceImpl();
        injectDaoIntoService(mockStorageDao, serviceImpl);
        
        EntityType mockEntity = createMockEntity();

        mockStorageDao.beginTransaction();
        expect(mockStorageDao.get(mockEntity.getUid())).andReturn(mockEntity);
        mockStorageDao.endTransaction();
        
        replay(mockStorageDao);
        
        serviceImpl.get(mockEntity.getUid());
        
        verify(mockStorageDao);
        
    }

    @Test
    public void shouldUseDaoToCheckExists()
    {
        DaoType mockStorageDao = createMockDao();
        StorageServiceType serviceImpl = createStorageServiceImpl();
        injectDaoIntoService(mockStorageDao, serviceImpl);
        
        EntityType mockEntity = createMockEntity();
        EntityType missingEntity = createMockEntity();

        expect(mockStorageDao.exists(mockEntity.getUid())).andReturn(true);
        expect(mockStorageDao.exists(missingEntity.getUid())).andReturn(false);
        
        replay(mockStorageDao);
        
        assertTrue(serviceImpl.exists(mockEntity.getUid()));
        assertFalse(serviceImpl.exists(missingEntity.getUid()));
        
        verify(mockStorageDao);
    }

}
