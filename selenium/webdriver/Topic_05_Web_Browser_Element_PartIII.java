package webdriver;

import org.testng.annotations.Test;

import com.sun.org.apache.xpath.internal.operations.And;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_05_Web_Browser_Element_PartIII {
  WebDriver driver;
  By emailTexboxBy = By.id("email");
  By educationTextAreaBy = By.id("edu");
  By ageUnder18Radio = By.id("under_18");
  By jobRole1Dropdown = By.id("job1");
  By jobRole2Dropdown = By.id("job2");
  By javaLanguageCheckboxBy = By.id("java");
  
  
  By passwordTextboxBy = By.id("password");
  By disableRadioBy = By.id("radio-disabled");
  By jobRole3Dropdown = By.id("job3");
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
  }
  @Test
  public void TC01_Check_IsDisplayed_Element() throws InterruptedException {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  WebElement emailTextbox = driver.findElement(emailTexboxBy);
	  WebElement ageRadioButton = driver.findElement(ageUnder18Radio);
	  WebElement educationTextArea = driver.findElement(educationTextAreaBy);
	  //email
	  if (emailTextbox.isDisplayed())
	  {
		  emailTextbox.sendKeys("Automation Testing");
		  System.out.println("Element is displayed"); 
	  }
	  else 
	  {
		  System.out.println("Element is not displayed");
	  }
	  Thread.sleep(2000);
	  //age
	  if (ageRadioButton.isDisplayed() == true )
	  {
		  ageRadioButton.click();
		  System.out.println("Element is displayed"); 
	  }
	  else 
	  {
		  System.out.println("Element is not displayed");
	  }
	  Thread.sleep(2000);
	  //education
	  if (educationTextArea.isDisplayed() == true )
	  {
		  educationTextArea.sendKeys("Automation Testing");
		  System.out.println("Element is displayed"); 
	  }
	  else 
	  {
		  System.out.println("Element is not displayed");
	  }

  }
  @Test
  public void TC02_Element_Displayed() {
	driver.navigate().refresh();
	if(isElementDisplayed(emailTexboxBy)) {
		driver.findElement(emailTexboxBy).sendKeys("Automation Testing");;
	}
	if(isElementDisplayed(ageUnder18Radio)) {
		driver.findElement(ageUnder18Radio).click();
	}
	if(isElementDisplayed(educationTextAreaBy)) {
		driver.findElement(educationTextAreaBy).sendKeys("Automation Testing");;
	}
  }
  @Test
  public void TC03_isEnabled_Element() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.navigate().refresh();
	  
	  Assert.assertTrue(isElementEnabled(emailTexboxBy));
	  Assert.assertTrue(isElementEnabled(educationTextAreaBy));
	  Assert.assertTrue(isElementEnabled(ageUnder18Radio));
	  Assert.assertTrue(isElementEnabled(jobRole1Dropdown));
	  Assert.assertTrue(isElementEnabled(jobRole2Dropdown));
	  
	  Assert.assertFalse(isElementEnabled(passwordTextboxBy));
	  Assert.assertFalse(isElementEnabled(disableRadioBy));
	  Assert.assertFalse(isElementEnabled(jobRole3Dropdown));
  }
  
  @Test
  public void TC04_isSelected_Element() throws InterruptedException {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.navigate().refresh();
  
	  Assert.assertFalse(isElementSelected(ageUnder18Radio));
	  Assert.assertFalse(isElementSelected(javaLanguageCheckboxBy));
	  
	  driver.findElement(ageUnder18Radio).click();
	  driver.findElement(javaLanguageCheckboxBy).click();
	  Thread.sleep(3000);
	  
	  Assert.assertTrue(isElementSelected(ageUnder18Radio));
	  Assert.assertTrue(isElementSelected(javaLanguageCheckboxBy));
	  
	  driver.findElement(javaLanguageCheckboxBy).click();
	  Assert.assertFalse(isElementSelected(javaLanguageCheckboxBy));
	  
  }
	  
  @Test
  public void TC05_Validate_Register_MailChimp() throws InterruptedException {
		  driver.get("https://login.mailchimp.com/signup/");
		  driver.findElement(emailTexboxBy).sendKeys("helen@gmail.com");
		  driver.findElement(emailTexboxBy).sendKeys("new_username");
		  
		  //Verify Sign up button is enabled or not
		  Assert.assertFalse(isElementEnabled(By.id("create-account")));
		  driver.findElement(By.id("new_password")).sendKeys("auto");
		  Thread.sleep(2000);
		  
		//lower case
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed']"))); 
		  
		  //special char
		  driver.findElement(By.id("new_password")).clear();
		  driver.findElement(By.id("new_password")).sendKeys("auto@");
		  Thread.sleep(2000);
		  Assert.assertFalse(isElementEnabled(By.id("create-account")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed']")));
		  
		  //upper case
		  driver.findElement(By.id("new_password")).clear();
		  driver.findElement(By.id("new_password")).sendKeys("Auto@");
		  Thread.sleep(2000);
		  Assert.assertFalse(isElementEnabled(By.id("create-account")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed']")));
		  
		  //8 char 
		  driver.findElement(By.id("new_password")).clear();
		  driver.findElement(By.id("new_password")).sendKeys("Automation@");
		  Thread.sleep(2000);
		  Assert.assertFalse(isElementEnabled(By.id("create-account")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='special-char completed']")));
		  
		//one number 
		  driver.findElement(By.id("new_password")).clear();
		  driver.findElement(By.id("new_password")).sendKeys("Automation1");
		  Thread.sleep(2000);
		  Assert.assertFalse(isElementEnabled(By.id("create-account")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='8-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed']")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		  
		 //Full password
		  driver.findElement(By.id("new_password")).clear();
		  driver.findElement(By.id("new_password")).sendKeys("Automation@1");
		  Thread.sleep(2000);
		  Assert.assertTrue(isElementEnabled(By.id("create-account")));
		  Assert.assertTrue(isElementDisplayed(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]")));
		  
		  Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='8-char completed']")));
		  Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='uppercase-char completed']")));
		  Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='lowercase-char completed']")));
		  Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='number-char completed' and text()='One number']")));
		  Assert.assertFalse(isElementDisplayed(By.xpath("//li[@class='special-char completed']")));
	  }
  public boolean isElementDisplayed(By by) 
  {
	  if(driver.findElement(by).isDisplayed()) {
		  System.out.println("Element is displayed");
	  return true;
  }
  else {
	  System.out.println("Element isn't displayed");
		  return false;
	  }
  }
  //enable
  public boolean isElementEnabled(By by) {
	  if(driver.findElement(by).isEnabled()) 
	  {
		  System.out.println("Element is enable");
		  return true;
	  }
  else {
	  System.out.println("Element is disable");
		  return false;
	  }
  }
  //selected
  public boolean isElementSelected(By by) {
	  if(driver.findElement(by).isSelected()) 
	  {
		  System.out.println("Element is selected");
		  return true;
	  }
  else {
	  System.out.println("Element is de-selected");
		  return false;
	  }
  }
  
  
  @AfterClass
  public void afterClass() {
  }

}
