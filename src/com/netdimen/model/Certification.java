package com.netdimen.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.utils.UIFunctionUtils;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**
 * 
 * @author martin.wang
 *
 */
public class Certification extends TestObjectSignature {
	private String CertID = "", CertTitle = "", CertType = "", CertPool = "", Status = "",
			Issuer = "", Desc = "", Permission = "", Exam = "";

	public String getExam() {
		return Exam;
	}

	public void setExam(String exam) {
		Exam = exam;
	}

	public Certification(){
		super();
	}

/*	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new StringBuilder().
				append(this.getClass().getName()).
				append("_").
				append(this.getFuncType()).
				append("_").
				append(this.getCertID()).
				toString();
	}*/
	public void setExam_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("TID_link");
			WebDriverUtils.clickLink(driver, by);
			WebDriverUtils.switchToPopUpWin(driver);
			WebDriverUtils.searchSelect_Selector(driver, str);
			WebDriverUtils.switchToParentWin(driver);
			WebDriverUtils.switchToFrame(driver, "CERT_MAIN");
		}
	}

	public void runCreate(WebDriver driver){
		Navigator.navigate(driver,Navigator.webElmtMgr.getNavigationPathList("ManageCenter","2.Cert.Editor"),this);

		WebDriverUtils.switchToPopUpWin(driver);
		WebDriverUtils.switchToFrame(driver, "CERT_TOP");

		By by = By.id("editorcreatebutton");
		WebDriverUtils.clickButton(driver, by);

		WebDriverUtils.switchToFrame(driver, "CERT_MAIN");
		this.setCertID_UI(driver, this.getCertID());
		this.setCertTitle_UI(driver, this.getCertTitle());
		this.setCertType_UI(driver, this.getCertType());
		this.setIssuer_UI(driver, this.getIssuer());
		this.setDesc_UI(driver, this.getDesc());
		this.setExam_UI(driver, this.getExam());
		
		Navigator.explicitWait(3000);
		
		by = By.name("CREATE");
		WebDriverUtils.clickButton(driver, by);

		by = By.name("continueButton");
		WebDriverUtils.clickButton(driver, by);

		//		this.setPermission_UI(driver, this.getPermission());

	}

	public String getCertType() {
		return CertType;
	}

	public void setCertType(String certType) {
		CertType = certType;
	}

	public String getCertID() {
		return CertID;
	}

	public void setCertID(String certID) {
		CertID = certID;
	}

	public String getCertTitle() {
		return CertTitle;
	}

	public void setCertTitle(String certTitle) {
		CertTitle = certTitle;
	}

	public String getIssuer() {
		return Issuer;
	}

	public void setIssuer(String issuer) {
		Issuer = issuer;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
	}

	public String getPermission() {
		return Permission;
	}

	public void setPermission(String permission) {
		Permission = permission;
	}

	public String getCertPool() {
		return CertPool;
	}

	public void setCertPool(String certPool) {
		CertPool = certPool;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public void checkExpectedResult_UI(WebDriver driver, String expectedResult){
		super.checkExpectedResult_UI(driver, expectedResult);
	}

	@Override
	public boolean equals(TestObject obj){
		if(obj instanceof Certification && ((Certification)obj).toString().equals(this.toString())){
			return true;
		}else{
			return false;
		}
	}

	public void setCertID_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by =  By.id("CODE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setCertTitle_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("TITLE");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setCertType_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.name("TYPEID");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setIssuer_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("ISSUEDBY");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setDesc_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.id("DESC");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}


	public void setPermission_UI(WebDriver driver, String str){
		if(!str.equalsIgnoreCase("")){
			By by = By.linkText("Permissions");
			UIFunctionUtils.setFullPermission_UI(driver, by);
		}
	}


}
