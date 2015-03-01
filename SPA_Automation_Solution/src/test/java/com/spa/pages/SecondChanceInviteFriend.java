package com.spa.pages;

import com.spa.objectrepository.SecondChanceInviteFriend_OR;


public class SecondChanceInviteFriend extends BasePage{
	public SecondChanceInviteFriend()
	{
		if(objCommonFunc.FindElement(SecondChanceInviteFriend_OR.Invitefriend,5)==null){
			throw new IllegalStateException("This is the Second Chance Invite screen.");
		}
	}
	public void InviteFriend()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(SecondChanceInviteFriend_OR.Invitefriend, 5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
