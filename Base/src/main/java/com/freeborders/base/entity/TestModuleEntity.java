package com.freeborders.base.entity;

import java.util.HashSet;
import java.util.Set;

import com.freeborders.base.enumeration.ModelNameEnum;

public class TestModuleEntity {
	private Set<ModelNameEnum> excelModels=new HashSet<ModelNameEnum>();

	public void addParseExcel(ModelNameEnum modelExcel){
		excelModels.add(modelExcel);
	}
	
	public Set<ModelNameEnum> getExcelModels() {
		return excelModels;
	}
}
