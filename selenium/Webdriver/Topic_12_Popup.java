package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_() {
		driver.get("https://ngoaingu24h.vn/");
		
		//click button đăng nhập
		driver.findElement(By.cssSelector("button.login_")).click();
		
		//kiểm tra pop up 
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());
		
		driver.findElement(By.cssSelector("#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("#password-input")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		
	}

	//@Test
	public void TC_02_Random_In_DOM() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(50);
		WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));
		if(popup.isDisplayed()) {
			//close 
			System.out.println("Pop up is displayed");
			driver.findElement(By.cssSelector("div.close-mailch")).click();
			sleepInSecond(5);
			
		}else  
		{
			System.out.println("Pop up is not displayed");
						
		}
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("selenium");
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		
		//verify title chứa text = selenium
		List<WebElement> articleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement article : articleTitle) {
			Assert.assertTrue(article.getText().contains("Selenium"));
		}
		
	}

	@Test
	public void TC_03_Random_Not_In_Dom() {
		driver.get("https://shopee.vn/");
		
		//nếu element ko có trong DOM thì hàm find element ko tìm thấy
		//chờ hết timeout của implicit
		//đánh fail testcase ngay tại steps
		//throw ra 1 exception: NoSuchElement
		//WebElement popup = driver.findElement(By.cssSelector("div.shopee-popup img"));
		
		//nếu element không có trong DOM thì hàm findElements ko tìm thấy
		//nó sẽ trả về 1 list empty (size =0)
		//ko đánh failed test case
		//ko throw exception
		
		List<WebElement> pop_up = driver.findElements(By.cssSelector("div.shopee-popup img"));
		
		if(pop_up.size()>0 && pop_up.get(0).isDisplayed()) {
			//close 
			System.out.println("Pop up is displayed");
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			sleepInSecond(5);
			
		}else  
		{
			System.out.println("Pop up is not displayed");
	
		}
		
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("Macbook");
		System.out.println("input thành công");
		driver.findElement(By.cssSelector("button.btn")).click();
		System.out.println("search thành công");
		
		List<WebElement> articleTitle = driver.findElements(By.cssSelector("div.yQmmFK"));
		
		for (WebElement article : articleTitle) {
			Assert.assertTrue(article.getText().contains("Macbook"));
		}
		
			
		
		
		
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