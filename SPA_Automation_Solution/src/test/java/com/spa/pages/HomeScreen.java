package com.spa.pages;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.spa.objectrepository.HomeScreen_OR;
import com.spa.util.MobileLocator;
/**
 * 
 * @author Divyanshu Garg
 *
 */
public class HomeScreen extends BasePage {
	public boolean passflag;
	int i=0;
	
	public HomeScreen(){
		objCommonFunc.waitforElementVisible(HomeScreen_OR.welcomeScreenElem,4);
		if(!objCommonFunc.IsElementVisible(HomeScreen_OR.jackpot)){
			i=0;
			while(i<=5){
				//welcomeScreenElem = objComFuncLib.FindElement(MobileLocator.ById, "com.yoolotto.android:id/relativeLL", 0);
				objCommonFunc.SwipeLeft();
				if (objCommonFunc.IsElementVisible(By.name("OK,GOT IT")))
				{
					objCommonFunc.WebDriverClick(objCommonFunc.FindElement(By.className("android.widget.Button"), 10));
					objCommonFunc.waitforElementVisible(HomeScreen_OR.jackpot,4);
					break;
				}
				else{
					i++;
				}
			}
			if(!objCommonFunc.IsElementVisible(HomeScreen_OR.jackpot)){
				throw new IllegalStateException("This is not the Home screen.");
			}
		}

	}
	
	/**
	 * @description: Click on Jackpot to check the jackpot prizes of that day
	 */
	public StateChange clickOnJackPot(){
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.jackpot, 8));
			if(objCommonFunc.IsElementVisible(HomeScreen_OR.jackpot)){
				objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.jackpot, 0));
			}
			Thread.sleep(2500);
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
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.SecondChance, 10));
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
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.YooGames, 10));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * @description: if HomepageInro pop display on page then click on Popup else click on Ticket button
	 */
	public void closeHomePagePopUpBox()
	{
		objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.HomeScreenPopUp, 10));
	}
	
	public boolean isPopUpBoxDisplay()
	{
		try {
			if (objCommonFunc.IsElementVisible(HomeScreen_OR.HomeScreenPopUp))
				return true;
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @description: Click on Camera to Scan a ticket
	 */
	public TicketScan camera ()
	{
		try {
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.Camera, 10));
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
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.Setting, 1));
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
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.Navigation, 2));
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
			objCommonFunc.WebDriverClick(objCommonFunc.FindElement(HomeScreen_OR.Ticket, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
