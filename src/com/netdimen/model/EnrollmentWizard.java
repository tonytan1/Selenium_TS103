package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class EnrollmentWizard extends TestObject {

	private String	ModuleID="", ModuleTitle="", Participants ="",	Status="";

	private static final String Success_Msg=Config.getInstance().getProperty("label.numberUserComplete").replace("\\", "");
	private static final String Failed_Msg=Config.getInstance().getProperty("label.numberUserFail").replace("\\", "");
	private static final String Entry_Form=Config.getInstance().getProperty("label.UserIdDirectForm");
	
	public String getModuleID() {
		return ModuleID;
	}

	public void setModuleID(String moduleID) {
		ModuleID = moduleID;
	}

	public String getModuleTitle() {
		return ModuleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		ModuleTitle = moduleTitle;
	}

	public String getParticipants() {
		return Participants;
	}

	public void setParticipants(String participants) {
		Participants = participants;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void runChangeStatus(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.EnrollWizard"), this);

		By by = By.id("selParticipantLink");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		by = By.xpath("//span[contains(text(),'"+Entry_Form+"')]");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		String SearchText = this.getParticipants().replaceAll(";", "\n");
		by = By.id("IDLIST");
		WebDriverUtils.fillin_textbox(driver, by, SearchText);

		by = By.name("VALIDATEBUTTON");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		by = By.name("SAVEBUTTON");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		by = By.name("save");
		WebDriverUtils.clickButton(driver, by);

		// Select Command
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		by = By.id("selAction");
		String str = "Change Enrollment Status";
		WebDriverUtils.select_selector(driver, by, str);

		// Select Modules
		by = By.id("selModulesLink");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		String module = this.getModuleID();
		String[] modules = module.split(";");
		WebDriverUtils.searchSelect_Selector(driver, modules);

		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		by = By.id("selAction");
		str = "Change Enrollment Status";
		WebDriverUtils.select_selector(driver, by, str);
		Navigator.explicitWait(1000);

		by = By.id("selChangeEnrollOptions");
		str = "Change overall status";
		WebDriverUtils.select_selector(driver, by, str);

		Navigator.explicitWait(1000);
		by = By.id("SUPPRESSEMAIL");
		WebDriverUtils.check_checkbox(driver, by);

		by = By.id("selTargetStatus");
		str = this.getStatus();
		WebDriverUtils.select_selector(driver, by, str);

		by = By.name("execute");
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(3000);

		int expectedPassed = this.getParticipants().split(";").length; 
		this.checkPassedResult_EnrollmentWizard_UI(driver, expectedPassed+"");
	}

	/**Check out the result of passed enrollment
	 * 
	 * @param driver
	 * @param expectedPassed
	 */
	private void checkPassedResult_EnrollmentWizard_UI(WebDriver driver, String expectedPassed){
		By by = By.xpath("//tr[descendant::td[contains(text(),'"+Success_Msg+"')]]/td[2]");
		String failed = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertEquals(expectedPassed, failed);
	}
	
	/**check out the result of failed enrollment
	 * 
	 * @param driver
	 */
	private void checkFailedResult_EnrollmentWizard_UI(WebDriver driver, String expectedFailed) {		
		By by = By.xpath("//tr[descendant::td[contains(text(),'"+Failed_Msg+"')]]/td[2]");
		String failed = WebDriverUtils.getText(driver, by);
		JUnitAssert.assertEquals(expectedFailed, failed);
	}

	public void runEnroll(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.EnrollWizard"), this);

		By by = By.id("selParticipantLink");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		by = By.xpath("//span[contains(text(),'UserID Direct Entry Form')]");
		WebDriverUtils.clickButton(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		String SearchText = this.getParticipants().replaceAll(";", "\n");
		by = By.id("IDLIST");
		WebDriverUtils.fillin_textbox(driver, by, SearchText);

		by = By.name("VALIDATEBUTTON");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);
		by = By.name("SAVEBUTTON");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		by = By.name("save");
		WebDriverUtils.clickButton(driver, by);

		// Select Command
		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		by = By.id("selAction");
		String str = "Enroll";
		WebDriverUtils.select_selector(driver, by, str);
		
		Navigator.explicitWait(1000);
		// Select Modules
		by = By.id("selModulesLink");
		WebDriverUtils.clickLink(driver, by);
		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		
		String module = this.getModuleID();
		String[] modules = module.split(";");
		WebDriverUtils.searchSelect_Selector(driver, modules);

		WebDriverUtils.switchToParentWin(driver);
		Navigator.explicitWait(1000);
		
		by = By.id("SUPPRESSEMAIL");
		WebDriverUtils.check_checkbox(driver, by);
		by = By.name("execute");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);

		// check out the result
		this.checkFailedResult_EnrollmentWizard_UI(driver,"0");
	}


	@Override
	public boolean equals(TestObject obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
/*	public String toString(){
		return new StringBuilder().append(this.getClass().getName())
				.append("_").append(this.getFuncType()).append("_").
				append(this.getModuleID()).
				append("_").
				append(this.getParticipants()).
				append("_").
				append(this.getStatus()).
				toString();
	}*/

}
