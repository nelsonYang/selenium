package com.freeborders.base.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextInputUtils {
	public static void editText(WebDriver webDriver, By by, String text) {
		if (text == null)
			return;
		WebElement wEle = WaitElementPresent.waitFindElement(webDriver, by);
		wEle.clear();
		wEle.sendKeys(text);
	}

	public static void editText(WebDriver webDriver, WebElement input, String text) {
		if (text == null)
			return;
		input.clear();
		input.sendKeys(text);
	}

	public static void appendText(WebDriver webDriver, By by, String text) {
		WebElement wEle = WaitElementPresent.waitFindElement(webDriver, by);
		wEle.sendKeys(text);
	}

	public static void editTextByRobot(WebDriver webDriver, By input, String text) {
		WebElement box = webDriver.findElement(input);
		editTextByRobot(webDriver, box, text);
	}

	/**
	 * very unstable
	 * 
	 * @param webDriver
	 * @param input
	 * @param text
	 */
	public static void editTextByRobot(WebDriver webDriver, WebElement input, String text) {
		if (text == null) {
			return;
		}
		while (true) {
			input.sendKeys("");
			if (input.equals(webDriver.switchTo().activeElement())) {
				break;
			}
		}
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input.sendKeys(text);
		input.findElement(By.xpath("..")).click();
	}
}
