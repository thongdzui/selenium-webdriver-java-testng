package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Iframe_Frame {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		scrollToButtonPage();
		//verify facebook page 
		//Index (nếu như thêm xóa sửa iframe thì index sẽ thay đổi)
		//driver.switchTo().frame(0);
		
		//Name, ID ko có
		//element
		//switch vào fb ifram
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		//verify fb fanpage = 169K likes
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title=\"Kyna.vn\"]/parent::div/following-sibling::div")).getText(), "169K lượt thích");
		
		//về parent page trc
		driver.switchTo().defaultContent();
		
		//switch vào chat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		sleepInSecond(3);
		
		//click vào khung chat 
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(3);
		
		//click vào button gửi tin nhắn
		driver.findElement(By.cssSelector("input.submit")).click();
		
		//verify error mess
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name+div.error_message")).getText(), "Tên của bạn chưa được nhập");
		Assert.assertEquals(driver.findElement(By.cssSelector("select#serviceSelect + div.error_message")).getText(), "Bạn chưa chọn dịch vụ hỗ trợ");
		
		//Back parent page trc
		driver.switchTo().defaultContent();
		
		//input vô thanh search
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		//verify title chứa text = excel
		List<WebElement> articleTitle = driver.findElements(By.cssSelector("section div.content>h4"));
		//verify số lượng element trong list
		Assert.assertEquals(articleTitle.size(), 9);
		
		//vòng lặp for -each
		for (WebElement article : articleTitle) {
			System.out.println(article.getText());
			Assert.assertTrue(article.getText().contains("Excel"));
		}
		
		//vòng lặp for
		for (int i = 0; i < articleTitle.size(); i ++ ) {
			
		}
		
		
		
	}
	
	public void scrollToButtonPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		//Name, ID ko có
		driver.switchTo().frame("login_page");
		
		
		
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
		
		driver.findElement(By.cssSelector("table.lForm img")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='fldPassword']")).isDisplayed());
		
		//Back to parent page
		driver.switchTo().parentFrame();
		
		//qua frame name = footer
		driver.switchTo().frame("footer");
		
		driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
		
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