package com.freeborders.base.entity;

import java.util.List;

/**
 *
 * @author nelson.yang
 */
public class TestSuiteEntity {
    private String testSuiteName;
    private List<TestClassEntity> testClassList;

    /**
     * @return the testSuiteName
     */
    public String getTestSuiteName() {
        return testSuiteName;
    }

    /**
     * @param testSuiteName the testSuiteName to set
     */
    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    /**
     * @return the testClassList
     */
    public List<TestClassEntity> getTestClassList() {
        return testClassList;
    }

    /**
     * @param testClassList the testClassList to set
     */
    public void setTestClassList(List<TestClassEntity> testClassList) {
        this.testClassList = testClassList;
    }
    
}
