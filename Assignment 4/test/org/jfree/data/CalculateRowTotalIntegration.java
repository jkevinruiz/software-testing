package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Tag;

class CalculateRowTotalIntegration {

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

	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * test for valid data in both inputs in CalculateRowTotal method
	 */
	@Test
	void testCalculateRowTotalWithValidInput() {
		value = new DefaultKeyedValues2D();
		value.setValue(1, 0, 0);
		value.setValue(2, 1, 1);
		value.setValue(3, 2, 2);
		
		data = value;
		assertEquals(1, DataUtilities.calculateRowTotal(data, 0), "The return should be a total of one");
	}
	
	/*
	 * test for null input for Valid2D data and valid input for int row in CalculateRowTotal method
	 */
	@Test
	void testCalculateRowTotalWithInvalidObject() {

		assertThrows(Exception.class, 
				() -> {DataUtilities.calculateRowTotal(null, 0);}, 
					"This should throw an Exception error" );
	}
	
	/*
	 * test for invalid row input for CalculateRowTotal method
	 */
	@Test
	void testCalculateRowTotalWithInvalidRow() {
		value = new DefaultKeyedValues2D();
		value.setValue(1, 0, 0);
		value.setValue(2, 1, 1);
		value.setValue(3, 2, 2);
		
		data = value;
		
		assertEquals(0, DataUtilities.calculateRowTotal(data, 1), "This return should be zero");


	}
}
