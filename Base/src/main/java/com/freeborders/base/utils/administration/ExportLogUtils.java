package com.freeborders.base.utils.administration;

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

public class ExportLogUtils {
	/**
	 *  assert a recent log is correct or not
	 * @param webDriver
	 * @param trInfo
	 * @param imageEntity
	 * @return
	 */
	public static String assertRecentLog(WebDriver webDriver, List<String> trInfo,TestImageEntity imageEntity) {
		String message = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.EXPORT_LOG);
		WaitTimeoutUtils.sleep(1);
		WaitElementPresent.waitFindElement(webDriver, By.linkText("Date")).click();
		List<WebElement> tds = WaitElementPresent.waitFindElement(webDriver, By.id("ExportTab")).findElements(
				By.xpath("./tbody/tr[2]/td"));
		message=TableElementFinder.assertTr(tds, trInfo);
		ScreenCaptureUtils.captureByDriver(imageEntity);
		return message;
	}
}
