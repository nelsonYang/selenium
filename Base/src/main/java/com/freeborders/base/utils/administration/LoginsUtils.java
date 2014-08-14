package com.freeborders.base.utils.administration;

import org.openqa.selenium.WebDriver;

public class LoginsUtils {
	/**
	 * change the first name and last name in drop down list of the Logins page
	 * @param webDriver
	 * @param userName
	 * @return
	 */
    public static String resetUserName(WebDriver webDriver, String userName) {
    	return userName.substring(userName.indexOf(" "),userName.length()).trim() + " " + userName.substring(0,userName.indexOf(" ")).trim();
    }
}
