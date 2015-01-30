package com.spa.testscripts;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.spa.objectrepository.HomeScreen_OR;
import com.spa.pages.HomeScreen;
import com.spa.pages.StateChange;
import com.spa.util.CommonVariables;

public class JackpotTC_01 extends SAS_Engine{
	
	HomeScreen objHomeScreen = null ;
	boolean passflag = true;
	String testScriptErrorMessage = "";
	
	@org.testng.annotations.BeforeMethod(alwaysRun = true)
	public void BeforeMethod(){
		CommonVariables.setDriver((objCommonFunc.StartAppiumDriver(CommonVariables.PlatformName.get() + "-" + CommonVariables.DeviceName.get())));
		this.driver = CommonVariables.getDriver();
		objCommonFunc.SetDriver(this.driver);
		objHomeScreen = new HomeScreen();
		passflag = true;
		testScriptErrorMessage = "";
	}
	
	@org.testng.annotations.AfterMethod(alwaysRun = true)
	public void afterMethod(){
		objCommonFunc.closeApp();	
	}
	
	public void updateTestSciptStatus(boolean status, String errorMessage){
		if(!status){
			Assert.fail(errorMessage);
		}
	}
	
	@org.testng.annotations.Test(priority = 1,description ="<Click on jackpot to check the jackpot prizes of all game>")
	public void JackpotResultVerification(){
		try{
			if (objHomeScreen.HomePagePopUp())
			{
				objHomeScreen.HomePagePopUp();
				int counter=0;
				while(counter<=3){
					//welcomeScreenElem = objComFuncLib.FindElement(MobileLocator.ById, "com.yoolotto.android:id/relativeLL", 0);
					objCommonFunc.SwipeLeft();
					if (objCommonFunc.IsElementVisible(HomeScreen_OR.jackpot))
					{
						break;
					}
					else{
						counter++;
					}
				}
				StateChange objstatechange = objHomeScreen.clickOnJackPot();
				objstatechange.ChangeState();
				objstatechange.SelectState();
				objstatechange.confirm();
				objCommonFunc.ScrollToBottom();
				objCommonFunc.AddToLog("CurrentTestCaseLog", "info", "successfully logged In");
			}
			else 
			{
				objHomeScreen.clickOnJackPot();
				objCommonFunc.ScrollToBottom();
			}
		}
		catch(Exception e2){
			passflag = false;
			testScriptErrorMessage = "Error Occurred in testcase: "+e2.getLocalizedMessage();
			objCommonFunc.AddToLog("CurrentTestCaseLog", "error", testScriptErrorMessage);
			objCommonFunc.AddToLog("CurrentTestCaseLog", "screenshot", testScriptErrorMessage);
		}
		updateTestSciptStatus(passflag,testScriptErrorMessage);
	}
}
