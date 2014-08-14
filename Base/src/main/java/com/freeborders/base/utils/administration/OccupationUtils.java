package com.freeborders.base.utils.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.OccupationData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.exception.TestException;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.TextInputUtils;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WindowSwitcher;

public class OccupationUtils {
   public static String createOccupation(WebDriver webDriver, OccupationData occupationData, TestImageEntity imageEntity) {
	   String message = null;
	   String mainWindow = null;
	   String locationWindow = null;
	   SubMenuSelector.administrationSubMenu(AdministrationPage.OCCUPATION);
		//1. Go to Administration->Occupation, click the "New" button. 
		WaitElementPresent.waitFindElement(webDriver, 3, By.name("New")).click();
		mainWindow = webDriver.getWindowHandle();
		locationWindow = WindowSwitcher.switchToWindow();
		WaitElementPresent.waitFindElement(webDriver, 3, By.name("occupationForm.name")).sendKeys(occupationData.getName());
		WebElement traditional = webDriver.findElement(By.name("occupationForm.traditional"));
    	if(occupationData.getTraditional() == 1 && !traditional.isSelected()){
    		traditional.click();
    	}
    	if(occupationData.getTraditional() == -1 && traditional.isSelected()) {
    		traditional.click();
    	}
		Selector.select(webDriver, By.name("occupationForm.categoryId"), occupationData.getCategoryName());
		WebElement active = webDriver.findElement(By.name("occupationForm.active"));
    	if(occupationData.getActive() == 1 && !active.isSelected()){
    		active.click();
    	}
    	if(occupationData.getActive() == -1 && active.isSelected()) {
    		active.click();
    	}
		webDriver.findElement(By.name("Save")).click();
		if(WindowSwitcher.isExists(webDriver, locationWindow)) {
    		message = WaitElementPresent.getErrorMsg(webDriver);
    		WindowSwitcher.checkAndCloseWindow(webDriver, locationWindow);
    	}
		webDriver.switchTo().window(mainWindow);
	   return message;
   }
   
	public static String editOccupation(WebDriver webDriver, OccupationData occupationData, TestImageEntity imageEntity) {
		String message = null;
		String mainWindow = null;
		String locationWindow = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.OCCUPATION);
		WebElement nameLink = TableElementFinder.findElementByName(webDriver, occupationData.getOriginalName(),
				TableElementFinder.SEARCHBYALINK, 5);
		if (nameLink != null) {
			nameLink.click();
			mainWindow = webDriver.getWindowHandle();
			locationWindow = WindowSwitcher.switchToWindow();
			TextInputUtils.editText(webDriver, By.name("occupationForm.name"), occupationData.getName());
			WebElement traditional = webDriver.findElement(By.name("occupationForm.traditional"));
			if (occupationData.getTraditional() == 1 && !traditional.isSelected()) {
				traditional.click();
			}
			if (occupationData.getTraditional() == -1 && traditional.isSelected()) {
				traditional.click();
			}
			Selector.select(webDriver, By.name("occupationForm.categoryId"), occupationData.getCategoryName());
			WebElement active = webDriver.findElement(By.name("occupationForm.active"));
			if (occupationData.getActive() == 1 && !active.isSelected()) {
				active.click();
			}
			if (occupationData.getActive() == -1 && active.isSelected()) {
				active.click();
			}
			 ScreenCaptureUtils.captureByDriver(imageEntity);
			webDriver.findElement(By.name("Save")).click();
			if (WindowSwitcher.isExists(webDriver, locationWindow)) {
				message = WaitElementPresent.getErrorMsg(webDriver);
				WindowSwitcher.checkAndCloseWindow(webDriver, locationWindow);
			}
			webDriver.switchTo().window(mainWindow);
		}
		return message;
	}
	
	public static String deleteOccupation(WebDriver webDriver, String occupationName, TestImageEntity imageEntity) {
		String message = null;
		String mainWindow = null;
		String locationWindow = null;
		SubMenuSelector.administrationSubMenu(AdministrationPage.OCCUPATION);
		//before create occupation, search this test data, if it exits, delete it.
		WebElement nameLink = TableElementFinder.findElementByName(webDriver, occupationName, TableElementFinder.SEARCHBYALINK, 5);
		if(nameLink != null) {
			mainWindow = webDriver.getWindowHandle();
			WebElement cbx = TableElementFinder.findCbxByLink(occupationName);
			cbx.click();
			webDriver.findElement(By.name("Delete")).click();
			WindowSwitcher.confirmDialog(webDriver, true);
			webDriver.switchTo().window(mainWindow);
			WebElement errorFile = WaitElementPresent.isExist(webDriver, By.partialLinkText("Occupation_DeleteFailures_"), 3);
			ScreenCaptureUtils.captureByDriver(imageEntity);
			if(errorFile != null){
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
			throw new TestException("Not find the occupation:" + occupationName);
		}
		return message;
	}
}
