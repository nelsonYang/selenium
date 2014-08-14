package com.freeborders.base.utils.administration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.ActivityData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WindowSwitcher;

public class ActivityCodeUtils {
	
    public static String editActivityCode(WebDriver webDriver,ActivityData activityData, TestImageEntity imageEntity){
    	String message = null;
    	String mainWindow = null;
    	String locationWindow = null;
    	SubMenuSelector.administrationSubMenu(AdministrationPage.ACTIVITY_CODES);
    	WebElement nameLink = TableElementFinder.findElementByName(webDriver, activityData.getOriginalName(), TableElementFinder.SEARCHBYALINK, 3);
		nameLink.click();
		mainWindow = webDriver.getWindowHandle();
		locationWindow = WindowSwitcher.switchToWindow();
		WaitElementPresent.waitElement(webDriver, 5, By.name("SaveChange"));
		if(activityData.getActivityId() != null) {
			WebElement activityId = webDriver.findElement(By.name("data.activityId"));
			activityId.clear();
			activityId.sendKeys(activityData.getActivityId());
		}
		if(activityData.getName() != null) {
			WebElement activityName = webDriver.findElement(By.name("data.name"));
			activityName.clear();
			activityName.sendKeys(activityData.getName());
		}
		Selector.select(webDriver, By.name("data.activityGroupId"), activityData.getActivityGroupName());
		if(activityData.getLedesName() != null) {
			WebElement ledesName = webDriver.findElement(By.name("data.ledesName"));
			ledesName.clear();
			ledesName.sendKeys(activityData.getLedesName());
		}
		
		List<WebElement> validForFees = webDriver.findElements(By.name("data.validForFees"));
		if(activityData.getValidForFees() == 1 && !validForFees.get(0).isSelected()) {
			validForFees.get(0).click();
		}
		if(activityData.getValidForExpenses() == 1 && !validForFees.get(1).isSelected()) {
			validForFees.get(1).click();
		}
		
		WebElement active = webDriver.findElement(By.name("data.active"));
    	if(activityData.getActive() == 1 && !active.isSelected()){
    		active.click();
    	}
    	if(activityData.getActive() == -1 && active.isSelected()) {
    		active.click();
    	}
    	
    	WebElement quantityRequired = webDriver.findElement(By.name("data.quantityRequired"));
    	if(activityData.getQuantityRequired() == 1 && !quantityRequired.isSelected()) {
    		quantityRequired.click();
    	}
    	if(activityData.getQuantityRequired() == -1 && quantityRequired.isSelected()) {
    		quantityRequired.click();
    	}
    	WebElement travelRelated = webDriver.findElement(By.name("data.travelRelated"));
    	if(activityData.getTravelRelated() == 1 && !travelRelated.isSelected()) {
    		travelRelated.click();
    	}
    	if(activityData.getTravelRelated() == -1 && travelRelated.isSelected()) {
    		travelRelated.click();
    	}
    	
    	WebElement commentsRequired = webDriver.findElement(By.name("data.commentsRequired"));
    	if(activityData.getCommentsRequired() == 1 && !commentsRequired.isSelected()) {
    		commentsRequired.click();
    	}
    	if(activityData.getCommentsRequired() == -1 && commentsRequired.isSelected()) {
    		commentsRequired.click();
    	}
    	WebElement seniorStaffAllow = webDriver.findElement(By.name("data.seniorStaffAllowed"));
    	if(activityData.getSeniorStaffAllowed() == 1 && !seniorStaffAllow.isSelected()) {
    		seniorStaffAllow.click();
    	}
    	if(activityData.getSeniorStaffAllowed() == -1 && seniorStaffAllow.isSelected()) {
    		seniorStaffAllow.click();
    	}
    	
    	WebElement settledCase = webDriver.findElement(By.name("data.validForSettledCase"));
    	if(activityData.getValidForSettledCase() == 1 && !settledCase.isSelected()){
    		settledCase.click();
    	}
    	if(activityData.getValidForSettledCase() == -1 && settledCase.isSelected()){
    		settledCase.click();
    	}
    	WebElement validForPost58 = webDriver.findElement(By.name("data.validForPost58"));
    	if(activityData.getValidForPost58() == 1 && !validForPost58.isSelected()){
    		validForPost58.click();
    	}
    	if(activityData.getValidForPost58() == -1 && validForPost58.isSelected()){
    		validForPost58.click();
    	}
    	ScreenCaptureUtils.captureByDriver(imageEntity);
    	WaitElementPresent.waitFindElement(webDriver, 3, By.name("SaveChange")).click();
    	if(WindowSwitcher.isExists(webDriver, locationWindow)) {
    		message = WaitElementPresent.getErrorMsg(webDriver);
    	}else {
    	    webDriver.switchTo().window(mainWindow);
    	}
    	return message;
    }
    
    public static String deleteActivityCode(WebDriver webDriver, String activityDescription, TestImageEntity imageEntity) {
    	String message = null;
    	String mainWindow = null;
    	String locationWindow = null;
    	SubMenuSelector.administrationSubMenu(AdministrationPage.ACTIVITY_CODES);
		WebElement activityLink2 = TableElementFinder.findElementByName(webDriver, activityDescription, TableElementFinder.SEARCHBYALINK, 3);
		WebElement cbx2 = activityLink2.findElement(By.xpath("../..")).findElement(By.id("cbxValue"));
		cbx2.click();
		webDriver.findElement(By.name("Delete")).click();
		WindowSwitcher.confirmDialog(webDriver, true);
		WebElement errorFile = WaitElementPresent.isExist(webDriver, By.partialLinkText("ActivityCode_DeleteFailures_"), 5);
		if(errorFile != null) {
			ScreenCaptureUtils.captureByDriver(imageEntity);
			errorFile.click();
			mainWindow = webDriver.getWindowHandle();
			locationWindow = WindowSwitcher.switchToWindow();
			WaitElementPresent.waitElement(webDriver, 5, By.tagName("pre"));
			ScreenCaptureUtils.captureByDriver(imageEntity);
			WindowSwitcher.checkAndCloseWindow(webDriver, locationWindow);
			webDriver.switchTo().window(mainWindow);
		}else {
			message= WaitElementPresent.getErrorMsg(webDriver);
		}
    	return message;
    }
}
