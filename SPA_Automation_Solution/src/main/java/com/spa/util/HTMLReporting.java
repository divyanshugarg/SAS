package com.spa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.text.TabExpander;

public class HTMLReporting {
	static String webPageTitle="Title";
	static String htmlreportFileName="";
	static String logreportFileName="";
	static String reportpath;
	
	static String highlevelhtmlreportFileName="";
	static String highlevellogreportFileName="";
	static String highlevelreportpath="";
	
	static String TestCaseHeader="Test Case Header Goes Here";
	static String ColumnHeader1="TimeStamp";
	static String ColumnHeader2="Log_Level";
	static String ColumnHeader3="TestCase";
	static String ColumnHeader4="Message";
	
	static String lastTCName="NA";
	static int TCIterationCtr = 0;
	
	String logFilePath;
	
//	public static void main(String args[]) throws IOException{
//		String Reportpath = "D:/Projects/Equinox/Automation/Java/New/logs/Results/2014_04_22_07_16_057/iPhone-Simulator/dummyTestCase/TestCases/";
//		String ReportName = "dummy_TC01";
//		HTMLReporting formatter = new HTMLReporting();
//		formatter.ConvertLogToHtml(Reportpath,ReportName);
//	}
	public boolean ConvertHighLevelLogToHtml(String Reportpath, String ReportName){
		highlevellogreportFileName = ReportName + ".log";
		highlevelhtmlreportFileName = ReportName  + ".html";
		highlevelreportpath = Reportpath;
		CommonVariables.GlobalHTMLReportPath.set(highlevelreportpath + "/" + highlevelhtmlreportFileName);
		try {
			this.creatHighLevelReportFile();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	
	private void creatHighLevelReportFile() throws IOException {
		File file = new File( highlevelreportpath + "/" + highlevelhtmlreportFileName);
		if(file==null){
			System.out.println("Html file cannot be created. Exit!");
			return;
		}
		FileOutputStream reportOutputStream=new FileOutputStream(file);
		
		boolean flag=createNewHighLevelReportFile(reportOutputStream);
		if(!flag)
			return;
		readHighLevelLogFileAndCreateReport(highlevellogreportFileName,reportOutputStream);
	}
	
	
	
	private void readHighLevelLogFileAndCreateReport(String logFileName, FileOutputStream reportOutputStream) {
		
		
		try {
			File fileInput = new File(highlevelreportpath+"/"+logFileName);
			
			if(fileInput!=null && !fileInput.exists()){
				System.out.println("Path of Log File is not set.");
				return;
			}
			FileReader fileReader = new FileReader(fileInput);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				processLineHighLevel(line,reportOutputStream);
//				System.out.println(line);
			}
			reportOutputStream.write("</table></body></html>".getBytes());
			fileReader.close();
			reportOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void processLineHighLevel(String line, FileOutputStream outputStream) throws IOException{
		String parts[]=line.split("\\t");
		if(!lastTCName.equals(parts[1])){
			TCIterationCtr = 1;
		}else if(lastTCName.equals(parts[1])){
			TCIterationCtr++;
		}
		String tableRow="";
//		System.out.println(parts.length);
		String color = ""; //74DF00
		if(parts[2].toUpperCase().trim().equals("PASS")){
			color = "74DF00";
		}else if(parts[2].toUpperCase().trim().equals("FAIL")){
			color = "FF0000";
		}else if(parts[2].toUpperCase().trim().equals("SKIP")){
			color = "F7FE2E";
		}
		//D:\Projects\Equinox\Automation\Java\New\logs\Results\2014_04_23_04_20_026_PM\iPhone-Simulator\dummyTestCase\TestCases\dummy_TC01.html
		String TestClassName = parts[0].toString();
		String TChtmlLogPath = CommonVariables.RootResultFolderPath.get() + "/" + parts[0].trim() + "/TestCases/" + TestClassName + "-" + parts[1].trim()+ "_" + Thread.currentThread().getId() + ".html#section" + String.valueOf(TCIterationCtr);
		String DeviceName = CommonVariables.DeviceName.get();
		String tempStr = TChtmlLogPath.split(DeviceName)[1];
		TChtmlLogPath = "../" + DeviceName + tempStr;
		tableRow=String.format("<tr bgcolor=\"" + color + "\">"
				+ "<td style=\"width:200px;\">%s</td>"
				+ "<td style=\"width:200px;\"><a href=\"" + TChtmlLogPath + "\">" +
				"%s" +
				"</a></td>"
				+ "<td style=\"width:100px;\">%s</td>"
				+ "<td style=\"width:500px;\">%s</td>"
				+ "<td style=\"width:200px;\">%s</td>"
				+ "<td style=\"width:200px;\">%s</td>"
				+ "<td style=\"width:200px;\">%s</td>"
				+ "<td style=\"width:100px;\">%s</td>"
				+ "<td style=\"width:150px;\">%s</td>"
				+ "</tr>",parts[0], parts[1], parts[2], parts[3], parts[4],parts[5],parts[6].replace("||", ", "),parts[7].replace("||", ", "),parts[8].replace("||", ", "));

		
//		if(parts[1].toUpperCase().trim().equals("ERROR")){
//			tableRow=String.format("<tr bgcolor=\"#FF0000\">"
//					+ "<td style=\"width:100px;\">%s</td>"
//					+ "<td style=\"width:100px;\">%s</td>"
//					+ "<td style=\"width:100px;\">%s</td>"
//					+ "<td style=\"width:1000px;\">%s</td>"
//					+ "</tr>",parts[0], parts[1], parts[2], parts[4],parts[5]);
//		}else if((parts[1].toUpperCase().trim().equals("INFO"))||(parts[1].toUpperCase().trim().equals("DEBUG"))){
//			tableRow=String.format("<tr>"
//					+ "<td style=\"width:100px;\">%s</td>"
//					+ "<td style=\"width:100px;\">%s</td>"
//					+ "<td style=\"width:100px;\">%s</td>"
//					+ "<td style=\"width:1000px;\">%s</td>"
//					+ "</tr>",parts[0], parts[1], parts[2], parts[4]);
//		}else if(parts[1].toUpperCase().trim().equals("SCREENSHOT")){
//			tableRow=String.format("<tr bgcolor=\"#FFA500\">"
//					+ "<td style=\"width:100px;\">%s</td>"
//					+ "<td style=\"width:100px;\"><a href=\"" + parts[4] + "\">"
//					+ "%s"
//					+ "</a></td>"
//					+ "<td style=\"width:100px;\">%s</td>"
//					+ "<td style=\"width:1000px;\">%s</td>"
//					+ "</tr>",parts[0], parts[1], parts[2], parts[4]);
//		}
		
		outputStream.write(tableRow.getBytes());
		outputStream.flush();
		lastTCName = parts[1];
	}
	private boolean createNewHighLevelReportFile(FileOutputStream output) throws IOException{
		
		StringBuilder tableFormat=new StringBuilder();
		

		//Decide the Title of the Web Page
		tableFormat.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"
				+ "<html><title>"+"HighLevelLog"+"</title></head><body>");
		tableFormat.append("<TABLE style=\"BACKGROUND-COLOR: #e2e7de; FONT-SIZE: 12px\" " +
				"border=0 cellSpacing=1cellPadding=2 width=\"100%\"><TBODY><TR bgColor=#c1ccb8><TH " +
				"style=\"TEXT-ALIGN: left; FONT-SIZE: 20px; FONT-WEIGHT: bold\"colSpan=2>" +
				"Execution Summary : SAS - High Level Execution Report" +
				"</TH></TR><TR bgColor=#a1ccb8><TD width=\"45%\" scope=row>" +
				"Passed TestCases" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.PassTCCount.get()) +
				"</TD></TR><TR bgColor=#a1ccb8><TD>" +
				"Failed TestCases" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.FailTCCount.get()) +
				"</TD></TR><TR bgColor=#a1ccb8><TD>" +
				"Skipped TestCases" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.SkipTCCount.get()) +
				"</TD></TR><TR bgColor=#a1ccb8><TD>" +
				"ExecutionDate" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.ExecutionDate.get()) +
				"</TD></TR><TR bgColor=#a1ccb8><TD>" +
				"StartTime" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.ExecutionStartTime.get()) +
				"</TD></TR><TR bgColor=#a1ccb8><TD>" +
				"EndTime" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.ExecutionEndTime.get()) +
				"</TD></TR><TR bgColor=#a1ccb8><TD>" +
				"Device Name" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.PlatformName.get()) + " : " + String.valueOf(CommonVariables.DeviceName.get()) +
				"</TD></TR><TR bgColor=#a1ccb8><TD>" +
				"Environment" +
				"</TD><TD style=\"PADDING-LEFT: 10px\"><B>" +
				String.valueOf("QA") +
				"</B></TD></TR>"
				+ "<TR bgColor=#a1ccb8><TD>" +
				"Machine Host Name" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.MachineHostName.get()) +
				"</TD></TR>"
				+ "<TR bgColor=#a1ccb8><TD>" +
				"Grid Node IP" +
				"</TD><TD style=\"PADDING-LEFT: 10px\">" +
				String.valueOf(CommonVariables.SeleniumGridNodeIP.get()) +
				"</TD></TR>"
				);


		//Header Content Goes Here	
		tableFormat.append("<table border=\"1\">"
				+ "<thead>"
				+ "<tr bgColor=#c1ccb8>"
				+ "<th style=\"width:100px;\">"+"Test Scenario"+"</th>"
				+ "<th style=\"width:100px;\">"+"Test Case"+"</th>"
				+ "<th style=\"width:100px;\">"+"Status"+"</th>"
				+ "<th style=\"width:100px;\">"+"TestCase Data Parameters"+"</th>"
				+ "<th style=\"width:100px;\">"+"Start Time"+"</th>"
				+ "<th style=\"width:100px;\">"+"End Time"+"</th>"
				+ "<th style=\"width:100px;\">"+"TestCase Id"+"</th>"
				+ "<th style=\"width:100px;\">"+"Script Writter"+"</th>"
				+ "<th style=\"width:100px;\">"+"TestCase Type"+"</th>"
				+ "</tr></thead>");

		output.write(tableFormat.toString().getBytes());
		output.flush();
		return true;
		
	}
	
	//**********************************************************************************************************************************//
	
	public void ConvertLogToHtml(String Reportpath, String ReportName){
		htmlreportFileName = ReportName + ".html";
		logreportFileName = ReportName + ".log";
		reportpath = Reportpath;
		TestCaseHeader = ReportName;
		try {
			this.creatReportFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void creatReportFile() throws IOException {
		logFilePath = reportpath;
		File file=new File( logFilePath+"/"+htmlreportFileName);
		if(file==null){
			System.out.println("Html file cannot be created. Exit!");
			return;
		}
		FileOutputStream reportOutputStream=new FileOutputStream(file);
		
		boolean flag=createNewReportFile(reportOutputStream);
		if(!flag)
			return;
		readLogFileAndCreateReport(logreportFileName,reportOutputStream);
	}

	private void readLogFileAndCreateReport(String logFileName, FileOutputStream reportOutputStream) {
		
		
		try {
			File fileInput = new File(logFilePath+"/"+logFileName);
			
			if(fileInput!=null && !fileInput.exists()){
				System.out.println("Path of Log File is not set.");
				return;
			}
			FileReader fileReader = new FileReader(fileInput);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				processLine(line,reportOutputStream);
//				System.out.println(line);
			}
			reportOutputStream.write("</table></body></html>".getBytes());
			fileReader.close();
			reportOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void processLine(String line, FileOutputStream outputStream) throws IOException{
		String parts[]=line.split("\\t");
		if(parts.length>4){
		String tableRow="";
//		System.out.println(parts.length);
		if(parts[1].toUpperCase().trim().equals("ERROR")){
			tableRow=String.format("<tr bgcolor=\"#FF0000\">"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:1000px;\">%s</td>"
					+ "</tr>",parts[0], parts[1], parts[2], parts[4]);
		}else if(((parts[1].toUpperCase().trim().equals("INFO"))||(parts[1].toUpperCase().trim().equals("DEBUG"))) && !(parts[4].toUpperCase().trim().contains("DATAPROVIDER ITERATION")) ){
			tableRow=String.format("<tr>"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:1000px;\">%s</td>"
					+ "</tr>",parts[0], parts[1], parts[2], parts[4]);
		}else if(parts[1].toUpperCase().trim().equals("PASS")){
			tableRow=String.format("<tr bgcolor=\"#74DF00\">"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:1000px;\">%s</td>"
					+ "</tr>",parts[0], parts[1], parts[2], parts[4]);
		}else if(parts[1].toUpperCase().trim().equals("SCREENSHOT")){
			String SS_Absolute_Path = parts[4];
			String tempStr  = SS_Absolute_Path;
			if(SS_Absolute_Path.split("ScreenShots").length>1)
				tempStr = SS_Absolute_Path.split("ScreenShots")[1];
			String SS_Relative_Path = "../" + "ScreenShots" + tempStr;
			tableRow=String.format("<tr bgcolor=\"#FFA500\">"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:100px;\"><a href=\"" + SS_Relative_Path + "\">"
					+ "%s"
					+ "</a></td>"
					+ "<td style=\"width:100px;\">%s</td>"
					+ "<td style=\"width:1000px;\">%s</td>"
					+ "</tr>",parts[0], parts[1], parts[2], parts[4]);
		}else if(((parts[1].toUpperCase().trim().equals("INFO"))||(parts[1].toUpperCase().trim().equals("DEBUG"))) && (parts[4].toUpperCase().trim().contains("DATAPROVIDER ITERATION"))){
			String strarr[] = parts[4].split(":");
			String ctr = strarr[1].trim();
			tableRow=String.format("<tr bgcolor=\"#BDBDBD\">"
					+ "<td "
					+ "<span style=\"font-weight:bold;height:50px\"colspan=\"4\"align=\"center\">"
					+ "<a name=\"section" + ctr + "\">"
					+ "%s"
//					"style=\"width:1000px;\"colspan=\"4\"align=\"center\"\"height:500px\"\"font-size: 22pt\">%s</td>"
					+ "</span></tr>",parts[4]);
		}
		
		outputStream.write(tableRow.getBytes());
		outputStream.flush();
		}
	}
	private boolean createNewReportFile(FileOutputStream output) throws IOException{
		String TCName = "";
		String tempStr = TestCaseHeader.split("-")[1];
//		String tempStr2 = tempStr1.substring(0, TC_length-3);
		String Arr[] = tempStr.split("_");
		int arrLen = Arr.length;
		
		if(arrLen>=3){
			for(int i=0;i<arrLen-1;i++){
				if(!TCName.equals("")){
					TCName = TCName + "_";
				}	
				TCName = TCName + Arr[i];
			}
		}else{
			TCName = tempStr.split("_")[0];
		}
		String TestDescriptionKey = CommonVariables.CurrentTestClassName.get() + "-" + TCName;
		StringBuilder tableFormat=new StringBuilder();

		//Decide the Title of the Web Page,
		tableFormat.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">"
				+ "<html><title>"+webPageTitle+"</title></head><body>");

		tableFormat.append("<font face=\'Tahoma'size=\'2\'><h1>Test Results  for: " + TCName + " </h1>");
		tableFormat.append("<font face=\'Tahoma'size=\'2\'><h1>Test Description: " + CommonVariables.TestMethodDescriptions.get().get(TestDescriptionKey) + " </h1>");

		//Header Content Goes Here
		tableFormat.append("<table border=\"1\">"
				+ "<thead>"
				+ "<tr bgColor=#c1ccb8>"
				+ "<th style=\"width:100px;\">"+ColumnHeader1+"</th>"
				+ "<th style=\"width:100px;\">"+ColumnHeader2+"</th>"
				+ "<th style=\"width:100px;\">"+ColumnHeader3+"</th>"
				+ "<th style=\"width:100px;\">"+ColumnHeader4+"</th>"
				+ "</tr></thead>");

		output.write(tableFormat.toString().getBytes());
		output.flush();
		return true;
		
	}
}