package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class TSSystem extends com.netdimen.abstractclasses.TestObject {
	private String Frequency = "", Type = "", Config = "", Value = "";
	
	public String getFrequency() {
		return Frequency;
	}

	public String getConfig() {
		return Config;
	}

	public String getValue() {
		return Value;
	}

	public void setConfig(String config) {
		Config = config;
	}

	public void setValue(String value) {
		Value = value;
	}

	public String getType() {
		return Type;
	}

	public void setFrequency(String frequency) {
		Frequency = frequency;
	}

	public void setType(String type) {
		Type = type;
	}

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = true;
		return result;
	}

	public TSSystem() {
		super();
	}

	public void runScheduledTask(WebDriver driver) {
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.ScheduledTasks"));
		
		By by = By.xpath("//input[@value='"+ this.getFrequency().toLowerCase()+"']");
		WebDriverUtils.checkRadio(driver, by);
		
		by = By.id("tasktype");
		WebDriverUtils.select_selector(driver, by, this.getType());
		
		by = By.xpath("//input[@value='Run scheduled tasks']");
		WebDriverUtils.clickButton(driver, by);
	}
	
	
	public void runSetupSystemConf(WebDriver driver){
		Navigator.navigate(driver, Navigator.webElmtMgr.getNavigationPathList(
				"ManageCenter", "2.SystemConf"));
		String key = this.getConfig();
		String value = this.getValue();
		
		//1.1: apply to "Module Launches", "Module Launches" and so on
		By by = By.xpath("//tr[descendant::td/div[contains(text(),'"+key+"')]]/td/div[descendant::label[contains(text(),'"+value+"')]]/input");
		int size = WebDriverUtils.getHowManyByPresntInPage(by);
		if(size > 0){
			WebDriverUtils.checkRadio(driver, by);	
		}else{
			//1.2:apply to "Sort users by last name?", "Show last name first?" and so on 
			by = By.xpath("//tr[descendant::td/label[contains(text(),'"+key+"')]]/td/input");
			size = WebDriverUtils.getHowManyByPresntInPage(by);
			if(size > 0 ){
				if(value.toLowerCase().contains("disable")){
					WebDriverUtils.uncheck_checkbox(driver, by);	
				}else if(value.toLowerCase().contains("enable")){
					WebDriverUtils.check_checkbox(driver, by);
				}
			}else{
				//1.3: apply to "Default Pop-up Window Size" and so on.
				by = By.xpath("//tr[descendant::td[contains(text(),'"+key+"')]]/td/input[@type='TEXT']");
				size = WebDriverUtils.getHowManyByPresntInPage(by);
				if(size > 0){
					WebDriverUtils.fillin_textbox(driver, by, value);
				}else{
					//1.4: apply to "Trusted Sites for Proxied Course Launches " and so on.
					by = By.xpath("//tr[descendant::td[contains(text(),'"+key+"')]]/td/label/textarea");
					size = WebDriverUtils.getHowManyByPresntInPage(by);
					if(size > 0){
						WebDriverUtils.fillin_textbox(driver, by, value);
					}else{
						//1.5: apply to "Course Player", "Other Name Field" and so on.
						by = By.xpath("//tr[descendant::td[contains(text(),'"+key+"')]]/td/label[contains(text(),'" + value + "')]/input");
						size = WebDriverUtils.getHowManyByPresntInPage(by);
						if(size > 0){
							WebDriverUtils.checkRadio(driver, by);
						}else{
							//1.6: apply to "Default Working Days Start Day" and so on.
							by = By.xpath("//tr[descendant::td[contains(text(),'"+key+"')]]/td/select");
							size = WebDriverUtils.getHowManyByPresntInPage(by);
							if(size > 0){
								WebDriverUtils.select_selector(driver, by, value);;
							}else{
								//1.7: apply to "Competency Revocation E-mail:" and so on.
								by = By.xpath("//tr[descendant::td[contains(text(),'"+key+"')]]/td/select");
								size = WebDriverUtils.getHowManyByPresntInPage(by);
								if(size > 0){
									WebDriverUtils.select_selector(driver, by, value);;
								}	
							}
						}
					}
				}
			}
		}
		
		//2. Save changes
		by = By.xpath("//input[@name='SAVE' and @value='Save']");
		WebDriverUtils.clickButton(driver, by);
	}

}