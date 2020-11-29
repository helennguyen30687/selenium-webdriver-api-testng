package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Javascript_Executor_PartI {
	WebDriver driver;
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Browser_Element() {
		// use JE ->access http://live.demoguru99.com/
		navigateToUrlByJS("http://live.demoguru99.com/");
		// use JE -> get domain of page
		String liveGuruDomai = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveGuruDomai, "live.demoguru99.com");
		// use JE -> get URL
		String homePageUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homePageUrl, "http://live.demoguru99.com/");
		// Open Mobile page use JE
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		// Add Samsung to cart use JE
		highlightElement("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']/button");
		// verify message display by using JE -> get inner text
//		String innerText= getInnerText();
//		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		// Open Customer Service page -> JE
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		// Verify title of page customer service
		String customerServiceTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		// Scroll to element Newsletter textbox at bottom -> JE
		scrollToElement("//input[@id='newsletter']");
		highlightElement("//input[@id='newsletter']");
		sleepInSecond(3);
		// Input valid email into Newletter textbox -> JE
		highlightElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		sleepInSecond(3);
		// Click Subcrube button -> JE
		highlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		// Verify text displayed -> JE
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		// Navigate to domain:http://demo.guru99.com/v4/ ->JE
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		// Verify domain after navigating : demo.guru99.com
		Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
	}

	//@Test
	public void TC_02_Validation_Message() {
		//Access page "https://automationfc.github.io/html5/index.html"
		driver.get("https://automationfc.github.io/html5/index.html");
		//Click Submit and verify message displayed at Name Textbox
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
		
		//Input data into Field Name and click Submit and then verify message displayed at Password textbox
		driver.findElement(By.xpath("//input[@id='fname']")).clear();
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Helen Nguyen");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");

		//Input data into Password field, click submit and then verify message displayed at Email textbox 
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");
		
		//Iput invalid data into Email field, then click submit and verify message displayed at Email textbox
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("132@435@4545");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");
		

		//Input valid data into Email field, then click Submit and verify message displayed at address field
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("helennguyen@mail.com");
		driver.findElement(By.name("submit-btn")).click();
		sleepInSecond(2);
		Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");
	}

	
	@Test
	public void TC_03_Hidden_Element() {
		//Access page "http://live.demoguru99.com/"
		driver.get("http://live.demoguru99.com/");
		//Click into My account at header to go to Sign in page (JE), Click on My account not click on account link
		clickToElementByJS("//div[@id ='header-account']//a[@title='My Account']");
		sleepInSecond(3);
		
		//click Create an account button to go to sign up (JE)
		
		//Input valid data in to all field: First name, last name, email address, pwd, confirm pwd (JE)
		
		//Click register button (JE)
		
		//verify message displayed when register successfully : Thank...
		
		// logout (JE) 
		
		//Check navifate to home page after logging in successfully (isDisplayed to check wait)
	}
	public Object executeForBrowser(String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean areJQueryAndJSLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	public WebElement getElement(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String generateEmail() {
		Random rand = new Random();
		return "helennguyen" + rand.nextInt(9999) + "@hotmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
