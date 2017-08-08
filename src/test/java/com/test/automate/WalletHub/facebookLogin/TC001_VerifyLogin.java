package com.test.automate.WalletHub.facebookLogin;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.test.automate.WalletHub.testBase.TestBase;
import com.test.automate.WalletHub.uiActions.FacebookLogin;

public class TC001_VerifyLogin extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC001_VerifyLogin.class.getName());

	@BeforeClass
	public void setUp()
	{
		log.info("Setting up test");
		try {
			loadConfigFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		init(fbConfig.getProperty("URL"),fbConfig.getProperty("Broswer"));
	}
	
	@Test(dataProvider="getTestData")
	public void loginAndPostStatus(String Username, String Password, String StatusText)
	{
		log.info("==================Starting test loginAndPostStatus================");
		log.info("data from data provider" + Username + " " + Password + " " + StatusText );
		FacebookLogin Flogin = new FacebookLogin(driver);
	    Flogin.loginToApplication(Username,Password);
	    String currentURL = driver.getCurrentUrl();
	    Assert.assertTrue((currentURL.contains("welcome")));
	    log.info("this is verified and going to post status");
	    
	  
	   Flogin.postStatus(StatusText);
	   Assert.assertTrue(Flogin.verifyStatusPosted().contains(StatusText));
	   log.info("==================Finished test loginAndPostStatus================"); 
	}
	
	
	@DataProvider
	public String[][] getTestData()
	{
		String[][] testRecords = getData("FbLogin.xlsx", "TC001");
		return testRecords;
	}
	
	
	@AfterClass
	public void tearDown()
	{
		log.info("quitting test");
		driver.quit();
		
	}
	
}
