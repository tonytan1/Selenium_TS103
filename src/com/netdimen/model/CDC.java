package com.netdimen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.controller.TestDriver;
import com.netdimen.dao.DBUser;
import com.netdimen.dao.DBUserDAO;
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
public class CDC extends com.netdimen.abstractclasses.TestObject {
	private String RevieweeID = "", RevieweeName = "", Org = "",
			ModuleTitle = "", Cert = "", Competency = "", JobProfile = "",
			LearningPathImageFile_Reviewee = "",
			LearningPathImageFile_Reviewer = "", AssessmentID = "",
			Reviewer_Assessment = "", ExpectedResult_CP = "",
			ExpectedResult_JP = "", ExpectedResult_Module = "",
			CPAcquisitionMethod = "";
	
	private static final String Proceed_Comp_Assessment = Config.getInstance()
			.getProperty("button.proceed_to_comp_assessment");
	
	public String getCPAcquisitionMethod() {
		return CPAcquisitionMethod;
	}

	public void setCPAcquisitionMethod(String cPAcquisitionMethod) {
		CPAcquisitionMethod = cPAcquisitionMethod;
	}

	private DBUser reviewDBUser;

	public DBUser getReviewDBUser() {
		return reviewDBUser;
	}

	public void setReviewDBUser(DBUser reviewDBUser) {
		this.reviewDBUser = reviewDBUser;
	}

	public String getExpectedResult_Module() {
		return ExpectedResult_Module;
	}

	public void setExpectedResult_Module(String expectedResult_Module) {
		ExpectedResult_Module = expectedResult_Module;
	}

	public String getExpectedResult_CP() {
		return ExpectedResult_CP;
	}

	public void setExpectedResult_CP(String expectedResult_CP) {
		ExpectedResult_CP = expectedResult_CP;
	}

	public String getModuleTitle() {
		return ModuleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		ModuleTitle = moduleTitle;
	}

	public String getAssessmentID() {
		return AssessmentID;
	}

	public void setAssessmentID(String assessmentID) {
		AssessmentID = assessmentID;
	}

	public String getReviewer_Assessment() {
		return Reviewer_Assessment;
	}

	public void setReviewer_Assessment(String reviewer_Assessment) {
		Reviewer_Assessment = reviewer_Assessment;
	}

	public String getLearningPathImageFile_Reviewee() {
		return LearningPathImageFile_Reviewee;
	}

	public void setLearningPathImageFile_Reviewee(
			String learningPathImageFile_Reviewee) {
		LearningPathImageFile_Reviewee = learningPathImageFile_Reviewee;
	}

	public String getLearningPathImageFile_Reviewer() {
		return LearningPathImageFile_Reviewer;
	}

	public void setLearningPathImageFile_Reviewer(
			String learningPathImageFile_Reviewer) {
		LearningPathImageFile_Reviewer = learningPathImageFile_Reviewer;
	}

	public String getRevieweeID() {
		return RevieweeID;
	}

	public void setRevieweeID(String revieweeID) {
		RevieweeID = revieweeID;
		DBUserDAO dbUserDAO = new DBUserDAO(TestDriver.dbManager.getConn());
		DBUser revieweeDBUser = dbUserDAO.findByUserId(revieweeID);
		this.setReviewDBUser(revieweeDBUser);
	}

	public String getRevieweeName() {
		return RevieweeName;
	}

	public void setRevieweeName(String revieweeName) {
		RevieweeName = revieweeName;
	}

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public CDC() {
		super();
	}

	public String getOrg() {
		return Org;
	}

	public String getCert() {
		return Cert;
	}

	public String getCompetency() {
		return Competency;
	}

	public String getJobProfile() {
		return JobProfile;
	}

	public void setOrg(String org) {
		Org = org;
	}

	public void setCert(String cert) {
		Cert = cert;
	}

	public void setCompetency(String competency) {
		Competency = competency;
	}

	public void setJobProfile(String jobprofile) {
		JobProfile = jobprofile;
	}

	public void setReviewee_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setOrg_UI(WebDriver driver, String str) {
		By by = null;
		if (!str.equals("")) {
			by = By.linkText("Organization");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.checkSelect_Radio(driver, str);
			WebDriverUtils.switchToParentWin(driver);
		}

		by = By.name("apply-filters");
		WebDriverUtils.clickButton(driver, by);
	}

	public void setModule_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setCert_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setCompetency_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setJobProfile_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void setPagination_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("RESULTS_PER_PAGE");
			WebDriverUtils.select_selector(driver, by, str);
			Navigator.explicitWait(1000);
		}
	}

	/**
	 * Direct Appraiser assigns goals (performance goal or development goal) to
	 * reviewee in CDC
	 * 
	 * @param driver
	 * @param goal
	 *            : goal object (performance goal or development goal)
	 */
	public void runDeployGoal_CDC(WebDriver driver, ArrayList<TestObject> goals) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.OrgUserReview"));
		for (TestObject goal : goals) {
			/*
			 * //1. Setup org. this.setOrg_UI(driver, this.getOrg());
			 * Navigator.explicitWait(1000); //2. Setup pagination
			 * this.setPagination_UI(driver, "200");
			 * Navigator.explicitWait(1000);
			 */

			this.filterUserInCDCByUserName(driver, this.getRevieweeName());

			// 3. Select the reviewee
			this.selectReviewee_CDC_UI(driver, this.getRevieweeName());
			Navigator.explicitWait(1000);
			// 4. Deploy goal
			By by = By.id("reviewActionDropdown");
			String str = "Assign ";
			Goal goal_ins = null;
			if (goal instanceof PerformanceGoal) {
				goal_ins = (PerformanceGoal) goal;
				str += Config.getInstance().getProperty("label.personal_goal");
			} else if (goal instanceof DevelopmentGoal) {
				goal_ins = (DevelopmentGoal) goal;
				str += Config.getInstance().getProperty("label.development_goal");
			} else {
				if (Config.DEBUG_MODE) {
					System.out.println("Should pass in a Goal-type instance");
				}
			}
			WebDriverUtils.select_selector(driver, by, str);
			Navigator.explicitWait(1000);
			if (goal_ins != null) {
				// 5. create goal
				goal_ins.runCreateWithoutNavigation(driver);
				
				//confirm send notification
				final By by_final = By.xpath("//button[descendant::span[text()='OK']]");
				WebDriverUtils.clickButton(driver, by_final);
				
	
				// 6. Check output
				// logon as reviewee to check goal if created successfully
				
//				User user = new User(this.getRevieweeID(), this.getPWD());
//				User.logout(driver);
//				user.login(driver);
//				
//				if (goal instanceof PerformanceGoal) {
//					((PerformanceGoal)goal_ins).runCheckGoalExisted(driver);
//				}else if (goal instanceof DevelopmentGoal) {
//					((DevelopmentGoal)goal_ins).runCheckGoalExisted(driver);
//				}
//				User.logout(driver);
//				User user2 = new User(this.getUID(), this.getPWD());
//				user2.login(driver);
			}
		}
	}

	/**
	 * Can delete the goal if the user is the creator and the goal is not
	 * included in the appraisal
	 * 
	 * @param driver
	 * @param goals
	 */
	public void runCheckCanDeleteGoal_CDC(WebDriver driver,
			ArrayList<TestObject> goals) {
		for (TestObject goal : goals) {
			// 1. Goto CDC->Learning Center of a reviewee
			this.gotoCDC_Reviewee_UI(driver, "CareerCenter",
					this.getRevieweeName());

			// 2. Click Career Center -> Goals
			By by = By.linkText("Goals");
			WebDriverUtils.clickLink(driver, by);

			Goal goal_ins = null;
			if (goal instanceof PerformanceGoal) {
				by = By.xpath("//a[descendant::span[text()='"+Config.getInstance().getProperty("tab.personal_goal.name")+"']]");
				goal_ins = (PerformanceGoal) goal;
			} else if (goal instanceof DevelopmentGoal) {
				by = By.xpath("//a[descendant::span[text()='"+Config.getInstance().getProperty("tab.development_goal.name")+"']]");
				goal_ins = (DevelopmentGoal) goal;
			} else {
				if (Config.DEBUG_MODE) {
					System.out.println("Should pass in a Goal-type instance");
				}
			}
			WebDriverUtils.clickButton(driver, by);
			// Navigator.explicitWait(1000);
			if (goal_ins != null) {
				// 5. Select goal
				goal_ins.checkCanDelete(driver);
			}
			WebDriverUtils.closeAllPopUpWins(driver);
		}
	}

	/**
	 * Cannot delete the goal if the user is NOT the creator or the goal is
	 * included in the appraisal
	 * 
	 * @param driver
	 * @param goal
	 */
	public void runCheckCannotDeleteGoal_CDC(WebDriver driver,
			ArrayList<TestObject> goals) {
		for (TestObject goal : goals) {
			// 1. Goto CDC->Learning Center of a reviewee
			this.gotoCDC_Reviewee_UI(driver, "CareerCenter",
					this.getRevieweeName());

			// 2. Click Career Center -> Goals
			By by = By.linkText("Goals");
			WebDriverUtils.clickLink(driver, by);

			Goal goal_ins = null;
			if (goal instanceof PerformanceGoal) {
				by = By.xpath("//a[descendant::span[text()='"+Config.getInstance().getProperty("tab.personal_goal.name")+"']]");
				goal_ins = (PerformanceGoal) goal;
			} else if (goal instanceof DevelopmentGoal) {
				by = By.xpath("//a[descendant::span[text()='"+Config.getInstance().getProperty("tab.development_goal.name")+"']]");
				goal_ins = (DevelopmentGoal) goal;
			} else {
				if (Config.DEBUG_MODE) {
					System.out.println("Should pass in a Goal-type instance");
				}
			}
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);
			if (goal_ins != null) {
				// 5. Select goal
				goal_ins.checkCannotDelete(driver);
			}
			WebDriverUtils.closeAllPopUpWins(driver);
		}
	}

	/**
	 * Direct Appraiser filter reviewee, deploys performance assessment in Team Review.
	 * Requirement: on Team Review page
	 * @param driver
	 * @param reviewee
	 * @param assessmentID
	 * @param secondaryReviewer
	 * @param adHocCompetency
	 */
	public void runDeployAssessment_CDC(WebDriver driver, String reviewee,
			String assessmentID, String secondaryReviewer,
			String adHocCompetency) {

	
		/*
		 * //1. Setup org. this.setOrg_UI(driver, this.getOrg());
		 * Navigator.explicitWait(1000); //2. Setup pagination
		 * this.setPagination_UI(driver, "200"); Navigator.explicitWait(1000);
		 */

		this.filterUserInCDCByUserName(driver, reviewee);

		// 3. Select the reviewee
		this.selectReviewee_CDC_UI(driver, reviewee);
		Navigator.explicitWait(1000);
		// 4. Deploy assessment
		By by = By.id("reviewActionDropdown");
		String str = "Deploy Assessment";
		WebDriverUtils.select_selector(driver, by, str);

		// 5. Select assessment
		by = By.name("templateId");
		WebDriverUtils.select_selector(driver, by, assessmentID);
		by = By.id("nextButton");
		WebDriverUtils.clickButton(driver, by);

		// 6. Select secondary reviewers
		by = By.id("Users-button-id");
		WebDriverUtils.clickLink(driver, by);

		ArrayList<String> Reviewers = new ArrayList<String>();
		Reviewers.add(secondaryReviewer);
		WebDriverUtils.searchSelect_Selector(driver,
				WebDriverUtils.PopUpSelector.InnerUserSelector, Reviewers);
		Navigator.explicitWait(1000);

		// 7. Select ad-hoc competency
		if (adHocCompetency.equals("")) {
			// 8. Save
			by = By.id("finishButton");
			WebDriverUtils.clickButton(driver, by);
		} else {
			by = By.id("nextButton");
			WebDriverUtils.clickButton(driver, by);
			Navigator.explicitWait(1000);

			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add(adHocCompetency);
			UIFunctionUtils.searchInAjaxSearchBox(driver, keywords);
			Navigator.explicitWait(1000);

			by = By.id("finishButton");
			WebDriverUtils.clickButton(driver, by);
		}

	
		// 9. Go to Competency Assessment
		by = By.xpath("//button[descendant::span[contains(text(),'"+Proceed_Comp_Assessment+"')]]");
		WebDriverUtils.clickButton(driver, by);
		//JUnitAssert.assertTrue(size > 0, "Do not include goal:"+ by);
	}

	/**
	 * Select a single reviewee in CDC
	 * 
	 * @param driver
	 * @param reviewee
	 *            : user name (last name + family name)
	 */
	public void selectReviewee_CDC_UI(WebDriver driver, String reviewee) {
		if (!reviewee.equals("")) {
			String user_info_row = "//div[@id='" + this.reviewDBUser.getEKPID()
					+ "']";
			By by = By.xpath(user_info_row
					+ "/div/div[@class='user-checkbox']/input");
			//Navigator.waitForAjax(driver, by);
			WebDriverUtils.check_checkbox(driver, by);
		}
	}

	/**
	 * Martin: set action for a single reviewee in CDC
	 * 
	 * @param driver
	 * @param reviewee
	 *            : user name (last name + family name)
	 * @param action
	 *            :Review Employee Profile, Review Learning Center, Review
	 *            Career Center, Deploy Assessment, Assign Performance Goal,
	 *            Assign Development Goal, Assign competency, Assign learning
	 *            module
	 */
	public void setAction_Reviewee_CDC_UI(WebDriver driver, String reviewee,
			String action) {
		if (!reviewee.equals("") && !action.equals("")) {
			// 1. select reviewee;
			this.selectReviewee_CDC_UI(driver, reviewee);

			// 2. mouse-over the gear button
			String user_info_row = "//div[@id='" + this.reviewDBUser.getEKPID()
					+ "']";

			By by = By.xpath(user_info_row
					+ "/div/div[@class='user-action']/div/button");
			WebDriverUtils.mouseOver(driver, by);
			;

			// 3. set action
			by = By.linkText(action);
			WebDriverUtils.clickLink(driver, by);
		}
	}

	/**
	 * Select a number of reviewees in CDC
	 * 
	 * @param driver
	 * @param reviewees
	 *            : an array to keep user names (last name + family name)
	 */
	public void selectReviewee_CDC_UI(WebDriver driver, String[] reviewees) {
		if (reviewees.length > 0) {
			for (String reviewee : reviewees) {
				this.selectReviewee_CDC_UI(driver, reviewee);
			}
		}
	}

	/**
	 * Navigate to CDC -> Learning Center, Employee Profile, or Career Center
	 * for a specific user
	 * 
	 * @param driver
	 * @param url
	 *            : LearningCenter, EmployeeProfile, or CareerCenter
	 * @param reviewee
	 *            : user name (last name + family name)
	 */
	public void gotoCDC_Reviewee_UI(WebDriver driver, String url,
			String reviewee) {
		if (!url.equals("") && !reviewee.equals("")) {
			Navigator
					.navigate(driver, Navigator.webElmtMgr
							.getNavigationPathList("LearningCenter",
									"3.OrgUserReview"));

			/*
			 * //1. Setup org. this.setOrg_UI(driver, this.getOrg());
			 * Navigator.explicitWait(1000); //2. Setup pagination
			 * this.setPagination_UI(driver, "200");
			 * Navigator.explicitWait(1000);
			 */

			this.filterUserInCDCByUserName(driver, reviewee);
			// 3. Goto CDC-> Learning Center, Employee Profile, or Career Center
			By by = null;

			String user_info_row = "//div[@id='" + this.reviewDBUser.getEKPID()
					+ "']";

			String path_target = user_info_row
					+ "/div/div[@class='user-action-link']";
			if (url.equalsIgnoreCase("LearningCenter")) {
				path_target += "/a[2]";
			} else if (url.equalsIgnoreCase("EmployeeProfile")) {
				path_target += "/a[1]";
			} else if (url.equalsIgnoreCase("CareerCenter")) {
				path_target += "/a[3]";
			}
			by = By.xpath(path_target);
			WebDriverUtils.clickLink(driver, by);

			WebDriverUtils.switchToPopUpWin(driver);
		}
	}

	/**
	 * Check CDC -> Learning Center -> Learning Path
	 * 
	 * @param driver
	 */
	public void runCheckLearningPath_LearningCenter_CDC(WebDriver driver) {
		// 1. Goto CDC-> Learning Center of a reviewee
		this.gotoCDC_Reviewee_UI(driver, "LearningCenter",
				this.getRevieweeName());

		// 2. Click Learning Center -> Learning Path
		By by = By.linkText("Learning Path");
		Navigator.explicitWait(1000);
		// Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickLink(driver, by);

		// 3. Check learning path: Sikuli Image-based comparison
		Navigator.explicitWait(1000);
		String screenshotFile = this.getLearningPathImageFile_Reviewer();
		boolean exist = SikuliUtils.screenshotExistInWin(screenshotFile);
		JUnitAssert.assertTrue(exist, "Cannot find image in screenshot:"
				+ screenshotFile);

		WebDriverUtils.closeAllPopUpWins(driver);
	}

	private void filterUserInCDCByUserName(WebDriver driver, String userName) {
		String[] userNames = userName.split(" ");
		String lastName = userNames[0];
		String firstName = userNames[1];
		By by = By.id("FIRSTNAME");
		WebDriverUtils.fillin_textbox(driver, by, firstName);

		by = By.id("LASTNAME");
		WebDriverUtils.fillin_textbox(driver, by, lastName);

		by = By.name("apply-filters");
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait();
	}

	/**
	 * Drill down job profile -> Competency in CDC to check module status for a
	 * specific reviewee.
	 * 
	 * @param driver
	 * @param JobProfile
	 * @param Competency
	 * @param LearningModule
	 * @param reviewee
	 */
	public void runCheckCP_DrillDown(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.OrgUserReview"));

		this.filterUserInCDCByUserName(driver, this.getRevieweeName());

		/*
		 * User reviewee = new
		 * User(this.getRevieweeID().toLowerCase(),this.getPWD());
		 * reviewee.setName(this.getRevieweeName()); //1. Checkout reviewee's
		 * learning path // Comment it out as it is not stable for image compare
		 * to check learning path
		 * reviewee.setLearningPathImageFile(this.getLearningPathImageFile_Reviewee
		 * ()); reviewee.runCheckLearningPath(driver);
		 * 
		 * //2. login as reviewer User reviewer = new User(this.getUID(),
		 * this.getPWD()); TestDriver.switchUser(reviewer);
		 */

		// 3. check module status based on job profile drill-down in CDC
		this.runCheckDrillDown(driver, this.getJobProfile(),
				this.getCompetency(), this.getCPAcquisitionMethod(),
				this.getModuleTitle());

		// 4. check Learning path in CDC -> Learning Center
		/* this.runCheckLearningPath_LearningCenter_CDC(driver); */
	}

	public String getExpectedResult_JP() {
		return ExpectedResult_JP;
	}

	public void setExpectedResult_JP(String expectedResult_JP) {
		ExpectedResult_JP = expectedResult_JP;
	}

	/**
	 * Check competency progress in CDC -> Learning Center -> Summary
	 * 
	 * @param driver
	 */
	public void runCheckCompetency_LearningCenter_CDC(WebDriver driver) {
		// 1. Goto CDC->Learning Center of a reviewee
		this.gotoCDC_Reviewee_UI(driver, "LearningCenter",
				this.getRevieweeName());

		// 2. Click Learning Center -> Learning Path
		By by = By.linkText("Summary");
		WebDriverUtils.clickLink(driver, by);

		// 3. Check out progress of a competency
		by = By.xpath("//tr[descendant::td/div/a[text()='"
				+ this.getCompetency() + "']]/td[2]/span/span");
		String str = WebDriverUtils.getText(driver, by);
		String expectedStr = "25%";
		JUnitAssert.assertEquals(expectedStr, str);

		WebDriverUtils.closeAllPopUpWins(driver);
	}

	/**
	 * Check competency assessessment in CDC -> Career Center -> Competencies ->
	 * Competency Assessments
	 * 
	 * @param driver
	 */
	public void runCheckCompetencyAssessments_CareerCenter_CDC(WebDriver driver) {
		// 1. Goto CDC->Career Center of a reviewee
		this.gotoCDC_Reviewee_UI(driver, "CareerCenter", this.getRevieweeName());

		// 2. Click Career Center -> Competencies
		By by = By.linkText("Competencies");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);

		// 3. Check out progress of a competency
		by = By.xpath("//tr[descendant::td[text()='" + this.getAssessmentID()
				+ "']]/td/a");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait();
		// Navigator.waitForAjaxElementLoad(driver, by);
		HashMap<String, ArrayList<String>> CP_Proficiencies = UIFunctionUtils
				.parseParticipants(this.getExpectedResult());

		Iterator<String> CPs = CP_Proficiencies.keySet().iterator();

		while (CPs.hasNext()) {
			String CP = CPs.next();

			ArrayList<String> Proficiencies = CP_Proficiencies.get(CP);
			for (String proficiencyStr : Proficiencies) {
				String[] proficiencyArray = proficiencyStr.split(";");

				String expectedProficiency = proficiencyArray[0];
				Navigator.explicitWait();
				by = By.xpath("//div[descendant::div/span[text()='"
						+ CP
						+ "']]/div[@class='competency-gap']/div[@class='text-right padding-right-10']");
				String actualProficiency = WebDriverUtils.getText(driver, by);
				JUnitAssert.assertTrue(
						actualProficiency.contains(expectedProficiency),
						"Actual Proficiency:(" + actualProficiency
								+ ") for Competency (" + CP
								+ ") does not match with expected proficiency:"
								+ expectedProficiency);
			}
		}
		WebDriverUtils.closeAllPopUpWins(driver);
	}
/*
	public void runCheckCP_AcquisitionMethod_DrillDown(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.TeamReview"));
		this.runCheckDrillDown(driver, this.getJobProfile(),
				this.getCompetency(), this.getCPAcquisitionMethod(),
				this.getModuleTitle());

	}*/

	public void runCheckDrillDown(WebDriver driver, String JobProfile,
			String Competency, String AcqMethod, String LearningModule) {

		String user_info_row = "//div[@id='" + this.reviewDBUser.getEKPID()
				+ "']";

		if (!JobProfile.equals("") && !Competency.equals("")
				&& !this.reviewDBUser.getEKPID().equals("")) {

			// 1. Expand job profile for a specific user
			By by = By.xpath(user_info_row + "/div/div[@class='user-info']/i");
			// Navigator.waitForAjaxElementLoad(driver, by);
			//Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickLink(driver, by);

			// 2. Further expand the job profile
			String job_profile_row = user_info_row
					+ "/div[@class='job-profile-summary-info']/ul/li[@class='data-job-profile' and ./div[contains(text(),'"
					+ JobProfile + "')]]";
			by = By.xpath(job_profile_row + "/div/i[1]");
			// Navigator.waitForAjaxElementLoad(driver, by);
			Navigator.waitForAjax(driver, by);
			WebDriverUtils.clickLink(driver, by);

			// 2.1. check job profile status
			by = By.xpath(job_profile_row + "/div/i[2]");
			// Navigator.waitForAjaxElementLoad(driver, by);
			String attr = "title";
			Navigator.waitForAjax(driver, by);

			String status = WebDriverUtils.getAttribute(driver, by, attr);
			JUnitAssert.assertEquals(this.getExpectedResult_JP(), status);

			// 2.2 check competency acquisition method

			if (!AcqMethod.equals("")) {
				String competency_row = job_profile_row
						+ "/ul[@class='competency-list display-none']/li[@class='data-competency' and ./div[contains(text(),'"
						+ Competency + "')]]";
				by = By.xpath(competency_row
						+ "/div/span/div[contains(text(),'" + AcqMethod + "')]");
				int size = WebDriverUtils.getHowManyByPresntInPage(by);
				JUnitAssert.assertTrue(size > 0,
						"Acquisition Method is shown properly" + Competency);

			}

			if (!LearningModule.equals("")) {
				// 3. Expand competency
				String competency_row = job_profile_row
						+ "/ul[@class='competency-list display-none']/li[@class='data-competency' and ./div[contains(text(),'"
						+ Competency + "')]]";
				by = By.xpath(competency_row + "/div/i[1]");
				//Navigator.waitForAjax(driver, by);
				// Navigator.waitForAjaxElementLoad(driver, by);
				WebDriverUtils.clickLink(driver, by);

				// 3.1. check competency status
				by = By.xpath(job_profile_row + "/div/i[2]");
				// Navigator.waitForAjaxElementLoad(driver, by);
				attr = "title";
				Navigator.waitForAjax(driver, by);

				status = WebDriverUtils.getAttribute(driver, by, attr);
				JUnitAssert.assertEquals(this.getExpectedResult_CP(), status);

				// 4. Check learning module
				String module_row = competency_row
						+ "/ul[@class='learning-module-list display-none']/li[@class='data-learning-module' and ./div[contains(text(),'"
						+ LearningModule + "')]]";
				by = By.xpath(module_row + "/div/i[2]");
				// Navigator.explicitWait();
				// Navigator.waitForAjaxElementLoad(driver, by);
				attr = "title";

				Navigator.waitForAjax(driver, by);
				status = WebDriverUtils.getAttribute(driver, by, attr);
				JUnitAssert.assertEquals(this.getExpectedResult_Module(),
						status);
			}
		}

	}
	public void runAwardAdHocCP(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "3.OrgUserReview"));

		this.filterUserInCDCByUserName(driver, this.getRevieweeName());

		// 3. Select the reviewee
		this.selectReviewee_CDC_UI(driver, this.getRevieweeName());
		Navigator.explicitWait(1000);

		By by = By.id("reviewActionDropdown");
		String str = "Assign competency";
		WebDriverUtils.select_selector(driver, by, str);
		Navigator.explicitWait(1000);

		by=By.xpath("//tr[descendant::td[contains(text(),'"+this.getCompetency()+"')]]/td[3]/label/select");
		System.out.println(by);
		WebDriverUtils.clickLink(driver, by);
		
		by = By.xpath("//div[descendant::span[text()='"+this.getCompetency()+"']]/table[@id='proficiencyLevelTable']/tbody/tr/td[contains(text(),'"
				+ "4" + "')]");

		Navigator.explicitWait();
	//	Navigator.waitForAjaxElementLoad(driver, by);
		WebDriverUtils.clickLink(driver, by);
		
		by = By.name("saveButton");
		WebDriverUtils.clickButton(driver, by);
		
	} 
}