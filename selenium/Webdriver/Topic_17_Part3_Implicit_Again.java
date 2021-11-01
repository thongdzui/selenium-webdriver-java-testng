package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Part3_Implicit_Again {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();

		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Timeout_LessThan_Find_Element_Visible() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// After click - take 5s to display Helloworld

		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed(), "Hello World!");
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());

	}

	@Test
	public void TC_02_Timeout_Equal_Find_Element_Visible() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// After click - take 5s to display Helloworld

		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed(), "Hello World!");

		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());
	}

	@Test
	public void TC_03_Timeout_Greater_Than_Find_Element_Visible() {
		System.out.println("Testt");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start>button")).click();

		// After click - take 5s to display Helloworld

		Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed(), "Hello World!");
		System.out.println(driver.findElement(By.cssSelector("div#finish>h4")).getText());
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