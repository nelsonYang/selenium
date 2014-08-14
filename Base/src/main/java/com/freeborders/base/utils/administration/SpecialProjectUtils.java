package com.freeborders.base.utils.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.ProjectData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.exception.TestException;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class SpecialProjectUtils {

	/**
	 * new an special project
	 */
	public static String createSpecialProject(WebDriver webDriver,
			ProjectData projectData) {
		String message=null;
		 SubMenuSelector.administrationSubMenu(AdministrationPage.SPECIAL_PROJECTS);
//		webDriver.get(PathConstant.SERVER_PATH + AdministratorMenuPath.Special_Projects);
		//new an special project:SP TEST
		 WaitTimeoutUtils.sleep(2);
		webDriver.findElement(By.id("NewProject")).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		WaitElementPresent.waitFindElement(webDriver, 2, By.id("projectId"))
				.sendKeys(projectData.getProjectId());
		WaitElementPresent.waitFindElement(webDriver, 2, By.id("name"))
				.sendKeys(projectData.getName());
		// select the project type
		new Select(WaitElementPresent.waitFindElement(webDriver, 2,
				By.id("projectTypeId"))).selectByVisibleText(projectData.getProjectTypeName());
		// select one division
		new Select(WaitElementPresent.waitFindElement(webDriver, 2,
				By.id("divisionId")))
				.selectByVisibleText(projectData.getDivisionName());
		// select state
		new Select(WaitElementPresent.waitFindElement(webDriver, 2,
				By.id("oiStateId"))).selectByVisibleText(projectData.getOiStateName());
		// select an plaintiff attorney
		new Select(WaitElementPresent.waitFindElement(webDriver, 2,
				By.id("paId"))).selectByVisibleText(projectData.getPaName());
		webDriver.findElement(By.id("Save")).click();
		WaitTimeoutUtils.sleep(3);
		if(WindowSwitcher.isExists(webDriver, locationWindow)) {
			WebElement errMessage = WaitElementPresent.waitFindElement(webDriver,5, By.id("errlabel"));
			if(errMessage.getText().trim() != null && !"".equals(errMessage.getText().trim())) {
				message = errMessage.getText().trim();
			}
		} else {
			webDriver.switchTo().window(mainWindow);
			WaitTimeoutUtils.sleep(1);
			WebElement errMessage = WaitElementPresent.waitFindElement(webDriver,2, By.id("errlabel"));
			message = errMessage.getText().trim();
		}
	
		return message;
	}
	
	/**
	 * new an special project
	 */
	public static String createSpecialProject(WebDriver webDriver, ProjectData projectData, TestImageEntity imageEntity) {
		String message = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.SPECIAL_PROJECTS);
		// webDriver.get(PathConstant.SERVER_PATH + AdministratorMenuPath.Special_Projects);
		// new an special project:SP TEST
		WaitTimeoutUtils.sleep(2);
		webDriver.findElement(By.id("NewProject")).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		WaitElementPresent.waitFindElement(webDriver, 2, By.id("projectId")).sendKeys(projectData.getProjectId());
		WaitElementPresent.waitFindElement(webDriver, 2, By.id("name")).sendKeys(projectData.getName());
		// select the project type
		new Select(WaitElementPresent.waitFindElement(webDriver, 2, By.id("projectTypeId")))
				.selectByVisibleText(projectData.getProjectTypeName());
		// select one division
		new Select(WaitElementPresent.waitFindElement(webDriver, 2, By.id("divisionId")))
				.selectByVisibleText(projectData.getDivisionName());
		// select state
		new Select(WaitElementPresent.waitFindElement(webDriver, 2, By.id("oiStateId")))
				.selectByVisibleText(projectData.getOiStateName());
		// select an plaintiff attorney
		new Select(WaitElementPresent.waitFindElement(webDriver, 2, By.id("paId"))).selectByVisibleText(projectData
				.getPaName());
		ScreenCaptureUtils.captureByDriver(imageEntity);
		webDriver.findElement(By.id("Save")).click();
		WaitTimeoutUtils.sleep(3);
		if (WindowSwitcher.isExists(webDriver, locationWindow)) {
			WebElement errMessage = WaitElementPresent.waitFindElement(webDriver, 5, By.id("errlabel"));
			if (errMessage.getText().trim() != null && !"".equals(errMessage.getText().trim())) {
				message = errMessage.getText().trim();
			}
		} else {
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}


	public static String editSpecialProject(WebDriver webDriver, ProjectData projectData, TestImageEntity imageEntity) {
		String message = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.SPECIAL_PROJECTS);
		Selector.select(webDriver, By.name("stateId"), projectData.getOriginalOiStateName());
		Selector.select(webDriver, By.name("projectTypeId"), projectData.getOriginalProjectTypeName());
		Selector.select(webDriver, By.name("divisionId"), projectData.getOriginalDivisionName());
		webDriver.findElement(By.name("Refresh0")).click();
		if (projectData.getOriginalName() != null) {
			WebElement link = TableElementFinder.findElementByName(webDriver, projectData.getOriginalName(),
					TableElementFinder.SEARCHBYALINK, 5);
			if (link != null) {
				link.click();
				String mainWindow = webDriver.getWindowHandle();
				String locationWindow = WindowSwitcher.switchToWindow();

				if (projectData.getProjectId() != null) {
					WebElement projectId = WaitElementPresent.waitFindElement(webDriver, 2, By.id("projectId"));
					projectId.clear();
					projectId.sendKeys(projectData.getProjectId());
				}
				if (projectData.getName() != null) {
					WebElement title = WaitElementPresent.waitFindElement(webDriver, 2, By.id("name"));
					title.clear();
					title.sendKeys(projectData.getName());
				}

				WebElement active = webDriver.findElement(By.name("projectData.active"));
				if (projectData.isActive()) {
					if (!active.isSelected()) {
						active.click();
					}
				} else {
					if (active.isSelected()) {
						active.click();
					}
				}
				if (projectData.getProjectTypeName() != null) {
					Selector.select(webDriver, By.id("projectTypeId"), projectData.getProjectTypeName());
				}
				if (projectData.getDivisionName() != null) {
					Selector.select(webDriver, By.id("divisionId"), projectData.getDivisionName());
				}
				if (projectData.getOiStateName() != null) {
					Selector.select(webDriver, By.id("oiStateId"), projectData.getOiStateName());
				}
				if (projectData.getPaName() != null) {
					Selector.select(webDriver, By.id("paId"), projectData.getPaName());
				}

				ScreenCaptureUtils.captureByDriver(imageEntity);
				webDriver.findElement(By.name("Save")).click();

				if (WindowSwitcher.isExists(webDriver, locationWindow)) {
					message = WaitElementPresent.getErrorMsg(webDriver);
				} else {
					webDriver.switchTo().window(mainWindow);
				}
			} else {
				throw new TestException("Not find Special Project with title " + projectData.getName());
			}
		} else {
			throw new TestException("There is no value for search, please check the title of special project.");
		}
		return message;
	}
	
	public static WebElement searchSPByConditions(WebDriver webDriver, ProjectData projectData,
			TestImageEntity imageEntity) {
		WebElement projectLink = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.SPECIAL_PROJECTS);
		if (projectData.getOiStateName() != null) {
			Selector.select(webDriver, By.name("stateId"), projectData.getOiStateName());
		}
		if (projectData.getProjectTypeName() != null) {
			Selector.select(webDriver, By.name("projectTypeId"), projectData.getProjectTypeName());
		}
		if (projectData.getDivisionName() != null) {
			Selector.select(webDriver, By.name("divisionId"), projectData.getDivisionName());
		}
		webDriver.findElement(By.name("Refresh0")).click();
		WebElement trElement = WaitElementPresent.isExist(webDriver, By.xpath("//tr[@class='generaltable']"), 5);
		if(trElement != null) {
			projectLink = TableElementFinder.findElementByName(webDriver, projectData.getName(), TableElementFinder.SEARCHBYALINK, 5);
		}
		return projectLink;
	}
	
	public static String deleteSpecialProject(WebDriver webDriver, ProjectData projectData,
			TestImageEntity imageEntity) {
		String mainWindow = null;
		String locationWindow = null;
		String message = null;
		WebElement projectLink2 = SpecialProjectUtils.searchSPByConditions(webDriver, projectData, imageEntity);
		if(projectLink2 != null){
			WebElement cbx = TableElementFinder.findCbxByLink(projectData.getName());
			mainWindow = webDriver.getWindowHandle();
			cbx.click();
			webDriver.findElement(By.name("Delete")).click();
			WindowSwitcher.confirmDialog(webDriver, true);
			webDriver.switchTo().window(mainWindow);
			WebElement errorLink = WaitElementPresent.isExist(webDriver, By.partialLinkText("Specialproject_DeleteFailures_"), 5);
			ScreenCaptureUtils.captureByDriver(imageEntity);
			if(errorLink != null) {
				errorLink.click();
				mainWindow = webDriver.getWindowHandle();
				locationWindow = WindowSwitcher.switchToWindow();
				ScreenCaptureUtils.captureByDriver(imageEntity);
				WindowSwitcher.checkAndCloseWindow(webDriver, locationWindow);
				webDriver.switchTo().window(mainWindow);
			}else {
				message = WaitElementPresent.getErrorMsg(webDriver);
			}
		}else{
			throw new TestException("Can't find Special Project with title:" + projectData.getName());
		}
		return message;
	}
}
