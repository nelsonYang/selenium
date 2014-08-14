package com.freeborders.base.utils.excel.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.freeborders.base.utils.excel.ExcelCursor;
import com.freeborders.base.utils.excel.ExcelFactory;

/**
 * HSSF is the POI Project's pure Java implementation of the Excel '97(-2007) file format.
 * 
 * @author tom.luo
 * 
 */
public class ExcelCursorXlsImpl implements ExcelCursor {
	Log log = LogFactory.getLog(ExcelCursorXlsImpl.class);
	private HSSFWorkbook wb = null;
	private HSSFSheet currentSheet = null;
	private int sheetIndex = -1;
	private InputStream excelFile;

	public ExcelCursorXlsImpl(HSSFWorkbook book) {
		this.wb = book;
	}

	@Override
	public boolean moveNextSheet() {
		// TODO Auto-generated method stub
		boolean hasNext = false;
		if (sheetIndex < wb.getNumberOfSheets() - 1) {
			sheetIndex++;
			currentSheet = wb.getSheetAt(sheetIndex);
			hasNext = true;
		}
		return hasNext;
	}

	@Override
	public int getMaxRow() {
		// TODO Auto-generated method stub
		return currentSheet.getLastRowNum();
	}

	@Override
	public Map<String, String> parseSheetParameters(String keyColum, String[] valueColums, int startRow) {
		// TODO Auto-generated method stub
		int keyColumnIndex = ExcelFactory.columnNameToIndex(keyColum);
		List<Integer> valueRange = new ArrayList<Integer>();
		for (String str : valueColums) {
			valueRange.add(ExcelFactory.columnNameToIndex(str));
		}
		Map<String, String> data = new TreeMap<String, String>();
		for (int i = startRow; i <= currentSheet.getLastRowNum(); i++) {
			HSSFRow row = currentSheet.getRow(i);
			if (row == null)
				continue;
			String key=null;
			try {
				key = row.getCell(keyColumnIndex).toString().trim();
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				log.error("Error:sheet '"+currentSheet.getSheetName()+"' key column '"+keyColum+"',row index:"+i+" is empty.",e);
				continue;
			}
			String content = "";
			for (int temp : valueRange) {
				content += row.getCell(temp).toString().trim();
			}
			if (content.length() > 0)
				data.put(key, content);

		}
		return data;
	}

	@Override
	public boolean moveToSheet(String name) {
		// TODO Auto-generated method stub
		boolean flag = false;
		HSSFSheet desired = wb.getSheet(name);
		if (desired != null) {
			currentSheet = desired;
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean moveToSheet(int index) {
		// TODO Auto-generated method stub
		if (index > wb.getNumberOfSheets() - 1)
			return false;
		currentSheet = wb.getSheetAt(index);
		return true;
	}

	@Override
	public Sheet currentSheet() {
		// TODO Auto-generated method stub
		return currentSheet;
	}

	@Override
	public Workbook workBook() {
		// TODO Auto-generated method stub
		return wb;
	}

	@Override
	public void setExcelFile(InputStream excelStream) {
		// TODO Auto-generated method stub
		this.excelFile=excelStream;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		try {
			this.excelFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	@Override
	public Map<String, String> parseSheet(String keyColumn, String[] contentColumns,int titleRow, String[] parametersColumns,
			int startRow) {
		// TODO Auto-generated method stub
				int keyColumnIndex = ExcelFactory.columnNameToIndex(keyColumn);
				List<Integer> paramsRange = new ArrayList<Integer>();
				List<Integer> contentRange = new ArrayList<Integer>();
				List<String> contentColumnTitle = new ArrayList<String>();
				HSSFRow row = currentSheet.getRow(titleRow);
				for (int i = 0; i < contentColumns.length; i++) {
					int columnNum = ExcelFactory.columnNameToIndex(contentColumns[i]);
					contentRange.add(columnNum);
					try {
						contentColumnTitle.add(row.getCell(columnNum).toString().trim());
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						log.error("Error:sheet '"+currentSheet.getSheetName()+"' column '" + columnNum + "',row index:" + titleRow + " is empty.", e);
					}
				}
				for (String str : parametersColumns) {
					paramsRange.add(ExcelFactory.columnNameToIndex(str));
				}
				Map<String, String> data = new TreeMap<String, String>();
				for (int i = startRow; i <= currentSheet.getLastRowNum(); i++) {
					row = currentSheet.getRow(i);
					if (row == null)
						continue;
					String key = null;
					try {
						key = row.getCell(keyColumnIndex).toString().trim();
					} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						log.error("Error:sheet '"+currentSheet.getSheetName()+"' key column '" + keyColumn + "',row index:" + i + " is empty.", e);
						continue;
					}
					String content = "";
					for (int temp : paramsRange) {
						content += row.getCell(temp).toString().trim();
					}
					for (int j = 0; j < contentRange.size(); j++) {
						content += contentColumnTitle.get(j) + "=" + row.getCell(contentRange.get(j)).toString().trim()+";" ;
					}
					if (content.length() > 0){
						data.put(key, content);
					}
				}
				return data;
	}
}
