package com.spa.util;

import java.util.List;

public class sringFunctions extends DetailedLogs{
	public sringFunctions(){
		super();
	}
	
	public String ConvertStringArrayListToString(List<String> list)
	{
		try{
			String listString = "";
			for (String s : list)
			{
			    listString += s + "\t";
			}
			return listString;
		}catch(Exception e){
			AddToLog("CurrentTestCaseLog", "error", e.getLocalizedMessage());
			return "";
		}
	}

}
