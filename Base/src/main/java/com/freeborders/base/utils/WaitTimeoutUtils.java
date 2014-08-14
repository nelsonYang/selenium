package com.freeborders.base.utils;

/**
 * 
 * @author nelson.yang
 */
public class WaitTimeoutUtils {
	private WaitTimeoutUtils() {
	}

	/**
	 * sleep by specified SECONDS,
	 * 
	 * @param timeoutInSeconds
	 *            seconds for sleep
	 */
	public static void sleep(long timeoutInSeconds) {
		try {
			Thread.sleep(timeoutInSeconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static void sleep(double timeoutInSeconds) {
		try {
			long time=Math.round(timeoutInSeconds*1000);
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
