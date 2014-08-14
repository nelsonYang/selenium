package com.freeborders.base.utils.administration;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.StaffData;
import com.freeborders.base.exception.TestException;
import com.freeborders.base.utils.CalendarSelector;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class StaffUtils {
	public static String newStaff(WebDriver webDriver, StaffData staffData, TestImageEntity testImageEntity) {
		String message = null;
		if (staffData.getVendorName() != null) {
			List<WebElement> dcList = DefenseCounselUtils.findDCsByName(webDriver, staffData.getVendorName());
			if (!dcList.isEmpty()) {
				dcList.get(0).findElement(By.xpath("../..")).findElement(By.xpath("td[6]/a")).click();
				WaitTimeoutUtils.sleep(2);
				webDriver.findElement(By.xpath("//input[@name='New']")).click();
				String mainWindow = webDriver.getWindowHandle();
				String locationWindow = WindowSwitcher.switchToWindow();

				if (staffData.getProposedFirstName() != null) {
					WebElement proposedFirstName = webDriver.findElement(By.id("proposedFirstName"));
					proposedFirstName.sendKeys(staffData.getProposedFirstName());
				}

				if (staffData.getProposedLastName() != null) {
					WebElement proposedLastName = webDriver.findElement(By.id("proposedLastName"));
					proposedLastName.sendKeys(staffData.getProposedLastName());
				}
				if (staffData.getProposedLEDESName() != null) {
					WebElement proposedLedesName = webDriver.findElement(By.id("proposedLEDESName"));
					proposedLedesName.sendKeys(staffData.getProposedLEDESName());
				}

				if (staffData.getStaffTitleName() != null) {
					Selector.select(webDriver, By.id("staffTitleId"), staffData.getStaffTitleName());
				}

				if (staffData.getPhone1() != null) {
					WebElement phone1 = webDriver.findElement(By.id("phone1"));
					phone1.sendKeys(staffData.getPhone1());
				}
				if (staffData.getPhone2() != null) {
					WebElement phone2 = webDriver.findElement(By.id("phone2"));
					phone2.sendKeys(staffData.getPhone2());
				}
				if (staffData.getPhone3() != null) {
					WebElement phone3 = webDriver.findElement(By.id("phone3"));
					phone3.sendKeys(staffData.getPhone3());
				}
				if (staffData.getFax1() != null) {
					webDriver.findElement(By.id("fax1")).sendKeys(staffData.getFax1());
				}
				if (staffData.getFax2() != null) {
					webDriver.findElement(By.id("fax2")).sendKeys(staffData.getFax2());
				}
				if (staffData.getFax3() != null) {
					webDriver.findElement(By.id("fax3")).sendKeys(staffData.getFax3());
				}
				if (staffData.getEmail() != null) {
					webDriver.findElement(By.id("email")).sendKeys(staffData.getEmail());
				}
				WebElement authorizedFor9XX = webDriver.findElement(By.name("staffData.authorizedFor9XX"));
				if (!authorizedFor9XX.isSelected()) {
					if (staffData.isAuthorizedFor9XX()) {
						authorizedFor9XX.click();
					}
				}
				if (staffData.getProposedBillingRate() != 0.0) {
					WebElement proposedBillingRate = webDriver.findElement(By.id("proposedBillingRate"));
					proposedBillingRate.clear();
					proposedBillingRate.sendKeys(String.valueOf(staffData.getProposedBillingRate()));
				}
				if (staffData.getEffectiveDate() != null) {
					CalendarSelector.imitateClick(webDriver, locationWindow, staffData.getEffectiveDate(),
							By.id("currentRequestDate"));
				}
				ScreenCaptureUtils.captureByAWT(testImageEntity);
				WaitElementPresent.waitFindElement(webDriver, 2, By.id("Save")).click();
				if (WindowSwitcher.isExists(webDriver, locationWindow)) {
					String errMessage = WaitElementPresent.waitFindElement(webDriver, 3, By.id("errlabel")).getText()
							.replaceAll("\n", "");
					message = errMessage;
				} else {
					webDriver.switchTo().window(mainWindow);
				}
			} else {
				throw new TestException("Cannot find the defense counsel:" + staffData.getVendorName());
			}
		} else {
			throw new TestException("Defense Counsel Name has not value.");
		}

		return message;
	}

	public static String editStaff(WebDriver webDriver, StaffData staffData, TestImageEntity imageEntity) {
		String message = null;
		if (staffData.getVendorName() != null) {
			List<WebElement> dcList = DefenseCounselUtils.findDCsByName(webDriver, staffData.getVendorName());
			if (!dcList.isEmpty()) {
				dcList.get(0).findElement(By.xpath("../..")).findElement(By.xpath("td[6]/a")).click();
				WaitTimeoutUtils.sleep(2);
				webDriver.findElement(
						By.linkText(staffData.getFirstName().trim() + " " + staffData.getLastName().trim())).click();
				String mainWindow = webDriver.getWindowHandle();
				String locationWindow = WindowSwitcher.switchToWindow();

				if (staffData.getProposedFirstName() != null) {
					WebElement proposedFirstName = webDriver.findElement(By.id("proposedFirstName"));
					proposedFirstName.clear();
					proposedFirstName.sendKeys(staffData.getProposedFirstName());
				}

				if (staffData.getProposedLastName() != null) {
					WebElement proposedLastName = webDriver.findElement(By.id("proposedLastName"));
					proposedLastName.clear();
					proposedLastName.sendKeys(staffData.getProposedLastName());
				}
				if (staffData.getProposedLEDESName() != null) {
					WebElement proposedLedesName = webDriver.findElement(By.id("proposedLEDESName"));
					proposedLedesName.clear();
					proposedLedesName.sendKeys(staffData.getProposedLEDESName());
				}

				if (staffData.getStaffTitleName() != null) {
					Selector.select(webDriver, By.id("staffTitleId"), staffData.getStaffTitleName());
				}

				if (staffData.getPhone1() != null) {
					WebElement phone1 = webDriver.findElement(By.id("phone1"));
					phone1.clear();
					phone1.sendKeys(staffData.getPhone1());
				}
				if (staffData.getPhone2() != null) {
					WebElement phone2 = webDriver.findElement(By.id("phone2"));
					phone2.clear();
					phone2.sendKeys(staffData.getPhone2());
				}
				if (staffData.getPhone3() != null) {
					WebElement phone3 = webDriver.findElement(By.id("phone3"));
					phone3.clear();
					phone3.sendKeys(staffData.getPhone3());
				}
				if (staffData.getFax1() != null) {
					WebElement fax1 = webDriver.findElement(By.id("fax1"));
					fax1.clear();
					fax1.sendKeys(staffData.getFax1());
				}
				if (staffData.getFax2() != null) {
					WebElement fax2 = webDriver.findElement(By.id("fax2"));
					fax2.clear();
					fax2.sendKeys(staffData.getFax2());
				}
				if (staffData.getFax3() != null) {
					WebElement fax3 = webDriver.findElement(By.id("fax3"));
					fax3.clear();
					fax3.sendKeys(staffData.getFax3());
				}
				if (staffData.getEmail() != null) {
					webDriver.findElement(By.id("email")).clear();
					webDriver.findElement(By.id("email")).sendKeys(staffData.getEmail());
				}
				WebElement authorizedFor9XX = webDriver.findElement(By.name("staffData.authorizedFor9XX"));
				if (!authorizedFor9XX.isSelected()) {
					if (staffData.isAuthorizedFor9XX()) {
						authorizedFor9XX.click();
					}
				} else {
					if (!staffData.isAuthorizedFor9XX()) {
						authorizedFor9XX.click();
					}
				}
				WebElement active = webDriver.findElement(By.name("staffData.active"));
				if (!active.isSelected()) {
					if (staffData.isActive()) {
						active.click();
					}
				} else {
					if (!staffData.isActive()) {
						active.click();
					}
				}
				if (staffData.getProposedBillingRate() != 0.0) {
					WebElement proposedBillingRate = webDriver.findElement(By.id("proposedBillingRate"));
					proposedBillingRate.clear();
					proposedBillingRate.sendKeys(String.valueOf(staffData.getProposedBillingRate()));
				}
				if (staffData.getEffectiveDate() != null) {
					CalendarSelector.imitateClick(webDriver, locationWindow, staffData.getEffectiveDate(),
							By.id("currentRequestDate"));
				}
				ScreenCaptureUtils.captureByAWT(imageEntity);
				WaitElementPresent.waitFindElement(webDriver, 2, By.id("Save")).click();
				if (WindowSwitcher.isExists(webDriver, locationWindow)) {
					String errMessage = WaitElementPresent.waitFindElement(webDriver, 3, By.id("errlabel")).getText()
							.replaceAll("\n", "");
					message = errMessage;
				}else{
					webDriver.switchTo().window(mainWindow);
				}
			} else {
				throw new TestException("Cannot find the defense counsel:" + staffData.getVendorName());
			}
		} else {
			throw new TestException("Defense Counsel Name has not value.");
		}

		return message;
	}

	public static WebElement deleteStaff(WebDriver webDriver, StaffData staffData, TestImageEntity imageEntity) {
		WebElement errorFile = null;
		String staffName = staffData.getFirstName().trim() + " " + staffData.getLastName().trim();
		List<WebElement> dcList1 = DefenseCounselUtils.findDCsByName(webDriver, staffData.getVendorName());
		if (!dcList1.isEmpty()) {
			dcList1.get(0).findElement(By.xpath("../..")).findElement(By.xpath("td[6]/a")).click();
			WebElement cbx2 = TableElementFinder.findCbxByLink(staffName);
			cbx2.click();
			webDriver.findElement(By.name("Delete")).click();
			Alert dialogWindow = webDriver.switchTo().alert();
			dialogWindow.accept();
			errorFile = WaitElementPresent.isExist(webDriver, By.partialLinkText("Staff_DeleteFailures_"), 2);
		} else {
			throw new TestException("Cannot find the defense counsel:" + staffData.getVendorName());
		}
		return errorFile;
	}
	

	/**
	 * find staff link
	 * 
	 * @param webDriver
	 * @param staffName
	 * @param dcName
	 * @return
	 */
	public static WebElement findStaffLink(WebDriver webDriver, String staffName, String dcName) {
		WebElement link = null;
		DefenseCounselUtils.findDCsByName(webDriver, dcName).get(0).findElement(By.xpath("../.."))
				.findElement(By.tagName("a")).click();
		webDriver.switchTo();
		WaitTimeoutUtils.sleep(1);
		link = WaitElementPresent.waitFindElement(webDriver, By.linkText(staffName));
		return link;
	}
}
