package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Program extends LearningModule {
	private String SessionIDs = "", SessionTitles = "", SessionStatus = "", SubModules="";
	
	public void setSessionID_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("SID");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setSessionTitle_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("SESSIONTITLE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setSessionStatus_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("SSTATUS");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setSubModules_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			String[] keywords = str.split(";");
			WebDriverUtils.searchSelect_Selector(driver, keywords);
		}
	}
	
	public void runCreate(WebDriver driver){
		super.runCreate(driver);
		
		//setup session
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		By by = By.linkText("Session Properties");
		WebDriverUtils.clickLink(driver, by);

		by = By.id("ADD_REORDER_SESSION_IMG");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
	    this.setSessionID_UI(driver, this.getSessionIDs());

	    by = By.cssSelector("input.topbutton");
	    WebDriverUtils.clickButton(driver, by);
//	    Navigator.waitForAjaxElementLoad(driver, by);
	    Navigator.explicitWait(1000);
	    WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
	    by = By.name("SEARCH");
	    WebDriverUtils.clickButton(driver,by);
	    
	    WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
	    this.setSessionStatus_UI(driver, this.getSessionStatus());
	    this.setSessionTitle_UI(driver, this.getSessionTitles());
	    
	    WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
	    by = By.cssSelector("img[alt=\"Save\"]");
	    WebDriverUtils.clickButton(driver, by);

	    // assign sub modules
	    WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
	    by = By.name("AC");
	    WebDriverUtils.clickLink(driver, by);
	    WebDriverUtils.switchToPopUpWin(driver);
	    this.setSubModules_UI(driver, this.getSubModules());
	    WebDriverUtils.switchToParentWin(driver);
	    
	    
	    WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
	    by = By.cssSelector("img[alt=\"Save\"]");
	    WebDriverUtils.clickButton(driver, by);
	}

	public String getSessionIDs() {
		return SessionIDs;
	}

	public void setSessionIDs(String sessionIDs) {
		SessionIDs = sessionIDs;
	}

	public String getSessionTitles() {
		return SessionTitles;
	}

	public void setSessionTitles(String sessionTitles) {
		SessionTitles = sessionTitles;
	}

	public String getSessionStatus() {
		return SessionStatus;
	}

	public void setSessionStatus(String sessionStatus) {
		SessionStatus = sessionStatus;
	}

	public String getSubModules() {
		return SubModules;
	}

	public void setSubModules(String subModules) {
		SubModules = subModules;
	}

	/**
	 * Empty method since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCostAccounting(WebDriver driver) {

	}

}
