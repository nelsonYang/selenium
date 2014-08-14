package com.freeborders.base.impl;

import java.util.Collections;
import java.util.List;

import com.freeborders.base.AbstractTestClass;
import com.freeborders.base.api.TestClassHandler;
import com.freeborders.base.entity.TestClassEntity;

/**
 *
 * @author nelson.yang
 */
public final class TestClassHandlerImpl implements TestClassHandler{
    private List<TestClassEntity> testClassList;
    public TestClassHandlerImpl(List<TestClassEntity> testClassList){
        this.testClassList =  Collections.unmodifiableList(testClassList);
    }
    
    @Override
    public AbstractTestClass getTestClass(final String testClassName) {
        return null;
    }

    @Override
    public AbstractTestClass getTestClass(final Class clazz) {
        return null;
    }
   
    @Override
    public List<TestClassEntity> getTestClassList() {
          return this.testClassList;
   }
    
}
