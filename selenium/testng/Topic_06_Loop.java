package testng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_06_Loop {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, companyName, day, month, year;
	
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//khởi tạo data cho form đk
		firstName = "Super VIP";
		lastName = "Hihihi";
		
		companyName = "Google";
		day = "10";
		month = "August";
		year = "1990";
		
		
	}
	
		@Test(invocationCount = 5)
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
			
			
			driver.findElement(By.id("Email")).sendKeys("more" + generateEmail());
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
			driver.findElement(By.className("ico-logout")).click();;
			sleepInSecond(2);
			
			
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
		@AfterClass
		public void afterClass() {
			driver.quit();
		}


}
