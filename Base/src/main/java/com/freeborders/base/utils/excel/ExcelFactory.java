package com.freeborders.base.utils.excel;

import java.io.File;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.freeborders.base.utils.excel.impl.ExcelCursorXlsImpl;
import com.freeborders.base.utils.excel.impl.ExcelCursorXlsxImpl;

public class ExcelFactory {

	public static ExcelCursor parseExcelFile(String location) throws InvalidFormatException, IOException {
		File file = new File(location);
		NPOIFSFileSystem npoifs = null;
		OPCPackage pkg = null;
		Workbook wb = WorkbookFactory.create(file);
//		try {
//			npoifs = new NPOIFSFileSystem(file);
//			wb = WorkbookFactory.create(npoifs);
//		} catch (OfficeXmlFileException ofe) {
//			pkg = OPCPackage.open(file);
//			wb = WorkbookFactory.create(pkg);
//		}
		ExcelCursor curso = null;
		if (wb instanceof HSSFWorkbook) {
			curso = new ExcelCursorXlsImpl((HSSFWorkbook) wb);
		} else if (wb instanceof XSSFWorkbook) {
			curso = new ExcelCursorXlsxImpl((XSSFWorkbook) wb);
		}
		// curso.setExcelFile(wb.);
		return curso;
	}

	/**
	 * return column index ,Index is (0-based physical & logical)
	 * 
	 * @param columnName
	 * @return
	 */
	public static int columnNameToIndex(String columnName) {
		int index = 0;
		int[] digitals = new int[] { 0, 0, 0 };
		byte[] bts = columnName.toUpperCase().getBytes();
		for (int i = 0; i < bts.length; i++) {
			digitals[i] = bts[bts.length - 1 - i] - 64;
		}
		for (int i = 0; i < digitals.length; i++) {
			index += Math.pow(26, i) * digitals[i];
		}
		// Index is (0-based physical & logical)
		return --index;
	}

}
