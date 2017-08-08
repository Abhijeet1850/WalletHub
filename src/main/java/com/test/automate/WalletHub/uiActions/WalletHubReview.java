package com.test.automate.WalletHub.uiActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automate.WalletHub.testBase.TestBase;

public class WalletHubReview extends TestBase {
	public static final Logger log = Logger.getLogger(WalletHubReview.class.getName());
	
	@FindBy(xpath="//span[contains(@class,'wh-rating rating_5')]")
	WebElement starsToHover;
	
	@FindBy(xpath="//div[contains(@class,'wh-rating-choices-holder')]/a[1]")
	WebElement Star1;
	
	@FindBy(xpath="//div[contains(@class,'wh-rating-choices-holder')]/a[2]")
	WebElement Star2;
	
	@FindBy(xpath="//div[contains(@class,'wh-rating-choices-holder')]/a[3]")
	WebElement Star3;
	
	@FindBy(xpath="//div[contains(@class,'wh-rating-choices-holder')]/a[4]")
	WebElement Star4;
	
	@FindBy(xpath="//div[contains(@class,'wh-rating-choices-holder')]/a[5]")
	WebElement Star5;
	
	
	@FindBy(xpath="//*[@id='reviewform']/descendant::i")
	WebElement selectPolicy;
	
	@FindBy(id="review-content")
	WebElement reviewText;
		
	@FindBy(xpath="//div[@class='submit']/input[@class='btn blue']")
	WebElement submit;
	
	@FindBy(xpath=".//*[@id='overallRating']/a[5]")
	WebElement FiveStar;
	
	@FindBy(xpath=".//a[contains(text(),'has been posted')]")
	WebElement successReview;
	
	
	@FindBy(xpath="//a[@class='user'][@data-menu='m-user']")
	WebElement userMenu;
	
	@FindBy(xpath="//a[text()='Profile']")
	WebElement profile;
	
	@FindBy(xpath="//div[@class='profilenav']/ul/li[3]")
	WebElement reviewMenuItem;
	
	@FindBy(xpath="//div[@class='reviews']/div[1]/p")
	WebElement authenticateReview;
	
	
	@FindBy(xpath=".//*[@id='join-login']/form/div[1]/input")
	WebElement loginEmail;
	
	@FindBy(xpath=".//*[@id='join-login']/form/div[2]/input")
	WebElement loginPassword;
	
	@FindBy(xpath=".//*[@id='join-login']/form/div[5]/button[2]")
	WebElement loginButton;
	
	@FindBy(xpath="//a[@class='login']")
	WebElement profileLogin;
	
	
	public WalletHubReview(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void loginToApplication(String email, String password)
	{
		
		log.info("inside the loginToApplication function");
		
		profileLogin.click();
			
		log.info("sending email " + email);
		loginEmail.sendKeys(email);
		
		log.info("setting Password " + password);
		loginPassword.sendKeys(password);
		
		log.info("clicking Login in button");
		loginButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	public void starHover()
	{
	    log.info("====== star hover====="); 
		Actions action = new Actions(driver);
		action.moveToElement(starsToHover)
	      .moveToElement(Star1)
	      .moveToElement(Star2)
	      .moveToElement(Star3)
	      .moveToElement(Star4)
	      .moveToElement(Star5)
	      .click()
	      .build()
	      .perform();
	      
	}
	

	
	public void enterReview(String dropdownVal, String review)
	{
		log.info("select the element from list which is " + dropdownVal);
		selectPolicy.click();
		List<WebElement> dropdown = driver.findElements(By.xpath("//ul[@class='drop-el']/li"));
		log.info(dropdown.size());
		for(int i=1; i<=dropdown.size();i++)
		{
			log.info("//ul[@class='drop-el']/li[" + i + "]");
			log.info(driver.findElement(By.xpath("//ul[@class='drop-el']/li[" + i + "]")).getText());
			
			if(driver.findElement(By.xpath("//ul[@class='drop-el']/li[" + i + "]")).getText().equalsIgnoreCase(dropdownVal))
			{
				driver.findElement(By.xpath("//ul[@class='drop-el']/li[" + i + "]")).click();
				break;
			}
			
		}
                      
      
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
          log.info("enter review comments");
          reviewText.clear();
          reviewText.sendKeys(review);
          
		
          log.info("click submit button on review form");
          submit.click();
          
          
	}
	
	public String authenticateReview()
	{
		return(successReview.getText());
	}
	
	
	public void navigateToReviewsection()
	{
		Actions action1 = new Actions(driver);
		action1.moveToElement(userMenu)
		.moveToElement(profile)
		.click()
		.build()
		.perform();
		
		reviewMenuItem.click();
		
	}
	
	
	public String authenticateReviewText()
	{
		return(authenticateReview.getText());
	}
	
	
	
}
