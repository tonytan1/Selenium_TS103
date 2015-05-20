package com.netdimen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.SikuliUtils;
import com.netdimen.utils.UIFunctionUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class User extends com.netdimen.abstractclasses.TestObject{
	private String SignatureEnabled="",CourseTitle="",CourseID="", ExamStatus="", LearningPathImageFile = "", Name = "", Criteria = "",
			UserID = "", Role = "", DA = "";

	public String getCriteria() {
		return Criteria;
	}


	public String getDA() {
		return DA;
	}


	public void setDA(String dA) {
		DA = dA;
	}


	public void setCriteria(String criteria) {
		Criteria = criteria;
	}


	public String getUserID() {
		return UserID;
	}


	public String getRole() {
		return Role;
	}


	public void setUserID(String userID) {
		UserID = userID;
	}


	public void setRole_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("ROL");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}
	
	public void setDA_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("DAP-button-id");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);
			
			String criteria = this.getCriteria();
			if(!criteria.equals("")){
				//set DA based on org. attr.
				by = By.xpath("//span[text()='Specify Additional Attributes']");
				WebDriverUtils.clickLink(driver, by);
				Navigator.explicitWait(1000);
				
				by = By.xpath("//div[@class='action-nav status-header']/span[descendant::h3[text()='Organization Attributes']]");
				UIFunctionUtils.setParticipants_UI(driver, criteria, by);
				
				Navigator.explicitWait(2000);
				by = By.xpath("//span[text()='Search']");
				WebDriverUtils.clickButton(driver, by);
				Navigator.explicitWait(2000);
				
				//check user exists
				by = By.xpath("//select[@id='avlParmSelector']/option[contains(text(), '" + str.toUpperCase() + "')]");				
				int size = WebDriverUtils.getHowManyByPresntInPage(by);
				JUnitAssert.assertTrue(size>0, "Cannot find DA:" + str);
				
			}else{
				ArrayList<String> keywords = new ArrayList<String>();
				keywords.add(str);
				WebDriverUtils.searchSelect_Selector(driver, WebDriverUtils.PopUpSelector.InnerUserSelector, keywords);
			}
			
		}
	}
	
	
	public void setRole(String role) {
		Role = role;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public User(){
		super();
	}

	
	public User(String UID, String PWD){
		this.UID = UID;
		this.PWD = PWD;
	}
	
	
	
	public String getExamStatus() {
		return ExamStatus;
	}


	public void setExamStatus(String examStatus) {
		ExamStatus = examStatus;
	}


	public boolean equals(TestObject obj){
		return true;
	}

	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
	}
	
	public String getSignatureEnabled(){
		return SignatureEnabled;
	}

	public String getCourseTitle(){
		return CourseTitle;
	}

	public String getCourseID(){
		return CourseID;
	}

	public void setSignatureEnabled(String signatureenabled){
		SignatureEnabled=signatureenabled;
	}

	public void setCourseTitle(String coursetitle){
		CourseTitle=coursetitle;
	}

	public void setCourseID(String courseid){
		CourseID=courseid;
	}

	public void setSignatureEnabled_UI(WebDriver driver, String str){
	}

	public void setCourseTitle_UI(WebDriver driver, String str){
	}

	public void setCourseID_UI(WebDriver driver, String str){
	}

	
	public void runMarkExam(WebDriver driver){
		String CID = "Onln_StdE_1FixSec1_UMA";
	    String EID = "StdE_1FixSec1_UMA";
	    // Search course
	    By by = By.linkText("My Current Courses");
	    WebDriverUtils.clickLink(driver, by);
	    
	    by = By.xpath("//li[descendant::div/h4[contains(text(),'" + CID + "')]]/div[@class='module-actions']/a[contains(text(),'Knowledge Center')]/i");
	    WebDriverUtils.clickLink(driver, by);
	    
	    by = By.xpath("//a[contains(text(),'" + EID + "')]");
	    WebDriverUtils.clickLink(driver, by);

	    by = By.xpath("//input[contains(@name,'Button')]");
	    WebDriverUtils.clickButton(driver, by);
	    
	    by = By.id("submitbutton");
	    WebDriverUtils.clickButton(driver, by);

	    by = By.id("A1_3");
	    WebDriverUtils.clickButton(driver, by);

	    by = By.id("submitbutton");
	    WebDriverUtils.clickButton(driver, by);

	    by = By.xpath("//select[@name='A2']");
	    String str = "True";
	    WebDriverUtils.select_selector(driver, by, str);

	    by =By.id("submitbutton");
	    WebDriverUtils.clickButton(driver, by);

	    by = By.id("submitbutton");
	    WebDriverUtils.clickButton(driver, by);

	    JUnitAssert.assertTrue(WebDriverUtils.closeAlertAndGetItsText().matches("^Are you sure that you want to end this exam[\\s\\S]$"),"Are you sure that you want to end this exam");
	    
	    by = By.name("closeButton");
	    WebDriverUtils.clickButton(driver, by);
	}
	
	
	/**Self-enroll module via catalog search
	 * 
	 * @param driver
	 */
	public void runEnrollCourse(WebDriver driver){
		By by = By.name("KEYW");
		String str = this.getCourseID();
		WebDriverUtils.fillin_textbox(driver, by, str);

		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
	    
	    Navigator.explicitWait(3000);

	    by = By.xpath("//a[em[contains(text(), '" + this.getCourseID() + "')]]");
	    WebDriverUtils.clickLink(driver, by);
	    WebDriverUtils.switchToPopUpWin(driver);

	    by = By.name("enroll");
	    WebDriverUtils.clickButton(driver, by);
	    
	    Navigator.explicitWait(3000);

	    by = By.name("submitButton");
	    WebDriverUtils.clickButton(driver, by);
	}
	

	public void login(WebDriver driver, String URL, String UID, String PWD){	 
		WebDriverUtils.openURL(driver, URL);
		By by = By.id("UID");
		WebDriverUtils.fillin_textbox(driver, by, UID);

		by = By.id("PWD");
		WebDriverUtils.fillin_textbox(driver, by, PWD);

		by = By.name("login");
		WebDriverUtils.clickButton(driver, by);
		
	/*	WebDriverUtils.acceptAlert(driver);
		Navigator.explicitWait(1000);*/
	}



	/**Login as a default user in config.properties
	 * 
	 * @param driver
	 */
	public void login(WebDriver driver){	  
		String URL =  Config.getInstance().getProperty("loginURL");
		this.login(driver, URL, this.UID, this.PWD);
	}

	public static void logout(WebDriver driver){
		//By by = By.linkText("Logout");	
		//WebDriverUtils.closeAllPopUpWins(driver);
		//Navigator.waitForAjax(driver, by);
		//driver.findElement(by).click();
		
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "1.Home"));
		
		By by = By.linkText("Logout");
		WebDriverUtils.clickLink(driver, by);
	}
	

	
	public String getLearningPathImageFile() {
		return LearningPathImageFile;
	}


	public void setLearningPathImageFile(String learningPathImageFile) {
		LearningPathImageFile = learningPathImageFile;
	}


/*	public String toString(){
		StringBuilder sb =  new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getUID());
		if(!this.getCourseID().equalsIgnoreCase("")){
			sb.append("_").append(this.getCourseID());
		}
		
		return sb.toString();
	}*/
	
	/**Checkout user's learning path
	 * 
	 * @param driver
	 */
	public void runCheckLearningPath(WebDriver driver){
		//1. Goto My Learning Path
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.LearningPath"));
		
		Navigator.explicitWait(1000);
		//2. Sikuli: Image-based comparison
		String screenshotFile = this.getLearningPathImageFile();
		boolean exist = SikuliUtils.screenshotExistInWin(screenshotFile);
		JUnitAssert.assertTrue(exist, "Cannot find image:" + screenshotFile);
	}
	
	public void searchUser(WebDriver driver, String UserID){
		//1. Click search
		WebDriverUtils.switchToFrame(driver, "USERMENU");
		Navigator.explicitWait(1000);
		By by = By.xpath("//img[@alt='Search']");
		WebDriverUtils.clickLink(driver, by);
				
		//2. Search users
		WebDriverUtils.switchToFrame(driver, "USERMAIN");
		Navigator.explicitWait(1000);
		by = By.id("PID");		
		WebDriverUtils.fillin_textbox(driver, by, UserID);
		by = By.xpath("//input[@value='Search']");
		WebDriverUtils.clickButton(driver, by);

	}
	
	public void runCheckVisibility(WebDriver driver){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.UserEditor"));
		WebDriverUtils.switchToPopUpWin(driver);
		HashMap<String, ArrayList<String>> criteria_users = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		Iterator<String> criteria = criteria_users.keySet().iterator();
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> users = criteria_users.get(criterion);
			for(String userStr: users){
				for(String user:userStr.split(";")){
					//2.1 search user
					this.searchUser(driver, user);
					
					//2.2 check user visibility
					WebDriverUtils.switchToFrame(driver, "USERMAIN");
					Navigator.explicitWait(1000);
					By by = By.xpath("//tr/td[text()='" + user + "']");
					int size;
					switch(criterion.toLowerCase()){
					case "visible":
						Navigator.waitForAjax(driver, by);
						size = WebDriverUtils.getHowManyByPresntInPage(by);
						JUnitAssert.assertTrue(size > 0, "User is invisible:" + user);
						break;
					case "invisible":
						size = WebDriverUtils.getHowManyByPresntInPage(by);
						JUnitAssert.assertTrue(size == 0, "User is visible:" + user);
						break;
					}	
				}
			}
		}
		
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	
	
	public void runUpdate(WebDriver driver){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.UserEditor"));
		WebDriverUtils.switchToPopUpWin(driver);
		//1. Search user
		this.searchUser(driver, this.getUserID());
		
		//2. click user
		WebDriverUtils.switchToFrame(driver, "USERMAIN");
		Navigator.explicitWait(1000);
		By by = By.xpath("//tr[descendant::td[text()='" + this.getUserID() + "']]/td[1]/a");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		
		// Update user
		this.setRole_UI(driver, this.getRole());
		this.setDA_UI(driver, this.getDA());
		
		//3. Save changes
		WebDriverUtils.switchToFrame(driver, "USERMENU");
		Navigator.explicitWait(1000);
		by = By.xpath("//img[@alt='Save']");
		WebDriverUtils.clickLink(driver, by);
		
		//4. close pop-up window
		WebDriverUtils.closeAllPopUpWins(driver);
	}
	
	public void runUniversalSearch(WebDriver driver){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.Search"));
		
		By by = By.id("KEYW-catalog-search");		
		WebDriverUtils.fillin_textbox(driver, by, this.getCourseID());

		by = By.xpath("//input[@name='SEARCH' and @value='Search']");
		WebDriverUtils.clickButton(driver, by);
		
		HashMap<String, ArrayList<String>> criteria_fields = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		Iterator<String> criteria = criteria_fields.keySet().iterator();
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> fields = criteria_fields.get(criterion);
			for(String fieldStr: fields){
				for(String field:fieldStr.split(";")){
					//2.2 check field visibility
					switch(criterion.toLowerCase()){
					case "visible":
						JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, field), "Cannot find:" + field);
						break;
					case "invisible":
						JUnitAssert.assertTrue(!WebDriverUtils.textPresentInPage(driver, field), "Can find:" + field);
						break;
					}	
				}
			}
		}
	}
	
	/**Empty body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckUniversalSearchFormat(WebDriver driver){
		
	}
	
	/**Empty body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckSessionSearchResult(WebDriver driver){
		
	}
	
	
	public void runCheckSessionSearch(WebDriver driver){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.Search"));
		
		//1. Set pagination
		By by = By.id("resultsPerPage");
		WebDriverUtils.select_selector(driver, by, "All");
		
		//2. Setup search criteria
		HashMap<String, ArrayList<String>> criteria_values = UIFunctionUtils.parseParticipants(this.getCriteria());
		Iterator<String> criteria = criteria_values.keySet().iterator();
		
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> values = criteria_values.get(criterion);
			for(String valueStr: values){
				for(String value:valueStr.split(";")){
					//2.2 check field visibility
					switch(criterion.toLowerCase()){
						case "location":
							by = By.id("LOCATION");
							WebDriverUtils.select_selector(driver, by, value);
							break;
						case "learningtype":
							by = By.id("LT");
							WebDriverUtils.select_selector(driver, by, value);
							break;
						default:
							break;
					}	
				}
			}
		}
		
		//3. Search
		by = By.xpath("//input[@name='SEARCH' and @value='Search']");
		WebDriverUtils.clickButton(driver, by);
		
		//4. Check result
		HashMap<String, ArrayList<String>> criteria_fields = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		criteria = criteria_fields.keySet().iterator();
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> fields = criteria_fields.get(criterion);
			for(String fieldStr: fields){
				for(String field:fieldStr.split(";")){
					//2.2 check field visibility
					switch(criterion.toLowerCase()){
					case "visible":
						JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, field), "Can find:" + field);
						break;
					case "invisible":
						JUnitAssert.assertTrue(!WebDriverUtils.textPresentInPage(driver, field), "Can find:" + field);
						break;
					}	
				}
			}
		}
	}

}