package com.freeborders.base.utils;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadFileUtils {

	/**
	 * return message if file doesn't exist,else null
	 * @param webDriver
	 * @param file
	 * @return
	 */
	public static String checkExists(WebDriver webDriver, String file) {
		File fi = new File(file);
		if (!fi.exists()) {
			return file + " doesn't exist";
		}
		return null;
	}
}
