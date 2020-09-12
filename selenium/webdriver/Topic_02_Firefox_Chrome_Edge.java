package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Firefox_Chrome_Edge {
  WebDriver driver;
	String ProjectFolder = System.getProperty("user.dir");
  @Test
  public void TC_01_Run_On_Firefox() {
	  driver =new FirefoxDriver();
	  driver.get("https://facebook.com");
	  driver.quit();
  }
  @Test
  public void TC_02_Run_On_Chrome() {
	  System.setProperty("webdriver.chrome.driver",ProjectFolder+"\\browserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("https://facebook.com");
	  driver.quit();
  }
  @Test
  public void TC_03_Run_On_Edge_Chromium() {
	  System.setProperty("webdriver.edge.driver", ".\\browserDrivers\\msedgedriver.exe");
	  driver = new EdgeDriver();
	  driver.get("https://facebook.com"); //take URL
	  driver.quit(); //close browser
  }
  
}
