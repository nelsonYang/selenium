package com.freeborders.base.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestEnumUtils {
	/**
	 * get enum by its enum toString() value
	 * @param enumClass
	 * @param toString
	 * @return
	 */
	public static <T extends Enum<T>> T valueByString(Class<T> enumClass, String toString) {
		T resultT = null;
		Method method;
		try {
			method = enumClass.getMethod("values", new Class[]{});
			T[] value=(T[])method.invoke(null, null);
			for (T t : value) {
				if(t.toString().equals(toString)){
					return t;
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultT;
	}
	
	
}
