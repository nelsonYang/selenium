package com.freeborders.base.utils.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.PaginationUtils;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;

public class ViewUserHistoryUtils {
	/**
	 * iterate every page ,find the nearest, by deleted user Name, will not throw exception
	 * 1st column.
	 * @param webDriver
	 * @param deletedUserName
	 * @return null if doesn't exists
	 */
	public static WebElement findRecord(final WebDriver webDriver, final String deletedUserName) {
		SubMenuSelector.administrationSubMenu(AdministrationPage.VIEW_USER_HISTORY);
		webDriver.switchTo();
		WaitElementPresent.waitFindElement(webDriver, By.linkText("Time Deleted")).click();
		WebElement tr = null;
		PaginationUtils.Busi<WebElement> check = new PaginationUtils.Busi<WebElement>() {
			@Override
			public WebElement process() {
				// TODO Auto-generated method stub
				return TableElementFinder.findTrByTd(webDriver, 1, deletedUserName, By.id("UserTab"));
			}
		};
		PaginationUtils<WebElement> pagination = new PaginationUtils<WebElement>();
		pagination.setBusi(check);
		tr = pagination.iteratePage(webDriver);
		return tr;
	}
}
