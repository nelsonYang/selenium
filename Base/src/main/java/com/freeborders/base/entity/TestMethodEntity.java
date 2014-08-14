package com.freeborders.base.entity;

import java.lang.reflect.Method;

import com.freeborders.base.annotation.TestMethod;
import com.freeborders.base.enumeration.ModelNameEnum;

/**
 * 
 * @author nelson.yang
 */
public class TestMethodEntity {
	private String testMethodName;
	private String testCaseID;
	private int testMethodNum = 1;
	private ModelNameEnum modelName;
	private String[] fieldNames;
	private String[] fieldValues;
	private String desc;
	private boolean exceptionContinue;

	private Method method;

	public TestMethodEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TestMethodEntity(TestMethod testMethod) {
		this.testMethodName=testMethod.testMethodName();
		this.testCaseID=testMethod.testCaseId();
		this.modelName=testMethod.modelName();
		this.desc=testMethod.desc();
		this.exceptionContinue=testMethod.exceptionContinue();
	}
	
	/**
	 * @return the testMethodName
	 */
	public String getTestMethodName() {
		return testMethodName;
	}

	/**
	 * @param testMethodName
	 *            the testMethodName to set
	 */
	public void setTestMethodName(String testMethodName) {
		this.testMethodName = testMethodName;
	}

	public String getTestCaseID() {
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}
	
	/**
	 * @return the testMethodNum
	 */
	public int getTestMethodNum() {
		return testMethodNum;
	}

	/**
	 * @param testMethodNum
	 *            the testMethodNum to set
	 */
	public void setTestMethodNum(int testMethodNum) {
		this.testMethodNum = testMethodNum;
	}

	/**
	 * @return the fieldNames
	 */
	public String[] getFieldNames() {
		return fieldNames;
	}

	/**
	 * @param fieldNames
	 *            the fieldNames to set
	 */
	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}

	/**
	 * @return the fieldValues
	 */
	public String[] getFieldValues() {
		return fieldValues;
	}

	/**
	 * @param fieldValues
	 *            the fieldValues to set
	 */
	public void setFieldValues(String[] fieldValues) {
		this.fieldValues = fieldValues;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the modelName
	 */
	public ModelNameEnum getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(ModelNameEnum modelName) {
		this.modelName = modelName;
	}
	
	public boolean isExceptionContinue() {
		return exceptionContinue;
	}

	public void setExceptionContinue(boolean exceptionContinue) {
		this.exceptionContinue = exceptionContinue;
	}

	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(Method method) {
		this.method = method;
	}
	
}
