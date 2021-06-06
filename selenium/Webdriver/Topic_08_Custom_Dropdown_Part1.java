package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_Part1 {
	WebDriver driver;
	//Thư viên dùng để wait
	WebDriverWait explicitWait;
	//thư viên dùng để inject 1 đoạn js code
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		//khởi tạo
		driver = new FirefoxDriver();
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

	@Test
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

	@Test
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

	@Test
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