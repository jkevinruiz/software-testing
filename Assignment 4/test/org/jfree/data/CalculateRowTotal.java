package org.jfree.data;

import static org.mockito.Matchers.anyInt;

import static org.mockito.Mockito.*;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Tag;

class CalculateRowTotal {
	
	private Values2D value;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {	
		value = mock(Values2D.class);	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	/*
	 * test for valid data in both inputs in CalculateRowTotal method
	 */
	@Test
	void testCalculateRowTotalWithValidInputs() {
		when(value.getColumnCount()).thenReturn(4);
		when(value.getRowCount()).thenReturn(3);
		
		when(value.getValue(0,0)).thenReturn(5);
		when(value.getValue(1, 0)).thenReturn(3);
		
		assertEquals(5, DataUtilities.calculateRowTotal(value, 0), .01d);
		verify(value, times(1)).getColumnCount();
		verify(value, never()).getRowCount();
		verify(value, times(3)).getValue(anyInt(), anyInt());
	}

	/*
	 * test for null input for Valid2D data and valid input for int row in CalculateRowTotal method
	 */
	@Test
	void testCalculateRowTotalWithInvalidObject() {
		when(value.getColumnCount()).thenThrow(new InvalidParameterException());
		
		assertThrows(InvalidParameterException.class, 
				() -> {DataUtilities.calculateRowTotal(value, 0);}, 
					"This should throw an InvalidParameterException error" );
		verify(value, times(1)).getColumnCount();
		verify(value, never()).getValue(anyInt(), anyInt());

	}
	
	/*
	 * test for invalid row input for CalculateRowTotal method
	 */
	@Test
	void testCalculateRowTotalWithInvalidRow() {
		when(value.getColumnCount()).thenReturn(4);
		when(value.getRowCount()).thenReturn(3);
		
		when(value.getValue(0,0)).thenReturn(5);
		when(value.getValue(1, 0)).thenReturn(3);
		
		assertEquals(0, DataUtilities.calculateRowTotal(value, 9));
		verify(value, times(1)).getColumnCount();
		verify(value, never()).getRowCount();
		verify(value, times(3)).getValue(anyInt(), anyInt());
		
	}
	
	

}
