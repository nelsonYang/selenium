package com.freeborders.base.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import com.freeborders.base.AbstractTestClass;
import com.freeborders.base.api.TestClassHandler;
import com.freeborders.base.api.TestExecutor;
import com.freeborders.base.api.TestLoginHandler;
import com.freeborders.base.api.TestReportHandler;
import com.freeborders.base.api.TestSuiteHandler;
import com.freeborders.base.constant.PathConstant;
import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.context.TestApplicationContextBuilderImpl;
import com.freeborders.base.entity.TestClassEntity;
import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.TestMethodEntity;
import com.freeborders.base.entity.TestSuiteEntity;
import com.freeborders.base.log.Logger;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

/**
 * todo 重试的动作
 * 
 * @author nelson.yang
 */
public final class TestExecutorImpl implements TestExecutor {
	Log log = LogFactory.getLog(TestExecutorImpl.class);

	@Override
	public final void test() {
		TestSuiteHandler testSuiteHanlder = TestApplicationContext.CTX.getTestSuiteHandler();
		TestClassHandler testClassHandler = TestApplicationContext.CTX.getTestClassHandler();
		TestLoginHandler testLoginHandler = TestApplicationContext.CTX.getTestLoginHandler();

		List<TestSuiteEntity> testSuiteList = testSuiteHanlder.getTestSuiteList();
		List<TestClassEntity> testClassList = testClassHandler.getTestClassList();
		// execute specified times
		int executeTimes = Integer.parseInt(TestApplicationContextBuilderImpl.getProperty(PathConstant.EXECUTE_TIMES,
				"1"));
		TestReportHandler testReportHandler = TestApplicationContext.CTX.getTestReportHandler();
		testLoginHandler.login();

		if (!testSuiteList.isEmpty()) {
			for (int i = 1; i <= executeTimes; i++) {
				for (TestSuiteEntity testSuiteEntity : testSuiteList) {
					List<TestClassEntity> classList = testSuiteEntity.getTestClassList();
					if (!classList.isEmpty()) {
						for (TestClassEntity testClassEntity : classList) {
							if (i == 1 && !"".equals(testClassEntity.getExcelSheet())) {
								// first time parse excel params
								testClassEntity.parseParameters(TestApplicationContext.CTX.getTestParameterHandler());
							}
							this.invokeClass(testClassEntity);
							// last run time ,generate report
							if (i == executeTimes) {
								generateReport(testClassEntity);
							}
						}
					}
				}
				testReportHandler.timeAdd();
			}
		} else if (!testClassList.isEmpty()) {
			for (int i = 1; i <= executeTimes; i++) {
				for (TestClassEntity testClassEntity : testClassList) {
					if (i == 1 && !"".equals(testClassEntity.getExcelSheet())) {
						// first time parse excel params
						testClassEntity.parseParameters(TestApplicationContext.CTX.getTestParameterHandler());
					}
					this.invokeClass(testClassEntity);
					// last run time ,generate report
					if (i == executeTimes) {
						generateReport(testClassEntity);
					}
				}
				testReportHandler.timeAdd();
			}
		}
		// send report
		// TestApplicationContext.CTX.getTestEmailHandler().sendEmails(emailContent);

		// end whole program
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		try {
			testLoginHandler.logout();
			// already closed
			webDriver.close();
			webDriver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			TestApplicationContext.CTX.killDefaultBrowser();
			TestApplicationContext.CTX.killDefaultServer();
		}
	}

	/**
	 * generate report file
	 * 
	 * @param testClassEntity
	 */
	private void generateReport(TestClassEntity testClassEntity) {
		TestReportHandler testReportHandler = TestApplicationContext.CTX.getTestReportHandler();
		// generateTestReport
		String emailContent = testReportHandler.generateTestReport(testClassEntity.getTestClassName());
		log.debug("emailContent" + emailContent);
		// write report
		// String location = filePath.substring(0,filePath.lastIndexOf("\\"));
		// get the image folder name and set it to be the html file name.
		if (emailContent == null)
			return;
		testReportHandler.writeFile(testClassEntity);
	}

	private void invokeClass(TestClassEntity testClassEntity) {
		log.info("invoke class");
		AbstractTestClass testClassInstance = testClassEntity.getTestClassInstance();
		testClassInstance.beforeClass();
		List<TestMethodEntity> methodList = testClassEntity.getTestMethodList();
		if (!methodList.isEmpty()) {
			for (TestMethodEntity testMethod : methodList) {
				log.info("method order is " + testMethod.getMethod().getName());
				if (!this.invokeMethod(testClassInstance, testMethod, testClassEntity)) {
					break;
				}
			}
		}
		testClassInstance.afterClass();
	}

	/**
	 * true if continue
	 * 
	 * @param testClassInstance
	 * @param testMethod
	 * @return continue or not
	 */
	private boolean invokeMethod(AbstractTestClass testClassInstance, TestMethodEntity testMethod,
			TestClassEntity testClassEntity) {
		Logger.info("TestExecutorImpl.invokeMethod", testMethod.getTestMethodName().concat(" setUp"));
		testClassInstance.setUp();
		String errorMessage = "";
		TestImageEntity imageEntity = new TestImageEntity();
		imageEntity.setModelName(testMethod.getModelName());
		imageEntity.setTestClassName(testClassEntity.getTestClassName());
		imageEntity.setTestCaseId(testMethod.getTestCaseID());
		imageEntity.setTestMethodName(testMethod.getMethod().getName());
		imageEntity.setWebDriver(TestApplicationContext.CTX.getDefaultDriver());
		boolean exceptionContinue = testMethod.isExceptionContinue();
		boolean pass = true;
		try {
			testMethod.getMethod().invoke(testClassInstance, new Object[] { imageEntity });
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			Logger.severe("invokeMethod " + testMethod.getTestMethodName(), ex.getMessage(), ex);
			pass = false;
			StringWriter writer = new StringWriter();
			ex.printStackTrace(new PrintWriter(writer));
			errorMessage = writer.getBuffer().toString();
			// ScreenCaptureUtils.captureByDriver(testClassInstance.getClass(),
			// testMethod.getMethod().getName());
			ScreenCaptureUtils.captureByDriver(imageEntity);
			if (!errorMessage.isEmpty()) {
				StringBuffer sbf = new StringBuffer();
				Pattern pa = Pattern.compile("Caused by:(.+)[\r\n]");
				Matcher match = pa.matcher(errorMessage);
				while (match.find()) {
					sbf.append(match.group());
				}
				errorMessage = sbf.toString();
			}
			// exception happens kill ie and log in
		} finally {
			Logger.info("TestExecutorImpl.invokeMethod", testMethod.getTestMethodName().concat(" tearDown"));
			log.info("tear down");
			TestApplicationContext.CTX.getTestReportHandler().log(testMethod, pass, errorMessage, testClassEntity);
			testClassInstance.tearDown();
		}
		// exception happens and need to continue kill and log in again
		if (!pass && exceptionContinue) {
			WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
			WindowSwitcher.closeAllDialog(webDriver);
			// reserved
			// TestApplicationContext.CTX.killDefaultBrowser();
			// TestApplicationContext.CTX.killDefaultServer();
			// TestApplicationContext.CTX.recreateDefaultDriver();
			// TestApplicationContext.CTX.getTestLoginHandler().login();
		}
		return exceptionContinue;
	}

	/**
	 * take the screen shot
	 * 
	 * @param testMethodName
	 * @param modelName
	 * @return the image name.
	 */
	private String takeScreenshot(String testMethodName, String modelName) {
		String path = null;
		String imageName = null;
		// try {
		WaitTimeoutUtils.sleep(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		imageName = testMethodName + " " + sdf.format(new Date()).replaceAll(":", "-") + ".png";
		// String fileName = testMethodName + "-" + sdf.format(new Date()).replaceAll(":", "-") +
		// ".png";
		// path = TestApplicationContextBuilderImpl.getProperty(PathConstant.DATA_Folder) +
		// modelName + "\\";
		// File tempFile = ((TakesScreenshot) TestApplicationContext.CTX.getDefaultDriver())
		// .getScreenshotAs(OutputType.FILE);
		// File dirfile = new File(path);
		// if (!dirfile.exists()) {
		// dirfile.mkdirs();
		// }
		// path = path + fileName;
		// File descfile = new File(path);
		// if (descfile.createNewFile()) {
		// Files.copy(tempFile, descfile);
		// } else {
		// Logger.warn("takescreenshot", "create file failure");
		// }
		// } catch (org.openqa.selenium.NoSuchWindowException ex) {
		// // driver can not be contacted ,take picture fail
		// path = null;
		// Logger.severe("takescreenshot path=" + path, ex.getMessage(), ex);
		// } catch (Exception ex) {
		// Logger.severe("takescreenshot", ex.getMessage(), ex);
		// }
		// Logger.info("takescreenshot", path);
		// return path;
		return imageName;

	}
}
