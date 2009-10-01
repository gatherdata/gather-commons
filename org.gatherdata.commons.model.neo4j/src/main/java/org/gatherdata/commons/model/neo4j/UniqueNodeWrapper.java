/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.model.neo4j;

import java.net.URI;
import java.net.URISyntaxException;

import org.joda.time.DateTime;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;
import org.neo4j.api.core.NotFoundException;
import org.neo4j.util.NodeWrapperImpl;

public class UniqueNodeWrapper extends NodeWrapperImpl implements GatherNodeWrapper {
    
    // lazily created copies of some properties
    private URI lazyUid;
    private DateTime lazyDateCreated;
    
    public UniqueNodeWrapper(NeoService neo, Node underlyingNode) {
        super(neo, underlyingNode);
    }


    public DateTime getDateCreated() {
        if (lazyDateCreated == null) {
            lazyDateCreated = new DateTime((Long) getUnderlyingNode().getProperty(DATE_CREATED_PROPERTY));
        }
        return lazyDateCreated;
    }

    public URI getUid() {
        if (lazyUid == null) {
            try {
                String uidAsString = (String) getUnderlyingNode().getProperty(UID_PROPERTY);
                lazyUid = new URI(uidAsString);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (NotFoundException nfe) {
                lazyUid = null;
            }
        }
        return lazyUid;
    }

    public void setDateCreated(DateTime dateCreated) {
        getUnderlyingNode().setProperty(DATE_CREATED_PROPERTY, dateCreated.getMillis());
    }

    public void setUid(URI uid) {
        getUnderlyingNode().setProperty(UID_PROPERTY, uid.toASCIIString());
    }

}
