package com.spa.testscripts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import com.spa.objectrepository.HomeScreen_OR;
import com.spa.pages.HomeScreen;
import com.spa.pages.StateChange;
import com.spa.pages.LotteryDetailsUnderAState;
import com.spa.util.CommonVariables;
import com.spa.util.LogName;
import com.spa.util.SystemUtilities;

public class JackpotTC_01 extends SAS_Engine{
	
	HomeScreen objHomeScreen = null ;
	boolean passflag = true;
	String testScriptErrorMessage = "";
	LotteryDetailsUnderAState ticketDetailsUnderAState = null;
	
	@BeforeMethod(alwaysRun = true)
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
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(){
		//objCommonFunc.closeApp();	
	}
	
	public void updateTestSciptStatus(boolean status, String errorMessage){
		if(!status){
			Assert.fail(errorMessage);
		}
	}
	
	@SuppressWarnings("serial")
	@DataProvider(name="jackpotLotteryDetailInDifferentState")
	public Object[][] getDifferentClubLocation(){
		return new Object[][]{
				{ new HashMap<String,String>(){{put("stateName", "TEXAS");put("lotteryDate", "Wednesday, February 04, 2015");put("lotteryName", "POWERBALL");put("lotteryPrice", "$ 317 MILLION");}}},
				{ new HashMap<String,String>(){{put("stateName", "FLORIDA");put("lotteryDate", "Wednesday, February 04, 2015");put("lotteryName", "POWERBALL");put("lotteryPrice", "$ 40 MILLION");}}},
				{ new HashMap<String,String>(){{put("stateName", "CALIFORNIA");put("lotteryDate", "Tuesday, February 03, 2015");put("lotteryName", "MEGA MILLIONS");put("lotteryPrice", "$ 40 MILLION");}}},
				{ new HashMap<String,String>(){{put("stateName", "DALLAS");put("lotteryDate", "Wednesday, February 04, 2015");put("lotteryName", "POWERBALL");put("lotteryPrice", "$ 317 MILLION");}}},
		};
	}
	
	@Test(dataProvider = "jackpotLotteryDetailInDifferentState",priority = 2,description ="Select different Lottery State's and verify the presence of a lottery for Jeckpot.")
	public void verifyLotteryDetailInDifferentState(HashMap<String,String> stateData){
		try{
			StateChange objstatechange = objHomeScreen.clickOnJackPot();
			objstatechange.ChangeState();
			if(objstatechange.SelectState(stateData.get("stateName"))){
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "info", "successfully Change Lottery State value ("+stateData.get("stateName")+")for Jackpot.");
				ticketDetailsUnderAState = objstatechange.confirm();
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "pass", "successfully Change and Confirm Lottery State value for Jackpot.");
				ArrayList<Map<String, String>> lotteryDetails = ticketDetailsUnderAState.getAllLotteryDetail();
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "info", "Lottery detail available on AUT: "+lotteryDetails);
				System.out.println(lotteryDetails);
				objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "info", "Scrolled till bottom.");
				objCommonFunc.ScrollToBottom();
				stateData.remove("stateName");
				if(systemUtilities.isItemPresenceInTheList(lotteryDetails, stateData)){
					objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "pass", "successfully find lottery ("+stateData+") in the selected state.");
				}
				else{
					passflag = false;
					testScriptErrorMessage = testScriptErrorMessage+"Failed to match lottery detail. Expected Value: "+stateData;
					objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "error", testScriptErrorMessage);

				}
			}
			else{
				passflag = false;
				testScriptErrorMessage = testScriptErrorMessage+"Failed to change the Lottery State value. Expected Value: "+stateData.get("stateName");
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

	@Test(priority = 1,description ="Verify the presence of Tool Tip on Camera.")
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
