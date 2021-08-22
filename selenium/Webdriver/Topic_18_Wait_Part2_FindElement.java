package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import sun.util.calendar.BaseCalendar.Date;

public class Topic_18_Wait_Part2_FindElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://www.facebook.com/");
		
		
	}
	//testt commit
	//@Test
	public void TC_01_Find_Element() {
		//find single element
		//tìm thấy 1 matching node
		//không cần chờ hết timeout nên chạy rất nhanh
		System.out.println("Start 1" + getDateTimeNow());
		driver.findElement(By.id("email")).sendKeys("automation");
		System.out.println("Start 2" + getDateTimeNow());
		
		
		//2 không tìm thấy node nào hết
		//chờ hết timeout
		//ném ra 1 ngoại lệ exception
		//đánh failed test case
		//không chạy tiếp nữa
		driver.findElement(By.id("tiki")).isDisplayed();
		
		
		//3 tìm thấy nhiều hơn 1 node
		//nếu như nhiều hơn 1 node thao tác với node đầu tiên
		driver.findElement(By.cssSelector("div#pageFooter a"));
		
	}
	

	@Test
	public void TC_02_Find_Elements() {
		//find multi element
		List<WebElement>elements ;
		
		
		//1tìm thấy 1 matching node
		//nó sẽ trả về 1 list chứa element =>size=1
		elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List size " + elements.size());
		
		//2 không tìm thấy node nào hết
		//cũng phải chờ hết timeout implicit
		//trong thời gian chờ tìm lại mỗi nửa 0.5 1 lần
		//chờ hết timeout không đánh faield test case
		//không ném ngoại lệ nào hết
		//vẫn chạy các steps tiép theo
		elements = driver.findElements(By.cssSelector("input#tiki"));
		System.out.println("List size " + elements.size());
		
		
		//3tìm thấy nhiều hơn 1 matching node
		//nó sẽ trả về 1 list chứa element =>size=n
		
		elements = driver.findElements(By.cssSelector("div#pageFooter a"));
		System.out.println("List size " + elements.size());
		
	}

	@Test
	public void TC_03_() {
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getDateTimeNow() {
		
		Date date = new Date();
		return date.toString();
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