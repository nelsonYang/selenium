package com.freeborders.base.annotation;

import com.freeborders.base.AbstractTestClass;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author nelson.yang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestSuite {
    public Class<? extends AbstractTestClass>[] suite() default {};
    public String testSuiteName();
}
