package com.netdimen.model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class UserProfile extends com.netdimen.abstractclasses.TestObject {
	private String SeenUserProfile = "", NoUserProfile = ""; 

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public UserProfile() {
		super();
	}
	
	public String getSeenUserProfile() {
		return SeenUserProfile;
	}

	public String getNoUserProfile() {
		return NoUserProfile;
	}
	
	public void setSeenUserProfile(String seenuserprofile) {
		SeenUserProfile = seenuserprofile;
	}
	
	public void setNoUserProfile(String nouserprofile) {
		NoUserProfile = nouserprofile;
	}

	public void setUserProfile_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	/**Check visibility of user profiles
	 * 
	 * @param driver
	 */
	public void runUserProfile(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.UserEditor"));
	
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "USERMENU");
		By by=By.cssSelector("img[alt=\"Create user\"]");
		WebDriverUtils.clickButton(driver, by);
			
		WebDriverUtils.switchToFrame(driver, "USERMAIN");
		
		//Check visible user profile
		if (this.getSeenUserProfile().trim().length()>0) {
			String[] userprofarray = this.getSeenUserProfile().split(";");
		
			for (String userprof: userprofarray) {

				by = By.xpath("//select[@name='PRO']/option[contains(text(),'" + userprof + "')]");				
				int size = WebDriverUtils.getHowManyByPresntInPage(by);
				JUnitAssert.assertTrue(size==1, "Cannot find user profile:" + userprof);
			}
		}
				
		// Check invisible user profile
		if (this.getNoUserProfile().trim().length()>0) {
			String[] userprofarray = this.getNoUserProfile().split(";");
			for (String userprof: userprofarray) {

				by = By.xpath("//select[@name='PRO']/option[contains(text(),'" + userprof + "')]");				
				int size = WebDriverUtils.getHowManyByPresntInPage(by);
				JUnitAssert.assertTrue(size == 0, "Can find user profile:" + userprof);
			}	
		}
		
	
	}
/*	public String toString(){
		return new StringBuilder().append(this.getClass().getName())
				.append("_").append(this.getFuncType()).append("_")
				.append(this.getUID()).
				toString();
	}*/
}