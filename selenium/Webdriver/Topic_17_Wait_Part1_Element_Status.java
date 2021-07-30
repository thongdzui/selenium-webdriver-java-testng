package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part1_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By comfrimEmailTextBox = By.xpath("//input[@name='reg_email_confirmation__']");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		
	
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait(driver,15);//timeout wait là 15s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	//@Test
	public void TC_01_Visible() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		driver.findElement(By.name("reg_email__")).sendKeys("Automation@gfg.com");
		
		//Chờ cho element dc hiển thị
		
		//hiển thị trên UI
		//Có trong DOM
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(comfrimEmailTextBox));
		
	}

	//@Test
	public void TC_02_Invisible_01() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//case 1 Element ko có trên UI và vẫn có trong HTML
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(comfrimEmailTextBox));
		
		
	}
	
	//@Test
	public void TC_02_Invisible_02() {
		driver.navigate().refresh();
		//Case 2 Element ko có trong UI cũng không có trong HTML (DOM)
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(comfrimEmailTextBox));
		
	}

	@Test
	public void TC_03_Presence() {
		
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//wait presence (in DOM - ko có trên UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(comfrimEmailTextBox));
		
		driver.findElement(By.name("reg_email__")).sendKeys("Automation@gfg.com");
		
		//wait presence (in DOM - có trên UI)
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(comfrimEmailTextBox));
		
		
	}
	
	//@Test
	public void TC_04_Staleness() {
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		WebElement emailTextBox = driver.findElement(By.xpath("//input[@name='reg_email__']"));
		
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		//Wait for email text box is staleness
		explicitWait.until(ExpectedConditions.stalenessOf(emailTextBox));
		
		
		
	}
	
	@Test
	public void TC_04_Staleness02() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.findElement(By.id("SubmitCreate")).click();
		
		//1
		WebElement accountErrorMessage = driver.findElement(By.xpath("//div[@id='create_account_error']"));
		
		//2element tại bc số 1 bị update lại - no longer attach to DOM
		driver.navigate().refresh();
		
		//3 wait element staleness wait cho 1 element ko còn trạng thái cũ
		explicitWait.until(ExpectedConditions.stalenessOf(accountErrorMessage));
		
		
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