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

class CalculateColumnTotal {
	
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

	@Test
	@Timeout(5)
	@Tag("mock")
	void testCalculateColumnTotalValidInputs() {
		when(value.getColumnCount()).thenReturn(4);
		when(value.getRowCount()).thenReturn(3);
		
		when(value.getValue(0, 2)).thenReturn(5);
		when(value.getValue(1, 2)).thenReturn(7);
		when(value.getValue(2, 2)).thenReturn(1);
		
		assertEquals(13, DataUtilities.calculateColumnTotal(value, 2), "The column total should be 13");
		verify(value, never()).getColumnCount();
		verify(value, times(1)).getRowCount();
		verify(value, times(3)).getValue(anyInt(), anyInt());
	}
	
	@Test
	@Timeout(5)
	@Tag("mock")
	void testCalculateColumnTotalWithInvalidObject() {
		when(value.getRowCount()).thenThrow(new InvalidParameterException());
		
		assertThrows(InvalidParameterException.class, 
				() -> {
					DataUtilities.calculateColumnTotal(value, 2);
				}, "The error thrown should be InvalidParameterException");
		
		verify(value, times(1)).getRowCount();
		verify(value, never()).getValue(anyInt(), anyInt());
	}
	
	@Test
	@Timeout(5)
	@Tag("mock")
	void testCalculateColumnTotalWithInvalidColumn() {
		when(value.getColumnCount()).thenReturn(4);
		when(value.getRowCount()).thenReturn(3);
		
		when(value.getValue(0, 6)).thenReturn(0);
		when(value.getValue(1, 6)).thenReturn(0);
		when(value.getValue(2, 6)).thenReturn(0);
		
		assertEquals(0, DataUtilities.calculateColumnTotal(value, 6), "The column total should be 13");
		verify(value, never()).getColumnCount();
		verify(value, times(1)).getRowCount();
		verify(value, times(3)).getValue(anyInt(), anyInt());

	}
}
