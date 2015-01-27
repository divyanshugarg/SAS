package com.spa.util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Divyanshu Garg
 *
 */
public class CommonFunctionLib extends DetailedLogs {
	public AppiumDriver driver;
	//public AndroidDriver abc;
	RemoteWebDriver rwd;
	WebDriver wd;
	String iOSAppPath;
	WebDriverWait wait;
	Properties properties; 
	DesiredCapabilities objCapabilities;
	/**
	 * These objects are made non-static. The objects of this class are re-created at some point of time.
	 */
	public Boolean doFullReset;
	FileWriter fw;
	PrintWriter pw;

	//Constructor with WebDriver argument
	public CommonFunctionLib(AppiumDriver driver)
	{
		this.driver = driver;
		initPropertiesFile();
		doFullReset = true;
	}
	//Constructor with no argument
	public CommonFunctionLib()
	{

	}

	private void initPropertiesFile(){

		properties = new Properties();
		try {
			FileReader reader = new FileReader("master_config.properties");
			properties.load(reader);
		} 
		catch (IOException e) {
			System.out.println("Failed to load Properties file");
		}
	}

	public void WebdriverWaitForPage(String time)
	{
		driver.manage().timeouts().implicitlyWait(Long.parseLong(time), TimeUnit.SECONDS);
	}

	public void WebdriverWaitForPage()
	{
		WebdriverWaitForPage(properties.getProperty("globalTimeOut"));
	}

	public AppiumDriver StartAppiumDriver(String browserType)
	{
		try
		{
			iOSAppPath = properties.getProperty("AppName");
			File appDir = new File(System.getProperty("user.dir")+ "\\aut");
			File path = new File(appDir,iOSAppPath.trim());
			ShutDownDriver();
			/**  
			 * Refer the below link to know about different capabilities of Appium server.
			 * http://appium.io/slate/en/v1.3.4/?java#appium-server-capabilities
			 */
			switch (browserType.trim())
			{
			case "Windows-Android-Simulator":
				createStartAndroidEmulator();
//				startAppium();
				objCapabilities = new DesiredCapabilities();
				objCapabilities.setCapability(MobileCapabilityType.VERSION, properties.getProperty("AndroidVersion")); //18
			    objCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			    objCapabilities.setCapability("app",path);
			    objCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");  //Android Emulator
			   // objCapabilities.setCapability("automationName", "Appium");
				objCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "android");
				objCapabilities.setCapability("session-override", true);
				objCapabilities.setCapability("autoLaunch", true);
				objCapabilities.setCapability("language", "en");
				objCapabilities.setCapability("fullReset", "true");
				objCapabilities.setCapability("locale", "US");
				objCapabilities.setCapability("takesScreenshot", true);
				objCapabilities.setCapability("deviceReadyTimeout", "300");  //Timeout in seconds while waiting for device to become ready
				objCapabilities.setCapability("androidDeviceReadyTimeout", "300"); //Timeout in seconds used to wait for a device to become ready after booting
				objCapabilities.setCapability("avdLaunchTimeout", "300000"); //How long to wait in milliseconds for an avd to launch and connect to ADB (default 120000)
				objCapabilities.setCapability("newCommandTimeout", "18000");
				objCapabilities.setCapability("session-override", true);
				objCapabilities.setCapability("device", "@default");
				objCapabilities.setCapability("avd", "myAndroidEmulator");  //Name of avd to launch
//				objCapabilities.setCapability("appPackage", "com.yoolotto.android");
//				objCapabilities.setCapability("appActivity", "activities.MainActivity");
//				objCapabilities.setCapability("appWaitActivity", "");
//				objCapabilities.setCapability("appWaitPackage", "com.mobli");
//				objCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				try {   
					driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), objCapabilities);
					//driver = new AndroidDriver(new URL("http://" + properties.getProperty("machineIP") + ":" + properties.getProperty("PortNumber") + "/wd/hub"), objCapabilities);

					break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				} 
			default:
				AddToLog(LogName.CurrentTestCaseLog, "error", "None of the DeviceType Case Matched");
				AddToLog("CurrentTestClassLog", "error", "None of the DeviceType Case Matched");
				AddToLog("CurrentTestCaseLog", "error", "None of the DeviceType Case Matched");
				System.out.println("None of the DeviceType Case Matched");
				return null;
			}

			return driver;
		}
		catch (Exception e)
		{
			AddToLog("CurrentTestClassLog", "error", "Failed to started driver: '" +browserType + "'. Error Type: "+e.getClass());
			AddToLog("CurrentTestCaseLog", "error", "Failed to started driver: '" +browserType + "'. Error Type: "+e.getClass());
			return null;
		}
	}

	public Boolean startAppium(){
        String line = null;
		 try 
         { 
			 String TASKLIST = "tasklist /FI \"STATUS eq running\" /FI \"IMAGENAME eq appium.exe\"";
             Process p=Runtime.getRuntime().exec(TASKLIST); 
             //p.waitFor(); 
             BufferedReader reader=new BufferedReader(
                 new InputStreamReader(p.getInputStream())
             ); 
             while((line = reader.readLine()) != null) 
             { 
            	 if(line.toLowerCase().contains("appium.exe")){
            		 return true;
            	 }
             }
       //      System.out.println("ANDROID_HOME : "+System.getenv("ANDROID_HOME"));
         //    System.out.println("PATH : "+System.getenv("PATH"));
             DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
             DefaultExecutor executor = new DefaultExecutor();
             executor.setExitValue(1);
             CommandLine command = new CommandLine("appium.exe");
             //command.addArgument("appium", false);
             command.addArgument("–address", false);
             command.addArgument("127.0.0.1");
             command.addArgument("-port", false);
             command.addArgument("4723");
             command.addArgument("-bootstrap-port", false);
             command.addArgument("4724");
             command.addArgument("-full-reset", false);
             command.addArgument("-local-timezone", false);
             command.addArgument("-avd", false);
             command.addArgument("myAndroidEmulator");
             command.addArgument("-log", false);
             command.addArgument("-pre-launch", false);
             command.addArgument("-session-override", false);
             executor.execute(command, resultHandler);
             Thread.sleep(10000);
/*			 TASKLIST = "cmd /c appium";
             p=Runtime.getRuntime().exec(TASKLIST); 
             Thread.sleep(3000);*/
             for(int i=0;i<10;i++){
            	 TASKLIST = "tasklist /FI \"STATUS eq running\" /FI \"IMAGENAME eq appium.exe\"";
            	 p=Runtime.getRuntime().exec(TASKLIST); 
            	 //p.waitFor(); 
            	 reader=new BufferedReader(
            			 new InputStreamReader(p.getInputStream())
            			 ); 
            	 while((line = reader.readLine()) != null) 
            	 { 
            		 if(line.toLowerCase().contains("appium.exe")){
            			 return true;
            		 }
            	 }
            	 Thread.sleep(1500);
             }
         }
         catch(IOException e1) {
        	 e1.printStackTrace();
         } 
         catch(Exception e2) {
        	 e2.printStackTrace();} 
		 return false;
	}
	
	/**
	 * To Do : Need to work on it.
	 * @return
	 */
	public Boolean createStartAndroidEmulator(){
		String line = null;
		try 
		{ 
			String command = "adb shell getprop ro.build.version.release";
			Process p=Runtime.getRuntime().exec(command); 
			Thread.sleep(2000);
			BufferedReader error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
			boolean isAlreadyRunning = false;
			if((line = error.readLine()) != null){
				error.close();
			}
			else{
				while ((line = reader.readLine()) != null){
					System.out.println(line);
					if(line.toLowerCase().contains(properties.getProperty("AndroidVersion"))){
						isAlreadyRunning = true;
						break;
					}
				}
			}
			if(!isAlreadyRunning){
				command = "cmd /c android create avd -n myAndroidEmulator -t 19 --force";
				p=Runtime.getRuntime().exec(command); 
				Thread.sleep(2000);
				reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
				error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
				if(reader!=null && reader.ready() && error!=null && error.readLine()== null) 
				{ 
					command = "cmd /c emulator -avd myAndroidEmulator -gpu on";
					p=Runtime.getRuntime().exec(command); 
					Thread.sleep(15000);
					boolean isEmulatorStarted = false;
					for(int i=0;i<10;i++){
						command = "cmd /c adb devices";
						p=Runtime.getRuntime().exec(command); 
						Thread.sleep(1500);
						reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
						while ((line = reader.readLine()) != null){
							//System.out.println(line);
							if(line.toLowerCase().contains("device")
									&& !line.toLowerCase().contains("list of devices")){
								isEmulatorStarted = true;
								break;
							}
						}
						if(isEmulatorStarted){
							break;
						}
						else{
							Thread.sleep(1500);
						}
					}
					if(isEmulatorStarted) 
					{ 
						Thread.sleep(15000);
						command = "adb shell getprop ro.build.version.release";
						p=Runtime.getRuntime().exec(command); 
						Thread.sleep(1500);
						reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
						error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
						while((line = reader.readLine()) != null){
							System.out.println("Attached Emulator Info: "+line);
							if(line.toLowerCase().contains(properties.getProperty("AndroidVersion"))){
								return true; 
							}
						}
					}
					else{
						System.out.println("failed to start Android Emulator.");
						return false;
					}
				} 
				else{
					System.out.println("failed to create Android Emulator.");
					return false;
				}
			}
			else{
				System.out.println("Skip to create and start new emulator because we found similar Android device (version) attached with running environment.");
				return true;
			}
		}
		catch(IOException e1) {
			e1.printStackTrace();
		} 
		catch(InterruptedException e2) {} 
		return false;
	}
	
	public int getIdBasedOnAndroidAndAPILevel(String version){
		try 
		{ 
			String line = "";
			String command = "cmd /c android list target";
			Process p=Runtime.getRuntime().exec(command); 
			Thread.sleep(2000);
			BufferedReader error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
			BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream()));
			String id = "";
			while((line = reader.readLine()) != null){
				if(line.toLowerCase().contains("id:")){
					id = line.split(" ")[1];
				}
				if(line.toLowerCase().contains(properties.getProperty("AndroidVersion"))
						&& (line.toLowerCase().contains("name:")||line.toLowerCase().contains("api level:") )){
					return Integer.parseInt(id); 
				}
			}
		}
		catch(Exception ex){
			System.out.println("Failed to get Android target Id based on provided API or Android version.");
		}
		return 0;
	}
	
	public void ShutDownDriver()
	{
		if (driver!=null) {
			try
			{
				try {Thread.sleep(2500);} catch (InterruptedException e1) {}
				driver.closeApp();
				driver.quit();
			}
			catch(WebDriverException e){
				try {Thread.sleep(2500);} catch (InterruptedException e1) {}	
			}
		}

	}

	public  WebElement FindElement(MobileLocator LocatorType, String LocatorString ,int timeoutInSeconds )
	{
		WebElement webElement = null;
		WebDriverWait wait;
		try
		{
			switch (LocatorType)
			{
			case ByAccessibilityId:  //a string corresponding to a recursive element search using the Id/Name that the native Accessibility options utilize.
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = wait.until(
							ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(LocatorString)));                     	
					webElement = driver.findElement(MobileBy.AccessibilityId(LocatorString));
				}
				break;
			case ByXPath:
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = wait.until(
							ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(LocatorString)));  
				}else{
					webElement = driver.findElement(MobileBy.xpath(LocatorString));
				}
				break;
			case ByClassName:
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = wait.until(
							ExpectedConditions.visibilityOfElementLocated(MobileBy.className(LocatorString)));  
				}else{
					webElement = driver.findElement(MobileBy.className(LocatorString));
				}
				break;
			case ByIosUIAutomation: //a string corresponding to a recursive element search using the UIAutomation library (iOS-only)
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = wait.until(
							ExpectedConditions.visibilityOfElementLocated(MobileBy.IosUIAutomation(LocatorString)));  
				}else{
					webElement = driver.findElement(MobileBy.IosUIAutomation(LocatorString));
				}
				break;
			case ByAndroidUIAutomator: //a string corresponding to a recursive element search using the UiAutomator Api (Android-only)
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = wait.until(
							ExpectedConditions.visibilityOfElementLocated(MobileBy.AndroidUIAutomator(LocatorString)));  
				}else{
					webElement = driver.findElement(MobileBy.IosUIAutomation(LocatorString));
				}
				break;
			default:
				return null;
			}
			return webElement;
		}
		catch(Exception e){
			AddToLog("CurrentTestCaseLog", "error", "Failed to find element: '" + LocatorString + "'. Error Type: "+e.getClass());
			return null;
		}	
	}

	public boolean SendKeys(WebElement webElement, String value){
		boolean state = false;
		try{
			webElement.clear();
			webElement.sendKeys(value);
			state = true;  		
		}catch(Exception e){
			AddToLog("CurrentTestCaseLog", "info", "Error occurred while sendkeys on WebElement: " + e.getClass());
		}
		return state;

	}

	public boolean WebDriverClick (WebElement webElement)
	{
		boolean state = false;
		try
		{
			webElement.click();
			try
			{
				Thread.sleep(2000);
				state  =true;
			}
			catch(InterruptedException e){}
		}
		catch(Exception e)
		{
			AddToLog("CurrentTestCaseLog", "info", "Error occurred while clicking on WebElement: " + e.getClass());
		}
		return state;
	}

	public String GetElementAttributeValue(WebElement objWebElement, String attribute)
	{
		try
		{
			AddToLog("CurrentTestCaseLog", "info", "Info: Get '"+attribute+"' Attribute value for '"+getElementXPath(objWebElement)+"' object ");
			return objWebElement.getAttribute(attribute);
		}
		catch(org.openqa.selenium.NoSuchElementException e)
		{
			AddToLog("CurrentTestCaseLog", "info", "Error: caught 'ElementNotFoundException' exception. Failed to get '"+ attribute +"' value for '"+objWebElement+"' on '"+driver.getTitle()+"' page");
			return "";
		}
		catch(ElementNotVisibleException e)
		{
			AddToLog("CurrentTestCaseLog", "info", "Error: caught 'ElementNotVisibleException' exception. Failed to get '"+ attribute +"' value for '"+objWebElement+"' on '"+driver.getTitle()+"' page");
			return "";
		}
		catch(WebDriverException e)
		{
			AddToLog("CurrentTestCaseLog", "info", "Error: caught 'WebDriverException' exception. Failed to get '"+ attribute +"' value for '"+objWebElement+"' on '"+driver.getTitle()+"' page");
			return "";
		}
		catch(NullPointerException e5)
		{
			AddToLog("CurrentTestCaseLog", "info", "Info. Caught 'NullPointerException' exception while try to get Element Attribute ("+attribute+") value on '"+driver.getTitle()+"'.");
			return "";
		}
		catch(Exception e)
		{
			System.out.println("Failed to find object ("+objWebElement+") property'"+attribute+"' value.");
			AddToLog("CurrentTestCaseLog", "info", "Failed to get '"+ attribute +"' value. Error Message: "+e.getMessage());
			return "";
		}
	}

	public void Scroll(String Direction){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", Direction);
		js.executeScript("mobile: scroll", scrollObject);
	}

	public void ChangeOrientation(String Orientation){   // Valid values are: "LANDSCAPELEFT" , "LANDSCAPERIGHT" , "PORTRAIT"
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//       String script = "target.setDeviceOrientation(UIA_DEVICE_ORIENTATION_" + Orientation + ");";
		//        js.executeScript(script);
		js.executeScript("target.setDeviceOrientation(UIA_DEVICE_ORIENTATION_LANDSCAPERIGHT);");
	}

	public boolean saveScreenshot(String ImgPath){
		boolean flag = true;
		try{			
			File screenshot = null;;
			//	            org.openqa.selenium.WebDriver augmentedDriver = new Augmenter().augment(driver);
			WebDriver augmentedDriver = (AppiumDriver)(new Augmenter().augment(driver));
			screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

			File screenshotfile = new File(ImgPath);
			FileUtils.copyFile(screenshot, screenshotfile);
		}catch(Exception e){
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}


	public boolean IsElementVisible(final By by) {
		try{
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			if((driver.findElement(by).getSize().height==0) && (driver.findElement(by).getSize().width==0)){
				return false;
			}else{
				return true;
			}
		}catch(Exception e1){
			return false;
		}
	}

	//Function will return an hashmap with the driverinfo.
	//@author: Divyanshu Garg
	//Usage: System.out.println(GetDriverInfo().get("DriverType") + System.out.println(GetDriverInfo().get("DriverName"));
	public Map<String, String> GetDriverInfo(){
		Map<String,String> DriverInfo = new HashMap<String,String>();
		WebDriver driver = null;
		if(this.wd!=null){
			driver = this.wd;
		}else{
			driver = this.rwd;
		}

		try{
			String DriverType = "";
			String DriverName = "";
			if(driver.getClass().toString().toLowerCase().contains("remotewebdriver")){
				Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
				try{
					DriverName = caps.getCapability("device").toString();
				}catch(NullPointerException e){ //to handle Android Chrome case
					String browsername = caps.getCapability("browserName").toString();
					if(browsername.equals("chrome")){
						DriverName = "androidchrome";
					}
				}

				if(DriverName.toLowerCase().contains("chrome")){
					DriverType = "Mobile";
				}else if(DriverName.toLowerCase().contains("ipad")){
					DriverType = "Tablet";
				}else{
					DriverType = "Mobile";
				}
			}
			else if(driver.getClass().toString().toLowerCase().contains("chrome")){
				DriverType = "Desktop";
				DriverName = "Chrome";
			}
			else if(driver.getClass().toString().toLowerCase().contains("safari")){
				DriverType = "Desktop";
				DriverName = "Safari";
			}
			else if(driver.getClass().toString().toLowerCase().contains("firefox")){
				DriverType = "Desktop";
				DriverName = "Firefox";
			}
			DriverInfo.put("DriverType", DriverType);
			DriverInfo.put("DriverName", DriverName);
			return DriverInfo;
		}catch(Exception e){
			String DriverType = "Mobile";
			String DriverName = "Android";
			DriverInfo.put("DriverType", DriverType);
			DriverInfo.put("DriverName", DriverName);
			return DriverInfo;
			//    		return null;
		}
	}

	public void ScrollToTop(){
		try{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,0);");
		}catch(Exception e){

		}

	}

	public void ScrollToBottom(){
		try{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,document.documentElement.scrollHeight);");
		}catch(Exception e){

		}
	}

	public String getElementXPath(WebElement element) {
		try{
			String str = element.toString().split("->")[1].trim();
			String str2 = str.split("xpath: ")[1].trim();
			str = str2.substring(0, str2.length()-1);
			return str;}
		catch(Exception e){
			return "<failed to retrive xpath>";
		}
	}

	//Function to Send Email Report
	public void sendEmailReport(String from, String pass, String[] to, String subject, String htmlreportpath) {
		//		File reader = new File("D:/Projects/Equinox/Automation/Java/New/logs/Results/2014_04_25_07_14_027_PM/iPad-Simulator/HighLevelLog.html");
		BufferedReader reader = null;
		//		htmlreportpath = "D:/Projects/Equinox/Automation/Java/New/logs/Results/2014_04_25_07_14_027_PM/iPad-Simulator/HighLevelLog.html";
		try {
			reader = new BufferedReader(
					new FileReader(htmlreportpath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer fileData = new StringBuffer();
		char[] buf = new char[2048];
		int numRead=0;
		try {
			while((numRead=reader.read(buf)) != -1){
				String readData = String.valueOf(buf, 0, numRead);
				fileData.append(readData);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		String body = fileData.toString();

		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for( int i = 0; i < to.length; i++ ) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for( int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			// to send a normal test message
			//            message.setText(body);
			// to send the html content body
			message.setContent(body, "text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		catch (AddressException ae) {
			ae.printStackTrace();
			System.out.println(ae.getMessage());
		}
		catch (MessagingException me) {
			me.printStackTrace();
			System.out.println(me.getMessage());
		}
	}

	public void SetDriver(AppiumDriver driver){
		this.driver = driver;
	}

	public AppiumDriver SwitchToWebView(AppiumDriver driver){
		try{
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
				System.out.println(contextNames); //prints out something like NATIVE_APP \n WEBVIEW_1
			}
			driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1
			this.driver = driver;
			return driver;
		}catch(Exception e){
			AddToLog("CurrentTestCaseLog", "error", e.getLocalizedMessage());
			return null;
		}
	}

	public AppiumDriver SwichToNativeView(AppiumDriver driver){
		try{
			driver.context("NATIVE_APP");
			this.driver = driver;
			return driver;
		}catch(Exception e){
			AddToLog("CurrentTestCaseLog", "error", e.getLocalizedMessage());
			return null;
		}
	}

	public List<WebElement> FindElements(MobileLocator LocatorType, String LocatorString ,int timeoutInSeconds )
	{
		List<WebElement> webElement;
		WebDriverWait wait;
		try
		{
			switch (LocatorType)
			{
			case ByAccessibilityId:
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = wait.until(
							ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.AccessibilityId(LocatorString)));                     	

				}else{
					webElement = driver.findElements(MobileBy.AccessibilityId(LocatorString));
				}
				break;
			case ByXPath:
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = driver.findElements(MobileBy.xpath(LocatorString));

				}else{
					webElement = driver.findElements(MobileBy.xpath(LocatorString));
				}
				break;
			case ByClassName:
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = driver.findElements(MobileBy.className(LocatorString));

				}else{
					webElement = driver.findElements(MobileBy.className(LocatorString));
				}
				break;
			case ByIosUIAutomation:
				if (timeoutInSeconds > 0)
				{
					wait = new WebDriverWait(driver,timeoutInSeconds);
					webElement = wait.until(
							ExpectedConditions.visibilityOfAllElementsLocatedBy(MobileBy.IosUIAutomation(LocatorString)));  

				}else{
					webElement = driver.findElements(MobileBy.IosUIAutomation(LocatorString));
				}
				break;
			default:
				return null;

			}
			return webElement;
		}
		catch(Exception e){
			AddToLog("CurrentTestCaseLog", "error", "Failed to find element: '" + LocatorString + "'. Error Type: "+e.getClass());
			return null;
		}	
	}

	/**
	 * Remove app, if already installed. 
	 * @param appName App Name. E.g. com.equinox.lotto 
	 */
	public void removeApp(String appName){
		try{
		if(driver.isAppInstalled("com.equinox.Equinox")){
			driver.removeApp("com.equinox.Equinox");
		}
		}
		catch(Exception ex){
			
		}
	}
	
	/**
	 * Reset app.
	 * @param appName App Name. E.g. com.equinox.lotto 
	 */
	public void resetApp(String appName){
		try{
			
		}
		catch(Exception ex){
			
		}
	}
}

