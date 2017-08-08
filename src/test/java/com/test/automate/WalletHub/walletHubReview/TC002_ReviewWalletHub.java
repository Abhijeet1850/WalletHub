package com.test.automate.WalletHub.walletHubReview;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.automate.WalletHub.testBase.TestBase;
import com.test.automate.WalletHub.uiActions.WalletHubReview;

public class TC002_ReviewWalletHub extends TestBase {

	public static final Logger log = Logger.getLogger(TC002_ReviewWalletHub.class.getName());
    public String dummyReview = "this is sample text2 if you are designing a brand new website for someone, most times you will have to make sure the prototype looks finished by inserting text or photos or what have you. The purpose of this is so the person viewing the prototype has a chance to actually feel and understand the idea behind what you have created.Now in some circumstances, designers may use squares and rectangles to help you visualize what should and could be in a specific location";
	
	WalletHubReview review;
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
		init(walletConfig.getProperty("URL"),walletConfig.getProperty("Broswer"));
		
	}
	
	@Test(dataProvider="getTestData")
	public void loginAndPostStatus(String Username,String Password,String DropdownVal,String StatusText)
	{
		log.info("Data from data provider" +Username +" " + Password + " " + DropdownVal + " " + StatusText );
		review = new WalletHubReview(driver);
		review.loginToApplication(Username, Password);
		review.starHover();
		review.enterReview(DropdownVal,StatusText );
		
		Assert.assertTrue(review.authenticateReview().contains("has been posted"));
		
		review.navigateToReviewsection();
		Assert.assertEquals(review.authenticateReviewText(),StatusText);
		
		
		
	}
	
	
	@DataProvider
	public String[][] getTestData()
	{
		String[][] testRecords = getData("walletLogin.xlsx", "TC002");
		return testRecords;
	}
	
	@AfterClass
	public void tearDown()
	{
		log.info("quitting test");
		driver.quit();
		
	}
	
}
