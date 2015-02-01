/**
 * 
 */
package com.spa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.spa.objectrepository.StateChange_OR;
import com.spa.objectrepository.LotteryDetailsUnderAState_OR;
import com.spa.util.LogName;

/**
 * @author divyanshu.garg
 *
 */
public class LotteryDetailsUnderAState extends BasePage {

	public LotteryDetailsUnderAState()
	{
		objCommonFunc.waitforElementVisible(LotteryDetailsUnderAState_OR.lotteryList, 5);
		if(!objCommonFunc.IsElementVisible(LotteryDetailsUnderAState_OR.lotteryList)){
			throw new IllegalStateException("This is not the Home screen.");
		}
	}
	
	public ArrayList<Map<String, String>> getAllLotteryDetail(){
		try{
			ArrayList<Map<String, String>> lotteryDetail = new ArrayList<Map<String, String>>();
			List<WebElement> lotterypriceObjs = objCommonFunc.FindElements(LotteryDetailsUnderAState_OR.lotteryPrice, 5);
			List<WebElement> lotteryNameObjs = objCommonFunc.FindElements(LotteryDetailsUnderAState_OR.lotteryName, 2);
			List<WebElement> lotteryDateObjs = objCommonFunc.FindElements(LotteryDetailsUnderAState_OR.lotteryDate, 2);
			
			for(Integer i=0;i<lotteryDateObjs.size();i++){
				String lotteryprice = objCommonFunc.GetElementAttributeValue(lotterypriceObjs.get(i), "text");
				String lotteryName = objCommonFunc.GetElementAttributeValue(lotteryNameObjs.get(i), "text");
				String lotteryDate = objCommonFunc.GetElementAttributeValue(lotteryDateObjs.get(i), "text");
				Map<String, String> lotteryInfo = new HashMap<String, String>();
				lotteryInfo.put("lotteryPrice", lotteryprice);
				lotteryInfo.put("lotteryName", lotteryName);
				lotteryInfo.put("lotteryDate", lotteryDate);
				lotteryDetail.add(lotteryInfo);
			}
			return lotteryDetail;
		}
		catch(Exception ex){
			ex.printStackTrace();
			objCommonFunc.AddToLog(LogName.CurrentTestCaseLog, "error", "Failed to get lottery details. Either there is any change in Xpath or lottery list is not display on page.");
			return null;
		}
	}
}
