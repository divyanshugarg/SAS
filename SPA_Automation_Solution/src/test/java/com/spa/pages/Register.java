package com.spa.pages;

import com.spa.objectrepository.EmailFeed_OR;
import com.spa.objectrepository.Registration_OR;

public class Register extends BasePage
{
	public Register()
	{
		    if(objCommonFunc.FindElement(Registration_OR.DoneButton,5)==null){
			throw new IllegalStateException("This is the Registration screen.");
		}
	}

	public void EnterName()
	{
		try {
			objCommonFunc.SendKeys(objCommonFunc.FindElement(Registration_OR.Name, 0), "SPA");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void EnterEmail()
	{
		try {
			objCommonFunc.SendKeys(objCommonFunc.FindElement(Registration_OR.Email, 0), "SPAMobile@yopmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void EnterPassword()
	{
		try {
			objCommonFunc.SendKeys(objCommonFunc.FindElement(Registration_OR.Password, 0), "123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void ReEnterPassword()
	{
		try {
			objCommonFunc.SendKeys(objCommonFunc.FindElement(Registration_OR.ReEnterPassword, 0), "123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void EnterPhone()
	{
		try {
			objCommonFunc.SendKeys(objCommonFunc.FindElement(Registration_OR.Phone, 0), "1234567890");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void EnterAddress()
	{
		try {
			objCommonFunc.SendKeys(objCommonFunc.FindElement(Registration_OR.Address, 0), "Vaishali");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public RegistrationPopUP ClickonDoneButton()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(Registration_OR.DoneButton, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new RegistrationPopUP();
	}
	
}
