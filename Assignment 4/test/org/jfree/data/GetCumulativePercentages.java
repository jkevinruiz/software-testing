package org.jfree.data;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyInt;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Tag;

class GetCumulativePercentages {
	private KeyedValues data;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		data = mock(KeyedValues.class);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Timeout(5)
	@Tag("mock")
	void test() {
		when(data.getItemCount()).thenReturn(3);
		
		when(data.getKey(0)).thenReturn(0);
		when(data.getKey(1)).thenReturn(1);
		when(data.getKey(2)).thenReturn(2);
	
		when(data.getValue(0)).thenReturn(5.0); // 0.45
		when(data.getValue(1)).thenReturn(9.0); // 1.25
		when(data.getValue(2)).thenReturn(2.0); // 1.27
		
		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		
		
		assertAll(
				() -> assertEquals(5.0/16.0, result.getValue(0), "The first element should be 0.3125"),
				() -> assertEquals((5.0+9.0)/16.0, result.getValue(1), "The second element should be 0.875"),
				() -> assertEquals((5.0+9.0+2.0)/16.0, result.getValue(2), "The third element should be 1.0")
			);
		
	}
	
	@Test
	@Timeout(5)
	@Tag("mock")
	void invalidDataGetCumulativePercentagesTest() {
		when(data.getKey(0)).thenReturn(null);
		when(data.getValue(0)).thenReturn(null);
		
		assertThrows(IllegalArgumentException.class, () -> {DataUtilities.getCumulativePercentages(data);}, "This should throw an IllegalArgumentException error" );
	}

}
