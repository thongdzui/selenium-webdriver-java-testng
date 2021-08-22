package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_02_Assert {
	//kiểu nguyên thủy sẽ có giá trị mặc định nếu chưa khởi tao (global)
	int studentNumber;
	Object studentName1 = null;
  @Test()
  public void TC_01_Assert() {
	  String studentName = "Truong Minh Thong";
	  
	  //verify 1 điều kiện trả về là đúng
	  Assert.assertTrue(studentName.contains("Thong"));
	  
	  //verify 1 điều kiện trả về là sai
	  Assert.assertFalse(studentName.contains("Lan"));
	  
	  //verify 2 cái điều kiện bằng nhau
	  Assert.assertEquals(studentName, "Truong Minh Thong");
	  
	  Assert.assertNotEquals(studentName, "Lonnd fsdfs");
	  
	 // Assert.assertNull(studentNumber);
	  Assert.assertNull(studentName1);
	  studentName1 = "Truong Minh Thai";
	  Assert.assertNotNull(studentName1);
	  
	  
  }
  
  

}
