/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.collections;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Row<E> extends AbstractList<E> implements List<E> {

	private static final int DEFAULT_INITIAL_SIZE = 10;
	
	private List<E> values;

	protected Row() {
		this(DEFAULT_INITIAL_SIZE);
	}
	
	public Row(int columnCount) {
		 values = new ArrayList<E>(columnCount);
		 for (int i=0; i<columnCount; i++) {
			 values.add(null);
		 }
	}
	
	@Override
	public E set(int index, E value) {
		return values.set(index,value);
	}

	@Override
	public E get(int index) {
		return values.get(index);
	}

	@Override
	public int size() {
		return values.size();
	}

}
