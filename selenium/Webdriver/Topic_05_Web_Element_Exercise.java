package Webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Exercise {
	//khai báo biến (declare)
	WebDriver driver;
	String firstName, lastName, fullName, emailAddress, password;
	
	//By passwordTextbox = By.id("password");
	//By disableCheckbox = By.id("check-disabled");
	//By disableButton = By.id("button-disabled");

	
	@BeforeClass
	public void beforeClass() {
		//Khởi tạo biến driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//khởi tạo data test
		firstName = "Tony";
		lastName = "Truong";
		fullName = firstName + " " + lastName;
		emailAddress = "toynytrupng" + generateEmail();
		password = "Abc123";
		
		
		
	}

	//@Test
	public void TC_01_Create_New_Account() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		//Dùng hàm isDisplay để kiểm tra
		
		//Dùng hàm getText và verify contains(fullname/email)
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformation);
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
		
		driver.findElement(By.cssSelector("a[class='skip-link skip-account']")).click(); //class là .skip-link skip-account
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		
	}

	//@Test
	public void TC_02_Login() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.cssSelector("#email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
	
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");
	}

	//@Test
	public void TC_03_() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//hàm kiểm tra đk
		//nếu đk đúng thì mới vào hàm if
		//sai thì ko vào
		if(driver.findElement(By.id("mail")).isDisplayed()) {
			driver.findElement(By.id("mail")).sendKeys("Automation FC");
			System.out.println("Mail text box is displayed");
		} else {
			System.out.println("Mail text box is not displayed");
		}
		
		if(driver.findElement(By.id("edu")).isDisplayed()) {
			driver.findElement(By.id("edu")).sendKeys("Automation FC");
			System.out.println("Education text area is displayed");
		} else {
			System.out.println("Education text area is not displayed");
		}
		
		if(driver.findElement(By.id("under_18")).isDisplayed()) {
			driver.findElement(By.id("under_18")).sendKeys("Automation FC");
			System.out.println("Radio button 'under 18' is clicked");
		} else {
			System.out.println("Radio button 'under 18' is not clicked");
		}
		
		
	}
	//@Test
	public void TC_03_Function() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By emailTextBox = By.id("mail");
		By educationTextArea = By.id("edu");
		By under18radio = By.id("under_18");
		
		if(isElementDisplayed(emailTextBox)) {
			sentkeyToElement(emailTextBox, "Automation FC");
		}
		
		if(isElementDisplayed(educationTextArea)) {
			sentkeyToElement(educationTextArea, "Automation FC");
		}
		
		if(isElementDisplayed(under18radio)) {
			clickToElement(under18radio);
		}
		
		
	}
	@Test
	public void TC_04_Selected_Function() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By under18radio = By.id("under_18");
		By javaCheckbox = By.id("java");
		clickToElement(under18radio);
		clickToElement(javaCheckbox);
		
		//verify checkbox/ radio are selected
		Assert.assertTrue(isElementSelected(under18radio));
		Assert.assertTrue(isElementSelected(javaCheckbox));
		
		
		clickToElement(javaCheckbox);
		//verify checkbox is de-selected
		Assert.assertFalse(isElementSelected(javaCheckbox));
		Assert.assertTrue(isElementSelected(under18radio));
		
		
		
		
	}
	public void TC_05_Enabled() {
		By emailTextBox = By.id("mail");
		By educationTextArea = By.id("edu");
		By under18radio = By.id("under_18");
		By javaCheckbox = By.id("java");
		By passwordTextbox = By.id("password");
		By disableCheckbox = By.id("check-disbaled");
		By disableButton = By.id("button-disabled");
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Expected nó enable 
		Assert.assertTrue(isElementEnabled(emailTextBox));
		Assert.assertTrue(isElementEnabled(educationTextArea));
		Assert.assertTrue(isElementEnabled(under18radio));
		Assert.assertTrue(isElementEnabled(javaCheckbox));
		
		//Expecy nó disable (read only)
		Assert.assertFalse(isElementEnabled(passwordTextbox));
		Assert.assertFalse(isElementEnabled(disableCheckbox));
		
		//Mong muốn 1 element là disable
		Assert.assertFalse(isElementEnabled(disableButton));
		Assert.assertTrue(!isElementEnabled(disableButton));
		
	}
	
	@Test
	public void TC_06_Register_Validate() {
		driver.get("https://login.mailchimp.com/signup/");
		By passwordTextbox = By.id("new_password");
		By newletterCheckbox = By.cssSelector("#marketing_newsletter");
		By signUpButton = By.cssSelector("#create-account");
		By upperCaseCompleted = By.cssSelector(".uppercase-char.completed");
		By lowerCaseCompleted = By.cssSelector(".lowercase-char.completed");
		By numberCompleted = By.cssSelector(".number-char.completed");
		By specialCharCompleted = By.cssSelector(".special-char.completed");
		By greaterThan8CharCompleted = By.cssSelector("li[class='8-char completed']");
		
		driver.findElement(By.id("email")).sendKeys("automation@hotmail.com");
		driver.findElement(By.id("new_username")).sendKeys("automation.net");
		
		//Verify Uppercase
		driver.findElement(passwordTextbox).sendKeys("A");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(upperCaseCompleted).isDisplayed());
		Assert.assertFalse(driver.findElement(signUpButton).isEnabled());
		
		
		//Verify Lowercase
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("automation");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Verify Number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123456");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Verify Special Char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("@#$@#");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(specialCharCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Verify Special Char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("dasjdhbasj");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(greaterThan8CharCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//All criteria
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("Automation1@");
		
		Assert.assertFalse(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementDisplayed(specialCharCompleted));
		Assert.assertFalse(isElementDisplayed(specialCharCompleted));
		Assert.assertFalse(isElementDisplayed(greaterThan8CharCompleted));
		
		Assert.assertTrue(isElementEnabled(signUpButton));
		
		driver.findElement(By.cssSelector("#marketing_newsletter")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("#marketing_newsletter")).isSelected());
		
		Assert.assertTrue(isElementSelected(newletterCheckbox));
		
	}
	
	
	public boolean isElementDisplayed(By by) {
		if(driver.findElement(by).isDisplayed()) {
			System.out.println(by + " is displayed ");
			return true;
		} else {
			System.out.println(by + " is not displayed ");
			return false;
		}
		
		
	}
	
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println(by + " is selected ");
			return true;
		} else {
			System.out.println(by + " is not selected ");
			return false;
		}
		
		
	}
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println(by + " is enabled ");
			return true;
		} else {
			System.out.println(by + " is disabled ");
			return false;
		}
		
		
	}
	public void sentkeyToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
		
	}
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}

	//@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.com";
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