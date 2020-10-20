package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {

	WebDriver driver;
	String email, userID, password, loginPageUrl;
	String name, address, dobInput, dobOutput, city, state, pin, phone, customerID;
	String editAddress, editCity, editState, editPin, editPhone,editEmail;

	By nameBy = By.name("name");
	By dobBy = By.name("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinBy = By.name("pinno");
	By phoneBy = By.name("telephoneno");
	By emailBy = By.name("emailid");
	By passwordBy = By.name("password");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");

		loginPageUrl = driver.getCurrentUrl();
		name = "Donald Trump";
		address = "1 Meadow Ridge Point";
		dobInput = "01/12/1958";
		dobOutput = "1958-01-12";
		city = "Mesa";
		state = "Arizona";
		pin = "854210";
		phone = "6024367543";
		email = generateEmail();

		editAddress = "13 Scoville Parkway";
		editCity = "Houston";
		editState = "Texas";
		editPin = "77223";
		editPhone = "7132306687";
		editEmail = "ebrimley0@shareasale.com";
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		System.out.println("UserID = " + userID);
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println("Password = " + password);
	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_03_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameBy).sendKeys(name);
		driver.findElement(dobBy).sendKeys(dobInput);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(phoneBy).sendKeys(phone);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);

		driver.findElement(By.name("sub")).click();
		Thread.sleep(10000);

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
				"Customer Registered Successfully!!!");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dobOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		// Verify value at Edit Customer
		Assert.assertEquals(driver.findElement(nameBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobBy).getAttribute("value"), dobOutput);
		Assert.assertEquals(driver.findElement(addressBy).getText(), address);
		Assert.assertEquals(driver.findElement(cityBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinBy).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneBy).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);

		driver.findElement(addressBy).clear();
		driver.findElement(addressBy).sendKeys(editAddress);
		driver.findElement(cityBy).clear();
		driver.findElement(cityBy).sendKeys(editCity);
		driver.findElement(stateBy).clear();
		driver.findElement(stateBy).sendKeys(editState);
		driver.findElement(pinBy).clear();
		driver.findElement(pinBy).sendKeys(editPin);
		driver.findElement(phoneBy).clear();
		driver.findElement(phoneBy).sendKeys(editPhone);
		driver.findElement(emailBy).clear();
		driver.findElement(emailBy).sendKeys(editEmail);
		driver.findElement(By.name("sub")).click();
		
		// Verify value after Edit Customer
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer details updated Successfully!!!");
		
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dobOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);
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
