package com.test.automate.WalletHub.uiActions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automate.WalletHub.testBase.TestBase;



public class FacebookLogin extends TestBase {
	
	public static final Logger log = Logger.getLogger(FacebookLogin.class.getName());
		
	@FindBy(id="email")
	WebElement loginEmail;
	
	@FindBy(id="pass")
	WebElement loginPassword;
	
	@FindBy(xpath="//*[@id='pass']/following::input[contains(@value,'Log In')]")
	WebElement Login;
	
	@FindBy(xpath="//a[contains(@title,'Profile')]/span")
	WebElement profileIcon;
		
	@FindBy(xpath="//div[contains(@id,'recent_capsule_container')]/descendant::p[1]")
	WebElement authenticateStatusPost;
	
	@FindBy(xpath="//*[text()='Post']")
	WebElement postButton;
	
	@FindBy(xpath="//textarea[contains(@placeholder,'on your mind')]")
	WebElement writeStatus;
	
	
	
	DateTimeFormatter dtf;
	LocalDateTime now;
	
	public FacebookLogin(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void loginToApplication(String email, String password)
	{
		
		log.info("inside the loginToApplication function");
		log.info("sending email " + email);
		loginEmail.sendKeys(email);
			
		log.info("setting Password " + password);
		loginPassword.sendKeys(password);
		
		log.info("clicking Login in button");
		Login.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	public void postStatus(String statusText)
	{
		System.out.println("code is in poststatus");
		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		now = LocalDateTime.now();
		System.out.println(dtf.format(now).toString());	
		
		
		waitForElement(5, profileIcon);
		System.out.println("profile icon clicking");
		profileIcon.click();
		
		System.out.println("profile icon clicked");
		
		
		
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", writeStatus);
		
	    System.out.println("scrolling");
	    
		writeStatus.sendKeys(statusText + " This is Test Status posted at " + dtf.format(now) );
		 try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	   			 
		postButton.click();
		System.out.println("clicked button");
			
		
	}
	
	
	public String verifyStatusPosted()
	{
		return(authenticateStatusPost.getText());
	}
	
	

}
