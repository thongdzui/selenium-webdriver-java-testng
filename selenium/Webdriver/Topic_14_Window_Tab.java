package Webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Window() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Get ra ID của active tab/Window đang đứng => 1cái
		String homeID = driver.getWindowHandle();
		System.out.println("Home ID là " + homeID);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);
		//Get ra ID của tất cả các tab/window đang có=> tất cả
	//	Set<String> allIDs = driver.getWindowHandles();
		
	//	String childIDs = null; //ID con, tab sau
		
	//	for (String id : allIDs) {
	//		System.out.println(id);
	//		if(!id.equals(homeID)) {
	//			childIDs = id ;
	//		}
			
			
	//	}
		
	
	//	System.out.println(driver.getTitle());
		
		//Switch vào tab/window nào đó bằng ID
	//	driver.switchTo().window(childIDs);
	//	System.out.println("Testt");
	//	System.out.println(driver.getTitle());
		
	//	switchToWIndowByID(homeID);
		
	//	driver.findElement(By.name("email")).sendKeys("childemail");
	//	System.out.println(driver.getTitle());
	//	String childID = driver.getWindowHandle();
	//	switchToWIndowByID(childID);
	//	System.out.println(driver.getTitle());
	//	driver.findElement(By.name("email")).sendKeys("parentemail");
		
		
		//switch vào Fb tab bằng title
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		driver.findElement(By.name("email")).sendKeys("facebookemail");
		
		//switch vào parent page lại
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		driver.findElement(By.name("email")).sendKeys("AutomationFC");
		
		//click vào link tiki => hành vi của app tự động nhảy qua tab đó lun
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		
		sleepInSecond(2);
		
		//switch vào tiki tab bằng title
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		
		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("Samsung Ultra");
		driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).click();
		sleepInSecond(5);
		
		//switch vào parent page lại
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		//click vào trang lazada
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		closeWindowWithoutParent(homeID);
		
		driver.findElement(By.id("email")).sendKeys("finalemail@gms.com");
		
		
	}
	//dùng cho 2 Window/tab
	//kiểm tra id trước
	//khác với parent
	//thì switch
	public void switchToWIndowByID(String parentID) {
		//get ra tất cả các tab/window d9ang có
		Set<String> allWindows = driver.getWindowHandles();
		
		//dùng vòng lặp để duyệt qua từng window
		for (String id : allWindows) {
			//nếu như id nào khác với parentID thì switch vào ID đó
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}
		
	}
	//dùng cho 2 hoặc nhiều hơn Window/Tab
	//switch vào từng window trc
	//get ra tilte của window đó
	//nếu như bằng thì ko kiểm tra tiếp
	public void switchToWindowByTitle(String expectedTitle) {
		//Get ra tất cả các tab/Window đang có
		Set<String> allWindows = driver.getWindowHandles();
		
		//dùng vòng lặp để duyệt qua từng tab/Window
		for (String id : allWindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
			//nếu như mà title = thì stop ko kiểm tra tiếp nữa
			if (windowTitle.equals(expectedTitle)) {
				break;
			}
		}
//		jds
//		sdfsam
		
	}
	@Test
	public void TC_02_() {
		
	}

	@Test
	public void TC_03_() {
		
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	public void closeWindowWithoutParent(String parentID) {
		//get ra tất cả các tab/Window đang có
		Set<String> allWindows = driver.getWindowHandles();
		
		//dùng vòng lặp duyệt qua từng window
		for (String id : allWindows) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		//switch về lại parentID
		driver.switchTo().window(parentID);
		
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