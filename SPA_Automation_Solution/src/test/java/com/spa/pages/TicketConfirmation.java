package com.spa.pages;

import com.spa.objectrepository.TicketConfirmation_OR;


public class TicketConfirmation extends BasePage {
	public TicketConfirmation()
	{
		if(objComFuncLib.FindElement(TicketConfirmation_OR.ScanGot,5)==null){
			throw new IllegalStateException("This is not the TicketConfirmation screen.");
		}
	}
	public TicketSubmissionConfirmation SendTicket()
	{
		objComFuncLib.WebDriverClick(objComFuncLib.FindElement(TicketConfirmation_OR.ScanTicket, 10));
		return new TicketSubmissionConfirmation();
	}
	public TicketScan SendBackToCamera()
	{
		objComFuncLib.WebDriverClick(objComFuncLib.FindElement(TicketConfirmation_OR.ScanAgain, 10));
		return new TicketScan();
	}
	public void OverlayGotit()
	{
		objComFuncLib.WebDriverClick(objComFuncLib.FindElement(TicketConfirmation_OR.ScanGot, 10));
	}
}
