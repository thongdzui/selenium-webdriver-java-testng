package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Normal_Class {
	
	//Interface
	WebDriver driver;
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}
	
	@Test
	public void TC_01_Browser() {
		//Bien driver tuong tac voi browser
		
		//mo 1 page, tham so truyen vao la 1 URl =>> **
		driver.get("https://m.facebook.com/?locale=vi_VN");
		
		
		//lấy ra đường dẫn của page hiện tại =>> **
		String localPageURl = driver.getCurrentUrl();
		
		//lấy ra title của page hiện tại  =>> **
		String localTitle = driver.getTitle();
		
		//lấy ra toàn bộ html code của page hiện tại
		driver.getPageSource();
		
		//Xử lí tab/Windows  =>> **
		driver.getWindowHandle();
		driver.getWindowHandles();
		
		//Framwork (Share class stage)
		//driver.manage().addCookie(cookie);
		
		//chờ cho element dc tìm thấy trong 30s   =>> **
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//setScriptTimeout
		//pageLoadTimeout
		
		//Back về page truoc do
		//Forward toi page trc
		//Refesh page hien tai
		//mở 1 url
		driver.navigate().back();
		
		//đóng trình duyệt  =>> **
		driver.quit();
		
		//đóng tab hiện tại
		driver.close();
		
		//Window/ Tab  =>> **
		//Alert  =>> ** 
		//Frame/ Iframe   =>> **
		driver.switchTo().alert();
		driver.switchTo().frame(1);
		driver.switchTo().window("");
		
		driver.manage().window().fullscreen();
		//=>> **
		driver.manage().window().maximize();  
		
		//Test GUI
		//lấy ra vị trí browser so với độ phân giải màn hình
		driver.manage().window().getPosition();
		//driver.manage().window().setPosition(targetPosition);
		
		driver.manage().window().getSize();
		//driver.manage().window().setSize(targetSize);
		
	}
	
	


	
	

}