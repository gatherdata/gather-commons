/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.model.util;

import java.net.URI;

import org.gatherdata.commons.model.DescribedEntity;
import org.gatherdata.commons.net.CbidFactory;

public class DescribedEntityIdGenerator implements IdentityGenerator<DescribedEntity>{

    public URI identify(DescribedEntity unidentifiedEntity) {
        return CbidFactory.createCbid(unidentifiedEntity.getDateCreated() + unidentifiedEntity.getName() + unidentifiedEntity.getDescription());
    }

}
