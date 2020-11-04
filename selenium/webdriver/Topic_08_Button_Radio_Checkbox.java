package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	By loginButton = By.cssSelector(".fhs-btn-login");
	By loginUsername = By.cssSelector(" #login_username[name='youama-email']");
	By loginPassword = By.cssSelector("#login_password");
	By summerRadio = By.xpath("//input[@value='Summer']");
	By checkedCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input");
	By IndeterminateCheckbox = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::div/input");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		Assert.assertFalse(isElementEnabled(loginButton));

		driver.findElement(loginUsername).sendKeys("huong@mail.com");
		driver.findElement(loginPassword).sendKeys("123456");

		sleepInSecond(3);
		Assert.assertTrue(isElementEnabled(loginButton));

		driver.navigate().refresh();

		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();

		removeAttributeDisabledByJS(loginButton);
		sleepInSecond(2);
		Assert.assertTrue(isElementEnabled(loginButton));

		driver.findElement(loginButton).click();
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

	}

	
	public void TC_02_Checkbox_SelectAll() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkbox : checkboxes) {
			checkToCheckboxOrRadio(checkbox);
			sleepInSecond(1);
		}
		// verify select all
		for (WebElement checkbox : checkboxes) {
			Assert.assertTrue(isElementSelected(checkbox));
		}
		sleepInSecond(5);
		// Deselect all
		for (WebElement checkbox : checkboxes) {
			uncheckToCheckboxOrRadio(checkbox);
			sleepInSecond(1);
		}
		// verify deselect all
		for (WebElement checkbox : checkboxes) {
			Assert.assertFalse(isElementSelected(checkbox));
		}
	}

	
	public void TC_03_Checkbox_Radio_Default() {
		//checkbox
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		//driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		sleepInSecond(2);
		Assert.assertTrue(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
		
		uncheckToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		Assert.assertFalse(isElementSelected(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"))));
		
		//Radio
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		checkToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		
		uncheckToCheckboxOrRadio(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
		Assert.assertTrue(isElementSelected(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")));
	}
	
	@Test
	public void TC_04_Checkbox_Radio_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(2);
		clickElementByJS(driver.findElement(summerRadio));
		sleepInSecond(3 );
		Assert.assertTrue(isElementSelected(summerRadio));
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		clickElementByJS(driver.findElement(checkedCheckbox));
		clickElementByJS(driver.findElement(IndeterminateCheckbox));
		sleepInSecond(2);
		
		Assert.assertTrue(isElementSelected(checkedCheckbox));
		Assert.assertTrue(isElementSelected(IndeterminateCheckbox));
		
		clickElementByJS(driver.findElement(checkedCheckbox));
		clickElementByJS(driver.findElement(IndeterminateCheckbox));
		sleepInSecond(2);
		
		Assert.assertFalse(isElementSelected(checkedCheckbox));
		Assert.assertFalse(isElementSelected(IndeterminateCheckbox));
		}


	public void removeAttributeDisabledByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
	}

	public void clickElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click()", element);
	}
	
	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is de-selected");
			return false;
		}
	}

	public boolean isElementSelected(WebElement element) {
		if (element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is de-selected");
			return false;
		}
	}

	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		} else {
			System.out.println("Element is disabled");
			return false;
		}
	}

	public void checkToCheckboxOrRadio(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckboxOrRadio(WebElement element) {
		if (element.isSelected()) {
			element.click();
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
		// .quit();
	}
}
