package com.freeborders.base.api;

import java.util.List;

import com.freeborders.base.AbstractTestClass;
import com.freeborders.base.entity.TestClassEntity;

/**
 *
 * @author nelson.yang
 */
public interface TestClassHandler {
    
    public AbstractTestClass getTestClass(final String testClassName);
    
    public AbstractTestClass getTestClass(final Class clazz);
    
    public List<TestClassEntity> getTestClassList();
}
