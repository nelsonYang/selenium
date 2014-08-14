package com.freeborders.base.utils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * 
 * @author nelson
 */
public class WaitElementPresent {
	public final static int WAIT_TIME_QUICK = 3;
	public final static int WAIT_TIME_SHORT = 5;
	public final static int WAIT_TIME_MEDIUM = 10;

	private WaitElementPresent() {
	}

	public static void waitElement(final WebDriver webDriver, final long timeOutInSeconds, final By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
		wait.until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver input) {
				// if out method doesn't surround try catch,here shall add it
				return input.findElement(by) != null;
			}
		});
	}

	/**
	 * get Element by time wait
	 * 
	 * @param webDriver
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public static WebElement waitFindElement(final WebDriver webDriver, final long timeOutInSeconds, final By by) {
		WebElement e = (new WebDriverWait(webDriver, timeOutInSeconds)).until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(by);
			}
		});

		return e;
	}

	/**
	 * get Elements by time wait
	 * 
	 * @param webDriver
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public static List<WebElement> waitFindElements(final WebDriver webDriver, final long timeOutInSeconds, final By by) {
		List<WebElement> e = (new WebDriverWait(webDriver, timeOutInSeconds))
				.until(new ExpectedCondition<List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver input) {
						// TODO Auto-generated method stub
						return input.findElements(by);
					}
				});
		return e;
	}

	/**
	 * get Elements by a specified element time wait
	 * 
	 * @param webDriver
	 * @param element
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public static List<WebElement> waitFindElementsByElement(final WebDriver webDriver, final WebElement element,
			final long timeOutInSeconds, final By by) {
		List<WebElement> e = (new WebDriverWait(webDriver, timeOutInSeconds))
				.until(new ExpectedCondition<List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver input) {
						// TODO Auto-generated method stub
						return element.findElements(by);
					}
				});
		return e;
	}

	/**
	 * get Elements by a specified element and default time wait
	 * 
	 * @param webDriver
	 * @param element
	 * @param by
	 * @return
	 */
	public static List<WebElement> waitFindElementsByElement(final WebDriver webDriver, final WebElement element,
			final By by) {
		List<WebElement> e = (new WebDriverWait(webDriver, WAIT_TIME_SHORT))
				.until(new ExpectedCondition<List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver input) {
						// TODO Auto-generated method stub
						return element.findElements(by);
					}
				});
		return e;
	}

	/**
	 * get Elements by default time wait
	 * 
	 * @param webDriver
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public static List<WebElement> waitFindElements(final WebDriver webDriver, final By by) {
		List<WebElement> e = (new WebDriverWait(webDriver, WAIT_TIME_SHORT))
				.until(new ExpectedCondition<List<WebElement>>() {
					@Override
					public List<WebElement> apply(WebDriver input) {
						// TODO Auto-generated method stub
						return input.findElements(by);
					}
				});
		return e;
	}

	/**
	 * check element by waiting a period of time ,will not throw exception
	 * 
	 * @param webDriver
	 * @param by
	 * @param timeOutInSeconds
	 * @return null if not exists ,will not throw exception
	 */
	public static WebElement isExist(final WebDriver webDriver, final By by, final long timeOutInSeconds) {
		WebElement e = null;
		try {
			e = waitFindElement(webDriver, timeOutInSeconds, by);
		} catch (org.openqa.selenium.TimeoutException e2) {
			// e2.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		return e;
	}

	/**
	 * immediately check if the defined element exists,will not throw exception
	 * 
	 * @param webDriver
	 * @param by
	 * @return null if not exists ,will not throw exception
	 */
	public static WebElement isExist(final WebDriver webDriver, final By by) {
		WebElement e = null;
		try {
			e = webDriver.findElement(by);
		} catch (org.openqa.selenium.TimeoutException e2) {
			// e2.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}

		return e;
	}

	/**
	 * check multiple elements exist or not
	 * 
	 * @param webDriver
	 * @param by
	 * @return
	 */
	public static List<WebElement> isMutipleExists(final WebDriver webDriver, By by) {
		List<WebElement> e = null;
		try {
			e = webDriver.findElements(by);
		} catch (org.openqa.selenium.TimeoutException e2) {
			// e2.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		return e;
	}

	public static void waitElementByElement(final WebDriver webDriver, final WebElement element,
			final long timeOutInSeconds, final By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return element.findElement(by) != null;
			}
		});
	}

	/**
	 * get Element by wait,
	 * 
	 * @param webDriver
	 * @param element
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public static WebElement waitFindByElement(final WebDriver webDriver, final WebElement element,
			final long timeOutInSeconds, final By by) {
		WebElement e = (new WebDriverWait(webDriver, timeOutInSeconds)).until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return element.findElement(by);
			}
		});
		return e;
	}

	/**
	 * get element by wait ,will not throw exception
	 * 
	 * @param webDriver
	 * @param element
	 * @param timeOutInSeconds
	 * @param by
	 * @return null if not exists
	 */
	public static WebElement isExistByElement(final WebDriver webDriver, final WebElement element,
			final long timeOutInSeconds, final By by) {
		WebElement e = null;
		try {
			e = waitFindByElement(webDriver, element, timeOutInSeconds, by);
		} catch (org.openqa.selenium.TimeoutException e2) {
			// e2.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		return e;
	}

	/**
	 * get Element by element,will not throw exception
	 * 
	 * @param webDriver
	 * @param element
	 * @param by
	 * @return
	 */
	public static WebElement isExistByElement(final WebDriver webDriver, final WebElement element, final By by) {
		WebElement e = null;
		try {
			e = element.findElement(by);
		} catch (org.openqa.selenium.TimeoutException e2) {
			// e2.printStackTrace();
		} catch (org.openqa.selenium.NoSuchElementException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		return e;
	}

	public static void waitTitle(final WebDriver webDriver, final long timeOutInSeconds, final String text) {
		WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
		// wait.ignoring(NoSuchElementException.class);// 一定要加
		wait.until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver input) {
				return text.equals(input.getTitle());// 上面不加这边要加try catch
			}
		});
	}

	/**
	 * get element by polling ,define the interval and time out
	 * 
	 * @param webDriver
	 * @param timeoutInSeconds
	 * @param pollingEveryInSeconds
	 * @param by
	 */
	public static void waitElement(final WebDriver webDriver, final long timeoutInSeconds,
			final long pollingEveryInSeconds, final By by) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver);
		wait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);
		wait.pollingEvery(pollingEveryInSeconds, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);// 加了 wait.pollingEvery(1,
													// TimeUnit.SECONDS);一定要加
		wait.until(new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return input.findElement(by) != null;// 或者在这里加try catch
			}
		});
	}

	/**
	 * wait and find a visible element
	 * 
	 * @param webDriver
	 * @param timeOutInSeconds
	 * @param by
	 * @return visible element
	 */
	public static WebElement waitPresent(final WebDriver webDriver, final long timeOutInSeconds, final By by) {
		WebElement e = (new WebDriverWait(webDriver, timeOutInSeconds)).until(ExpectedConditions
				.visibilityOfElementLocated(by));
		return e;
	}

	/**
	 * wait until an element can be clicked.
	 * 
	 * @param d
	 * @param by
	 * @param timeOutInSeconds
	 * @return
	 * @author david.lee
	 */
	public static WebElement waitCanClick(WebDriver d, By by, long timeOutInSeconds) {

		final int MAX_STALE_ELEMENT_RETRIES = 5;

		WebDriverWait wait = new WebDriverWait(d, timeOutInSeconds);
		int retries = 0;
		while (true) {
			try {
				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
				return element;
			} catch (StaleElementReferenceException e) {
				if (retries < MAX_STALE_ELEMENT_RETRIES) {
					retries++;
					continue;
				} else {
					throw e;
				}
			}
		}
	}

	/**
	 * get Element by time wait
	 * 
	 * @param webDriver
	 * @param timeOutInSeconds
	 * @param by
	 * @return
	 */
	public static WebElement waitFindElement(final WebDriver webDriver, final By by) {
		WebElement e = (new WebDriverWait(webDriver, WAIT_TIME_SHORT)).until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(by);
			}
		});
		return e;
	}
/**
 * sleep for 1 second in case of previous error message collision
 * @param webDriver
 * @return
 */
	public static String getErrorMsg(final WebDriver webDriver) {
		WaitTimeoutUtils.sleep(1);
		return waitFindElement(webDriver, By.id("errlabel")).getText();
	}

}
