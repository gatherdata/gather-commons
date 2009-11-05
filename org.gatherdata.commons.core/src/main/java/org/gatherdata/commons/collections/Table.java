/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.collections;

import java.util.Collection;
import java.util.List;

public interface Table<T> {

	public int getColumnCount();
	
	public int getRowCount();

	public void set(int column, int ofRow, T toValue);
	
	public T get(int column, int ofRow);
	
	public List<T> getRow(int row);
	
	public List<T> getColumn(int column);
	
	public Collection<T> getSubtables();
	
}
