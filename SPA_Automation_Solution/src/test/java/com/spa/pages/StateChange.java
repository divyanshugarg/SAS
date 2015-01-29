package com.spa.pages;

import com.spa.objectrepository.StateChange_OR;

public class StateChange extends BasePage
{
		public StateChange()
		{
			if(objComFuncLib.FindElement(StateChange_OR.ConfirmButton,5)==null){
				throw new IllegalStateException("This is not the Home screen.");
			}
		}
	/**
	 * @description: List of State getting display to user after clickinh on change state button
	 */
	public void ChangeState()
	{
		objComFuncLib.WebDriverClick(objComFuncLib.FindElement(StateChange_OR.ChangeStateButton, 2));
	}
	/**
	 * @description: Click on Jackpot to check the jackpot prizes of that day
	 */
	public void SelectState()
	{
		//objComFuncLib.WebDriverClick(objComFuncLib.FindElements(StateChange_OR.StateDropDown, 2));
	}
	public void confirm()
	{
		objComFuncLib.WebDriverClick(objComFuncLib.FindElement(StateChange_OR.ConfirmButton, 15));
	}
}
