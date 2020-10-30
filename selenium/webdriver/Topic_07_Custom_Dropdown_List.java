package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.swing.internal.plaf.metal.resources.metal;

public class Topic_07_Custom_Dropdown_List {

	WebDriver driver;
	WebDriverWait explicitWait;
	Select select;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		// 5
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(5);
		// 10
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSecond(5);
		// 3
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
		sleepInSecond(5);
		// 8
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "8");
		sleepInSecond(5);
	}

	// @Test
	public void TC_02_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']//li", "Hockey");
		sleepInSecond(3);
		Assert.assertEquals(
				getAngularSelectedValueByJS("return document.querySelector(\"select[name='games'] option\").text"),
				"Hockey");

		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']//li", "Basketball");
		sleepInSecond(3);

		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]",
				"//ul[@id='games_options']//li", "Tennis");
		sleepInSecond(3);

	}

	// @Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdown("//div[@role='listbox']//i", "//div[@role='listbox']//span", "Matt");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Matt']")).getText(), "Matt");

		selectItemInCustomDropdown("//div[@role='listbox']//i", "//div[@role='listbox']//span", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Stevie Feliciano']")).getText(),
				"Stevie Feliciano");

	}

	// @Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");

		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a",
				"Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

	}

//	@Test
	public void TC_05_Editable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		selectItemInEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']",
				"Australia");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Australia']")).getText(), "Australia");

		selectItemInEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Belgium");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Australia']")).getText(), "Belgium");
	}

	@Test
	public void TC_06_Multiple_Custom_Dropdown() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

		selectItemInEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']",
				"Australia");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Australia']")).getText(), "Australia");

		selectItemInEditDropdown("//input[@class='search']", "//div[@role='combobox']//span[@class='text']", "Belgium");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Australia']")).getText(), "Belgium");
	}

	String[] month = { "January", "April", "July" };

	// Common
	public void selectMutipleItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {

		driver.findElement(By.xpath(parentXpath)).click(); // 1 - Input vào 1 element (textbox) của dropdown để cho nó
															// xổ
															// hết tất cả các site ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath))); // 2 - Chờ cho
																										// all các item
																										// được load lên
		List<WebElement> allItems = driver.findElements(By.xpath(childXpath)); // 3 - Lưu nó lại vào 1 List chứa những
																				// element
		for (WebElement childElement : allItems) { // 4 - Duyệt qua từng element và Lấy ra text của từng element
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) { // 5 - Kiểm tra nó có bằng với cái text cần tìm hay ko
					childElement.click();
					sleepInSecond(5);
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					if(itemSelected.size()==expectedValueItem.length)
						break;
				}
			}
		}
	}

	public void selectItemInEditDropdown(String editableXpath, String allItemXpath, String expectedValueItem) {

		driver.findElement(By.xpath(editableXpath)).sendKeys(expectedValueItem); // 1 - Input vào 1 element (textbox)
																					// của dropdown để cho nó xổ
		// hết tất cả các site ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath))); // 2 - Chờ cho
																										// all các item
																										// được load lên
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath)); // 3 - Lưu nó lại vào 1 List chứa những
																					// element
		int allItemsNumber = allItems.size(); // 19 gia tri
		for (int i = 0; i < allItemsNumber; i++) { // 4 - Duyệt qua từng element và Lấy ra text của từng element
			String actualValueItem = allItems.get(i).getText();

			if (actualValueItem.equals(expectedValueItem)) { // 5 - Kiểm tra nó có bằng với cái text cần tìm hay ko

				allItems.get(i).click(); // 6 nếu như có thì click vào - thoát khỏi vòng lặp
				break;
			}
		}
	}

	public String getAngularSelectedValueByJS(String cssSelector) {
		return (String) jsExecutor.executeScript(cssSelector);
	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {

		driver.findElement(By.xpath(parentXpath)).click(); // 1 - Click vào 1 element bất kì của dropdown để cho nó xổ
															// hết tất cả các site ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath))); // 2 - Chờ cho
																										// all các item
																										// được load lên
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath)); // 3 - Lưu nó lại vào 1 List chứa những
																					// element
		int allItemsNumber = allItems.size(); // 19 gia tri
		for (int i = 0; i < allItemsNumber; i++) { // 4 - Duyệt qua từng element và Lấy ra text của từng element
			String actualValueItem = allItems.get(i).getText();

			if (actualValueItem.equals(expectedValueItem)) { // 5 - Kiểm tra nó có bằng với cái text cần tìm hay ko

				allItems.get(i).click(); // 6 nếu như có thì click vào - thoát khỏi vòng lặp
				break;
			}
		}
//			//Nếu không có thì tiếp tục duyệt những item khác cho đến khi hết all item
//		for (WebElement item : allItems) {
//			if (item.getText().equals(expectedValueItem) ) {
//				//6 nếu như có thì click vào - thoát khỏi vòng lặp
//				item.click();
//				break;
//			}
//		}

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

		// driver.quit();
	}
}
