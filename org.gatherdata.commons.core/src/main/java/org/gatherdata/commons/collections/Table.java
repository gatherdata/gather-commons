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
