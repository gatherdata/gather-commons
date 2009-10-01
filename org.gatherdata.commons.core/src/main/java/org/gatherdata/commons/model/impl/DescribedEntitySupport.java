/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.model.impl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.gatherdata.commons.model.DescribedEntity;

public class DescribedEntitySupport extends UniqueEntitySupport {

	public boolean equals(DescribedEntity lhs, DescribedEntity rhs) {
		boolean areEqual = super.equals(lhs, rhs);

		if (areEqual) {
			if (lhs.getName() == null) {
				if (rhs.getName() != null)
					areEqual = false;
			} else if (!lhs.getName().equals(rhs.getName()))
				areEqual = false;
		}

		return areEqual;
	}

	public static boolean deepEquals(DescribedEntity lhs, DescribedEntity rhs) {
		boolean areEqual = true;
		if (lhs != rhs) {
			areEqual = new EqualsBuilder().append(lhs.getUid(), rhs.getUid())
					.append(lhs.getName(), rhs.getName()).append(
							lhs.getDescription(), rhs.getDescription()).append(
							lhs.getDateCreated(), rhs.getDateCreated())
					.isEquals();
		}
		return areEqual;
	}

}
