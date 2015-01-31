package com.spa.testscripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.spa.pages.HomeScreen;
import com.spa.pages.ScanIntro;
import com.spa.pages.TicketConfirmation;
import com.spa.pages.TicketScan;
import com.spa.pages.TicketSubmissionConfirmation;
import com.spa.util.CommonVariables;

public class TicketSubmiting_TC02 extends SAS_Engine{
	HomeScreen objHomeScreen = null;
  @Test
  public void TicketSubmission() 
  {
	  objCommonFunc.SwipeRight();
	  //OK,GotIt
	  objHomeScreen.closeHomePagePopUpBox();
	  TicketScan Ticketscan = objHomeScreen.camera();
	  objCommonFunc.SwipeRight();
	  //GotIt Scan Intro need to be added
	  Ticketscan.stateselectionclick();
	  Ticketscan.Gameelectionclick();
	  Ticketscan.Dateselectionclick();
	  TicketConfirmation TicketConfirmation = Ticketscan.ScanButtonClick();
	  TicketSubmissionConfirmation TicketSC = TicketConfirmation.SendTicket();
  }
  @BeforeClass
  public void beforeClass() {
	  CommonVariables.setDriver((objCommonFunc.StartAppiumDriver(CommonVariables.PlatformName.get() + "-" + CommonVariables.DeviceName.get())));
		this.driver = CommonVariables.getDriver();
		objCommonFunc.SetDriver(this.driver);
		objHomeScreen = new HomeScreen();
  }

  @AfterClass
  public void afterClass() {
	  
  }

}
