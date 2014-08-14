package com.freeborders.base.annotation;

import com.freeborders.base.enumeration.ModelNameEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nelson.yang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestMethod {
    //use for test case number
    public String  testMethodName();
    //use for the test case Id.
    public String testCaseId() default "";
    public int testMethodNum() default 1;
    public String desc();
    public String[] fieldNames() default {};
    public String[] fieldValues() default {};
    public ModelNameEnum modelName();
    // true when exception happens , continue next test
    public boolean exceptionContinue() default true;
}
