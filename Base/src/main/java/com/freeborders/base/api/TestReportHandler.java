package com.freeborders.base.api;

import com.freeborders.base.entity.TestClassEntity;
import com.freeborders.base.entity.TestMethodEntity;
import com.freeborders.base.entity.TestReportMap;

public interface TestReportHandler {
	public int timeAdd();

	/**
	 * class name annotation,test case id, report
	 */
	public final TestReportMap testReportMap = new TestReportMap();

	public String generateTestReport(String testClassName);

	public void log(TestMethodEntity testMethod, boolean status, String reason, TestClassEntity testClassEntity);

	public String appendImage(String testCaseId, String imageName, String testClassName, String modelName);

	/**
	 * write the report to file
	 * 
	 * @param location
	 * @param modelName
	 */
	public void writeFile(TestClassEntity testClassEntity);
	/**
	 * add some summary to this excel which is used for report
	 * @param classAnnotationName
	 * @param key
	 * @param value
	 */
	void addClassSummary(String classAnnotationName, String key, String value);
}
