package com.netdimen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.controller.TestDriver;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.UIFunctionUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class JobProfile extends TestObject {
	private String	Catalog = "",	Code="",	Title="",	Desc="",	isActive="", Participants="", Competencies = "";
	
	private ArrayList<Competency> competencyList = new ArrayList<Competency>();
	

	public ArrayList<Competency> getCompetencyList(){
		return this.competencyList;
	}
	
	public void addCompetency(Competency ins){
		this.competencyList.add(ins);
	}
	
	public String getCompetencies() {
		return Competencies;
	}

	public void setCompetencies(String competencies) {
		Competencies = competencies;
	}

	public String getParticipants() {
		return Participants;
	}

	public void setParticipants(String participants) {
		Participants = participants;
	}

	public void setCatalog_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.linkText(str);
			WebDriverUtils.clickLink(driver, by);
		}
	}

	public void setCode_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("referenceCode");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setTitle_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("name");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setDesc_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("description");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setActive_UI(WebDriver driver, String str){
		if(str.equalsIgnoreCase("yes")){
			By by = By.id("isActive");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	public void setProficiencyLevel_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("levelGroup");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	
	public void runCreate(WebDriver driver){
		Navigator.navigate(driver,Navigator.webElmtMgr.getNavigationPathList("ManageCenter","2.JobProfile"));

		this.setCatalog_UI(driver, this.getCatalog());
		By by = By.id("addJobProfileBtn");
		WebDriverUtils.clickButton(driver, by);
		
		this.setCode_UI(driver, this.getCode());
		this.setTitle_UI(driver, this.getTitle());
		this.setDesc_UI(driver, this.getDesc());
		this.setActive_UI(driver, this.getisActive());
		
		by = By.id("saveButton");
		WebDriverUtils.clickButton(driver, by);
	}
	
	public void runAutoEnroll(WebDriver driver){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList("ManageCenter", "2.JobProfile"));
		this.setCatalog_UI(driver, this.getCatalog());
		
		//1. expand job profile
		By by = By.linkText(this.getCode());
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait();
		WebDriverUtils.clickLink(driver, by);
		
		//2. click "Auto-assign"
		by = By.xpath("//a[descendant::span[text()='Auto-assign']]");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait();
		WebDriverUtils.clickLink(driver, by);
		
		//3. auto-enroll users based on org.attributes
		by = By.xpath("//div[descendant::span/h3[text()='"
				+ Config.getInstance().getProperty("heading.org.attrs")
				+ "'] and @class='action-nav status-header']");
		UIFunctionUtils.setParticipants_UI(driver, this.getParticipants(),by);
		
		by = By.xpath("//button[@title='Set Auto-Enroll Targets']");
		if (WebDriverUtils.isElementPresent(by)) {
			WebDriverUtils.clickButton(driver, by);
		} else {
			by = By.xpath("//button[@title='Set Auto-Assign Targets']");
			WebDriverUtils.clickButton(driver, by);
		}

		JUnitAssert.assertTrue(WebDriverUtils.closeAlertAndGetItsText().matches(
				"^" + Config.getInstance().getProperty("msg.are_you_sure_auto")
						+ "[\\s\\S]$"), "Cannot find assertion msg");
		
		Navigator.explicitWait(2000);
		//4 check auto-enroll results
		String text = Config.getInstance().getProperty("msg_jobprofile_autoassing_now_in_effect");
		JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, text), "Cannot find text in page:" + text);
		
		WebDriverUtils.closeAllPopUpWins(driver);		
		this.checkExpectedResult_AutoEnroll_UI(driver, 
				UIFunctionUtils.parseExpectedResults_AutoEnroll(this.getExpectedResult()));
	}

	private void checkEnrollment_UI(WebDriver driver, ArrayList<String> users){		
		for(String UID: users){			
			this.checkEnrollment_UI(driver, UID);
		}		
	}
	
	private void checkEnrollment_UI(WebDriver driver, String UID){
		User user = new User(UID, "11111111");
		TestDriver.switchUser(user);	
		
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList("LearningCenter","2.JobProfile"),this);
		
		boolean flag = WebDriverUtils.textPresentInPage(driver, this.getCode());
		
		//re-login to check the auto-assigned job profile
		int counter = 0;
		int loop_max = 10;
		while(!flag && counter < loop_max){
			user.logout(driver);
			user.login(driver);
			Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList("LearningCenter","2.JobProfile"),this);
			Navigator.explicitWait(1000);
			flag = WebDriverUtils.textPresentInPage(driver, this.getCode());;
			counter ++;
		}
		
		JUnitAssert.assertTrue(flag, "User:"+ user.getUserID()+" NOT enroll into course:" + this.getCode());
	}
	
	private void checkUnEnrollment_UI(WebDriver driver, String UID){
		User user = new User(UID, "11111111");
		TestDriver.switchUser(user);	
		
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList("LearningCenter","2.JobProfile"),this);
		Navigator.explicitWait(1000);
		JUnitAssert.assertTrue(!WebDriverUtils.textPresentInPage(driver, this.getCode()), "User:"+ user.getUserID()+" enrolls into course:" + this.getCode());
	}
	
	private void checkUnEnrollment_UI(WebDriver driver, ArrayList<String> users){
		for(String UID: users){
			this.checkUnEnrollment_UI(driver, UID);
		}
	}
	
	private void checkExpectedResult_AutoEnroll_UI(WebDriver driver, HashMap<String, ArrayList<String>> criteria_users){
		//2. login user to checkout auto-enroll results
		Iterator<String> criteria_ite = criteria_users.keySet().iterator();
		while(criteria_ite.hasNext()){
			String criteria_ins = criteria_ite.next();
			ArrayList<String> users_ins = criteria_users.get(criteria_ins);
			
			switch(criteria_ins){
			case "enrollment":
				this.checkEnrollment_UI(driver, users_ins);
				break;
			case "non-enrollment":
				this.checkUnEnrollment_UI(driver, users_ins);
				break;
			}
		}		
	}
	
/*	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getCode()).
				toString();
	}*/

	@Override
	public boolean equals(TestObject obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void checkExpectedResult_UI(WebDriver driver, String expectedResult) {
		// TODO Auto-generated method stub
		super.checkExpectedResult_UI(driver, expectedResult);
	}

	public String getCatalog() {
		return Catalog;
	}

	public void setCatalog(String catalog) {
		Catalog = catalog;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public String getisActive() {
		return isActive;
	}

	public void setisActive(String isActive) {
		this.isActive = isActive;
	}

}
