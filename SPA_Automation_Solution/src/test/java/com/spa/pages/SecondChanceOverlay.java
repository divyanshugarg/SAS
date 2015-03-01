package com.spa.pages;

import com.spa.objectrepository.SecondChanceOverlay_OR;

public class SecondChanceOverlay extends BasePage{
	public SecondChanceOverlay()
	{
		if(objCommonFunc.FindElement(SecondChanceOverlay_OR.overlay,5)==null){
			throw new IllegalStateException("This is the Second Chance Overlay screen.");
		}
	}
	public SecondChanceVendor ClickonOverlay()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(SecondChanceOverlay_OR.overlay, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SecondChanceVendor();
	}
}
