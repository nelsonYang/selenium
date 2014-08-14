package com.freeborders.base.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.freeborders.base.log.Logger;

/**
 * 
 * @author nelson.yang
 */
public final class ClassParser {
	Log log = LogFactory.getLog(getClass());

	public List<String> parse(final String[] packageNames) {
		List<String> classNameList = new ArrayList<String>(240);
		if (packageNames != null) {
			String packagePath;
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			File file;
			URL url;
			String filePath;
			Enumeration<URL> eUrl;
			for (String packageName : packageNames) {
				packagePath = packageName.replaceAll("\\.", "/");
				try {
					eUrl = classLoader.getSystemResources(packagePath);
					while (eUrl.hasMoreElements()) {
						url = eUrl.nextElement();
						filePath = url.getPath();
						if (filePath.indexOf("src/test/") == -1 && filePath.indexOf("src/main/") == -1) {
							filePath = filePath.substring(filePath.indexOf("/") + 1);
							file = new File(filePath);
							this.parseFile(classNameList, file, packagePath);
						}
					}
				} catch (IOException ex) {
					log.error(ex.getMessage(), ex);
				}
			}
		} else {
			log.warn("no packages to scan");
		}
		return classNameList;
	}

	private void parseFile(List<String> classNameList, File file, String packagePath) {
		File[] subFiles;
		String path = file.getPath();
		if (file.isDirectory()) {
			subFiles = file.listFiles();
			for (File subFile : subFiles) {
				this.parseFile(classNameList, subFile, packagePath);
			}
		} else if (path.endsWith(".class")) {
			this.findClass(classNameList, file, packagePath);
		} else if (path.contains(".jar")) {
			// like file:/D:/temp/oistate.jar!/com/freeborders

			String jarPath = path.substring(0, path.indexOf("!"));
			this.findClassInJar(classNameList, new File(jarPath), packagePath);
		}
	}

	private void findClassInJar(List<String> classNameList, File file, String packagePath) {
		// packagePatch like com/freeborders
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(file.getPath());
			Enumeration<JarEntry> jarEntryEnum = jarFile.entries();
			JarEntry jarEntry;
			String className;
			while (jarEntryEnum.hasMoreElements()) {
				jarEntry = jarEntryEnum.nextElement();

				if (jarEntry.getName().startsWith("com/freeborders/") && !jarEntry.isDirectory()) {
					className = jarEntry.getName().replaceAll("/", ".").substring(0, jarEntry.getName().length() - 6);
					if (!classNameList.contains(className)) {
						classNameList.add(className);
					}
				}
			}
		} catch (IOException ex) {
			log.fatal(ex.getMessage(), ex);
		} finally {
			if (jarFile != null) {
				try {
					jarFile.close();
				} catch (IOException ex) {
					log.fatal(ex.getMessage(), ex);
				}
			}
		}
	}

	private void findClass(List<String> classNameList, File file, String packagePath) {
		String absolutePath = file.getAbsolutePath().replaceAll("\\\\", "/");
		int index = absolutePath.indexOf(packagePath);
		String className = absolutePath.substring(index);
		className = className.replaceAll("/", ".");
		className = className.substring(0, className.length() - 6);
		if (!classNameList.contains(className)) {
			classNameList.add(className);
		}
	}
}
