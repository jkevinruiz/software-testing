package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class calculateColumnTotalIntegration {

	private Values2D data;
	private DefaultKeyedValues2D value;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		value = new DefaultKeyedValues2D();
		value.setValue(0, 0, 0);
		value.setValue(0, 1, 1);
		value.setValue(0, 2, 2);
		
		data = value;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculateColumnTotalValidInput() {
		assertEquals(0, DataUtilities.calculateColumnTotal(data, 1), "The return should be a total of 2");
	}
	
	@Test
	void testCalculateColumnTotalInvalidObject() {
		assertThrows(InvalidParameterException.class, () -> { DataUtilities.calculateColumnTotal(null, 2);}, "The method should throw an InvalidParameterException");
	}
	
	@Test
	void testCalculateColumnTotalInvalidInput() {
//		assertEquals(0, DataUtilities.calculateColumnTotal(data, 4), "The return should be 0 with an invalid column");
		DefaultKeyedValues2D value1 = new DefaultKeyedValues2D();
		Values2D data1 = value1;
		
		assertAll(
			() -> assertEquals(0, DataUtilities.calculateColumnTotal(data1, 0), "Empty data object, return should be 0"),
			() -> assertEquals(0, DataUtilities.calculateColumnTotal(data, 4),  "Invalid column, return should be 0")
				);
	}

}
