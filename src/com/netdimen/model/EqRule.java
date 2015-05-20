package com.netdimen.model;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.UIFunctionUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;
import com.netdimen.view.WebElementManager;
import com.netdimen.view.WebElementWrapper;

public class EqRule extends TestObject {
	private String TargetModuleID = "", SubstituteModuleIDs = "", Audience = "";
	private String TargetModuleTitle;
	
	private static final String Create_Rule_Btn = Config.getInstance()
			.getProperty("button.equivalency.create.rule");
	private static final String Create_Btn = Config.getInstance().getProperty(
			"button.Create");
	private static final String Gear_EQ_Btn = Config.getInstance().getProperty(
			"label.equivalency.manager");
	private static final String Add_Module_Link = Config.getInstance()
			.getProperty("link.Add_Module(s)...");
	private static final String Gear_Audience_Btn = Config.getInstance()
			.getProperty("button.equivalency_rule.target_audience");

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public EqRule() {
		super();
	}


	public String getAudience() {
		return Audience;
	}

	public void setAudience(String audience) {
		Audience = audience;
	}

	public void SetEquivalentRule(WebDriver driver, String moduleTitle) {
		WebElementWrapper webElem = WebElementManager.getInstance()
				.getWebElementWrapper("General", "1.LearningGearBtn");
		String[] paramters = { moduleTitle };
		webElem.setParamters(paramters);
		WebDriverUtils.mouseOver(driver, webElem.getBy());
		WebDriverUtils.clickButton(driver, By.linkText(Gear_EQ_Btn));
		ArrayList<String> Titles = new ArrayList<String>();
		String [] SubstituteArray =null;
		if (!(this.SubstituteModuleIDs.isEmpty())) {
			SubstituteArray = SubstituteModuleIDs.trim().split(";"); // module are separated by "|"
			for (String SubstituteID : SubstituteArray) {
				System.out.println(SubstituteID);
				Titles.add(SubstituteID);
			}
			CreateRule(driver, Titles);

		}
		By by = By.xpath("//tr[descendant::td/ul/li[contains(text(),'"+SubstituteArray[0]+"')]]/td/div[1]/button");
		WebDriverUtils.mouseOver(driver, by);
		WebDriverUtils.clickButton(driver, By.linkText(Gear_Audience_Btn));
		ArrayList<String> orgs = new ArrayList<String>();
		orgs.add("Org include:" + Audience + " \n");
		WebDriverUtils.searchSelect_Selector(driver,
				WebDriverUtils.PopUpSelector.PermissionSelector, orgs);

		Titles.clear();

		// Check output
		by = By.xpath("//table[@class='netd-table full-width ']");
		String output = WebDriverUtils.getText(driver, by).trim();
		if (!(SubstituteModuleIDs.isEmpty()))
			JUnitAssert.assertTrue(output.contains(SubstituteArray[0]), output + " does not contain " + SubstituteArray[0]);
	}

	public void CreateRule(WebDriver driver, ArrayList<String> Titles) {
		By by;
		by = By.xpath("//button[descendant::span[text()='" + Create_Rule_Btn
				+ "']]");
		WebDriverUtils.clickButton(driver, by);
		by = By.linkText(Add_Module_Link);
		WebDriverUtils.clickButton(driver, by);
		WebElementManager.getInstance().getWebElementWrapper(
				"TopDownSelector", "1.SearchText");
		WebDriverUtils.searchSelect_Selector(driver,
				WebDriverUtils.PopUpSelector.TopDownSelector, Titles);
		by = By.xpath("//button[descendant::span[text()='" + Create_Btn + "']]");
		WebDriverUtils.clickButton(driver, by);
	}

	public void runCreateEQRule(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.Modules"), this);
		UIFunctionUtils.SearchModuleInManagerCenter(driver, this.TargetModuleID);
		SetEquivalentRule(driver, TargetModuleTitle);
	}

	public String getTargetModuleID() {
		return TargetModuleID;
	}

	public void setTargetModuleID(String targetModuleID) {
		TargetModuleID = targetModuleID;
	}

	public String getSubstituteModuleIDs() {
		return SubstituteModuleIDs;
	}

	public void setSubstituteModuleIDs(String substituteModuleIDs) {
		SubstituteModuleIDs = substituteModuleIDs;
	}

	public String getTargetModuleTitle() {
		return TargetModuleTitle;
	}

	public void setTargetModuleTitle(String targetModuleTitle) {
		TargetModuleTitle = targetModuleTitle;
	}

}