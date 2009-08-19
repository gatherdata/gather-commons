package org.gatherdata.commons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Resizable array-of-arrays Table implementation.
 * 
 * @param <E> type of table cells
 */
public class ArrayTable<E> implements Table<E> {
	
	private final static int DEFAULT_COLUMN_WIDTH = 10;
	
	private int columnCount;
	
	private List<Row<E>> rows;
	
	public ArrayTable() {
		this(DEFAULT_COLUMN_WIDTH);
	}
	
	/**
	 * Constructs a table with the specified initial number 
	 * of columns, and a single empty row.
	 * @param initialColumnCount
	 */
	public ArrayTable(int initialColumnCount) {
		columnCount = initialColumnCount;
		rows = new ArrayList<Row<E>>();
		
		addRow();
	}

	public void addRow() {
		Row<E> addedRow = new Row<E>(columnCount);
		rows.add(addedRow);
	}

	public E get(int column, int ofRow) {
		Row<E> r = getRow(ofRow);
		return r.get(column);
	}

	public List<E> getColumn(int column) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

	public int getColumnCount() {
		return columnCount;
	}

	public Row<E> getRow(int row) {
		return rows.get(row);
	}

	public int getRowCount() {
		return rows.size();
	}

	public Collection<E> getSubtables() {
		// TODO Auto-generated method stub
		return null;
	}

	public void set(int column, int ofRow, E toValue) {
		Row<E> r = getRow(ofRow);
		r.set(column, toValue);
	}

}
