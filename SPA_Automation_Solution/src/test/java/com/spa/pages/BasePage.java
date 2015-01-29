package com.spa.pages;



import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.spa.util.CommonFunctionLib;
import com.spa.util.CommonVariables;

public class BasePage {
	
	CommonFunctionLib objComFuncLib;
	Properties properties;
	
	public BasePage(){
		objComFuncLib = CommonVariables.getCommonFunctionLib();
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
