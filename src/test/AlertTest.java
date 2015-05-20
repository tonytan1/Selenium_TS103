package test;

import org.openqa.selenium.WebDriver;

import com.netdimen.model.User;
import com.netdimen.utils.WebDriverUtils;

public class AlertTest {

	public static void testAlert(){
		WebDriver driver = WebDriverUtils.getWebDriver_new();
		User user = new User("bsara","11111111");
		user.login(driver);
		
//		boolean alertPresent = WebDriverUtils.isAlertPresent(driver);
		
//		System.out.println(alertPresent);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		testAlert();
	}

}
