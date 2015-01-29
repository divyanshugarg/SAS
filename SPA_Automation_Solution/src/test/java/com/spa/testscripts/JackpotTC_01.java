package com.spa.testscripts;

import com.spa.pages.HomeScreen;
import com.spa.pages.StateChange;
import com.spa.util.CommonVariables;

public class JackpotTC_01 extends SAS_Engine{
	
	HomeScreen objHomeScreen = null ;
	
	@org.testng.annotations.BeforeMethod
	public void BeforeClass(){
		CommonVariables.setDriver((objCommonFunc.StartAppiumDriver(CommonVariables.PlatformName.get() + "-" + CommonVariables.DeviceName.get())));
		this.driver = CommonVariables.getDriver();
		objCommonFunc.SetDriver(this.driver);
		objHomeScreen = new HomeScreen();
	}
	
	@org.testng.annotations.AfterMethod
	public void afterClass(){
	//	driver.closeApp();	
	}
	
	@org.testng.annotations.Test(priority = 1,description ="<Click on jackpot to check the jackpot prizes of all game>")
	public void JackpotResultVerification(){
		boolean passflag=objHomeScreen.HomePagePopUp();
		try{
			
			if (passflag)
				{
				objHomeScreen.HomePagePopUp();
				StateChange objstatechange = objHomeScreen.clickOnJackPot();
				objstatechange.ChangeState();
				objstatechange.SelectState();
				objstatechange.confirm();
				objHomeScreen.ScrollDownwards();
				objCommonFunc.AddToLog("CurrentTestCaseLog", "info", "successfully logged In");
				}
			else 
			{
				objHomeScreen.clickOnJackPot();
				objHomeScreen.ScrollDownwards();
				
			}
			
		}
		catch(Exception e2){
			objCommonFunc.AddToLog("CurrentTestCaseLog", "error", "Error Occurred in testcase: "+e2.getLocalizedMessage());

		}
	}
}
