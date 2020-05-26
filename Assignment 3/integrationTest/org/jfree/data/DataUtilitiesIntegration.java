package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataUtilitiesIntegration {
	
	private Values2D data;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		DefaultKeyedValues2D value = new DefaultKeyedValues2D();
		value.setValue(1, 0, 0);
		value.setValue(2, 0, 1);
		
		data = value;
		System.out.println(data.getColumnCount());
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculateColumnTotal() {
		assertEquals(2, DataUtilities.calculateColumnTotal(data, 1));
	}

}
