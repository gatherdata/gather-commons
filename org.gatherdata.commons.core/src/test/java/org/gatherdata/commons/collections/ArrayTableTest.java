package org.gatherdata.commons.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.gatherdata.commons.collections.ArrayTable;
import org.gatherdata.commons.collections.Table;
import org.junit.Test;

/**
 * Unit tests for expected behavior of ArrayTable.
 * 
 */
public class ArrayTableTest {

	/**
	 * Upon construction, the table should initially
	 * have a single row.
	 */
	@Test
	public void shouldInitiallyHaveRow() {
		Table<String> testTable = new ArrayTable<String>();
		assertEquals(1, testTable.getRowCount());
	}

	
	/**
	 * The table should be constructed with a specific
	 * column count.s
	 */
	@Test
	public void shouldInitialToSpecificColumnCount() {
		final int TABLE_COLUMN_COUNT = 4;
		
		Table<String> testTable = new ArrayTable<String>(TABLE_COLUMN_COUNT);
		assertEquals(TABLE_COLUMN_COUNT, testTable.getColumnCount());
	}
	
	/**
	 * The returned row should be the same size as the
	 * current number of columns in the table.
	 */
	@Test
	public void shouldReturnRowWithSizeThatMatchesColumnCount() {
		final int TABLE_COLUMN_COUNT = 4;
		
		Table<String> testTable = new ArrayTable<String>(TABLE_COLUMN_COUNT);
		List<String> initialRow = testTable.getRow(0);
		assertEquals(TABLE_COLUMN_COUNT , initialRow.size());
	}
	
	/**
	 * A row should be return in a list of cell values.
	 */
	@Test
	public void shouldReturnRowAsListOfValues() {
		Table<String> testTable = new ArrayTable<String>();
		List<String> initialRow = testTable.getRow(0);
		assertNotNull(initialRow);
	}
	
	/**
	 * Upon construction, the initial row should
	 * be empty (filled with null)
	 */
	@Test
	public void shouldNullTheInitialRow() {
		Table<String> testTable = new ArrayTable<String>();
		List<String> initialRow = testTable.getRow(0);
		for (String s : initialRow) {
			assertNull(s);
		}
	}
	
	/**
	 * The list of values returned for a row
	 * should be of fixed size, throwing an
	 * exception if a value is added.
	 */
	@Test (expected = UnsupportedOperationException.class)
	public void shouldReturnRowListThatCantBeExpanded() {
		Table<String> testTable = new ArrayTable<String>();
		List<String> initialRow = testTable.getRow(0);
		initialRow.add("this operation should be un-supported");
	}
	
	/**
	 * The list of values returned for a row
	 * should be of fixed size, throwing an
	 * exception if a value is removed.
	 */
	@Test (expected = UnsupportedOperationException.class)
	public void shouldReturnRowListThatCantBeShrunk() {
		Table<String> testTable = new ArrayTable<String>();
		List<String> initialRow = testTable.getRow(0);
		initialRow.remove(0);
	}
	/**
	 * A row returned from the table shoulds support
	 * random (indexed) access to values.
	 */
	@Test
	public void shouldAllowRandomAccessToRowValues() {
		final String EXPECTED_VALUE = "test";

		Table<String> testTable = new ArrayTable<String>();
		List<String> initialRow = testTable.getRow(0);
		
		for (int i=0; i<initialRow.size(); i++) {
			initialRow.set(i, EXPECTED_VALUE);
			assertEquals(EXPECTED_VALUE, initialRow.get(i));
		}
	}
	
	/**
	 * The table should support random column, row indexed
	 * access to values.
	 * 
	 */
	@Test
	public void shouldAllowRandomAccessToCells() {
		final String EXPECTED_BASE_VALUE = "test";

		Table<String> testTable = new ArrayTable<String>();
		for (int col=0; col<testTable.getColumnCount(); col++) {
			for (int row=0; row<testTable.getRowCount(); row++) {
				String expectedCellValue = EXPECTED_BASE_VALUE + "_" + col + ":" + row;
				testTable.set(col, row, expectedCellValue);
				assertEquals(expectedCellValue, testTable.get(col, row));
			}
		}
	}
}
