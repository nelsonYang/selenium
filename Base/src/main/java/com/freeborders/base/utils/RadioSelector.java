package com.freeborders.base.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author nelson.yang
 */
public class RadioSelector {
	private RadioSelector() {
	}

	public static void select(final WebDriver webDriver, final By by, final String value) {
		List<WebElement> webElements = webDriver.findElements(by);
		for (WebElement element : webElements) {
			if (value.equals(element.getAttribute("value"))) {
				element.click();
				break;
			}
		}
	}

	public static void selectCheckbox(WebDriver webDriver, final By by, final boolean check) {
		WebElement checkbox = WaitElementPresent.waitFindElement(webDriver, by);
		if (checkbox.isSelected() && !check) {
			checkbox.click();
		} else if (!checkbox.isSelected() && check) {
			checkbox.click();
		}
	}
	
	public static void selectCheckbox(WebDriver webDriver, final By by, int flag) {
		if(flag==0){
			return;
		}else{
			selectCheckbox(webDriver, by, flag>0);
		}
	}

}
