package com.freeborders.base.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.log.Logger;

/**
 * 
 * @author nelson
 */
public class WindowSwitcher {
	static Log log = LogFactory.getLog(WindowSwitcher.class);
	// include main window
	private static Set<String> lastWindows = new HashSet<String>();

	private WindowSwitcher() {
	}

	// public static String switchToWindow() {
	// Logger.info("WindowSwitcher", "switchToWindow");
	// WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
	// // WaitTimeoutUtils.sleep(5);
	// String locationWindow = null;
	// String mainWindow = webDriver.getWindowHandle();// 一定要加上这句才能获取到两个窗口句柄？？？？
	// Logger.debug("mainWindow", "mainWindow=".concat(mainWindow));
	// String mainTitle = webDriver.getTitle();
	// Logger.debug("mainTitle", "mainTitle=".concat(mainTitle));
	// String popupTitle = mainTitle;
	// // retry five times to swith if still cannot switch then threw an
	// // exception
	// int loopNum = 0;
	// while (true) {
	// loopNum++;
	// Logger.debug("loopNum",
	// "loopNum = ".concat(String.valueOf(loopNum)));
	// if (mainTitle.equals(popupTitle)) {
	// if (loopNum < 5) {
	// mainWindow = webDriver.getWindowHandle();
	// Logger.debug("mainWindow", "mainWindow=".concat(mainWindow));
	// // switch to alert() get the page data
	// Set<String> windows = webDriver.getWindowHandles();
	// int size = windows.size();
	// Logger.debug("window.sizes= ",
	// "window.sizes=".concat(String.valueOf(size)));
	// for (String window : windows) {
	// Logger.debug("window", "window=".concat(window));
	// }
	// locationWindow = (String) windows.toArray()[size - 1];
	// webDriver.switchTo().window(locationWindow);
	// Logger.debug("location window ",
	// "locationwindow=".concat(locationWindow));
	// // popupTitle = webDriver.getTitle();
	// // Logger.debug("window title",
	// // "windowTitle=".concat(popupTitle));
	// WaitTimeoutUtils.sleep(2);
	// } else {
	// locationWindow = null;
	// break;
	// }
	// } else {
	// break;
	// }
	// }
	// return locationWindow;
	// }

	public static String switchToWindow() {
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		// WaitTimeoutUtils.sleep(5);
		String locationWindow = null;
		String mainWindow = webDriver.getWindowHandle();// 一定要加上这句才能获取到两个窗口句柄？？？？
		lastWindows.add(mainWindow);
		int loopNum = 0;
		while (true) {
			loopNum++;
			if (loopNum >= 6 || locationWindow != null)
				break;
			Set<String> windows = webDriver.getWindowHandles();
			windows.removeAll(lastWindows);
			int size = windows.size();
			if (size == 0) {
				WaitTimeoutUtils.sleep(1);
				continue;
			}
			for (String handle : windows) {
				log.info("window=".concat(handle));
				if (!WindowSwitcher.isExists(webDriver, handle)) {
					continue;
				}
				locationWindow = handle;
				break;
			}

		}
		lastWindows = webDriver.getWindowHandles();
		return locationWindow;
	}

	public static WebElement switchToWindow(WebDriver webDriver, By expectation) {
		WebElement expect = null;
		String mainWindow = webDriver.getWindowHandle();
		lastWindows.add(mainWindow);
		String locationWindow = null;
		int loopNum = 0;
		while (true) {
			loopNum++;
			if (loopNum >= 6 || locationWindow != null)
				break;
			// get the right windows
			Set<String> currentWins = webDriver.getWindowHandles();
			currentWins.removeAll(lastWindows);
			Set<String> windows = currentWins;
			int size = windows.size();
			if (size == 0) {
				WaitTimeoutUtils.sleep(2);
				continue;
			}
			for (String handle : windows) {
				if (!WindowSwitcher.isExists(webDriver, handle)) {
					continue;
				}
				locationWindow = handle;
				break;
			}
		}
		// already switched in isExists method
		expect = WaitElementPresent.waitFindElement(webDriver, expectation);
		lastWindows = webDriver.getWindowHandles();
		return expect;
	}

	public static WebElement switchWindow(WebDriver webDriver, By click, By expectation) {
		WebElement expect = null;
		WaitElementPresent.waitFindElement(webDriver, click).click();
		expect = switchToWindow(webDriver, expectation);
		return expect;
	}

	public static WebElement switchWindow(WebDriver webDriver, WebElement click, By expectation) {
		WebElement expect = null;
		click.click();
		expect = switchToWindow(webDriver, expectation);
		return expect;
	}

	public static String switchToWindowByUrl(String url) {
		Logger.info("switchToWindowByUrl", "switchToWindowByUrl");
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		WaitTimeoutUtils.sleep(5);
		String mainWindow = webDriver.getWindowHandle(); // 一定要加上这句才能获取到两个窗口句柄？？？？
		Logger.debug("mainWindow", "mainWindow=".concat(mainWindow));
		String locationWindow = null;
		// switch to alert() get the page data
		Set<String> windows = webDriver.getWindowHandles();
		int size = windows.size();
		Logger.debug("window.sizes= ", "window.sizes=".concat(String.valueOf(size)));
		for (String window : windows) {
			Logger.debug("window", "window=".concat(window));
			try {
				webDriver.switchTo().window(window);
				if (url.equals(webDriver.getCurrentUrl())) {
					locationWindow = window;
					break;
				}
			} catch (Exception ex) {
				Logger.severe("switchToWindowByUrl", ex.getMessage(), ex);
			}
		}
		Logger.debug("location window ", "locationwindow=".concat(locationWindow));
		Logger.debug("window title", "windowTitle=".concat(webDriver.getTitle()));
		return locationWindow;
	}

	public static String switchToWindowByTitle(String title) {
		Logger.info("switchToWindowByTitle", "switchToWindowByTitle");
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		WaitTimeoutUtils.sleep(5);
		String locationWindow = null;
		// switch to alert() get the page data
		String mainWindow = webDriver.getWindowHandle();// 一定要加上这句才能获取到两个窗口句柄？？？？
		Logger.debug("mainWindow", "mainWindow=".concat(mainWindow));
		Set<String> windows = webDriver.getWindowHandles();
		int size = windows.size();
		Logger.debug("window.sizes= ", "window.sizes=".concat(String.valueOf(size)));
		for (String window : windows) {
			Logger.debug("window", "window=".concat(window));
			// try {
			if (!WindowSwitcher.isExists(webDriver, window)) {
				continue;
			}
			if (title.equals(webDriver.getTitle())) {
				locationWindow = window;
				break;
			}
			// } catch (Exception ex) {
			// Logger.severe("switchToWindowByTitle", ex.getMessage(), ex);
			// }
		}
		Logger.debug("location window ", "locationwindow=".concat(locationWindow));
		Logger.debug("window title", "windowTitle=".concat(webDriver.getTitle()));
		return locationWindow;
	}

	/**
	 * detect the window exists or not,switch to it if exists
	 * 
	 * @param webDriver
	 * @param windowHandle
	 * @return false not exists
	 */
	public static boolean isExists(WebDriver webDriver, String windowHandle) {
		Boolean flag = true;
		try {
			if (webDriver.getWindowHandles().contains(windowHandle)) {
				webDriver.switchTo().window(windowHandle);
				// this is used for judge whether this window is visible or not ,invisible will
				// throw
				// exception
				webDriver.getCurrentUrl();
				webDriver.getTitle();
			}
		} catch (NoSuchWindowException e) {
			flag = false;
		} catch (org.openqa.selenium.UnhandledAlertException e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * detect the main window exists or not
	 * 
	 * @param webDriver
	 * @return
	 */
	public static boolean isWindowExists(WebDriver webDriver) {
		Boolean flag = true;
		try {
			// this is used for judge whether this window is visible or not ,invisible will throw
			// exception
			webDriver.getCurrentUrl();
		} catch (NoSuchWindowException e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * Use js to close window, avoid alert dialog when invoke the webDriver.close()
	 * 
	 */
	public static void jsCloseWindow(WebDriver webDriver) {
		JavascriptExecutor js;
		try {
			if (webDriver instanceof JavascriptExecutor) {
				js = (JavascriptExecutor) webDriver;
				js.executeScript("window.close();");
			}
		} catch (NoSuchWindowException e) {
			// window already closed,no need to throw exception
		}
	}

	/**
	 * a convinient method combined isExists() and jsCloseWindow()
	 */
	public static void checkAndCloseWindow(WebDriver webDriver, String windowHandle) {
		if (isExists(webDriver, windowHandle)) {
			jsCloseWindow(webDriver);
		}
	}

	/**
	 * get Log out span ,will not throw exception if not found can be used to check if it is main
	 * window or not
	 * 
	 * @param webDriver
	 * @return
	 */
	public static WebElement getLogOut(WebDriver webDriver) {
		WebElement logOut = null;
		List<WebElement> spans = WaitElementPresent.isMutipleExists(webDriver, By.xpath("//span[@onclick]"));
		if (spans != null) {
			for (WebElement element : spans) {
				if ("Log Out".equals(element.getText())) {
					logOut = element;
					break;
				}
			}
		}
		return logOut;
	}

	/**
	 * close all dialog and back to main window
	 * 
	 * @param webDriver
	 */
	public static void closeAllDialog(WebDriver webDriver) {
		List<String> windows = new ArrayList<String>(webDriver.getWindowHandles());
		String mainWindow = null;

		try {
			if (windows.size() > 1) {
				// on dialog window
				if (WindowSwitcher.getLogOut(webDriver) == null) {
					WindowSwitcher.jsCloseWindow(webDriver);
					for (String handle : windows) {
						// another window
						if (!WindowSwitcher.isExists(webDriver, handle)) {
							continue;
						}
						if (WindowSwitcher.getLogOut(webDriver) != null) {
							mainWindow = handle;
						} else {
							WindowSwitcher.jsCloseWindow(webDriver);
						}
					}
				} else {
					// on main window
					mainWindow = webDriver.getWindowHandle();
					for (String handle : windows) {
						if (!handle.equals(mainWindow)) {
							if (!WindowSwitcher.isExists(webDriver, handle)) {
								continue;
							}
							WindowSwitcher.jsCloseWindow(webDriver);
						}
					}
				}
				webDriver.switchTo().window(mainWindow);
			} else if (!WindowSwitcher.isWindowExists(webDriver)) {
				webDriver.switchTo().window(windows.get(0));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * used for alert dialog
	 * 
	 * @param webDriver
	 * @param yes
	 */
	public static void confirmDialog(WebDriver webDriver, boolean yes) {
		Alert alert = webDriver.switchTo().alert();
		if (yes)
			alert.accept();
		else
			alert.dismiss();
	}

	/**
	 * wait dialog dismiss by a specified time
	 * 
	 * @param webDriver
	 * @param locationWindow
	 * @param seconds
	 */
	public static void waitDialogClose(WebDriver webDriver, String locationWindow, int seconds) {
		long tmill = System.currentTimeMillis();
		while (true) {
			if (!isExists(webDriver, locationWindow)) {
				return;
			}
			long period = System.currentTimeMillis() - tmill;
			if (seconds * 1000 < period) {
				return;
			}
		}
	}

	/**
	 * wait dialog dismiss by a specified time
	 * 
	 * @param webDriver
	 * @param locationWindow
	 * @param seconds
	 */
	public static void waitDialogClose(WebDriver webDriver, String locationWindow) {
		int seconds = WaitElementPresent.WAIT_TIME_QUICK;
		long tmill = System.currentTimeMillis();
		while (true) {
			if (!isExists(webDriver, locationWindow)) {
				return;
			}
			long period = System.currentTimeMillis() - tmill;
			if (seconds * 1000 < period) {
				return;
			}
		}
	}

	/**
	 * jump from one page to another one,
	 * 
	 * @param webDriver
	 * @param click
	 * @param mark
	 * @return the markable web element
	 */
	public static WebElement pageJump(WebDriver webDriver, By click, By mark) {
		return pageJump(webDriver, WaitElementPresent.waitFindElement(webDriver, click), mark);
	}

	public static WebElement pageJump(WebDriver webDriver, WebElement click, By mark) {
		click.click();
		WaitTimeoutUtils.sleep(0.5);
		webDriver.switchTo();
		return WaitElementPresent.waitFindElement(webDriver, 8, mark);
	}
}
