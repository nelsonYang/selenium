package com.freeborders.base.entity;

import java.util.ArrayList;
import java.util.List;

import com.freeborders.base.enumeration.ModelNameEnum;

/**
 *
 * @author nelson
 */
public class TestReportEntity {
    
    private String testMethodName;
    private String testCaseId;
    private ModelNameEnum modelName;
    private boolean status;
    private String reason;
    private String summary;
    private List<String> imageUrl = new ArrayList<String>(0);

    /**
     * @return the testMethodName
     */
    public String getTestMethodName() {
        return testMethodName;
    }

    /**
     * @param testMethodName the testMethodName to set
     */
    public void setTestMethodName(String testMethodName) {
        this.testMethodName = testMethodName;
    }

    /**
     * @return the modelName
     */
    public ModelNameEnum getModelName() {
        return modelName;
    }

    /**
     * @param modelName the modelName to set
     */
    public void setModelName(ModelNameEnum modelName) {
        this.modelName = modelName;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

	public List<String> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(List<String> imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
