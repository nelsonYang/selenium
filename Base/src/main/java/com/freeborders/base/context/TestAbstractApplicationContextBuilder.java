package com.freeborders.base.context;

import java.util.List;

import com.freeborders.base.constant.PathConstant;
import com.freeborders.base.entity.TestClassEntity;
import com.freeborders.base.entity.TestLoginInfoEntity;
import com.freeborders.base.entity.TestMethodEntity;
import com.freeborders.base.entity.TestModuleEntity;
import com.freeborders.base.entity.TestSuiteEntity;
import com.freeborders.base.factory.WebDriverFactory;
import com.freeborders.base.threadlocal.ThreadLocalManager;
import com.freeborders.base.utils.ConfigurationParser;

/**
 * @author nelson.yang
 */
public abstract class TestAbstractApplicationContextBuilder {

	protected TestLoginInfoEntity testLoginInfoEntity;

	public TestAbstractApplicationContextBuilder(
			TestLoginInfoEntity testLoginInfoEntity) {
		this.testLoginInfoEntity = testLoginInfoEntity;
	}

	public TestAbstractApplicationContextBuilder() {
		this.testLoginInfoEntity = new TestLoginInfoEntity();
	}
	
	public TestAbstractApplicationContextBuilder(TestModuleEntity entity) {
		this.testLoginInfoEntity = new TestLoginInfoEntity();
	}


	public void setTestLoginInfoEntity(TestLoginInfoEntity testLoginInfoEntity) {
		this.testLoginInfoEntity = testLoginInfoEntity;
	}

	public void build() {
		testLoginInfoEntity.setLoginUrl(PathConstant.LOGIN_PATH);
		// login info
		ThreadLocalManager<TestLoginInfoEntity> threadLocalManager = new ThreadLocalManager<TestLoginInfoEntity>();
		TestApplicationContext.CTX.setThreadLocalManager(threadLocalManager);
		TestApplicationContext.CTX.setTestLoginInfoEntity(testLoginInfoEntity);
		// webDriverFactory
		TestApplicationContext.CTX.setWebDriverFactory(WebDriverFactory
				.getInstance());

		// parser
		ConfigurationParser configParser = new ConfigurationParser();
		configParser.configurationParse(new String[] { "com.freeborders" });
		List<TestMethodEntity> testMethodList = configParser
				.getTestMethodList();
		List<TestClassEntity> testClassList = configParser.getTestClassList();
		List<TestSuiteEntity> testSuiteList = configParser.getTestSuiteList();
		this.buildTestMethodHandler(testMethodList);
		this.buildTestClassHandler(testClassList);
		this.buildTestSuiteHandler(testSuiteList);
		this.buildTestLoginHandler();
		this.buildTestEmailHandler();
		this.buildTestReportHandler();

	}

	protected abstract void buildTestMethodHandler(
			final List<TestMethodEntity> testMethodList);

	protected abstract void buildTestClassHandler(
			final List<TestClassEntity> testClassList);

	protected abstract void buildTestSuiteHandler(
			final List<TestSuiteEntity> TestSuiteList);

	protected abstract void buildTestLoginHandler();

	protected abstract void buildTestEmailHandler();

	protected abstract void buildTestReportHandler();

}
