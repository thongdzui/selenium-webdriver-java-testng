package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Method {
	//interface
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver(); //class
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Browser() {
		//bien driver tuong tac voi browser
		
		//bien ... tuong tac voi element (textbox,)
		
	}

	

	
	}

}