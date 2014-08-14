package com.freeborders.base.utils.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class CaseExportUtils {
	
	public static void deleteExport(WebDriver webDriver, String exportName) {
		WebElement link=findExport(webDriver, exportName);
		TableElementFinder.findCbxByLink(exportName).click();
		WaitElementPresent.waitFindElement(webDriver, By.id("Delete")).click();
		WindowSwitcher.confirmDialog(webDriver, true);
		webDriver.switchTo();
		WaitTimeoutUtils.sleep(1);
	}

	/**
	 * return null if not be found
	 * @param webDriver
	 * @param exportName
	 * @return
	 */
	public static WebElement findExport(WebDriver webDriver, String exportName) {
		SubMenuSelector.administrationSubMenu(AdministrationPage.CASE_EXPORTS);
		WaitTimeoutUtils.sleep(1);
		WebElement link = null;
		link=TableElementFinder.findElementByName(webDriver, exportName, TableElementFinder.SEARCHBYALINK, 3);
		return link;
	}
}
