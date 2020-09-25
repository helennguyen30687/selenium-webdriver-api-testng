package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_I_Locator {
	WebDriver driver;

@Test
	public void TC_01(){
		//open firefox
		driver = new FirefoxDriver();
		//input url
		driver.get("https://facebook.com/");
		//input email
		driver.findElement(By.id("email")).sendKeys("helennguyen30687@gmail.com");
		//classname
		driver.findElement(By.className("_featuredLogin__formContainer")).isDisplayed();
		//name
		driver.findElement(By.name("pass")).sendKeys("1234560");
		//tagname
		int linknumber =driver.findElements(By.tagName("a")).size();
		System.out.println("Link number  =" + linknumber);
		//link text
		driver.findElement(By.linkText("English (UK)")).click();
		//partial linktext
		driver.findElement(By.partialLinkText("Việt")).click();
		
		//Css
		driver.findElement(By.cssSelector("#email")).sendKeys("helennguyen30687@gmail.com");
		driver.findElement(By.cssSelector("input[name='email']")).clear();
		//xpath
		driver.findElement(By.cssSelector("//input[@id='email']")).sendKeys("123456");
		//xpath text
		driver.findElement(By.cssSelector("//a[text()=\"English (UK)\"]")).click();
		
		
	}
	public void TC_02(){
		
	}
}
