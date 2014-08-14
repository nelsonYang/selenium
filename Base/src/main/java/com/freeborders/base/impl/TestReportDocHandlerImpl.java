package com.freeborders.base.impl;

import com.freeborders.base.api.TestReportHandler;
import com.freeborders.base.entity.TestClassEntity;
import com.freeborders.base.entity.TestMethodEntity;

/**
 *
 * @author nelson
 */
public class TestReportDocHandlerImpl implements TestReportHandler{
    private int times = 0;
    private String folder;
    @Override
    public String generateTestReport(String testClassName) {
        return null;
    }
    
	/**
	 * add the url of image which produced in the test case into the report.
	 * 
	 */
	public String appendImage(String testCaseID, String imageName,
			String testClassName, String modelName) {
		return null;
	}


	public void log(TestMethodEntity testMethod, final boolean status,
			final String reason, TestClassEntity testClassEntity) {
	}

	@Override
	public int timeAdd() {
		return times++;
	}

	@Override
	public void writeFile(TestClassEntity testClassEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addClassSummary(String classAnnotationName, String key, String value) {
		// TODO Auto-generated method stub
		
	}
    
}
