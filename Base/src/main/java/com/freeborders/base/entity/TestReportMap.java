package com.freeborders.base.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.freeborders.base.exception.TestException;

public class TestReportMap {
	public static final String SHEET_TOTAL_TC = "SHEET_TOTAL_TC";

	private ClassReports clReports;
	// key is test case name in annotation
	private Map<String, String> folderPaths = new HashMap<String, String>();

	public int excelCaseTotal(String classAnnotationName) {
		return Integer.parseInt(this.clReports.getSummary(classAnnotationName).get(SHEET_TOTAL_TC));
	}

	public TestReportMap() {
		super();
		clReports = new ClassReports();
	}

	public void putClassReports(String classAnnotationName, TreeMap<String, List<TestReportEntity>> reports) {
		clReports.put(classAnnotationName, (MethodReports) reports);
	}

	/**
	 * get the wholes reports of a test class
	 * 
	 * @param classAnnotationName
	 * @return
	 */
	public MethodReports getClassReports(String classAnnotationName) {
		return clReports.get(classAnnotationName);
	}

	public void addImage(String classAnnotationName, String testCaseId, String imageUrl, final int runTimes) {
		MethodReports methodReports = clReports.get(classAnnotationName);
		TestReportEntity repo = null;
		List<String> imgs = null;
		// null means it is the first image captured of this test class
		if (methodReports == null) {
			methodReports = new MethodReports();
			List<TestReportEntity> reportList = new ArrayList<TestReportEntity>();
			imgs = new ArrayList<String>();
			imgs.add(imageUrl);
			repo = new TestReportEntity();
			repo.setImageUrl(imgs);
			reportList.add(repo);
			methodReports.put(testCaseId, reportList);
			clReports.put(classAnnotationName, methodReports);
		} else {
			// mReports is test case id's reports
			List<TestReportEntity> mReports = methodReports.get(testCaseId);
			if (mReports == null) {
				List<TestReportEntity> reportList = new ArrayList<TestReportEntity>();
				imgs = new ArrayList<String>();
				imgs.add(imageUrl);
				repo = new TestReportEntity();
				repo.setImageUrl(imgs);
				reportList.add(repo);
				methodReports.put(testCaseId, reportList);
				clReports.put(classAnnotationName, methodReports);
			} else {
				int reportSize = mReports.size();
				/**
				 * already create report
				 */
				if (reportSize == (runTimes + 1)) {
					methodReports.get(testCaseId).get(runTimes).getImageUrl().add(imageUrl);
				} else if (reportSize == runTimes) {
					imgs = new ArrayList<String>();
					imgs.add(imageUrl);
					repo = new TestReportEntity();
					repo.setImageUrl(imgs);
					mReports.add(runTimes, repo);
				} else {
					throw new TestException("report size is " + reportSize + ".Current execute time is "
							+ (runTimes + 1));
				}
			}
		}
	}

	/**
	 * put a report in reports map,
	 * 
	 * @param classAnnotationName
	 * @param testCaseId
	 * @param report
	 * @param runTimes
	 *            0 based
	 */
	public void addReport(String classAnnotationName, String testCaseId, TestReportEntity report, final int runTimes) {
		// methodReports the whole reports of a test class
		MethodReports methodReports = clReports.get(classAnnotationName);
		if (methodReports == null) {
			methodReports = new MethodReports();
			List<TestReportEntity> reportList = new ArrayList<TestReportEntity>(1);
			reportList.add(report);
			methodReports.put(testCaseId, reportList);
			clReports.put(classAnnotationName, methodReports);
		} else {
			List<TestReportEntity> mReports = methodReports.get(testCaseId);
			if (mReports == null) {
				List<TestReportEntity> reportList = new ArrayList<TestReportEntity>(1);
				reportList.add(report);
				methodReports.put(testCaseId, reportList);
				clReports.put(classAnnotationName, methodReports);
			} else {
				/**
				 * already create report
				 */
				int reportSize = mReports.size();
				if (reportSize == (runTimes + 1)) {
					// put the new captured image behind original images
					report.getImageUrl().addAll(0, mReports.get(runTimes).getImageUrl());
					mReports.set(runTimes, report);
				} else if (reportSize == runTimes) {
					mReports.add(runTimes, report);
				} else {
					throw new TestException("report size is " + reportSize + ".Current execute time is "
							+ (runTimes + 1));
				}
			}
		}
	}

	// public List<TestReportEntity> getMethodReports(String classAnnotationName, String testCaseId)
	// {
	// return clReports.get(classAnnotationName).get(testCaseId);
	// }

	public boolean isEmpty() {
		return clReports == null || clReports.size() == 0;
	}

	/**
	 * no "\" ended
	 * 
	 * @param classAnnotationName
	 * @return like c:\test
	 */
	public String getFolderPath(String classAnnotationName) {
		return folderPaths.get(classAnnotationName);
	}

	/**
	 * 
	 * @param classAnnotationName
	 * @param path
	 *            like c:\test
	 */
	public void putFolderPath(String classAnnotationName, String path) {
		this.folderPaths.put(classAnnotationName, path);
	}

	/**
	 * a treeMap, key is testCaseId
	 * 
	 * @author tom.luo
	 * 
	 */
	class MethodReports extends TreeMap<String, List<TestReportEntity>> {
		private static final long serialVersionUID = 1L;
	};

	/**
	 * a TreeMap , key is test class annotation name
	 * 
	 * @author tom.luo
	 * 
	 */
	class ClassReports extends TreeMap<String, MethodReports> {
		private static final long serialVersionUID = 1L;
		// used for class summary,key is the class annotation name
		Map<String, Map<String, String>> classSummary = new HashMap<String, Map<String, String>>();

		public void putSummary(String testAnnotationName, String key, String value) {
			Map<String, String> keyValues = this.classSummary.get(testAnnotationName);
			if (keyValues == null) {
				keyValues = new HashMap<String, String>();
				this.classSummary.put(testAnnotationName, keyValues);
			}
			keyValues.put(key, value);
		}

		public Map<String, Map<String, String>> getAllSummaries() {
			return this.classSummary;
		}

		public Map<String, String> getSummary(String classAnnotationName) {
			return this.classSummary.get(classAnnotationName);
		}
	};

	public void putSummary(String classAnnotationName, String key, String value) {
		this.clReports.putSummary(classAnnotationName, key, value);
	}
}
