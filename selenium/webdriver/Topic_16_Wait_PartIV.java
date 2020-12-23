package webdriver;

import java.util.concurrent.TimeUnit;

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

public class Topic_16_Wait_PartIV {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocataion = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Only_Implicit() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		driver.findElement(By.xpath("//a[text()='15']/parent::td")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and text()='Tuesday, December 15, 2020']")).isDisplayed());
	}

	//@Test
	public void TC_02_Only_Explicit() throws InterruptedException {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		//chờ cho ngày 15 được click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='15']/parent::td")));
		driver.findElement(By.xpath("//a[text()='15']/parent::td")).click(); 
		
				//chờ cho ngày được chọn thành công
		
		
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='15']/parent::td[@class='rcSelected']")));
			
			//chờ cho loading icon biến mất
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']/parent::div[@style='display:none;']")));
			Thread.sleep(30000);
			//get text
			Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),"Tuesday, December 15, 2020");
	}
	//@Test
	public void TC_03_Only_Explicit() {
		driver.get("https://www.file.io/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectLocataion+"\\uploadFile\\unsplash1.jpg");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("progress-button-total")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='download-url']")));
		driver.findElement(By.id("download-url")).click();
	
	}
	@Test
	public void TC_04_Only_Explicit() {
		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.name("btnLogin")).click();
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		alert.accept();
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
