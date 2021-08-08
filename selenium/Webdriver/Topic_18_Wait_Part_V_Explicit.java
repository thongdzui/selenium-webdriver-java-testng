package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_Part_V_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By startButton = By.cssSelector("div[id='start'] button");
	By loadingIcon = By.cssSelector("div[id='loading'] img");
	By helloWorldText = By.cssSelector("div[id='finish'] h4");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		
		
		//khởi tạo explicitWait set timeout 15s
		explicitWait = new WebDriverWait(driver, 15);
		
		
	}

	//@Test
	public void TC_01_LessThan() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 15);
		
		//trước khi click wait cho start button dc click chưa (In case button disable, page loading...)
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
		driver.findElement(startButton).click();
		
		//wait cho loading icon disappear
		//bussiness loading icon biến mất hello world text sẽ hiển thị
		explicitWait = new WebDriverWait(driver, 3);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
		//driver.findElement(By.name("lastname")).sendKeys("Auto");
		
	}

	//@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 15);
		
		//trước khi click wait cho start button dc click chưa (In case button disable, page loading...)
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
		driver.findElement(startButton).click();
		
		
		explicitWait = new WebDriverWait(driver, 5);
		//wait cho loading icon disappear
		//bussiness loading icon biến mất hello world text sẽ hiển thị
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		//wait cho hello world hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloWorldText));
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
		//driver.findElement(By.name("lastname")).sendKeys("Auto");
		
	}

	//@Test
	public void TC_03_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 15);
		
		//trước khi click wait cho start button dc click chưa (In case button disable, page loading...)
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
		driver.findElement(startButton).click();
		
		//wait cho loading icon disappear
		//bussiness loading icon biến mất hello world text sẽ hiển thị
		explicitWait = new WebDriverWait(driver, 8);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertEquals(driver.findElement(helloWorldText).getText(), "Hello World!");
		//driver.findElement(By.name("lastname")).sendKeys("Auto");
		
		
	}
	@Test
	public void TC_04_Ajax_Loading() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		WebElement textToday = driver.findElement(By.xpath("//span[@class='label']"));
		Assert.assertEquals(textToday.getText(), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='1']")));
		
		driver.findElement(By.xpath("//a[text()='1']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='label']")).getText(), "Sunday, August 1, 2021");
		
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='1']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='1']")).isDisplayed());
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