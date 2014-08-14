package com.freeborders.base.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author nelson.yang
 */
public final class Logger {
	
	private Logger() {
	}

	public static void finest(String tag, String message, Throwable t) {
		ConsoleHandler handler = new ConsoleHandler();
		java.util.logging.Logger log = java.util.logging.Logger
				.getLogger("Console.".concat(tag));
		handler.setFormatter(new SimpleFormatter());
		log.setUseParentHandlers(false);
		handler.setLevel(Level.ALL);
		log.addHandler(handler);
		log.log(Level.FINEST, message, t);
		
	}

	public static void debug(String tag, String message) {
		SimpleFormatter fmt = new SimpleFormatter();
		StreamHandler sh = new StreamHandler(System.out, fmt);
		java.util.logging.Logger log = java.util.logging.Logger
				.getLogger("Console.".concat(tag));
		log.setUseParentHandlers(false);
		log.addHandler(sh);
		log.log(Level.FINE, message);
	}

	public static void info(String tag, String message) {
		SimpleFormatter fmt = new SimpleFormatter();
	
		StreamHandler sh = new StreamHandler(System.out, fmt);
		java.util.logging.Logger log = java.util.logging.Logger
				.getLogger("Console.".concat(tag));
		log.setUseParentHandlers(false);
		sh.setLevel(Level.ALL);
		log.addHandler(sh);
		log.log(Level.INFO, message);
	}

	public static void warn(String tag, String message) {
		SimpleFormatter fmt = new SimpleFormatter();
		StreamHandler sh = new StreamHandler(System.out, fmt);
		java.util.logging.Logger log = java.util.logging.Logger
				.getLogger("Console.".concat(tag));
		log.setUseParentHandlers(false);
		log.addHandler(sh);
		log.log(Level.WARNING, message);
	}

	public static void severe(String tag, String message, Throwable t) {
		java.util.logging.Logger.getLogger("Console".concat(tag)).log(
				Level.SEVERE, message, t);
	}
}
