package com.VerifyContent.testcases;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class VerifyContent {
	WebDriver driver;
	//static Logger logger;
	ReadConfig readconfig = new ReadConfig();
		
		@BeforeClass
		void setup()
		{
			System.setProperty("webdriver.chrome.driver","C://seleniumDrivers//chromedriver84/chromedriver.exe"); 
			driver = new ChromeDriver();
			
			//logger = Logger.getLogger("LaunchUrl"); // provide test case  or class name as a parameter
			//PropertyConfigurator.configure("log4j.properties");

		}
		
		@Test (priority=1)
		void openUrl()
		{
			String expectedUrl=readconfig.getApplicationUrl();
			System.out.println("Open Url "+ expectedUrl);
			driver.get(expectedUrl);
		}
		
		@Test (priority=2)
		// test case fails if text 'World' is not found
		void checkContent() 
		{		
			try {
				WebElement hdrWorld = driver.findElement(By.linkText("World"));
				System.out.println("Located element " + hdrWorld.getAttribute("href"));
			}
			catch (NoSuchElementException e)
			{
				System.out.println("Element not found: "+e.getMessage());
				Assert.fail();
			}
			
		}
		
		@Test (priority=3)
		//test case fails if text 'Universe' found
		void checkInvalidContent()
		{
			try{
				WebElement invalidTxt = driver.findElement(By.xpath("//*[text()='Universe']"));
				if (invalidTxt.isDisplayed())
				{
					System.out.println("Element: " + invalidTxt.getText() +" found");
					Assert.fail();
				}
			}
			catch (NoSuchElementException e)
			{
				System.out.println("Element not found: " + e.getMessage()); //default test case pass
			}
		}
			
		@AfterClass
		void closeBrowser()
		{
			driver.close();
		} 

}
