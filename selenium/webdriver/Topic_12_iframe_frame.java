package webdriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_iframe_frame {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//	@Test
	public void TC_01_Iframe() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		//Verify FB displayed
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Facebook Social Plugin')]")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(), "Automation FC");
		//Verify amount of like
		String likeText= driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
		int likeNumber =  Integer.parseInt(likeText.split(" ")[0].replace(",", ""));
		assertThat(likeNumber, greaterThan(1900));
		//Verify web doc iframe displayed
		  driver.switchTo().defaultContent();
		  Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='post-title']")).getText(), "[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
		  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		  Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle ")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
		  //sendkey -enter
		//Sendkey -excel - click search
		
		
	
	}

	@Test
	public void TC_02_Iframe() {
		driver.get("https://kyna.vn/");
		sleepInSecond(10);
		//verify iframe facebook displayed
		//verify amount of likes
		//verify web chat iframe displayed
		driver.switchTo().frame("cs_chat_iframe");
		//sendkey - enter - verify form displayed
		driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
		driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("Ngoc"); 
		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("0988888888"); 
		Select select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
		select.selectByValue("60021729");
		driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys("abc"); 
		driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
		sleepInSecond(4);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Ngoc']")).isDisplayed());
		driver.switchTo().defaultContent();
		//sendky with excel - click on search button
		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("excel");
		driver.findElement(By.cssSelector(".search-button")).click();
		List<WebElement> courseNameHeader = driver.findElements(By.cssSelector("div.content h4"));
		//verify transfer to page with keyword is excel 
		List<String> courseNameText = new ArrayList<String>();
		
		for (WebElement courseElement : courseNameHeader) {
			System.out.println(courseElement.getText());
			courseNameText.add(courseElement.getText());
			
		}
		for (String courseName:courseNameText)
		{
			Assert.assertTrue(courseName.contains("Excel"));
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
