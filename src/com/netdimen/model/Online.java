package com.netdimen.model;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.controller.TestDriver;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**A parent class for sub-class AICC.  
 * 
 * @author martin.wang
 *
 */
public class Online extends LearningModule{

	public Online(){
		super();
	}

	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
	}


	@Override
	public boolean equals(TestObject obj){
		if(obj instanceof Online && ((Online)obj).toString().equals(this.toString())){
			return true;
		}else{
			return false;
		}
	}


	public void importContentPackage_UI(WebDriver driver){
		/*WebElement we = driver.findElement(By.xpath("//button/span/span[contains(text(),'Import New Revision')]"));
		WebDriverUtils.mouseOver_UI(driver, we);*/

		//		driver.findElement(By.linkText("Import content package")).click();
	}
	
	/**Empty method since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckScorm12(WebDriver driver){
		
	}
	
	public void runAdHoc_Enroll(WebDriver driver){
		
	}
	/**Empty method since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckScorm2004(WebDriver driver){
		
	}
	
	/**Empty method since it's a test suite
	 * 
	 * @param driver
	 */
	public void runCheckAICC(WebDriver driver){
		
	}
}
