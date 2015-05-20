package com.netdimen.model;

import static com.netdimen.utils.WebDriverUtils.closeAlertAndGetItsText;

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
public class LearningModule extends TestObjectSignature {

	private String ModuleID = "", ModuleType = "", PolicyID = "",
			ModuleTitle = "", Desc = "", Catalog = "", Visibility = "",
			Participants = "", Exam = "", isMandatoryExam = "",
			Evaluation = "", isMandatoryEval = "", Certification = "",
			Status = "", Permission = "", Score = "", Credits = "",
			otherInfo = "",LaunchArea = "", ModuleLevel = "";
	
	private static String Msg_Confirm_Delete =Config.getInstance().getProperty("msg.confirm_delete").replace("\\", "");
	private static String Msg_Group_Assign =Config.getInstance().getProperty("msg.Are_you_sure_group").replace("?", "");

	public String getModuleLevel() {
		return ModuleLevel;
	}

	public void setModuleLevel(String moduleLevel) {
		ModuleLevel = moduleLevel;
	}

	public String getIsMandatoryExam() {
		return isMandatoryExam;
	}

	public String getIsMandatoryEval() {
		return isMandatoryEval;
	}

	public String getScore() {
		return Score;
	}

	public String getCredits() {
		return Credits;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public String getLaunchArea() {
		return LaunchArea;
	}

	public void setLaunchArea(String LaunchArea) {
		this.LaunchArea = LaunchArea;
	}

	public void setIsMandatoryExam(String isMandatoryExam) {
		this.isMandatoryExam = isMandatoryExam;
	}

	public void setIsMandatoryEval(String isMandatoryEval) {
		this.isMandatoryEval = isMandatoryEval;
	}

	public void setScore(String score) {
		Score = score;
	}

	public void setCredits(String credits) {
		Credits = credits;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getisMandatoryExam() {
		return isMandatoryExam;
	}

	public void setisMandatoryExam(String isMandatoryExam) {
		this.isMandatoryExam = isMandatoryExam;
	}

	public String getisMandatoryEval() {
		return isMandatoryEval;
	}

	public void setisMandatoryEval(String isMandatoryEval) {
		this.isMandatoryEval = isMandatoryEval;
	}

	public String getPermission() {
		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

/*	public String toString() {
		return new StringBuilder().append(this.getClass().getName())
				.append("_").append(this.getFuncType()).append("_")
				.append(this.getModuleID()).toString();
	}
*/
	public void setExam_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.linkText("Assign exam(s)");
			WebDriverUtils.clickButton(driver, by);

			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.searchSelect_Selector(driver, str);
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setisMandatoryExam_UI(WebDriver driver, String str) {
		if (str.equalsIgnoreCase("yes")) {
			By by = By.id("MANTESTS");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	public void setCertification_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("SELECT_BUTTON2");
			WebDriverUtils.clickButton(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.searchSelect_Selector(driver, str);
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setEvaluation_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("SELECT_BUTTON3");
			WebDriverUtils.clickButton(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.searchSelect_Selector(driver, str);
			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setisMandatoryEvaluation_UI(WebDriver driver, String str) {
		if (str.equalsIgnoreCase("yes")) {
			By by = By.id("EVLREQ");
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	public void setParticipant_UI(WebDriver driver, String UIDs) {
		String[] UID_Array = this.getParticipants().split(";");
		WebDriverUtils.searchSelect_Selector(driver, UID_Array);
	}

	public void setDesc_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("DESC1");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setPolicyID_UI(WebDriver driver, String policyID) {
		if (!policyID.equals("")) {
			By by = By.id("POLICY_SELECT_POLICYID");
			WebDriverUtils.clickButton(driver, by);

			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.switchToFrame(driver, "POLICYTREE");
			Navigator.explicitWait(3000);
			by = By.xpath("//a[contains(text(),'" + policyID + "')]");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToFrame(driver, "POLICYMENU");
			by = By.cssSelector("img[alt=\"Select\"]");
			WebDriverUtils.clickButton(driver, by);

			WebDriverUtils.switchToParentWin(driver);
		}
	}

	public void setCourseID_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("LID");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setCourseTitle_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("aTextDesc");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setVisibility_UI(WebDriver driver, String str) {
		By by = By.id("SHOWIN");
		if (str.equalsIgnoreCase("true")) {
			WebDriverUtils.check_checkbox(driver, by);
		} else if (str.equalsIgnoreCase("false")) {
			WebDriverUtils.uncheck_checkbox(driver, by);
		}
	}

	public void setCatalog_UI(WebDriver driver, String catalog) {
		if (!catalog.equals("")) {
			By by = By.linkText(Config.getInstance().getProperty("label.EditLO_Desc19"));
			int size = driver.findElements(by).size();
			if (size == 1) {
				by = By.linkText("Assign catalog");
				WebDriverUtils.clickLink(driver, by);
				WebDriverUtils.switchToPopUpWin(driver);
				WebDriverUtils.searchSelect_Selector(driver, catalog);
				WebDriverUtils.switchToParentWin(driver);

			} else {// for TS93, 92
				by = By.xpath("//a/label[contains(text(),'"+Config.getInstance().getProperty("label.EditLO_Desc19")+"')]");
				size = driver.findElements(by).size();
				if (size == 1) {
					WebDriverUtils.clickLink(driver, by);
					WebDriverUtils.switchToPopUpWin(driver);
					WebDriverUtils.checkSelect_CheckBox(driver, catalog);
					WebDriverUtils.switchToParentWin(driver);
				}
			}
		}
	}

	public void setCourseType_UI(WebDriver driver, String str) {
		if (!str.equalsIgnoreCase("")) {
			By by = By.id("LT");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "3.ModuleCreate"), this);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");

		// Navigator.explicitWait();

		this.setCourseID_UI(driver, this.getModuleID());
		this.setCourseType_UI(driver, this.getModuleType());
		By by = By.name("CREATE");
		WebDriverUtils.clickButton(driver, by);

		this.setCourseTitle_UI(driver, this.getModuleTitle());
		this.setDesc_UI(driver, this.getDesc());
		this.setVisibility_UI(driver, this.getVisibility());
		this.setCatalog_UI(driver, this.getCatalog());
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");

		by = By.linkText("2.5 Define Enrollment Policy");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setPolicyID_UI(driver, this.getPolicyID());

		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		by = By.partialLinkText(Config.getInstance().getProperty("label.Assign_Test/Cert/Eval"));
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");

		// Config Exam
		this.setExam_UI(driver, this.getExam());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setisMandatoryExam_UI(driver, this.getisMandatoryExam());

		// Config Certification

		this.setCertification_UI(driver, this.getCertification());

		// Config Evaluation
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setEvaluation_UI(driver, this.getEvaluation());
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setisMandatoryEvaluation_UI(driver, this.getisMandatoryEval());

		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);

	}

	public void runUpdateKC(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);

		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(2000);

		By by = By.linkText("3.1 Setup Options");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(2000);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.id("isUseKc");
		WebDriverUtils.check_checkbox(driver, by);
		Navigator.explicitWait(2000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);
	}

	public void searchModule(WebDriver driver) {
		By by = null;

		String funcType = this.getFuncType().toLowerCase();
		if (funcType.contains("create")) {
			by = By.id("createButton");
			WebDriverUtils.clickButton(driver, by);
		} else {
			// not creation
			by = By.id("KEYW");
			WebDriverUtils.fillin_textbox(driver, by, this.getModuleID());
			Navigator.explicitWait(1000);
			by = By.name("apply-filters");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(3000);
			
			String course = "";
			if (funcType.contains("delete")) {
				// for deleting
				course = this.getModuleTitle() + " (" + this.getModuleID()
						+ ")";
			} else {
				// for editing
				course = this.getModuleID();
			}

			by = By.linkText(course);
			int size = driver.findElements(by).size();
			if (size == 1) {
				WebDriverUtils.clickLink(driver, by);
			} else {
				course = this.getModuleTitle();
				by = By.linkText(course);
				size = driver.findElements(by).size();
				if (size == 1) {
					WebDriverUtils.clickLink(driver, by);
				}
			}
		}
	}

	public void runUpdate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setCourseTitle_UI(driver, this.getModuleTitle());
		this.setVisibility_UI(driver, this.getVisibility());
		this.setCatalog_UI(driver, this.getCatalog());
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");

		By by = By.linkText("2.5 Define Enrollment Policy");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		this.setPolicyID_UI(driver, this.getPolicyID());

		Navigator.explicitWait(3000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);
	}

	public void runSetLaunchAsExam(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(2000);

		By by = By.partialLinkText(Config.getInstance().getProperty("label.Define_Launch_Properties"));
		WebDriverUtils.clickLink(driver, by);
		int size;
//		int size = driver.findElements(by).size();
//		if (size > 0) {
//			WebDriverUtils.clickLink(driver, by);
//		} else {
//			by = By.linkText("3.1 Define Launch Properties");
//			size = driver.findElements(by).size();
//			if (size > 0) {
//				WebDriverUtils.clickLink(driver, by);
//			}
//		}

		Navigator.explicitWait(2000);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.id("runTimeEnvironment_t");
		String str = "";
		size = driver.findElements(by).size();
		if (size == 0) {
			by = By.id("LINT");
			str = "Resource";
			WebDriverUtils.select_selector(driver, by, str);
		}
		Navigator.explicitWait(2000);

		by = By.id("runTimeEnvironment_t");
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(2000);
		by = By.id("select_button");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.searchSelect_Selector(driver, this.getExam());
		Navigator.explicitWait(2000);
		WebDriverUtils.switchToParentWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");

		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(2000);
	}

	public void runDelete(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");

		By by = By.cssSelector("img[alt=\"Delete module/program\"]");
		WebDriverUtils.clickButton(driver, by);

		// WebDriverUtils.closeAlertAndGetItsText();
		JUnitAssert.assertTrue(closeAlertAndGetItsText()
				.matches("^"+Msg_Confirm_Delete+"$"), "Cannot find text:"+Msg_Confirm_Delete);

	}

	/**
	 * Self-enroll course via catalog searcher
	 * 
	 * @param driver
	 */
	public void searchToEnroll_UI(WebDriver driver) {
		By by = By.id("KEYW");
		String str = this.getModuleID();
		WebDriverUtils.fillin_textbox(driver, by, str);
		Navigator.explicitWait(1000);
		by = By.name("SEARCH");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(3000);

		by = By.xpath("//a[descendant::em[text()='" + str + "']]");
		WebDriverUtils.clickLink(driver, by);
		
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(2000);
		by = By.name("enroll");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(2000);
		by = By.name("submitButtonJS");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(2000);
		
	}

	/**
	 * Self-enroll and finish course, check the course status in
	 * "My Training History"
	 * 
	 * @param driver
	 */
	public void runSearchToEnrollAndFinish(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "1.Home"), this);

		// 1. Self-enroll courses
		this.searchToEnroll_UI(driver);
		Navigator.explicitWait(1000);
		// 2. Finish courses
		By by = By.linkText("Launch this course!");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "course_button");
		Navigator.explicitWait(1000);
		by = By.xpath("//input[@value='Mark as completed']");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		// 3. Close all pop-up windows
		WebDriverUtils.closeAllPopUpWins(driver);

		// 4. Check results: disabled since it's too time-consuming
	/*	Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.MyTrainingHistory"), this);
		
		
		by = By.id("RESULTS_PER_PAGE");
		WebDriverUtils.select_selector(driver, by, "All");
		Navigator.explicitWait(6000);
		
		by = By.xpath("//tr[descendant::td[3]/div/a[contains(text(),'"
				+ this.getModuleTitle()
				+ "')] and ./td[4][text()='Completed (Self-Asserted)']]");
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by), "Cannot find module transcript of " + this.getModuleTitle());*/
	}

	/**
	 * Self-enroll course, check the course status in "My Training History"
	 * 
	 * @param driver
	 */
	public void runSearchToEnroll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.Search"), this);

		// 1. Self-enroll courses
		this.searchToEnroll_UI(driver);
		Navigator.explicitWait(2000);
		WebDriverUtils.closeAllPopUpWins(driver);
		
		// 2. Check results
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.MyTrainingHistory"), this);

		By by = By.id("RESULTS_PER_PAGE");
		WebDriverUtils.select_selector(driver, by, "200");

		switch (this.getModuleType()) {
		case "Online":
			by = By.xpath("//tr[descendant::td[3]/div/a[contains(text(),'"
					+ this.getModuleTitle()
					+ "')] and ./td[4][text()='Not Started']]");
			break;
		case "Program":
			by = By.xpath("//tr[descendant::td[3]/div/a/span[contains(text(),'"
					+ this.getModuleTitle()
					+ "')] and ./td[4][text()='Not Started']]");
			break;
		}
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by), "Cannot find module transcript:" + this.getModuleTitle());
	}

	public void runGroupEnroll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");

		By by = By.xpath("//a[contains(text(),'Session Properties')]");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);

//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		by = By.xpath("//a[contains(text(),'Group Enroll')]");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");

		by = By.xpath("//a[contains(text(),'Select participant(s)')]");
		driver.findElement(by).click();

		WebDriverUtils.switchToPopUpWin(driver);
		this.setParticipant_UI(driver, this.getParticipants());
		WebDriverUtils.switchToParentWin(driver);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.name("tableButton");
		WebDriverUtils.clickButton(driver, by);
		JUnitAssert.assertTrue(WebDriverUtils
				.closeAlertAndGetItsText()
				.matches("^"+Msg_Group_Assign+"[\\s\\S]$"), "Cannot find text:"+Msg_Group_Assign);

		WebDriverUtils.switchToPopUpWin(driver);
		by = By.name("submitButton");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToParentWin(driver);

		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");

		Navigator.explicitWait(3000);
		by = By.cssSelector("img[alt=\"Save\"]");
		Navigator.waitForAjax(driver, by);
//		Navigator.explicitWait();
		WebDriverUtils.clickButton(driver, by);
		this.checkExpectedResult_UI(driver, this.getExpectedResult());
	}

	private void checkEnrollment_UI(WebDriver driver, ArrayList<String> users) {
		for (String UID : users) {
			this.checkEnrollment_UI(driver, UID);
		}
	}

	private void checkEnrollment_UI(WebDriver driver, String UID) {
		// 1. login user
		User user = new User(UID, "11111111");
		TestDriver.switchUser(user);

		// 2. wait for a while and handle with pop-up alert(time is specified in
		// Manager Center -> Auto-Enroll Console)
		WebDriverUtils.waitAlertAndAccept(driver);

		// 3. Goto My Current Learning
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.Learning"), this);
		Navigator.explicitWait(1000);
		JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, this.getModuleID()), "Cannot find module transcript:" + this.getModuleID());
	}

	private void checkUnEnrollment_UI(WebDriver driver, String UID) {
		User user = new User(UID, "11111111");
		TestDriver.switchUser(user);

		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.Learning"), this);
		// Navigator.explicitWait(1000);
		JUnitAssert.assertTrue(!WebDriverUtils
				.textPresentInPage(driver, this.getModuleID()), "Can find module transcript:" + this.getModuleID());
	}

	private void checkUnEnrollment_UI(WebDriver driver, ArrayList<String> users) {
		for (String UID : users) {
			this.checkUnEnrollment_UI(driver, UID);
		}
	}

	/**
	 * Login user to checkout auto-enroll results
	 * 
	 * @param driver
	 * @param criteria_users
	 */
	private void checkExpectedResult_AutoEnroll_UI(WebDriver driver,
			HashMap<String, ArrayList<String>> criteria_users) {
		if (criteria_users != null) {
			Iterator<String> criteria_ite = criteria_users.keySet().iterator();
			while (criteria_ite.hasNext()) {
				String criteria_ins = criteria_ite.next();
				ArrayList<String> users_ins = criteria_users.get(criteria_ins);

				switch (criteria_ins) {
				case "enrollment":
					this.checkEnrollment_UI(driver, users_ins);
					break;
				case "non-enrollment":
					this.checkUnEnrollment_UI(driver, users_ins);
					break;
				}
			}
		}
	}

	public void runAutoEnroll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		

		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		By by = By.xpath("//a[contains(text(),'Session Properties')]");

		WebDriverUtils.clickLink(driver, by);

		Navigator.explicitWait(3000);
		
		by = By.xpath("//a[contains(text(),'Auto-Enroll')]");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait();
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		
		by = By.xpath("//div[descendant::span/h3[text()='"
				+ Config.getInstance().getProperty("heading.org.attrs")
				+ "'] and @class='action-nav status-header']");
		UIFunctionUtils.setParticipants_UI(driver,
				this.getParticipants(), by);
		
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
		
		Navigator.explicitWait(1000);
		WebDriverUtils.acceptAlert(driver);
		
		Navigator.explicitWait(1000);
		// 1. Test AutoEnroll
		by = By.xpath("//button[@title='Test AutoEnroll']");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		Navigator.explicitWait(1000);
		by = By.xpath("//input[@value='Test AutoEnroll']");
		Navigator.explicitWait(1000);
		WebDriverUtils.clickButton(driver, by);

		// 2. Check enrollment/non-enrollment here
		HashMap<String, ArrayList<String>> criteria_users = UIFunctionUtils
				.parseExpectedResults_AutoEnroll(this.getExpectedResult());

		Iterator<String> criteria_ite = criteria_users.keySet().iterator();
		while (criteria_ite.hasNext()) {
			String criteria_ins = criteria_ite.next();
			ArrayList<String> users_ins = criteria_users.get(criteria_ins);

			switch (criteria_ins) {
			case "enrollment":
				for (String user : users_ins) {
					// 1. check Test AutoEnroll here
					JUnitAssert.assertTrue(WebDriverUtils.textPresentInPage(driver, user), "User:" + user + " not auto-enroll course:" + this.getModuleID());
				}

				break;
			case "non-enrollment":
				for (String user : users_ins) {
					// 1. check Test AutoEnroll here
					JUnitAssert.assertTrue(!WebDriverUtils.textPresentInPage(driver, user), "User:" + user + " auto-enroll course:" + this.getModuleID());
				}

				break;
			}
		}

		WebDriverUtils.closeAllPopUpWins(driver);

		// 2. login user to check auto-enroll
		this.checkExpectedResult_AutoEnroll_UI(driver, criteria_users);
	}

	public String getModuleID() {
		return ModuleID;
	}

	public void setModuleID(String moduleID) {
		ModuleID = moduleID;
	}

	public String getPolicyID() {
		return PolicyID;
	}

	public void setPolicyID(String policyID) {
		PolicyID = policyID;
	}

	public String getModuleTitle() {
		return ModuleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		ModuleTitle = moduleTitle;
	}

	public String getCatalog() {
		return Catalog;
	}

	public void setCatalog(String catalog) {
		Catalog = catalog;
	}

	public String getVisibility() {
		return Visibility;
	}

	public void setVisibility(String visibility) {
		Visibility = visibility;
	}

	public String getParticipants() {
		return Participants;
	}

	public void setParticipants(String participants) {
		Participants = participants;
	}

	public String getExam() {
		return Exam;
	}

	public void setExam(String exam) {
		Exam = exam;
	}

	public String getEvaluation() {
		return Evaluation;
	}

	public void setEvaluation(String evaluation) {
		Evaluation = evaluation;
	}

	public String getCertification() {
		return Certification;
	}

	public void setCertification(String certification) {
		Certification = certification;
	}

	public String getModuleType() {
		return ModuleType;
	}

	public void setModuleType(String moduleType) {
		ModuleType = moduleType;
	}

	public void runUpdatePermission(WebDriver driver) {
		this.runUpdate(driver);

		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		By by = By.linkText("2.1 Define Module Security");
		WebDriverUtils.clickLink(driver, by);

		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		by = By.name("permissionButton");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.clickButton(driver, by);
		UIFunctionUtils.setPermission_UI(driver, this.getPermission());

		WebDriverUtils.switchToParentWin(driver);
	}

	
	public void runAttachAssessmentExam(WebDriver driver, ArrayList<TestObject> objs){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);

		this.searchModule(driver);
		Navigator.explicitWait();
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "BSCAT_LEFT");
		Navigator.explicitWait(1000);
		
		By by = By.xpath("//a[contains(text(),'Session Properties')]");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
		
		//attach assessment
		AssessmentWorkflow assessment = (AssessmentWorkflow)objs.get(0);		
		by = By.name("EWID");
		WebDriverUtils.select_selector(driver, by, assessment.getName());
		
		WebDriverUtils.switchToFrame(driver, "BSCAT_TOP");
		Navigator.explicitWait(1000);
		by = By.cssSelector("img[alt=\"Save\"]");
		WebDriverUtils.clickButton(driver, by);
	}
	
	public void openKC(WebDriver driver){
		By by = null;
		if(!this.getModuleTitle().trim().equals("")){
			//for a non-program learning modules
			String module = this.getModuleTitle();
			by = By.xpath("//li[descendant::div[@class='module-info']/h4[text()='" + module + "']]/div[@class='module-actions']/a[contains(text(),'Knowledge Center')]");
			WebDriverUtils.clickLink(driver, by);
		}else if (!(this.getModuleLevel().trim().equals(""))) {
			String[] Progs = this.getModuleLevel().split("/");
			
			this.CLM_ExpandModules(driver, Progs, "KC");
			// In KC, click Launch Button
			by = By.xpath("//div[@class='module-main-actions']/button");
			WebDriverUtils.clickButton(driver, by);
			
		}	
		
		Navigator.explicitWait();
	}
	
	public void runPassAssessmentExam(WebDriver driver){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.MyCurrentCourses"), this);
		Navigator.explicitWait(3000);
		this.openKC(driver);
		
		//1. Click Assessment workflow
		By by = By.linkText("Assessment Workflow");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
		//2. Click Exam
		Exam ins = new Exam();
		ins.passExam(driver, this.getExam());
		
		//3. Check status
		by = By.xpath("//td[contains(text(),'" + this.getExam() + "')]/span");
		String status = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertTrue(status.equalsIgnoreCase(this.getStatus()), 
				"Expected status:"+ this.getStatus() + ";actual status:"+ status);
	}
	
	/**
	 * Launch and complete courses in KC (done) or CLM(not yet done)
	 * 
	 * @param driver
	 */
	public void runComplete(WebDriver driver) {

		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.MyCurrentCourses"), this);

		String[] Progs = null;
		By by = null;

		if (!(this.getModuleLevel().trim().equals(""))) {
			Progs = this.getModuleLevel().split("/");
		}

		if (this.getLaunchArea().equals("KC")) {

			this.CLM_ExpandModules(driver, Progs, "KC");
			// In KC, click Launch Button
			by = By.xpath("//div[@class='module-main-actions']/button");
			WebDriverUtils.clickButton(driver, by);
//			Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait(1000);

		} else if (this.getLaunchArea().equals("CLM")) {
			// Not yet implemented
			if (this.getModuleLevel().trim() == "") {
				by = By.xpath("//li[descendant::div/h4[text()='"
						+ this.getModuleTitle()
						+ "']]/div[@class='module-actions']/div[@class='module-launch']/form/input");
			}
			WebDriverUtils.clickButton(driver, by);
		}

		// Mark as Complete
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "course_button");
		Navigator.explicitWait(1000);

		by = By.xpath("//input[@class='button']");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);//10.2 is very slow to mark course as completed
		WebDriverUtils.closeAllPopUpWins(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.switchToBaseWin(driver);
		String msg = Config.getInstance().getProperty(
				"desc.No_longer_available");
		by = By.xpath("//div[@class='message-view-messagekey' and contains(text(),'"
				+ msg + "')]");
		JUnitAssert.assertTrue(WebDriverUtils.isElementPresent(by), "");

	}

	public void runFinish(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.MyCurrentCourses"), this);

		// 1. Launch course
		By by = By
				.xpath("//li[descendant::div/h4[text()='"
						+ this.getModuleTitle()
						+ "']]/div[@class='module-actions']/div[@class='module-launch']/form/input");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		WebDriverUtils.closeAllPopUpWins(driver);
		Navigator.explicitWait(1000);
		// 2. Finish course
		by = By.xpath("//li[descendant::div/h4[text()='"
				+ this.getModuleTitle()
				+ "']]/div[@class='module-status-change-buttons float-right']/form[@name='COURSE_FINISHED']/input[@value='Finished']");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(1000);
		WebDriverUtils.clickButton(driver, by);

		// 3. confirm Finish
		by = By.xpath("//div[@aria-describedby='jMessage']/div/div/button[descendant::span[text()='OK']]");
		WebDriverUtils.clickButton(driver, by);

		// 4. check confirm msg
		by = By.xpath("//ul[@class='success messagebar']/li");
		String text = WebDriverUtils.getText(driver, by);
		Navigator.explicitWait(1000);
		String msg = Config.getInstance().getProperty("msg.session_finished");
		JUnitAssert.assertEquals(msg, text);
	}

	/**
	 * Check transcript status in Learning Center -> My Training History
	 * 
	 * @param driver
	 */
	public void runCheckTranscript(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.MyTrainingHistory"), this);

		By by = By.id("RESULTS_PER_PAGE");
		Navigator.explicitWait(1000);
		WebDriverUtils.select_selector(driver, by, "200");
		Navigator.explicitWait(1000);

		String[] Progs = null;

		if (!(this.getModuleLevel().trim().equals(""))) {
			Progs = this.getModuleLevel().split("/");
		}
		this.RT_ExpandModules(driver, Progs, "");

		String module_row = "//tr[descendant::td/div/a[text()='"
				+ this.getModuleTitle() + "']]";

		Navigator.explicitWait();
		// 1. Check status
		if (!this.getStatus().equals("")) {
			by = By.xpath(module_row + "/td[4]");
			String status = WebDriverUtils.getTextWithoutChecking(driver, by);
			JUnitAssert.assertEquals(this.getStatus(), status);
		}

		// 2.Check score
		if (!this.getScore().equals("")) {
			by = By.xpath(module_row + "/td[6]");
			String score = WebDriverUtils.getTextWithoutChecking(driver, by);
			JUnitAssert.assertEquals(this.getScore(), score);
		}

		// 2.Check credits
		if (!this.getCredits().equals("")) {
			by = By.xpath(module_row + "/td[7]");
			String credit = WebDriverUtils.getTextWithoutChecking(driver, by);
			JUnitAssert.assertEquals(this.getCredits(), credit);
		}

	}
	/**
	 * 
	 * @param driver
	 * @param ArrayProgs e.g. Prog_Composite/Prog_Child
	 * @param action  e.g. KC 
	 */
	private void CLM_ExpandModules(WebDriver driver, String ArrayProgs[],
			String action) {
		By by = null;
		if (ArrayProgs.length == 2) {
			// Composite Program
			// expand first level
			by = By.xpath("//li[descendant::div[@class='module-info' and ./h4[contains(text(),'"
					+ ArrayProgs[0]
					+ "')]]]/div[descendant::a/span[contains(text(),'Show Sub-Modules')]]/a");
			WebDriverUtils.clickLink(driver, by);
			// expand second level
			by = By.xpath("//div[@class='submodule-list floor1']/div/div/ul/li[descendant::div[@class='module-info' and ./h4[contains(text(),'"
					+ ArrayProgs[1]
					+ "')]]]/div[descendant::a/span[contains(text(),'Show Sub-Modules')]]/a");
			WebDriverUtils.clickLink(driver, by);
			if (action.equals("KC")) {
				by = By.xpath("//div[@class='submodule-list floor2' and descendant::div/h4[text()='"
						+ this.getModuleTitle()
						+ "']]/div/div/ul/li/div[@class='module-actions']/a");
			}
		} else if (ArrayProgs.length == 1) {
			// Simple Program
			// expand first level
			by = By.xpath("//li[descendant::div[@class='module-info' and ./h4[contains(text(),'"
					+ ArrayProgs[0]
					+ "')]]]/div[descendant::a/span[contains(text(),'Show Sub-Modules')]]/a");
			WebDriverUtils.clickLink(driver, by);
			if (action.equals("KC")) {
				by = By.xpath("//div[@class='submodule-list floor1' and descendant::div/h4[text()='"
						+ this.getModuleTitle()
						+ "']]/div/div/ul/li/div[@class='module-actions']/a");
			}
		} else {

			if (action.equals("KC")) {
				by = By.xpath("//li[descendant::div/h4[text()='"
						+ this.getModuleTitle()
						+ "']]/div[@class='module-actions']/a");
			}
		}
		// Click KC link
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);
	}
	/**
	 * 
	 * @param driver
	 * @param ArrayProgs  e.g. Prog_Composite/Prog_Child
	 * @param action 
	 */
	private void RT_ExpandModules(WebDriver driver, String ArrayProgs[],
			String action) {
		By by = null;
		if (ArrayProgs.length == 2) {
			// Composite Program
			// expand first level
			by = By.xpath("//td[descendant::div/a/span[contains(text(),'Prog_Composite_UMA T')]]/a");
//			Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait();
			WebDriverUtils.clickLink(driver, by);
			
			// expand second level
			by = By.xpath("//td[@class='title-column floor2' and descendant::div/a/span[contains(text(),'Prog_4Prog_UMA T')]]/a");
//			Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait();
			WebDriverUtils.clickLink(driver, by);
			

		} else if (ArrayProgs.length == 1) {
			// Simple Program
			// expand first level
			by = By.xpath("//li[descendant::div[@class='module-info' and ./h4[contains(text(),'"
					+ ArrayProgs[0]
					+ "')]]]/div[descendant::a/span[contains(text(),'Show Sub-Modules')]]/a");
//			Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.explicitWait();
			WebDriverUtils.clickLink(driver, by);


		}
	}
	

}
