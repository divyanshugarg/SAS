package com.spa.pages;



import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.spa.util.CommonFunctionLib;
import com.spa.util.CommonVariables;
import com.spa.util.SystemUtilities;

public class BasePage {
	
	CommonFunctionLib objCommonFunc;
	SystemUtilities systemUtilities;
	Properties properties;
	
	public BasePage(){
		objCommonFunc = CommonVariables.getCommonFunctionLib();
		systemUtilities = new SystemUtilities();
		properties = new Properties();
		try {
			FileReader reader = new FileReader("master_config.properties");
			properties.load(reader);
		} 
		catch (IOException e) {
			System.out.println("Failed to load Properties file");
		}
	}

}
