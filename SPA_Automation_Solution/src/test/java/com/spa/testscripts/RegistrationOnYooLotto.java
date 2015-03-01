package com.spa.testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.spa.pages.AccountScreen;
import com.spa.pages.HomeScreen;
import com.spa.pages.Register;
import com.spa.pages.SettingScreen;
import com.spa.util.CommonVariables;
import com.spa.util.LogName;

public class RegistrationOnYooLotto extends SAS_Engine {
	HomeScreen objHomeScreen = null ;
	boolean passflag = true;
	String testScriptErrorMessage = "";
  @Test (priority = 2, description= "User Register in Yoolotto")
  public void RegisterYooLotto()
  {
	   SettingScreen objSetting = objHomeScreen.setting();
	   AccountScreen objAccount = objSetting.clickonAccount();
	   Register objRegister = objAccount.ClickonRegistration();
	   objRegister.EnterName();
	   objRegister.EnterEmail();
	   objRegister.EnterPassword();
	   objRegister.ReEnterPassword();
	   objRegister.EnterPhone();
	   objRegister.EnterAddress();
	   objRegister.ClickonDoneButton();
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
  
  public void updateTestSciptStatus(boolean status, String errorMessage){
		if(!status){
			Assert.fail(errorMessage);
		}
	}
  @BeforeMethod
  public void beforeMethod() {
	  if(CommonVariables.getDriver()==null)
	    {
			CommonVariables.setDriver((objCommonFunc.StartAppiumDriver(CommonVariables.PlatformName.get() + "-" + CommonVariables.DeviceName.get())));
		}
		else
		{
			objCommonFunc.launchApp(false);
		}
		this.driver = CommonVariables.getDriver();
		//objCommonFunc.SetDriver(this.driver);
		objHomeScreen = new HomeScreen();
		passflag = true;
		testScriptErrorMessage = "";
  }

  @AfterMethod
  public void afterMethod() {
  }

}
