package com.freeborders.base.utils.administration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.UserData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.RadioSelector;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.TextInputUtils;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class UserUtils {
	static Log log = LogFactory.getLog(UserUtils.class);

	/**
	 * return the link of a specified user
	 * 
	 * @param webDriver
	 * @param name
	 * @param imageEntity
	 * @return
	 */
	public static List<WebElement> findUser(WebDriver webDriver, String name, TestImageEntity imageEntity) {
		SubMenuSelector.administrationSubMenu(AdministrationPage.USERS);
		List<WebElement> ele = null;
		WaitElementPresent.waitFindElement(webDriver, By.name("findUsers")).sendKeys(name);
		webDriver.findElement(By.name("popButton")).click();
		webDriver.switchTo();
		WaitTimeoutUtils.sleep(1);
		ele = WaitElementPresent.waitFindElements(webDriver, By.partialLinkText(name));
		return ele;
	}

	/**
	 * capture a image before save
	 * 
	 * @param webDriver
	 * @param data
	 * @param imageEntity
	 * @return
	 */
	public static String approveUser(WebDriver webDriver, UserData data, TestImageEntity testImageEntity) {
		String message = null;
		String name = data.getFirstName() + " " + data.getLastName();
		SubMenuSelector.administrationSubMenu(AdministrationPage.USERS);
		WebElement ele = null;
		WaitElementPresent.waitFindElement(webDriver, 3, By.name("findUsers")).sendKeys(name);
		webDriver.findElement(By.name("popButton")).click();
		ele = WaitElementPresent.waitFindElement(webDriver, 4, By.partialLinkText(name));
		// find link approve
		ele.findElement(By.xpath("../../td[4]/a")).click();
		String mainWindow = webDriver.getWindowHandle();
		String location = WindowSwitcher.switchToWindow();
		if (data.getUserName() != null) {
			WaitElementPresent.waitFindElement(webDriver, 3, By.name("data.userName")).sendKeys(data.getUserName());
		}
		if (data.getTokenSerial() != null) {
			webDriver.findElement(By.name("data.tokenSerial")).sendKeys(data.getTokenSerial());
		}
		if (data.getTokenSerial2() != null) {
			webDriver.findElement(By.name("data.tokenSerial2")).sendKeys(data.getTokenSerial2());
		}
		if (data.isActive()) {
			webDriver.findElement(By.name("data.active")).click();
		}
		if (data.isBocciManager()) {
			webDriver.findElement(By.name("data.bocciManager")).click();
		}
		if (data.isProd()) {
			webDriver.findElement(By.name("data.prod")).click();
		}
		if (data.isTest()) {
			webDriver.findElement(By.name("data.test")).click();
		}
		if (data.isToken_delivered()) {
			webDriver.findElement(By.name("data.token_delivered")).click();
		}
		if (!data.getStates().isEmpty()) {
			webDriver.findElement(By.name("showAll")).click();
			WebElement tr = WaitElementPresent.waitFindElement(webDriver, 2, By.id("StateTab")).findElement(
					By.tagName("tr"));
			do {
				if (data.getStates().isEmpty()) {
					break;
				}
				if (data.getStates().remove(tr.findElement(By.tagName("td")).getText())) {
					WebElement checkBox = tr.findElement(By.tagName("input"));
					if (!checkBox.isSelected()) {
						checkBox.click();
					}
				}
				// next tr
				tr = tr.findElement(By.xpath("following-sibling::tr[1]"));

			} while (tr != null);
		}
		ScreenCaptureUtils.captureByDriver(testImageEntity);
		WaitElementPresent.waitFindElement(webDriver, 2, By.name("Save")).click();
		WaitTimeoutUtils.sleep(1);
		if (WindowSwitcher.isExists(webDriver, location)) {
			message = WaitElementPresent.waitFindElement(webDriver, 5, By.id("errlabel")).getText();
		} else {
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}

	public static String deleteUser(WebDriver webDriver, String fullName, TestImageEntity imageEntity) {
		String message = null;
		List<WebElement> users = UserUtils.findUser(webDriver, fullName, imageEntity);
		if (!users.isEmpty()) {
			WebElement cbx = TableElementFinder.findCbxByLink(users.get(0).getText());
			cbx.click();
			webDriver.findElement(By.name("Delete")).click();
			WindowSwitcher.confirmDialog(webDriver, true);
			WebElement linked = WaitElementPresent.isExist(webDriver, By.partialLinkText("User_DeleteFailures"), 5);
			if (linked != null) {
				ScreenCaptureUtils.captureByDriver(imageEntity);
				String mainWindow = webDriver.getWindowHandle();
				linked.click();
				String subWindow = WindowSwitcher.switchToWindow();
				message = WaitElementPresent.waitFindElement(webDriver, 3, By.tagName("pre")).getText();
				ScreenCaptureUtils.captureByDriver(imageEntity);
				WindowSwitcher.jsCloseWindow(webDriver);
				webDriver.switchTo().window(mainWindow);
			}
		}
		return message;
	}

	/**
	 * get the current full user name
	 * 
	 * @param webDriver
	 * @return
	 */
	public static String getCurrentUsername(WebDriver webDriver) {
		String userName = WaitElementPresent.waitFindElement(webDriver, By.className("hint"))
				.findElement(By.xpath("tbody/tr[2]/td")).getText();
		userName = userName.substring(userName.indexOf(" ") + 1, userName.indexOf(","));
		return userName;
	}

	/**
	 * set the states of a user
	 * 
	 * @param webDriver
	 * @param states
	 */
	public static void setStates(WebDriver webDriver, Set<String> states) {
		JavascriptExecutor js;
		if (webDriver instanceof JavascriptExecutor) {
			js = (JavascriptExecutor) webDriver;
			js.executeScript("checkAll('selectedStateId', true)");
			js.executeScript("checkAll('selectedStateId', false)");
		}
		WebElement table = WaitElementPresent.waitFindElement(webDriver, 2, By.id("StateTab"));
		int i = 2;
		WebElement tr = table.findElement(By.xpath("./tbody/tr[" + i + "]"));
		do {
			if (states.isEmpty()) {
				break;
			}
			if (states.remove(tr.findElement(By.tagName("td")).getText())) {
				WebElement checkBox = tr.findElement(By.tagName("input"));
				if (!checkBox.isSelected()) {
					checkBox.click();
				}
			}
			i++;
			// next tr
			tr = WaitElementPresent.isExistByElement(webDriver, table, By.xpath("./tbody/tr[" + i + "]"));
		} while (tr != null);
	}

	/**
	 * change the role of user, will capture a image before save.
	 * 
	 * @param webDriver
	 * @param userName
	 * @param roleName
	 * @param imageEntity
	 * @return successfully msg
	 */
	public static String changeUserRole(WebDriver webDriver, String userName, String roleName,
			TestImageEntity imageEntity) {
		UserUtils.findUser(webDriver, userName, imageEntity).get(0).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		WaitElementPresent.waitFindElement(webDriver, By.id("roleId"));
		Selector.select(webDriver, By.id("roleId"), roleName);
		ScreenCaptureUtils.captureByDriver(imageEntity);
		WaitElementPresent.waitFindElement(webDriver, By.name("Save")).click();
		webDriver.switchTo().window(mainWindow);
		String msg = WaitElementPresent.getErrorMsg(webDriver);
		return msg;
	}

	public static String createUser(WebDriver webDriver, UserData userData, TestImageEntity imageEntity) {
		String message = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.USERS);
		WaitTimeoutUtils.sleep(1);
		WaitElementPresent.waitFindElement(webDriver, By.name("New")).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		WaitTimeoutUtils.sleep(1);
		TextInputUtils.editText(webDriver, By.id("firstName"), userData.getFirstName());
		TextInputUtils.editText(webDriver, By.id("lastName"), userData.getLastName());
		TextInputUtils.editText(webDriver, By.id("email"), userData.getEmail());
		TextInputUtils.editText(webDriver, By.id("tokenSerial"), userData.getTokenSerial());
		TextInputUtils.editText(webDriver, By.id("tokenSerial2"), userData.getTokenSerial2());
		RadioSelector.selectCheckbox(webDriver, By.id("internal"), userData.getInternal());
		RadioSelector.selectCheckbox(webDriver, By.id("jurisdictionManager"), userData.isJurisdictionManager());
		RadioSelector.selectCheckbox(webDriver, By.id("bocciManager"), userData.isBocciManager());
		RadioSelector.selectCheckbox(webDriver, By.id("claimProcessor"), userData.isClaimProcessor());
		RadioSelector.selectCheckbox(webDriver, By.id("prod"), userData.isProd());
		RadioSelector.selectCheckbox(webDriver, By.id("test"), userData.isTest());
		RadioSelector.selectCheckbox(webDriver, By.id("token_delivered"), userData.isToken_delivered());
		Selector.select(webDriver, By.id("roleId"), userData.getRoleName());
		Selector.select(webDriver, By.id("reportAccessLevel"), userData.getReportAccessLevel());
		log.info(userData.getStates().size());
		TableElementFinder.selectGridBoxes(webDriver, userData.getStates(), 1, By.id("StateTab"));
		ScreenCaptureUtils.captureByDriver(imageEntity);
		WaitElementPresent.waitFindElement(webDriver, By.name("Save")).click();
		if (WindowSwitcher.isExists(webDriver, locationWindow)) {
			message = WaitElementPresent.getErrorMsg(webDriver);
			ScreenCaptureUtils.captureByDriver(imageEntity);
		} else {
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}

	public static List<String> getUsersTds(WebDriver webDriver, int column, TestImageEntity imageEntity) {
		List<String> tds = new ArrayList<String>();
		SubMenuSelector.administrationSubMenu(AdministrationPage.USERS);
		// just for wait
		WaitElementPresent.waitFindElement(webDriver, 4, By.id("UserTab"));
		tds.addAll(TableElementFinder.findColumnTdTxt(webDriver, By.id("UserTab"), column, 2));
		List<WebElement> pages = WaitElementPresent.waitFindElements(webDriver, 3, By.className("gotoPage"));
		// all page jump times
		int pps = pages.size() - 2;
		for (int i = 0; i < pps; i++) {
			ScreenCaptureUtils.captureByDriver(imageEntity);
			WebElement nextPage = webDriver.findElement(By.className("contentbgbd")).findElements(By.tagName("span"))
					.get(i + 2);
			nextPage.click();
			// wait
			WaitElementPresent.waitFindElement(webDriver, 4, By.id("UserTab"));
			tds.addAll(TableElementFinder.findColumnTdTxt(webDriver, By.id("UserTab"), column, 2));
		}
		return tds;
	}

}
