package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Part3_Implicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By startButton = By.cssSelector("div[id='start'] button");
	By loadingIcon = By.cssSelector("div[id='loading'] img");
	By helloWorldText = By.cssSelector("div[id='finish'] h4");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("https://www.facebook.com/");
		
		
		
	}

	@Test
	public void TC_01_LessThan() {
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");//load trang mất hơn 5s
		
		driver.findElement(startButton).click();
		//từ lúc click tới lúc hiện Hello World là 5s
		// tìm element helloWorldText 
		//Nửa giây đầu tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>ko thấy => hết 2s implicitWait
		
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
		//driver.findElement(By.name("lastname")).sendKeys("Auto");
		
	}

	@Test
	public void TC_02_Enough() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(startButton).click();
		
		//từ lúc click tới lúc hiện Hello World là 5s
		// tìm element helloWorldText 
		//Nửa giây đầu tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>ko thấy => hết 2s implicitWait
		//Nửa giây tiếp theo tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>ko thấy
		//Nửa giây tiếp theo tìm =>tìm thấy => 5s
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
		//driver.findElement(By.name("lastname")).sendKeys("Auto");
	}

	@Test
	public void TC_03_Greater_Than() {
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(startButton).click();
		
		//từ lúc click tới lúc hiện Hello World là 5s
				// tìm element helloWorldText 
				//Nửa giây đầu tìm =>ko thấy
				//Nửa giây tiếp theo tìm =>ko thấy
				//Nửa giây tiếp theo tìm =>ko thấy
				//Nửa giây tiếp theo tìm =>ko thấy => hết 2s implicitWait
				//Nửa giây tiếp theo tìm =>ko thấy
				//Nửa giây tiếp theo tìm =>ko thấy
				//Nửa giây tiếp theo tìm =>ko thấy
				//Nửa giây tiếp theo tìm =>tìm thấy => 5s
				//giây thứ 6 ko tìm pass test case
		
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");//step này mất 5s
		//driver.findElement(By.name("sdf")).sendKeys("Auto");
		
		
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