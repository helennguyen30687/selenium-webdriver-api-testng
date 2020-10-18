package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Run_On_Chrome_Firefox {
	String project_location = System.getProperty("user.dir");
	
  @Test
  public void TC_01_Run_On_Firefox() {
	  WebDriver driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://facebook.com");
	  driver.quit();
  } 
  
  @Test
  public void TC_02_Run_On_Chrome() {
	//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
	//  driver = new ChromeDriver();
	  System.setProperty("webdriver.chrome.driver", project_location + "\\browserDrivers\\chromedriver.exe");
	  WebDriver driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("http://facebook.com");
	  driver.quit();
  }
  
  
}
 