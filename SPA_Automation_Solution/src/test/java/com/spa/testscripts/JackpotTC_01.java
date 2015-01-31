package com.spa.testscripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.spa.objectrepository.HomeScreen_OR;
import com.spa.pages.HomeScreen;
import com.spa.pages.StateChange;
import com.spa.util.CommonVariables;
import com.spa.util.LogName;

public class JackpotTC_01 extends SAS_Engine{
	
	HomeScreen objHomeScreen = null ;
	boolean passflag = true;
	String testScriptErrorMessage = "";
	
	@org.testng.annotations.BeforeMethod(alwaysRun = true)
	public void BeforeMethod(){
		if(CommonVariables.getDriver()==null){
			CommonVariables.setDriver((objCommonFunc.StartAppiumDriver(CommonVariables.PlatformName.get() + "-" + CommonVariables.DeviceName.get())));
		}
		else{
			objCommonFunc.launchApp(false);
		}
		this.driver = CommonVariables.getDriver();
		//objCommonFunc.SetDriver(this.driver);
		objHomeScreen = new HomeScreen();
		passflag = true;
		testScriptErrorMessage = "";
	}
	
	@org.testng.annotations.AfterMethod(alwaysRun = true)
	public void afterMethod(){
		//objCommonFunc.closeApp();	
	}
	
	public void updateTestSciptStatus(boolean status, String errorMessage){
		if(!status){
			Assert.fail(errorMessage);
		}
	}
	
	@DataProvider(name="jackpotLotteryStateNames")
	public String[][] getDifferentClubLocation(){
		return new String[][]{
				{ "TEXAS"},
				{ "CALIFORNI"},
				{ "FLORIDA"},
		};
	}
	
	@org.testng.annotations.Test(dataProvider = "jackpotLotteryStateNames",priority = 2,description ="Select different Lottery State's to play for Jeckpot price.")
	public void JackpotResultVerification(String stateName){
		try{
			StateChange objstatechange = objHomeScreen.clickOnJackPot();
			objstatechange.ChangeState();
			if(objstatechange.SelectState(stateName)){
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "info", "successfully Change Lottery State value for Jackpot.");
				objstatechange.confirm();
				objCommonFunc.ScrollToBottom();
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "info", "Scrolled till bottom.");
				objCommonFunc.ScrollToText("ALL OR NOTHING");
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "pass", "successfully Change and Confirm Lottery State value for Jackpot.");
			}
			else{
				passflag = false;
				testScriptErrorMessage = testScriptErrorMessage+"Failed to change the Lottery State value. Expected Value: "+stateName;
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "error", testScriptErrorMessage);
			}
		}
		catch(Exception e2){
			passflag = false;
			testScriptErrorMessage = testScriptErrorMessage+"Error/Exception occurred while executing 'JackpotResultVerification' script. Exception message: "+e2.getLocalizedMessage();
			objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "error", testScriptErrorMessage);
		}
		updateTestSciptStatus(passflag,testScriptErrorMessage);
	}

	@org.testng.annotations.Test(priority = 1,description ="Verify the presence of Tool Tip on Camera.")
	public void verifyToolTipPresenceOnCamera(){
		try{
			if(objHomeScreen.isPopUpBoxDisplay()){
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "pass", "Successfully find 'Tap the Camera to add your first ticket' Pop-up box on the Home page. ");
			}
			else{
				passflag = false;
				testScriptErrorMessage =testScriptErrorMessage+"Fail to find 'Tap the Camera to add your first ticket' Pop-up box on the Home page. ";
				objCommonFunc.AddToLog("CurrentTestCaseLog", "error",testScriptErrorMessage );
			}
		}
		catch(Exception e2){
			passflag = false;
			testScriptErrorMessage = testScriptErrorMessage+"Error/Exception occurred while executing 'JackpotResultVerification' script. Exception message: "+e2.getLocalizedMessage();
			objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "error", testScriptErrorMessage);
		}
		updateTestSciptStatus(passflag,testScriptErrorMessage);
	}


}
