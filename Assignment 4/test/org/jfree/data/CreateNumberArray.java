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

class CreateNumberArray {

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

	@Test
	@Timeout(5)
	@Tag("function")
	void testCreateNumberArrayValidInput() {
		double[] data = new double[] {1.0,2.0,3.0,4.0};
		Number[] actual = DataUtilities.createNumberArray(data);
		Number[] expected = new Number[] {1.0,2.0,3.0,4.0};
		
		assertAll(
			() -> assertEquals(expected.getClass(), actual.getClass(), "Result should be similar type Number object"),
			() -> assertEquals(expected, actual, "The object should be equals")
				);
	}
	
	@Test
	@Timeout(5)
	@Tag("exception")
	void testCreateNumberArrayInvalidInput() {
		double[] data = null;
		
		assertThrows(Exception.class, () -> {
			DataUtilities.createNumberArray(data);
		}, "Invalid object input, should throw an Exception");
	}

}
