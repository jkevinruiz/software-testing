package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Tag;

class GetCumulativePercentagesIntegration {
	
	private KeyedValues data;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		DefaultKeyedValues value = new DefaultKeyedValues();
		value.addValue("0", 5.0);
		value.addValue("1", 9.0);
		value.addValue("2", 2.0);
		
		data = value;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Timeout(5)
	@Tag("integration")
	void getCumulativePercentagesTest() {
				assertAll(
			() -> assertEquals(5.0/16.0, DataUtilities.getCumulativePercentages(data).getValue("0")),
			() -> assertEquals((5.0+9.0)/16, DataUtilities.getCumulativePercentages(data).getValue("1")),
			() -> assertEquals((5.0+9.0+2.0)/16, DataUtilities.getCumulativePercentages(data).getValue("2"))
				);
	}

}
