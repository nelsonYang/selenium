package com.freeborders.base.utils.administration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.exception.TestException;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class SecurityRolesUtils {
	static Log log = LogFactory.getLog(SecurityRolesUtils.class);

	/**
	 * edit the roles of security role
	 * 
	 * @param webDriver
	 * @param securityRole
	 * @param roleNames
	 *            (the roles needed to add to the security role)
	 * @param imageEntity
	 */
	public static void editRoles(WebDriver webDriver, String securityRole, List<String> roleNames,
			TestImageEntity imageEntity) {
		String mainWindow = null;
		String locationWindow = null;
		JavascriptExecutor js;
		SubMenuSelector.administrationSubMenu(AdministrationPage.SECURITY_ROLES);
		WebElement security = TableElementFinder.findElementByName(webDriver, securityRole,
				TableElementFinder.SEARCHBYALINK, 3);
		if (security != null) {
			security.click();
			mainWindow = webDriver.getWindowHandle();
			locationWindow = WindowSwitcher.switchToWindow();
			if (webDriver instanceof JavascriptExecutor) {
				js = (JavascriptExecutor) webDriver;
				js.executeScript("checkAll('selectedFunctionId', false);");
			}
			if (roleNames != null) {
				for (int i = 0; i < roleNames.size(); i++) {
					WebElement tr = TableElementFinder.findTrByTd(webDriver, 1, roleNames.get(i), By.id("FunctionTab"));
					WebElement cbx = tr.findElement(By.id("selectedFunctionId"));
					if (!cbx.isSelected()) {
						cbx.click();
					}
				}
			}
			ScreenCaptureUtils.captureByDriver(imageEntity);
			WaitElementPresent.waitFindElement(webDriver, 5, By.name("Save")).click();
			WaitTimeoutUtils.sleep(1);
			webDriver.switchTo().window(mainWindow);
		} else {
			throw new TestException("Can't find security role:" + securityRole);

		}
	}

	/**
	 * capture a image
	 * 
	 * @param webDriver
	 * @param securityRole
	 * @param roleNames
	 * @param imageEntity
	 */
	public static String editRoles(WebDriver webDriver, String securityRole, Set<String> roleNames,
			TestImageEntity imageEntity) {
		String mainWindow = null;
		String locationWindow = null;
		JavascriptExecutor js;
		SubMenuSelector.administrationSubMenu(AdministrationPage.SECURITY_ROLES);
		WaitElementPresent.waitElement(webDriver, 5, By.id("RoleTab"));
		WebElement security = TableElementFinder.findElementByName(webDriver, securityRole,
				TableElementFinder.SEARCHBYALINK, 4);
		if (security != null) {
			security.click();
			mainWindow = webDriver.getWindowHandle();
			locationWindow = WindowSwitcher.switchToWindow();
			WebElement saveButton = WaitElementPresent.waitFindElement(webDriver, By.name("Save"));
			TableElementFinder.selectGridBoxes(webDriver, roleNames, 1, By.id("FunctionTab"));
			ScreenCaptureUtils.captureByDriver(imageEntity);
			saveButton.click();
			webDriver.switchTo().window(mainWindow);
		} else {
			throw new TestException("Can't find security role:" + securityRole);

		}
		return WaitElementPresent.getErrorMsg(webDriver);
	}

	public static void clearAllRoles(WebDriver webDriver, String securityRole, TestImageEntity imageEntity) {
		String mainWindow = null;
		String locationWindow = null;
		JavascriptExecutor js;
		SubMenuSelector.administrationSubMenu(AdministrationPage.SECURITY_ROLES);
		WebElement security = TableElementFinder.findElementByName(webDriver, securityRole,
				TableElementFinder.SEARCHBYALINK, 3);
		if (security != null) {
			security.click();
			mainWindow = webDriver.getWindowHandle();
			locationWindow = WindowSwitcher.switchToWindow();
			if (webDriver instanceof JavascriptExecutor) {
				js = (JavascriptExecutor) webDriver;
				js.executeScript("checkAll('selectedFunctionId', false);");
			}
			webDriver.findElement(By.name("Save")).click();
			webDriver.switchTo().window(mainWindow); 
			WaitElementPresent.getErrorMsg(webDriver);
		} else {
			throw new TestException("Can't find security role:" + securityRole);
		}
	}

	/**
	 * check the shown of sub-menu in Administration module under the selected security role
	 * 
	 * @param webDriver
	 * @param imageEntity
	 */
	public static void checkSubMenuShownInAdmin(WebDriver webDriver, TestImageEntity imageEntity) {
		SubMenuSelector.mouseOverMainMenu(webDriver, By.id("frameHeadermenuBar_5"));
		List<WebElement> adminSubMenu = webDriver.findElements(By
				.xpath("//div[@id='frameHeadermenuBar_5M']/span/table[@class='frameHeadermenuBarIslandsub']/tbody/tr"));
		if (!adminSubMenu.isEmpty()) {
			for (WebElement submenu : adminSubMenu) {
				String subMenuName = submenu.findElement(By.xpath("td/div")).getText();
				if (subMenuName != null && !"User Preferences".equals(subMenuName)) {
					throw new TestException("The subMenus " + subMenuName
							+ " shouldn't display under this security role.");
				}
			}
		}
		ScreenCaptureUtils.captureByDriver(imageEntity);
	}

	/**
	 * check the list page under the edit security role the new button and delete button should
	 * display on the list page.
	 * 
	 * @param webDriver
	 * @param submenuId
	 *            (for example:By.id("frameHeadermenuBar_5_10"))
	 * @param subMenuName
	 * @param imageEntity
	 */
	public static void checkShownUnderEditRoles(WebDriver webDriver, By submenuId, AdministrationPage subMenuName,
			TestImageEntity imageEntity) {
		WebElement projectsElement = webDriver.findElement(submenuId);
		if (projectsElement != null) {
			SubMenuSelector.administrationSubMenu(subMenuName);
			WebElement newBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='New']"), 5);
			if (newBtn == null || !newBtn.isDisplayed()) {
				throw new TestException("New button should display if the user has the edit previlege.");
			}
			WebElement deleteBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='Delete']"), 5);
			if (deleteBtn == null || !deleteBtn.isDisplayed()) {
				throw new TestException("Delete button should display if the user has the edit previlege.");
			}
			ScreenCaptureUtils.captureByDriver(imageEntity);
		} else {
			throw new TestException("The security role can't work correctly.");
		}
	}

	/**
	 * 
	 * @param webDriver
	 * @param subMenuName
	 * @param imageEntity
	 */
	public static void checkShownUnderEditRoles(WebDriver webDriver, AdministrationPage subMenuName,
			TestImageEntity imageEntity) {
		SubMenuSelector.administrationSubMenu(subMenuName);
		WebElement newBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='New']"), 5);
		if (newBtn == null || !newBtn.isDisplayed()) {
			throw new TestException("New button should display if the user has the edit previlege.");
		}
		WebElement deleteBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='Delete']"), 5);
		if (deleteBtn == null || !deleteBtn.isDisplayed()) {
			throw new TestException("Delete button should display if the user has the edit previlege.");
		}
		ScreenCaptureUtils.captureByDriver(imageEntity);
	}

	/**
	 * Generally check new and delete those two buttons
	 * 
	 * @param webDriver
	 * @param imageEntity
	 */
	public static void checkShownUnderEditRoles(WebDriver webDriver, TestImageEntity imageEntity) {
		WebElement newBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='New']"), 5);
		if (newBtn == null || !newBtn.isDisplayed()) {
			throw new TestException("New button should display if the user has the edit previlege.");
		}
		WebElement deleteBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='Delete']"), 5);
		if (deleteBtn == null || !deleteBtn.isDisplayed()) {
			throw new TestException("Delete button should display if the user has the edit previlege.");
		}
		ScreenCaptureUtils.captureByDriver(imageEntity);
	}

	/**
	 * check the list page under the view security role the new and delete button should not display
	 * on the list page. in a submenu
	 * 
	 * @param webDriver
	 * @param submenuId
	 *            (for example:By.id("frameHeadermenuBar_5_10"))
	 * @param subMenuName
	 * @param imageEntity
	 */
	public static void checkShownUnderViewRoles(WebDriver webDriver, By submenuId, AdministrationPage subMenuName,
			TestImageEntity imageEntity) {
		WebElement projectsElement = webDriver.findElement(submenuId);
		if (projectsElement != null) {
			SubMenuSelector.administrationSubMenu(subMenuName);
			WebElement newBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='New']"), 5);
			if (newBtn != null && !newBtn.getAttribute("style").contains("none")) {
				throw new TestException("New button should not display if the user hasn't the edit previlege.");
			}
			WebElement deleteBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='Delete']"), 5);
			if (deleteBtn != null && !deleteBtn.getAttribute("style").contains("none")) {
				throw new TestException("Delete button should not display if the user hasn't the edit previlege.");
			}
			ScreenCaptureUtils.captureByDriver(imageEntity);
		} else {
			throw new TestException("The security role can't work correctly.");
		}
	}

	/**
	 * Generally check a screen is read-only or not save ,delete ,new three button
	 * 
	 * @param webDriver
	 * @param imageEntity
	 */
	public static void checkShownUnderViewRoles(WebDriver webDriver, TestImageEntity imageEntity) {
		WebElement newBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='New']"), 5);
		if (newBtn != null && newBtn.isDisplayed()) {
			throw new TestException("New button should not display if the user hasn't the edit previlege.");
		}
		WebElement deleteBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='Delete']"), 5);
		if (deleteBtn != null && deleteBtn.isDisplayed()) {
			throw new TestException("Delete button should not display if the user hasn't the edit previlege.");
		}
		WebElement saveBtn = WaitElementPresent.isExist(webDriver, By.xpath("//input[@value='Save']"), 5);
		if (saveBtn != null && saveBtn.isDisplayed()) {
			throw new TestException("save button should not display if the user hasn't the edit previlege.");
		}
		ScreenCaptureUtils.captureByDriver(imageEntity);
	}

	/**
	 * find security roles by name
	 * 
	 * @param webDriver
	 * @param roleName
	 * @return
	 */
	public static WebElement findSecurityRole(WebDriver webDriver, String roleName) {
		WebElement link = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.SECURITY_ROLES);
		WaitTimeoutUtils.sleep(1);
		link = TableElementFinder.findElementByName(webDriver, roleName, TableElementFinder.SEARCHBYALINK, 3);
		return link;
	}

	/**
	 * delete a security role,when can't be deleted ,there are two screen captures
	 * 
	 * @param webDriver
	 * @param securityRole
	 * @param imageEntity
	 * @return
	 */
	public static String deleteSecurityRole(WebDriver webDriver, String securityRole, TestImageEntity imageEntity) {
		String message = null;
		WebElement link = findSecurityRole(webDriver, securityRole);
		link.findElement(By.xpath("../../td[1]/input")).click();
		WaitElementPresent.waitFindElement(webDriver, By.name("Delete")).click();
		WindowSwitcher.confirmDialog(webDriver, true);
		WebElement errLink = WaitElementPresent.isExist(webDriver, By.partialLinkText("Role_DeleteFailures"),
				WaitElementPresent.WAIT_TIME_SHORT);
		if (errLink != null) {
			ScreenCaptureUtils.captureByDriver(imageEntity);
			errLink.click();
			String mainWindow = webDriver.getWindowHandle();
			String subWindow = WindowSwitcher.switchToWindow();
			WebElement pre = WaitElementPresent.waitFindElement(webDriver, By.tagName("pre"));
			ScreenCaptureUtils.captureByDriver(imageEntity);
			message = pre.getText();
			webDriver.close();
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}

	/**
	 * this method usually used in role check test case: relogin add a role to a security role ,then capture
	 * image
	 * capture 2 images
	 * @param webDriver
	 * @param userID1
	 * @param sRName
	 * @param role1
	 * @param imageEntity
	 */
	public static void setARole(WebDriver webDriver, String userID1, String sRName, String role1,
			TestImageEntity imageEntity) {
		TestApplicationContext.CTX.getTestLoginHandler().reLogin(webDriver, userID1);
		Set<String> roles = new HashSet<String>();
		roles.add(role1);
		SecurityRolesUtils.editRoles(webDriver, sRName, roles, imageEntity);
		WaitElementPresent.getErrorMsg(webDriver);
		ScreenCaptureUtils.captureByDriver(imageEntity);
	}

	/**
	 * this method usually used in role check test case: relogin, set roles to a security role ,then capture
	 * image
	 * capture 2 images
	 * @param webDriver
	 * @param userID1
	 * @param sRName
	 * @param roles
	 * @param imageEntity
	 */
	public static void setSomeRoles(WebDriver webDriver, String userID1, String sRName, Set<String> roles,
			TestImageEntity imageEntity) {
		TestApplicationContext.CTX.getTestLoginHandler().reLogin(webDriver, userID1);
		SecurityRolesUtils.editRoles(webDriver, sRName, roles, imageEntity);
		WaitElementPresent.getErrorMsg(webDriver);
		ScreenCaptureUtils.captureByDriver(imageEntity);
	}
}
