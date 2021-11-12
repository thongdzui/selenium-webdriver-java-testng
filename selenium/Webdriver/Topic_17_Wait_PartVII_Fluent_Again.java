package Webdriver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_Wait_PartVII_Fluent_Again {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	FluentWait<WebDriver> fluentWaitDriver;
	FluentWait<WebElement> fluentWaitElement;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countDownTime = driver.findElement(By.id("javascript_countdown_time"));
		fluentWaitElement = new FluentWait<WebElement>(countDownTime);

		// wait với tổng thời gian 15s
		fluentWaitElement.withTimeout(Duration.ofSeconds(15))
				// cơ chế tìm lại nếu chưa thấy , cứ mỗi 0.5s tìm lại 1 lần
				.pollingEvery(Duration.ofMillis(500))
				// nếu như trong thời gian tìm lại không thấy element
				.ignoring(NoSuchElementException.class)
				// xử lí đk
				.until(new Function<WebElement, Boolean>() {

					@Override
					public Boolean apply(WebElement element) {
						String text = element.getText();
						System.out.println("Time = " + text);
						// TODO Auto-generated method stub
						return text.endsWith("00");
					}

				});
	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div[id='start'] button")).click();

		fluentWaitDriver = new FluentWait<WebDriver>(driver);

		// wait với tổng thời gian 15s
		fluentWaitDriver.withTimeout(Duration.ofSeconds(6)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		WebElement helloworldText = fluentWaitDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector("div[id='finish'] h4"));
			}

		});

		Assert.assertEquals(helloworldText.getText(), "Hello World!");

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