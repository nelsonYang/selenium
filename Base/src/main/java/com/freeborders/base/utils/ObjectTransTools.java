/**
 * Title : ObjectTransTools.java Description : Object translate tools class. *
 * 
 * @author Danis.qiu
 * @modifier Ivan
 * @version 1.1
 */

package com.freeborders.base.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Object translate tools class.
 */
public class ObjectTransTools {
	private static final Log log = LogFactory.getLog(ObjectTransTools.class);
	public static final String DOLLAR_PREFIX = "$";

	public static boolean isUrl(String path) {
		// boolean isUrl = true;
		// if(true)
		// {
		// return true;
		// }
		// try
		// {
		// log.info("The Path is " + path);
		// // URL url = new
		// // URL("http://images.sohu.com/uiuea/asohu_logo/2005/news_logo.gif");
		// // url = new
		// // URL("https://legaltest.o-i.com/TIFFImages/CD214/ASBESTMI/0000/0001.tif");
		// URL url = new URL(path);
		//
		// HttpURLConnection con = (HttpURLConnection)url.openConnection();
		// con.setFollowRedirects(true);
		// con.setInstanceFollowRedirects(false);
		// con.connect();
		// java.io.InputStream in = con.getInputStream();
		// java.io.BufferedReader breader = new BufferedReader(new InputStreamReader(in, "GBK"));
		// String str = breader.readLine();
		// while(str != null)
		// {
		// log.info("Str = " + str);
		// str = breader.readLine();
		// }
		// }
		// catch(Exception e)
		// {
		// isUrl = false;
		// log.error("got error during proccessing PATH:"+path, e);
		// return isUrl;
		// }
		// return isUrl;

		/* For some reason, god know why they write like this!!! */
		return true;
	}

	/**
	 * translate string to int
	 * 
	 * @param strValue
	 *            String
	 * @return int Int
	 */
	public static int stringToInt(String strValue) {
		return stringToInt(strValue, 0);
	}

	/**
	 * validate string null.
	 * 
	 * @param string
	 *            String
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String string) {
		if (string == null || string.trim().equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}

	/**
	 * translate string to int, if string is null, then with zero replace.
	 * 
	 * @param strValue
	 *            String
	 * @param zero
	 *            zero
	 * @return int the int value after parsing from string.
	 */
	public static int stringToInt(String strValue, int zero) {

		if (strValue == null || (strValue.trim()).length() == 0) {
			return zero;
		}
		try {
			return Integer.parseInt(strValue.trim());
		} catch (NumberFormatException ex) {
			return zero;
		}
	}

	/**
	 * translate string to float, if string is null, then with zero replace.
	 * 
	 * @param strValue
	 *            String
	 * @param zero
	 *            zero
	 * @return float the float value after parsing from string.
	 */
	public static float stringToFloat(String strValue, float zero) {

		if (strValue == null || (strValue.trim()).length() == 0) {
			return zero;
		}
		try {
			return Float.parseFloat(strValue.trim());
		} catch (NumberFormatException ex) {
			return zero;
		}
	}

	/**
	 * translate string to double.
	 * 
	 * @param strValue
	 *            String
	 * @return double
	 */
	public static double stringToDouble(String strValue) {
		return stringToDouble(strValue, 0.0);
	}

	/**
	 * translate string to double, if string is null, then with zero replace.
	 * 
	 * @param strValue
	 *            string
	 * @param zero
	 *            zero
	 * @return double
	 */
	public static double stringToDouble(String strValue, double zero) {
		if (strValue == null || strValue.trim().length() == 0) {
			return zero;
		}
		try {
			strValue = strValue.replaceAll(",", "");
			return Double.parseDouble(strValue.trim());
		} catch (NumberFormatException ex) {
			return zero;
		}
	}

	/**
	 * get boolean frome string
	 * 
	 * @param value
	 *            string.
	 * @return boolean value.
	 */
	public static boolean StringToBoolean(String value) {
		if (value != null && (value.equals("1") || "TRUE".equals(value.toUpperCase()))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * translate string object to int
	 * 
	 * @param obj
	 *            object
	 * @return Int
	 */
	public static int strObjToInt(Object obj) {
		if (obj == null || obj.toString().trim().length() == 0) {
			return 0;
		} else {
			return Integer.parseInt(obj.toString().trim());
		}
	}

	/**
	 * split source string to
	 * 
	 * @param source
	 *            source
	 * @param delim
	 *            delim
	 * @return String[]
	 */
	public static String[] split(String source, String delim) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (delim == null) {
			delim = ",";
		}
		StringTokenizer st = new StringTokenizer(source, delim);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	/**
	 * array to String
	 * 
	 * @param orgArray
	 *            array
	 * @param strSplit
	 *            split
	 * @return String
	 */
	public static String arrayToString(String[] orgArray, String strSplit) {
		if (orgArray == null) {
			return null;
		}
		if (orgArray.length == 0) {
			return null;
		}
		String tmp = null;
		int size = orgArray.length;
		for (int i = 0; i < size; i++) {
			if (tmp == null) {
				tmp = orgArray[i] + strSplit;
			} else {
				tmp = tmp + orgArray[i];
			}
			if (i + 1 < size) {
				tmp = tmp + strSplit;
			}
		}
		return tmp;
	}

	/**
	 * list to String
	 * 
	 * @param list
	 *            array List
	 * @param strSplit
	 *            split
	 * @return String
	 */
	public static String ListToString(List list, String strSplit) {
		if (list == null) {
			return null;
		}
		if (list.size() == 0) {
			return null;
		}
		String tmp = null;
		int size = list.size();
		for (int i = 0; i < size; i++) {
			if (tmp == null) {
				tmp = list.get(i) + strSplit;
			} else {
				tmp = tmp + list.get(i);
			}
			if (i + 1 < size) {
				tmp = tmp + strSplit;
			}
		}
		return tmp;
	}

	/**
	 * Split string by split
	 * 
	 * @param source
	 *            Source String
	 * @param delim
	 *            delim
	 * @return String[]
	 */
	public static String[] split(String source, char delim) {
		return split(source, String.valueOf(delim));
	}

	/**
	 * split string
	 * 
	 * @param source
	 *            source String
	 * @return String[]
	 */
	public static String[] split(String source) {
		return split(source, ",");
	}

	/**
	 * replace a String
	 * 
	 * @param str
	 *            String
	 * @param source
	 *            source String
	 * @param replace
	 *            replace String
	 * @return String
	 */
	public static String replaceStr(String str, String source, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		if (str != null) {
			while ((e = str.indexOf(source, s)) >= 0) {
				result.append(str.substring(s, e));
				result.append(replace);
				s = e + source.length();
			}
			result.append(str.substring(s));
			return result.toString();
		} else {
			return "";
		}
	}

	/**
	 * String to long
	 * 
	 * @param s
	 *            String
	 * @param dest
	 *            long
	 * @return long
	 */
	public static long stringToLong(String s, long dest) {
		if (s == null || s.trim().length() == 0) {
			return dest;
		}
		try {
			return Long.parseLong(s);
		} catch (Exception ex) {
			return dest;
		}
	}

	/**
	 * String to ""
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String stringTrans(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	/**
	 * String to a default string
	 * 
	 * @param str
	 *            String
	 * @param def
	 *            default String
	 * @return String
	 */
	public static String stringTrans(String str, String def) {
		if (str == null || "".equals(str)) {
			return def;
		}
		return str;
	}

	/**
	 * get a default String
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String getStringByDefault(String str) {
		return getStringByDefault(str, "");
	}

	public static String getToString(String str) {
		if (str == null || str.trim().length() <= 0) {
			return "0.0";
		}
		return str;
	}

	/**
	 * get a string by default
	 * 
	 * @param str
	 *            String
	 * @param defaultStr
	 *            default
	 * @return String
	 */
	public static String getStringByDefault(String str, String defaultStr) {
		if (str == null || str.length() == 0) {
			return defaultStr;
		}
		return str;
	}

	/**
	 * get a default from object
	 * 
	 * @param stringObject
	 *            string
	 * @param defaultStr
	 *            default String
	 * @return String
	 */
	public static String getObjectStringByDefault(Object stringObject, String defaultStr) {
		if (stringObject == null || "".equals(stringObject)) {
			return defaultStr;
		}
		return stringObject.toString();
	}

	/**
	 * get a int from object
	 * 
	 * @param stringObject
	 *            object
	 * @return Int
	 */
	public static int getObjectInt(Object stringObject) {
		String str = getObjectStringByDefault(stringObject, "");
		return stringToInt(str);
	}

	/**
	 * get a String from object
	 * 
	 * @param stringObject
	 *            object
	 * @return String
	 */
	public static String getObjectString(Object stringObject) {
		return getObjectStringByDefault(stringObject, "");
	}

	/**
	 * cut strTemp frist charTemp
	 * 
	 * @param strTemp
	 *            source string
	 * @param charTemp
	 *            cut string
	 * @return String
	 */
	public static String trimChar(String strTemp, String charTemp) {
		if (strTemp == null || strTemp.equals("")) {
			return "";
		}
		if (charTemp == null || charTemp.equals("")) {
			charTemp = ",";
		}

		if (!strTemp.equals("") && strTemp.length() > charTemp.length() && strTemp.startsWith(charTemp)) {
			strTemp = strTemp.substring(charTemp.length(), strTemp.length() - 1);
		}
		if (!strTemp.equals("") && strTemp.length() > charTemp.length() && strTemp.endsWith(charTemp)) {
			strTemp = strTemp.substring(0, strTemp.length() - charTemp.length());
		}
		return strTemp;
	}

	/**
	 * get sub string if the string length more than length
	 * 
	 * @param strobject
	 *            string
	 * @param length
	 *            length
	 * @return String
	 */
	public static String getSubString(String strobject, int length) {
		if (length <= 3) {
			length = 10;
		}
		if (strobject == null) {
			strobject = "";
		} else {
			if (strobject.length() > length) {
				strobject = strobject.substring(0, length - 3) + "...";
			}
		}
		return strobject;
	}

	public static String getDivisionString(String strobject) {
		if (strobject == null || "".equals(strobject)) {
			return "";
		} else if ("select".equalsIgnoreCase(strobject.trim()) || strobject.length() <= 4) {
			return strobject;
		} else {
			return strobject.substring(0, 4);
		}
	}

	/**
	 * Date Format Transform Function
	 * 
	 * @param dateTypeObj
	 *            It is the Date Class of the subClass of Date
	 * @return "MM/dd/yy hh:mm a",example:"04/18/05 04:35 PM"
	 */
	public static String dateFormatToMMddyyhhmma(Object dateTypeObj) {
		if (dateTypeObj == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("MM/dd/yy hh:mm a", Locale.US);
		String result = format.format(dateTypeObj);
		return result;
	}

	/**
	 * Date Format Transform Function
	 * 
	 * @param dateTypeObj
	 *            It is the Date Class of the subClass of Date
	 * @param dateFormat
	 *            the date format
	 * @return date
	 */
	public static String dateFormat(Object dateTypeObj, String dateFormat) {
		if (dateTypeObj == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat(dateFormat, Locale.US);
		String result = format.format(dateTypeObj);
		return result;
	}

	/**
	 * get error file name
	 * 
	 * @param key
	 *            1: bulk load 2: billing 3: scanned
	 * @return
	 */
	public static String getFileName(String key) {
		String name;
		name = key + "_ImportErrors_" + dateFormat(new Date(), "MMddyyyy") + "_" + new Date().getTime();
		return name;
	}

	/**
	 * Date Format Transform Function
	 * 
	 * @param dateTypeObj
	 *            It is the Date Class of the subClass of Date
	 * @return "MM/dd/yy hh:mm a",example:"04/18/05 04:35 PM"
	 */
	public static String dateToString(Object dateTypeObj) {
		if (dateTypeObj == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		String result = format.format(dateTypeObj);
		return result;
	}

	public static String dateToString(Object dateTypeObj, String formatStr) {
		if (dateTypeObj == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat(formatStr, Locale.US);
		String result = format.format(dateTypeObj);
		return result;
	}

	public static String adjustDate(String sourceDate, int balance, boolean weekday, boolean aheadOfTime) {
		String result = "";
		if (sourceDate == null || sourceDate.trim().length() == 0) {
			return result;
		}
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		Date d = null;
		GregorianCalendar calendar = new GregorianCalendar(Locale.US);
		try {
			d = df.parse(sourceDate);
			calendar.setTime(d);
			int numberOfDaysCurrentYear = calendar.get(Calendar.DAY_OF_YEAR);
			int adjustNumberOfDays = numberOfDaysCurrentYear + balance;
			calendar.set(Calendar.DAY_OF_YEAR, adjustNumberOfDays);
			if (weekday) {
				adjustweekday(calendar, aheadOfTime);
			}
			result = df.format(calendar.getTime());
		} catch (Exception e) {
			log.error("Error:", e);
		}

		return result;
	}

	public static GregorianCalendar adjustweekday(GregorianCalendar calendar, boolean aheadOfTime) {
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		if (weekDay == Calendar.SATURDAY) {
			if (aheadOfTime) {
				dayOfYear -= 1;
			} else {
				dayOfYear += 2;
			}
		} else if (weekDay == Calendar.SUNDAY) {
			if (aheadOfTime) {
				dayOfYear -= 2;
			} else {
				dayOfYear += 1;
			}
		}
		calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return calendar;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(viewDollar("50"));
	}

	/**
	 * Date Format Transform Function
	 * 
	 * @param dateTypeObj
	 *            It is the Date Class of the subClass of Date
	 * @return "MM/dd/yy hh:mm a",example:"04/18/05 04:35 PM"
	 */
	public static String dateToSecondString(Object dateTypeObj) {
		if (dateTypeObj == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
		String result = format.format(dateTypeObj);
		return result;
	}

	/**
	 * Date Format Transform Function
	 * 
	 * @param str
	 *            It is the Date Class of the subClass of Date
	 * @return "MM/dd/yy hh:mm a",example:"04/18/05 04:35 PM"
	 */
	public static String dateFormatString(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		Date d = null;
		try {
			d = df.parse(str);

		} catch (Exception e) {
			log.error("Error:", e);
		}
		return dateToString(d == null ? new Date() : d);
	}

	public static String dateFormatString(String str, String format1, String format2) {
		if (str == null || str.trim().length() == 0) {
			return "-1";
		}
		SimpleDateFormat df = new SimpleDateFormat(format1, Locale.US);
		Date d = null;
		try {
			d = df.parse(str);

		} catch (Exception e) {
			log.error("Error:", e);
		}
		return dateToString(d == null ? new Date() : d, format2);
	}

	/**
	 * String to date
	 * 
	 * @param dateStr
	 *            It is the Date Class of the subClass of Date
	 * @return "MM/dd/yy hh:mm a",example:"04/18/2005 04:35 PM"
	 */
	public static java.sql.Date stringToDate(String dateStr) {
		if (dateStr == null || dateStr.trim().length() == 0) {
			return null;
		}
		java.sql.Date sqlDate = null;
		try {
			String[] dateArray = dateStr.split("/");
			int day = Integer.parseInt(dateArray[1]);
			int month = Integer.parseInt(dateArray[0]) - 1;
			// System.out.println(month);
			int year = Integer.parseInt(dateArray[2]);
			GregorianCalendar da = new GregorianCalendar(year, month, day);
			sqlDate = new java.sql.Date(da.getTimeInMillis());
		} catch (Exception e) {
			log.error("Error:", e);
		}
		return sqlDate;

	}

	public static java.sql.Timestamp stringToTimestamp(String dateStr) {
		if (dateStr == null || dateStr.trim().length() == 0) {
			return null;
		}
		java.sql.Timestamp timestamp = null;
		try {
			String[] dateArray = dateStr.split("/");
			int day = Integer.parseInt(dateArray[1]);
			int month = Integer.parseInt(dateArray[0]) - 1;
			int year = Integer.parseInt(dateArray[2].trim().substring(0, 4));
			String[] time = dateArray[2].trim().substring(4, dateArray[2].length()).split(":");
			int hour = Integer.parseInt(time[0].trim());
			int minute = Integer.parseInt(time[1].trim());
			int second = Integer.parseInt(time[2].trim());
			GregorianCalendar da = new GregorianCalendar(year, month, day, hour, minute, second);
			timestamp = new java.sql.Timestamp(da.getTimeInMillis());
			/*
			 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); date =
			 * sdf.parse(dateStr); timestamp = new java.sql.Timestamp(date.getTime());
			 */
		} catch (Exception e) {
			log.error("Error:", e);
		}
		return timestamp;
	}

	/**
	 * get the String is a imageType or not..
	 * 
	 * @param imageType
	 *            image type
	 * @return boolean
	 */
	public static boolean isValidImageType(String imageType) {
		boolean result = false;
		if (imageType != null) {
			if ("gif".equalsIgnoreCase(imageType)) {
				result = true;
			} else if ("jpg".equalsIgnoreCase(imageType)) {
				result = true;
			} else if ("bmp".equalsIgnoreCase(imageType)) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * round up
	 * 
	 * @param d
	 * @param RoundFormat
	 * @return String the number after formating.
	 */
	public static String roundUp(String d, String RoundFormat) {
		DecimalFormat nf = new DecimalFormat(RoundFormat);
		double number = Double.parseDouble(d);
		return nf.format(number);
	}

	/**
	 * round up a double data
	 * 
	 * @param d
	 *            the double data
	 * @param RoundFormat
	 *            the format string
	 * @return a fromatted double data.
	 */
	public static double roundUp(double d, String RoundFormat) {
		DecimalFormat nf = new DecimalFormat(RoundFormat);
		String number = nf.format(d);
		Double obj = new Double(number);
		return obj.doubleValue();
	}

	/**
	 * round up a double data
	 * 
	 * @param d
	 *            the double data
	 * @param RoundFormat
	 *            the format string
	 * @return a fromatted double data.
	 */
	public static String roundUpToString(double d, String RoundFormat) {
		DecimalFormat nf = new DecimalFormat(RoundFormat);
		String number = nf.format(d);
		return number;
	}

	/**
	 * the dollar format
	 * 
	 * @param dollar
	 *            the dollar string
	 * @return format
	 */
	public static String viewDollarEscStr(String dollar) {
		if (dollar == null || "".equals(dollar)) {
			return "";
		} else {
			try {
				new Double(dollar);
			} catch (Exception e) {
				return dollar;
			}
			String result = roundUp(dollar, "#,##0.00");
			return DOLLAR_PREFIX + result;
		}
	}

	/**
	 * String format
	 * 
	 * @param dollar
	 *            dollar
	 * @return format
	 */
	public static String viewDollar(String dollar) {
		if (dollar == null || "".equals(dollar)) {
			return "";
		}
		String result = roundUp(dollar, "#,##0.00");
		return DOLLAR_PREFIX + result;
	}

	/**
	 * format string
	 * 
	 * @param dollar
	 *            dollar
	 * @return String
	 */
	public static String viewDollar(double dollar) {
		String d = String.valueOf(dollar);
		return viewDollar(d);
	}

	/**
	 * double change to String
	 * 
	 * @param dollar
	 *            double
	 * @return String
	 */
	public static String viewDollar(Double dollar) {
		return viewDollar(dollar.doubleValue());
	}

	/**
	 * String format
	 * 
	 * @param dollar
	 *            dollar
	 * @param prefix
	 *            prefix String
	 * @return format
	 */
	public static String viewDollar(String dollar, String prefix) {
		if (dollar == null || "".equals(dollar)) {
			return "";
		}
		String result = roundUp(dollar, "#,##0.00");
		if (prefix != null) {
			return prefix + result;
		}
		return result;
	}

	/**
	 * format string
	 * 
	 * @param dollar
	 *            dollar
	 * @param prefix
	 *            prefix String
	 * @return String
	 */
	public static String viewDollar(double dollar, String prefix) {
		String d = String.valueOf(dollar);
		return viewDollar(d, prefix);
	}

	/**
	 * double change to String
	 * 
	 * @param dollar
	 *            double
	 * @param prefix
	 *            prefix String
	 * @return String
	 */
	public static String viewDollar(Double dollar, String prefix) {
		return viewDollar(dollar.doubleValue(), prefix);
	}

	/**
	 * String format
	 * 
	 * @param number
	 *            String
	 * @return String
	 */
	public static String viewNumber(String number) {
		if (number == null) {
			return "";
		}
		String result = roundUp(number, "#,###");
		return result;
	}

	/**
	 * int format to string
	 * 
	 * @param number
	 *            int
	 * @return String
	 */
	public static String viewNumber(int number) {
		String n = String.valueOf(number);
		if (number < 999) {
			return n;
		}
		return viewNumber(n);
	}

	/**
	 * integer change to string
	 * 
	 * @param number
	 *            integer
	 * @return String
	 */
	public static String viewNumber(Integer number) {
		return viewNumber(number.intValue());
	}

	/**
	 * remove Comma
	 * 
	 * @param input
	 *            input string.
	 * @return all commas have been deleted.
	 */
	public static String removeComma(String input) {
		return input.replaceAll(",", "");
	}

	/**
	 * get fix radix point double by random double.
	 * 
	 * @param d
	 *            the random double.
	 * @param fixRadixPoint
	 *            the fix radix point.
	 * @return the fix radix point BigDecimal object.
	 */
	public static BigDecimal getFixRadixPoint(double d, int fixRadixPoint) {
		BigDecimal dInt = new BigDecimal(BigInteger.valueOf(1));

		BigDecimal bd = new BigDecimal(d);

		BigDecimal dResult = bd.divide(dInt, fixRadixPoint, BigDecimal.ROUND_HALF_UP);

		return dResult;
	}

	/**
	 * convert string array to int array.
	 * 
	 * @param strArray
	 *            the string array.
	 * @return the int array.
	 */
	public static int[] stringArrayToIntArray(String[] strArray) {
		int[] iArray;
		if (strArray == null) {
			iArray = new int[0];
		} else {
			iArray = new int[strArray.length];
			for (int i = 0; i < strArray.length; i++) {
				iArray[i] = ObjectTransTools.stringToInt(strArray[i], 0);
			}
		}

		return iArray;
	}

	/**
	 * convert string array to float array.
	 * 
	 * @param strArray
	 *            the string array.
	 * @return the float array.
	 */
	public static float[] stringArrayToFloatArray(String[] strArray) {
		float[] iArray;
		if (strArray == null) {
			iArray = new float[0];
		} else {
			iArray = new float[strArray.length];
			for (int i = 0; i < strArray.length; i++) {
				iArray[i] = ObjectTransTools.stringToFloat(strArray[i], 0);
			}
		}

		return iArray;
	}

	/**
	 * compare a string
	 * 
	 * @param newArray
	 *            new array
	 * @param oldArray
	 *            old array
	 * @return boolean
	 */
	public static boolean compareStringArray(String[] newArray, String[] oldArray) {
		boolean flag = false;

		if (newArray == null && oldArray == null) {
			return true;
		}
		if ((newArray == null && oldArray.length == 0) || (oldArray == null && newArray.length == 0)) {
			return true;
		}
		if ((newArray == null && oldArray.length != 0) || (oldArray == null && newArray.length != 0)) {
			return false;
		}
		if (newArray.length != oldArray.length) {
			return false;
		}

		for (int i = 0; i < newArray.length; i++) {
			flag = false;
			String newStr = newArray[i];
			for (int j = 0; j < oldArray.length; j++) {
				String oldStr = oldArray[j];
				if (newStr.equals(oldStr)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				return false;
			}
		}

		return flag;
	}

	/**
	 * compare two string of date
	 * 
	 * @param currentDate
	 *            the current date.
	 * @param destinationDate
	 *            the destination date.
	 * @return 0 if the currentDate is equal to destinationDate.
	 */
	public static int compareStringDate(String currentDate, String destinationDate) {
		Date cdate = stringToDate(currentDate);
		Date ddate = stringToDate(destinationDate);
		if (cdate == null || ddate == null) {
			return Integer.MAX_VALUE;
		} else {
			return cdate.compareTo(ddate);
		}

	}

	/**
	 * String to date
	 * 
	 * @param str
	 *            String
	 * @return String
	 */
	public static String strToFullDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.US);
		if (str == null || str.trim().length() == 0) {
			return "";
		}
		Date d = null;
		try {
			d = sdf.parse(str);
		} catch (Exception e) {
			log.error("Error:", e);
		}
		String s = df.format(d);
		return s;
	}

	/**
	 * handl null list.
	 * 
	 * @param list
	 *            list object.
	 */
	public static List handleDropDownList(List list) {
		if (list == null) {
			list = new ArrayList();
		}
		return list;
	}

	/**
	 * object to date
	 * 
	 * @param dateTypeObj
	 *            date object
	 * @return String
	 */
	public static String dateToFileString(Object dateTypeObj) {
		if (dateTypeObj == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat("dd_MM_yyyy", Locale.US);
		String result = format.format(dateTypeObj);
		return result;
	}

	/**
	 * check a string is a null, if yes, then return ""
	 * 
	 * @param input
	 *            String
	 * @return String
	 */
	public static String checkNull(String input) {
		if (input == null) {
			return "";
		}
		return input;
	}

	/**
	 * String equal null
	 * 
	 * @param input
	 *            String
	 * @return boolean
	 */
	public static boolean equalNull(String input) {
		if (input == null) {
			return true;
		}
		if (input.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Convert special char to html.
	 * 
	 * @param name
	 *            the string name.
	 * @return the string after converted.
	 */
	public static String textToHtml(String name) {
		if (name == null) {
			return "";
		}

		name = name.replaceAll("&", "&amp;");
		name = name.replaceAll("\"", "&quot;");
		name = name.replaceAll("<", "&lt;");
		name = name.replaceAll(">", "&gt;");
		name = name.replaceAll("'", "&lsquo;");

		return name;
	}

	/**
	 * Convert special html characters to text char.
	 * 
	 * @param name
	 *            the string name.
	 * @return the string after converted.
	 */
	public static String htmlToText(String name) {
		if (name == null) {
			return "";
		}

		name = name.replaceAll("&amp;", "&");
		name = name.replaceAll("&quot;", "\"");
		name = name.replaceAll("&lt;<", "<");
		name = name.replaceAll("&gt;", ">");
		name = name.replaceAll("&lsquo;", "'");

		return name;
	}

	/**
	 * Get the first part of phone.
	 * 
	 * @param phone
	 *            the phone number.
	 * @return String the first part of phone or fax.
	 */
	public static String getPhone1ByPhone(String phone) {
		String result = "";
		if (phone != null && phone.trim().length() > 0 && phone.indexOf(")") > 1) {
			result = phone.substring(1, phone.indexOf(")"));
		}

		return result;
	}

	/**
	 * Get the second part of phone or fax.
	 * 
	 * @param phone
	 *            the phone number.
	 * @return String the second part of phone or fax.
	 */
	public static String getPhone2ByPhone(String phone) {
		String result = "";
		if (phone != null && phone.trim().length() > 0 && phone.indexOf(")") != -1
				&& phone.indexOf("-") > phone.indexOf(")") + 1) {
			result = phone.substring(phone.indexOf(")") + 1, phone.indexOf("-"));
		}
		return result;
	}

	/**
	 * Get the third part of phone or fax.
	 * 
	 * @param phone
	 *            the phone number.
	 * @return String the third part of phone or fax.
	 */
	public static String getPhone3ByPhone(String phone) {
		String result = "";
		if (phone != null && phone.trim().length() > 0 && phone.indexOf("-") != -1) {
			result = phone.substring(phone.indexOf("-") + 1);
		}

		return result;
	}

	/**
	 * Parse currency.
	 * 
	 * @param curr
	 *            the currency value;
	 * @return double the currency after parsing.
	 */
	public static double parseCurrency(String curr) {
		double d = 0.0;
		if (curr == null) {
			return d;
		}
		if (curr.length() > 0 && curr.charAt(0) == '$') {
			curr = curr.substring(1);
		}
		if (curr.indexOf(",") != -1) {
			curr = curr.replaceAll(",", "");
			d = ObjectTransTools.stringToDouble(curr, 0.0);
		} else {
			d = ObjectTransTools.stringToDouble(curr, 0.0);
		}

		return d;
	}

	/**
	 * Parse currency.
	 * 
	 * @param curr
	 *            the currency value;
	 * @return double the currency after parsing.
	 */
	public static String parseCurrencyForDC(String curr) {
		if (curr == null) {
			return "";
		}
		if (curr.length() > 0 && curr.charAt(0) == '$') {
			curr = curr.substring(1);
		}
		if (curr.indexOf(",") != -1) {
			curr = curr.replaceAll(",", "");
		}

		return curr;
	}

	/**
	 * Parse currency.
	 * 
	 * @param curr
	 *            the currency value;
	 * @return int the currency after parsing.
	 */
	public static int parseCurrencyToInt(String curr) {
		int i = 0;
		if (curr == null) {
			return i;
		}
		if (curr.length() > 0 && curr.charAt(0) == '$') {
			curr = curr.substring(1);
		}
		if (curr.indexOf(",") != -1) {
			curr = curr.replaceAll(",", "");
			i = ObjectTransTools.stringToInt(curr, 0);
		} else {
			i = ObjectTransTools.stringToInt(curr, 0);
		}

		return i;
	}

	/**
	 * get Desc From Option Array by Vale
	 * 
	 * @param options
	 *            the element must be options array;
	 * @param value
	 *            option value;
	 * @return string
	 */
	public static String getDescFromOptionArray(String optionsList, String value) {
		String[] options = optionsList.split(",");
		String[] option;
		for (int i = 0; i < options.length; i++) {
			option = options[i].split("_");
			if (option.length == 2) {
				if (option[0].equals(value)) {
					return option[1];
				}
			}
		}
		return "";
	}

	/**
	 * check the field, convert "-1" to ""
	 * 
	 * @param field
	 *            the field
	 * @return
	 */
	public static String checkField(String field) {
		if (field.trim().equals("-1")) {
			return "";
		}
		return field;
	}

	public static String doubleQuotes(String s) {
		if (s == null) {
			return null;
		}
		if (s.indexOf("\"") != -1) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '\"') {
					sb.append("\\\"");
				} else {
					sb.append(s.charAt(i));
				}
			}
			s = sb.toString();
		}
		return s;
	}

	public Map mapSortByKey(Map map) {
		TreeMap result = new TreeMap();
		Object[] mapKey = map.keySet().toArray();
		Arrays.sort(mapKey);
		for (int i = 0; i < mapKey.length; i++) {
			result.put(mapKey[i], map.get(mapKey[i]));
		}
		return result.tailMap(result.firstKey());
	}

	public static String removeZero(String s) {
		String a = "", b = "";
		int index = s.indexOf(".");
		if (index <= 0 || index == s.length() - 1) {
			return s;
		} else {
			a = s.substring(0, index);
			b = s.substring(index + 1, s.length());
			char[] charB = b.toCharArray();
			for (int i = 0; i < charB.length; i++) {
				if (charB[i] != '0') {
					return s;
				}
			}
			return a;
		}
	}

	public static String[] removeObjectByIndex(String[] sourceArry, int index) {
		if (sourceArry == null || index < 0) {
			return sourceArry;
		}
		if (index > sourceArry.length - 1) {
			return sourceArry;
		}
		List tempList = new ArrayList();
		for (int i = 0; i < sourceArry.length; i++) {
			if (i != index) {
				tempList.add(sourceArry[i]);
			}
		}
		String[] resultArray = new String[tempList.size()];
		for (int i = 0; i < tempList.size(); i++) {
			resultArray[i] = (String) tempList.get(i);
		}
		return resultArray;
	}

}