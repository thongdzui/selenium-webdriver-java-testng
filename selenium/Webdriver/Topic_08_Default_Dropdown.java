package Webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Topic_08_Default_Dropdown {
	WebDriver driver;
	Select select;
	String firstName, lastName, emailAddress, companyName, day, month, year;
	JavascriptExecutor jsExecutor;
	List<String> expectedItemText;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//khởi tạo data cho form đk
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "SuperVip" + generateEmail() ;
		companyName = "Google";
		day = "10";
		month = "August";
		year = "1990";
		expectedItemText = new ArrayList<String>(Arrays.asList("Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
		
	}

	//@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-register")).click();
		sleepInSecond(2);
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//khởi tạo 1 biến select lk với biến dropdown
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		
		
		clickByJS(By.id("register-button"));
		//sleepInSecond(2);
		//driver.findElement(By.id("register-button")).click();
		//sleepInSecond(2);
		
		
		//verify text đã đk thành công
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		
		//click qua My account
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		sleepInSecond(2);
		
		//verify dữ liệu sau khi đã truyển vào có hiện đúng như server trả về ko
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		
		//kiểm tra dữ liệu sau của dropdown list
		//phải new lại dropdown
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		//3 cách CHỌn khác nhau
		//select.selectByIndex(0);
		//select.selectByValue("0");
		//select.selectByVisibleText("0");
		
		//chọn item trong dropdown
		//select.selectByVisibleText("11");
		
		//dùng để verify xem trong dropdown này có bao nhiêu item, trả về kiểu int
		//Assert.assertEquals(select.getOptions().size(), 32);
		
		//verify dropdown này ko chọn nhiều item cùng lúc
		//Assert.assertFalse(select.isMultiple());
		
		//verify đã chọn đúng item này hay chưa
		//Assert.assertEquals(select.getFirstSelectedOption().getText(), "11");
		
		
		
		
	}

	@Test
	public void TC_02_Sum() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-register")).click();
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		List<WebElement> allItems = select.getOptions();
		List<String> actualItemsText = new ArrayList<>();
		//dùng duyệt qua all items trong list element
		
		for(WebElement item : allItems) {
			actualItemsText.add(item.getText());
		}
		
		Assert.assertEquals(expectedItemText, actualItemsText);
	}

	@Test
	public void TC_03_() {
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.net";
	}
	public void clickByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
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