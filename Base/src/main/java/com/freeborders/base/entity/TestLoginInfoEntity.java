package com.freeborders.base.entity;

/**
 *
 * @author nelson.yang
 */
public class TestLoginInfoEntity {
    private String userName;
    private String password;
    private String loginUrl;
    private String session;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the loginUrl
     */
    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * @param loginUrl the loginUrl to set
     */
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    /**
     * @return the session
     */
    public String getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(String session) {
        this.session = session;
    }
}
