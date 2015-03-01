package com.spa.pages;

import com.spa.objectrepository.AccountScreen_OR;

public class AccountScreen extends BasePage {
	public AccountScreen()
	{
		if(objCommonFunc.IsElementVisible(AccountScreen_OR.AccountText))
			throw new IllegalStateException("this is setting screen");
	}
	
	public void EmailTextEnter()
	{
		try {
			objCommonFunc.SendKeys(objCommonFunc.FindElement(AccountScreen_OR.EmailTextBox, 2), "qwerty@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void PasswordTextEnter()
	{
		try {
			objCommonFunc.SendKeys(objCommonFunc.FindElement(AccountScreen_OR.PasswordTextBox, 2), "qwerty");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public SettingScreen ClickonLogin ()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(AccountScreen_OR.LoginButton, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SettingScreen();
	}
	public Register ClickonRegistration ()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(AccountScreen_OR.NotRegistered, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Register();
	}
}
