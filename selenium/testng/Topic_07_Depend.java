package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_07_Depend {
	//test case nên bị phụ thuộc nhau
	//test trên failed thì test dưới cũng failed
	@Test(description = "Lalalla")
	public void Account_01_Create_New_Account() {
		Assert.assertTrue(false);
		
	}
	
	//(priority = 2)
	@Test(dependsOnMethods = "Account_01_Create_New_Account")
	public void Account_02_View_Account() {
		
	}
	
	//(priority = 3)
	@Test(dependsOnMethods = "Account_02_View_Account")
	public void Account_03_Edit_Account() {
		
	}
	
	//(priority = 4)
	@Test(dependsOnMethods = "Account_03_Edit_Account")
	public void Account_04_Move_Account() {
		
	}
	
	//(priority = 5)
	@Test(dependsOnMethods = "Account_04_Move_Account")
	public void Account_05_Delete_Account() {
		
	}

}
