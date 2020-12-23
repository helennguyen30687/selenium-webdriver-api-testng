package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;

public class Topic_16_Wait_PartI {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Displayed_Visible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// Wait for element is visible - Wait for the email textbox displayed in DOM and
		// UI during 10s.
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

	}

	//
	public void TC_02_Undisplayed_Invisible_In_DOM() {
		// Wait for element is NOT visible - Wait for the element not displayed in UI
		// and user can't implements
		// TH1: element is still in DOM
		explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
	}
	
	//@Test
	public void TC_03_Undisplayed_Invisible_Out_DOM() {
		// Wait for element is NOT visible - Wait for the element not displayed in UI
		// and user can't implements
		// TH2: element isNOT still in DOM
		// Tìm element đầu tiên: ko tìm thấy element -> tìm đi tìm lại cho tới khi hết
		// timeout của implicitWait =10s . Sau đó mới apply đk của invisibility
		explicitWait
		.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='edit_account_error']")));
		
	}

//	 @Test
	public void TC_04_Presence() {
		// Wait for element is in DOM
		// TH1: Element co trong DOM va hien thi tren UI
		// TH2: Element co trong DOM va ko hien thi tren UI
		 explicitWait
		 .until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		explicitWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));

	}
//	 @Test
		public void TC_05_Clickable_Element_Enabled() {
			driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		 explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("SubmitLogin"))));
		 
		 driver.get("https://login.mailchimp.com/signup/");
		 driver.findElement(By.id("email")).sendKeys("olala114@gmail.com");
		 driver.findElement(By.id("new_username")).sendKeys("olala114");
		 driver.findElement(By.id("new_password")).sendKeys("Abc@123456");
		 explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("create-account")));
		 
	 }
		@Test
		public void TC_06_Staleness() {
			//Wait for 1 element staleness:
			//khong co/con o trong DOM
			//Step1: Thao tac voi 1 elemt => error msg xuat hien - hien thi
			//step2: Thao tac voi element => error msg ko con xuat hien 
			//Step3: cho`cho error message ko con xuat hien trong DOM nua
		}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
