package com.freeborders.base.utils.excel;

import java.awt.Color;
import java.util.Map;


public interface WritableExcelCursor {

	/**
	 * InsertHead Map, Format as <columnName,index>.
	 * */
	void insertHead(Map<String,Integer> head);
	
	/**
	 * insert head into excel
	 * @param head <columnName,index>.
	 * @param row
	 * @param flag {true:clean,error}{flase:conflict}
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	void insertHead(Map head,int row,boolean flag);

	/**
	 * Insert to the last row of the excel file.Map format as <index,value>.
	 * */
	void insertToLast(Map map);

	/**
	 * flush the cache.
	 * */
	void flush();

	/**
	 * flush and close the oi stream.
	 * */
	void close();

	/**
	 * insert into the cell.
	 * 
	 * @param row
	 *            the row index
	 * @param column
	 *            the column index.
	 * @param value
	 *            the inserted value.
	 * @param colour
	 *            the cell font colour.
	 * */
	public void insertCell(int row, String column, String value, Color colour);

	/**
	 * get head Map.
	 * */
	public Map getHead();

	/**
	 * get column index value according head Map.
	 * */
	public int getIndex(String columnName);

	/**
	 * get current max row value.
	 * */
	public int getMaxRow();
	
	public void insertCell(int row, String column, String value, Color colour,boolean boldflag);
	
	public void insertCell(int row, int column, String value, Color colour,boolean boldflag);
}
