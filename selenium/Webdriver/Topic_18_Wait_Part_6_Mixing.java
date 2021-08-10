package Webdriver;


import java.util.concurrent.TimeUnit;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Wait_Part_6_Mixing {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	WebElement element;
	
	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 3);
		
		System.out.println("start time " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("end time " + getDateTimeNow());
		
		System.out.println("start time " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("start time " + getDateTimeNow());
		
		
		
	}

	//@Test
	public void TC_02_01_Element_Not_Found_Only_Implicit() {
		
		//2.1 - Chỉ sử dụng implicit ko - ko dùng explicit
		//Chờ hết timeout/ tìm lại sau mỗi nửa s/ hết timeout đánh fail testcase/ throw NoSuchElementException
		
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		System.out.println("Start implicit " + getDateTimeNow());
		try {
			
		driver.findElement(By.cssSelector("input#testing"));
		} finally {
			System.out.println("End implicit " + getDateTimeNow());
		}
		
	}
	//@Test
	public void TC_02__02_Element_Not_Found_Only_Explicit() {
		driver.get("https://www.facebook.com/");
		explicitWait = new WebDriverWait(driver, 5);
		System.out.println("Start explicit " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#testing")));
		} finally {
			System.out.println("End explicit " + getDateTimeNow());
		}
	}
	
	@Test
	public void TC_02__03_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		//Implicit > Explicit
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);
		
		
		System.out.println("Start implicit " + getDateTimeNow());
		
		
		//nhận timeout của implicit
		try {
			driver.findElement(By.cssSelector("input#tiki"));
		} catch (Exception e1) {
			
		}
		
		System.out.println("end implicit " +getDateTimeNow());
		
		System.out.println("Start explicit " + getDateTimeNow());
		//nhận timeout của cả 2 hàm visibilityOfElementLocated
		//driver.findElement (locator) =>bị ảnh hưởng timeout của implicit 5s
		//elementfVisible =>bị ảnh hưởng timeout của explicit 3s
		
		try {
			element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#testing")));
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		System.out.println("End explicit " + getDateTimeNow());
		Assert.assertNull(element);
	}
	
	
	//Explicit nhận vào tham số By
	//Explicit nhận vào tham số Web Element
	
	
	//@Test
	public void TC_02_4_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		explicitWait = new WebDriverWait(driver, 5);
		
		System.out.println("Start explicit " + getDateTimeNow());
		try {
			//Nhận tham số là By
			//Nhận timeout của cả 2 trong hàm visibilityOfElementLocated
			//driver.findElement(locator) bị ảnh hưởng timeout implicit
			//elementIfvisible bị ảnh hưởng timout của Explicit

			//throw ra exception: Timeout exception (explicit)
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(("input#testing"))));
			
			//Nhận tham số là Web Element
			//chạy từ trong ra ngoài findElement -> Nhận timeout của implicit
			//chờ hế timeoput
			//đánh failed testcase
			//throw NoSuchElement (Implicit)
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#testing"))));
		} catch (Exception e)  {
			e.printStackTrace();
		}
		
		System.out.println("End explicit " + getDateTimeNow());
	}

	@Test
	public void TC_05() {
		driver.get("");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		
		System.out.println("Start explicit " + getDateTimeNow());
		
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#testing"))));
		}finally {
			System.out.println("End explicit " + getDateTimeNow());
		}
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