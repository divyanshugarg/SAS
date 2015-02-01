package com.spa.pages;

import com.spa.objectrepository.TicketSubmissionConfirmation_OR;

public class TicketSubmissionConfirmation extends BasePage {
	public TicketSubmissionConfirmation()
	{
		if(objCommonFunc.FindElement(TicketSubmissionConfirmation_OR.ConfirmGotIt,5)==null){
			throw new IllegalStateException("This is not the TicketSubmissionConfirmation screen.");
		}
	}
	
	public HomeScreen GotItClickTicket()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(TicketSubmissionConfirmation_OR.ConfirmGotIt,10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HomeScreen();
	}

}
