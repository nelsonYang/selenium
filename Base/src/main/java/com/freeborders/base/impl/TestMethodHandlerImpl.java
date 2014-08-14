package com.freeborders.base.impl;

import java.util.Collections;
import java.util.List;

import com.freeborders.base.api.TestMethodHandler;
import com.freeborders.base.entity.TestMethodEntity;

/**
 *
 * @author nelson.yang
 */
public class TestMethodHandlerImpl  implements TestMethodHandler{
    private List<TestMethodEntity> testMethodList;
    
    public TestMethodHandlerImpl(List<TestMethodEntity> testMethodList){
        this.testMethodList = Collections.unmodifiableList(testMethodList);
    }

    @Override
    public List<TestMethodEntity> getTestMethodList() {
       return this.testMethodList;
    }



}
