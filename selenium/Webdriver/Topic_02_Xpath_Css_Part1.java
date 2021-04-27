package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		//thời gian để chờ cho element dc tìm thấy trong 1 khoảng times 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
	}

	@Test
	public void TC_01_ID() {
		// Để thao tác với 1 element ở trên page
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		
		//Click vào male radio button
		driver.findElement(By.id("gender-male")).click();
		sleepInSecond(3);
		
		
	}

	@Test
	public void TC_02_Clasname() {
		//refresh page
		driver.navigate().refresh();
		
		driver.findElement(By.className("search-box-text")).sendKeys("Macbook");
		sleepInSecond(3);
		driver.findElement(By.className("search-box-button")).click();
		sleepInSecond(3);
	}

	@Test
	public void TC_03_Name() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.name("Email")).sendKeys("testttcase3@gmaiiil.com");
		sleepInSecond(3);
		driver.findElement(By.name("Newsletter")).click();
		sleepInSecond(3);
	}
	@Test
	public void TC_04_Tagname() {
		System.out.print("Sum link = " + driver.findElements(By.tagName("a")).size());
		System.out.print("Sum link = " + driver.findElements(By.tagName("input")).size());
		
		
	}
	@Test
	public void TC_05_Link_Text() {
		//chuyển qua trang Log in
		//đường link bao giờ cũng là thẻ a
		driver.findElement(By.linkText("Log in")).click();
		sleepInSecond(3);
	}
	@Test
	public void TC_06_Partial_Link_Text() {
		
		driver.findElement(By.partialLinkText("Recently viewed products")).click();
		sleepInSecond(3);
		driver.findElement(By.partialLinkText("viewed products")).click();
		driver.findElement(By.partialLinkText("Recently viewed ")).click();
		sleepInSecond(3);
		
		
	}
	
	@Test
	public void TC_07_Css() {
		//css đều cover dc 6 loại trên
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Thong vip pro");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']")).sendKeys("Macbook");
		
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("Thongvip@gmail.com");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("a[href*='login']")).click();
	}
	
	@Test
	public void TC_08_Xpath() {
		//chạy chậm nhất nhưng mạhh nhất
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation Thong Vip pro");
		
		
		//driver.findElement(By.xpath("//input[contains(@class,'small-search-box-form')]")).sendKeys("Macbook");
		
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("Thongvippro@gmail.com");
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		
		driver.findElement(By.xpath("//a[contains(text(),'Recently viewed products')]")).click();
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