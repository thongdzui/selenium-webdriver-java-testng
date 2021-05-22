package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Method {
	//interface
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		//Mở ra 1 trình duyệt
		driver = new FirefoxDriver(); //class
		
		//wait cho element xuất hiện trong 1 khoảng time xxx second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://demo.nopcommerce.com/");
		
		
	}

	@Test
	public void TC_01_Browser() {
		//bien driver tuong tac voi browser
		
		//bien ... tuong tac voi element (textbox,)
		
		//Muốn thao tác dc vs element thì phải tìm element trc
		
		//Tìm 1 element
		driver.findElement(By.id("")); //**
		
		//tìm nhiều element
		driver.findElements(By.id(""));  //**
		
		// nếu như mi2nh chỉ thao tác vs element 1 lần thì ko cần khai báo biến
		driver.findElement(By.id("small-searchterms")).sendKeys("Test");  //**
		
		//nếu cần thao tác với nhiều element thì nên khai báo biến
		//do thằng findElement thuộc trong WebElement nên ...
		WebElement searchtextbox = driver.findElement(By.id("small-searchterms"));
		searchtextbox.clear();  //**
		searchtextbox.sendKeys("kakak");
		searchtextbox.getAttribute("value");  //**
		
		//driver.findElement(By.id("small-searchterms")).clear();
		//driver.findElement(By.id("small-searchterms")).sendKeys("Apple");;
		//driver.findElement(By.id("small-searchterms")).getAttribute("value");
		
		
		//Đếm xem có bao nhiêu element thỏa mãn đk
		//verify số lượng element trả về như mong doi
		//thao tác với tất cả các element trong 1 page (checkbox/textbox...)
		List<WebElement> checkboxes = driver.findElements(By.xpath(""));
		
		//verify có đúng 6 textbox trong form đk
		Assert.assertEquals(checkboxes.size(), 6);
		
		WebElement singleElement = driver.findElement(By.className(""));
		//Textbox/TextArea/Editable Dropdown
		singleElement.clear();
		singleElement.sendKeys("");
		
		//button/Link /radio/ checkbox/ custom dropdown
		singleElement.click(); //**
		
		//các hàm có tiền tố bằng get luôn trả về dữ liệu
		//getTitle/ getCurrentUrl/ getPageSource/ getAttribute/ getCssValue/ getText
		singleElement = driver.findElement(By.xpath("//input[@id='FirstName']"));
		singleElement.getAttribute("value");
		//Autoo
		
		singleElement = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		singleElement.getAttribute("placeholder");
		//Search store
		//lấy ra giá trị các thuộc tính css thường dùng test GUI
		singleElement = driver.findElement(By.cssSelector(".search-box-button"));
		singleElement.getCssValue("background-color");  //*
		//#4ab2f1
		singleElement.getCssValue("text-transform");
		//uppercase
		//test giao diện luôn dc ưu tiên sau test chức năng
		
		
		//lấy ra tọa độ element so với page hiện tại, tọa độ bên ngoài
		singleElement.getLocation();
		
		//lấy ra size của element (rộng * cao),get góc bên trong của element
		singleElement.getSize();
		
		//chụp hình lỗi -> đưa vào HTML report
		singleElement.getScreenshotAs(OutputType.FILE);   //**
		
		//từ 1 element ko bik tag name lấy ra dc cái tagname rồi truyền vào 1 locator khác
		singleElement = driver.findElement(By.cssSelector(".search-box-button"));
		String searchButtonTagname = singleElement.getTagName();  //**
		
		
		//lấy ra text của 1 element (Header/ Link/ message)
		singleElement.getText();    //**
		
		//các hàm có tiền tố is thì trả về kiểu boolean (true/false

		//kiểm tra xem 1 element là hiển thị cho người dùng thao tác hay ko
		//rue: hiển thị
		//flase: ko hiển thị
		singleElement.isDisplayed();   //**
		
		//kiểm tra xem 1 element là disable hay ko
		//true:ko thao tác dc
		//false: thao tác dc
		singleElement.isEnabled();
		
		//checkbox/ radio/ dropdown
		//true: đã chọn
		//false: chưa dc chọn
		//kiểm tra xem 1 element là đã dc chọn rồi hay chưa
		singleElement.isSelected();
		
		
		//Nó thay cho hành vị Enter vào textbox/click vào button
		//chỉ dùng dc trong form (Login/Search/Register.....)
		singleElement.submit();
		singleElement = driver.findElement(By.id("small-searchterms"));
		singleElement.sendKeys("Apple");
		singleElement.submit();
	}

	

	
	}

}