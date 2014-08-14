package com.freeborders.base.constant;

/**
 * @author nelson.yang
 */
public interface PathConstant {
	public static final String SERVER_PATH = "http://192.168.106.129:7001/ACMBWeb/";
	public static final String LOGIN_PATH = SERVER_PATH + "ACMB/loginLocal.jsp";
	public static final String HOME_PATH = SERVER_PATH + "home!list.action?method=list";
	public static final String FIND_CASE_PATH = SERVER_PATH + "Search.do?action=show&type=2&batch=undefined\n";
	public static final String DISCOVERY_HISTORY_PATH = SERVER_PATH + "discoveryImport.do?method=init";

	// properties const
	public static final String DATA_Folder = "dataFolder";
	public static final String BROWSER_TYPE = "browserType";
	public static final String LOGIN_USER = "user1";
	public static final String EXECUTE_TIMES = "executeTimes";
	public static final String TEST_CASE_FOLDER = "testCaseFolder";
	//jdbc
	public static final String JDBC_URL = "jdbc.url";
	public static final String JDBC_USERNAME = "jdbc.username";
	public static final String JDBC_PASSWORD="jdbc.password";
}
