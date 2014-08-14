package com.freeborders.base.utils;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.enumeration.BillingPage;
import com.freeborders.base.enumeration.CasesPage;
import com.freeborders.base.enumeration.ModelNameEnum;
import com.freeborders.base.enumeration.ToolsPage;
import com.freeborders.base.exception.TestException;

/**
 * 
 * @author nelson
 */
public class SubMenuSelector {
	static Log log = LogFactory.getLog(SubMenuSelector.class);

	private SubMenuSelector() {
	}

	/**
	 * select SubMenum
	 * 
	 * @param mainMenum
	 * @param subMainMenum
	 * @author david.lee
	 */
	public static void selectSubMenum(By mainMenum, By subMainMenum) {
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();

		// try {

		Actions builder = new Actions(webDriver);
		// 1. move to main menu and perform
		// final WebElement mainElement =
		// WaitElementPresent.waitCanClick(webDriver,mainMenum,3);
		final WebElement mainElement = webDriver.findElement(mainMenum);

		Action action = builder.moveToElement(mainElement).build();
		action.perform();

		// 2. move to sub menu and perform
		// final WebElement subElement =
		// WaitElementPresent.waitCanClick(webDriver,subMainMenum,3);
		WaitTimeoutUtils.sleep(1);
		final WebElement subElement = webDriver.findElement(subMainMenum);

		builder.moveToElement(subElement).click().perform();
		webDriver.switchTo();
		WaitTimeoutUtils.sleep(0.5);
		WaitElementPresent.waitFindElement(webDriver, By.tagName("table"));
		// } catch (Exception e) {
		//
		// e.printStackTrace();
		// }

	}

	/**
	 * In the menu Tools >> Change Database, select the given freeze database in the database list.
	 * 
	 * @param webDriver
	 * @param subMenuOfDatabase
	 *            (Id of freeze databse. example,freeze database:May 20, 2013,its menu id is
	 *            "frameHeadermenuBar_2_9_8")
	 */
	public static void selectFreezeDatabase(WebDriver webDriver, By subMenuOfDatabase) {
		// 1. Click Tools->Change Database->May 20, 2013.
		Actions builder = new Actions(webDriver);
		WebElement mainElement = webDriver.findElement(By.id("frameHeadermenuBar_2"));
		Action action = builder.moveToElement(mainElement).build();
		action.perform();
		WaitTimeoutUtils.sleep(2);
		WebElement subElement = webDriver.findElement(By.id("frameHeadermenuBar_2_9"));
		builder.moveToElement(subElement).build().perform();
		// move to the second sub-menu
		WebElement subElementM = webDriver.findElement(By.id("frameHeadermenuBar_2_9M"));
		Action action2 = builder.moveToElement(subElementM).build();
		action2.perform();
		WaitTimeoutUtils.sleep(2);
		builder.moveToElement(webDriver.findElement(subMenuOfDatabase)).click().perform();
		webDriver.switchTo();
		//wait till load
		WaitTimeoutUtils.sleep(0.5);
		WaitElementPresent.waitFindElement(webDriver, By.tagName("table"));
	}

	/**
	 * select freeze database by name
	 * 
	 * @param webDriver
	 * @param dataBaseName
	 */
	public static void selectFreezeDatabase(WebDriver webDriver, String dataBaseName) {
		Actions builder = new Actions(webDriver);
		WebElement mainElement = webDriver.findElement(By.id("frameHeadermenuBar_2"));
		Action action = builder.moveToElement(mainElement).build();
		action.perform();
		WaitTimeoutUtils.sleep(1);
		WebElement subElement = webDriver.findElement(By.id("frameHeadermenuBar_2_9"));
		builder.moveToElement(subElement).build().perform();
		// move to the second sub-menu
		WebElement subElementM = webDriver.findElement(By.id("frameHeadermenuBar_2_9M"));
		Action action2 = builder.moveToElement(subElementM).build();
		action2.perform();
		WaitTimeoutUtils.sleep(1);
		List<WebElement> tds = WaitElementPresent.waitFindElement(webDriver, By.id("frameHeadermenuBar_2_9M"))
				.findElements(By.tagName("td"));
		boolean flag = false;
		for (WebElement base : tds) {
			log.info(" database " + base.getText());
			if (base.getText().equals(dataBaseName)) {
				builder.moveToElement(base).click().perform();
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new TestException("database name " + dataBaseName + " can't be found");
		}
		webDriver.switchTo();
		// wait till load
		WaitTimeoutUtils.sleep(0.5);
		WaitElementPresent.waitFindElement(webDriver, By.tagName("table"));
	}

	/**
	 * mouse over the selected main menu
	 * 
	 * @param webDriver
	 * @param mainMenu
	 *            (id of main menu)
	 */
	public static void mouseOverMainMenu(WebDriver webDriver, By mainMenu) {
		Actions builder = new Actions(webDriver);
		WebElement menuElement = webDriver.findElement(mainMenu);
		Action action = builder.moveToElement(menuElement).build();
		action.perform();
		WaitTimeoutUtils.sleep(1);
	}

	/**
	 * mouse over the selected main menu
	 * 
	 * @param webDriver
	 * @param mainMenu
	 *            (model name enum)
	 */
	public static void mouseOverMainMenu(WebDriver webDriver, ModelNameEnum model) {
		Actions builder = new Actions(webDriver);
		By mainMenu = null;
		switch (model) {
		case ADMINISTRATION:
			mainMenu = By.id("frameHeadermenuBar_5");
			break;
		case REPORTS:
			mainMenu = By.id("frameHeadermenuBar_4");
			break;
		case BILLING:
			mainMenu = By.id("frameHeadermenuBar_3");
			break;
		case TOOLS:
			mainMenu = By.id("frameHeadermenuBar_2");
			break;
		case CASES:
			mainMenu = By.id("frameHeadermenuBar_1");
			break;
		case HOME:
			mainMenu = By.id("frameHeadermenuBar_0");
			break;
		}
		WebElement menuElement = webDriver.findElement(mainMenu);
		Action action = builder.moveToElement(menuElement).build();
		action.perform();
		WaitTimeoutUtils.sleep(1);
	}

	public static void caseSubMenu(CasesPage subMenuNum) {
		switch (subMenuNum) {
		case FIND_CASES:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_1"));
			break;
		case NEW_CASE:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_8"));
			break;
		case NEW_BATCH:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_2"));
			break;
		case CREATE_BATCH_FROM_EXISTING_CASE:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_9"));
			break;
		case BATCHES:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_3"));
			break;
		case PROVISIONAL_CASES:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_5"));
			break;
		case HOT_CASES:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_6"));
			break;
		case DISCOVERY_TRACKER:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_10"));
			break;
		case DISCOVERY_HISTORY_LOAD:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_11"));
			break;
		case PROPERTY_DAMAGE:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_1"), By.id("frameHeadermenuBar_1_7"));
			break;
		}
	}

	public static void toolsSubMenu(ToolsPage subMenuNum) {
		switch (subMenuNum) {
		case LISTS:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_2"), By.id("frameHeadermenuBar_2_1"));
			break;
		case QUERIES:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_2"), By.id("frameHeadermenuBar_2_2"));
			break;
		case SETTLEMENT_GROUPS:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_2"), By.id("frameHeadermenuBar_2_3"));
			break;
		case PAYMENTS:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_2"), By.id("frameHeadermenuBar_2_4"));
			break;
		case BULK_DATA_LOAD:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_2"), By.id("frameHeadermenuBar_2_7"));
			break;
		case CHANGE_DATABASE:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_2"), By.id("frameHeadermenuBar_2_9"));
			break;
		}
	}

	public static void billingSubMenu(BillingPage subMenuNum) {
		switch (subMenuNum) {
		case DEFENSE_COUNSEL_CONTROL_PANEL:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_1"));
			break;
		case DC_INVOICE_APPROVE:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_10"));
			break;
		case DC_INVOICE_VIEW:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_13"));
			break;
		case DC_INVOICE_PAY:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_11"));
			break;
		case DC_INVOICE_RECORD_PAYMENT:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_12"));
			break;
		case DC_IMPORT_FEES_EXPENSES:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_15"));
			break;
		case DCBA_IMPORT_BILLING_HISTORY:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_23"));
			break;
		case VENDOR_CONTROL_PANEL:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_18"));
			break;
		case VENDOR_NEW_INVOICE:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_19"));
			break;
		case VENDOR_INVOICE_REVIEW_APPROVE:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_20"));
			break;
		case VENDOR_INVOICE_PAY:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_21"));
			break;
		case VENDOR_INVOICE_RECORD_PAYMENT:
			SubMenuSelector.selectSubMenum(By.id("frameHeadermenuBar_3"), By.id("frameHeadermenuBar_3_22"));
			break;
		}
	}

	/**
	 * go to the submenu page
	 */
	public static void administrationSubMenu(AdministrationPage subMenuNum) {
		switch (subMenuNum) {
		case PLAINTIFF_ATTORNEYS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_1"));
			break;
		case AGREEMENTS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_2"));
			break;
		case DEFENSE_COUNSEL:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_4"));
			break;
		case NEW_STAFF_APPROVAL:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_5"));
			break;
		case REQUEST_RATE_CHANGE:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_6"));
			break;
		case APPROVE_RATE_CHANGE:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_7"));
			break;
		case APPROVE_NAME_LEDES_CHANGE:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_28"));
			break;
		case VENDORS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_8"));
			break;
		case SPECIAL_PROJECTS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_10"));
			break;
		case ACTIVITY_CODES:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_11"));
			break;
		case OCCUPATION:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_12"));
			break;
		case OI_STATE:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_13"));
			break;
		case LOOKUP_TABLES:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_14"));
			break;
		case SECURITY_ROLES:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_16"));
			break;
		case USERS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_17"));
			break;
		case VIEW_USER_HISTORY:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_18"));
			break;
		case BILL_INVOICE_DELETION_HISTORY:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_27"));
			break;
		case LOGINS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_19"));
			break;
		case MASS_CHANGE_LOG:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_20"));
			break;
		case EXPORT_LOG:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_21"));
			break;
		case OPTIONS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_22"));
			break;
		case USER_PREFERENCES:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_23"));
			break;
		case CASE_EXPORTS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_24"));
			break;
		case IMPORT_SCANNED_DOCUMENTS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_25"));
			break;
		case FREEZE_STATUS:
			selectSubMenum(By.id("frameHeadermenuBar_5"), By.id("frameHeadermenuBar_5_26"));
			break;
		}
	}

}
