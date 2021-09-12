package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Upload_File {
	WebDriver driver;
	String project_location = System.getProperty("user.dir");
	String documentFilePath = project_location + "\\uploadFile\\CoverLetter_Anna_Thi_Pan.pdf";
	String unSplashFileName = "unsplash.jpg";
	String unSplash1FileName = "unsplash1.jpg";
	String unSplashFileNamePath = project_location + "\\uploadFile\\" + unSplashFileName;
	String unSplash1FileNamePath = project_location + "\\uploadFile\\" + unSplash1FileName;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_One_File_Per_Time() {
		// driver.get("https://gofile.io/uploadFiles");
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		// work voi element //input[@type='file']
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(unSplashFileNamePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(unSplash1FileNamePath);

		// verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + unSplashFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + unSplash1FileName + "']")).isDisplayed());
		// click start upload each file
		List<WebElement> startUpload = driver.findElements(By.cssSelector("table .start"));
		// verify file upload thanh cong
		for (WebElement startButton : startUpload) {
			startButton.click();
			sleepInSecond(1);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + unSplashFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + unSplash1FileName + "']")).isDisplayed());
	}

//	@Test
	public void TC_02_Multiple_File_Per_Time() {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		// work voi element //input[@type='file']
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys(unSplashFileNamePath + "\n" + unSplash1FileNamePath);

		// verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + unSplashFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + unSplash1FileName + "']")).isDisplayed());
		// click start upload each file
		List<WebElement> startUpload = driver.findElements(By.cssSelector("table .start"));
		// verify file upload thanh cong
		for (WebElement startButton : startUpload) {
			startButton.click();
			sleepInSecond(1);
		}
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + unSplashFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + unSplash1FileName + "']")).isDisplayed());

		Assert.assertTrue(isImageLoadedSuccess("//a[@title='" + unSplashFileName + "']/img"));
		sleepInSecond(6);
		Assert.assertTrue(isImageLoadedSuccess("//a[@title='" + unSplash1FileName + "']/img"));

		driver.get("https://automationfc.github.io/basic-form/");
		Assert.assertTrue(isImageLoadedSuccess("//img[@alt='broken_05']"));
		Assert.assertFalse(isImageLoadedSuccess("//img[@alt='broken_02']"));
		Assert.assertTrue(isImageLoadedSuccess("//img[@alt='broken_03']"));
	}

	@Test
	public void TC_03_SendKey_Google_Translate() {
		driver.get("https://translate.google.com/?sl=en&tl=vi&op=docs");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(documentFilePath);
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isImageLoadedSuccess(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof "
				+ "arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
	}

	@AfterClass
	public void afterClass() { 
		// driver.quit();
	}
}
