package com.freeborders.base.context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.freeborders.base.api.TestClassHandler;
import com.freeborders.base.api.TestEmailHandler;
import com.freeborders.base.api.TestLoginHandler;
import com.freeborders.base.api.TestMethodHandler;
import com.freeborders.base.api.TestParameterHandler;
import com.freeborders.base.api.TestReportManager;
import com.freeborders.base.api.TestSuiteHandler;
import com.freeborders.base.constant.PathConstant;
import com.freeborders.base.entity.TestClassEntity;
import com.freeborders.base.entity.TestLoginInfoEntity;
import com.freeborders.base.entity.TestMethodEntity;
import com.freeborders.base.entity.TestModuleEntity;
import com.freeborders.base.entity.TestSuiteEntity;
import com.freeborders.base.enumeration.BrowserType;
import com.freeborders.base.enumeration.DocumentType;
import com.freeborders.base.impl.TestClassHandlerImpl;
import com.freeborders.base.impl.TestEmailHandlerImpl;
import com.freeborders.base.impl.TestLoginHandlerImpl;
import com.freeborders.base.impl.TestMethodHandlerImpl;
import com.freeborders.base.impl.TestParameterHandlerImpl;
import com.freeborders.base.impl.TestReportManagerImpl;
import com.freeborders.base.impl.TestSuiteHandlerImpl;
import com.freeborders.base.log.Logger;

/**
 * 
 * @author nelson.yang
 */

public final class TestApplicationContextBuilderImpl extends TestAbstractApplicationContextBuilder {
	Log log = LogFactory.getLog(TestApplicationContextBuilderImpl.class);
	private DocumentType docType = DocumentType.HTML;
	private TestReportManager testReportManager = new TestReportManagerImpl();
	private static Properties properties;

	public static String getProperty(String key, String defaultvalue) {
		return properties.getProperty(key, defaultvalue);
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public TestApplicationContextBuilderImpl(TestLoginInfoEntity testLoginInfoEntity, DocumentType docType) {
		super(testLoginInfoEntity);
		this.docType = docType;
		this.initProperties();
	}

	public TestApplicationContextBuilderImpl() {
		super();
		this.initProperties();
	}

	public TestApplicationContextBuilderImpl(TestModuleEntity entity) {
		super(entity);
		this.initProperties();
		// initial parse excel, before use parameters will check
		TestParameterHandler impl = new TestParameterHandlerImpl();
		impl.parseTestCase(entity.getExcelModels());
		TestApplicationContext.CTX.setTestParameterHandler(impl);
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		super.build();
		testLoginInfoEntity.setUserName(this.getProperty(PathConstant.LOGIN_USER, "qiu"));
		// set the default type
		BrowserType browserType = Enum.valueOf(BrowserType.class, properties.getProperty(PathConstant.BROWSER_TYPE));
		TestApplicationContext.CTX.setDefaultDriver(browserType);
	}

	private void initProperties() {
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("system.properties");
			log.info("jar path "+TestApplicationContextBuilderImpl.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			// String str =
			// Thread.currentThread().getContextClassLoader().getResource("").getPath();
			properties = new Properties();
			properties.load(is);
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException ex) {
				log.error(ex.getMessage(), ex);
			}
		}
	}

	@Override
	protected void buildTestClassHandler(final List<TestClassEntity> testClassList) {
		TestClassHandler testClassHanlder = new TestClassHandlerImpl(testClassList);
		TestApplicationContext.CTX.setTestClassHandler(testClassHanlder);
	}

	@Override
	protected void buildTestSuiteHandler(final List<TestSuiteEntity> testSuiteList) {
		TestSuiteHandler testSuiteHandler = new TestSuiteHandlerImpl(testSuiteList);
		TestApplicationContext.CTX.setTestSuiteHandler(testSuiteHandler);
	}

	@Override
	protected void buildTestMethodHandler(final List<TestMethodEntity> testMethodList) {
		TestMethodHandler testMethodHandler = new TestMethodHandlerImpl(testMethodList);
		TestApplicationContext.CTX.setTestMethodHandler(testMethodHandler);
	}

	@Override
	protected void buildTestLoginHandler() {
		TestLoginHandler testLoginHandler = new TestLoginHandlerImpl();
		TestApplicationContext.CTX.setTestLoginHandler(testLoginHandler);

	}

	@Override
	protected void buildTestEmailHandler() {
		TestEmailHandler testEmailHandler = new TestEmailHandlerImpl(properties);
		TestApplicationContext.CTX.setTestEmailHandler(testEmailHandler);
	}

	@Override
	protected void buildTestReportHandler() {
		TestApplicationContext.CTX.setTestReportHandler(this.testReportManager.getTestReportHandler(docType));
	}

}
