package webdriver;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Wait_PartIII {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocataion = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Found_Element() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("Start explicit Wait: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		System.out.println("End explicit Wait: " + getDateTimeNow());
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
		System.out.println("End implicit Wait: " + getDateTimeNow());
	}

	// @Test
	public void TC_02_Not_Found_Element() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("Start implicit Wait: " + getDateTimeNow());
		try {
			Assert.assertTrue(driver.findElement(By.id("emailAddress")).isDisplayed());
		} catch (Exception e) {
	
		}
		System.out.println("End implicit Wait: " + getDateTimeNow());
	}

	//@Test
	public void TC_03_Not_Found_element_implicit_Equal_Explicit() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 4);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		System.out.println("Start implicit Wait: " + getDateTimeNow());
		try {
			Assert.assertTrue(driver.findElement(By.id("emailAddress")).isDisplayed());
		} catch (Exception e) {
			
		}
		System.out.println("End implicit Wait: " + getDateTimeNow());

		System.out.println("Start explicit Wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailAddress")));
		} catch (Exception e) {
		
		}
		System.out.println("End explicit Wait: " + getDateTimeNow());
	}
	//@Test
	public void TC_04_Not_Found_element_implicit_Less_Explicit() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 6);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		System.out.println("Start implicit Wait: " + getDateTimeNow());
		try {
			Assert.assertTrue(driver.findElement(By.id("emailAddress")).isDisplayed());
		} catch (Exception e) {
		
		}
		System.out.println("End implicit Wait: " + getDateTimeNow());

		System.out.println("Start explicit Wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailAddress")));
		} catch (Exception e) {
			
		}
		System.out.println("End explicit Wait: " + getDateTimeNow());
	}
	//@Test
	public void TC_05_Not_Found_element_implicit_Greater_Explicit() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 4);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		System.out.println("Start implicit Wait: " + getDateTimeNow());
		try {
			Assert.assertTrue(driver.findElement(By.id("emailAddress")).isDisplayed());
		} catch (Exception e) {
		
		}
		System.out.println("End implicit Wait: " + getDateTimeNow());

		System.out.println("Start explicit Wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailAddress")));
		} catch (Exception e) {
			
		}
		System.out.println("End explicit Wait: " + getDateTimeNow());
	}
	@Test
	public void TC_06_Not_Found_Element_Only_Explicit_Param_By() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 4);
	

		System.out.println("Start explicit Wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailAddress")));
		} catch (Exception e) {
			
		}
		System.out.println("End explicit Wait: " + getDateTimeNow());
		
	
	}
	@Test
	public void TC_07_Not_Found_Element_Only_Explicit_Param_Element() {
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 4);
	

		System.out.println("Start explicit Wait: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("emailAddress"))));
		} catch (Exception e) {
			
		}
		System.out.println("End explicit Wait: " + getDateTimeNow());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}
