package com.freeborders.base.utils.administration;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.freeborders.base.entity.TestImageEntity;
import com.freeborders.base.entity.administration.DefenseCounselData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.exception.TestException;
import com.freeborders.base.utils.ScreenCaptureUtils;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WindowSwitcher;

public class VendorUtils {
  public static String createVendor(WebDriver webDriver, DefenseCounselData vendorData,TestImageEntity imageEntity) {
	  String message = null;
	  String mainWindow = null;
	  String locationWindow = null;
	  SubMenuSelector.administrationSubMenu(AdministrationPage.VENDORS);
	  WaitElementPresent.waitFindElement(webDriver, 4, By.name("New")).click();
	  mainWindow = webDriver.getWindowHandle();
	  locationWindow = WindowSwitcher.switchToWindow();
	  if(vendorData.getName() != null) {
		  WebElement name = webDriver.findElement(By.name("data.name"));
		  name.sendKeys(vendorData.getName());
	  }
	  if(vendorData.getAddress1() != null){
		  WebElement address1 = webDriver.findElement(By.name("data.address1"));
		  address1.sendKeys(vendorData.getAddress1());
	  }
	  if(vendorData.getAddress2() != null){
		  WebElement address2 = webDriver.findElement(By.name("data.address2"));
		  address2.sendKeys(vendorData.getAddress2());
	  }
	  if(vendorData.getCity() != null) {
		  WebElement city = webDriver.findElement(By.name("data.city"));
		  city.sendKeys(vendorData.getCity());
	  }
	  
	  if(vendorData.getStateName() != null){
		  new Select(webDriver.findElement(By.name("data.stateId"))).selectByVisibleText(vendorData.getStateName());
	  }
	  if(vendorData.getPhone1() != null) {
		  WebElement phone1 = webDriver.findElement(By.name("data.phone1"));
		  phone1.sendKeys(vendorData.getPhone1());
	  }
	  if(vendorData.getPhone2() != null) {
		  WebElement phone2 = webDriver.findElement(By.name("data.phone2"));
		  phone2.sendKeys(vendorData.getPhone2());
	  }
	  if(vendorData.getPhone3() != null) {
		  WebElement phone3 = webDriver.findElement(By.name("data.phone3"));
		  phone3.sendKeys(vendorData.getPhone3());
	  }
	  if(vendorData.getFax1() != null) {
		  WebElement fax1 = webDriver.findElement(By.name("data.fax1"));
		  fax1.sendKeys(vendorData.getFax1());
	  }
	  if(vendorData.getFax2() != null) {
		  WebElement fax2 = webDriver.findElement(By.name("data.fax2"));
		  fax2.sendKeys(vendorData.getFax2());
	  }
	  if(vendorData.getFax3() != null) {
		  WebElement fax3 = webDriver.findElement(By.name("data.fax3"));
		  fax3.sendKeys(vendorData.getFax3());
	  }
	  if(vendorData.getZipCode() != null) {
		  webDriver.findElement(By.name("data.zipCode")).sendKeys(vendorData.getZipCode());
	  }
	  if(vendorData.getTaxId() != null) {
		  webDriver.findElement(By.name("data.taxId")).sendKeys(vendorData.getTaxId());
	  }
	  if(vendorData.getAccountsPayableId() != null) {
		  webDriver.findElement(By.name("data.accountsPayableId")).sendKeys(vendorData.getAccountsPayableId());
	  }
	  if(vendorData.getEmail() != null) {
		  webDriver.findElement(By.name("data.email")).sendKeys(vendorData.getEmail());
	  }
	  WebElement active = webDriver.findElement(By.name("data.active"));
	  if(!active.isSelected()) {
		  if(vendorData.isActive()) {
			  active.click();
		  }
	  } else {
		  if(!vendorData.isActive()) {
			  active.click();
		  }
	  }
	  
	  WebElement makeDC = webDriver.findElement(By.name("data.processAsVendor"));
	  if(!makeDC.isSelected()) {
		  if(vendorData.isProcessAsVendor()) {
			  makeDC.click();
		  }
	  }else {
		  if(!vendorData.isProcessAsVendor()) {
			  makeDC.click();
		  }
	  }
	  ScreenCaptureUtils.captureByDriver(imageEntity);
	  webDriver.findElement(By.name("Save")).click();
	  
	  if(WindowSwitcher.isExists(webDriver, locationWindow)) {
		  WebElement errLabel = WaitElementPresent.waitFindElement(webDriver, 5, By.id("errlabel"));
		  if(errLabel != null) {
			  message = errLabel.getText();
		  }
	  }
	  return message;
  }

  public static String editVendors(WebDriver webDriver, DefenseCounselData vendorData, TestImageEntity imageEntity) {
	  String message = null;
	  String mainWindow = null;
	  String locationWindow = null;
	  List<WebElement> vendorList = VendorUtils.searchVendorsByName(webDriver,vendorData.getOriginalVendorName());
		if (!vendorList.isEmpty()) {
			vendorList.get(0).click();
			mainWindow = webDriver.getWindowHandle();
			locationWindow = WindowSwitcher.switchToWindow();
			if (vendorData.getName() != null) {
				WebElement name = webDriver.findElement(By.name("data.name"));
				name.clear();
				name.sendKeys(vendorData.getName());
			}
			if (vendorData.getAddress1() != null) {
				WebElement address1 = webDriver.findElement(By.name("data.address1"));
				address1.clear();
				address1.sendKeys(vendorData.getAddress1());
			}
			if (vendorData.getAddress2() != null) {
				WebElement address2 = webDriver.findElement(By.name("data.address2"));
				address2.clear();
				address2.sendKeys(vendorData.getAddress2());
			}
			if (vendorData.getCity() != null) {
				WebElement city = webDriver.findElement(By.name("data.city"));
				city.clear();
				city.sendKeys(vendorData.getCity());
			}

			if (vendorData.getStateName() != null) {
				new Select(webDriver.findElement(By.name("data.stateId"))).selectByVisibleText(vendorData.getStateName());
			}
			if (vendorData.getPhone1() != null) {
				WebElement phone1 = webDriver.findElement(By.name("data.phone1"));
				phone1.clear();
				phone1.sendKeys(vendorData.getPhone1());
			}
			if (vendorData.getPhone2() != null) {
				WebElement phone2 = webDriver.findElement(By.name("data.phone2"));
				phone2.clear();
				phone2.sendKeys(vendorData.getPhone2());
			}
			if (vendorData.getPhone3() != null) {
				WebElement phone3 = webDriver.findElement(By.name("data.phone3"));
				phone3.clear();
				phone3.sendKeys(vendorData.getPhone3());
			}
			if (vendorData.getFax1() != null) {
				WebElement fax1 = webDriver.findElement(By.name("data.fax1"));
				fax1.clear();
				fax1.sendKeys(vendorData.getFax1());
			}
			if (vendorData.getFax2() != null) {
				WebElement fax2 = webDriver.findElement(By.name("data.fax2"));
				fax2.clear();
				fax2.sendKeys(vendorData.getFax2());
			}
			if (vendorData.getFax3() != null) {
				WebElement fax3 = webDriver.findElement(By.name("data.fax3"));
				fax3.clear();
				fax3.sendKeys(vendorData.getFax3());
			}
			if (vendorData.getZipCode() != null) {
				WebElement zipCode = webDriver.findElement(By.name("data.zipCode"));
				zipCode.clear();
				zipCode.sendKeys(vendorData.getZipCode());
			}
			if (vendorData.getTaxId() != null) {
				WebElement taxId = webDriver.findElement(By.name("data.taxId"));
				taxId.clear();
				taxId.sendKeys(vendorData.getTaxId());
			}
			if (vendorData.getAccountsPayableId() != null) {
				WebElement accountPayableId = webDriver.findElement(By.name("data.accountsPayableId"));
				accountPayableId.clear();
				accountPayableId.sendKeys(vendorData.getAccountsPayableId());
			}
			if (vendorData.getEmail() != null) {
				WebElement email = webDriver.findElement(By.name("data.email"));
				email.clear();
				email.sendKeys(vendorData.getEmail());
			}
			WebElement active = webDriver.findElement(By.name("data.active"));
			if (!active.isSelected()) {
				if (vendorData.isActive()) {
					active.click();
				}
			} else {
				if (!vendorData.isActive()) {
					active.click();
				}
			}

			WebElement makeDC = webDriver.findElement(By.name("data.processAsVendor"));
			if (!makeDC.isSelected()) {
				if (vendorData.isProcessAsVendor()) {
					makeDC.click();
				}
			} else {
				if (!vendorData.isProcessAsVendor()) {
					makeDC.click();
				}
			}
			ScreenCaptureUtils.captureByDriver(imageEntity);
			webDriver.findElement(By.name("Save")).click();

			if (WindowSwitcher.isExists(webDriver, locationWindow)) {
				WebElement errLabel = WaitElementPresent.waitFindElement(webDriver, 2, By.id("errlabel"));
				if (errLabel != null) {
					message = errLabel.getText();
				}
			}
		} else {
			throw new TestException("Can't find Vendor:"
					+ vendorData.getOriginalVendorName());
		}
		return message;
  }
  
  public static List<WebElement> searchVendorsByName(WebDriver webDriver, String vendorName) {
	  List<WebElement> vendorList = null;
	  SubMenuSelector.administrationSubMenu(AdministrationPage.VENDORS);
	  WaitElementPresent.waitFindElement(webDriver, 3, By.name("findVendors")).sendKeys(vendorName);
	  webDriver.findElement(By.name("popButton")).click();
	  vendorList = WaitElementPresent.waitFindElements(webDriver, 3, By.xpath("//tr[@class='generaltable']/td/a"));
	  return vendorList;
  }
  
  public static WebElement deleteVendor(WebDriver webDriver, String vendorName) {
	  WebElement element = null;
	  List<WebElement> dcList = DefenseCounselUtils.findDCsByName(webDriver,
				vendorName);
		if (!dcList.isEmpty()) {
			String mainWindow = webDriver.getWindowHandle();
			WebElement cbx1 = dcList.get(0).findElement(By.xpath("../.."))
					.findElement(By.id("cbxValue"));
			cbx1.click();
			webDriver.findElement(By.name("Delete")).click();
			WindowSwitcher.confirmDialog(webDriver, true);
			webDriver.switchTo().window(mainWindow);
			WebElement errorLink = WaitElementPresent.isExist(webDriver, By.partialLinkText("DefenseCounsel_DeleteFailures_"), 5);
			if(errorLink != null){
				element = errorLink;
			}else {
				element =  WaitElementPresent.waitFindElement(webDriver, 3, By.id("errlabel"));
			}
			}
		return element;
  }
}
