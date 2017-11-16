package fr.epita.iamtesting.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestLog4jIntegration {
	private static final Logger LOGGER = LogManager.getLogger(TestLog4jIntegration.class); 
	
	@Test
	public void testLogger(){
		LOGGER.error("error");
		LOGGER.debug("debug");
	}
	
}
