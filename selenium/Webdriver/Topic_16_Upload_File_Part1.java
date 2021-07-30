package Webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File_Part1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String dellName = "dell.png";
	String razerName = "razer.png";
	String thinkPadName = "ThinkPad.png";
	
	//String uploadFilePath = projectPath  + File.separator + "uploadFiles" + File.separator ;
	String uploadFilePath = projectPath   + "/uploadFiles/";
	
	String dellFilePath = uploadFilePath + dellName ;
	String razerFilePath = uploadFilePath + razerName ;
	String thinkPadFilePath = uploadFilePath + thinkPadName ;

	@BeforeClass
	public void beforeClass() {
		
		//System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDriver/chromedriver");
		
		driver = new ChromeDriver();
		
		
		//System.setProperty("webdriver.edge.driver", projectPath + "/browserDriver/msedgedriver");

		//driver = new EdgeDriver();
		
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Send_Key_One_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		//load file
		//driver.findElement(By.xpath("//input[@type='file']")).sendKeys("/Users/tminht/Desktop/Automation FC/02-WebDriver/uploadFiles/dell.png");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dellFilePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(razerFilePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(thinkPadFilePath);
		
		
		//verify list loaded success
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='dell.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dellName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + razerName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thinkPadName + "']")).isDisplayed());
		
		
		//Click to Start Button => Upload
		List<WebElement>startButton = driver.findElements(By.cssSelector("table button[class*='start']"));
		for (WebElement start : startButton ) {
			start.click();
			sleepInSecond(1);
		}
		
		//verify uploaded success
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='dell.png']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + dellName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + razerName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thinkPadName + "']")).isDisplayed());
		
	}

	@Test
	public void TC_02_Send_Key_Multi_File() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		//Note: Firefox ver 47 + selenium 2.5.3 ko work
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dellFilePath + "\n" + razerFilePath + "\n"+ thinkPadFilePath);
		
		
	}

	@Test
	public void TC_03_() {
		
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
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