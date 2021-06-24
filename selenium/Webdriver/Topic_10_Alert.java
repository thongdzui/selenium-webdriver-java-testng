package Webdriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	Alert alert;
	

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("http://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.name("btnLogin")).click();
		//Wait cho Alert xuất hiện + switch qa Alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(5);
		
		//Verify alert text
	
		Assert.assertEquals(alert.getText(), "User or Password is not valid");
		
		
		
		//Switch qa Alert
		//alert = driver.switchTo().alert();
		
		//accept
		alert.accept();
		
		//cancel
		//alert.dismiss();
		
		//get text
		//alert.getText();
		
		//send key
		//alert.sendKeys("");
		
	}

	//@Test
	public void TC_01_Accept_Alert02() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		//Wait cho Alert xuất hiện + switch qa Alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(5);
		
		//Verify alert text
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		//accept
		alert.accept();
		
		//verify text sau khi accpet alert
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
		
		//Verify alert text
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		//Wait cho Alert xuất hiện + switch qa Alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(5);
		
		
		//Verify alert text
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//accept
		alert.dismiss();
		//verify text sau khi accpet alert
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
		
	}
	
	//@Test
	public void TC_03_Prompt_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String fullName = "AutomationFC";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		//Wait cho Alert xuất hiện + switch qa Alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(5);
		
		//Verify alert text
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//Send key vào alert
		alert.sendKeys(fullName);
		sleepInSecond(5);
		
		//Alert accept
		alert.accept();
		
		//verify text sau khi accpet alert
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + fullName);
		
		
	}
	
	//@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		//Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).getText(), "Congratulations! You must have the proper credentials.");
		
	}
	
	@Test
	public void TC_04_Authentication_Alert02() {
		driver.get("http://the-internet.herokuapp.com");
		
		String hrefValue = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		passValueToUrl(hrefValue,"admin","admin");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public void passValueToUrl(String url, String username, String pass) {
		String[] hrefValue = url.split("//");
		url = hrefValue[0] + "//" +  username + ":" + pass + "@" + hrefValue[1] ;
		driver.get(url);
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