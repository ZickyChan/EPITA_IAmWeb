/**
 * 
 */
package fr.epita.iamtesting.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Zick
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestSpring {

	@Autowired
	private String test;
	
	private static final Logger LOGGER = LogManager.getLogger(TestSpring.class);
	
	@Test
	public void textStringHello(){
		LOGGER.info("Spring said: {}", test);
	}
}
