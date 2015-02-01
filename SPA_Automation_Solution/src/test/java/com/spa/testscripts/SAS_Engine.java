package com.spa.testscripts;

import io.appium.java_client.AppiumDriver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.spa.util.CommonFunctionLib;
import com.spa.util.CommonVariables;
import com.spa.util.CustomLog4JLevel;
import com.spa.util.DetailedLogs;
import com.spa.util.HTMLReporting;
import com.spa.util.SystemUtilities;

public class SAS_Engine {
	AppiumDriver driver;
	CommonFunctionLib objCommonFunc;
	SystemUtilities systemUtilities;
	DateFormat dateFormat;
	public static long threadCount = 1;
	Calendar startTime;
	Calendar endTime;
	public int itr_cnt=1;
	Properties prop;
  
	  @BeforeTest(alwaysRun = true)
	  public void beforeSuite(ITestContext context) throws IOException{
		  prop = new Properties();
		  String hostname = InetAddress.getLocalHost().getHostName();
		  CommonVariables.MachineHostName.set(hostname);
		  try{Thread.sleep(1000*threadCount);threadCount = threadCount+1;}catch(InterruptedException ex){}
		  CommonVariables.DL.set(new DetailedLogs());
		  CommonVariables.ResultSheet.set(new HashMap<String, String>());
		  CommonVariables.TestMethodDescriptions.set(new HashMap<String, String>());
		  CommonVariables.CommonDriver.set(null);
		  CommonVariables.CurrentTestCaseName.set("");
		  CommonVariables.TotalTCCount.set(new Integer(0));
		  CommonVariables.PassTCCount.set(new Integer(0));
		  CommonVariables.FailTCCount.set(new Integer(0));
		  CommonVariables.SkipTCCount.set(new Integer(0));
		  CommonVariables.TestCasessHighLevelLog.set(new ArrayList<String>());
		  CommonVariables.ScenariosHighLevelLog.set(new ArrayList<String>());
		  CommonVariables.LastMethodName.set("");
		  CommonVariables.CurrentTestClassResult.set("PASS");
		  CommonVariables.LastMethodName.set("");
		  String DeviceName = System.getenv("DeviceName");
		  String DeviceEnvironment = System.getenv("DeviceEnvironment");
		  CommonVariables.ExecutionDate.set(GetCurrentTime().split(" ")[0]);
		  CommonVariables.ExecutionStartTime.set(GetCurrentTime());
		  String DeviceType = "UnknownDevice";
		  FileReader reader = new FileReader("master_config.properties");
		  prop.load(reader);
		  if(DeviceName != null){
			  CommonVariables.DeviceName.set(DeviceName);
		  }
		  else if( context.getCurrentXmlTest().getParameter("DeviceType")!=null){
			  CommonVariables.DeviceName.set(context.getCurrentXmlTest().getParameter("DeviceType"));
		  }
		  else{
			  CommonVariables.DeviceName.set(prop.getProperty("DeviceType"));
		  }
		  System.out.println("Running on Device: "+CommonVariables.DeviceName.get());
		  DeviceType = CommonVariables.DeviceName.get();
		  if(DeviceEnvironment != null){
			  CommonVariables.PlatformName.set(DeviceEnvironment);
		  }
		  else if( context.getCurrentXmlTest().getParameter("Platform")!=null){
			  CommonVariables.PlatformName.set(context.getCurrentXmlTest().getParameter("Platform"));
		  }
		  else{
			  CommonVariables.PlatformName.set(prop.getProperty("Platform"));
		  }
		  System.out.println("Running on Platform: "+CommonVariables.PlatformName.get());
		  if( context.getCurrentXmlTest().getParameter("SeleniumGrid")!=null){
			  CommonVariables.IsGridExecution.set(context.getCurrentXmlTest().getParameter("SeleniumGrid").trim().equalsIgnoreCase("true"));
		  }
		  else{
			  CommonVariables.IsGridExecution.set(prop.getProperty("SeleniumGrid").trim().equalsIgnoreCase("true"));
		  }
		  System.out.println("Running on Grid: "+CommonVariables.IsGridExecution.get());
		  if(DeviceEnvironment == null){
			  CommonVariables.PlatformName.set(prop.getProperty("Platform"));
		  }else{
			  CommonVariables.PlatformName.set(DeviceEnvironment);
		  }
		  dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  startTime = Calendar.getInstance();
		  System.out.println(dateFormat.format(startTime.getTime()));
		  CommonVariables.DL.set(new DetailedLogs());

		  String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_sss_a").format(new Date());
		  String Report_Root_Path = System.getProperty("user.dir")+"/logs/Results/" + timestamp;
		  CommonVariables.DL.get().CreateFolder(Report_Root_Path);
		  Report_Root_Path = System.getProperty("user.dir")+"/logs/Results/" + timestamp + "/" + DeviceType + "_" + Thread.currentThread().getId();
		  CommonVariables.DL.get().CreateFolder(Report_Root_Path);
	
		  CommonVariables.RootResultFolderPath.set(Report_Root_Path);

		  //		  System.out.println(DeviceType + " result path: " + CommonVariables.RootResultFolderPath);

		  CommonVariables.CurrentGlobalLog.set(CommonVariables.DL.get().StartLogs("Global_Log",Report_Root_Path));
		  CommonVariables.CurrentGlobalLog.get().info("Staring the Suite");	
		  systemUtilities = new SystemUtilities();
		  File highlevellogfile = new File(Report_Root_Path + "/" + "HighLevelLog" + ".log");
		  FileWriter highlevellogfw = new FileWriter(highlevellogfile.getAbsoluteFile());
		  CommonVariables.HighLevelLog.set(new BufferedWriter(highlevellogfw));
	  }

	  
	  @BeforeClass(alwaysRun = true)
	  public void beforeClass() throws IOException
	  {
		  CommonVariables.CurrentTestClassResult.set("PASS");
		  objCommonFunc = new CommonFunctionLib(CommonVariables.CommonDriver.get());
		  String completeclassName = this.getClass().getName();//HomePageValidation
		  
		  String[] sArr = completeclassName.split("\\.");
		  int last_array_item_index = sArr.length - 1;
		  String className = sArr[last_array_item_index];
		  String classLogName = className + "_" + Thread.currentThread().getId();
		  CommonVariables.CurrentTestClassName.set(className);		//HomePageValidation
		  
		  String Scenario_Report_Folder = CommonVariables.RootResultFolderPath.get() + "/" + className;
		  CommonVariables.DL.get().CreateFolder(Scenario_Report_Folder);
		  CommonVariables.ScenarioResultFolderPath.set(Scenario_Report_Folder);
		  
		  String TestCases_Report_Folder = CommonVariables.ScenarioResultFolderPath.get() + "/" + "TestCases";
		  CommonVariables.DL.get().CreateFolder(TestCases_Report_Folder);
		  
		  String TestCases_ScreenShot_Folder = CommonVariables.ScenarioResultFolderPath.get() + "/" + "ScreenShots";
		  CommonVariables.DL.get().CreateFolder(TestCases_ScreenShot_Folder);
		  
		  CommonVariables.CurrentTestClassLog.set(CommonVariables.DL.get().StartLogs(classLogName,Scenario_Report_Folder));
		  CommonVariables.CurrentTestClassLog.get().info("Staring the class");
		  CommonVariables.CurrentTestClassLog.get().debug("In Before Class method inside BaseTestCase");	
		  CommonVariables.CurrentTestCaseResult.set("");
		  CommonVariables.setCommonFunctionLib();
		  objCommonFunc = CommonVariables.getCommonFunctionLib();
		  CommonVariables.CommonDriver.set(null);
	  }
	  
	  @BeforeMethod(alwaysRun = true)
	  public void beforeTestCase(Method method) throws IOException{
		  String testName = method.getName(); 							//VerifyHomePgModuleNameandOrder
			String testClassName = CommonVariables.CurrentTestClassName.get();
			String testcaseLogName = testClassName + "-" + testName + "_" + Thread.currentThread().getId();
		  if(CommonVariables.CurrentTestCaseName.get().equals(testName)){
			  if(CommonVariables.CurrentTestCaseLog.get() != null){	
				  CommonVariables.TestCase_Data_Iterator.set(CommonVariables.TestCase_Data_Iterator.get() + 1);
//				  objCommonFunc.AddToLog("info", "***************************   Dataprovider Iteration No: " +  CommonVariables.TestCase_Data_Iterator.get() + " :************************************");
				  CommonVariables.CurrentTestCaseLog.get().info("***************************   Dataprovider Iteration No: " +  CommonVariables.TestCase_Data_Iterator.get() + " :************************************");
//				  CommonVariables.CurrentTestCaseLog.info("Staring the test method");
			  }else{
				  CommonVariables.DataProviderIterator.set(1);
				  CommonVariables.CurrentTestCaseName.set(testName);
				  CommonVariables.TestCase_Data_Iterator.set(CommonVariables.TestCase_Data_Iterator.get() + 1);
				  CommonVariables.CurrentTestCaseLog.set(CommonVariables.DL.get().StartLogs(testcaseLogName,CommonVariables.ScenarioResultFolderPath.get() + "/" + "TestCases"));
//				  objCommonFunc.AddToLog("info", "***************************   Dataprovider Iteration No: " +  CommonVariables.TestCase_Data_Iterator.get() + " :************************************");
				  CommonVariables.CurrentTestCaseLog.get().info("***************************   Dataprovider Iteration No: " +  CommonVariables.TestCase_Data_Iterator.get() + " :************************************");
//				  CommonVariables.CurrentTestCaseLog.info("Staring the test method");
			  }
		  }else{
			  CommonVariables.CurrentTestCaseName.set(testName);
			  CommonVariables.TestCase_Data_Iterator.set(1);
			  CommonVariables.CurrentTestCaseLog.set(CommonVariables.DL.get().StartLogs(testcaseLogName,CommonVariables.ScenarioResultFolderPath.get() + "/" + "TestCases"));
			  CommonVariables.CurrentTestCaseLog.get().info("***************************   Dataprovider Iteration No: " +  CommonVariables.TestCase_Data_Iterator.get() + " :************************************");
		  }
		  CommonVariables.TCStartTime.set(GetCurrentTime());
		  System.out.println("Executng the testcase: " + testName);
	  }
	  
	  @AfterMethod(alwaysRun = true)
	  public void afterTestCase(Method method, ITestResult result){
			//*** code to get the Method Description ****************************************************************
			String MethodDescription = "";
			java.lang.annotation.Annotation[] annotations = method.getAnnotations();
			for(java.lang.annotation.Annotation annotation : annotations){
				if(annotation instanceof org.testng.annotations.Test){
					org.testng.annotations.Test myAnnotation = (org.testng.annotations.Test) annotation;
					MethodDescription = myAnnotation.description().toString().replace(",",";");
					//				  System.out.println("description: " + MethodDescription);
				}
			}
			//***************************************** *************************************************************
			String testName = method.getName();
			Integer testResult=result.getStatus();
			CommonVariables.CurrentTestCaseName.set(testName);
			String DataParameters = "";
			try{
				String[] TestCasePArametersArray = (String[]) result.getParameters()[0];
				DataParameters = Arrays.toString(TestCasePArametersArray);
			}catch(java.lang.ClassCastException e){
				try{
					Object[] arrDataObj= (Object[]) result.getParameters()[0];
					for(Object obj: arrDataObj ){
						DataParameters  =DataParameters+obj.toString()+"||";
					}
				}
				catch(java.lang.ClassCastException e1){
					DataParameters = Arrays.toString(result.getParameters());
				}
			}catch(Exception e){
				DataParameters = Arrays.toString(result.getParameters());
				//			  e.printStackTrace();
			}
			if(DataParameters.equals("[]"))
				DataParameters ="";
			CommonVariables.TotalTCCount.set(CommonVariables.TotalTCCount.get() + 1);
			CommonVariables.TCEndTime.set(GetCurrentTime());
			String completeclassName = this.getClass().getName();
			String testcasename = CommonVariables.CurrentTestCaseName.get();
			String CompleteTCName = completeclassName + "." + testcasename;
			String testCases_ScreenShot_Folder = CommonVariables.ScenarioResultFolderPath.get() + "/" + "ScreenShots";
			String TestCaseResult ="";
			if (result.getStatus() == ITestResult.FAILURE) {
				TestCaseResult = "FAIL";
				CommonVariables.CurrentTestClassResult.set("FAIL");
				//			  CommonVariables.CurrentTestCaseResult = "FAIL";
				String ScreenShotPath = testCases_ScreenShot_Folder+ "/" + testcasename + "_" + new Date().getTime() + ".jpg";
				if(CommonVariables.getDriver()!=null)
					if(!objCommonFunc.saveScreenshot(ScreenShotPath)){
						ScreenShotPath = "Error Occurred while taking screenshot";
					}
				//			  objCommonFunc.AddToLog("screenshot", ScreenShotPath);
				CommonVariables.CurrentTestCaseLog.get().log(CustomLog4JLevel.SCREENSHOT, ScreenShotPath);
				CommonVariables.FailTCCount.set(CommonVariables.FailTCCount.get()+1);
			}else if (result.getStatus() == ITestResult.SUCCESS){
				TestCaseResult = "PASS";
				CommonVariables.PassTCCount.set(CommonVariables.PassTCCount.get() + 1);
			}else if (result.getStatus() == ITestResult.SKIP){
				TestCaseResult = "SKIP";
				CommonVariables.SkipTCCount.set(CommonVariables.SkipTCCount.get() + 1);
			}else{
				TestCaseResult = "UNKNOWN";
			}
			String TestDescriptionKey = CommonVariables.CurrentTestClassName.get() + "-" + CommonVariables.CurrentTestCaseName.get();
			if(CommonVariables.LastMethodName.get().equals(testName)){
				itr_cnt++;
			}else{
				itr_cnt = 1;
			}
			String NewCompleteTCName = CompleteTCName + ">Itr"+itr_cnt;
			System.out.println(CommonVariables.ResultSheet.get());
			CommonVariables.ResultSheet.get().put(NewCompleteTCName, CompleteTCName + "," + itr_cnt + "," + TestCaseResult + ","  + CommonVariables.PlatformName.get() + "-" + CommonVariables.DeviceName.get() + "," + MethodDescription);
			CommonVariables.TestMethodDescriptions.get().put(TestDescriptionKey, MethodDescription);
			CommonVariables.CurrentTestCaseResult.set(TestCaseResult);		  
			CommonVariables.TestCasessHighLevelLog.get().add(CommonVariables.CurrentTestCaseName.get() + ":" + TestCaseResult + ":" + DataParameters);
			CommonVariables.LastMethodName.set(testName);
			//		  CommonVariables.CurrentTestCaseLog.info("Stopping the test method");

			try {
				CommonVariables.HighLevelLog.get().write(CommonVariables.CurrentTestClassName.get() + "\t"+CommonVariables.CurrentTestCaseName.get() + "\t"+TestCaseResult+"\t"+DataParameters+"\t"+CommonVariables.TCStartTime.get() + "\t"+CommonVariables.TCEndTime.get()+"\t"+"-"+"\t"+"-"+"\t"+"-"+"\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	  @AfterClass(alwaysRun = true)
	  public void ClassCleanUp()
	  {
		  CommonVariables.CurrentTestClassLog.get().info("Stopping the class");
		  if(CommonVariables.CurrentTestClassResult.get().equalsIgnoreCase("PASS")){
			  CommonVariables.ScenariosHighLevelLog.get().add(CommonVariables.CurrentTestClassName.get() + ": PASS");
		  }else{
			  CommonVariables.ScenariosHighLevelLog.get().add(CommonVariables.CurrentTestClassName.get() + ": FAIL");
		  }
		  
//		  DetailedLogs.APP_LOGS.debug("In After Class");	
		  try
		  {Thread.sleep(1000);
		  }catch(InterruptedException e){}
		objCommonFunc.ShutDownDriver();
		System.out.println(CommonVariables.TestCasessHighLevelLog.get().toString());
		CommonVariables.TestCasessHighLevelLog.get().clear();
		
		String ReportPath = CommonVariables.ScenarioResultFolderPath.get() + "/TestCases/";
		File dir = new File(ReportPath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File file : directoryListing) {
				if(!file.isDirectory()){
					if(FilenameUtils.getExtension(file.getPath()).contains("log")){
						HTMLReporting htmlreporting = new HTMLReporting();
						htmlreporting.ConvertLogToHtml(ReportPath, FilenameUtils.removeExtension(file.getName().toString()));			  
					}
				}

				// Do something with child
			}
		} else {}
		
	  }

	  @AfterTest (alwaysRun = true)
	  public void afterTest(){
		  if(driver != null){
			  try{
				  driver.close();
				  driver.quit();
			  }catch(Exception e){
				  
			  }
		  }
		  endTime = Calendar.getInstance();
		  System.out.println(dateFormat.format(endTime.getTime()));
		  CommonVariables.CurrentGlobalLog.get().info("Stopping the Suite");	
		  System.out.println(CommonVariables.ScenariosHighLevelLog.get().toString());
//		  CommonVariables.ScenariosHighLevelLog.clear();
		  try {
			CommonVariables.HighLevelLog.get().close();
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		  CommonVariables.ExecutionEndTime.set(GetCurrentTime());
		  HTMLReporting htmlreporting = new HTMLReporting();
		  boolean flag = htmlreporting.ConvertHighLevelLogToHtml(CommonVariables.RootResultFolderPath.get(), "HighLevelLog");

		  // code to send Email Report
		  if (prop.getProperty("EmailReport").toLowerCase().trim().contains("true")){
			  
		  }
	  }
	  
	  public String GetCurrentTime(){
		  DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
		  String currenttime = df.format(Calendar.getInstance().getTime());
		  return currenttime;
	  }
}
