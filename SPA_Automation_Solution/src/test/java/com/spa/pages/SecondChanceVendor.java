package com.spa.pages;


import com.spa.objectrepository.SecondChanceVendor_OR;

public class SecondChanceVendor extends BasePage {
	public SecondChanceVendor()
	{
		if(objCommonFunc.FindElement(SecondChanceVendor_OR.GetMoreCoins,5)==null){
			throw new IllegalStateException("This is the Second Chance Vendor screen.");
		}
	}
	public SecondChanceInviteFriend ClickonMoreCoins()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(SecondChanceVendor_OR.GetMoreCoins, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SecondChanceInviteFriend();
	}
	public void ClickonFAQ()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(SecondChanceVendor_OR.FAQ, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HomeScreen ClickonHome()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(SecondChanceVendor_OR.Home, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HomeScreen();
	}
	public ScanIntro ClickonHelp()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(SecondChanceVendor_OR.Help, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ScanIntro();
		// need to add swipe functionality there
	}
	public void ClickonVendorList()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(SecondChanceVendor_OR.VendorList, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
