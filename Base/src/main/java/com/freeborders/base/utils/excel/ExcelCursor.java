package com.freeborders.base.utils.excel;

import java.io.InputStream;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * This is the interface for read and write excel.Current sheet index is -1 ,use moveNextSheet() to
 * iterate all sheets
 * */
public interface ExcelCursor {
	boolean moveNextSheet();

	boolean moveToSheet(String name);

	/**
	 * 
	 * @param index
	 *            of the sheet number (0-based physical & logical)
	 * @return
	 */
	boolean moveToSheet(int index);

	int getMaxRow();

	/**
	 * parse the current excel sheet to get test parameters and stored in a tree map
	 * 
	 * @param keyColumn
	 *            map key column (like "A,AK,BE")
	 * @param valueColumns
	 *            map value columns (like "A,AK,BE"),will concat cell values
	 * @param startRow
	 *            row to start (0-based physical & logical)
	 * @return
	 */
	Map<String, String> parseSheetParameters(String keyColumn, String[] valueColumns, int startRow);

	/**
	 * parse the current excel sheet to get test parameters & content cells (like Summary column) ,stored in
	 * a tree map
	 * 
	 * @param keyColumn
	 *            test case id
	 * @param contentColumns
	 *            key is column title ,value
	 * @param titleRow
	 * 			  column title row index (0-based physical & logical)
	 * @param parametersColumns
	 *            column title is not used
	 * @param startRow
	 *            row to start (0-based physical & logical)
	 * @return
	 */

	Map<String, String> parseSheet(String keyColumn, String[] contentColumns, int titleRow, String[] parametersColumns,
			int startRow);

	/**
	 * 
	 * @return current sheet ,it could be HSSFSheet or XSSFSheet
	 */
	Sheet currentSheet();
	/**
	 * 
	 * @return get workbook ,it could be HSSFWorkbook or XSSFWorkbook
	 */
	Workbook workBook();
	
	void setExcelFile(InputStream input);

	void close();
}
