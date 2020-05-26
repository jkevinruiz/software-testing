package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeTest {
	
	private Range range1;
	private Range range2;
	private Range range3;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	/* 
	 * combine method
	 * */
	@Test
	void combineNullRange() {	
		range1 = null;
		range2 = null;
		
		Range combinedRange1 = Range.combine(range1, range2);
		assertNull(combinedRange1, "The return should be NULL");
	}
	
	@Test 
	void combineValidRangeAndNull() {
		range1 = new Range(-1, 1);
		range2 = null;
		
		Range combinedRange1 = Range.combine(range1, range2);
		Range combinedRange2 = Range.combine(range2, range1);
		
		assertAll(
			() -> assertNotNull(combinedRange1, "The return should not be NULL"),
			() -> assertEquals(range1, combinedRange2, "The return should be a range [-1, 1]")
				);	
	}

	@Test
	void combineValidRange() {
		range1 = new Range(-1, 10);
		range2 = new Range(11,20);	
		Range expectedRange = new Range(-1,20); 
		Range combinedRange = Range.combine(range2, range1);
		assertAll(
			() -> assertEquals(expectedRange, combinedRange, "The new range should be -1 to 20")
				);
		
	}
	
	/* 
	 * constrain method
	 * */
	@Test
	void constrainLessThanLowerBound() {
		range1 = new Range(-1, 10);
		double actual = range1.constrain(-12);
		
		assertEquals(-1, actual , "The closest number to the specified value should be -1 ");
	}
	
	@Test
	void constrainGreaterThanUpperBound() {
		range1 = new Range(-1, 10);
		double actual = range1.constrain(12);
		
		assertEquals(10, actual, "The closest number to the specified value should be 10");
	}
	
	@Test
	void constrainWithinRange() {
		range1 = new Range(-1, 10);
		double actual1 = range1.constrain(6.5);
		double actual2 = range1.constrain(-1);
		double actual3 = range1.constrain(10);
		
		assertAll(
			() -> assertEquals(6.5, actual1, "The closest number to the specified value should be 6.5"),
			() -> assertEquals(-1, actual2, "The closest number to the specified value should be -1"),
			() -> assertEquals(10, actual3, "The closest number to the specified value should be 10")
				);
	}
	
	/* 
	 * contains method
	 * */
	@Test
	void containsLessThanLowerBound() {
		range1 = new Range(-1, 10);
		boolean actual = range1.contains(-15);
		
		assertFalse(actual, "Method should return false");
	}
	
	@Test
	void containsGreaterThanUpperBound() {
		range1 = new Range(-1, 10);
		boolean actual = range1.contains(15);
		
		assertFalse(actual, "Method should return false");
	}
	
	@Test
	void containsWithinRange() {
		range1 = new Range(-1, 10);
		boolean actual = range1.contains(5);
		
		assertTrue(actual, "Method should return true");
	}
	
	/* 
	 * equals method
	 * */
	@Test
	void equalsSameRange() {
		range1 = new Range(-1, 10);
		range2 = new Range(-1, 10);
		boolean actual1 = range1.equals(range2);
		boolean actual2 = range2.equals(range1);
		
		assertAll(
			() -> assertTrue(actual1, "This should return TRUE"),
			() -> assertTrue(actual2, "This should return TRUE")
				);
	}
	
	@Test
	void equalsSameLowerBoundDifferentUpperBound() {
		range1 = new Range(-1, 10);
		range2 = new Range(-1, 5);
		boolean actual1 = range1.equals(range2);
		boolean actual2 = range2.equals(range1);
		
		assertAll(
				() -> assertFalse(actual1, "This should return FALSE"),
				() -> assertFalse(actual2, "This should return FALSE")
					);
	}
	
	@Test
	void equalsDifferentLowerBoundSameUpperBound() {
		range1 = new Range(-1, 15);
		range2 = new Range(10, 15);
		boolean actual1 = range1.equals(range2);
		boolean actual2 = range2.equals(range1);
		
		assertAll(
				() -> assertFalse(actual1, "This should return FALSE"),
				() -> assertFalse(actual2, "This should return FALSE")
					);
	}
	
	@Test
	void equalsNullRange() {
		range1 = new Range(-1, 10);
		range2 = null;
		boolean actual = range1.equals(range2);
		
		assertFalse(actual, "This should return FALSE");
	}
	
	@Test
	void equalsCompletelyDifferentRanges() {
		range1 = new Range(-10, 0);
		range2 = new Range(16, 80);
		boolean actual = range1.equals(range2);
		assertFalse(actual, "This should return FALSE");
	}
	
	/* 
	 * expand method
	 * */
	@Test
	void expandPositiveValidValues() {
		range1 = new Range(2,6);		
		
		assertAll(
			() -> assertEquals(new Range(1, 8), Range.expand(range1, 0.25, 0.50), "The return range should be 1 to 8"),
			() -> assertEquals(new Range(0, 10), Range.expand(range1, 0.50, 1.0), "The return range should be 0 to 10")
				);

	}
	
	@Test
	void expandNegativeValidValues() {
		range1 = new Range(2,6);
		assertAll(
			() -> assertEquals(new Range(3, 8), Range.expand(range1, -0.25, 0.50), "The return range should be 3 to 8"),
			() -> assertEquals(new Range(1, 7), Range.expand(range1, 0.25, -0.25), "The return range should be 3 to 7")
				);

	}
	
	@Test
	void expandNullRange() {
		range1 = null;
		
		assertThrows(InvalidParameterException.class, () -> {Range.expand(range1, 1, 1);}, "This should throw an InvalidParameterException error" );
	}
	
	
	/*
	 * expandToInclude method
	 */

	@Test
	void expandToIncludeValid() {
		range1 = new Range(-10.0, 10.0);
		range2 = Range.expandToInclude(range1, 5.0);

		assertAll(() -> assertTrue(range2.contains(5.0), "Method should return TRUE"),
				() -> assertEquals(range1, range2, "The range should be unchanged"));
	}

	@Test
	void expandToIncludeLessThanLower() {
		range1 = new Range(-10.0, 10.0);
		range2 = Range.expandToInclude(range1, -11.0);
		range3 = new Range(-11.0, 10.0);

		assertAll(() -> assertTrue(range2.contains(-11.0), "Method should return TRUE"),
				() -> assertEquals(range3, range2, "The range should be extended down -11.0"));
	}

	@Test
	void expandToIncludeGreaterThanUpper() {
		range1 = new Range(-10.0, 10.0);
		range2 = Range.expandToInclude(range1, 11.0);
		range3 = new Range(-10.0, 11.0);

		assertAll(() -> assertTrue(range2.contains(11), "Method should return TRUE"),
				() -> assertEquals(range3, range2, "The range should be exteneded up to 11.0"));
	}

	@Test
	void expandToIncludeNull() {
		range1 = null;
		range2 = Range.expandToInclude(range1, 5.0);

		assertAll(() -> assertTrue(range2.contains(5.0), "Method should return TRUE"),
				() -> assertNotNull(range2, "A range is created"));
	}

	/*
	 * getCentralValue method
	 */

	@Test
	void getCentralValueOdd() {
		range1 = new Range(-1.0, 1.0);
		double result = range1.getCentralValue();

		assertEquals(0, result, "The median of range -1 to 1 is 0");
	}

	@Test
	void getCentralValueEven() {
		range1 = new Range(1.0, 4.0);
		double result = range1.getCentralValue();

		assertEquals(2.5, result, "The median of range 1 to 5 is 2.5");
	}

	/*
	 * getLength method
	 */
	
	@Test
	void getLengthValid() {
		range1 = new Range(1.0, 5.0);
		
		assertEquals(4.0, range1.getLength(),"The length should be 4.0");
	}
	
	/*
	 * getLowerBound method
	 */
	
	@Test
	void getLowerBoundValid() {
		range1 = new Range(-10.0, 10.0);
		
		assertEquals(-10.0, range1.getLowerBound(),"The lower bound should be -10.0");
	}
	
	/*
	 * getUpperBound method
	 */
	
	@Test
	void getUpperBoundValid() {
		range1 = new Range(-10.0, 10.0);
		
		assertEquals(10.0, range1.getUpperBound(),"The Upper bound should be 10.0");
	}
	
	/*
	 * toString method
	 */
	@Test
	void toStringTestExpectedReturn() {
		range1 = new Range(-1, 10);
		assertEquals("[-1,10]", range1.toString(), "The expected string output is [-1,10]");
	}
	
	/*
	 * shift method do not allow crossing
	 */
	@Test
	void shiftDoesNotAllowZeroCrossing() {
		range1 = new Range(-1,10);
		range2 = new Range(1,5);
		Range shiftedRange1 = Range.shift(range1, 2); // [0,12]
		Range shiftedRangeWithBooleanFalse = Range.shift(range1, 2, false);
		Range shiftedRange2 = Range.shift(range2, -2); //[0,3]
		assertAll(
			() -> assertEquals(new Range(0,12), shiftedRange1, "The shifted range should be [0,12]"),
			() -> assertEquals(new Range(0,3), shiftedRange2, "The shifted range should be [0,3]"),
			() -> assertEquals(new Range(0,12), shiftedRangeWithBooleanFalse, "The shifted range should be [0,12]")
				);
	}
	
	
	/*
	 * shift method allow crossing
	 */
	@Test
	void shiftShouldAllowZeroCrossing() {
		range1 = new Range(-1,10);
		range2 = new Range(1,5);
		Range shiftedRange1 = Range.shift(range1, 2, true); // [1,12]
		Range shiftedRange2 = Range.shift(range2, -2, true); // [-1,3]
		
		assertAll(
		() -> assertEquals(new Range(1,12), shiftedRange1, "The shifted range should be [1,12]" ),
		() -> assertEquals(new Range(-1,3), shiftedRange2, "The shifted range should be [-1,3]")
		);
	}
	
	
	/*
	 * intersect method
	 */
	@Test
	void intersectOverlaps() {
		range1 = new Range(-1,10);
		range2 = new Range(10,11);
		boolean intersect1 = range1.intersects(5, 8);
		boolean intersect2 = range1.intersects(-15, -1);
		
		assertAll(
			() -> assertTrue(intersect1, "The return should be TRUE"),
			() -> assertTrue(intersect2, "The return should be TRUE")
				);
	}
	
	@Test
	void intersectDoNotOverlap() {
		range1 = new Range(-1,10);
		boolean intersect1 = range1.intersects(11,15);
		boolean intersect2 = range1.intersects(-15, -5);
		
		assertAll(
			() -> assertFalse(intersect1, "The return should be FALSE"),
			() -> assertFalse(intersect2, "The return should be FALSE")
				);
	}
	
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
}
