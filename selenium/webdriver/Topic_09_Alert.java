package webdriver;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	By resultText = By.xpath("//p[@id='result']");
	String username = "admin";
	String password = "admin";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// click
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		// cho alert load
		explicitWait.until(ExpectedConditions.alertIsPresent());
		// switch frame
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
	}

//	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");
	}

//	@Test
	public void TC_03_Prompt_Alert() {
		String loginValue = "nguyen thanh huong";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(loginValue);
		alert.accept();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + loginValue);
	}

//	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
		sleepInSecond(3);
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}

	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		sleepInSecond(3);
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		System.out.println(basicAuthenLink);
		driver.get(getAuthenticationUrl(basicAuthenLink,username,password));
		Assert.assertTrue(driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]"))
				.isDisplayed());
	}

	public String getAuthenticationUrl(String oldUrl, String userName, String passWord) {
		String urlValue[] = oldUrl.split("//");
		String newUrl = urlValue[0] + "//" + userName +":" + passWord + "@" + urlValue[1];
		return newUrl;
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
