package com.freeborders.base.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.freeborders.base.AbstractTestClass;
import com.freeborders.base.api.TestParameterHandler;
import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.enumeration.ModelNameEnum;
import com.freeborders.base.exception.TestException;

/**
 * 
 * @author nelson.yang
 */
public class TestClassEntity {
	private String testClassName;
	private String excelSheet;
	private ModelNameEnum modelName;
	private List<TestMethodEntity> testMethodList;
	private AbstractTestClass testClassInstance;
	private Map<String, Map<String, String>> params = new HashMap<String, Map<String, String>>();

	public void parseParameters(TestParameterHandler handler) {
		// originalParams , key=OI_Plaintiff Attorneys_010,
		// value=OiSTATE="ALASKA";PAName="Asland";CaseId="1000098"; should be processed
		while (true) {
			String result = handler.getResult().get(modelName);
			if ("true".equals(result))
				break;
			else if (result != null)
				throw new TestException(result);
		}
		Map<String, String> originalParams = handler.getParams().get(modelName).get(excelSheet);
		if (originalParams == null) {
			try {
				throw new TestException("Test Case '" + testClassName
						+ "' parameters parsing has failed.Desired sheet name is '" + excelSheet + "'");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		TestApplicationContext.CTX.getTestReportHandler().addClassSummary(this.testClassName,
				TestReportMap.SHEET_TOTAL_TC, originalParams.size() + "");
		for (Entry<String, String> entry : originalParams.entrySet()) {
			String testCaseId = entry.getKey();
			Map<String, String> keyValues = new HashMap<String, String>();
			// OiSTATE="ALASKA";PAName="Asland";CaseId="1000098";
			for (String keyValue : entry.getValue().split(";")) {
				if (keyValue.isEmpty())
					continue;
				String[] temp = keyValue.trim().split("=");
				keyValues.put(temp[0], temp[1]);
			}
			params.put(testCaseId, keyValues);
		}
		testClassInstance.setParams(params);
	}
	
	public String getParamValue(String testCaseID,String key){
		return this.params.get(testCaseID).get(key);
	}

	/**
	 * @return the testClassName
	 */
	public String getTestClassName() {
		return testClassName;
	}

	/**
	 * @param testClassName
	 *            the testClassName to set
	 */
	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}

	/**
	 * @return the testMethodList
	 */
	public List<TestMethodEntity> getTestMethodList() {
		return testMethodList;
	}

	/**
	 * @param testMethodList
	 *            the testMethodList to set
	 */
	public void setTestMethodList(List<TestMethodEntity> testMethodList) {
		this.testMethodList = testMethodList;
	}

	/**
	 * @return the testClassInstance
	 */
	public AbstractTestClass getTestClassInstance() {
		return testClassInstance;
	}

	/**
	 * @param testClassInstance
	 *            the testClassInstance to set
	 */
	public void setTestClassInstance(AbstractTestClass testClassInstance) {
		this.testClassInstance = testClassInstance;
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

	public String getExcelSheet() {
		return excelSheet;
	}

	public void setExcelSheet(String excelSheet) {
		this.excelSheet = excelSheet;
	}
}
