package fr.epita.iamtesting.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class TestJUnitForMarven {
	
	private static final Logger LOGGER = LogManager.getLogger(TestJUnitForMarven.class); 
	
	@BeforeClass
	public static void globalSetUp() throws IOException{
		LOGGER.info("Global Setup");
	}
	
	@Before
	public void beforeTest(){
		LOGGER.info("Start test");
	}
	
	@After
	public void afterTest(){
		LOGGER.info("End test");
	}
	
	@Test
	public void testJUnit(){
		
		//GIVEN
		File file = new File("/temp/test.txt");
		
		//WHEN
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//THEN
		Assert.assertTrue(file.exists());
		
		LOGGER.info("ZICK");	
	}
	
	@Test
	public void testJUnit2(){
		
		//GIVEN
		File file = new File("/temp/test2.txt");
		
		//WHEN
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Can't create file");
		}
		
		//THEN
		Assert.assertTrue(file.exists());
		
		LOGGER.info("test 2");
		
	}

	@AfterClass
	public static void globalTearDown(){
		LOGGER.info("Global Tear Down");
		
		try {
			Files.delete(new File("/temp/test.txt").toPath());
			Files.delete(new File("/temp/test2.txt").toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error occured when trying to delete");
		}
	}

}
