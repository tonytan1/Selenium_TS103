package com.netdimen.model;

import static com.netdimen.utils.WebDriverUtils.closeAlertAndGetItsText;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Config;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Classroom extends LearningModule {
	private String SessionIDs = "", SessionTitles = "", SessionStatus = "", 
			EnrollBeginDates ="", EnrollEndDates = "", Credits = "",
			SessionBeginDates = "", SessionEndDates = "", isCustomVenue = "", Location="", Room = "", AssessmentID = "";
/*dfdfsd*/
	public String getAssessmentID() {
		return AssessmentID;
	}

	public void setAssessmentID(String assessmentID) {
		AssessmentID = assessmentID;
	}

	public Classroom(){
		super();
	}
	
	public void setSessionID_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("SESSIONCODE");
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
			By by = By.id("EST");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	
	public void setEndDates_UI(WebDriver driver, String endDate){
		if(!endDate.equals("")){
			String xpath_calendar = "(//img[@alt='Calendar'])[2]";
			WebDriverUtils.dateSelect_Calandar(driver, endDate, xpath_calendar);
		}
	} 

	
	public void setBeginDates_UI(WebDriver driver, String beginDate){
		if(!beginDate.equals("")){
			String xpath_calendar = "(//img[@alt='Calendar'])[1]";
			WebDriverUtils.dateSelect_Calandar(driver, beginDate, xpath_calendar);
			
		}
	} 
	
	
	public void setBeginHours_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("SCHHOUR");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setisCustomVenue_UI(WebDriver driver, String str){
		if(str.equalsIgnoreCase("yes")){
			By by = By.id("useCustomVenue");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}
	
	public void setLocation_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("LOC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void setRoom_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("ROOM_NAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}
	
	public void runAttachAssessment(WebDriver driver){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		By by = By.xpath("//a[contains(text(),'Session Properties')]");
		Navigator.explicitWait(3000);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(3000);
		
		by = By.name("STATUSIDOPT");		
		WebDriverUtils.select_selector(driver, by, this.getSessionStatus());
		
		String name_selector = "EVENTIDOPT";
		WebDriverUtils.select_selector_partialTexts(driver, name_selector, this.getSessionTitles());
		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
	
		by = By.name("EWID");
		WebDriverUtils.select_selector(driver, by, this.getAssessmentID());
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
	}
	
	
	public void runCreate(WebDriver driver){
		super.runCreate(driver);

		//edit session
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		By by = By.linkText("Session Properties");
//		Navigator.explicitWait(3000);
		WebDriverUtils.clickLink(driver, by);

		by = By.id("ADD_SESSION_IMG");
		WebDriverUtils.clickLink(driver, by);
		JUnitAssert.assertTrue(
				closeAlertAndGetItsText().matches("^Are you sure you want to add a new session[\\s\\S]$"), 
				"Cannot find text:Are you sure you want to add a new session");
		Navigator.explicitWait(1000);
//		Navigator.waitForAjaxElementLoad(driver, by);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setSessionID_UI(driver, this.getSessionIDs());
		this.setSessionTitle_UI(driver, this.getSessionTitles());
		this.setSessionStatus_UI(driver, this.getSessionStatus());
		
		this.setBeginDates_UI(driver, this.getEnrollBeginDates());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setEndDates_UI(driver, this.getEnrollEndDates());
		
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
//		Navigator.waitForAjaxElementLoad(driver, by);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		by = By.xpath("//a[contains(text(), '2. "+Config.getInstance().getProperty("tab.Edit_Session_Schedule")+"')]");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.name("ADDNEW");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		
		this.setBeginDates_UI(driver, this.getSessionBeginDates());
		this.setBeginHours_UI(driver, "11");
		this.setEndDates_UI(driver, this.getSessionEndDates());
		
		this.setisCustomVenue_UI(driver, this.getisCustomVenue());
		this.setLocation_UI(driver, this.getLocation());
		this.setRoom_UI(driver, this.getRoom());

		by = By.name("CHECK_ANS");
		WebDriverUtils.clickButton(driver, by);
		/*by = By.name("OK");
		WebDriverUtils.clickButton(driver, by);*/
		
		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
	}

	
	public void runGroupEnroll(WebDriver driver){	
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		By by = By.xpath("//a[contains(text(),'"+Config.getInstance().getProperty("label.SessionProperties")+"')]");
		Navigator.explicitWait();
//		Navigator.waitForAjaxElementLoad(driver, by);
		
		WebDriverUtils.clickLink(driver, by);
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait();
		
		by = By.name("STATUSIDOPT");
		
		WebDriverUtils.select_selector(driver, by, this.getSessionStatus());
		
		String name_selector = "EVENTIDOPT";
		WebDriverUtils.select_selector_partialTexts(driver, name_selector, this.getSessionTitles());
		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
//		Navigator.waitForAjaxElementLoad(driver, by);
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		by = By.xpath("//a[contains(text(),'Group Enroll')]");
		Navigator.explicitWait();
//		Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);

		by = By.xpath("//a[contains(text(),'"+Config.getInstance().getProperty("label.GEnroll_Desc3")+"')]");
		driver.findElement(by).click();

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		this.setParticipant_UI(driver, this.getParticipants());
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");Navigator.explicitWait(1000);
		by = By.name("tableButton");
		Navigator.explicitWait(1000);
		WebDriverUtils.clickButton(driver, by);
		JUnitAssert.assertTrue(WebDriverUtils
				.closeAlertAndGetItsText()
				.matches("^Are you sure you want to make this group assignment[\\s\\S]$"), 
				"Cannot find text:Are you sure you want to make this group assignment");
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
		by = By.xpath("//input[@type='SUBMIT' and @name='submitButton']");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		//check out the enrollment result
//		this.checkGroupEnrollResult_UI(driver, this.getParticipants());
		
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		Navigator.explicitWait(1000);

//		Navigator.explicitWait(3000);
		by = By.cssSelector("img[alt=\"Save\"]");
		Navigator.explicitWait();
//		Navigator.waitForAjaxElementLoad(driver, by);
		driver.findElement(by).click();
	}
	
	private void checkGroupEnrollResult_UI(WebDriver driver, String participants) {
		String[] UID_Array = this.getParticipants().split(";");
		By by = null;
		for(String UID: UID_Array){				 
			by = By.xpath("//tr[descendant::td[contains(text(),'" + UID.toUpperCase() + "')]]/td[2]");
			String result = WebDriverUtils.getText(driver, by);
			String expectedResult = Config.getInstance().getProperty("msg.reg.3.Enrollment_Completed");
			JUnitAssert.assertEquals(expectedResult, result);
		}
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

	public String getEnrollBeginDates() {
		return EnrollBeginDates;
	}

	public void setEnrollBeginDates(String enrollBeginDates) {
		EnrollBeginDates = enrollBeginDates;
	}

	public String getEnrollEndDates() {
		return EnrollEndDates;
	}

	public void setEnrollEndDates(String enrollEndDates) {
		EnrollEndDates = enrollEndDates;
	}

	public String getCredits() {
		return Credits;
	}

	public void setCredits(String credits) {
		Credits = credits;
	}

	public String getSessionBeginDates() {
		return SessionBeginDates;
	}

	public void setSessionBeginDates(String sessionBeginDates) {
		SessionBeginDates = sessionBeginDates;
	}

	public String getSessionEndDates() {
		return SessionEndDates;
	}

	public void setSessionEndDates(String sessionEndDates) {
		SessionEndDates = sessionEndDates;
	}

	public String getisCustomVenue() {
		return isCustomVenue;
	}

	public void setisCustomVenue(String isCustomVenue) {
		this.isCustomVenue = isCustomVenue;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}
}
