package com.freeborders.base.context;

import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.freeborders.base.api.TestClassHandler;
import com.freeborders.base.api.TestEmailHandler;
import com.freeborders.base.api.TestLoginHandler;
import com.freeborders.base.api.TestMethodHandler;
import com.freeborders.base.api.TestParameterHandler;
import com.freeborders.base.api.TestReportHandler;
import com.freeborders.base.api.TestSuiteHandler;
import com.freeborders.base.entity.TestLoginInfoEntity;
import com.freeborders.base.entity.TestSuiteEntity;
import com.freeborders.base.enumeration.BrowserType;
import com.freeborders.base.factory.WebDriverFactory;
import com.freeborders.base.threadlocal.ThreadLocalManager;

/**
 * 
 * @author nelson.yang
 */
public final class TestApplicationContext {
	private TestApplicationContext() {
	}

	public final static TestApplicationContext CTX = new TestApplicationContext();
	private TestSuiteHandler testSuiteHandler;
	private TestLoginHandler testLoginHandler;
	private TestClassHandler testClassHandler;
	private TestMethodHandler testMethodHandler;
	private TestParameterHandler testParameterHandler;
	private TestReportHandler testReportHandler;
	private TestEmailHandler testEmailHandler;
	private ThreadLocalManager<TestLoginInfoEntity> threadLocalManager;
	private WebDriverFactory webDriverFactory;

	/**
	 * @return the testSuiteHandler
	 */
	public TestSuiteHandler getTestSuiteHandler() {
		return testSuiteHandler;
	}

	/**
	 * @param testSuiteHandler
	 *            the testSuiteHandler to set
	 */
	void setTestSuiteHandler(TestSuiteHandler testSuiteHandler) {
		this.testSuiteHandler = testSuiteHandler;
	}

	/**
	 * @return the testLoginHandler
	 */
	public TestLoginHandler getTestLoginHandler() {
		return testLoginHandler;
	}

	/**
	 * @param testLoginHandler
	 *            the testLoginHandler to set
	 */
	void setTestLoginHandler(TestLoginHandler testLoginHandler) {
		this.testLoginHandler = testLoginHandler;
	}

	/**
	 * @return the testClassHandler
	 */
	public TestClassHandler getTestClassHandler() {
		return testClassHandler;
	}

	/**
	 * @param testClassHandler
	 *            the testClassHandler to set
	 */
	void setTestClassHandler(TestClassHandler testClassHandler) {
		this.testClassHandler = testClassHandler;
	}

	/**
	 * @return the testMethodHandler
	 */
	public TestMethodHandler getTestMethodHandler() {
		return testMethodHandler;
	}

	/**
	 * @param testMethodHandler
	 *            the testMethodHandler to set
	 */
	void setTestMethodHandler(TestMethodHandler testMethodHandler) {
		this.testMethodHandler = testMethodHandler;
	}

	/**
	 * @return the testReportHandler
	 */
	public TestReportHandler getTestReportHandler() {
		return testReportHandler;
	}

	/**
	 * @param testReportHandler
	 *            the testReportHandler to set
	 */
	void setTestReportHandler(TestReportHandler testReportHandler) {
		this.testReportHandler = testReportHandler;
	}

	/**
	 * @return the testEmailHandler
	 */
	public TestEmailHandler getTestEmailHandler() {
		return testEmailHandler;
	}

	/**
	 * @param testEmailHandler
	 *            the testEmailHandler to set
	 */
	void setTestEmailHandler(TestEmailHandler testEmailHandler) {
		this.testEmailHandler = testEmailHandler;
	}

	public Map<String, TestSuiteEntity> getTestSuiteMap() {
		return null;
	}

	/**
	 * @return the threadLocalManager
	 */
	public ThreadLocalManager<TestLoginInfoEntity> getThreadLocalManager() {
		return threadLocalManager;
	}

	/**
	 * @param threadLocalManager
	 *            the threadLocalManager to set
	 */
	void setThreadLocalManager(
			ThreadLocalManager<TestLoginInfoEntity> threadLocalManager) {
		this.threadLocalManager = threadLocalManager;
	}

	public String getLoginUrl() {
		return this.threadLocalManager.getThreadLocal().getLoginUrl();
	}

	public String getUserName() {
		return this.threadLocalManager.getThreadLocal().getUserName();
	}

	public String getPassword() {
		return this.threadLocalManager.getThreadLocal().getPassword();
	}

	public String getSession() {
		return this.threadLocalManager.getThreadLocal().getSession();
	}

	void setTestLoginInfoEntity(TestLoginInfoEntity testLoginInfoEntity) {
		this.threadLocalManager.openThreadLocal(testLoginInfoEntity);
	}

	/**
	 * @param webDriverFactory
	 *            the webDriverFactory to set
	 */
	void setWebDriverFactory(WebDriverFactory webDriverFactory) {
		this.webDriverFactory = webDriverFactory;
	}

	/**
	 * get a specified webDriver
	 * 
	 * @return the webDriver
	 */
	public WebDriver getWebDriver(BrowserType browserType) {
		return this.webDriverFactory.getWebDriver(browserType);
	}

	/**
	 * get the predefined default browser driver,specified by properties
	 * 
	 * @return
	 */
	public WebDriver getDefaultDriver() {
		return this.webDriverFactory.getDefaultDriver();
	}

	/**
	 * create a new default web driver ,will not be stored in map and closed by
	 * system automatically. remember to close it after using
	 * 
	 * @return new driver same browse type as default driver
	 */
	public WebDriver createDefaultDriver() {
		return this.webDriverFactory.createDefaultWebDriver();
	}

	/**
	 * recreate a new default web driver ,will replace the default driver stored
	 * in context. Do not need to close it manually
	 * 
	 * @return default driver
	 */
	public WebDriver recreateDefaultDriver() {
		return this.webDriverFactory.recreateDefaultWebDriver();
	}

	public void killDefaultBrowser() {
		this.webDriverFactory.killDefaultBrowser();
	}

	public void killDefaultServer() {
		this.webDriverFactory.killDefaultServer();
	}

	/**
	 * set specified browserType
	 * 
	 * @param browserType
	 */
	void setDefaultDriver(BrowserType browserType) {
		this.webDriverFactory.setDefaultDriver(browserType);
	}

	public TestParameterHandler getTestParameterHandler() {
		return testParameterHandler;
	}

	public void setTestParameterHandler(TestParameterHandler testParameterHandler) {
		this.testParameterHandler = testParameterHandler;
	}
}
