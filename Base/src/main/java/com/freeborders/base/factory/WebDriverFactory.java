package com.freeborders.base.factory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.freeborders.base.enumeration.BrowserType;
import com.freeborders.base.utils.CommandKillProcess;
import com.freeborders.base.utils.ScreenCaptureUtils;

/**
 * 
 * @author nelson.yang
 */
public final class WebDriverFactory {

	private static final WebDriverFactory webDriverFactory = new WebDriverFactory();

	private static final Map<Enum, WebDriver> driverMap = new HashMap<Enum, WebDriver>(8, 1);
	private BrowserType defaultDriver = null;
	private String serverExe;

	public static WebDriverFactory getInstance() {
		return webDriverFactory;
	}

	/**
	 * get a specified web driver
	 * 
	 * @param browserType
	 * @return
	 */
	public synchronized WebDriver getWebDriver(BrowserType browserType) {
		WebDriver webDriver = driverMap.get(browserType);
		if (webDriver == null) {
			webDriver = createWebDriver(browserType);
			driverMap.put(browserType, webDriver);
		}
		return webDriver;
	}

	/**
	 * create a specified new browser driver
	 * 
	 * @param browserType
	 * @return
	 */
	private WebDriver createWebDriver(BrowserType browserType) {
		WebDriver webDriver = null;
		switch (browserType) {
		case IE:
			serverExe = "IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver",
					Thread.currentThread().getContextClassLoader().getResource(serverExe).getPath());
			webDriver = new InternetExplorerDriver();
			break;
		case IE64:
			serverExe = "IEDriverServer64.exe";
			System.setProperty("webdriver.ie.driver",
					Thread.currentThread().getContextClassLoader().getResource("IEDriverServer64.exe").getPath());
			webDriver = new InternetExplorerDriver();
			break;
		case FIRE_FOX:
			webDriver = new FirefoxDriver();
			break;
		case CHROME:
			webDriver = new ChromeDriver();
			break;
		case ANDROID:
			// webDriver = new AndroidDriver();
			break;
		case IPHONE:
			// webDriver = new IPhoneDriver();
			break;
		}
		Window win = webDriver.manage().window();
		win.maximize();
		ScreenCaptureUtils.setMaxBrowser(win.getPosition().x, win.getPosition().getY(), win.getSize().getWidth(), win
				.getSize().getHeight());
		// DesiredCapabilities capability =
		// DesiredCapabilities.internetExplorer();
		// webDriver = new EventFiringWebDriver(new
		// RemoteWebDriver(capability)).register(new WebDriverListener());
		// webDriver = new EventFiringWebDriver(webDriver).register(new
		// WebDriverListener());
		// webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		return webDriver;
	}

	/**
	 * kill default browser
	 */
	public void killDefaultBrowser() {
		WebDriver driver = driverMap.get(defaultDriver);
		Platform plat = ((RemoteWebDriver) driver).getCapabilities().getPlatform();
		CommandKillProcess.killProcessName(plat, getBrowserExe(defaultDriver));
	}

	public void killDefaultServer() {
		WebDriver driver = driverMap.get(defaultDriver);
		Platform plat = ((RemoteWebDriver) driver).getCapabilities().getPlatform();
		CommandKillProcess.killProcessName(plat, serverExe);
	}

	public String getBrowserExe(BrowserType browserType) {
		String bro_exe = null;
		switch (browserType) {
		case IE:
			bro_exe = "iexplore.exe";
			break;
		case IE64:

			break;
		case FIRE_FOX:

			break;
		case CHROME:

			break;
		case ANDROID:

			break;
		case IPHONE:

			break;
		}
		return bro_exe;
	}

	/**
	 * set the default browser type used whole route, default type comes from properties file
	 * 
	 * @param browserType
	 */
	public synchronized void setDefaultDriver(BrowserType browserType) {
		this.defaultDriver = browserType;
	}

	/**
	 * get the predefined default browser driver,specified by properties
	 * 
	 * @return
	 */
	public synchronized WebDriver getDefaultDriver() {
		return getWebDriver(defaultDriver);
	}

	/**
	 * create a new default web driver ,will not be stored in map and closed by system
	 * automatically. remember to close it after using
	 * 
	 * @return
	 */
	public synchronized WebDriver createDefaultWebDriver() {
		return createWebDriver(defaultDriver);
	}

	/**
	 * recreate a new default web driver ,will be stored in map and closed by system automatically.
	 * do not need to close it manually
	 * 
	 * @return
	 */
	public synchronized WebDriver recreateDefaultWebDriver() {
		this.driverMap.put(defaultDriver, createWebDriver(defaultDriver));
		return getDefaultDriver();
	}

}
