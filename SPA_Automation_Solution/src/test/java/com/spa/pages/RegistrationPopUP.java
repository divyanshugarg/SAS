package com.spa.pages;

import com.spa.objectrepository.RegistrationPopUP_OR;
import com.spa.objectrepository.Registration_OR;

public class RegistrationPopUP extends BasePage 
{
	public RegistrationPopUP()
	{
		 if(objCommonFunc.FindElement(RegistrationPopUP_OR.PopupOK,2)==null){
				throw new IllegalStateException("This is the Registration screen.");
			}
	}
	public SettingScreen ClickonPopup()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(RegistrationPopUP_OR.PopupOK, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SettingScreen();
	}
}
