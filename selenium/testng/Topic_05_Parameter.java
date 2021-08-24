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

public class Topic_05_Parameter {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, emailAddress, companyName, day, month, year;
	
	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		System.out.println("Run with " + browserName);
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
			driver = new FirefoxDriver();
			
		}else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");

			driver = new ChromeDriver();
			
		}else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDriver/msedgedriver");

			driver = new EdgeDriver();
			
		}else {
			throw new RuntimeException ("Please input correct browser name");
		}
		driver.get(url);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//khởi tạo data cho form đk
		firstName = "Super VIP";
		lastName = "Hihihi";
		emailAddress = "more" + generateEmail();
		companyName = "Google";
		day = "10";
		month = "August";
		year = "1990";
		
		
	}
	
		@Test
		public void TC_01_NopCommerce() {
			
			
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
