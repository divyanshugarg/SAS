package com.spa.pages;


import io.appium.java_client.MobileBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.spa.objectrepository.HomeScreen_OR;
import com.spa.util.MobileLocator;
/**
 * 
 * @author Kapil
 *
 */
public class HomeScreen extends BasePage {
	public boolean passflag;
	public WebElement welcomeScreenElem = null;
	
	public HomeScreen(){
		welcomeScreenElem = objComFuncLib.FindElement(MobileLocator.ById, "com.yoolotto.android:id/relativeLL", 0);
		objComFuncLib.SwipeLeft(welcomeScreenElem);
		if(objComFuncLib.FindElement(HomeScreen_OR.jackpot,0)==null){
			throw new IllegalStateException("This is not the Home screen.");
		}
	}
	
	/**
	 * @description: Click on Jackpot to check the jackpot prizes of that day
	 */
	public StateChange clickOnJackPot(){
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(HomeScreen_OR.jackpot, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new StateChange();
	}

	/**
	 * @description: Click on Second Chance to win the prizes
	 */
	public void secondchance ()
	{
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(HomeScreen_OR.SecondChance, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @description: Click on YooGames to win the prizes
	 */
	public void YooGames()
	{
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(HomeScreen_OR.YooGames, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @description: if HomepageInro pop display on page then click on Popup else click on Ticket button
	 */
	public boolean HomePagePopUp()
	{

		try {
			if (objComFuncLib.IsElementVisible(HomeScreen_OR.HomeScreenPopUp))
					{
						objComFuncLib.WebDriverClick(objComFuncLib.FindElement(HomeScreen_OR.HomeScreenPopUp, 10));
						return true;
						
					}
			else
			{
				ticket();
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	//	return this;
		
	}
	/**
	 * @description: Click on Camera to Scan a ticket
	 */
	public TicketScan camera ()
	{
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(HomeScreen_OR.Camera, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new TicketScan();
	}
	/**
	 * @description: Go to Setting screen
	 */
	public void setting()
	{
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(HomeScreen_OR.Setting, 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @description: Click on Navigation icon to check the user location
	 */
	public void navigation()
	{
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(HomeScreen_OR.Navigation, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @description: Click on ticket icon and all tickets slightly goes to upward direction.
	 */
	public void ticket()
	{
		try {
			objComFuncLib.WebDriverClick(objComFuncLib.FindElement(HomeScreen_OR.Ticket, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Scrollupwards()
	{
		objComFuncLib.ScrollToTop();
	}
	public void ScrollDownwards()
	{
		objComFuncLib.ScrollToBottom();
	}
	public void SwipeLeft()
	{
		//objComFuncLib.SwipeLeft(element);
	}
	public void SwipeRight()
	{
		//objComFuncLib.SwipeRight(element);
	}
}
