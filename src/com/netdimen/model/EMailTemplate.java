package com.netdimen.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.junit.JUnitAssert;
import com.netdimen.utils.UIFunctionUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class EMailTemplate extends com.netdimen.abstractclasses.TestObject {
	private String ParentTemplate = "", TemplateName = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public EMailTemplate() {
		super();
	}

	public String getParentTemplate() {
		return ParentTemplate;
	}

	public String getTemplateName() {
		return TemplateName;
	}

	public void setParentTemplate(String parenttemplate) {
		ParentTemplate = parenttemplate;
	}

	public void setTemplateName(String templatename) {
		TemplateName = templatename;
	}

	public void setParentTemplate_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
			By by = By.linkText(str);
			WebDriverUtils.clickLink(driver, by);
		}
	}

	public void setTemplateName_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}
	
	public void selectEmail(WebDriver driver, String email){
		//Expand Email tree
		WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATETREE");
		Navigator.explicitWait(1000);
		By by = null;
		if(!email.equals("")){
			//expand system/user email template
			by = By.xpath("//a[descendant::img[@alt='Expand']]");
			int size = WebDriverUtils.getHowManyByPresntInPage(by);
			while(size > 0){
				WebDriverUtils.clickLink(driver, by);
				size--;
			}
			
			Navigator.explicitWait(1000);
			by = By.linkText(email);
			WebDriverUtils.clickLink(driver, by);
			Navigator.explicitWait(3000);
		}
		
		//Select email
		WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATEMENU");
		Navigator.explicitWait(1000);
		by = By.xpath("//td[@class='smalleditorback2']/table/tbody/tr/td[6]/a");
		WebDriverUtils.clickLink(driver, by);
	}

	public void runCheckVisibility(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.EmailEditor"));
		
		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "EMAILTEMPLATETREE");
		Navigator.explicitWait(1000);
		
		//1. Expand parent template
		this.setParentTemplate_UI(driver, this.getParentTemplate());
		Navigator.explicitWait(1000);
		
		//2.Check visibility 
		HashMap<String, ArrayList<String>> criteria_emails = UIFunctionUtils.parseParticipants(this.getExpectedResult());
		Iterator<String> criteria = criteria_emails.keySet().iterator();
		By by = null;
		
		while(criteria.hasNext()){
			String criterion = criteria.next();
			ArrayList<String> emails = criteria_emails.get(criterion);
			for(String emailStr: emails){
				for(String email: emailStr.split(";")){
					//2.2 check email visibility
					by = By.linkText(email);
					int size = WebDriverUtils.getHowManyByPresntInPage(by);
					switch(criterion.toLowerCase()){
					case "visible":
						JUnitAssert.assertTrue(size > 0, "EMail is invisible:" + email);
						break;
					case "invisible":
						JUnitAssert.assertTrue(size == 0, "EMail is visible:" + email);
						break;
					}						
				}
			}
		}
	}
	
/*	public String toString(){
		return new StringBuilder().append(this.getClass().getName()).append("_").
				append(this.getFuncType()).append("_").
				append(this.getUID()).append("_").
				append(this.getTemplateName()).toString();
	}*/

}