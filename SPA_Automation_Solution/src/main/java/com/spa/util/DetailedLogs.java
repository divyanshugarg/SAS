package com.spa.util;
/**
 * @author divyanshu.garg
 */
import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;


public class DetailedLogs {
	public static Logger APP_LOGS=null;
	public void StartLogs() throws IOException{
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmsss'.log'").format(new Date());
		System.setProperty("logfile.name",System.getProperty("user.dir")+"/logs/TestLog.log");
//		APP_LOGS = Logger.getLogger("devpinoyLogger");
//		APP_LOGS = Logger.getLogger("TestLog");
		APP_LOGS = Logger.getLogger("DetailedLogs");
//		SimpleLayout layout = new SimpleLayout(); 
		PatternLayout layout = new PatternLayout("%d{dd/MM/yyyy HH:mm:ss} %-5p %c %x - %m%n");
//		ConsoleAppender a = (ConsoleAppender) Logger.getRootLogger().getAppender("stdout");
//		a.setLayout(new PatternLayout("%d{HH:mm:ss}  %-5.5p  %t %m%n")); 
		FileAppender appender = new FileAppender(layout,System.getProperty("user.dir")+"/logs/DetailedLog_"+ timestamp,false);
		APP_LOGS.addAppender(appender);
	}
	public Logger StartLogs(String LogName, String location) throws IOException{
		try{
//			String timestamp = new SimpleDateFormat("yyyyMMddhhmmsss'.log'").format(new Date());
//			System.setProperty("logfile.name",System.getProperty("user.dir")+"\\logs\\TestLog.log");
//			APP_LOGS = Logger.getLogger("devpinoyLogger");
//			APP_LOGS = Logger.getLogger("TestLog");
			Logger logger = Logger.getLogger(LogName);
//			SimpleLayout layout = new SimpleLayout(); 
//			PatternLayout layout = new PatternLayout("%d{dd/MM/yyyy HH:mm:ss}\t%-5p\t%c\t%x\t-\t%m%n");
			PatternLayout layout = new PatternLayout("%d{dd/MM/yyyy HH:mm:ss}\t%-5p\t%c\t%x\t%m%n");
//			ConsoleAppender a = (ConsoleAppender) Logger.getRootLogger().getAppender("stdout");
//			a.setLayout(new PatternLayout("%d{HH:mm:ss}  %-5.5p  %t %m%n")); 
//			FileAppender appender = new FileAppender(layout,System.getProperty("user.dir")+"\\logs\\DetailedLog_"+ timestamp,false);
			CommonVariables.CurrentTCLogPath.set("");
			FileAppender appender = new FileAppender(layout,location.trim() + "/" + LogName + ".log",false);
			CommonVariables.CurrentTCLogPath.set(location + "/" + LogName + ".log");
			logger.addAppender(appender);
			return logger;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void CreateFolder(String location){
//		String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_sss").format(new Date());
//		String Report_Root_Path = System.getProperty("user.dir")+"\\logs\\Results\\" + timestamp;
		new File(location).mkdir();

	}

	public void AddToLog(String LogName, String LogLevel, String LogMessage){

		if(LogName =="CurrentTestClassLog" && CommonVariables.CurrentTestClassLog.get()!=null)
		{
			logCurrentTestClassLogs(LogLevel, LogMessage);
		}else if(LogName =="CurrentGlobalLog" && CommonVariables.CurrentGlobalLog.get()!=null)
		{
			logCurrentGlobalLogs(LogLevel, LogMessage);
		}else if(LogName =="CurrentTestCaseLog" && CommonVariables.CurrentTestCaseLog.get()!=null)
		{
			logCurrentTestCaseLogs(LogLevel, LogMessage);
		}

	}

	public void AddToLog(LogName logName, String LogLevel, String LogMessage){
		switch (logName) {
		case CurrentTestClassLog:
			if(CommonVariables.CurrentTestClassLog.get()!=null)
			{
				logCurrentTestClassLogs(LogLevel, LogMessage);
			}
		case CurrentGlobalLog:
			if(CommonVariables.CurrentGlobalLog.get()!=null)
			{
				logCurrentGlobalLogs(LogLevel, LogMessage);
			}

		case CurrentTestCaseLog:
			if(CommonVariables.CurrentTestCaseLog.get()!=null)
			{
				logCurrentTestCaseLogs(LogLevel, LogMessage);
			}
		default:
			break;
		}	
	}

	public void logCurrentTestCaseLogs(String LogLevel, String LogMessage) {
		if(LogLevel.equalsIgnoreCase("debug")){
			CommonVariables.CurrentTestCaseLog.get().debug(LogMessage);
		}else if(LogLevel.equalsIgnoreCase("info")){
			CommonVariables.CurrentTestCaseLog.get().info(LogMessage);
		}else if(LogLevel.equalsIgnoreCase("error")){
			CommonVariables.CurrentTestCaseLog.get().error(LogMessage);
			try{
				String ScreenShotPath = CommonVariables.ScenarioResultFolderPath.get() + "/" + "ScreenShots"+ "/" + CommonVariables.CurrentTestCaseName.get() + "_" + new Date().getTime() + ".jpg";
				if(CommonVariables.getDriver()!=null)
					if(!this.saveScreenshot(ScreenShotPath)){
						ScreenShotPath = "Error Occurred while taking screenshot";
					}
				CommonVariables.CurrentTestCaseLog.get().log(CustomLog4JLevel.SCREENSHOT, ScreenShotPath);
			}
			catch(Exception e){
			}
		}else if(LogLevel.equalsIgnoreCase("screenshot")){
			if(LogMessage==null || LogMessage.isEmpty()){
				LogMessage = CommonVariables.ScenarioResultFolderPath.get() + "/" + "ScreenShots"+ "/" + CommonVariables.CurrentTestCaseName.get() + "_" + new Date().getTime() + ".jpg";
			}
			saveScreenshot(LogMessage);
			CommonVariables.CurrentTestCaseLog.get().log(CustomLog4JLevel.SCREENSHOT, LogMessage);
		}else if(LogLevel.equalsIgnoreCase("pass")){
			CommonVariables.CurrentTestCaseLog.get().log(CustomLog4JLevel_Pass.PASS, LogMessage);
		}
	}

	public void logCurrentGlobalLogs(String LogLevel, String LogMessage) {
		if(LogLevel.equalsIgnoreCase("debug")){
			CommonVariables.CurrentGlobalLog.get().debug(LogMessage);
		}else if(LogLevel.equalsIgnoreCase("info")){
			CommonVariables.CurrentGlobalLog.get().info(LogMessage);
		}else if(LogLevel.equalsIgnoreCase("error")){
			CommonVariables.CurrentGlobalLog.get().error(LogMessage);
		}else if(LogLevel.equalsIgnoreCase("pass")){
			CommonVariables.CurrentTestCaseLog.get().log(CustomLog4JLevel_Pass.PASS, LogMessage);
		}
	}

	public void logCurrentTestClassLogs(String LogLevel, String LogMessage) {
		if (LogLevel.equalsIgnoreCase("debug")) {
			CommonVariables.CurrentTestClassLog.get().debug(LogMessage);
		} else if (LogLevel.equalsIgnoreCase("info")) {
			CommonVariables.CurrentTestClassLog.get().info(LogMessage);
		} else if (LogLevel.equalsIgnoreCase("error")) {
			CommonVariables.CurrentTestClassLog.get().error(LogMessage);
		} else if (LogLevel.equalsIgnoreCase("screenshot")) {
			CommonVariables.CurrentTestClassLog.get().log(CustomLog4JLevel.SCREENSHOT, LogMessage);
		}else if(LogLevel.equalsIgnoreCase("pass")){
			CommonVariables.CurrentTestCaseLog.get().log(CustomLog4JLevel_Pass.PASS, LogMessage);
		}
	}

	public boolean saveScreenshot(String ImgPath){
		try{			
			if(CommonVariables.getDriver() != null){
				File screenshot = null;
				if(CommonVariables.IsGridExecution.get()!=null & CommonVariables.IsGridExecution.get()){
					try {
						org.openqa.selenium.WebDriver augmentedDriver = new Augmenter().augment(CommonVariables.getDriver());
						screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
					} catch (WebDriverException e) {
						WebDriver augmentedDriver = (AppiumDriver)(new Augmenter().augment(CommonVariables.getDriver()));
						screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
					}
				}
				else if(CommonVariables.getDriver().getClass().toString().toLowerCase().contains("chromedriver")
						||CommonVariables.getDriver().getClass().toString().toLowerCase().contains("safari")
						||CommonVariables.getDriver().getClass().toString().toLowerCase().contains("firefox")
						||CommonVariables.getDriver().getClass().toString().toLowerCase().contains("internet")
						||CommonVariables.getDriver().getClass().toString().toLowerCase().contains("ie")
						||CommonVariables.getDriver().getClass().toString().toLowerCase().contains("remotewebdriver")){
					screenshot = ((TakesScreenshot) CommonVariables.getDriver()).getScreenshotAs(OutputType.FILE);
				}
				File screenshotfile = new File(ImgPath);
				try {
					FileUtils.copyFile(screenshot, screenshotfile);
					if(CommonVariables.getDriver().getClass().toString().toLowerCase().contains("internet")
							||CommonVariables.getDriver().getClass().toString().toLowerCase().contains("ie")){
						Thread.sleep(1500);
					}
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			else{
				return false;
			}
		}
		catch(Exception ex){
			return false;
		}
	}
}
