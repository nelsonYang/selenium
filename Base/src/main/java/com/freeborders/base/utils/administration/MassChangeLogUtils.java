package com.freeborders.base.utils.administration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class MassChangeLogUtils {

	/**
	 * 
	 * @param webDriver
	 * @param imageEntity
	 * @param infos
	 * @return null if pass ,else unequal message 
	 */
	public static String verifyFirstLog(WebDriver webDriver, TestImageEntity imageEntity, List<String> infos) {
		String message = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.MASS_CHANGE_LOG);
		WaitTimeoutUtils.sleep(1);
		WebElement tr = WaitElementPresent.waitFindElement(webDriver, By.id("listTable")).findElement(
				By.xpath("./tbody/tr[2]"));
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		message = TableElementFinder.assertTr(tds, infos);
		ScreenCaptureUtils.captureByDriver(imageEntity);
		return message;
	}
}
