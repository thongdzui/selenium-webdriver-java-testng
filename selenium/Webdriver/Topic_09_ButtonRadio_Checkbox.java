package Webdriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Topic_09_ButtonRadio_Checkbox {
	WebDriver driver;
	boolean status;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		//Verify button is disabled
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Status is " + status);
		Assert.assertFalse(status);
		
		
		driver.findElement(By.cssSelector("#login_username")).sendKeys("0987389097");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");
		
		//click button Đăng nhập
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		
		//Verify button is enabled
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Status is " + status);
		Assert.assertTrue(status);
		
		//refresh page
		driver.navigate().refresh();
		
		//navigate to Đăng nhập tab
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
	
		
		//TRICK
		//remove attribute disabled of button Đăng nhập
	
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInSecond(5);
		
		//Verify button is enabled
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Status is " + status);
		Assert.assertTrue(status);
		
		//click button Đăng nhập
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		
	}

	//@Test
	public void TC_02_Radio_Checkbox_Default() {
		
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		//click checkbox
		//driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).click();
		
		//verify checkbox is selected
		//Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).isSelected());
		
		//click checkbox 
		//driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).click();
		
		//Verify checkbox is deselected
		//Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).isSelected());
		//
		//--------------------------------------------------------------------------------------------------------------------
		//--------------------------------------Cách dùng Checkbox hợp lí nhất -----------------------------------------
		
		//click vào checkbox
		checkToCheckBoxOrRadioButton(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"));
		sleepInSecond(1);
		
		//verify checkbox is selected
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).isSelected());
		
		//click để bỏ chọn
		checkToCheckBoxOrRadioButton(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input"));
		sleepInSecond(1);
		
		//Verify checkbox is deselected
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input")).isSelected());
		
		
		//Default radio button
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		//Click vô radio button
		By rearSideRadioButton = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
		checkToCheckBoxOrRadioButton(rearSideRadioButton);
		
		//verify checkbox is selected
		Assert.assertTrue(driver.findElement(rearSideRadioButton).isSelected());
		
		//Click to radio button để bỏ chọn
		
	}
	//@Test
	public void TC_03_Checkbox_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//Select all checkbox
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement singlecheckbox : checkboxes) {
			if (!singlecheckbox.isSelected()) {
				singlecheckbox.click();
				sleepInSecond(1);
			}
		}
		//Verify những signle check box đã dc selected
		for (WebElement singlecheckbox : checkboxes) {
			Assert.assertTrue(singlecheckbox.isSelected());
		}
		
		
	}
	//@Test
	public void TC_04_Radio_Custom() {
		driver.get("https://material.angular.io/components/radio/examples");
		//driver.findElement(By.xpath("//input[@value='Winter']")).click();
		//sleepInSecond(2);
		//1Thẻ input bị ẩn + có thể verify dc
		//By radioWinter = By.xpath("//input[@value='Winter']");
		//checkToCheckBoxOrRadioButton(radioWinter);
		//sleepInSecond(2);
		
		//Assert.assertTrue(driver.findElement(radioWinter).isSelected());
		
		
		//2 Thẻ span để click + ko verify dc
		//driver.findElement(By.xpath("//span[contains(string(),'Winter')]")).click();
		//sleepInSecond(2);
		//Assert.assertTrue(driver.findElement(By.xpath("//span[contains(string(),'Winter')]")).isSelected());;
		
		
		//3Thẻ Span để click + thẻ input để verify
		//dùng thẻ span để click
		//By winterSpan = By.xpath("//span[contains(string(),'Winter')]");
		//checkToCheckBoxOrRadioButton(winterSpan);
		//sleepInSecond(2);
		
		//dùng the input de verify
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Winter']")).isSelected());
		
		//define 2 locator cho 1 element
		//maintain => bảo trì nhiều chỗ
		
		//4dùng hàm JS để click vô thẻ bị ẩn + verify như cách 1
		By radioWinter = By.xpath("//input[@value='Winter']");
		clicktoElementByJS(radioWinter);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(radioWinter).isSelected());
		
	}
	
	@Test
	public void TC_05_Radio_Checkbox_Custom() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		//verify checkbox is deselected
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']")).isDisplayed());
		//click
		driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']//div[contains(@class,'exportInnerBox')]")).click();
		sleepInSecond(5);
		
		String quangnamCheckboxChecked = driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']")).getAttribute("aria-checked");
		System.out.println("In ra gì :" + quangnamCheckboxChecked);
		
		//verify checkbox is selected
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']")).isDisplayed());
		
		//Single Element
		//WebElement dangkiButton = driver.findElement(By.xpath(""));
		//Tương tác vs Element đầu tiên trong 3 node => Signle
		//WebElement dangkiButton = driver.findElement(By.xpath(""));
		
		//Multi element
		//List<WebElement> links = driver.findElements(By.xpath(""));
		//Tett
		
	}
	public void checkToCheckBoxOrRadioButton(By by) {
		WebElement checkbox = driver.findElement(by);
		if(!checkbox.isSelected()) {
			checkbox.click();
		}
		
	}
	
	public void uncheckToCheckBox(By by) {
		WebElement checkbox = driver.findElement(by);
		if(checkbox.isSelected()) {
			checkbox.click();
		}
	}
	
	public void clicktoElementByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}


	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}