package Webdriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part2 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		
		
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
	}

	//@Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//scroll to element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
		
		
	}

	@Test
	public void TC_02_Right_Click() {
		
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//right click
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		//hover to cut menu
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-cut"))).perform();
		
		//verify đã hover vào Cut Menu
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-visible")).isDisplayed());
		
		//click vào cut menu
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-cut"))).perform();
		
		//Wait cho Alert xuất hiện + switch qa Alert
		//alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		//sleepInSecond(5);
				
				//Verify alert text
				
		//Assert.assertEquals(alert.getText(), "clicked: cut");
		
		//accept
		//		alert.accept();
				
		//Alert is presence
		Assert.assertEquals(driver.switchTo().alert().getText(),"clicked: cut");
		
		//accept
		driver.switchTo().alert().accept();
		
		
	}

	@Test
	public void TC_03_() {
		
		
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