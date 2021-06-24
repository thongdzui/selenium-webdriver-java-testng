package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part1 {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//how to verify text in Tooltip
	//@Test
	public void TC_01_Hover_Mouse() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		//action hover chuột vào textbox
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(4);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
		
	}

	//@Test
	public void TC_01_Hover_Mouse_Tikipage() {
		driver.get("https://tiki.vn/");
		
		//action hover chuột vào 
		action.moveToElement(driver.findElement(By.cssSelector(".profile-icon"))).perform();
		//sleepInSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='tel']")).isDisplayed());
	
		
	}
	//@Test
	public void TC_01_Hover_Mouse_Mytra() {
		driver.get("http://www.myntra.com/");
		//a[@class='desktop-main' and text()='Kids']
		
		//action hover chuột vào
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).getText(), "Kids Home Bath");
		//Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
	}
	
	
	//@Test
	public void TC_02_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));
		System.out.println("số lượng element là " + rectangleNumber.size());
		
		//click and hold=> hover chuột đến element đích => nhả chuot trái ra
		action.clickAndHold(rectangleNumber.get(0))
		.moveToElement(rectangleNumber.get(3))
		.release()
		.perform();
		
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
	}

	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));
		//Nhấn phím Ctrl xuống
		action.keyDown(Keys.COMMAND).perform();
		
		//chọn các element đích
		action.click(rectangleNumber.get(0));
		action.click(rectangleNumber.get(2));
		action.click(rectangleNumber.get(7));
		action.click(rectangleNumber.get(8)).perform();;
		
		//nhả phím Ctrl ra
		action.keyUp(Keys.COMMAND).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
		
		
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