package com.freeborders.base.entity;

import org.openqa.selenium.WebDriver;

import com.freeborders.base.enumeration.ModelNameEnum;

public class TestImageEntity {
	private ModelNameEnum modelName;
	private String testMethodName;
	private String testCaseId;
	private String testClassName;
	WebDriver webDriver;
	// default screen capture is the active window
	private boolean fullScreen = false;
	private boolean maxBrowserSize = false;

	/**
	 * set the capture style to be active window capture ,other styles reset to false;
	 */
	private void resetCaptureStyle() {
		this.fullScreen = false;
		this.maxBrowserSize = false;
	}
	
	public void captureFullScreen(){
		resetCaptureStyle();
		this.fullScreen=true;
	}
	
	public void captureBrowserSize(){
		resetCaptureStyle();
		this.maxBrowserSize=true;
	}
	
	public void captureActiveScreen(){
		this.resetCaptureStyle();
	}

	public ModelNameEnum getModelName() {
		return modelName;
	}

	public void setModelName(ModelNameEnum modelName) {
		this.modelName = modelName;
	}

	public String getTestMethodName() {
		return testMethodName;
	}

	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getTestClassName() {
		return testClassName;
	}

	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public boolean isMaxBrowserSize() {
		return maxBrowserSize;
	}

}
