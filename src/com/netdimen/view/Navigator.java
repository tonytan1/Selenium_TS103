package com.netdimen.view;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.netdimen.abstractclasses.TestObject;
import com.netdimen.config.Config;
import com.netdimen.utils.WebDriverUtils;


public class Navigator {	
	public static final WebElementManager webElmtMgr = WebElementManager.getInstance();
	
	public static void navigate(WebDriver driver, ArrayList<WebElementWrapper> webElementList){
		navigate(driver, webElementList, null);
	}
	
	public static void navigate(WebDriver driver,ArrayList<WebElementWrapper> webElementList, TestObject obj){
		WebElementWrapper temp = webElementList.get(0);
		String strSheetName= temp.getSheetName();
		
		WebDriverUtils.closeAllPopUpWins(driver);
		
		if(!strSheetName.equals("")){
			switch(strSheetName){
			case "LearningCenter":
				navigateToHomePage(driver);
				break;
			case "ManageCenter":
				navigateToManageCenter(driver);
				break;
			default:
				break;
			}
			navigateToPage(driver, webElementList, obj);
		}
	}
	
	private static void navigateToPage(WebDriver driver,
			ArrayList<WebElementWrapper> webElementList, TestObject testObj) {
		
		for (int i = 0; i < webElementList.size(); i++) {
			WebElementWrapper we = webElementList.get(i);
			By by = we.getBy();
			Navigator.explicitWait(300);
//			Navigator.waitForAjaxElementLoad(driver, by);
			
			//Wait for a while if still not found 
			if(WebDriverUtils.getHowManyByPresntInPage(by) == 0){
				Navigator.explicitWait(); 
			}
			WebDriverUtils.clickLink(driver, by);			
		}
	}

	
	private static void navigateToHomePage(WebDriver driver){
		driver.get(Config.getInstance().getProperty("HomePage"));

		By by = By.id("footer-links-container");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(300);
	}

	private static void navigateToManageCenter(WebDriver driver){
		driver.get(Config.getInstance().getProperty("ManageCenter"));
		By by = By.id("footer-links-container");
//		Navigator.waitForAjaxElementLoad(driver, by);
		Navigator.explicitWait(300);
	}

	public static void explicitWait(){
		explicitWait(Integer.parseInt(Config.getInstance().getProperty("ExplicitWait_millis")));
	}


	public static void explicitWait(long wait_millis){
		try {
			Thread.sleep(wait_millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**Wait for Elements to occur
	 * 
	 * @param driver
	 * @param by
	 */
	public static void waitForElementLoad(WebDriver driver, final By by){
		/*Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Integer.parseInt(Config.getInstance().getProperty("WaitAjaxElment_millis")), TimeUnit.MILLISECONDS)
				.pollingEvery(300, TimeUnit.MILLISECONDS)
				.ignoring(Exception.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));*/
		
		double  startTime;
		double  endTime,totalTime;
		Double period = Double.parseDouble(Config.getInstance().getProperty("WaitAjaxElment_millis"));
		int size = WebDriverUtils.getHowManyByPresntInPage(by);
		startTime = System.currentTimeMillis();
		 while(size<=0){
		  	
		  	endTime = System.currentTimeMillis();
		  	totalTime = endTime - startTime;
		  	
		  	if (totalTime>period){
		  		//explicitWait();
		         throw new RuntimeException("Timeout finding webelement "+ by.toString() +" PLS CHECK report.xls for screen captured");
		  	}
		    try {
		    	Navigator.explicitWait(300);
			  	size = WebDriverUtils.getHowManyByPresntInPage(by);
		      } catch ( StaleElementReferenceException ser ) {	
		    	  System.out.println("waitForElementLoad: "+ ser.getMessage() );
		      } catch ( NoSuchElementException nse ) {	
		    	  System.out.println( "waitForElementLoad: "+ nse.getMessage() );
		      } catch ( Exception e ) {
		    	  System.out.println("waitForElementLoad: "+  e.getMessage() );
		      }
		 }
	}
	/**Wait for javascript Ajax to finish at back-end in browser. 
	 * 
	 * @param driver
	 * @param by
	 */
	public static void waitForAjax(WebDriver driver, final By by)  {
		int timeoutInSeconds =Integer.parseInt(Config.getInstance().getProperty("WaitAjaxElment_millis"))/1000;
		 // System.out.println("Checking active ajax calls by calling jquery.active");	
	    if (driver instanceof JavascriptExecutor) {
			JavascriptExecutor jsDriver = (JavascriptExecutor)driver;
					
	        for (int i = 0; i< timeoutInSeconds; i++) 
	        {
	        	try {
	        		Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
	        		  // return should be a number
	 			   if (numberOfAjaxConnections instanceof Long) {
	 			       Long n = (Long)numberOfAjaxConnections;
	 			       //check n=0
	 			       if (n.intValue()==0){
	 			    	   break;
	 			       }
	 			       //System.out.println("\t wait for "+by.toString());
	 			       //System.out.println("\t No. of active jquery ajax calls: " + n.intValue());
	 			       explicitWait(500);
	 			   }
	        	}catch (WebDriverException e){
	        		//System.out.println("\t  jQuery is not used in current (pop up) page");
	        		break;
	        	}
		       
		    }
	        // after finish loading Ajax Element, then look for it
	        waitForElementLoad(driver, by);
		}
		else {
			System.out.println("Web driver: " + driver + " cannot execute javascript");
		}
		
	}
	
	/**Wait until page is fully loaded when switching windows. And FirefoxWebDriver has implemented automatically 
	 * 
	 * @param driver
	 */
	public static void waitForPageLoad(WebDriver driver){
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver driver){
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		
		WebDriverWait wait = new WebDriverWait(driver, 30);//wait 30 seconds
		wait.until(pageLoadCondition);
	}
	
	/**Disable JQuery Animation to speed up execution and make execution more stable
	 * 
	 * @param driver
	 */
	public static void disableJQueryAnimation(WebDriver driver){
		((JavascriptExecutor)driver).executeScript("jQuery.fx.off=true");
	}

}
