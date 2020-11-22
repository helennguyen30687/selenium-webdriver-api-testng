package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		driver.findElement(By.id("Loginform")).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertTrue(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());

		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='loginForm']")));
		Assert.assertFalse(driver.findElement(By.xpath("//form[@id='loginForm']")).isDisplayed());
	}

	// @Test
	public void TC_02_Fixed_Popup() {
		driver.get("https://bni.vn/");
		// wait for element display
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		driver.findElement(By.xpath("//input[@value='JOIN WITH US']")).click();

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='Close']")));
		driver.findElement(By.xpath("//img[@alt='Close']")).click();
		// wait for element not display
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")));
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
	}
	// @Test
	public void TC_02_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		//step 02
		// xuat hien
		// ko xuat hien
		sleepInSecond(10);
//		boolean a = driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed();
//		System.out.println(a);
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='close-mailch']")));
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSecond(10);
		}
			// step 03
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//input[@class='search-field']")));
			driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys("selenium");
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section//span[@class='glass']")));
			driver.findElement(By.xpath("//section//span[@class='glass']")).click();
			//step 04
			
	
	}
	@Test
	public void TC_03_Random_Popup_Not_In_DOM() {
		driver.get("https://www.facebook.com/");
		List<WebElement> popup=driver.findElements(By.xpath("//img[@alt='home_popup_banner']"));
		if(popup.size()>0 && popup.get(0).isDisplayed()) {
			//close popup
			System.out.println("close popup");
			explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='shopee-popup__close-btn']")));
			driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
		}else {
			//ko xuat hien
			System.out.println("Popup ko xuat hien");
		}
		sleepInSecond(5);
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
	//	driver.quit();
	}
}
