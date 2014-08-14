package com.freeborders.base.api;


import java.util.Map;
import java.util.Set;

import com.freeborders.base.enumeration.ModelNameEnum;

public interface TestParameterHandler {
	// this is the title of summary column in excel
	final static String CASE_SUMMARY="Summary";
	public Map<ModelNameEnum, String> getResult();
	boolean parseTestCase(Set<ModelNameEnum> model);
	/**
	 * get excel parameters, 3 level, module,page(excel sheet) and caseId & parameters 
	 * @return
	 */
	public Map<Enum<?>, Map<String, Map<String, String>>> getParams();
}
