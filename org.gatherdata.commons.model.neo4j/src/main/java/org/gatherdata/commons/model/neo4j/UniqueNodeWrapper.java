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

import org.gatherdata.commons.model.UniqueEntity;
import org.gatherdata.commons.net.CbidFactory;
import org.joda.time.DateTime;
import org.neo4j.api.core.NeoService;
import org.neo4j.api.core.Node;
import org.neo4j.api.core.NotFoundException;
import org.neo4j.util.NodeWrapperImpl;

public class UniqueNodeWrapper extends NodeWrapperImpl implements GatherNodeWrapper, UniqueEntity {
    
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((lazyDateCreated == null) ? 0 : lazyDateCreated.hashCode());
        result = prime * result + ((lazyUid == null) ? 0 : lazyUid.hashCode());
        return result;
    }

    public URI selfIdentify() {
        return CbidFactory.createCbid(Integer.toHexString(hashCode()));
    }

}
