package Webdriver;

import java.util.Random;
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

public class Topic_15_JavaScript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	String emailAddress, loginPageUrl, userID, passWord;

	// Data Test (New Customer)
	String name, dob, city, address, state, pin, phone;
	
	By nameTextboxBy = By.name("name");
	By dobTextboxBy = By.name("dob");
	By addressTextareaBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDriver/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}

	//@Test
	public void TC_01_Live_Guru() {
		//driver.get("http://live.demoguru99.com/");
		
		//Action tương tác với broswer/element
		//click/ set value/scroll
		
		//get giá trị
	
		
		System.out.println(jsExecutor.executeScript("return document.title"));
		System.out.println(jsExecutor.executeScript("return document.URL"));
		System.out.println(jsExecutor.executeScript("return document.domain"));
		
		String homePageTitle = (String) jsExecutor.executeScript("return document.title");
		Assert.assertEquals(homePageTitle, "Home page");
		
		String homePageTitle1;
		homePageTitle1 = driver.getTitle();
		Assert.assertEquals(homePageTitle1, "Home page");
		
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		String liveGuruURL = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveGuruURL, "http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		String customerServiceTitle = (String) executeForBrowser("return document.tilte");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		highlightElement("//input[@id='newsletter']");
		scrollToElement("//input[@id='newsletter']");
		
		sendkeyToElementByJS("//input[@id='newsletter']",generateEmail());
		
		highlightElement("//button[@class='button']");
		clickToElementByJS("//button[@class='button']");
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		String bankGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(bankGuruDomain, "demo.guru99.com");
		
		
		
	}
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.com";
	}
	//validation message HTML5
	//@Test
	public void TC_02_() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		
		
	
		
		driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
		
		String validationMessage;
		validationMessage = getElementValidationMessage("//input[@id='lastName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation");
		driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='firstName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		
		//input vô Họ
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("FC");
		driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
		
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		//input vô email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@");
		driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please enter an email address.");
		
		//input vô email
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@33");
		driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please match the requested format.");
		
		//input vô email
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@33.com");
		driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
		
		//verify html5 password
		validationMessage = getElementValidationMessage("//input[@id='password']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		
		
	}
	
	
	//remove attribute
	@Test
	public void TC_03_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4");
		
		//Init data (New Customer)
		emailAddress = "tony" + generateEmail();
		name = "John sd";
		dob = "1980-01-03"; 
		city = "Seattle"; 
		address = "12 PO sd"; 
		state = "UK"; 
		pin = "543122"; 
		phone = "0947839298";
		
		
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		
		
		
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passWord);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

	
		

		// input data
		driver.findElement(nameTextboxBy).sendKeys(name);
		
		removeAttributeInDOM("//input[@id='dob']","type");
		sleepInSecond(5);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		
		driver.findElement(addressTextareaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(passWord);

		// submit
		driver.findElement(By.name("sub")).click();

		// verify message dk thannh cong
	//	Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailAddress);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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