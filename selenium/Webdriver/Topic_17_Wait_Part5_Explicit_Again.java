package Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part5_Explicit_Again {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Invisible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for loading icon invisible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed(), "Hello World!");
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());

	}

	// @Test
	public void TC_02_Visible() {
		// explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait cho loading icon biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed(), "Hello World!");
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());

	}

	@Test
	public void TC_03_Date_Picker() {
		// explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Wait cho Date Time Picker dc hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

		// Wait cho element này có khả năng click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='23']")));

		// click vào element này
		driver.findElement(By.xpath("//a[text()='23']")).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("div[id$='RadCalendar1']>div.raDiv")));
		sleepInSecond(2);

		// Wait cho Date Time Picker dc hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

		// get text bằng với ngày mình mong muốn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Tuesday, November 23, 2021");

		// wait cho 23 display tr
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='23']/parent::td[@class='rcSelected']")));

		// check display
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='23']/parent::td[@class='rcSelected']")).isDisplayed());

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