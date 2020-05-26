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

class CreateNumberArray2D {

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
	 * method for testing createNumberArray2D with valid input
	 */
	@Test
	@Timeout(5)
	@Tag("function")
	void testCreateNumberArray2DWithValidInput() {
			double[][] arr = new double[][] {{1.0,2.0,3.0,4.0},{5.0,6.0,7.0,8.0}};
			Number[][] actual = DataUtilities.createNumberArray2D(arr);
			Number[][] expected = new Number[][] {{1.0,2.0,3.0,4.0},{5.0,6.0,7.0,8.0}};
			
			assertAll(
				() -> assertEquals(expected.getClass(), actual.getClass(), "Result should be similar type Number object"),
				() -> assertEquals(expected, actual, "The object should be equals")
					);	
		}

	/*
	 * method for testing createNumberArray2D with invalid input
	 */
	@Test
	@Timeout(5)
	@Tag("function")
	void testCreateNumberArray2DWithInvalidInput() {
		assertThrows(Exception.class, () ->{DataUtilities.createNumberArray2D(null);}, "This should throw an Exception" );
		
	}

}
