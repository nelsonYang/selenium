package com.freeborders.base.impl;

import java.util.Collections;
import java.util.List;

import com.freeborders.base.api.TestSuiteHandler;
import com.freeborders.base.entity.TestSuiteEntity;

/**
 *
 * @author nelson.yang
 */
public class TestSuiteHandlerImpl implements TestSuiteHandler {
    private List<TestSuiteEntity> testSuiteList;
    
    public TestSuiteHandlerImpl( List<TestSuiteEntity> testSuiteList){
        this.testSuiteList = Collections.unmodifiableList(testSuiteList);
    }

    @Override
    public List<TestSuiteEntity> getTestSuiteList() {
        return this.testSuiteList;
    }

    
}
