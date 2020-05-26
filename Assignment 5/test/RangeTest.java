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
class RangeTest {
	
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
	 * Range Constructor
	 * */
	@Test
	@Timeout(5)
	@Tag("exception")
	void rangeConstructor() {
		assertThrows(IllegalArgumentException.class, () -> { range1 = new Range(2,1);}, "Invalid range, should throw an exception");
	}
	

	/* 
	 * combine method
	 * */
	@Test
	@Timeout(5)
	@Tag("function")
	void combineNullRange() {	
		range1 = null;
		range2 = null;
		
		assertNull(Range.combine(range1, range2), "The return should be NULL");
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void combineValidRangeAndNull() {
		range1 = new Range(-1, 1);
		range2 = null;
		
		assertAll(
			() -> assertNotNull(Range.combine(range1, range2), "The return should not be NULL"),
			() -> assertEquals(range1, Range.combine(range2, range1), "The return should be a range [-1, 1]")
				);	
	}

	@Test
	@Timeout(5)
	@Tag("function")
	void combineValidRange() {
		range1 = new Range(-1, 10);
		range2 = new Range(11,20);	
		
		Range combinedRange = Range.combine(range2, range1);
		assertAll(
			() -> assertEquals(new Range(-1,20), combinedRange, "The new range should be -1 to 20")
				);
		
		
	}
	
	/* 
	 * constrain method
	 * */
	@Test
	@Timeout(5)
	@Tag("function")
	void constrainLessThanLowerBound() {
		range1 = new Range(-1, 10);
		
		assertEquals(-1, range1.constrain(-12), "The closest number to the specified value should be -1 ");
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void constrainGreaterThanUpperBound() {
		range1 = new Range(-1, 10);
		
		assertEquals(10, range1.constrain(12), "The closest number to the specified value should be 10");
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void constrainWithinRange() {
		range1 = new Range(-1, 10);
		
		assertAll(
			() -> assertEquals(6.5, range1.constrain(6.5), "The closest number to the specified value should be 6.5"),
			() -> assertEquals(-1, range1.constrain(-1), "The closest number to the specified value should be -1"),
			() -> assertEquals(10, range1.constrain(10), "The closest number to the specified value should be 10")
				);
	}
	
	/* 
	 * contains method
	 * */
	@Test
	@Timeout(5)
	@Tag("function")
	void containsLessThanLowerBound() {
		range1 = new Range(-1, 10);
		
		assertFalse(range1.contains(-15), "Method should return false");
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void containsGreaterThanUpperBound() {
		range1 = new Range(-1, 10);
		
		assertFalse(range1.contains(15), "Method should return false");
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void containsWithinRange() {
		range1 = new Range(-1, 10);
		
		assertTrue(range1.contains(5), "Method should return true");
	}
	
	/* 
	 * equals method
	 * */
	@Test
	@Timeout(5)
	@Tag("function")
	void equalsSameRange() {
		range1 = new Range(-1, 10);
		range2 = new Range(-1, 10);
		
		assertAll(
			() -> assertTrue(range1.equals(range2), "This should return TRUE"),
			() -> assertTrue(range2.equals(range1), "This should return TRUE")
				);
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void equalsSameLowerBoundDifferentUpperBound() {
		range1 = new Range(-1, 10);
		range2 = new Range(-1, 5);
		
		assertAll(
				() -> assertFalse(range1.equals(range2), "This should return FALSE"),
				() -> assertFalse(range2.equals(range1), "This should return FALSE")
					);
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void equalsDifferentLowerBoundSameUpperBound() {
		range1 = new Range(-1, 15);
		range2 = new Range(10, 15);
		
		assertAll(
				() -> assertFalse(range1.equals(range2), "This should return FALSE"),
				() -> assertFalse(range2.equals(range1), "This should return FALSE")
					);
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void equalsNullRange() {
		range1 = new Range(-1, 10);
		range2 = null;
		
		assertFalse(range1.equals(range2), "This should return FALSE");
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void equalsCompletelyDifferentRanges() {
		range1 = new Range(-10, 0);
		range2 = new Range(16, 80);
		
		assertFalse(range1.equals(range2), "This should return FALSE");
	}
	
	/* 
	 * expand method
	 * */
	@Test
	@Timeout(5)
	@Tag("function")
	void expandPositiveValidValues() {
		range1 = new Range(2,6);		
		
		assertAll(
			() -> assertEquals(new Range(1, 8), Range.expand(range1, 0.25, 0.50), "The return range should be 1 to 8"),
			() -> assertEquals(new Range(2, 10), Range.expand(range1, 0.50, 1.0), "The return range should be 0 to 10")
				);

	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void expandNegativeValidValues() {
		range1 = new Range(2,6);
		assertAll(
			() -> assertEquals(new Range(-1, 8), Range.expand(range1, -0.25, 0.50), "The return range should be 3 to 8"),
			() -> assertEquals(new Range(1, 5), Range.expand(range1, 0.25, -0.25), "The return range should be 3 to 7")
				);

	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void expandNullRange() {
		range1 = null;
		
		assertThrows(Exception.class, () -> {Range.expand(range1, 1, 1);}, "This should throw an Exception error" );
	}
	
	/*
	 * expandToInclude method
	 */
	@Test
	@Timeout(5)
	@Tag("function")
	void expandToIncludeValid() {
		range1 = new Range(-10.0, 10.0);
		range2 = Range.expandToInclude(range1, 5.0);

		assertAll(() -> assertTrue(range2.contains(5.0), "Method should return TRUE"),
				() -> assertEquals(range1, range2, "The range should be unchanged"));
	}

	@Test
	@Timeout(5)
	@Tag("function")
	void expandToIncludeLessThanLower() {
		range1 = new Range(-10.0, 10.0);
		range2 = Range.expandToInclude(range1, -11.0);
		range3 = new Range(-11.0, 10.0);

		assertAll(() -> assertTrue(range2.contains(-11.0), "Method should return TRUE"),
				() -> assertEquals(range3, range2, "The range should be extended down -11.0"));
	}

	@Test
	@Timeout(5)
	@Tag("function")
	void expandToIncludeGreaterThanUpper() {
		range1 = new Range(-10.0, 10.0);
		range2 = Range.expandToInclude(range1, 11.0);
		range3 = new Range(-10.0, 11.0);

		assertAll(() -> assertTrue(range2.contains(11), "Method should return TRUE"),
				() -> assertEquals(range3, range2, "The range should be exteneded up to 11.0"));
	}

	@Test
	@Timeout(5)
	@Tag("function")
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
	@Timeout(5)
	@Tag("function")
	void getCentralValueOdd() {
		range1 = new Range(-1.0, 1.0);
		double result = range1.getCentralValue();

		assertEquals(0, result, "The median of range -1 to 1 is 0");
	}

	@Test
	@Timeout(5)
	@Tag("function")
	void getCentralValueEven() {
		range1 = new Range(1.0, 4.0);
		double result = range1.getCentralValue();

		assertEquals(2.5, result, "The median of range 1 to 5 is 2.5");
	}

	/*
	 * getLength method
	 */
	@Test
	@Timeout(5)
	@Tag("function")
	void getLengthValid() {
		range1 = new Range(1.0, 5.0);
		
		assertEquals(4.0, range1.getLength(),"The length should be 4.0");
	}
	
	/*
	 * getLowerBound method
	 */
	@Test
	@Timeout(5)
	@Tag("function")
	void getLowerBoundValid() {
		range1 = new Range(-10.0, 10.0);
		
		assertEquals(-10.0, range1.getLowerBound(),"The lower bound should be -10.0");
	}
	
	/*
	 * getUpperBound method
	 */
	@Test
	@Timeout(5)
	@Tag("function")
	void getUpperBoundValid() {
		range1 = new Range(-10.0, 10.0);
		
		assertEquals(10.0, range1.getUpperBound(),"The Upper bound should be 10.0");
	}
	
	/*
	 * toString method
	 */
	@Test
	@Timeout(5)
	@Tag("function")
	void toStringTestExpectedReturn() {
		range1 = new Range(-1, 10);
		assertEquals("Range[-1.0,10.0]", range1.toString(), "The expected string output is [-1.0,10.0]");
	}
	
	/*
	 * shift method do not allow crossing
	 */
	@Test
	@Timeout(5)
	@Tag("function")
	void shiftDoesNotAllowZeroCrossing() {
		range1 = new Range(-1,10);
		range2 = new Range(1,5);
		range3 = new Range(0,15);
		Range shiftedRange1 = Range.shift(range1, 2d ); // [0,12]
		Range shiftedRange2 = Range.shift(range2, -2d); //[0,3]
		Range shiftedRange3 = Range.shift(range3, 4d);
		Range shiftedRangeWithBooleanFalse = Range.shift(range1, 4d, false);
		assertAll(
			() -> assertEquals(new Range(0,12), shiftedRange1, "The shifted range should be [0,12]"),
			() -> assertEquals(new Range(0,3), shiftedRange2, "The shifted range should be [0,3]"),
			() -> assertEquals(new Range(0,14), shiftedRangeWithBooleanFalse, "(shiftedWithBooleanFalse) The shifted range should be [0,12]"),
			() -> assertEquals(new Range(4,19), shiftedRange3, "The shifted range should be [4,19]")
				);
	}
	
	
	/*
	 * shift method allow crossing
	 */
	@Test
	@Timeout(5)
	@Tag("function")
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
	@Timeout(5)
	@Tag("function")
	void intersectOverlaps() {
		range1 = new Range(-1,10);
		range2 = new Range(10,11);
		
		assertAll(
			() -> assertTrue(range1.intersects(5, 8), "The (5,8) should return TRUE"),
			() -> assertTrue(range1.intersects(-15, -1), "The (-15,-1) should return TRUE")
				);
	}
	
	@Test
	@Timeout(5)
	@Tag("function")
	void intersectDoNotOverlap() {
		range1 = new Range(-1,10);
		
		assertAll(
			() -> assertFalse(range1.intersects(11,15), "The (11,15) should return FALSE"),
			() -> assertFalse(range1.intersects(-15, -5), "The (-15,-5) should return≈ FALSE")
				);
	}

	
	@Test
	@Timeout(5)
	@Tag("function")
	void hashCodeTest() {
		range1 = new Range(0,0);
		int hashCode = range1.hashCode();
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
}
