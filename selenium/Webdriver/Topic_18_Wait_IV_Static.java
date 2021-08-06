package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_IV_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By startButton = By.cssSelector("div[id='start'] button");
	By loadingIcon = By.cssSelector("div[id='loading'] img");
	By helloWorldText = By.cssSelector("div[id='finish'] h4");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_LessThan() {
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");//load trang mất hơn 5s
		
		driver.findElement(startButton).click();
		
		//time bị thiếu
		sleepInSecond(2);
		
		//thiếu 3s
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
		//driver.findElement(By.name("lastname")).sendKeys("Auto");
		
	}

	@Test
	public void TC_02_Enough() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(startButton).click();
		
		//time vừa đủ
		//khó bik bao nhiêu s sẽ đủ
		//lúc này nó là xx time
		//lúa kia nó > xx hoặc < xx time
		//do mạng load chậm do server client, API chậm
		//set nhiều thì dư, set ít thì failed, ko bik bao nhiêu là đủ
		
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
		//driver.findElement(By.name("lastname")).sendKeys("Auto");
	}

	@Test
	public void TC_03_Greater_Than() {
		
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(startButton).click();
		
		//tìm bị dư
		//time bị dư
		//chạy đúng/đủ nhưng chưa tối ưu
		sleepInSecond(10);
		//1 step dư mất 5 s
		//Set sleep này cho nhiều test trong 1 testcase
		//sleep 1 m 15s cho 1 test
		//project tầm 200 -300 test case, dư tầm 3h
		
		
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