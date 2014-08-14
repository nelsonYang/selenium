package com.freeborders.base.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.entity.TestImageEntity;

public class PaginationUtils<T> {
	/**
	 * 1.implements this interface
	 */
	public static interface Busi<T> {
		T process();
	}

	Busi<T> affair;

	/**
	 * 2. set that child class here
	 * 
	 * @param bus
	 */
	public void setBusi(Busi<T> bus) {
		affair = bus;
	}

	/**
	 * 3. execute pagination and process ,will break the iterate if return is not null
	 * 
	 * @param webDriver
	 * @return
	 */
	public T iteratePage(WebDriver webDriver) {
		T t = null;
		List<WebElement> pages = WaitElementPresent.waitFindElements(webDriver, By.className("gotoPage"));
		int pps = pages.size() - 1;
		// iterate all pages
		for (int i = 0; i < pps; i++) {
			WebElement nextPage = webDriver.findElement(By.className("contentbgbd")).findElements(By.tagName("span"))
					.get(i + 1);
			nextPage.click();
			t = (T) affair.process();
			if (t != null) {
				return t;
			}
		}
		return t;
	}

	/**
	 * go to a specified page ,1,2,or >,>>, capture a image;
	 * @param webDriver
	 * @param page
	 * @param imageEntity
	 */
	public static void goToPage(WebDriver webDriver, String page, TestImageEntity imageEntity) {
		List<WebElement> pages = WaitElementPresent.waitFindElements(webDriver, By.className("gotoPage"));
		for (WebElement num : pages) {
			if (num.getText().equals(page)) {
				num.click();
				webDriver.switchTo();
				break;
			}
		}
		ScreenCaptureUtils.captureByDriver(imageEntity);
	}
}
