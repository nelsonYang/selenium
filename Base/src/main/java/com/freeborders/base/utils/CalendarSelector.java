package com.freeborders.base.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author nelson.yang
 */
public class CalendarSelector {
	private CalendarSelector() {
	}

	public static void selectToday(final WebDriver webDriver, final By by, final String rootWindow) {
		WebElement serveIcon = webDriver.findElement(by);
		serveIcon.click();
		WebDriver newDri = webDriver.switchTo().frame("meizzCalendarIframe");
		// WaitElementPresent.waitElement(webDriver, 50, By.name("today"));
		// webDriver.findElement(By.name("today")).click();
		WaitElementPresent.waitElement(newDri, 50, By.name("today"));
		newDri.findElement(By.name("today")).click();
		newDri.switchTo().window(rootWindow);
	}

	/**
	 * imitate time controller click event, to a specified time ,
	 * 
	 * @param time
	 *            10/12/2013 MM/DD/yyyy
	 * @param input
	 *            input that time controller put value in
	 */
	public static void imitateClick(WebDriver webDriver, String rootWindow, String time, By input) {
		if (time == null)
			return;
		String[] date = time.split("/");
		String zero = "00";
		date[0] = zero.substring(0, 2 - date[0].length()) + date[0];
		date[1] = zero.substring(0, 2 - date[1].length()) + date[1];
		String year = date[2];
		String month = Integer.parseInt(date[0]) + "";
		String day = year + "-" + date[0] + "-" + date[1];
		WebElement serveIcon = webDriver.findElement(input).findElement(By.xpath("../img"));
		serveIcon.click();
		webDriver = webDriver.switchTo().frame("meizzCalendarIframe");
		// select year
		WaitElementPresent.waitFindElement(webDriver, 10, By.id("meizzYearHead")).click();
		// WaitElementPresent.waitPresent(webDriver, 10, By.name("tmpYearSelect"));
		WebElement element = WaitElementPresent.waitFindElement(webDriver, By.name("tmpYearSelect"));
		Select sel = new Select(element);
		sel.selectByVisibleText(year);
		// WaitTimeoutUtils.sleep(1);
		// select month
		WaitElementPresent.waitFindElement(webDriver, 10, By.id("meizzYearMonth")).click();
		element = WaitElementPresent.waitFindElement(webDriver, By.name("tmpMonthSelect"));
		sel = new Select(element);
		sel.selectByVisibleText(month);
		// select day
		WaitElementPresent.waitFindElement(webDriver, 5, By.xpath("//td[@title='" + day + "']")).click();
		webDriver.switchTo().window(rootWindow);
	}
}
