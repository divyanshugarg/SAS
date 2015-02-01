package com.spa.pages;

import com.spa.objectrepository.TicketScan_OR;

public class TicketScan extends BasePage{
	public TicketScan()
	{
		if(objCommonFunc.FindElement(TicketScan_OR.ScanButton,5)==null){
			throw new IllegalStateException("This is not the Home screen.");
		}
	}
	
	public TicketConfirmation ScanButtonClick()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(TicketScan_OR.ScanButton, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TicketConfirmation();
	}
	public TicketScan FlashButtonClick()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(TicketScan_OR.FlashButton, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TicketScan();
	}
	public void HelpClick()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(TicketScan_OR.HelpButton, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public TicketScan StateTabClick()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(TicketScan_OR.stateTab, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TicketScan();
	}
	public TicketScan GameTabClick()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(TicketScan_OR.GameTab, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TicketScan();
	}
	public TicketScan DateTabClick()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(TicketScan_OR.DateTab, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TicketScan();
	}
	public void stateselectionclick()
	{
		//objComFuncLib.WebDriverClick(objComFuncLib.FindElements(TicketScan_OR.StateList, 10));
	}
	public void Gameelectionclick()
	{
		//objComFuncLib.WebDriverClick(objComFuncLib.FindElements(TicketScan_OR.StateList, 10));
	}
	public void Dateselectionclick()
	{
		//objComFuncLib.WebDriverClick(objComFuncLib.FindElements(TicketScan_OR.StateList, 10));
	}
}
