package com.freeborders.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import com.freeborders.base.constant.PathConstant;
import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.exception.TestException;
import com.freeborders.base.log.Logger;
import com.freeborders.base.utils.WaitElementPresent;

/**
 * @author nelson.yang
 */
public class AbstractTestClass {
	protected WebDriver webDriver;
	protected Map<String, Map<String, String>> params = new HashMap<String, Map<String, String>>();
	protected Log log = LogFactory.getLog(this.getClass());

	public void setParams(Map<String, Map<String, String>> params) {
		this.params = params;
	}

	protected String getParameter(String testCaseID, String key) {
		String value = null;
		try {
			value = params.get(testCaseID).get(key);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			this.throwException("no such test case Id parameters found:" + testCaseID);
		}
		return value;
	}

	public void beforeSuite() {
	}

	public void afterSuite() {
	}

	public void beforeClass() {
	}

	public void afterClass() {
	}

	public void setUp() {
		// TestLoginHandler testLoginHandler =
		// TestApplicationContext.CTX.getTestLoginHandler();
		// testLoginHandler.login();
		try {
			webDriver = TestApplicationContext.CTX.getDefaultDriver();
			webDriver.get(PathConstant.HOME_PATH);
			WaitElementPresent.waitTitle(webDriver, 5, "ABACUS - Home");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	public void tearDown() {
		// close all dialog
		// WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		// WindowSwitcher.closeAllDialog(webDriver);
		// WaitTimeoutUtils.sleep(1);
	}

	public void assertNotNull() {
	}

	/**
	 * throw a custom exception
	 * 
	 * @param message
	 */
	protected void throwException(String message) {
		throw new TestException(message);
	}

	public void assertEqual(String expection, String result) {
		// Logger.debug("assertEqual", result);
		if (expection.trim().equals(result.trim())) {
			// Logger.info("assertEqual", "Success");
			log.info("Success");
		} else {
			Logger.info("assertEqual", "Failure");
			throw new TestException("Expected message:" + expection + ". Actual message:" + result, expection, result);
		}
	}

	public void assertGreaterAndEqual(String expection, String result) {
		log.debug(result);
		if (expection.compareTo(result.trim()) > 0) {
			log.info("Failure");
			throw new TestException(result + " is not great and Equal than " + expection, result, expection);
		} else {
			log.info("Success");
		}
	}

	public void assertContain(String conatinExpection, String result) {
		log.info(result);
		if (result.contains(conatinExpection)) {
			log.info("Success");
		} else {
			log.info("Failure");
			throw new TestException(result + " is not contain " + conatinExpection, result, conatinExpection);
		}
	}

	public void assertNotEqual(String expection, String result) {
		log.debug(result);
		if (!expection.equals(result)) {
			log.info("Success");
		} else {
			log.info("Failure");
			throw new TestException(expection + ": is  equal :" + result, expection, result);
		}
	}

	public void assertSuccess() {
		log.info("Success");
	}

	public void noData() {
		log.info("NoData");
		throw new TestException("no data, can not test. please add data then test");
	}
}
