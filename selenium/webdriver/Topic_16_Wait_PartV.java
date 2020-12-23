package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;


public class Topic_16_Wait_PartV {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	WebElement element;
	long timeoutInSecond = 15;
	long intervalInMilisecond = 500;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countdownTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		fluentElement = new FluentWait<WebElement>(countdownTime);
		fluentElement.withTimeout(15, TimeUnit.SECONDS).pollingEvery(300, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);

		fluentElement.until(new Function<WebElement, Boolean>() {
			@Override
			public Boolean apply(WebElement countdownTime) {
				String text = countdownTime.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
	}

	@Test
	public void TC_02() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
		waitForElementAndClick(By.xpath("//div[@id='start']/button"));
		Assert.assertTrue(isElementDisplay(By.xpath("//div[@id='finish']/h4")));
	}

	public WebElement getElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeoutInSecond, TimeUnit.SECONDS)
				.pollingEvery(intervalInMilisecond, TimeUnit. MILLISECONDS).ignoring(NoSuchElementException.class);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}

	public void waitForElementAndClick(By locator) {
		WebElement element = getElement(locator);
		element.click();
	}

	public boolean isElementDisplay(By locator) {
		WebElement element = getElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element).withTimeout(timeoutInSecond, TimeUnit.SECONDS)
				.pollingEvery(intervalInMilisecond, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);


		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return isDisplayed;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
