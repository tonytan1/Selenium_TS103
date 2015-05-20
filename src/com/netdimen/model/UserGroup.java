package com.netdimen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Config;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.UIFunctionUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 * 
 */
public class UserGroup extends com.netdimen.abstractclasses.TestObject {
	private String UserGroupName = "", LogicalDomain = "", Criterion = "",
			Permission = "";

	private static final String Heading_Org_Attrs = Config.getInstance()
			.getProperty("heading.org.attrs");

	private static final String Create_User_Group = Config.getInstance()
			.getProperty("button.CreateNewUserGroup");

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public UserGroup() {
		super();
	}

	public String getUserGroupName() {
		return UserGroupName;
	}

	public String getLogicalDomain() {
		return LogicalDomain;
	}

	public String getCriterion() {
		return Criterion;
	}

	public String getPermission() {
		return Permission;
	}

	public void setUserGroupName(String usergroupname) {
		UserGroupName = usergroupname;
	}

	public void setLogicalDomain(String logicaldomain) {
		LogicalDomain = logicaldomain;
	}

	public void setCriterion(String criterion) {
		Criterion = criterion;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public void setUserGroupName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.id("GROUPNAME");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setLogicalDomain_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.name("DOMAIN");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setPermission_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	/**
	 * Check user group visibility of User Group
	 * 
	 * @param driver
	 */
	public void runCheckVisibility(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.UserGroup"));

		HashMap<String, ArrayList<String>> criteria_userGroups = UIFunctionUtils
				.parseParticipants(this.getExpectedResult());
		Iterator<String> criteria = criteria_userGroups.keySet().iterator();
		// 1. Uncheck to show all my user groups
		By by = By.id("MYGROUP");
		WebDriverUtils.uncheck_checkbox(driver, by);
		by = By.name("apply-filters");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);

		// 2. Check visibility
		while (criteria.hasNext()) {
			String criterion = criteria.next();
			ArrayList<String> userGroups = criteria_userGroups.get(criterion);
			for (String userGroupStr : userGroups) {
				for (String userGroup : userGroupStr.split(";")) {
					// 2.2 check user visibility
					by = By.linkText(userGroup);
					int size = WebDriverUtils.getHowManyByPresntInPage(by);
					switch (criterion.toLowerCase()) {
					case "visible":
						JUnitAssert.assertTrue(size > 0,
								"Cannot find user group:" + userGroup);
						break;
					case "invisible":
						JUnitAssert.assertTrue(size == 0,
								"Can find user group:" + userGroup);
						break;
					}
				}
			}
		}
	}

	/**
	 * Check membership of user group in User Editor
	 * 
	 * @param driver
	 */
	public void runCheckMembership(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.UserEditor"));
		WebDriverUtils.switchToPopUpWin(driver);
		// 1. Goto User Editor -> User Group
		WebDriverUtils.switchToFrame(driver, "USERTREE");
		Navigator.explicitWait(1000);
		By by = By.linkText("User Group");
		WebDriverUtils.clickLink(driver, by);
		Navigator.explicitWait(1000);

		// 2. Save changes
		by = By.name("SELECTEDGROUP");
		WebDriverUtils.select_selector(driver, by, this.getUserGroupName());
		Navigator.explicitWait(1000);

		// 3. Check visibility
		HashMap<String, ArrayList<String>> criteria_users = UIFunctionUtils
				.parseParticipants(this.getExpectedResult());
		Iterator<String> criteria = criteria_users.keySet().iterator();

		while (criteria.hasNext()) {
			String criterion = criteria.next();
			ArrayList<String> users = criteria_users.get(criterion);
			for (String userStr : users) {
				for (String user : userStr.split(";")) {
					// 2.2 check user visibility
					by = By.partialLinkText("(" + user.toUpperCase() + ")");
					int size = WebDriverUtils.getHowManyByPresntInPage(by);
					switch (criterion.toLowerCase()) {
					case "visible":
						JUnitAssert.assertTrue(size > 0,
								"Cannot find user: " + user + " in user group:"
										+ this.getUserGroupName());
						break;
					case "invisible":
						JUnitAssert.assertTrue(size == 0,
								"Can find user: " + user + " in user group:"
										+ this.getUserGroupName());
						break;
					}
				}
			}
		}
		WebDriverUtils.closeAllPopUpWins(driver);
	}

	/**
	 * Empty method body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckUpdatedMembership(WebDriver driver) {

	}

	public void runCreate(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.UserGroup"));

		// 1. Uncheck to show all my user groups
		By by = By.xpath("//span[text()='" + Create_User_Group + "']");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait(1000);

		// 2. Create user group based on org. attributes
		this.setUserGroupName_UI(driver, this.getUserGroupName());
		this.setLogicalDomain_UI(driver, this.getLogicalDomain());
		this.setPermission_UI(driver, this.getPermission());

		// 3. Set membership
		by = By.xpath("//div[@class='action-nav status-header']/span[descendant::h3[text()='"
				+ Heading_Org_Attrs + "']]");
		UIFunctionUtils.setParticipants_UI(driver, this.getCriterion(), by);

		// 4. Save
		by = By.name("SAVE");
		WebDriverUtils.clickButton(driver, by);
		Navigator.explicitWait();
	}

	/**
	 * Empty body since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCreateUGOnOrgAttribute(WebDriver driver) {

	}
	/*
	 * public String toString(){ return new
	 * StringBuilder().append(this.getClass().getName()).append("_").
	 * append(this.getFuncType()).append("_").
	 * append(this.getUID()).append("_").
	 * append(this.getUserGroupName()).toString(); }
	 */

}