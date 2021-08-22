package mobileTest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Mobile {

  @Test(groups = {"mobile" , "regression" })
  public void TC_01_Get_Certificate() {
	  System.out.println("Run TC 01");
	  
  }
  
  @Test(groups = {"mobile" , "regression" })
  public void TC_02_View_Certificate() {
	  System.out.println("Run TC 02");
	  
  }
  
  @Test(groups = {"mobile" , "regression" })
  public void TC_03_Test_Certificate() {
	  System.out.println("Run TC 03");
	  
  }
  

}
