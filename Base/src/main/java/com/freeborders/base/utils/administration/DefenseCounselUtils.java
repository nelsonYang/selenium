package com.freeborders.base.utils.administration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.DefenseCounselData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.CalendarSelector;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class DefenseCounselUtils {

	public static String createDC(WebDriver webDriver, DefenseCounselData data) {
		String message = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.DEFENSE_COUNSEL);
		WaitElementPresent.waitFindElement(webDriver, 4, By.name("New")).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		if (data.getName() != null) {
			WaitElementPresent.waitFindElement(webDriver, 5, By.name("data.name")).sendKeys(data.getName());
		}
		if (data.getShortName() != null) {
			webDriver.findElement(By.name("data.shortName")).sendKeys(data.getShortName());
		}
		if (data.getAddress1() != null) {
			webDriver.findElement(By.name("data.address1")).sendKeys(data.getAddress1());
		}
		if (data.getAddress2() != null) {
			webDriver.findElement(By.name("data.address2")).sendKeys(data.getAddress2());
		}
		if (data.getCity() != null) {
			webDriver.findElement(By.name("data.city")).sendKeys(data.getCity());
		}
		if (data.getStateName() != null) {
			Selector.select(webDriver, By.name("data.stateId"), data.getStateName());
		}
		if (data.getZipCode() != null) {
			webDriver.findElement(By.name("data.zipCode")).sendKeys(data.getZipCode());
		}
		if (data.getPhone1() != null) {
			webDriver.findElement(By.name("data.phone1")).sendKeys(data.getPhone1());
		}
		if (data.getPhone2() != null) {
			webDriver.findElement(By.name("data.phone2")).sendKeys(data.getPhone2());
		}
		if (data.getPhone3() != null) {
			webDriver.findElement(By.name("data.phone3")).sendKeys(data.getPhone3());
		}
		if (data.getFax1() != null) {
			webDriver.findElement(By.name("data.fax1")).sendKeys(data.getFax1());
		}
		if (data.getFax2() != null) {
			webDriver.findElement(By.name("data.fax2")).sendKeys(data.getFax2());
		}
		if (data.getFax3() != null) {
			webDriver.findElement(By.name("data.fax3")).sendKeys(data.getFax3());
		}
		if (!data.getjMUsers().isEmpty()) {
			for (String string : data.getjMUsers()) {
				Selector.select(webDriver, By.name("allJMUserId"), string);
				webDriver.findElements(By.name("Add2")).get(0).click();
			}
		}
		if (data.getAccountsPayableId() != null) {
			webDriver.findElement(By.name("data.accountsPayableId")).sendKeys(data.getAccountsPayableId());
		}
		if (data.getLedesId() != null) {
			webDriver.findElement(By.name("data.ledesId")).sendKeys(data.getLedesId());
		}
		if (data.isActive()) {
			webDriver.findElement(By.name("data.active")).click();
		}
		if (data.isProcessAsVendor()) {
			webDriver.findElement(By.name("data.processAsVendor")).click();
		}
		if (data.getFixedPaymentStartDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getFixedPaymentStartDate(),
					By.name("data.fixedPaymentStartDate"));
		}
		if (data.getFixedPaymentEndDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getFixedPaymentEndDate(),
					By.name("data.fixedPaymentEndDate"));
		}
		if (data.getMonthlyAmount() != null) {
			webDriver.findElement(By.name("data.monthlyAmount")).sendKeys(data.getMonthlyAmount());
		}
		if (!data.getStateworks().isEmpty()) {
			for (String string : data.getStateworks()) {
				Selector.select(webDriver, By.name("oistatesDD"), string);
				webDriver.findElements(By.name("Add2")).get(1).click();
			}
		}
		webDriver.findElement(By.name("Save")).click();
		if (WindowSwitcher.isExists(webDriver, locationWindow)) {
			message = WaitElementPresent.waitFindElement(webDriver, 3, By.id("errlabel")).getText();
		} else {
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}

	/**
	 * return the link of given DC name, return a empty List if not found
	 * 
	 * @param name
	 * @return
	 */
	public static List<WebElement> findDCsByName(WebDriver webDriver, String name) {
		List<WebElement> links = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.DEFENSE_COUNSEL);
		WaitElementPresent.waitFindElement(webDriver, 3, By.name("findDCs")).sendKeys(name);
		webDriver.findElement(By.name("popButton")).click();
		links = WaitElementPresent.waitFindElements(webDriver, 4, By.className("linkedArea"));
		return links;
	}

	/**
	 * check if the user is Billing authorizers or not
	 * 
	 * @param webDriver
	 * @param users
	 * @param DCIds
	 * @param imageEntity
	 * @return error message
	 */
	public static String checkAuthorizerByDCId(WebDriver webDriver, String userName, Set<String> DCIds,
			TestImageEntity imageEntity) {
		String message = null;
		// find dc
		SubMenuSelector.administrationSubMenu(AdministrationPage.DEFENSE_COUNSEL);
		// iterate page
		int pageTimes = WaitElementPresent.waitFindElements(webDriver, 3, By.className("gotoPage")).size() - 2 + 1;
		for (int i = 0; i < pageTimes; i++) {
			WebElement nextPage = WaitElementPresent.waitFindElement(webDriver, 4, By.className("contentbgbd"))
					.findElements(By.tagName("span")).get(i + 2);
			List<WebElement> spans = WaitElementPresent.waitFindElements(webDriver, 3, By.className("linkedArea"));
			for (WebElement temp : spans) {
				String onclick = temp.getAttribute("onclick");
				String vid = onclick.substring(onclick.indexOf("(") + 1, onclick.indexOf(")"));
				if (DCIds.isEmpty()) {
					return message;
				}
				if (DCIds.remove(vid)) {
					temp.click();
					String mainWindow = webDriver.getWindowHandle();
					String locationWindow = WindowSwitcher.switchToWindow();
					List<WebElement> jmOptions = new Select(WaitElementPresent.waitFindElement(webDriver, 3,
							By.id("JMUserId"))).getOptions();
					boolean flag = false;
					for (WebElement option : jmOptions) {
						if (option.getText().equals(userName)) {
							flag = true;
							break;
						}
					}
					String dcName = webDriver.findElement(By.name("data.name")).getAttribute("value");
					if (!flag)
						return " current user " + userName + " is not an billing authorizer of dc " + dcName;
					ScreenCaptureUtils.captureByDriver(imageEntity);
					WindowSwitcher.jsCloseWindow(webDriver);
					webDriver.switchTo().window(mainWindow);
				}
			}
			nextPage.click();
		}
		return message;
	}

	/**
	 * check if this user is billing authorizers or not
	 * 
	 * @param webDriver
	 * @param userName
	 * @param DCNames
	 * @param imageEntity
	 * @return
	 */
	public static String checkAuthorizerByDCNames(WebDriver webDriver, String userName, Set<String> DCNames,
			TestImageEntity imageEntity) {
		String message = null;
		List<WebElement> links = null;
		// find dc
		SubMenuSelector.administrationSubMenu(AdministrationPage.DEFENSE_COUNSEL);
		for (String name : DCNames) {
			WaitElementPresent.waitFindElement(webDriver, 3, By.name("findDCs")).clear();
			WaitElementPresent.waitFindElement(webDriver, 3, By.name("findDCs")).sendKeys(name);
			webDriver.findElement(By.name("popButton")).click();
			links = WaitElementPresent.waitFindElements(webDriver, 4, By.className("linkedArea"));
			links.get(0).click();
			String mainWindow = webDriver.getWindowHandle();
			String locationWindow = WindowSwitcher.switchToWindow();
			List<WebElement> jmOptions = new Select(WaitElementPresent.waitFindElement(webDriver, 3, By.id("JMUserId")))
					.getOptions();
			boolean flag = false;
			for (WebElement option : jmOptions) {
				if (option.getText().equals(userName)) {
					flag = true;
					break;
				}
			}
			if (!flag)
				return " current user " + userName + " is not an billing authorizer of dc " + name;
			ScreenCaptureUtils.captureByDriver(imageEntity);
			WindowSwitcher.jsCloseWindow(webDriver);
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}

	/**
	 * add an authorizer 
	 * @param webDriver
	 * @param userName
	 * @param dcName
	 * @param imageEntity
	 * @return
	 */
	public static String addAuthorizer(WebDriver webDriver, String userName, String dcName, TestImageEntity imageEntity) {
		String message = null;
		List<WebElement> links = null;
		// find dc
		SubMenuSelector.administrationSubMenu(AdministrationPage.DEFENSE_COUNSEL);
		WaitTimeoutUtils.sleep(1);
		WaitElementPresent.waitFindElement(webDriver, 3, By.name("findDCs")).clear();
		WaitElementPresent.waitFindElement(webDriver, 3, By.name("findDCs")).sendKeys(dcName);
		webDriver.findElement(By.name("popButton")).click();
		links = WaitElementPresent.waitFindElements(webDriver, 4, By.className("linkedArea"));
		links.get(0).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		WaitElementPresent.waitElement(webDriver, 3, By.name("allJMUserId"));
		Selector.select(webDriver, By.name("allJMUserId"), userName);
		webDriver.findElements(By.name("Add2")).get(0).click();
		ScreenCaptureUtils.captureByDriver(imageEntity);
		webDriver.findElement(By.name("Save")).click();
		if (WindowSwitcher.isExists(webDriver, locationWindow)) {
			message = WaitElementPresent.waitFindElement(webDriver, 3, By.id("errlabel")).getText();
		} else {
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}

	/**
	 * get the total number of all DCs 
	 * @param webDriver
	 * @param imageEntity
	 * @return
	 */
	public static int getDcNum(WebDriver webDriver, TestImageEntity imageEntity) {
		int num = 0;
		SubMenuSelector.administrationSubMenu(AdministrationPage.DEFENSE_COUNSEL);
		String[] strNum = WaitElementPresent.waitFindElement(webDriver, By.className("contentbgbd"))
				.findElement(By.tagName("div")).getText().split(" ");
		num = Integer.parseInt(strNum[strNum.length - 1]);
		return num;
	}
	
	/**
	 * get all dcs in the table
	 * @param webDriver
	 * @param column
	 * @param imageEntity
	 * @return
	 */
	public static List<String> getDCsTds(WebDriver webDriver, int column,TestImageEntity imageEntity) {
		List<String> tds = new ArrayList<String>();
		SubMenuSelector.administrationSubMenu(AdministrationPage.DEFENSE_COUNSEL);
		//just for wait 
		WaitElementPresent.waitFindElement(webDriver, 4, By.id("listTable"));
		tds.addAll(TableElementFinder.findColumnTdTxt(webDriver, By.id("listTable"), column, 2));
		List<WebElement> pages = WaitElementPresent.waitFindElements(webDriver, 3, By.className("gotoPage"));
		// all page jump times
		int pps = pages.size() - 2;
		for (int i = 0; i < pps; i++) {
			ScreenCaptureUtils.captureByDriver(imageEntity);
			WebElement nextPage = webDriver.findElement(By.className("contentbgbd")).findElements(By.tagName("span"))
					.get(i + 2);
			nextPage.click();
			// wait 
			WaitElementPresent.waitFindElement(webDriver, 4, By.id("listTable"));
			tds.addAll(TableElementFinder.findColumnTdTxt(webDriver, By.id("listTable"), column, 2));
		}
		return tds;
	}
}
