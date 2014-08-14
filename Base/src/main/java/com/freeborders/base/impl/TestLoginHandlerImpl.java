package com.freeborders.base.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.freeborders.base.api.TestLoginHandler;
import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.entity.TestLoginInfoEntity;
import com.freeborders.base.log.Logger;
import com.freeborders.base.utils.WaitElementPresent;
import com.freeborders.base.utils.WindowSwitcher;

/**
 * 
 * @author nelson.yang
 */
public class TestLoginHandlerImpl implements TestLoginHandler {

	@Override
	public void login() {
		try {
			Logger.debug("login", "login");
			TestLoginInfoEntity testLoginInfoEntity = TestApplicationContext.CTX.getThreadLocalManager()
					.getThreadLocal();
			WebDriver ieDriver = TestApplicationContext.CTX.getDefaultDriver();
			ieDriver.get(testLoginInfoEntity.getLoginUrl());
			WaitElementPresent.waitFindElement(ieDriver, 10, By.name("userName")).sendKeys(
					testLoginInfoEntity.getUserName());
			ieDriver.findElement(By.id("login")).click();
			WaitElementPresent.waitElement(ieDriver, 10, By.id("Agree"));
			ieDriver.findElement(By.id("Agree")).click();
			// ieDriver.switchTo();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void logout() {
		Logger.debug("logout", "logout");
		WebDriver webDriver = TestApplicationContext.CTX.getDefaultDriver();
		WindowSwitcher.closeAllDialog(webDriver);
		WindowSwitcher.getLogOut(webDriver).click();
	}

	@Override
	public void reLogin(WebDriver webDriver, String username) {
		// TODO Auto-generated method stub
		this.logout();
		TestLoginInfoEntity testLoginInfoEntity = TestApplicationContext.CTX.getThreadLocalManager().getThreadLocal();
		webDriver.get(testLoginInfoEntity.getLoginUrl());
		WaitElementPresent.waitFindElement(webDriver, 10, By.name("userName")).sendKeys(
				username);
		webDriver.findElement(By.id("login")).click();
		WaitElementPresent.waitElement(webDriver, 10, By.id("Agree"));
		webDriver.findElement(By.id("Agree")).click();
	}

	@Override
	public void login(WebDriver webDriver,String userName) {
		// TODO Auto-generated method stub
		TestLoginInfoEntity testLoginInfoEntity = TestApplicationContext.CTX.getThreadLocalManager().getThreadLocal();
		webDriver.get(testLoginInfoEntity.getLoginUrl());
		WaitElementPresent.waitFindElement(webDriver, 10, By.name("userName")).sendKeys(
				userName);
		webDriver.findElement(By.id("login")).click();
		WaitElementPresent.waitElement(webDriver, 10, By.id("Agree"));
		webDriver.findElement(By.id("Agree")).click();
	}

}
