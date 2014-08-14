package com.freeborders.base.utils.administration;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class LookupTablesUtils {
	/**
	 * get the contents in the table of selected item.
	 * 
	 * @param webDriver
	 * @param linkText
	 *            (the link name in the Lookup Tables page)
	 * @param column
	 * @param imageEntity
	 * @return
	 */
	public static List<String> getSelectedItemTds(WebDriver webDriver, String linkText, int column,
			TestImageEntity imageEntity) {
		List<String> tds = new ArrayList<String>();
		SubMenuSelector.administrationSubMenu(AdministrationPage.LOOKUP_TABLES);
		WaitElementPresent.waitFindElement(webDriver, By.linkText(linkText)).click();
		// use table to determine screen finished successfully or not
		WaitElementPresent.waitFindElement(webDriver, By.id("FieldsListTable"));
		tds.addAll(TableElementFinder.findColumnTdTxt(webDriver, By.id("FieldsListTable"), column, 1));
		List<WebElement> pages = WaitElementPresent.waitFindElements(webDriver, 3, By.className("gotoPage"));
		ScreenCaptureUtils.captureByDriver(imageEntity);
		// pps is the click times for all page jump
		int pps = pages.size() - 2;
		// iterate all pages
		for (int i = 0; i < pps; i++) {
			WebElement nextPage = webDriver.findElement(By.className("contentbgbd")).findElements(By.tagName("span"))
					.get(i + 2);
			nextPage.click();
			WaitElementPresent.waitFindElement(webDriver, 4, By.id("FieldsListTable"));
			tds.addAll(TableElementFinder.findColumnTdTxt(webDriver, By.id("FieldsListTable"), column, 1));
		}
		return tds;
	}

	public static WebElement findItemLink(WebDriver webDriver, String lookupTable, String itemName) {
		WebElement link = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.LOOKUP_TABLES);
		WaitElementPresent.waitFindElement(webDriver, 3, By.linkText(lookupTable)).click();
		webDriver.switchTo();
		WaitTimeoutUtils.sleep(1);
		link = WaitElementPresent.waitFindElement(webDriver, By.linkText(itemName));
		return link;
	}

	/**
	 * screen capture if fail deleting
	 * 
	 * @param webDriver
	 * @param lookupTable
	 * @param itemName
	 * @param imageEntity
	 * @return
	 */
	public static String deleteItem(WebDriver webDriver, String lookupTable, String itemName,
			TestImageEntity imageEntity) {
		WebElement link = findItemLink(webDriver, lookupTable, itemName);
		link.findElement(By.xpath("../../td[1]/input")).click();
		WaitElementPresent.waitFindElement(webDriver, By.name("Delete")).click();
		WindowSwitcher.confirmDialog(webDriver, true);
		WaitTimeoutUtils.sleep(1);
		String message = WaitElementPresent.getErrorMsg(webDriver);
		if (message != null && !message.isEmpty())
			ScreenCaptureUtils.captureByDriver(imageEntity);
		return message;
	}
}
