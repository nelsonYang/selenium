package com.freeborders.base.utils.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.ApproveStaffData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class ApproveStaffUtils {

	/**
	 * find a staff's tr
	 * 
	 * @param webDriver
	 * @param staffName
	 * @param imageEntity
	 * @return
	 */
	public static WebElement findStaffTr(WebDriver webDriver, ApproveStaffData data, TestImageEntity imageEntity) {
		WebElement webElement = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.NEW_STAFF_APPROVAL);
		WaitTimeoutUtils.sleep(1);
		if (data.getVendorName() != null) {
			Selector.select(webDriver, By.id("toStaffListAction_staffData_vendorId"), data.getVendorName());
			webDriver.findElement(By.name("Refresh0")).click();
		}
		WaitElementPresent.waitFindElement(webDriver, 4, By.id("newStaffApprovalTab"));
		webElement = TableElementFinder.findTrByTd(webDriver, 1, data.getFirstName() + " " + data.getLastName(),
				By.id("newStaffApprovalTab"));
		return webElement;
	}

	/**
	 * screen captured before approve if fail,return the error message
	 * 
	 * @param webDriver
	 * @param data
	 * @param imageEntity
	 * @return
	 */
	public static String approveStaff(WebDriver webDriver, ApproveStaffData data, TestImageEntity imageEntity) {
		String message = null;
		findStaffTr(webDriver, data, imageEntity).findElement(By.tagName("a")).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		if (data.isCreateUser()) {
			WaitElementPresent.waitFindElement(webDriver, 3, By.id("approveStaff1")).click();
			if (data.getRoleName() != null)
				Selector.select(webDriver, By.name("data.roleId"), data.getRoleName());
			if (data.getReportLevel() != null)
				Selector.select(webDriver, By.name("data.reportAccessLevel"), data.getReportLevel());
			if (!data.getStates().isEmpty()) {
				webDriver.findElement(By.name("reset")).click();
				List<WebElement> stateTrs = WaitElementPresent.waitFindElement(webDriver, 2, By.id("StateTab"))
						.findElements(By.tagName("tr"));
				for (int i = 0; i < stateTrs.size(); i++) {
					WebElement tr = stateTrs.get(i);
					if (data.getStates().isEmpty())
						break;
					if (data.getStates().remove(tr.findElement(By.tagName("td")).getText())) {
						WebElement checkBox = tr.findElement(By.tagName("input"));
						if (!checkBox.isSelected()) {
							checkBox.click();
						}
					}
				}
			}
		} else {
			WaitElementPresent.waitFindElement(webDriver, 3, By.id("approveStaff0")).click();
		}
		if (data.getFirstName() != null) {
			WebElement firstName = webDriver.findElement(By.name("staffData.firstName"));
			firstName.clear();
			firstName.sendKeys(data.getFirstName());
		}
		if (data.getLastName() != null) {
			WebElement lastName = webDriver.findElement(By.name("staffData.lastName"));
			lastName.clear();
			lastName.sendKeys(data.getLastName());
		}
		if (data.getEmail() != null) {
			WebElement email = webDriver.findElement(By.name("staffData.email"));
			email.clear();
			email.sendKeys(data.getEmail());
		}
		ScreenCaptureUtils.captureByDriver(imageEntity);
		webDriver.findElement(By.name("Approve")).click();
		if (WindowSwitcher.isExists(webDriver, locationWindow)) {
			message = WaitElementPresent.waitFindElement(webDriver, 2, By.id("errlabel")).getText();
		}else{
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}
}
