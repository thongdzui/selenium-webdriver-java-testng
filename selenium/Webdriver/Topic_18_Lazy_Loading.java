package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Lazy_Loading {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		explicitWait = new WebDriverWait(driver , 20 );
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test
	public void TC_02_Random_In_DOM() {
		driver.get("https://blog.testproject.io/");
		
		
		
		
		Assert.assertTrue(isJQueryLoadedSuccess());
					
		
		
		//input "Selenium" into text box search
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
		
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#search-2 span.glass")));
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		
		
		
		//verify title chứa text = selenium
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("h3.post-title>a")));
		List<WebElement> articleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement article : articleTitle) {
			Assert.assertTrue(article.getText().contains("Selenium"));
		}
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isJQueryLoadedSuccess() {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.
						executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
		
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