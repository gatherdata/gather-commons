/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.db4o;

import java.io.File;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.hamcrest.Matcher;


import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.Configuration;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.io.MemoryStorage;
import com.db4o.reflect.ReflectClass;
import com.db4o.reflect.generic.GenericReflector;
import com.db4o.reflect.jdk.JdkReflector;
import com.db4o.typehandlers.TypeHandlerPredicate;

public class DateTimeHandlerTest {

    private final static String DB4O_FILE_NAME = "test.db4o";
    private static ObjectContainer db4o = null;
    private static final MockEntity expectedEntity = new MockEntity();

    @BeforeClass
    public static void createdExpectedEntity() {
        expectedEntity.setTestDate(new DateTime());
    }
    
    @AfterClass
    public static void removeTestDbFile() {
        new File(DB4O_FILE_NAME).delete();
    }
    
    @Before
    public void startDatabase() {
        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();

        GenericReflector reflector = new GenericReflector(
                null,
                new JdkReflector(Thread.currentThread().getContextClassLoader()));
        TypeHandlerPredicate predicate = new DateTimeHandlerPredicate(reflector);
        
        config.common().registerTypeHandler(predicate, new DateTimeHandler());

        db4o = Db4oEmbedded.openFile(config, DB4O_FILE_NAME);
    }

    @After
    public void shutdownDatabase() {
        db4o.close();
    }
    
    @Test
    public void shouldHaveAValidObjectContainer() {
        assertThat(db4o, notNullValue());
    }
    
    @Test 
    public void shouldSaveDateTime() {        
        db4o.store(expectedEntity);
    }

    @Test 
    public void shouldRetrieveDateTime() {
        DateTime expectedDate = expectedEntity.getTestDate();
                
        List<MockEntity> foundEntities = db4o.query(MockEntity.class);
        
        assertThat(foundEntities.size(), is(1));
        
        MockEntity foundEntity = foundEntities.get(0);
        
        assertThat(foundEntity.getTestDate(), is(expectedDate));
        
    }
    
    static class MockEntity {
        DateTime testDate;
        
        public DateTime getTestDate() {
            return testDate;
        }
        
        public void setTestDate(DateTime testDate) {
            this.testDate = testDate;
        }
    }
}
