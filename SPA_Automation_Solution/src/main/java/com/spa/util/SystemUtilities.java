package com.spa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SystemUtilities extends DetailedLogs{
	public SystemUtilities(){
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

	public Boolean isItemPresenceInTheList(ArrayList<Map<String, String>> allLotteryInfo, Map<String,String> searchValue)
	{
		try{
			for(Map<String,String> eachElementInList : allLotteryInfo){
				Set<String> keyNames = searchValue.keySet();
				boolean isMatch = true;
				for(String keyValue : keyNames){
					try{
						if(!eachElementInList.get(keyValue).toLowerCase().contains(searchValue.get(keyValue).toLowerCase())){
							isMatch = false;
							break;
						}
					}
					catch(Exception ex){
						isMatch = false;
						break;
					}
				}
				if(isMatch){
					return true;
				}
			}
			return false;
		}catch(Exception e){
			AddToLog("CurrentTestCaseLog", "error", "Getting exception when finding an element in list. Exception message: "+e.getLocalizedMessage());
			return false;
		}
	}
}
