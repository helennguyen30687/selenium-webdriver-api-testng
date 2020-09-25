package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css_Part_II_FindElement {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver =new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
  }
  
  @Test
  public void TC_01_Login_Empty_Email_And_Password() {
	  //Click vao My Account link

	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	
	 driver.findElement(By.id("email")).sendKeys("");
	 driver.findElement(By.name("login[password]")).sendKeys("");
	 driver.findElement(By.id("send2")).click();
	 //kiem tra validation message
	
	 Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
	 Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
  } 
  @Test
  public void TC_02_Login_Invalid_Email() {
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		 driver.findElement(By.id("email")).sendKeys("12341234@123456");
		 driver.findElement(By.name("login[password]")).sendKeys("123456");
		 driver.findElement(By.id("send2")).click();
		 
		 Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
  }
  @Test
  public void TC_03_Login_Invalid_Password() {
		
		  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		  driver.findElement(By.id("email")).sendKeys("automation@gmail.com"); 
		  driver.findElement(By.name("login[password]")).sendKeys("123"); 
		  driver.findElement(By.id("send2")).click();
		  Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		   }
  @Test
  public void TC_04_Login_Incorrect_Email_Or_Password() {
		
		 driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click(); 
		 driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		  driver.findElement(By.name("login[password]")).sendKeys("123123123");
		  driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		
		 
		   }
  @AfterClass
  public void afterClass() {
  }

}
