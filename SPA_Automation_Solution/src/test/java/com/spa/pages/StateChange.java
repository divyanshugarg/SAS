package com.spa.pages;

import com.spa.objectrepository.StateChange_OR;

public class StateChange extends BasePage
{
		public StateChange()
		{
			if(objCommonFunc.FindElement(StateChange_OR.ConfirmButton,5)==null){
				throw new IllegalStateException("This is not the Home screen.");
			}
		}
	/**
	 * @description: List of State getting display to user after clickinh on change state button
	 */
	public void ChangeState()
	{
		objCommonFunc.WebDriverClick(objCommonFunc.FindElement(StateChange_OR.ChangeStateButton, 2));
	}
	/**
	 * @description: Click on Jackpot to check the jackpot prizes of that day
	 */
	public boolean SelectState(String stateName)
	{
		return objCommonFunc.selectRadioButton(StateChange_OR.StateDropDown, stateName);
	}
	public LotteryDetailsUnderAState confirm()
	{
		objCommonFunc.WebDriverClick(objCommonFunc.FindElement(StateChange_OR.ConfirmButton, 10));
		return new LotteryDetailsUnderAState();
		
	}
}
