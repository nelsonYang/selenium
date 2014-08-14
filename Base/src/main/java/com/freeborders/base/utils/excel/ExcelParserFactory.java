package com.freeborders.base.utils.excel;


public interface ExcelParserFactory {

	public ExcelParserFactory getSheet(int value);

	public ExcelParserFactory createSheet(String sheetName, int value);

	public ExcelParserFactory prepareWritableParser(String fileName);

	public ExcelCursor getExcelCursor();

}
