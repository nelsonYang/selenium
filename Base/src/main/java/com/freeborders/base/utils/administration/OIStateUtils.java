package com.freeborders.base.utils.administration;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.exception.TestException;
import com.freeborders.base.utils.PaginationUtils;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class OIStateUtils {
	
	public static List<String> getOiStateTds(WebDriver webDriver, int column,TestImageEntity imageEntity) {
		List<String> tds = new ArrayList<String>();
		SubMenuSelector.administrationSubMenu(AdministrationPage.OI_STATE);
		//just for wait 
		WaitElementPresent.waitFindElement(webDriver, 4, By.id("PaTab"));
		tds.addAll(TableElementFinder.findColumnTdTxt(webDriver, By.id("PaTab"), column, 2));
		List<WebElement> pages = WaitElementPresent.waitFindElements(webDriver, 3, By.className("gotoPage"));
		// all page jump times
		int pps = pages.size() - 2;
		for (int i = 0; i < pps; i++) {
			WebElement nextPage = webDriver.findElement(By.className("contentbgbd")).findElements(By.tagName("span"))
					.get(i + 2);
			nextPage.click();
			// wait 
			WaitElementPresent.waitFindElement(webDriver, 4, By.id("PaTab"));
			tds.addAll(TableElementFinder.findColumnTdTxt(webDriver, By.id("PaTab"), column, 2));
		}
		return tds;
	}
	
	
	/**
	 * find oiState by Oi state name
	 * 
	 * @param webDriver
	 * @return
	 */
	public static WebElement findOiState(final WebDriver webDriver, final String stateName, TestImageEntity imageEntity) {
		SubMenuSelector.administrationSubMenu(AdministrationPage.OI_STATE);
		WaitTimeoutUtils.sleep(1);
		WebElement link = null;
		PaginationUtils.Busi<WebElement> busi = new PaginationUtils.Busi<WebElement>() {

			@Override
			public WebElement process() {
				// TODO Auto-generated method stub
				WebElement ele = null;
				ele = WaitElementPresent.isExist(webDriver, By.linkText(stateName),WaitElementPresent.WAIT_TIME_SHORT);
				return ele;
			}
		};
		PaginationUtils<WebElement> utls = new PaginationUtils<WebElement>();
		utls.setBusi(busi);
		link = utls.iteratePage(webDriver);
		return link;
	}
	
	public static String deleteOiState(WebDriver webDriver, String oiStateName, TestImageEntity imageEntity) {
		String message = null;
		String mainWindow = null;
		String locationWindow = null;
		
		SubMenuSelector.administrationSubMenu(AdministrationPage.OI_STATE);
		WebElement nameLink = TableElementFinder.findElementByName(webDriver, oiStateName, TableElementFinder.SEARCHBYALINK, 5);
		if(nameLink != null) {
			mainWindow = webDriver.getWindowHandle();
			WebElement cbx = TableElementFinder.findCbxByLink(oiStateName);
			cbx.click();
			webDriver.findElement(By.name("Delete")).click();
			WindowSwitcher.confirmDialog(webDriver, true);
			webDriver.switchTo().window(mainWindow);
			WebElement errorFile = WaitElementPresent.isExist(webDriver, By.partialLinkText("OI_State_DeleteFailures_"), 3);
			if(errorFile != null){
				ScreenCaptureUtils.captureByDriver(imageEntity);
				errorFile.click();
				mainWindow = webDriver.getWindowHandle();
				locationWindow = WindowSwitcher.switchToWindow();
				WaitElementPresent.waitElement(webDriver, 3, By.tagName("pre"));
				ScreenCaptureUtils.captureByDriver(imageEntity);
				WindowSwitcher.checkAndCloseWindow(webDriver, locationWindow);
				webDriver.switchTo().window(mainWindow);
			}else {
				message = WaitElementPresent.getErrorMsg(webDriver);
			}
		}else {
			throw new TestException("Not find OiState name:" + oiStateName);
		}
		return message;
	}
}
