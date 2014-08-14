package com.freeborders.base.utils.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.freeborders.base.entity.administration.DeletedHistoryFilterData;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.utils.CalendarSelector;
import com.freeborders.base.utils.RadioSelector;
import com.freeborders.base.utils.Selector;
import com.freeborders.base.utils.SubMenuSelector;
import com.freeborders.base.utils.WaitElementPresent;

public class DeletedHistoryUtils {
    public static void searchFunction(WebDriver webDriver, DeletedHistoryFilterData data) {
    	String mainWindow = null;
    	SubMenuSelector.administrationSubMenu(AdministrationPage.BILL_INVOICE_DELETION_HISTORY);
    	WaitElementPresent.waitElement(webDriver, 3, By.name("Refresh"));
    	Selector.select(webDriver, By.name("filter.vendorId"), data.getVendorName());
    	Selector.select(webDriver, By.name("filter.userId"), data.getUserName());
    	RadioSelector.select(webDriver, By.name("filter.typeId"), data.getTypeName());
    	CalendarSelector.imitateClick(webDriver, mainWindow, data.getStartDate(), By.name("filter.startDate"));
    	CalendarSelector.imitateClick(webDriver, mainWindow, data.getStartDate(), By.name("filter.endDate"));
    	WaitElementPresent.waitFindElement(webDriver, 5, By.name("Refresh")).click();
    }
}
