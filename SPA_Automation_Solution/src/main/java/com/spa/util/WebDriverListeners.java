package com.spa.util;


import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.sun.jna.platform.win32.Win32Exception;

public class WebDriverListeners implements WebDriverEventListener{ //AbstractWebDriverEventListener{
	private By lastFindBy;
	private WebElement lastElement;
	private EventFiringWebDriver webDriver;
	private CommonFunctionLib objCommonfunclib;
	private String DebugMode;
	private static int exceptionCounter = 1;
	
//	public WebDriverListerners(RemoteWebDriver webDriver){
//		this.webDriver = webDriver;
//	}
	
	public WebDriverListeners(EventFiringWebDriver webDriver){
		this.webDriver = webDriver;
		DebugMode = CommonVariables.DebugMode.get();
//		DebugMode = ConfigManager.getProperties().getProperty("DebugMode");
	}
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		if(DebugMode.equals("True")){
			System.out.println("Navigating to URL: " + url);
		}
//		System.out.println("Before navigating to url printing the browser associated capabilities");
		objCommonfunclib.AddToLog("CurrentTestClassLog", "info", "Navigating to URL: " + url);
//		System.out.println(this.webDriver.getCapabilities());
	}
	 // Called before finding Element(s)
	 @Override
	 public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		  lastFindBy = by;
			if(DebugMode.equals("True")){
//				System.out.println("Trying to find: " + by.toString());
			}
//		  System.out.println("Trying to find: " + by.toString()); // This is optional and an alternate way
//		  objCommonfunclib.AddToLog("CurrentTestClassLog", "info", "Trying to find the webelement: " + by.toString());
		  
	 }

	 // Called after finding Element(s)
	 @Override
	 public void afterFindBy(By by, WebElement element, WebDriver driver) {
		  lastFindBy = by;
			if(DebugMode.equals("True")){
				System.out.println("Found: " + by.toString() + "'."); 
			}
//		  System.out.println("Found: " + by.toString() + "'."); // This is optional and an alternate way
//		  objCommonfunclib.AddToLog("CurrentTestClassLog", "info", "Found the webelement: " + by.toString());
	 }
	 
	@Override
	public void onException(Throwable throwable, WebDriver driver) {

		if(throwable.getMessage().toLowerCase().contains("error communicating with the remote browser. it may have died")){
			driver.navigate().refresh();
		}
		/*		if((throwable.getClass().isInstance(new NoSuchWindowException("")))){
			CommonVariables.CommonDriver.set(null);

		}
		else if((throwable.getClass().isInstance(new NoSuchElementException("")))
				|| (throwable.getClass().isInstance(new TimeoutException("")))
				|| (throwable.getClass().isInstance(new ElementNotVisibleException("")))
				|| (throwable.getClass().isInstance(new InvalidSelectorException("")))){

		}
		else{}
		 */
		try{
			if(throwable.getClass().isInstance(new org.openqa.selenium.NoSuchWindowException("")) &&
					(throwable.getMessage().toString().toLowerCase().contains("target window already closed")
							|| throwable.getMessage().toString().toLowerCase().startsWith("chrome not reachable")
							|| throwable.getMessage().toString().toLowerCase().startsWith("terminated due to browser_timeout"))){
				CommonVariables.CommonDriver.set(null);
				try{
					objCommonfunclib.AddToLog("CurrentTestClassLog", "debug", "Current Test Class Name: "+CommonVariables.CurrentTestClassName.get()+" Current Test Case Name: "+CommonVariables.CurrentTestCaseName.get() +" Got this WebDriver Exception: "+throwable.getMessage().toString());
					System.out.println("Current Test Class Name: "+CommonVariables.CurrentTestClassName.get()+" Current Test Case Name: "+CommonVariables.CurrentTestCaseName.get() +" Got this WebDriver Exception: "+throwable.getMessage().toString());
				}
				catch(Exception e){			}
			}
			if(DebugMode.equals("True")){
				System.out.println("Got this WebDriver Exception: " + throwable.getMessage().toString()); 
			}
		}
		catch(WebDriverException e){
			try{Thread.sleep(10000);}catch(InterruptedException interr){}
			CommonVariables.CommonDriver.set(null);
		}
		catch(Exception ex){
			try{Thread.sleep(5000);}catch(InterruptedException interr){}
			CommonVariables.CommonDriver.set(null);
		}

	}

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		if(DebugMode.equals("True")){
			System.out.println("Changed value of  '" + objCommonfunclib.getElementXPath(arg0) + "' webelement on '"+CommonVariables.getDriver().getTitle()+" page."); 
		}
		objCommonfunclib.AddToLog("CurrentTestClassLog", "info", "Changed value of  '" + objCommonfunclib.getElementXPath(arg0) + "' webelement on '"+CommonVariables.getDriver().getTitle()+" page." );
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		if(DebugMode.equals("True")){
			System.out.println("Clicked '" + objCommonfunclib.getElementXPath(arg0) + "' webelement on '"+CommonVariables.getDriver().getTitle()+" page."); 
		}
		objCommonfunclib.AddToLog("CurrentTestClassLog", "info", "Clicked '" + objCommonfunclib.getElementXPath(arg0) + "' webelement on '"+CommonVariables.getDriver().getTitle()+" page." );
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver arg1) {
		// TODO Auto-generated method stub
		if(DebugMode.equals("True")){
			System.out.println("Navigated to URL: " + url);
		}
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		if(DebugMode.equals("True")){
			System.out.println("Trying to change value of  '" + objCommonfunclib.getElementXPath(arg0) + "' webelement on '"+CommonVariables.getDriver().getTitle()+" page."); 
		}
		objCommonfunclib.AddToLog("CurrentTestClassLog", "info", "Trying to change value of  '" + objCommonfunclib.getElementXPath(arg0) + "' webelement on '"+CommonVariables.getDriver().getTitle()+" page." );
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		if(DebugMode.equals("True")){
			System.out.println("Trying to Click '" + objCommonfunclib.getElementXPath(arg0) + "' webelement on '"+CommonVariables.getDriver().getTitle()+" page."); 
		}
		objCommonfunclib.AddToLog("CurrentTestClassLog", "info", "Trying to Click '" + objCommonfunclib.getElementXPath(arg0) + "' webelement on '"+CommonVariables.getDriver().getTitle()+" page." );
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		if(DebugMode.equals("True")){
			System.out.println("Trying to run the javascript: " + script); 
		}
		
	}

}
