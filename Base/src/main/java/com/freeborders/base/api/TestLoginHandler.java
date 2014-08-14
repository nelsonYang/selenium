package com.freeborders.base.api;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author nelson.yang
 */
public interface TestLoginHandler {
    
    public void login();
    public void login(WebDriver webDriver,String userName);
    /**
     * log out and log in
     * @param username
     */
    public void reLogin(WebDriver webDriver,String username);
    
    public void logout();

}
