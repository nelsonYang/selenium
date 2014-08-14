package com.freeborders.base.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.freeborders.base.entity.TestImageEntity;

/**
 * 
 * @author nelson.yang
 */
public class Selector {

	private Selector() {
	}

	public static WebElement select(final WebDriver webDriver, final By by, final int index) {
		if (index < 0)
			return null;
		WebElement element = WaitElementPresent.waitFindElement(webDriver, by);
		Select sel = new Select(element);
		sel.selectByIndex(index);
		return sel.getFirstSelectedOption();
	}

	public static void select(final WebDriver webDriver, final By by, final String text) {
		// WaitTimeoutUtils.sleep(1);
		if (text == null)
			return;
		WebElement element = WaitElementPresent.waitFindElement(webDriver, by);
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
		// return sel.getFirstSelectedOption();
	}

	/**
	 * verify the contents in the drop down list
	 * 
	 * @param webDriver
	 * @param by
	 *            (By id/Name of the drop down menu)
	 * @param expectDropDownText
	 *            (the expected result in the excel file,
	 *            example:dropDownContents=Active,Duplicate,Ended,In Dispute,In Negotiation,In
	 *            Principle,Inactive,Signed;)
	 * @return (if they are equal,return true, else return false)
	 */
	public static boolean verifyDropDownList(WebDriver webDriver, By by, String expectDropDownText) {
		List<WebElement> options = WaitElementPresent.waitFindElement(webDriver, 3, by).findElements(
				By.tagName("option"));
		String[] optionTexts = expectDropDownText.split(",");

		List<String> expectDropDownList = Arrays.asList(optionTexts);
		List<String> dropDownList = new ArrayList<String>();
		// add the option text into list and remove "Select" in the drop down list.
		int j = 0;
		if (options.get(0).getText().trim().equals("Select")) {
			j = 1;
		}
		for (int i = j; i < options.size(); i++) {
			dropDownList.add(options.get(i).getText().trim());
		}
		return compare(dropDownList, expectDropDownList);
	}

	/**
	 * compare selector with specified list
	 * 
	 * @param webDriver
	 * @param by
	 * @param expections
	 *            list<String>
	 * @param imageEntity
	 * @return
	 */
	public static boolean verifyDropDownList(WebDriver webDriver, By by, List<String> expections,
			TestImageEntity imageEntity) {
		List<String> results = Selector.getOptionTexts(webDriver, by, imageEntity);
		return compare(expections, results);
	}

	/**
	 * compare two list
	 * 
	 * @param resultList
	 * @param expectResultList
	 * @return (if they are equal,return true, else return false)
	 */
	public static boolean compare(List<String> resultList, List<String> expectResultList) {
		boolean flag = true;
		if (resultList.size() == expectResultList.size()) {
			resultList.removeAll(expectResultList);
			flag = resultList.size() == 0;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * get a specified number of random seeds for a range
	 * 
	 * @param total
	 * @param randomSize
	 *            how many random number you want
	 * @param startIndex
	 *            (start from 0).inclusive
	 * @return Set<Integer>
	 */
	public static Set<Integer> getRandomIndexes(int total, int randomSize, int startIndex) {
		Set<Integer> indexes = new HashSet<Integer>();
		Random rand = new Random();
		while (indexes.size() < randomSize) {
			indexes.add(rand.nextInt(total - startIndex) + startIndex);
		}
		return indexes;
	}

	/**
	 * get random texts of a select
	 * 
	 * @param webDriver
	 * @param input
	 * @param randomSize
	 * @param startIndex
	 * @param imageEntity
	 * @return Set<String>
	 */
	public static Set<String> getRandomText(WebDriver webDriver, By input, int randomSize, int startIndex,
			TestImageEntity imageEntity) {
		Set<String> texts = new HashSet<String>();
		Select mySele = new Select(WaitElementPresent.waitFindElement(webDriver, input));
		Set<Integer> ints = getRandomIndexes(mySele.getOptions().size(), randomSize, startIndex);
		for (int temp : ints) {
			texts.add(mySele.getOptions().get(temp).getText());
		}
		return texts;
	}

	/**
	 * get all option items in the drop down list
	 * 
	 * @param webDriver
	 * @param select
	 *            (the id or name of drop down list)
	 * @param imageEntity
	 * @return (the texts of option items)
	 */
	public static List<String> getOptionTexts(WebDriver webDriver, By select, TestImageEntity imageEntity) {
		List<String> optionTexts = new ArrayList<String>();
		WebElement stateList = WaitElementPresent.waitFindElement(webDriver, 5, select);
		stateList.click();
		ScreenCaptureUtils.captureByAWT(imageEntity);
		List<WebElement> stateOptions = new Select(stateList).getOptions();
		for (WebElement option : stateOptions) {
			if (!"All".equals(option.getText()) && !"Select".equals(option.getText())) {
				optionTexts.add(option.getText().trim());
			}
		}
		return optionTexts;
	}

	/**
	 * check a user is in a select in dialog,capture a image
	 * 
	 * @param webDriver
	 * @param userName
	 * @param desireSelect
	 * @param imageEntity
	 * @return
	 */
	public static boolean checkSelect(WebDriver webDriver, String userName, By desireSelect, TestImageEntity imageEntity) {
		String mainWindow = webDriver.getWindowHandle();
		String locationWindow = WindowSwitcher.switchToWindow();
		WaitTimeoutUtils.sleep(2);
		WebElement jms = WaitElementPresent.waitFindElement(webDriver, desireSelect);
		List<WebElement> jmOptions = new Select(jms).getOptions();
		boolean flag = false;
		for (WebElement option : jmOptions) {
			if (option.getText().equals(userName)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			return flag;
		}
		jms.click();
		imageEntity.captureBrowserSize();
		ScreenCaptureUtils.captureByAWT(imageEntity);
		WindowSwitcher.jsCloseWindow(webDriver);
		webDriver.switchTo().window(mainWindow);
		WaitElementPresent.getErrorMsg(webDriver);
		return flag;
	}
}
