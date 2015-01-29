package com.spa.pages;

import com.spa.objectrepository.ScanIntro_OR;

public class ScanIntro extends BasePage {
	public ScanIntro()
	{
		if(objComFuncLib.FindElement(ScanIntro_OR.Gotit,5)==null){
			throw new IllegalStateException("This is not the ScanIntro screen.");
		}
	}
	public void ScanIntroGotItClick()
	{
		objComFuncLib.WebDriverClick(objComFuncLib.FindElement(ScanIntro_OR.Gotit, 5));
	}
}
