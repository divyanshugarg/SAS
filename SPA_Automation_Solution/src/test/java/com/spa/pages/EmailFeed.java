package com.spa.pages;

import com.spa.objectrepository.EmailFeed_OR;

public class EmailFeed extends BasePage{
	public EmailFeed()
	{
		if(objComFuncLib.FindElement(EmailFeed_OR.NoThanksButton,5)==null){
			throw new IllegalStateException("This is not the EmailFeed screen.");
		}
	}
	
	public TicketSubmissionConfirmation SubmitButtonClick()
	{
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(EmailFeed_OR.SubmitButton, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TicketSubmissionConfirmation();
	}
	
	public TicketSubmissionConfirmation NoThanksButtonClick()
	{
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(EmailFeed_OR.NoThanksButton, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TicketSubmissionConfirmation();
	}
	
	public EmailFeed Emailtext()
	{
		return new EmailFeed();
	//	objComFuncLib.SendKeys(objComFuncLib.FindElement(EmailFeed_OR.EmailTextbox), "xyz@yopmail.com");
	}



}
