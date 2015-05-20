package com.netdimen.utils;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.config.Config;
import com.netdimen.view.Navigator;
import com.netdimen.view.WebElementManager;

/**
 * This class provides common function-related APIs. For example: 1.
 * runAutoEnroll: shared by Modules and JobProfiles 2. expandTree: shared by
 * Organizations and Catalogs 3. setOrgAttributes_UI: shared by permission
 * setter and auto-enrollment of modules/job profiles
 * 
 * @author martin.wang
 *
 */
public class UIFunctionUtils {

	/**
	 * Classify participant string (e.g., Organization Attributes:Org_Numeric=0
	 * to 200) based on different criteria (e.g., Org Attributes)
	 * 
	 * @param str_participants
	 * @return
	 */
	public static HashMap<String, ArrayList<String>> parseParticipants(
			String str_participants) {
		HashMap<String, ArrayList<String>> criteria_values = new HashMap<String, ArrayList<String>>();

		String[] strs = str_participants.split("\n");
		ArrayList<String> values = null;
		for (String str : strs) {
			if (!str.equals("")) {
				//Method A: not good since cannot handle with cases like "A:B:C;D;"
				/*String[] strs_temp = str.split(":");// Organization
				// Attributes:Org_Numeric=0 to
				// 200
				String criteria = strs_temp[0].toLowerCase();// criteria =
																// Organization
																// Attributes
				String value = strs_temp[1];*/
				
				//Method B: better than Method A in handling with cases like "A:B:C;D;"
				int index = str.indexOf(":");
				String criteria = str.substring(0, index);
				String value = str.substring(index+1);
			

				if (criteria_values.containsKey(criteria)) {
					values = criteria_values.get(criteria);
				} else {
					values = new ArrayList<String>();
				}

				values.add(value);// values = Org_Numeric=0 to 200
				criteria_values.put(criteria, values);

			}
		}

		return criteria_values;
	}

	/**
	 * SearchModuleInManagerCenter
	 * 
	 * @param driver
	 * @param moduleid
	 */
	public static void SearchModuleInManagerCenter(WebDriver driver,
			String moduleId) {

		By by = By.id(WebElementManager.getInstance()
				.getWebElementWrapper("ManageCenter", "3.SearchModule")
				.getElementValue());
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(moduleId);
		driver.findElement(By.name("apply-filters")).click();
		Navigator.waitForPageLoad(driver);
	}

	/**Auto-enroll users based on organization attributes
	 * 
	 * @param driver
	 * @param values
	 * @param by
	 */
	public static void setOrgAttributes_UI(WebDriver driver, ArrayList<String> values, By by){
		Navigator.explicitWait();
		WebDriverUtils.clickButton(driver, by);
		UIFunctionUtils.setOrgAttributes_UI(driver, values.toArray(new String[0]));
	}
	
	
/*	public static void setOrgAttributes_AutoEnroll_UI(WebDriver driver,
			ArrayList<String> values) {
		By by = By.xpath("//div[descendant::span/h3[text()='"
				+ Config.getInstance().getProperty("heading.org.attrs")
				+ "'] and @class='action-nav status-header']");
		Navigator.explicitWait();
		WebDriverUtils.clickButton(driver, by);

		UIFunctionUtils.setOrgAttributes_UI(driver,
				values.toArray(new String[0]));
	}
*/
/*	public static void setParticipants_AutoEnroll_UI(WebDriver driver,
			String str_participants) {

		UIFunctionUtils.setParticipants_AutoEnroll_UI(driver,
				UIFunctionUtils.parseParticipants(str_participants));
	}*/

	/**A controller to set participants based on organization/organization attributes/Employment Information/User Attributes.
	 * This method can support JobProfile/LearningModule auto-enroll, User Group Creation, User DA assignment.
	 *
	 * 
	 * @param driver
	 * @param str_participants
	 * @param by
	 */
	public static void setParticipants_UI(WebDriver driver, String str_participants, By by){
		UIFunctionUtils.setParticipants_UI(driver, UIFunctionUtils.parseParticipants(str_participants), by);
	}
	
	/**A controller to set participants
	 * 
	 * @param driver
	 * @param criteria_values
	 * @param webElement: Web Element for "Organization Attributes" 
	 */
	public static void setParticipants_UI(WebDriver driver, HashMap<String, ArrayList<String>> criteria_values, By webElement){
		Iterator<String> criteria_ite = criteria_values.keySet().iterator();
		while (criteria_ite.hasNext()) {
			// Organization Attributes:Org_Numeric=0 to 200
			String criteria = criteria_ite.next(); // criteria =
																	// Organization
																	// Attributes
			ArrayList<String> values = criteria_values.get(criteria);// values =
																		// Org_Numeric=0
																		// to
																		// 200

			switch (criteria.toLowerCase()) {
			case "organization attributes":
				setOrgAttributes_UI(driver, values, webElement);
				break;
				
			case "organization":
				By by = By.xpath("//a[contains(text(),'Organization')]");		
				WebDriverUtils.clickLink(driver, by);
				WebDriverUtils.switchToPopUpWin(driver);
				Navigator.explicitWait(1000);
				WebDriverUtils.checkSelect_CheckBox(driver,values.get(0));
				WebDriverUtils.switchToParentWin(driver);
				WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");
				break;

			default:
				break;
			}
		}
	}
	
	/**
	 * A controller to auto-enroll users
	 * 
	 * @param driver
	 * @param criteria_values
	 *//*
	public static void setParticipants_AutoEnroll_UI(WebDriver driver,
			HashMap<String, ArrayList<String>> criteria_values) {
		Iterator<String> criteria_ite = criteria_values.keySet().iterator();
		while (criteria_ite.hasNext()) {
			// Organization Attributes:Org_Numeric=0 to 200
			String criteria = criteria_ite.next(); // criteria =
																	// Organization
																	// Attributes
			ArrayList<String> values = criteria_values.get(criteria);// values =
																		// Org_Numeric=0
																		// to
																		// 200

			switch (criteria.toLowerCase()) {
			case "organization attributes":
				setOrgAttributes_AutoEnroll_UI(driver, values);
				break;
				
			case "organization":
				By by = By.xpath("//a[contains(text(),'Organization')]");		
				WebDriverUtils.clickLink(driver, by);
				WebDriverUtils.switchToPopUpWin(driver);
				Navigator.explicitWait(1000);
				WebDriverUtils.checkSelect_CheckBox(driver,values.get(0));
				WebDriverUtils.switchToParentWin(driver);
				WebDriverUtils.switchToFrame(driver, "BSCAT_MAIN");


				break;

			default:
				break;
			}
			
		}
		
			//WebDriverUtils.switchToParentWin(driver);
		By by = By.xpath("//button[@title='Set Auto-Enroll Targets']");
		if (WebDriverUtils.isElementPresent(by)) {
			WebDriverUtils.clickButton(driver, by);
		} else {
			by = By.xpath("//button[@title='Set Auto-Assign Targets']");
			WebDriverUtils.clickButton(driver, by);
		}

		assertTrue(WebDriverUtils.closeAlertAndGetItsText().matches(
				"^" + Config.getInstance().getProperty("msg.are_you_sure_auto")
						+ "[\\s\\S]$"));
	}*/

	/**
	 * Parse expected results w.r.t. participant list (e.g.,
	 * "enrollment: uma_rob;non-enrollment: bsam;") for auto-enroll into
	 * criteria-user maps
	 * 
	 * @param str
	 * @return: criteria-users map
	 */
	public static HashMap<String, ArrayList<String>> parseExpectedResults_AutoEnroll(
			String str) {
		HashMap<String, ArrayList<String>> criteria_users = new HashMap<String, ArrayList<String>>();

		if (!str.equals("")) {
			String[] strs = str.split("\n");

			ArrayList<String> users = null;
			String criteria = "";
			for (String str_tmp : strs) { // str_tmp = enrollment:
											// uma_rob;uma_qa1;
				String[] strs_tmp = str_tmp.split(":");
				criteria = strs_tmp[0].trim().toLowerCase(); // enrollment or
																// non-enrollment

				if (criteria_users.containsKey(criteria)) {
					users = criteria_users.get(criteria);
				} else {
					users = new ArrayList<String>();
				}

				String[] users_tmp = strs_tmp[1].split(";");
				for (String user : users_tmp) {
					users.add(user.trim());
				}
				criteria_users.put(criteria, users);
			}
		}

		return criteria_users;
	}

	/**Apply to expand trees to select catalog, organization, competency models, proficiency levels,
	 * job profile levels
	 * 
	 * @param driver
	 * @param path
	 */
	public static void expandTree_UI(WebDriver driver, String path) {
		if (!path.equals("")) {
			String[] nodes = path.split("/");
			String xpath_parent = "//li[descendant::span/a[contains(text(),'"
					+ nodes[0].trim() + "')]]/";
			By by = null;
			String node = "";
			for (int i = 1; i < nodes.length; i++) {
				node = nodes[i].trim();
				xpath_parent += "ul/li[descendant::span/a[contains(text(),'"
						+ node + "')]]/";
				by = By.xpath(xpath_parent
						+ "span/span[starts-with(@id, \"EXPAND_NODE_ID\")]");
				WebDriverUtils.clickLink(driver, by);
			}
		}
	}

	/**
	 * Set org. attributes. for permission selector/auto-enrollment
	 * 
	 * @param driver
	 * @param orgAttributes
	 *            : e.g., Org_DropDown=list2-CS;Org_Numeric=0 to 200\n
	 */
	public static void setOrgAttributes_UI(WebDriver driver,
			String[] orgAttributes) {

		By by = null;
		// values = Org_DropDown=list2-CS;Org_Numeric=0 to 200\n
		for (String value : orgAttributes) { // value: Org_Numeric = 0 to 200
			String value_tmp = value.toLowerCase();
			if (value_tmp.contains("org_numeric")) {
				by = By.xpath("//div[contains(text(),'Org_Numeric')]/input[1]");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);

				String[] strs = value.split("=")[1].split("to");

				by = By.xpath("//div[contains(text(),'Org_Numeric')]/input[2]");
				Navigator.explicitWait(1000);
				WebDriverUtils.fillin_textbox(driver, by, strs[0].trim());

				by = By.xpath("//div[contains(text(),'Org_Numeric')]/input[3]");
				Navigator.explicitWait(1000);
				WebDriverUtils.fillin_textbox(driver, by, strs[1].trim());
			} else if (value_tmp.contains("org_freetext")) {
				by = By.xpath("//label[contains(text(),'Org_FreeText')]/input[1]");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);

				String str = value.split("=")[1];

				by = By.xpath("//div[descendant::div/label[contains(text(),'Org_FreeText')]]/div[2]/div/input");
				Navigator.explicitWait(1000);
				WebDriverUtils.fillin_textbox(driver, by, str.trim());
			} else if (value_tmp.contains("org_dropdown")) {
				by = By.xpath("//label[contains(text(),'Org_DropDown')]/input[1]");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);

				// value = Org_DropDown=list2-CS
				String str = value.split("=")[1];

				by = By.xpath("//div[contains(text(),'" + str
						+ "')]/input[@type='CHECKBOX']");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);
				;
			} else if (value_tmp.contains("org_textarea")){
				by = By.xpath("//label[contains(text(),'Org_Textarea')]/input[1]");
				Navigator.explicitWait(1000);
				WebDriverUtils.check_checkbox(driver, by);

				String str = value.split("=")[1];
				by = By.xpath("//textarea[@class='org-attr-textarea large required']");
				WebDriverUtils.fillin_textbox(driver, by, str);
			}
		}
	}

	/**
	 * Set permission based on user/org/org attributes
	 * 
	 * @param driver
	 * @param by
	 * @param permission
	 *            :1. User Read:uma_qa1;uma_qa2\n User Write:uma_qa1;uma_qa2 \n
	 *            2. Organization Attributes
	 *            Write:Org_DropDown=list2-CS;Org_Numeric=0 to 200\n
	 *            Organization Attributes
	 *            Read:Org_DropDown=list2-CS;Org_Numeric=0 to 200\n 3. Org Read
	 *            include:My Company One/Org1_ACC; NETD IT/Major Release/DEV \n
	 *            Org Read exclude:My Company One/Org1_ACC; NETD IT/Major
	 *            Release/DEV \n Org Write include:My Company One/Org1_ACC; NETD
	 *            IT/Major Release/DEV \n Org Write exclude:My Company
	 *            One/Org1_ACC; NETD IT/Major Release/DEV \n
	 */
	public static void setPermission_UI(WebDriver driver, String permissionStr) {

		WebDriverUtils.switchToPopUpWin(driver);
		Navigator.explicitWait(1000);
		String[] permissionArray = permissionStr.split("\n");
		By byb = null;

		for (String permission : permissionArray) {

			String[] strs1 = permission.split(":"); // User Read:uma_qa1;uma_qa2
			String criterion = strs1[0].toLowerCase();

			if (criterion.contains("read")) {
				byb = By.xpath("//span[contains(text(),'"
						+ Config.getInstance().getProperty(
								"label.permission.selector.read_access")
						+ "')]");
			} else if (criterion.contains("write")) {
				byb = By.xpath("//span[contains(text(),'"
						+ Config.getInstance().getProperty(
								"label.permission.selector.write_access")
						+ "')]");
			}
			// some audience selection has not write or read access
			if (byb != null) {
				Navigator.waitForAjax(driver, byb);
//				Navigator.explicitWait();
				WebDriverUtils.clickLink(driver, byb);
			}
			Navigator.explicitWait(1000);
			String[] values = strs1[1].split(";");
			
			if (criterion.contains("user")) {
				// set up permission based on users
				byb = By.linkText("Users");
				Navigator.waitForAjax(driver, byb);
//				Navigator.explicitWait();
				WebDriverUtils.clickLink(driver, byb);

				WebDriverUtils.switchToPopUpWin(driver);
				Navigator.explicitWait(1000);

				WebDriverUtils.searchSelect_Selector(driver, values);

				WebDriverUtils.switchToParentWin(driver);

			} else if (criterion.contains(Config.getInstance()
					.getProperty("heading.org.attrs").toLowerCase())) {
				// set up permission based on organization attributes

				byb = By.id("SELECTOR");
				Navigator.waitForAjax(driver, byb);
//				Navigator.explicitWait();
				WebDriverUtils.select_selector(driver, byb, Config
						.getInstance().getProperty("heading.org.attrs"));

				byb = By.name("goButton");
				Navigator.waitForAjax(driver, byb);
//				Navigator.explicitWait();
				WebDriverUtils.clickButton(driver, byb);

				UIFunctionUtils.setOrgAttributes_UI(driver, values);

			} else if (criterion.contains("org")) {
				// set up permission based on org (include, exclude)

				byb = By.id("SELECTOR");
				Navigator.waitForAjax(driver, byb);
//				Navigator.explicitWait();
				WebDriverUtils.select_selector(driver, byb, "Organization");

				byb = By.name("goButton");
				Navigator.waitForAjax(driver, byb);
//				Navigator.explicitWait();
				WebDriverUtils.clickButton(driver, byb);

				if (criterion.contains("include")) {
					byb = By.linkText(Config.getInstance().getProperty(
							"desc.include.Organization"));
				} else if (criterion.contains("exclude")) {
					byb = By.linkText(Config.getInstance().getProperty(
							"desc.exclude.Organization"));
				}
//				Navigator.explicitWait();
				Navigator.waitForAjax(driver, byb);
				WebDriverUtils.clickLink(driver, byb);
				WebDriverUtils.switchToPopUpWin(driver);

				Navigator.explicitWait(1000);
				WebDriverUtils.checkSelect_CheckBox(driver, values);

				WebDriverUtils.switchToParentWin(driver);
			}

			byb = By.id("saveButton");
			Navigator.explicitWait(1000);
			WebDriverUtils.clickButton(driver, byb);
		}

		byb = By.name("Cancel");
		Navigator.explicitWait(1000);
		WebDriverUtils.clickButton(driver, byb);

		WebDriverUtils.switchToParentWin(driver);
	}

	public static void setFullPermission_UI(WebDriver driver, By by) {
		// Set Permissions
		driver.findElement(by).click();

		WebDriverUtils.switchToPopUpWin(driver);
		By byb = By.xpath("//span[contains(text(),'"
				+ Config.getInstance().getProperty(
						"label.permission.selector.write_access") + "')]");
		WebDriverUtils.clickLink(driver, byb);

		byb = By.id("SELECTOR");
		String str = "Organization";
		WebDriverUtils.select_selector(driver, byb, str);

		byb = By.name("goButton");
		WebDriverUtils.clickButton(driver, byb);

		byb = By.linkText(Config.getInstance().getProperty(
				"desc.include.Organization"));
		WebDriverUtils.clickButton(driver, byb);

		WebDriverUtils.switchToPopUpWin(driver);
		byb = By.linkText(Config.getInstance().getProperty("link.ExpandTree"));
		WebDriverUtils.clickLink(driver, byb);

		byb = By.xpath("//tr[descendant::td[contains(text(), 'ALL')]]/td/input[@type='CHECKBOX'][1]");
		WebDriverUtils.clickLink(driver, byb);

		byb = By.name("save");
		WebDriverUtils.clickButton(driver, byb);

		WebDriverUtils.switchToParentWin(driver);
		byb = By.id("saveButton");
		WebDriverUtils.clickButton(driver, byb);

		byb = By.name("Cancel");
		WebDriverUtils.clickButton(driver, byb);

		WebDriverUtils.switchToParentWin(driver);
		byb = By.xpath("//input[@value='Save']");
		WebDriverUtils.clickButton(driver, byb);
	}

	public static void fillESignature(WebDriver driver, String UID, String PWD) {
		driver.findElement(By.id("ESIGNATURE-username")).clear();
		driver.findElement(By.id("ESIGNATURE-username")).sendKeys(UID);
		driver.findElement(By.id("ESIGNATURE-password")).clear();
		driver.findElement(By.id("ESIGNATURE-password")).sendKeys(PWD);
		driver.findElement(By.id("ESIGN-submit")).click();
	}


	/**Search keyword in Ajax search box. This applies to assign learning modules to competency,
	 * assign competency to job profiles/ad-hoc assessment. 
	 * 
	 * @param driver
	 * @param keywords
	 */
	public static void searchInAjaxSearchBox(WebDriver driver, ArrayList<String> keywords){		
		if(keywords!=null){
			for(String keyword:keywords){
				Navigator.explicitWait(1000);
				By by = By.id("SEARCHMODELCOMP_input");
				driver.findElement(by).clear();
				driver.findElement(by).sendKeys("");
				driver.findElement(by).sendKeys(keyword);
				
				by = By.xpath("//div[@id='SEARCHMODELCOMP_ctr']/div/div[@val='"+keyword+"']");
				Navigator.explicitWait(1000);
				WebDriverUtils.mouseDown(driver, by);
				Navigator.explicitWait(1000);
				WebDriverUtils.mouseUp(driver, by);
			}
		}
	}
}
