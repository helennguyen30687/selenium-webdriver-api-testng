package webdriver;

import java.util.Set;
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


public class Topic_13_Window_Tab {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);

	}

//	@Test
	public void TC_01_Only_2_Windows_Tab() {
		// Step1access page https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentWindowID = driver.getWindowHandle();
		System.out.println(parentWindowID);
		// Step2: Click Google link=> Switch new tab
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToWindowByID(parentWindowID);
		// Step3: verify title of new window = GOOGLE
		Assert.assertEquals(driver.getTitle(), "Google");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());
		String googleWindowID = driver.getWindowHandle();
		// Step4: Switch back parent window
		switchToWindowByID(parentWindowID);

		// Step5: Click FB link => Switch new tab
		switchToWindowByID(googleWindowID);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

	}

	// @Test
	public void TC_02_Greater_Than_2_Windows_Tabs() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		switchToTWindowByTitle("Google");

		Assert.assertTrue(driver.findElement(By.xpath("//img[@id='hplogo']")).isDisplayed());
		switchToTWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// Step5: Click FB link => Switch new tab
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		// Step6: Verify title of new window = Facebook - ...
		switchToTWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertTrue(driver.findElement(By.name("email")).isDisplayed());

		// Step7: Switch parent window
		switchToTWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();

		switchToTWindowByTitle("LAZADA Vietnam™ - Mua Hàng Trực Tuyến Giá Tốt");
		Assert.assertTrue(driver.findElement(By.id("q")).isDisplayed());

		// Step8: Click TIki lik => Switch new tab
		switchToTWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		// Step9: Verify title of new widow = Mua hang truc tuyen
		switchToTWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Giỏ hàng']")).isDisplayed());

		// Step10: Close all window and tab except parent window
		closeAllWindowExceptParent(parentID);
		// Step11: Verify go back parent window successfully(title/url)
		switchToTWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='GOOGLE']")).isDisplayed());
	}
	// @Test
	public void TC_03_() {
		driver.get("http://live.demoguru99.com/index.php/");
		String parentID = driver.getWindowHandle();
		//step 2: click on tab mobile
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		//step 3: add iphone vao de compare, sau do Verify text
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product IPhone has been added to comparison list.");
		//step 4: Add Samsung vao de compare, sau do Verify text
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

		//Step 5: click compare button
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		//step 6: Switch qua window moi
		switchToTWindowByTitle("Products Comparison List - Magento Commerce");
		//step 7: Verify title cua window moi
		Assert.assertEquals(driver.findElements(By.xpath("//h2[@class='product-name']/a")).size(), 2);
		//step 8: close tap va chuyen ve parent window
		closeAllWindowExceptParent(parentID);
		sleepInSecond(3);
		switchToTWindowByTitle("Mobile");
		//step 9: click clear all link va accept alert
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		explicitWait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
		//step 10: verify message xuat hien
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"The comparison list was cleared.");

	}

	public void switchToWindowByID(String parentID) {
		Set<String> allWindowID = driver.getWindowHandles();
		System.out.println(allWindowID);
		for (String windowID : allWindowID) {
			if (!windowID.equalsIgnoreCase(parentID)) {
				driver.switchTo().window(windowID);
				break;
			}
		}
	}

	public void switchToTWindowByTitle(String expectedWindowTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		System.out.println("The total amount of windows are openning: " + allWindowIDs.size());
		for (String windowID : allWindowIDs) {
			driver.switchTo().window(windowID);
			String windowTitle = driver.getTitle();
			if (windowTitle.equals(expectedWindowTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowExceptParent(String parentID) {
		Set<String> allWindowID = driver.getWindowHandles();

		for (String windowID : allWindowID) {
			if (!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
				driver.close();
			}

		}
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
