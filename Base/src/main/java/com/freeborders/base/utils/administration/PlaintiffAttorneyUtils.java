package com.freeborders.base.utils.administration;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.freeborders.base.entity.administration.PlaintiffAttorneyData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class PlaintiffAttorneyUtils {
	/**
	 * new an plaintiff attorney
	 */
	public static String createPlaintiffAttorney(WebDriver webDriver, PlaintiffAttorneyData paData) {
		String message = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.PLAINTIFF_ATTORNEYS);
		// webDriver.get(PathConstant.SERVER_PATH+ AdministratorMenuPath.Platiff_Attoneys);
		// 1. Created a PA named "Asland", assign it with an agreement then try
		// to delete it and check the result .
		WaitTimeoutUtils.sleep(2);
		WaitElementPresent.waitFindElement(webDriver, 5, By.name("New")).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		if (paData.getName() != null && !"".equals(paData.getName())) {
			// set the pa name
			WaitElementPresent.waitFindElement(webDriver, 3, By.name("paData.name")).clear();
			WaitElementPresent.waitFindElement(webDriver, 3, By.name("paData.name")).sendKeys(paData.getName());
		}
		if (paData.getShortName() != null && !"".equals(paData.getShortName())) {
			// set pa short name
			webDriver.findElement(By.name("paData.shortName")).clear();
			webDriver.findElement(By.name("paData.shortName")).sendKeys(paData.getShortName());
		}
		if (paData.getTaxId() != null && !"".equals(paData.getTaxId())) {
			// set tax id
			webDriver.findElement(By.id("taxId")).clear();
			webDriver.findElement(By.id("taxId")).sendKeys(paData.getTaxId());
		}
		if (paData.getAddress1() != null && !"".equals(paData.getAddress1())) {
			// set address1
			webDriver.findElement(By.id("address1")).clear();
			webDriver.findElement(By.id("address1")).sendKeys(paData.getAddress1());
		}
		if (paData.getAddress2() != null && !"".equals(paData.getAddress2())) {
			// set address2
			webDriver.findElement(By.id("address2")).clear();
			webDriver.findElement(By.id("address2")).sendKeys(paData.getAddress2());
		}
		if (paData.getCity() != null && !"".equals(paData.getCity())) {
			// set the city
			webDriver.findElement(By.id("city")).clear();
			webDriver.findElement(By.id("city")).sendKeys(paData.getCity());
		}
		// select oi state
		if (paData.getStatesName() != null) {
			Selector.select(webDriver, By.name("paData.stateId"), paData.getStatesName());
		}
		if (paData.getZipCode() != null && !"".equals(paData.getZipCode())) {
			// set the zipCode
			webDriver.findElement(By.id("zipCode")).clear();
			webDriver.findElement(By.id("zipCode")).sendKeys(paData.getZipCode());
		}
		if (paData.getPhone1() != null && paData.getPhone2() != null && paData.getPhone3() != null) {
			// set phone1,phone2,phone3
			webDriver.findElement(By.id("phone1")).clear();
			webDriver.findElement(By.id("phone1")).sendKeys(paData.getPhone1());
			webDriver.findElement(By.id("phone2")).clear();
			webDriver.findElement(By.id("phone2")).sendKeys(paData.getPhone2());
			webDriver.findElement(By.id("phone3")).clear();
			webDriver.findElement(By.id("phone3")).sendKeys(paData.getPhone3());
		}
		if (paData.getAccountPayableId() != null) {
			// set account Payable Id
			webDriver.findElement(By.id("accountPayableId")).clear();
			webDriver.findElement(By.id("accountPayableId")).sendKeys(paData.getAccountPayableId());
		}
		// check the checkbox:nationalAttorney
		WebElement nationalAttorney = webDriver.findElement(By.id("nationalAttorney"));
		if (paData.getNationalAttorney() != null && !"".equals(paData.getNationalAttorney())) {
			if ("1".equals(paData.getNationalAttorney())) {
				if (!nationalAttorney.isSelected()) {
					nationalAttorney.click();
				}
			} else {
				if (nationalAttorney.isSelected()) {
					nationalAttorney.click();
				}
			}
		}
		// check the checkbox:Active
		WebElement active = webDriver.findElement(By.id("active"));
		if (!active.isSelected()) {
			active.click();
		}
		// select oi state
		if (paData.getOiStateName() != null && !"".equals(paData.getOiStateName())) {
			Selector.select(webDriver, By.id("oiStateId"), paData.getOiStateName());
			webDriver.findElement(By.name("Add2")).click();
		}
		WaitElementPresent.waitFindElement(webDriver, 2, By.id("Save")).click();
		WaitTimeoutUtils.sleep(3);
	
		if (WindowSwitcher.isExists(webDriver, locationWindow)) {
			WebElement errMessage = WaitElementPresent.waitFindElement(webDriver, 5, By.id("errlabel"));
			if (errMessage.getText().trim() != null && !"".equals(errMessage.getText().trim())) {
				message = errMessage.getText().trim();
			}
		} else {
			webDriver.switchTo().window(mainWindow);
			WaitTimeoutUtils.sleep(1);
			WebElement successMessage = WaitElementPresent.waitFindElement(webDriver, 2, By.id("errlabel"));
			message = successMessage.getText().trim();
		}

		return message;
	}

	/**
	 * search plaintiff attorney by name return the link of pa name
	 */
	public static WebElement searchPAByName(WebDriver webDriver, String paName) {
		WebElement nameLink = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.PLAINTIFF_ATTORNEYS);
		WaitTimeoutUtils.sleep(2);
		// webDriver.get(PathConstant.SERVER_PATH+ AdministratorMenuPath.Platiff_Attoneys);
		WaitElementPresent.waitFindElement(webDriver, 2, By.name("findName")).clear();
		WaitElementPresent.waitFindElement(webDriver, 2, By.name("findName")).sendKeys(paName);
		webDriver.findElement(By.name("Refresh0")).click();
		List<WebElement> nameLinks = webDriver.findElements(By.xpath("//tr[@class='generaltable']/td[2]/a"));
		for (WebElement name : nameLinks) {
			if (paName.equals(name.getText())) {
				nameLink = name;
				break;
			}
		}
		return nameLink;
	}

	/**
	 * delete plaintiff attorney
	 */
	public static String deletePA(WebDriver webDriver, String paName) {
		String message = null;
		PlaintiffAttorneyUtils.searchPAByName(webDriver, paName);
		WebElement checkbox = TableElementFinder.findCbxByLink(paName);
		checkbox.click();
		webDriver.findElement(By.name("Delete")).click();
		Alert alertDialog = webDriver.switchTo().alert();
		alertDialog.accept();
		WebElement resultMessage = WaitElementPresent.waitFindElement(webDriver, 2, By.id("errlabel"));
		message = resultMessage.getText().trim();
		return message;
	}

	/**
	 * delete oi state from the selected state
	 */
	public static String deleteOiState(WebDriver webDriver, String mainWindow, String selectedState) {
		String message = null;
		List<WebElement> options = new Select(WaitElementPresent.waitFindElement(webDriver, 2, By.id("selectedId")))
				.getOptions();
		List<String> selectedStateList = new ArrayList<String>(5);
		for (WebElement option : options) {
			selectedStateList.add(option.getText());
		}
		if (selectedStateList.contains(selectedState)) {
			Selector.select(webDriver, By.id("selectedId"), selectedState);
			webDriver.findElement(By.name("Delete")).click();
			webDriver.findElement(By.name("Save")).click();
			webDriver.switchTo().window(mainWindow);
			WaitTimeoutUtils.sleep(3);
			WebElement updateMessage = WaitElementPresent.waitFindElement(webDriver, 2, By.id("errlabel"));
			if (updateMessage != null) {
				message = updateMessage.getText();
			}
		} else {
			WindowSwitcher.jsCloseWindow(webDriver);
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}

	/**
	 * add oi state to the selected states list
	 */
	public static String addOiState(WebDriver webDriver, String mainWindow, String oiStateName) {
		String message = null;
		Selector.select(webDriver, By.id("oiStateId"), oiStateName);
		webDriver.findElement(By.name("Add2")).click();
		webDriver.findElement(By.name("Save")).click();
		webDriver.switchTo().window(mainWindow);
		WebElement updateMessage = WaitElementPresent.waitFindElement(webDriver, 2, By.id("errlabel"));
		if (updateMessage != null) {
			message = updateMessage.getText();
		}
		return message;
	}

	/**
	 * get PA tds by state 
	 * @param webDriver
	 * @param oiState
	 * @return
	 */
	public static List<WebElement> searchByState(WebDriver webDriver, String oiState) {
		List<WebElement> tds = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.PLAINTIFF_ATTORNEYS);
		WaitTimeoutUtils.sleep(2);
		WebElement element=WaitElementPresent.waitFindElement(webDriver, 3, By.name("stateId"));
		new Select(element).selectByVisibleText(oiState);
		webDriver.findElement(By.name("Refresh0")).click();
		tds=TableElementFinder.findColumnTds(webDriver, By.id("PaTab"), 2, 2);;
		return tds;

	}
}
