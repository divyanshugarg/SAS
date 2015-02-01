package com.spa.pages;

import com.spa.objectrepository.ScanIntro_OR;

public class ScanIntro extends BasePage {
	public ScanIntro()
	{
		if(objCommonFunc.FindElement(ScanIntro_OR.Gotit,5)==null){
			throw new IllegalStateException("This is not the ScanIntro screen.");
		}
	}
	public void ScanIntroGotItClick()
	{
		objCommonFunc.WebDriverClick(objCommonFunc.FindElement(ScanIntro_OR.Gotit, 5));
	}
}
