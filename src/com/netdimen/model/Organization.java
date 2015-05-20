package com.netdimen.model;


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
public class Organization extends com.netdimen.abstractclasses.TestObject {
	private String Org = "";

	public boolean equals(com.netdimen.abstractclasses.TestObject para0) {
		boolean result = false;
		return result;
	}

	public Organization() {
		super();
	}

	public String getOrg() {
		return Org;
	}

	public void setOrg(String org) {
		Org = org;
	}

	public void setOrg_UI(WebDriver driver, String str) {
		if (!str.equals("")) {
		}
	}

	public void runCheckVisibility(WebDriver driver) {
		Navigator.navigate(driver,Navigator.webElmtMgr.getNavigationPathList("ManageCenter","2.Org.Maintenance"), this);
		
		String[] orgs = this.getOrg().split("\n");
		for(String org_tmp: orgs){
			String[] orgs1 = org_tmp.split(":");
			String criteria = orgs1[0]; //visible or invisible;
			
			//expand the tree
			String orgPaths = orgs1[1]; //ALL/My Company One/Org1_IT/Org1_AdHoc;ALL/Netdimensions/Netd_AdHoc
			
			String[] orgPathArray = orgPaths.split(";");
			for(String orgPath: orgPathArray){
				String[] orgPath_tmp = orgPath.split("/");
				
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < orgPath_tmp.length -1 ; i ++){
					sb.append(orgPath_tmp[i]).append("/");
				}
				
				String orgFinal = orgPath_tmp[orgPath_tmp.length -1];
				//check visibility of final org
				By by = By.linkText(orgFinal);
				int size;
				
				if(criteria.equalsIgnoreCase("visible")){
					UIFunctionUtils.expandTree_UI(driver, sb.toString());
					Navigator.waitForAjax(driver, by);
					size = WebDriverUtils.getHowManyByPresntInPage(by);
					JUnitAssert.assertTrue(size > 0, "Org is invisible:" + orgFinal);	
				}else if(criteria.equalsIgnoreCase("invisible")){
					size = WebDriverUtils.getHowManyByPresntInPage(by);
					JUnitAssert.assertTrue(size==0, "Org is visible:" + orgFinal);
				}
				
			}			
		}
	}
	
	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
	}
	
	
	
/*	public String toString(){
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getUID()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getOrg()).
				toString();
	}
*/



//	public static String getOrgPath_UI(WebDriver driver, String UName){
//		//HomePage -> User Preference -> My Orgs 		  
//		String orgPath = "";
//		Navigator.navigate(driver,Navigator.webElmtMgr.getNavigationPathList("LearningCenter","1.Home"));
//		
//		
//		By by = By.xpath("//div[@class='sec-menu-container']/ul/li/a[@class='username']");
//		WebDriverUtils.clickLink(driver, by);
//
//		by = By.xpath("//div[@id='main-content']/div/ul/li[2]/a/span[contains(text(),'My Orgs')]");
//		WebDriverUtils.clickLink(driver, by);
//
//		by = By.xpath("//td/select/option[@selected=\"\"]");
//		List<WebElement> elements = driver.findElements(by);
//
//		StringBuilder sb = new StringBuilder();	  
//		//ends with "UNASSIGNED"
//		int size = elements.size();
//		if(size > 2){
//			//for "ALL/ORG1/UNASSIGNED", ignore the last one - "UNASSIGNED"
//			for(int i = 0; i < elements.size() - 1; i++){
//				WebElement element = elements.get(i);
//				sb.append(element.getText()).append("/");  
//			}  
//		}else if(size == 2){
//			//for "ALL/UNASSIGNED", inherite from "ALL"
//			if(elements.get(1).getText().equalsIgnoreCase("UNASSIGNED")){
//				sb.append(elements.get(0).getText()).append("/");
//			}else{
//				for(WebElement element: elements){
//					sb.append(element.getText()).append("/");
//				}	
//			}
//		}
//
//		int index = sb.lastIndexOf("/");
//		orgPath = sb.replace(index, index+1, "").toString();
//
//		Navigator.navigate(driver,Navigator.webElmtMgr.getNavigationPathList("ManageCenter","2.Org.Maintenance"));
//		
//		return orgPath;
//	}


}