package com.freeborders.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Platform;

public class CommandKillProcess {

	/**
	 * kill processes by name
	 * @param platform
	 * @param processName
	 */
	public static void killProcessName(Platform platform, String processName) {
		// TODO Auto-generated method stub
		switch (platform) {
		case WINDOWS:
			List<String> pids = getPIDs(Platform.WINDOWS, processName);
			try {
				for (String string : pids) {
					Runtime.getRuntime().exec("taskkill /F /PID " + string);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	/**
	 * get program pids 
	 * @param platform
	 * @param processName 
	 * @return 
	 */
	public static List<String> getPIDs(Platform platform, String processName) {
		List<String> pids = new ArrayList<String>();
		String cmd = "tasklist /nh /FI \"IMAGENAME eq " + processName + "\"";
		switch (platform) {
		case WINDOWS:
			try {
				Runtime runtime = Runtime.getRuntime();
				Process process = runtime.exec(cmd);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					if (line.indexOf(processName) != -1) {
						String[] lineArray = line.split("\\s+");
						pids.add(lineArray[1].trim());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		return pids;
	}

}
