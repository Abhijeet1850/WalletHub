package com.test.automate.WalletHub.testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automate.WalletHub.excelReader.ExcelReader;



import org.openqa.selenium.WebElement;

public class TestBase {

	public static FileInputStream fis;
	public static FileInputStream fis1;
	public static Properties fbConfig = new Properties();
	public static Properties walletConfig = new Properties();
	public static WebDriver driver;
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static ExcelReader xlReader;
	//String URL = "https://www.facebook.com/";
	String Browser = "Chrome";
	ChromeOptions options;
	
	
	public void init(String URL,String Browser)
	{
		selectbrowser(Browser);
		getURL(URL);
		String log4jconfigpath = "log4j.properties";
		PropertyConfigurator.configure(log4jconfigpath);
		
		
	}
	
	public void selectbrowser(String Browser)
	{
		log.info("creating object of " + Browser);
		if(Browser.equalsIgnoreCase("chrome"))
		{
			options = new ChromeOptions();
			//prefs   = new HashMap<String, Object>();
			//options.setExperimentalOption("prefs", prefs);		
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automate\\WalletHub\\drivers\\chromedriver.exe");
			driver = new ChromeDriver(options);
			
		}
		else if(Browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automate\\WalletHub\\drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		else if(Browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\automate\\WalletHub\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
					
	}
	
	
	public void getURL(String URL)
	{
		log.info("navigating to URL" + URL);
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	public void waitForElement(int timeOutInSeconds,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void scroll()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,200)");
		
	}
	
	
	public String[][] getData(String ExcelName,String SheetName)
	{
		String Path = System.getProperty("user.dir") + "\\src\\main\\java\\com\\test\\automate\\WalletHub\\data\\" + ExcelName;
			
		xlReader = new ExcelReader(Path);
		String[][] data = xlReader.getDataFromSheet(SheetName, ExcelName);
	       return data;		 
	 
	 
	}
	
	
	public void loadConfigFile() throws IOException 
	{
		try
		{
		 fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\test\\automate\\WalletHub\\config\\Facebookconfig.properties");
		 fbConfig.load(fis);
		
		 fis1 = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\test\\automate\\WalletHub\\config\\WalletHubConfig.properties");
		 walletConfig.load(fis1);
		}
		catch(IOException e)
		{
			System.out.println(e);
			
		}
		
		finally
		{
			fis.close();
			fis1.close();
			
		}
	}
	
	
}
