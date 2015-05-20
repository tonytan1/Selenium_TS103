package com.netdimen.model;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.netdimen.annotation.Schedule;
import com.netdimen.config.Config;
import com.netdimen.utils.EmailUtil;
import com.netdimen.utils.PropertiesFileUtil;
import com.netdimen.utils.WebDriverUtils;
import com.netdimen.view.Navigator;

/**Accounts for AdobeConnect, GTT, and so on.
 * 
 * @author martin.wang
 *
 */
public class Account extends com.netdimen.abstractclasses.TestObject{
	private String Type="",
			DisplayName="",AccountURL="",UserName="",UserPWD="",
			EnableAuotRecord="",APIKey="",SiteName="",SiteID="",
			PartnerID="", ExpiryDate = "";
	
	private String URL_trial = "http://10minutemail.com/10MinuteMail/index.html";
	public final static String AdobeConnect=Config.getInstance().getProperty("label.vc_adobe_connect");
	public final static String Webex=Config.getInstance().getProperty("label.vc_webex");
	public final static String GoToTraining=Config.getInstance().getProperty("label.vc_gtt");
	public final static String HorizonWimba=Config.getInstance().getProperty("label.vc_horizon_wimba");
	
	public String getExpiryDate() {
		return ExpiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		ExpiryDate = expiryDate;
	}

	public boolean equals(com.netdimen.abstractclasses.TestObject para0){
		boolean result = false;
		return result;}

	public void checkExpectedResult_UI(org.openqa.selenium.WebDriver driver,java.lang.String para1){
		super.checkExpectedResult_UI(driver, para1);
	}

	public Account(){
		super();
	}
	
	private String myField;
	

	public String getMyField() {
		return myField;
	}

	public void setMyField(String myField) {
		this.myField = myField;
	}

	public String getType(){
		return Type;
	}

	public String getDisplayName(){
		return DisplayName;
	}

	public String getAccountURL(){
		return AccountURL;
	}

	public String getUserName(){
		return UserName;
	}

	public String getUserPWD(){
		return UserPWD;
	}

	public String getEnableAuotRecord(){
		return EnableAuotRecord;
	}

	public String getAPIKey(){
		return APIKey;
	}

	public String getSiteName(){
		return SiteName;
	}

	public String getSiteID(){
		return SiteID;
	}

	public String getPartnerID(){
		return PartnerID;
	}

	public void setType(String type){
		Type=type;
	}

	public void setDisplayName(String displayname){
		DisplayName=displayname;
	}

	public void setAccountURL(String accounturl){
		AccountURL=accounturl;
	}

	public void setUserName(String username){
		UserName=username;
	}

	public void setUserPWD(String userpwd){
		UserPWD=userpwd;
	}

	public void setEnableAuotRecord(String enableauotrecord){
		EnableAuotRecord=enableauotrecord;
	}

	public void setAPIKey(String apikey){
		APIKey=apikey;
	}

	public void setSiteName(String webexsitename){
		SiteName=webexsitename;
	}

	public void setSiteID(String siteid){
		SiteID=siteid;
	}

	public void setPartnerID(String partnerid){
		PartnerID=partnerid;
	}

	public void setType_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.name("select");
			WebDriverUtils.select_selector(driver, by, str);
		}
	}

	public void setDisplayName_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = null;
			String type = this.getType(); 
			if(type.equalsIgnoreCase(Account.Webex)){
				by = By.id("webExDisplayName"); 
			}else if(type.equalsIgnoreCase(Account.AdobeConnect)){
				by = By.id("acDisplayName"); 
			}else if(type.equalsIgnoreCase(Account.GoToTraining)){
				by = By.id("displayName");
			}else if(type.equalsIgnoreCase(Account.HorizonWimba)){
				by = By.id("wimbaDisplayName");
			}
			
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setAccountURL_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("accountUrl");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setUserName_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("userName");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setUserPWD_UI(WebDriver driver, String str){
		if(!str.equals("")){
			
		}
	}

	public void setEnableAuotRecord_UI(WebDriver driver, String str){
		if(!str.equals("")){
		}
	}

	public void setAPIKey_UI(WebDriver driver, String str){
		if(!str.equals("")){
		}
	}

	public void setSiteName_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("siteName");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setSiteID_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("siteID");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void setPartnerID_UI(WebDriver driver, String str){
		if(!str.equals("")){
			By by = By.id("partnerID");
			WebDriverUtils.fillin_textbox(driver, by, str);
		}
	}

	public void runCreate(WebDriver driver){
		//Navigator.navigate(driver, Navigator.URL.AccountSetup);
		Navigator.navigate(driver,Navigator.webElmtMgr.getNavigationPathList("ManageCenter","2.VirtualAcc"),this);
		
		By by = By.xpath("//button[@type='button']");
		WebDriverUtils.clickButton(driver, by);
		
		this.setType_UI(driver, this.getType());
		this.setDisplayName_UI(driver, this.getDisplayName());
		this.setAccountURL_UI(driver, this.getAccountURL());
		this.setUserName_UI(driver, this.getUserName());
		this.setUserPWD_UI(driver, this.getUserPWD());
		this.setEnableAuotRecord_UI(driver, this.getEnableAuotRecord());
		this.setAPIKey_UI(driver, this.getAPIKey());
		this.setSiteName_UI(driver, this.getSiteName());
		this.setSiteID_UI(driver, this.getSiteID());
		this.setPartnerID_UI(driver, this.getPartnerID());

		String type = this.getType();
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(Account.Webex)){
			str.append("//form[@id='webEx']"); 
		}else if(type.equalsIgnoreCase(Account.AdobeConnect)){
			str.append("//form[@id='adobeConnect']") ; 
		}else if(type.equalsIgnoreCase(Account.GoToTraining)){
			str.append("//form[@id='gtt']");			
		}else if(type.equalsIgnoreCase(Account.HorizonWimba)){
			str.append("//form[@id='horizonWimba']");
		}
		
		str.append("/div/button[@type='button']");
		by = By.xpath(str.toString());
		WebDriverUtils.clickButton(driver, by);
	}
	
	@Schedule(Monthly=true)
	public Account runCreateAdobeConnectAccount(WebDriver driver) throws IllegalAccessException{
		
		Account account =null;
		try{
			String email = this.getTempEmail(driver);		//email = "a5620670@drdrb.net";
			this.registerTrailAccount(driver, email);		
			account = this.parseTrialEmail(driver);		
			String mailcontent = new String();
			Hashtable<String, String> map = new Hashtable<String, String>();
	
			// Use varible name as properties file key and variable value as value
			Field fld[] = Account.class.getDeclaredFields();  
	        for (int i = 0; i < fld.length-1; i++)  
	        {  
	        	String value = ((String)(fld[i].get(account)));
	            map.put(fld[i].getName(), value);
	            mailcontent=mailcontent+fld[i].getName()+":" +value+System.lineSeparator();
	        }   
	        EmailUtil.Send(Config.getInstance().getProperty("ts.instance") + " Monthly QA Adobe Account (Only first Five lines is useful)", mailcontent);
	        PropertiesFileUtil.SaveAsPropertiesFile(Config.getInstance().getProperty("AdobeAccountConfFile"), map);    
		} catch(RuntimeException e) {
	        e.printStackTrace();
	        EmailUtil.Send(Config.getInstance().getProperty("ts.instance") + " Fail in creating Monthly QA Adobe Account", "As subject");
	    }
		this.activateAccount(driver, account);
		
		return account;
	}
	
	private String getTempEmail(WebDriver driver){
		driver.get(URL_trial);
		By by = By.linkText("10 more minutes!");
		WebDriverUtils.clickLink(driver, by);
		
		by = By.id("addyForm:addressSelect");
		String attr = "value";
		String Email_Temp = WebDriverUtils.getAttribute(driver, by, attr);
		return Email_Temp;
	}

	private void registerTrailAccount(WebDriver driver, String email){
		String URL_temp =Config.getInstance().getProperty("AdobeAccountRegisterSite");

		driver.get(URL_temp);

		By by = By.id("e-mail");
		String str = email;
		WebDriverUtils.fillin_textbox(driver, by, str);

		by = By.id("first-name");
		str = "Martin";
		WebDriverUtils.fillin_textbox(driver, by, str);

		by = By.id("last-name");
		str = "Wang";
		WebDriverUtils.fillin_textbox(driver, by, str);

		by = By.id("company");
		str = "NetDimensions";
		WebDriverUtils.fillin_textbox(driver, by, str);

		by = By.id("phone-number");
		str = "+852 2122 4535";
		WebDriverUtils.fillin_textbox(driver, by, str);

		by = By.id("country");
		str = "Hong Kong";
		WebDriverUtils.select_selector(driver, by, str);

		by = By.id("stateProvinceDropdown-HK");
		str = "Hong Kong Island";
		WebDriverUtils.select_selector(driver, by, str);

		by = By.id("region");
		str = "APAC";
		WebDriverUtils.select_selector(driver, by, str);

		by = By.id("timezone");
		str = "(GMT+08:00) Beijing, Chongqing, Hong Kong SAR, Urumqi";
		WebDriverUtils.select_selector(driver, by, str);

		by = By.id("number-org");
		str = "100-499";
		WebDriverUtils.select_selector(driver, by, str);

		by = By.id("industry");
		str = "Other";
		WebDriverUtils.select_selector(driver, by, str);

		by = By.id("primary-occupation");
		str = "Other";
		WebDriverUtils.select_selector(driver, by, str);

		by = By.id("area-interest");
		str = "Training solutions";
		WebDriverUtils.select_selector(driver, by, str);

		by = By.name("iAcceptAgreement");
		WebDriverUtils.clickButton(driver, by);

		by = By.name("Submit");
		WebDriverUtils.clickButton(driver, by);

		Navigator.explicitWait(3000);
	}

	private Account parseTrialEmail(WebDriver driver){
		driver.get(URL_trial);

		By by = By.linkText("Adobe Connect Trial");
		boolean exist = WebDriverUtils.isElementPresent(by);

		long waitInSeconds =Integer.parseInt(Config.getInstance().getProperty("WaitAjaxElment_millis"))/1000;
		long maxWaitTime=waitInSeconds*6*10;// wait 10 mins
		long startTime = System.currentTimeMillis()/1000;
		long endTime, totalTime;
		while(!exist){
			endTime = System.currentTimeMillis()/1000;
		  	totalTime = endTime - startTime;
		  	
		  	if (totalTime>maxWaitTime){
		  		throw new RuntimeException("Adobe website cannot give a successful adobeconnect account to " + URL_trial);
		  	}
			driver.navigate().refresh();
			Navigator.explicitWait(waitInSeconds);//10 sec
			exist = WebDriverUtils.isElementPresent(by);
		}
		WebDriverUtils.clickLink(driver, by);

		by = By.xpath("//tr/td/a[contains(text(),'https://')]");
		String attr = "href";
		String Account_URL = WebDriverUtils.getAttribute(driver, by, attr).trim();

		by = By.xpath("//td[strong[contains(text(),'Account Name')]]");
		String AccountName = WebDriverUtils.getText(driver, by);
		AccountName = AccountName.substring(AccountName.indexOf("Account Name:")+"Account Name:".length()).trim();
		by = By.xpath("//td[strong[contains(text(),'Username')]]");
		String Username = WebDriverUtils.getText(driver, by);
		Username = Username.substring(Username.indexOf("Username:")+"Username:".length()).trim();
		by = By.xpath("//td[strong[contains(text(),'Password')]]");
		String Password = WebDriverUtils.getText(driver, by);
		Password = Password.substring(Password.indexOf("Password:") + "Password:".length()).trim();
		Account account = new Account();
		account.setType(Account.AdobeConnect);
		account.setAccountURL(Account_URL);
		account.setDisplayName(AccountName);
		account.setUserName(Username);
		account.setUserPWD(Password);
		
		if(Config.DEBUG_MODE){
			System.out.println("AdobeConnect account:"+ account);
		}

		return account;
	}
	
	private void activateAccount(WebDriver driver, Account account){
		driver.get(account.getAccountURL());
		
		By by = By.id("name");
		String str = account.getUserName();		
		WebDriverUtils.fillin_textbox(driver, by, str);
		
		by = By.id("pwd");
		str = account.getUserPWD();		
		WebDriverUtils.fillin_textbox(driver, by, str);
		
		by = By.id("login-button");
		WebDriverUtils.clickButton(driver, by);
		
		by = By.id("read-tos");
		WebDriverUtils.check_checkbox(driver, by);
		
		by = By.id("accept-tos");
		WebDriverUtils.clickButton(driver, by);		
		
		by = By.xpath("//a[contains(text(),'Administration')]");
		WebDriverUtils.clickLink(driver, by);
		
		by = By.xpath("//a[contains(text(),'More Settings')]");
		WebDriverUtils.clickLink(driver, by);
		
		by = By.name("fid-enhanced-security");
		WebDriverUtils.uncheck_checkbox(driver, by);
		
		by = By.id("savemore");
		WebDriverUtils.clickButton(driver, by);
	}
	
}