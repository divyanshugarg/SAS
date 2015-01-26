package com.spa.testscripts;

import com.spa.util.CommonVariables;

public class homePageValidation extends SAS_Engine {
	
	@org.testng.annotations.BeforeMethod
	public void BeforeClass(){
		CommonVariables.setDriver((objCommonFunc.StartAppiumDriver(CommonVariables.PlatformName.get() + "-" + CommonVariables.DeviceName.get())));
		this.driver = CommonVariables.getDriver();
		objCommonFunc.SetDriver(this.driver);
	}
	
	@org.testng.annotations.AfterMethod
	public void afterClass(){
		driver.closeApp();	
	}
	
	@org.testng.annotations.Test(priority = 1,description ="<write object of Test Case>")
	public void TestSuccessfullWebLogin(){
		boolean passflag = true;
		try{

		}
		catch(Exception e2){
			objCommonFunc.AddToLog("CurrentTestCaseLog", "error", "Error Occurred in testcase: "+e2.getLocalizedMessage());
			passflag = false;

		}
	}
}
