package com.freeborders.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.exception.NoDataException;
import com.freeborders.base.exception.TestException;

/**
 * 
 * @author nelson
 */
public class TableElementFinder {
	static Log log = LogFactory.getLog(TableElementFinder.class);

	public static final String SEARCHBYCSS = "searchByCSS";// for span with CSS name

	public static final String SEARCHBYALINK = "searchByALINK"; // for a link

	private TableElementFinder() {

	}

	/**
	 * assert tds of a tr, use specified strings
	 * 
	 * @param tds
	 * @param msgs
	 * @return null if passed else which cell is not equal
	 */
	public static String assertTr(List<WebElement> tds, List<String> msgs) {
		String message = null;
		for (int i = 0; i < tds.size(); i++) {
			String td = tds.get(i).getText();
			String msg = msgs.get(i);
			if (!td.equals(msg)) {
				return message = td + " doesn't equal " + msg;
			}
		}
		return message;
	}

	public static WebElement findFirstRequireElement(By tableBy, String xpath) throws NoDataException {
		WaitTimeoutUtils.sleep(1);
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		WebElement element = webDriver.findElement(tableBy);
		if (element != null) {
			WebElement elem = element.findElement(By.xpath(xpath));
			if (elem != null) {
				return elem;
			} else {
				throw new NoDataException("require element does not exist");
			}
		} else {
			throw new NoDataException("table element does not exist");
		}
	}

	/**
	 * 
	 * @param tableBy
	 * @param column
	 *            start from 1
	 * @param xpath
	 *            xpath for table
	 * @return
	 * @throws NoDataException
	 */
	public static WebElement findFirstRequireElementTR(By tableBy, int column, String xpath) throws NoDataException {
		WaitTimeoutUtils.sleep(1);
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		WebElement element = webDriver.findElement(tableBy);
		if (element != null) {
			if (xpath == null || xpath.isEmpty()) {
				xpath = "tbody/tr/td[" + column + "]/a[1]";
			}
			WebElement aElem = element.findElement(By.xpath(xpath));
			if (aElem != null) {
				WebElement trElment = aElem.findElement(By.xpath("../.."));
				return trElment;
			} else {
				throw new NoDataException("a element does not exist");
			}
		} else {
			throw new NoDataException("table element does not exist");
		}
	}

	public static WebElement findFirstRequireAElement(By tableBy, int column, String xpath) throws NoDataException {
		WaitTimeoutUtils.sleep(1);
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		WebElement element = webDriver.findElement(tableBy);
		if (element != null) {
			if (xpath == null || xpath.isEmpty()) {
				xpath = "tbody/tr/td[" + column + "]/a[1]";
			}
			// WaitElementPresent.waitElementByElement(webDriver, element, 5,
			// By.xpath(xpath));
			WebElement aElem = element.findElement(By.xpath(xpath));
			return aElem;
		} else {
			throw new NoDataException("table element does not exist");
		}
	}

	public static WebElement findFirstRequireElementTR(By tableBy, Map<String, String> textMap) {
		WaitTimeoutUtils.sleep(1);
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();

		WebElement element = webDriver.findElement(tableBy);
		if (element != null) {
			// find tr
			List<WebElement> elements = element.findElements(By.tagName("tr"));
			if (elements != null && !elements.isEmpty()) {
				boolean isExist = false;
				WebElement trElement = null;
				for (WebElement elem : elements) {
					if (!isExist) {
						List<WebElement> tdElements = elem.findElements(By.tagName("td"));
						for (WebElement tdElement : tdElements) {
							if (textMap.containsKey(tdElement.getText())) {
								isExist = true;
								trElement = elem;
								break;
							}
						}
					}
				}
				if (isExist) {
					return trElement;
				} else {
					throw new NoDataException("required tr element does not exist");
				}
			} else {
				throw new NoDataException("tr element does not exist");
			}
		} else {
			throw new NoDataException("table element does not exist");
		}
	}

	public static WebElement findFirstRequireElementTR(By tableBy, String text) {
		WaitTimeoutUtils.sleep(1);
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		WebElement element = webDriver.findElement(tableBy);
		if (element != null) {
			// find tr
			List<WebElement> elements = element.findElements(By.tagName("tr"));
			if (elements != null && !elements.isEmpty()) {
				boolean isExist = false;
				WebElement trElement = null;
				for (WebElement elem : elements) {
					if (!isExist) {
						List<WebElement> tdElements = elem.findElements(By.tagName("td"));
						for (WebElement tdElement : tdElements) {
							if (text.equals(tdElement.getText())) {
								isExist = true;
								trElement = elem;
								break;
							}
						}
					}
				}
				if (isExist) {
					return trElement;
				} else {
					throw new NoDataException("required tr element does not exist");
				}
			} else {
				throw new NoDataException("tr element does not exist");
			}
		} else {
			throw new NoDataException("table element does not exist");
		}

	}

	/**
	 * get check box in same row by partial text of a link
	 * 
	 * @param partialText
	 * @return
	 */
	public static WebElement findCbxByLink(String partialText) {
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		WebElement element = webDriver.findElement(By.partialLinkText(partialText)).findElement(By.xpath("../.."))
				.findElement(By.xpath("td[1]/input[@type='checkbox']"));
		return element;
	}

	/**
	 * find a column tds
	 * 
	 * @param table
	 * @param columnIndex
	 *            start 1
	 * @param startRowIndex
	 *            start 1 this row included
	 * @return td elements list
	 */
	public static List<WebElement> findColumnTds(WebDriver webDriver, By table, int columnIndex, int startRowIndex) {
		List<WebElement> tds = new ArrayList<WebElement>();
		WebElement eTable = WaitElementPresent.waitFindElement(webDriver, 4, table);
		List<WebElement> trs = eTable.findElements(By.tagName("tr"));
		for (int i = startRowIndex - 1; i < trs.size(); i++) {
			WebElement td = trs.get(i).findElements(By.tagName("td")).get(columnIndex - 1);
			tds.add(td);
		}
		return tds;
	}

	/**
	 * return td Strings
	 * 
	 * @param webDriver
	 * @param table
	 * @param columnIndex
	 * @param startRowIndex
	 *            this row included ,start from 1
	 * @return
	 */
	public static List<String> findColumnTdTxt(WebDriver webDriver, By table, int columnIndex, int startRowIndex) {
		List<String> tds = new ArrayList<String>();
		WebElement eTable = WaitElementPresent.waitFindElement(webDriver, 4, table);
		List<WebElement> trs = eTable.findElements(By.tagName("tr"));
		for (int i = startRowIndex - 1; i < trs.size(); i++) {
			WebElement td = trs.get(i).findElements(By.tagName("td")).get(columnIndex - 1);
			tds.add(td.getText());
		}
		return tds;
	}

	/**
	 * find a random number of specified cells
	 * 
	 * @param webDriver
	 * @param table
	 * @param columnIndex
	 * @param startRowIndex
	 * @param randomNum
	 * @return
	 */
	public static List<String> findColumnTdTxt(WebDriver webDriver, By table, int columnIndex, int startRowIndex,
			int randomNum) {
		Random rand = new Random();
		List<String> tds = new ArrayList<String>();
		WebElement eTable = WaitElementPresent.waitFindElement(webDriver, 4, table);
		List<WebElement> wtds = eTable.findElements(By.xpath(".//tr[" + startRowIndex + "]/*[" + columnIndex + "]"));
		for (int temp : Selector.getRandomIndexes(wtds.size(), randomNum, 0)) {
			tds.add(wtds.get(temp).getText());
		}
		return tds;
	}

	/**
	 * find basic element by name in table
	 * 
	 * @param name
	 * @param searchBy
	 *            : SEARCHBYCSS = "searchByCSS";//for span with CSS name SEARCHBYALINK =
	 *            "searchByALINK"; //for a link
	 * @param timeoutInSeconds
	 *            1 or 2 seconds
	 * @return the basic element in table,
	 * @author david.lee
	 */
	public static WebElement findElementByName(WebDriver webDriver, String name, String searchBy, long timeoutInSeconds) {

		WebElement elmentFind = null;

		elmentFind = findElementByName(webDriver, name, searchBy);

		if (elmentFind != null) {

			return elmentFind;

		} else {

			String[] total = getTotalNumbers(webDriver);

			if (total != null && Integer.parseInt(total[0]) < Integer.parseInt(total[1])) {

				int pages = (Integer.parseInt(total[1]) / Integer.parseInt(total[0]));

				for (int i = 1; i <= pages; i++) {

					List<WebElement> allGotoPages = webDriver.findElements(By.xpath("//span[@class='gotoPage']"));
					WebElement nextElement = null;
					for (WebElement gotoPage : allGotoPages) {
						if (">".equals(gotoPage.getText())) {
							nextElement = gotoPage;
							break;
						}
					}
					if(nextElement != null) {
					nextElement.click();
					
					WaitTimeoutUtils.sleep(timeoutInSeconds);

					elmentFind = findElementByName(webDriver, name, searchBy);
					}
					if (elmentFind == null) {
						continue;
					} else {
						break;
					}

				}
			}

			return elmentFind;
		}

	}

	private static WebElement findElementByName(WebDriver d, String name, String type) {

		WebElement elementFind = null;
		if (type.equals(SEARCHBYCSS)) {
			List<WebElement> agrs = d.findElements(By.className("linkedArea"));
			for (WebElement agreement : agrs) {
				if (agreement.getText().trim().equals(name)) {
					elementFind = agreement;
					break;
				}
			}
		} else if (type.equals(SEARCHBYALINK)) {
			List<WebElement> agrs = d.findElements(By.linkText(name));

			if (!agrs.isEmpty()) {
				elementFind = agrs.get(0);
			}
		}

		return elementFind;
	}

	private static String[] getTotalNumbers(WebDriver d) {

		try {
			WebElement totalNumber = d.findElement(By.xpath("//table[@class='contentbgbd']//tr//td[1]"));

			String totalNumberStr = totalNumber.getText();// totalNumberStr = Showing 1 - 30 of 154

			String perPageRows = (totalNumberStr.substring(totalNumberStr.indexOf("-") + 2,
					totalNumberStr.indexOf("of"))).trim();

			String total = totalNumberStr.substring(totalNumberStr.indexOf("of") + 3, totalNumberStr.length());

			return new String[] { perPageRows, total };

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * find the tr's td by td's index.
	 * 
	 * @param d
	 * @param element
	 *            : the TR element
	 * @param index
	 *            start from 1
	 * @return
	 */
	public static WebElement getTdByTr(WebElement tr, int index) {
		return tr.findElement(By.xpath("td[" + index + "]"));
	}

	/**
	 * find a tr by a td text
	 * 
	 * @param webDriver
	 * @param column
	 * @param tdText
	 * @param table
	 * @return null if not found
	 */
	public static WebElement findTrByTd(WebDriver webDriver, int column, String tdText, By table) {
		List<WebElement> trs = webDriver.findElement(table).findElements(By.tagName("tr"));
		WebElement returnTr = null;
		for (WebElement tr : trs) {
			String text = tr.findElements(By.xpath("./*")).get(column - 1).getText();
			if (text.equals(tdText)) {
				returnTr = tr;
				break;
			}
		}
		return returnTr;
	}

	public static List<WebElement> findTrsByTd(WebDriver webDriver, int column, String tdText, By table) {
		List<WebElement> trs = webDriver.findElement(table).findElements(By.tagName("tr"));
	    List<WebElement> returnTrs = new ArrayList<WebElement>();
	    for(WebElement tr : trs) {
	    	String text = tr.findElements(By.xpath("./*")).get(column - 1).getText();
			if (text.equals(tdText)) {
				returnTrs.add(tr);
			}
	    }
	    return returnTrs;
	}
	/**
	 * get Parent Element
	 * 
	 * @param element
	 *            the current element
	 * @param path
	 *            (eg."../..")
	 * @return Parent Element
	 */
	public static WebElement getParentElement(WebElement element, String path) {
		return element.findElement(By.xpath(path));

	}

	/**
	 * sort items by the given column name.
	 * 
	 * @param webDriver
	 * @param columnName
	 * @param tdIndex
	 *            (the index of "td" which the column name belongs to.start from 1)
	 */
	public static void sortByColumnName(WebDriver webDriver, String columnName, String tableId, int tdIndex,
			boolean ignoreCaps) {
		WebElement sortLink = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName));
		sortLink.click();
		WebElement sort = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName))
				.findElement(By.xpath("../..")).findElement(By.tagName("span"));
		List<WebElement> elements = webDriver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr/td["
				+ tdIndex + "]"));
		boolean sortResult = compareDataInList(elements, columnName, sort, ignoreCaps);
		if (sortResult == false) {
			throw new TestException("The " + columnName + " sorting function is not work correctly");
		}
	}

	/**
	 * verify the sort result
	 * 
	 * @param list
	 *            :the contents in tds.
	 * @param columnName
	 * @param sortValue
	 *            ,ascending order:5,descending order :6.
	 * @return
	 */
	private static boolean compareDataInList(List<WebElement> list, String columnName, WebElement sortValue,
			boolean ignoreCaps) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		boolean flag = true;
		// Random random = new Random();
		// int i = 0;
		// if (list.size() > 2) {
		// i = random.nextInt(list.size() - 2);
		// } else if (list.size() < 2) {
		// return true;
		// }
		// String dataPrevious = list.get(i).getText();
		// String dataNext = list.get(i + 1).getText();
		String dataPrevious = list.get(0).getText();
		if (ignoreCaps) {
			dataPrevious = dataPrevious.toUpperCase();
		}
		boolean isDateColumn = columnName.contains("Date");
		try {
			// if the sort order is ascending,the sortValue is 5;
			// descending order,sortValue is 6.
			if (sortValue.getText().equals("5")) {
				for (int i = 1; i < list.size(); i++) {
					// ascending data
					String dataNext = list.get(i).getText();
					log.info("5 previous  " + dataPrevious + " next " + dataNext);
					if (ignoreCaps) {
						dataNext = dataNext.toUpperCase();
					}
					if (!dataNext.equals("") && dataPrevious.equals("")) {
						return flag = false;
					}
					if (!isDateColumn && !dataPrevious.isEmpty() && !dataNext.isEmpty()) {
						if (dataNext.compareTo(dataPrevious) < 0) {
							return flag = false;
						}
					} else if (isDateColumn) {
						if ((!dataPrevious.equals("") && !dataNext.equals(""))
								&& (!dataPrevious.equals("--") && !dataNext.equals("--"))) {
							if (dateFormat.parse(dataPrevious).after(dateFormat.parse(dataNext))) {
								// compare the date
								return flag = false;
							}
						} else if ((dataPrevious.equals("") && !dataNext.equals(""))
								|| (!dataPrevious.equals("--") && dataNext.equals("--"))) {
							return flag = false;
						}

					}
					dataPrevious = dataNext;
				}
			} else {
				for (int i = 1; i < list.size(); i++) {
					// descending order
					String dataNext = list.get(i).getText();
					log.info("previous  " + dataPrevious + " next " + dataNext);
					if (ignoreCaps) {
						dataNext = dataNext.toUpperCase();
					}
					if (dataNext.isEmpty() && !dataPrevious.isEmpty()) {
						return flag = false;
					}
					if (!isDateColumn && !dataPrevious.isEmpty() && !dataNext.isEmpty()) {
						if (dataNext.compareTo(dataPrevious) > 0) {
							return flag = false;
						}
					} else if (isDateColumn) {
						if ((!dataPrevious.equals("") && !dataNext.equals(""))
								&& (!dataPrevious.equals("--") && !dataNext.equals("--"))) {
							if (dateFormat.parse(dataPrevious).before(dateFormat.parse(dataNext))) {
								// compare the date
								return flag = false;
							}
						} else if ((!dataPrevious.equals("") && dataNext.equals(""))
								|| (dataPrevious.equals("--") && !dataNext.equals("--"))) {
							// compare the date
							return flag = false;
						}
					}
					dataPrevious = dataNext;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 
	 * @param webDriver
	 * @param columnName
	 * @param tableId
	 * @param tdIndex
	 * @param max
	 *            custom max value
	 * @param min
	 *            custom min value
	 * @param ignoreCaps
	 */
	public static void sortByColumnName(WebDriver webDriver, String columnName, String tableId, int tdIndex,
			String max, String min, boolean ignoreCaps) {
		WebElement sortLink = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName));
		sortLink.click();
		WebElement sort = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName))
				.findElement(By.xpath("../..")).findElement(By.tagName("span"));
		List<WebElement> elements = webDriver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr/td["
				+ tdIndex + "]"));
		boolean sortResult = compareDataInList(elements, columnName, sort, max, min, ignoreCaps);
		if (sortResult == false) {
			throw new TestException("The " + columnName + " sorting function is not work correctly");
		}
	}

	/**
	 * verify the sort result
	 * 
	 * @param list
	 *            :the contents in tds.
	 * @param columnName
	 * @param max
	 * @param min
	 * @param sortValue
	 *            ,ascending order:5,descending order :6.
	 * @return
	 */
	private static boolean compareDataInList(List<WebElement> list, String columnName, WebElement sortValue,
			String max, String min, boolean ignoreCaps) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		boolean flag = true;
		String dataPrevious = list.get(0).getText();
		if (ignoreCaps) {
			dataPrevious = dataPrevious.toUpperCase();
			dataPrevious = changeMaxMin(max, min, dataPrevious);
		}
		boolean isDateColumn = columnName.contains("Date");
		try {
			// if the sort order is ascending,the sortValue is 5;
			// descending order,sortValue is 6.
			if (sortValue.getText().equals("5")) {
				for (int i = 1; i < list.size(); i++) {
					// ascending data
					String dataNext = list.get(i).getText();
					dataNext = changeMaxMin(max, min, dataNext);
					log.info("5 previous  " + dataPrevious + " next " + dataNext);
					if (ignoreCaps) {
						dataNext = dataNext.toUpperCase();
					}
					if (!dataNext.equals("") && dataPrevious.equals("")) {
						return flag = false;
					}
					if (!isDateColumn && !dataPrevious.isEmpty() && !dataNext.isEmpty()) {
						if (dataNext.compareTo(dataPrevious) < 0) {
							return flag = false;
						}
					} else if (isDateColumn) {
						if ((!dataPrevious.equals("") && !dataNext.equals(""))
								&& (!dataPrevious.equals("--") && !dataNext.equals("--"))) {
							if (dateFormat.parse(dataPrevious).after(dateFormat.parse(dataNext))) {
								// compare the date
								return flag = false;
							}
						} else if ((dataPrevious.equals("") && !dataNext.equals(""))
								|| (!dataPrevious.equals("--") && dataNext.equals("--"))) {
							return flag = false;
						}

					}
					dataPrevious = dataNext;
				}
			} else {
				for (int i = 1; i < list.size(); i++) {
					// descending order
					String dataNext = list.get(i).getText();
					dataNext = changeMaxMin(max, min, dataNext);
					log.info("previous  " + dataPrevious + " next " + dataNext);
					if (ignoreCaps) {
						dataNext = dataNext.toUpperCase();
					}
					if (dataNext.isEmpty() && !dataPrevious.isEmpty()) {
						return flag = false;
					}
					if (!isDateColumn && !dataPrevious.isEmpty() && !dataNext.isEmpty()) {
						if (dataNext.compareTo(dataPrevious) > 0) {
							return flag = false;
						}
					} else if (isDateColumn) {
						if ((!dataPrevious.equals("") && !dataNext.equals(""))
								&& (!dataPrevious.equals("--") && !dataNext.equals("--"))) {
							if (dateFormat.parse(dataPrevious).before(dateFormat.parse(dataNext))) {
								// compare the date
								return flag = false;
							}
						} else if ((!dataPrevious.equals("") && dataNext.equals(""))
								|| (dataPrevious.equals("--") && !dataNext.equals("--"))) {
							// compare the date
							return flag = false;
						}
					}
					dataPrevious = dataNext;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}

	private static String changeMaxMin(String max, String min, String changeStr) {
		String str = changeStr.trim();
		if (changeStr.equals(max.trim())) {
			str = "";
		} else if (changeStr.equals(min.trim())) {
			str = "--";
		}
		return str;
	}

	/**
	 * sort column by last name
	 * 
	 * @param webDriver
	 * @param columnName
	 * @param tableId
	 * @param tdIndex
	 * @param ignoreCaps
	 *            true if ignore capitalization
	 */
	public static void sortColumnLastName(WebDriver webDriver, String columnName, String tableId, int tdIndex,
			boolean ignoreCaps) {
		WebElement sortLink = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName));
		sortLink.click();
		WebElement sort = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName))
				.findElement(By.xpath("../..")).findElement(By.tagName("span"));
		List<WebElement> elements = webDriver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr/td["
				+ tdIndex + "]"));
		boolean sortResult = compareListLastName(elements, columnName, sort, ignoreCaps);
		if (sortResult == false) {
			throw new TestException("The " + columnName + " sorting function is not work correctly");
		}
	}

	private static boolean compareListLastName(List<WebElement> list, String columnName, WebElement sortValue,
			boolean ignoreCaps) {
		boolean flag = true;
		String[] array = list.get(0).getText().split(" ");
		String dataPrevious = array[array.length - 1];
		if (ignoreCaps) {
			dataPrevious = dataPrevious.toUpperCase();
		}
		// if the sort order is ascending,the sortValue is 5;
		// descending order,sortValue is 6.
		if (sortValue.getText().equals("5")) {
			for (int i = 1; i < list.size(); i++) {
				// ascending data
				array = list.get(i).getText().split(" ");
				String dataNext = array[array.length - 1];
				if (ignoreCaps) {
					dataNext = dataNext.toUpperCase();
				}
				log.info("ascending: previous  " + dataPrevious + " next " + dataNext);
				if (!dataNext.equals("") && dataPrevious.equals("")) {
					return flag = false;
				}
				if (!dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					if (dataNext.compareTo(dataPrevious) < 0) {
						return flag = false;
					}
				}
				dataPrevious = dataNext;
			}
		} else {
			for (int i = 1; i < list.size(); i++) {
				// descending order
				array = list.get(i).getText().split(" ");
				String dataNext = array[array.length - 1];
				if (ignoreCaps) {
					dataNext = dataNext.toUpperCase();
				}
				log.info("descend: previous  " + dataPrevious + " next " + dataNext);
				if (dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					return flag = false;
				}
				if (!dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					if (dataNext.compareTo(dataPrevious) > 0) {
						return flag = false;
					}
				}
				dataPrevious = dataNext;
			}
		}
		return flag;
	}

	public static void sortColumnNumeric(WebDriver webDriver, String columnName, String tableId, int tdIndex) {
		WebElement sortLink = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName));
		sortLink.click();
		WebElement sort = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName))
				.findElement(By.xpath("../..")).findElement(By.tagName("span"));
		List<WebElement> elements = webDriver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr/td["
				+ tdIndex + "]"));
		boolean sortResult = compareListNumeric(elements, columnName, sort);
		if (sortResult == false) {
			throw new TestException("The " + columnName + " sorting function is not work correctly");
		}
	}

	public static boolean compareListNumeric(List<WebElement> list, String columnName, WebElement sortValue) {
		boolean flag = true;
		// $192,100.32 or 50,
		String dataPrevious = list.get(0).getText().replace("--", "").replace(",", "").replace("$", "");
		float numPrevious = 0;
		if (!dataPrevious.isEmpty()) {
			numPrevious = Float.parseFloat(dataPrevious);
		}
		// if the sort order is ascending,the sortValue is 5;
		// descending order,sortValue is 6.
		if (sortValue.getText().equals("5")) {
			for (int i = 1; i < list.size(); i++) {
				// ascending data
				String dataNext = list.get(i).getText().replace("--", "").replace(",", "").replace("$", "");
				float numNext = 0;
				log.info("5 previous  " + dataPrevious + " next " + dataNext);
				if (!dataNext.isEmpty()) {
					numNext = Float.parseFloat(dataNext);
				}
				if (numNext < numPrevious) {
					return flag = false;
				}
				dataPrevious = dataNext;
				numPrevious = numNext;
			}
		} else {
			for (int i = 1; i < list.size(); i++) {
				// descending order
				String dataNext = list.get(i).getText().replace("--", "").replace(",", "").replace("$", "");
				float numNext = 0;
				log.info("previous  " + dataPrevious + " next " + dataNext);
				if (!dataNext.isEmpty()) {
					numNext = Float.parseFloat(dataNext);
				}
				if (numNext > numPrevious) {
					return flag = false;
				}
				dataPrevious = dataNext;
			}
		}

		return flag;
	}

	public static void sortColumnTime(WebDriver webDriver, String columnName, String tableId, int tdIndex) {
		WebElement sortLink = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName));
		sortLink.click();
		WebElement sort = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName))
				.findElement(By.xpath("../..")).findElement(By.tagName("span"));
		List<WebElement> elements = webDriver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr/td["
				+ tdIndex + "]"));
		boolean sortResult = compareListOfTime(elements, columnName, sort, false);
		if (sortResult == false) {
			throw new TestException("The " + columnName + " sorting function is not work correctly");
		}
	}

	public static void sortColumn24Time(WebDriver webDriver, String columnName, String tableId, int tdIndex) {
		WebElement sortLink = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName));
		sortLink.click();
		WebElement sort = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName))
				.findElement(By.xpath("../..")).findElement(By.tagName("span"));
		List<WebElement> elements = webDriver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr/td["
				+ tdIndex + "]"));
		boolean sortResult = compareListOfTime(elements, columnName, sort, true);
		if (sortResult == false) {
			throw new TestException("The " + columnName + " sorting function is not work correctly");
		}
	}

	public static boolean compareListOfTime(List<WebElement> list, String columnName, WebElement sortValue, boolean h24) {
		boolean flag = true;

		String fot = null;
		if (h24) {
			fot = "MM/dd/yyyy hh:mm:ss a";
		} else {
			fot = "MM/dd/yyyy HH:mm:ss";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(fot);
		String dataPrevious = list.get(0).getText().trim();
		if ("5".equals(sortValue.getText())) {
			for (int i = 1; i < list.size(); i++) {
				String dataNext = list.get(i).getText().trim();
				log.info("ascending previous  " + dataPrevious + " next " + dataNext);
				if (!dataNext.isEmpty() && dataPrevious.isEmpty()) {
					return flag = false;
				}
				if (!dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					try {
						if (dateFormat.parse(dataNext).before(dateFormat.parse(dataPrevious))) {
							return flag = false;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				dataPrevious = dataNext;
			}

		} else {
			for (int i = 1; i < list.size(); i++) {
				String dataNext = list.get(i).getText().trim();
				log.info("descending previous  " + dataPrevious + " next " + dataNext);
				if (dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					return flag = false;
				}
				if (!dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					try {
						if (dateFormat.parse(dataNext).after(dateFormat.parse(dataPrevious))) {
							return flag = false;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				dataPrevious = dataNext;
			}
		}
		return flag;
	}
	/**
	 * sort the date column whose format is MMMM dd, yyyy for example:April 29, 2014
	 */
	public static void sortColumnByLongMonthDate(WebDriver webDriver, String columnName, String tableId, int tdIndex) {
		WebElement sortLink = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName));
		sortLink.click();
		WebElement sort = WaitElementPresent.waitFindElement(webDriver, 2, By.linkText(columnName))
				.findElement(By.xpath("../..")).findElement(By.tagName("span"));
		List<WebElement> elements = webDriver.findElements(By.xpath("//table[@id='" + tableId + "']/tbody/tr/td["
				+ tdIndex + "]"));
		boolean sortResult = compareListOfLongMonthDate(elements, columnName, sort);
		if (sortResult == false) {
			throw new TestException("The " + columnName + " sorting function is not work correctly");
		}
	}
    public static boolean compareListOfLongMonthDate(List<WebElement> list, String columnName, WebElement sortValue) {
    	boolean flag = true;
    	SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
    	String dataPrevious = list.get(0).getText().trim();
		if ("5".equals(sortValue.getText())) {
			for (int i = 1; i < list.size(); i++) {
				String dataNext = list.get(i).getText().trim();
				log.info("ascending previous  " + dataPrevious + " next " + dataNext);
				if (!dataNext.isEmpty() && dataPrevious.isEmpty()) {
					return flag = false;
				}
				if (!dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					try {
						if (sdf.parse(dataNext).before(sdf.parse(dataPrevious))) {
							return flag = false;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				dataPrevious = dataNext;
			}

		} else {
			for (int i = 1; i < list.size(); i++) {
				String dataNext = list.get(i).getText().trim();
				log.info("descending previous  " + dataPrevious + " next " + dataNext);
				if (dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					return flag = false;
				}
				if (!dataNext.isEmpty() && !dataPrevious.isEmpty()) {
					try {
						if (sdf.parse(dataNext).after(sdf.parse(dataPrevious))) {
							return flag = false;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				dataPrevious = dataNext;
			}
		}
    	return flag;
    }
    
    
	public static List<WebElement> findAllMessages(WebDriver webDriver, long timeOut, By by) {
		List<WebElement> elements = null;
		List<WebElement> pageElements = WaitElementPresent.waitFindElements(webDriver, timeOut,
				By.className("gotoPage"));
		// only one page, search elements directly.
		if (pageElements.isEmpty()) {
			elements = webDriver.findElements(by);
		} else {
			// search element in the first page
			elements = webDriver.findElements(by);
			// turning page then search elements in the other pages.
			int turnPageNum = pageElements.size() - 2;
			for (int i = 0; i < turnPageNum; i++) {
				pageElements.get(i).click();
				elements.addAll(webDriver.findElements(by));
			}
		}
		return elements;
	}

	/**
	 * 0. start 1.end 2.total
	 * 
	 * @param webDriver
	 * @return
	 */
	public static List<Integer> getSumInfo(WebDriver webDriver) {
		String[] pageInfo = null;
		try {
			pageInfo = WaitElementPresent.waitFindElement(webDriver, By.className("contentbgbd"))
					.findElement(By.tagName("div")).getText().trim().split("\\s+");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Arrays.asList(new Integer[] { 0, 0, 0 });
		}
		List<Integer> info = new ArrayList<Integer>();
		info.add(Integer.parseInt(pageInfo[1]));
		info.add(Integer.parseInt(pageInfo[3]));
		info.add(Integer.parseInt(pageInfo[5]));
		return info;
	}

	public static void selectGridBoxes(WebDriver webDriver, Set<String> values, int columnNum, By table) {
		WaitElementPresent.waitFindElement(webDriver, table);
		JavascriptExecutor js = null;
		if (webDriver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) webDriver;
			js.executeScript("checkAll('selectedFunctionId', false);");
		}
		;
		List<WebElement> tds = WaitElementPresent.waitFindElementsByElement(webDriver,
				WaitElementPresent.waitFindElement(webDriver, table), By.xpath("./tbody/tr/td[" + columnNum + "]"));
		for (WebElement td : tds) {
			String tdText = td.getText();
			if (values.remove(tdText)) {
				td.click();
				WebElement checkBox = td.findElement(By.xpath(".."))
						.findElement(By.xpath(".//input[@type='checkbox']"));
				log.info(checkBox.getAttribute("value"));
				checkBox.click();
			}
			if (values.isEmpty()) {
				break;
			}
		}
	}

	public static void checkShownUnderFreezeMode(WebDriver webDriver) {
		// check New button
		WebElement newBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='New']"), 5);
		if (newBtn != null && !newBtn.getAttribute("style").contains("none")) {
			throw new TestException("New button should not display under the Freeze Data Mode.");
		}
		// check Delete Button
		WebElement deleteBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='Delete']"), 5);
		if (deleteBtn != null && !deleteBtn.getAttribute("style").contains("none")) {
			throw new TestException("Delete button should not display under the Freeze Data Mode.");
		}
		// check Save Button
		WebElement saveBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='Save']"), 5);
		if (saveBtn != null && !saveBtn.getAttribute("style").contains("none")) {
			throw new TestException("Save button should not display under the Freeze Data Mode.");
		}
		// check the text is read only or not
		List<WebElement> textList = webDriver.findElements(By.xpath("//input[@type='text']"));
		if (textList.size() != 0) {
			throw new TestException("The input box should be read only under freeze data mode");
		}
		// check the checkbox under freeze data mode
		List<WebElement> cbxList = webDriver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement cbx : cbxList) {
			// String cbxLabel =
			// cbx.findElement(By.xpath("../..")).findElement(By.xpath("td[1]")).getText();
			// cbxLabel = cbxLabel.substring(0,cbxLabel.indexOf(":"));
			if (cbx.isEnabled()) {
				throw new TestException("The checkbox should be disabled under freeze database mode.");
			}
		}

		// check radio button under freeze data mode
		List<WebElement> radioBtns = webDriver.findElements(By.xpath("//input[@type='radio']"));
		for (WebElement radioBtn : radioBtns) {
			if (radioBtn.isEnabled()) {
				throw new TestException("The Radio button should be disabled under freeze database mode.");
			}
		}
	}
	
	public static WebElement turnPageFindTrByTd(WebDriver webDriver, int column, String tdText, By table,
			long timeoutInSeconds) {
		WebElement tr = null;
		tr = findTrByTd(webDriver, column, tdText, table);
		if (tr != null) {
			return tr;
		} else {

			String[] total = getTotalNumbers(webDriver);

			if (total != null && Integer.parseInt(total[0]) < Integer.parseInt(total[1])) {

				int pages = (Integer.parseInt(total[1]) / Integer.parseInt(total[0]));

				for (int i = 1; i <= pages; i++) {

					List<WebElement> allGotoPages = webDriver.findElements(By.xpath("//span[@class='gotoPage']"));
					WebElement nextElement = null;
					for (WebElement gotoPage : allGotoPages) {
						if (">".equals(gotoPage.getText())) {
							nextElement = gotoPage;
							break;
						}
					}
					if (nextElement != null) {
						nextElement.click();

						WaitTimeoutUtils.sleep(timeoutInSeconds);

						tr = findTrByTd(webDriver, column, tdText, table);
					}
					if (tr == null) {
						continue;
					} else {
						break;
					}

				}
			}

			return tr;
		}
	}
}

