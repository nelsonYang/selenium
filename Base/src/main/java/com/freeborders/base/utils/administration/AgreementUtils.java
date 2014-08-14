package com.freeborders.base.utils.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.freeborders.base.entity.administration.AgreementData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.CalendarSelector;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.TableElementFinder;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WaitTimeoutUtils;
import com.freeborders.base.utils.WindowSwitcher;

public class AgreementUtils {

	public static void deleteAgreement(WebDriver webDriver, String AgreementName) {
		SubMenuSelector.administrationSubMenu(AdministrationPage.AGREEMENTS);
		WebElement td = TableElementFinder.findElementByName(webDriver, AgreementName, TableElementFinder.SEARCHBYCSS,
				1);
		WebElement tr = TableElementFinder.getParentElement(td, "../..");
		tr.findElement(By.id("cbxValue")).click();
		webDriver.findElement(By.xpath("//input[@value='Delete']")).click();
		WindowSwitcher.confirmDialog(webDriver, true);
	}

	/**
	 * if duplicate ,dialog will not be closed,
	 * 
	 * @param webDriver
	 * @param data
	 * @return error message,null if succeed
	 */
	public static String createAgreement(WebDriver webDriver, AgreementData data) {
		SubMenuSelector.administrationSubMenu(AdministrationPage.AGREEMENTS);
		String message = null;
		WaitElementPresent.waitFindElement(webDriver, 5, By.xpath("//input[@value='New']")).click();
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		if (data.getName() != null) {
			webDriver.findElement(By.name("data.name")).sendKeys(data.getName());
		}
		if (data.getEffectiveDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getEffectiveDate(),
					By.name("data.effectiveDate"));
		}
		if (data.getExpirationDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getExpirationDate(),
					By.name("data.expirationDate"));
		}
		if (data.getAgreementStatusName() != null) {
			Selector.select(webDriver, By.name("data.agreementStatusId"), data.getAgreementStatusName());
		}
		if (data.getNaitionalPlaintiffAttorney() != null) {
			Selector.select(webDriver, By.name("data.naitionalPlaintiffAttorney"), data.getNaitionalPlaintiffAttorney());
		}
		if (data.getSignedDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getSignedDate(), By.name("data.signedDate"));
		}
		if (data.getAcknowledgeTime() != null) {
			webDriver.findElement(By.name("data.acknowledgeTime")).sendKeys(data.getAcknowledgeTime());
		}
		if (data.getResponseTime() != null) {
			webDriver.findElement(By.name("data.responseTime")).sendKeys(data.getResponseTime());
		}
		if (data.getPaymentTime() != null) {
			webDriver.findElement(By.name("data.paymentTime")).sendKeys(data.getPaymentTime());
		}
		if (data.getFlowCapQty() != null) {
			webDriver.findElement(By.name("data.flowCapQty")).sendKeys(data.getFlowCapQty());
		}
		if (data.getFlowCapPeriod() != null) {
			webDriver.findElement(By.name("data.flowCapPeriod")).sendKeys(data.getFlowCapPeriod());
		}
		if (data.getNegotiaingAttorneyId() != null) {
			webDriver.findElement(By.name("data.negotiaingAttorneyId")).sendKeys(data.getNegotiaingAttorneyId());
		}
		if (data.getBeginExpDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getBeginExpDate(),
					By.name("data.beginExpDate"));
		}
		if (data.getEndExpDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getEndExpDate(), By.name("data.endExpDate"));
		}
		if (data.getAgreementText() != null) {
			webDriver.findElement(By.name("data.agreementText")).sendKeys(data.getAgreementText());
		}
		if (data.getAttorneys() != null) {
			for (String attor : data.getAttorneys()) {
				Selector.select(webDriver, By.name("attorns"), attor);
				webDriver.findElement(By.id("but_addAttorney")).click();
			}
		}
		if (data.getOIstates() != null) {
			for (String state : data.getOIstates()) {
				Selector.select(webDriver, By.name("oiState"), state);
				webDriver.findElement(By.id("but_addOIState")).click();
			}
		}
		if (data.isApprovedSites()) {
			webDriver.findElement(By.name("data.approvedSites")).click();
		}
		if (data.isLatencyPeriodRequired()) {
			webDriver.findElement(By.name("data.latencyPeriodRequired")).click();
		}
		if (data.isPathologyDiagnosis()) {
			webDriver.findElement(By.name("data.pathologyDiagnosis")).click();
		}
		if (data.isPftsRequired()) {
			webDriver.findElement(By.name("data.pftsRequired")).click();
		}
		if (data.isXrayQualityRequired()) {
			webDriver.findElement(By.name("data.xrayQualityRequired")).click();
		}
		if (data.isIloRequired()) {
			webDriver.findElement(By.name("data.iloRequired")).click();
		}
		// click save
		webDriver.findElement(By.id("Save")).click();
		WaitTimeoutUtils.sleep(1);
		if (WindowSwitcher.isExists(webDriver, locationWindow)) {
			message = WaitElementPresent.waitFindElement(webDriver, 3, By.id("errlabel")).getText();
		}
		return message;
	}

	
	public static String editAgreement(WebDriver webDriver, AgreementData data,String name) {
		SubMenuSelector.administrationSubMenu(AdministrationPage.AGREEMENTS);
		String message = null;
		TableElementFinder.findElementByName(webDriver, name, TableElementFinder.SEARCHBYCSS, 1).click();
		String locationWindow = WindowSwitcher.switchToWindow();
		if (data.getName() != null) {
			webDriver.findElement(By.name("data.name")).clear();
			webDriver.findElement(By.name("data.name")).sendKeys(data.getName());
		}
		if (data.getEffectiveDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getEffectiveDate(),
					By.name("data.effectiveDate"));
		}
		if (data.getExpirationDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getExpirationDate(),
					By.name("data.expirationDate"));
		}
		if (data.getAgreementStatusName() != null) {
			Selector.select(webDriver, By.name("data.agreementStatusId"), data.getAgreementStatusName());
		}
		if (data.getNaitionalPlaintiffAttorney() != null) {
			Selector.select(webDriver, By.name("data.naitionalPlaintiffAttorney"), data.getNaitionalPlaintiffAttorney());
		}
		if (data.getSignedDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getSignedDate(), By.name("data.signedDate"));
		}
		if (data.getAcknowledgeTime() != null) {
			webDriver.findElement(By.name("data.acknowledgeTime")).clear();
			webDriver.findElement(By.name("data.acknowledgeTime")).sendKeys(data.getAcknowledgeTime());
		}
		if (data.getResponseTime() != null) {
			webDriver.findElement(By.name("data.responseTime")).clear();
			webDriver.findElement(By.name("data.responseTime")).sendKeys(data.getResponseTime());
		}
		if (data.getPaymentTime() != null) {
			webDriver.findElement(By.name("data.paymentTime")).clear();
			webDriver.findElement(By.name("data.paymentTime")).sendKeys(data.getPaymentTime());
		}
		if (data.getFlowCapQty() != null) {
			webDriver.findElement(By.name("data.flowCapQty")).clear();
			webDriver.findElement(By.name("data.flowCapQty")).sendKeys(data.getFlowCapQty());
		}
		if (data.getFlowCapPeriod() != null) {
			webDriver.findElement(By.name("data.flowCapPeriod")).clear();
			webDriver.findElement(By.name("data.flowCapPeriod")).sendKeys(data.getFlowCapPeriod());
		}
		if (data.getNegotiaingAttorneyId() != null) {
			webDriver.findElement(By.name("data.negotiaingAttorneyId")).clear();
			webDriver.findElement(By.name("data.negotiaingAttorneyId")).sendKeys(data.getNegotiaingAttorneyId());
		}
		if (data.getBeginExpDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getBeginExpDate(),
					By.name("data.beginExpDate"));
		}
		if (data.getEndExpDate() != null) {
			CalendarSelector.imitateClick(webDriver, locationWindow, data.getEndExpDate(), By.name("data.endExpDate"));
		}
		if (data.getAgreementText() != null) {
			webDriver.findElement(By.name("data.agreementText")).clear();
			webDriver.findElement(By.name("data.agreementText")).sendKeys(data.getAgreementText());
		}
		if (data.getAttorneys() != null) {
			new Select(webDriver.findElement(By.name("attorneyIds"))).deselectAll();
			for (String attor : data.getAttorneys()) {
				Selector.select(webDriver, By.name("attorns"), attor);
				webDriver.findElement(By.id("but_addAttorney")).click();
			}
		}
		if (data.getOIstates() != null) {
			new Select(webDriver.findElement(By.name("statesIds"))).deselectAll();
			for (String state : data.getOIstates()) {
				Selector.select(webDriver, By.name("oiState"), state);
				webDriver.findElement(By.id("but_addOIState")).click();
			}
		}
		WebElement sites = webDriver.findElement(By.name("data.approvedSites"));
		if (!data.isApprovedSites() && sites.isSelected()) {
			sites.click();
		}
		WebElement lant = webDriver.findElement(By.name("data.latencyPeriodRequired"));
		if (!data.isLatencyPeriodRequired() && lant.isSelected()) {
			lant.click();
		}
		WebElement diagno = webDriver.findElement(By.name("data.pathologyDiagnosis"));
		if (!data.isPathologyDiagnosis() && diagno.isSelected()) {
			diagno.click();
		}
		WebElement pfts = webDriver.findElement(By.name("data.pftsRequired"));
		if (!data.isPftsRequired() && pfts.isSelected()) {
			pfts.click();
		}
		WebElement xray = webDriver.findElement(By.name("data.xrayQualityRequired"));
		if (!data.isXrayQualityRequired() && xray.isSelected()) {
			xray.click();
		}
		WebElement ilo = webDriver.findElement(By.name("data.iloRequired"));
		if (!data.isIloRequired() && ilo.isSelected()) {
			ilo.click();
		}
		// click save
		webDriver.findElement(By.id("Save")).click();
		WaitTimeoutUtils.sleep(1);
		if (WindowSwitcher.isExists(webDriver, locationWindow)) {
			message = WaitElementPresent.waitFindElement(webDriver, 3, By.id("errlabel")).getText();
		}
		return message;
	}
	
}
