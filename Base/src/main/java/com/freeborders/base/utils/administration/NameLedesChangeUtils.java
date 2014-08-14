package com.freeborders.base.utils.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class NameLedesChangeUtils {

	public static String approveRateChange(WebDriver webDriver, String staffName, boolean approve,
			TestImageEntity imageEntity) {
		return approveRateChange(webDriver, staffName, null, approve, imageEntity);
	}

	public static String approveRateChange(WebDriver webDriver, String staffName, String dcName, boolean approve,
			TestImageEntity imageEntity) {
		SubMenuSelector.administrationSubMenu(AdministrationPage.APPROVE_NAME_LEDES_CHANGE);
		WaitTimeoutUtils.sleep(1);
		if (dcName != null && !dcName.isEmpty()) {
			Selector.select(webDriver, By.name("vendorId"), dcName);
			WaitElementPresent.waitFindElement(webDriver, By.name("Refresh")).click();
			WaitTimeoutUtils.sleep(1);
		}
		TableElementFinder.findTrByTd(webDriver, 2, staffName, By.id("approveRateChangeTab"))
				.findElement(By.id("cbxValue")).click();
		if (approve) {
			webDriver.findElement(By.name("Process")).click();
		} else {
			webDriver.findElement(By.id("Disapprove")).click();
			WindowSwitcher.confirmDialog(webDriver, true);
		}
		WaitTimeoutUtils.sleep(1);
		String message = WaitElementPresent.waitFindElement(webDriver, By.id("errlabel")).getText();
		ScreenCaptureUtils.captureByDriver(imageEntity);
		return message;
	}

}
