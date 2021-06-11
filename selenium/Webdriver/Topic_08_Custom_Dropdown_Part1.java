package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_Part1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	//Thư viên dùng để wait
	WebDriverWait explicitWait;
	//thư viên dùng để inject 1 đoạn js code
	JavascriptExecutor jsExecutor;
	
	String[] firstMonth = { "February" , "May" , "June" };
	String[] secondMonth = { "February" , "May" , "June" , "August", "December" };
	
	@BeforeClass
	public void beforeClass() {
		//khởi tạo
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		//khởi tạo wait
		explicitWait = new WebDriverWait(driver, 15);
		//khởi tão JavascriptExecutor
		//jsExecutor = new JavascriptExecutor(); =>sai
		//ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		
		
		//ép kiểu ngầm định: từ kiểu dữ liệu range nhỏ lên range lớn hơn
		int price = 15600;
		float size = price;
		
		//ép kiểu tường minh: từ lớn => nhỏ (short nhỏ hơn int)
		short sPrice = (short) price;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//span[@id=\"number-button\"]","//ul[@id='number-menu']//div","5");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		
		
		selectItemInCustomDropdown("//span[@id=\"number-button\"]","//ul[@id='number-menu']//div","15");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		
		selectItemInCustomDropdown("//span[@id=\"number-button\"]","//ul[@id='number-menu']//div","10");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")).isDisplayed());
		
	}

	//@Test
	public void TC_02_React() {
		
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//i[@class='dropdown icon']","//div[@role='option']/span", "Christian");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']","//div[@role='option']/span", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Stevie Feliciano']")).isDisplayed());
		
		
		selectItemInCustomDropdown("//i[@class='dropdown icon']","//div[@role='option']/span", "Elliot Fu");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Elliot Fu']")).isDisplayed());
		
		
	
	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		selectItemInCustomDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Third Option");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
		
	}
	//@Test
	public void TC_04_Angular() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//span[@aria-owns='games_options']","//li[@class='e-list-items']","Football");
	
	}
	
	//@Test
	public void TC_05_Valor() {
		driver.get("https://valor-software.com/ng2-select/");
		selectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']","//a[@class='dropdown-item']/div","Barcelona");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-match-text')]")).getText(), "Barcelona");
	
		selectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']","//a[@class='dropdown-item']/div","Berlin");
		sleepInSecond(3);
		
		
	}
	
	//@Test
	public void TC_05_Valor_Editable() {
		driver.get("https://valor-software.com/ng2-select/");
		enterAndSelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']","//tab[@heading='Single']//input","//a[@class='dropdown-item']/div","Berlin");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//div[@class='ui-select-choices-row active']//div")).click();
		enterAndSelectItemInCustomDropdown("//tab[@heading='Single']//i[@class='caret pull-right']","//tab[@heading='Single']//input","//a[@class='dropdown-item']/div","Rotterdam");
		sleepInSecond(3);
		
	}
	
	//@Test
	public void TC_05_Valor_Editable02() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterAndTabToCustomDropDown("//input[@class='search']","Algeria");
		sleepInSecond(1);
		
		enterAndTabToCustomDropDown("//input[@class='search']","Belgium");
	}
	@Test
	public void TC_06_Multiple() {
		
		
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]","(//div[@class='form-group row'])[2]//div[@class='ms-drop bottom']//span", firstMonth);
		sleepInSecond(5);
		
		Assert.assertTrue(areItemSelected(firstMonth));
		
		driver.navigate().refresh();
		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]","(//div[@class='form-group row'])[2]//div[@class='ms-drop bottom']//span", secondMonth);
		sleepInSecond(5);
	}
	public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
		// 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();

		// 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

		// Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {
			// "January", "April", "July"
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					// 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);

					// 4: click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}
	
	public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
	}
	
	
	public void enterAndTabToCustomDropDown(String textBoxXpath, String expectedItems) {
		driver.findElement(By.xpath(textBoxXpath)).sendKeys(expectedItems);
		sleepInSecond(1);
		
		driver.findElement(By.xpath(textBoxXpath)).sendKeys(Keys.TAB);
		sleepInSecond(1);
		
		
	}
	public void selectItemInCustomDropdown(String parentXpath, String childXpath, String expectedItems) {
		//Click vào 1 element để nó xổ hết tất cả các items trong list ra =>Parent Element
		driver.findElement(By.xpath(parentXpath)).click();
		//Chờ tất cả các element dc load ra thành công => Child Element
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Tìm items cần 
		
		//List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		//duyệt qua từng items
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItems)) {
				if(!item.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView()", item);
					sleepInSecond(2);
				}
				item.click();
				break;//thoát khỏi vòng lặp sau khi click 
			}
			//sau khi duyệt qua gettext 
			//kiểm tra có bằng items mình muốn
			
			//+Items cần chọn hiển thị -> Click
			//+Items cần chọn ko hiển thị =>Scroll đến items -> Click
		}
		
		
		
	}
	public void enterAndSelectItemInCustomDropdown(String parentXpath, String textBoxXpath, String childXpath, String expectedItems) {
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath(textBoxXpath)).sendKeys(expectedItems);
		sleepInSecond(1);
		
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItems)) {
				if(!item.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView()", item);
					sleepInSecond(2);
					item.click();
					break;
				}
				
			}
		}
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//wait cứng, cố định time
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}