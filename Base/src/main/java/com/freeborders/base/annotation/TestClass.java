package com.freeborders.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.freeborders.base.enumeration.ModelNameEnum;

/**
 *
 * @author nelson.yang
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestClass {
    public String testClassName();
    public ModelNameEnum modelName();
    public String sheetName() default "";
    public String browserName() default "ie";
    public String nodeUrl() default "localhost";
    
}
