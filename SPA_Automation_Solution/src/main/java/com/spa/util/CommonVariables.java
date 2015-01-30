package com.spa.util;



import io.appium.java_client.AppiumDriver;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class CommonVariables {
	public static ThreadLocal<DetailedLogs> DL = new ThreadLocal<DetailedLogs>();
	public static ThreadLocal<AppiumDriver> CommonDriver = new ThreadLocal<AppiumDriver>();
	public static ThreadLocal<CommonFunctionLib> objComFuncLib = new ThreadLocal<CommonFunctionLib>();
	public static ThreadLocal<String> CurrentTestCaseName = new ThreadLocal<String>();
	public static ThreadLocal<String> CurrentTestClassName = new ThreadLocal<String>();
	public static ThreadLocal<String> CurrentTestClassResult = new ThreadLocal<String>();
	public static ThreadLocal<Map<String, String>> TestMethodDescriptions = new ThreadLocal<Map<String, String>>();
	public static ThreadLocal<Logger> CurrentTestCaseLog = new ThreadLocal<Logger>();
	public static ThreadLocal<String> CurrentTestCaseResult = new ThreadLocal<String>();
	public static ThreadLocal<Logger> CurrentTestClassLog = new ThreadLocal<Logger>();
	public static ThreadLocal<Logger> CurrentGlobalLog = new ThreadLocal<Logger>();
	public static ThreadLocal<Logger> CurrentSiteCoreLog = new ThreadLocal<Logger>();
	public static ThreadLocal<BufferedWriter> HighLevelLog = new ThreadLocal<BufferedWriter>();
	public static ThreadLocal<String> RootResultFolderPath = new ThreadLocal<String>();
	public static ThreadLocal<String> ScenarioResultFolderPath = new ThreadLocal<String>();
	public static ThreadLocal<String> TCResultFolderPath = new ThreadLocal<String>();
	public static ThreadLocal<String> CurrentTCLogPath = new ThreadLocal<String>();
	public static ThreadLocal<Integer> TestCase_Data_Iterator = new ThreadLocal<Integer>();
	public static ThreadLocal<Map<String, String>> ResultSheet = new ThreadLocal<Map<String, String>>();
	public static ThreadLocal<ArrayList<String>> ScenariosHighLevelLog = new ThreadLocal<ArrayList<String>>();
	public static ThreadLocal<ArrayList<String>> TestCasessHighLevelLog = new ThreadLocal<ArrayList<String>>();
	
	public static ThreadLocal<String> DeviceName = new ThreadLocal<String>();
	public static ThreadLocal<String> PlatformName = new ThreadLocal<String>();
	public static ThreadLocal<Integer> DataProviderIterator = new ThreadLocal<Integer>();
	
	public static ThreadLocal<String> TCStartTime = new ThreadLocal<String>();
	public static ThreadLocal<String> TCEndTime = new ThreadLocal<String>();
	public static ThreadLocal<String> ExecutionDate = new ThreadLocal<String>();
	public static ThreadLocal<String> ExecutionStartTime = new ThreadLocal<String>();
	public static ThreadLocal<String> ExecutionEndTime = new ThreadLocal<String>();
	public static ThreadLocal<Integer> TotalTCCount = new ThreadLocal<Integer>();
	public static ThreadLocal<Integer> PassTCCount = new ThreadLocal<Integer>();
	public static ThreadLocal<Integer> FailTCCount = new ThreadLocal<Integer>();
	public static ThreadLocal<Integer> SkipTCCount = new ThreadLocal<Integer>();
	public static ThreadLocal<String> GlobalHTMLReportPath = new ThreadLocal<String>();
	public static ThreadLocal<String> DebugMode = new ThreadLocal<String>();
	public static ThreadLocal<String> test_device = new ThreadLocal<String>();
	public static ThreadLocal<String> test_Environment = new ThreadLocal<String>();
	public static ThreadLocal<Boolean> IsGridExecution= new ThreadLocal<Boolean>();
	public static ThreadLocal<Map<String, String>> UserDetail= new ThreadLocal<Map<String, String>>();
	public static ThreadLocal<String> EmailReport = new ThreadLocal<String>();
	public static ThreadLocal<String> LastMethodName = new ThreadLocal<String>();
	public static ThreadLocal<String> MachineHostName = new ThreadLocal<String>();
	public static ThreadLocal<String> SeleniumGridNodeIP = new ThreadLocal<String>();
	public static String testNGGroupNames = "";
	
	public static void setCommonFunctionLib(){
		if(getCommonFunctionLib()==null)
		objComFuncLib.set(new CommonFunctionLib(CommonDriver.get()));
	}

	public static CommonFunctionLib getCommonFunctionLib(){
		return objComFuncLib.get();
	}
	
	public static void setDriver(AppiumDriver driver){
		CommonDriver.set(driver);
	}

	public static AppiumDriver getDriver(){
		return CommonDriver.get();
	}

}
