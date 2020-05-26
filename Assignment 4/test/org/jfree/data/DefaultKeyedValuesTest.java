package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.jfree.util.SortOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class DefaultKeyedValuesTest {
	
	private DefaultKeyedValues value, value2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testDefaultKeyedValues() {
		value = new DefaultKeyedValues();
		
		assertEquals(value, new DefaultKeyedValues(), "Creates an empty DefaultKeyedValues object");
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testGetItemCount() {
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("2", 2);
		
		assertEquals(2, value.getItemCount(), "Returned count should be 2");
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testGetValueInt() {
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("2", 2);
		
		assertEquals(1, value.getValue(1), "Returned value should be 1");
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testGetKey() {
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("2", 3);
		
		assertEquals("2", value.getKey(1), "Returned key should be 2");
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testGetIndex() {
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("2", 3);
		
		assertEquals(1, value.getIndex("2"), "Returned index should be 1");
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testGetKeys() {
		value = new DefaultKeyedValues();
		value.addValue("3", 1);
		value.addValue("2", 3);
		List expected = new ArrayList<>();
		expected.add("3");
		expected.add("2");
		
		assertEquals(expected, value.getKeys(), "Return should be a list of [3,2]" );
		
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testGetValueComparable() {
		value = new DefaultKeyedValues();
		value.addValue("3", 1);
		value.addValue("2", 3);
		
		assertAll(
			() -> assertEquals(1, value.getValue("3"), "Return should be 1"),
			() -> assertThrows(UnknownKeyException.class, () -> {value.getValue("-1");})
				);

	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testAddValueComparableDouble() {
		value = new DefaultKeyedValues();
		value.addValue("3", 1.2);
		value.addValue("2", 3.2);
		
		assertEquals(3.2, value.getValue("2"), "Return is 3.2");
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testAddValueComparableNumber() {
		value = new DefaultKeyedValues();
		value.addValue("3", 1);
		value.addValue("2", 3);
		
		assertEquals(3, value.getValue("2"), "Return is 3");
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testSetValueComparableDouble() {
		value = new DefaultKeyedValues();
		
		value.addValue("1", 1.0);
		value.setValue("1", 3.2);
		
		assertEquals(3.2, value.getValue("1"), "Return should be 3.2");
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testSetValueComparableNumber() {
		value = new DefaultKeyedValues();
		
		value.addValue("1", 1);
		value.setValue("1", 3);
		assertAll(
			() -> assertEquals(3, value.getValue("1"), "Return should be 3"),
			() -> assertThrows(IllegalArgumentException.class, () -> {value.setValue(null, 2);})
				);

		
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testRemoveValueInt() {
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.removeValue(0);
		assertEquals(new DefaultKeyedValues(), value , "Value should now be empty");
		
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testRemoveValueComparable() {
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("2", 2);
		value.addValue("3", 3);
		value.removeValue("3");
		value.removeValue("1");
		assertEquals(new DefaultKeyedValues(), value , "Value should now be empty");
	}

	@Timeout(5)
	@Tag("unittest")
	@Test
	void testSortByKeysAscending() {
		DefaultKeyedValues expected = new DefaultKeyedValues();
		expected.addValue("1", 1);
		expected.addValue("2", 2);
		expected.addValue("3", 3);
		
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("3", 2);
		value.addValue("2", 3);
		

		
		SortOrder asc = SortOrder.ASCENDING;
		value.sortByKeys(asc);
		assertTrue(expected.equals(value));
		
		
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testSortByKeysDescending() {
		SortOrder desc = SortOrder.DESCENDING;
		
		DefaultKeyedValues expected = new DefaultKeyedValues();
		expected.addValue("3", 3);
		expected.addValue("2", 2);
		expected.addValue("1", 1);
		
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("3", 2);
		value.addValue("2", 3);
		

		value.sortByKeys(desc);
		
		assertTrue(expected.equals(value));
		
		
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testSortByValuesDescending() {
		
		SortOrder desc = SortOrder.DESCENDING;
		
		DefaultKeyedValues expected = new DefaultKeyedValues();
		expected.addValue("3", 3);
		expected.addValue("2", 2);
		expected.addValue("1", 1);
		
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("3", 2);
		value.addValue("2", 3);
		

		value.sortByValues(desc);
		
		assertTrue(expected.equals(value));
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testSortByValuesAscending() {
		DefaultKeyedValues expected = new DefaultKeyedValues();
		expected.addValue("1", 1);
		expected.addValue("2", 2);
		expected.addValue("3", 3);
		
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("3", 2);
		value.addValue("2", 3);
				
		SortOrder asc = SortOrder.ASCENDING;
		value.sortByValues(asc);
		
		assertTrue(expected.equals(value));
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testEqualsObject() {
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("2", 2);
		value.addValue("3", 3);
		
		assertAll(
			() -> assertTrue(value.equals(value)),
			() -> assertFalse(value.equals(value2))
				);
	}
	
	@Timeout(5)
	@Tag("unittest")
	@Test
	void testClone() throws CloneNotSupportedException {
		value = new DefaultKeyedValues();
		value.addValue("1", 1);
		value.addValue("2", 2);
		value.addValue("3", 3);
		
		value2 = (DefaultKeyedValues) value.clone();
		assertEquals(value, value2, "both values should be the same" );
	}

}