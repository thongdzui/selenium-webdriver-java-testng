package Webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Exercise {
	//khai báo biến (declare)
	WebDriver driver;
	String firstName, lastName, fullName, emailAddress, password;

	
	@BeforeClass
	public void beforeClass() {
		//Khởi tạo biến driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//khởi tạo data test
		firstName = "Tony";
		lastName = "Truong";
		fullName = firstName + " " + lastName;
		emailAddress = "toynytrupng" + generateEmail();
		password = "Abc123";
		
		
		
	}

	@Test
	public void TC_01_Create_New_Account() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		//Dùng hàm isDisplay để kiểm tra
		
		//Dùng hàm getText và verify contains(fullname/email)
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformation);
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
		
		driver.findElement(By.cssSelector("a[class='skip-link skip-account']")).click(); //class là .skip-link skip-account
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		
	}

	@Test
	public void TC_02_Login() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.cssSelector("#email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
	
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");
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
		return rand.nextInt(9999) + "@mail.com";
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