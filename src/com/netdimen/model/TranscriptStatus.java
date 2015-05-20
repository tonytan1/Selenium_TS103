package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.UIFunctionUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class TranscriptStatus extends com.netdimen.abstractclasses.TestObject {
	private String Status = "", Permission = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public TranscriptStatus() {
		super();
	}

	public String getStatus() {
		return Status;
	}

	public String getPermission() {
		return Permission;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public void setStatus_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("//tr[descendant::td[contains(text(),'"+str+"')]]/td/div/button");
			WebDriverUtils.mouseOver(driver, by);
		}
	}

	public void setPermission_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.xpath("//tr[descendant::td[contains(text(),'"+this.getStatus()+"')]]/td/div/ul/li/a[contains(text(),'Permissions')]");
			WebDriverUtils.clickLink(driver, by);			
			UIFunctionUtils.setPermission_UI(driver, this.getPermission());
		}
	}

	public void runUpdatePermission(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.TranscriptStatus"));
		this.setStatus_UI(driver, this.getStatus());
		this.setPermission_UI(driver, this.getPermission());
	}
	
/*	public String toString(){
		return new StringBuilder().
				append(this.getStatus()).
				append("_").
				append(this.getPermission()).toString();
	}
*/
	
}