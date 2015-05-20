package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

public class Email extends com.netdimen.abstractclasses.TestObject {
	private String Name = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Email() {
		super();
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runCheckEmail(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"LearningCenter", "2.MyEmails"));
		
		By by = By.xpath("//a[contains(text(),'"+this.getName()+") and descendant::img[contains(@src,'envelop_unread.png')]]");
		int size = WebDriverUtils.getHowManyByPresntInPage(by);
		JUnitAssert.assertTrue(size>=1, "Fail to receive email:" + this.getName());
	}

}