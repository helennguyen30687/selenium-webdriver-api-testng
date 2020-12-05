package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {

	WebDriver driver;
	Select select;

	String firstName, lastName, date, month, year, company, email;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		firstName = "Helen";
		lastName = "Nguyen";
		date = "1";
		month = "May";
		year = "1980";
		company = "HTM";
		email = generateEmail();
	}

//	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/");

		// click register link tren Header
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		// Input valid information vao form:
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

		// Assert.assertTrue(select.isMultiple());
		// DOB -> day =1, kiem tra dd co 32 items
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		Assert.assertEquals(select.getOptions().size(), 32);
		// month -> may, kiem tra dd co 13 items
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);
		// Year = 1980, kiem tra dd co 112 items
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);

		// email, company, pwd
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");

		// click register button
		driver.findElement(By.id("register-button")).click();
		// Verify access Home page successfully
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result'] ")).getText(),
				"Your registration completed");

		// click my account and check date/month/year is correct
		driver.findElement(By.className("ico-account")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='gender-male']")).isSelected());
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), company);
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	}

	@Test
	public void TC_02_Mutiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.name("user_job2")));
		select.selectByVisibleText("Manual");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Security");
		select.selectByVisibleText("Functional UI");

		List<WebElement> itemSelected = select.getAllSelectedOptions();
		List<String> itemSelectedText = new ArrayList<String>();

		for (WebElement item : itemSelected) {
			itemSelectedText.add(item.getText());
			System.out.println(item.getText());
		}
		Assert.assertTrue(itemSelectedText.contains("Manual"));
		Assert.assertTrue(itemSelectedText.contains("Mobile"));
		Assert.assertTrue(itemSelectedText.contains("Security"));
		Assert.assertTrue(itemSelectedText.contains("Functional UI"));
	}

//	@Test
	public void TC_03_New_Customer() throws InterruptedException {
//		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
//		driver.findElement(nameBy).sendKeys(name);
//		driver.findElement(dobBy).sendKeys(dobInput);
//		driver.findElement(addressBy).sendKeys(address);
//		driver.findElement(cityBy).sendKeys(city);
//		driver.findElement(stateBy).sendKeys(state);
//		driver.findElement(pinBy).sendKeys(pin);
//		driver.findElement(phoneBy).sendKeys(phone);
//		driver.findElement(emailBy).sendKeys(email);
//		driver.findElement(passwordBy).sendKeys(password);
//
//		driver.findElement(By.name("sub")).click();
//		Thread.sleep(10000);
//
//		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
//				"Customer Registered Successfully!!!");
//		Assert.assertEquals(
//				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
//				dobOutput);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
//				address);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
//				state);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
//				phone);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
//				email);
//
//		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	// @Test
	public void TC_04_Edit_Customer() {
//		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
//		driver.findElement(By.name("cusid")).sendKeys(customerID);
//		driver.findElement(By.name("AccSubmit")).click();
//		// Verify value at Edit Customer
//		Assert.assertEquals(driver.findElement(nameBy).getAttribute("value"), name);
//		Assert.assertEquals(driver.findElement(dobBy).getAttribute("value"), dobOutput);
//		Assert.assertEquals(driver.findElement(addressBy).getText(), address);
//		Assert.assertEquals(driver.findElement(cityBy).getAttribute("value"), city);
//		Assert.assertEquals(driver.findElement(stateBy).getAttribute("value"), state);
//		Assert.assertEquals(driver.findElement(pinBy).getAttribute("value"), pin);
//		Assert.assertEquals(driver.findElement(phoneBy).getAttribute("value"), phone);
//		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
//
//		driver.findElement(addressBy).clear();
//		driver.findElement(addressBy).sendKeys(editAddress);
//		driver.findElement(cityBy).clear();
//		driver.findElement(cityBy).sendKeys(editCity);
//		driver.findElement(stateBy).clear();
//		driver.findElement(stateBy).sendKeys(editState);
//		driver.findElement(pinBy).clear();
//		driver.findElement(pinBy).sendKeys(editPin);
//		driver.findElement(phoneBy).clear();
//		driver.findElement(phoneBy).sendKeys(editPhone);
//		driver.findElement(emailBy).clear();
//		driver.findElement(emailBy).sendKeys(editEmail);
//		driver.findElement(By.name("sub")).click();
//
//		// Verify value after Edit Customer
//		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
//				"Customer details updated Successfully!!!");
//
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(),
//				customerID);
//		Assert.assertEquals(
//				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
//				dobOutput);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
//				editAddress);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
//				editCity);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
//				editState);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),
//				editPin);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
//				editPhone);
//		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
//				editEmail);
	}

	public String generateEmail() {
		Random rand = new Random();
		return "donald" + rand.nextInt(9999) + "@gmail.com";

	}

	@AfterClass
	public void afterClass() {

		// driver.quit();
	}
}
