package com.freeborders.base.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.freeborders.base.api.TestParameterHandler;
import com.freeborders.base.api.TestReportHandler;
import com.freeborders.base.constant.PathConstant;
import com.freeborders.base.context.TestApplicationContextBuilderImpl;
import com.freeborders.base.entity.TestClassEntity;
import com.freeborders.base.entity.TestMethodEntity;
import com.freeborders.base.entity.TestReportEntity;

/**
 * 
 * @author nelson
 */
public final class TestReportHtmlHandlerImpl implements TestReportHandler {
	private int times = 0;
	private String content;
	Log log = LogFactory.getLog(getClass());
	@Override
	public String generateTestReport(String testClassName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		// construct summary
		double successRate = 0.00;
		int successCount = 0;
		int total = 0;
		int manualTotal = 0;
		double coverageRate = 0.00;
		Map<String, List<TestReportEntity>> classReports = testReportMap.getClassReports(testClassName);
		if (classReports == null)
			return null;
		for (Entry<String, List<TestReportEntity>> entry : classReports.entrySet()) {
			List<TestReportEntity> reportList = entry.getValue();
			total += reportList.size();
			for (TestReportEntity testReportEntity : reportList) {
				if (testReportEntity.isStatus()) {
					successCount = successCount + 1;
				}
			}
		}
		
		if (total > 0) {
			successRate = successCount * 100.00 / total;
		}
		manualTotal = testReportMap.excelCaseTotal(testClassName);
		if(manualTotal > 0) {
			coverageRate = total * 100.00 / manualTotal;
		}
		StringBuilder emailContentBuilder = new StringBuilder(100);
		String beforeBody = "<HTML><HEAD><title>Test Report</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></HEAD><body>";
		String afterBody = "</body></html>";
		emailContentBuilder
				.append(beforeBody)
				.append("<div>")
				.append("<table border='1'  cellSpacing = '0' cellPadding = '10'>")
				.append("<tr><th>Num. of Manual TC</th><th>Num. of Selenium TC</th><th>Automation Test Coverage Rate(%)</th><th>Success</th><th>Failure</th><th>Success Rate(%)</th><th>Failure Rate(%)</th><th>Date</th></tr>")
				.append("<tr>")
				.append("<td>").append(String.valueOf(manualTotal)).append("</td>")
				.append("<td>").append(String.valueOf(total)).append("</td>")
				.append("<td>").append(decimalFormat.format(coverageRate)).append("</td>")
				.append("<td>").append(String.valueOf(successCount)).append("</td>")
				.append("<td>").append(String.valueOf(total - successCount)).append("</td>")
				.append("<td>").append(decimalFormat.format(successRate)).append("</td>")
				.append("<td>").append(decimalFormat.format(100.00 - successRate)).append("</td>")
				.append("<td>").append(sdf.format(new Date())).append("</td>")
				.append("</tr>")
				.append("</table>")
				.append("</div>");

		// if not all test case succeed
		StringBuilder emailDataBuilder = new StringBuilder(500);
		emailDataBuilder
				.append("<div style='padding-top:10px;'>")
				.append("<table border='1' cellSpacing = '0' cellPadding = '10'>")
				.append("<tr><th>TestCaseID</th><th>Summary</th><th>Module</th><th>Test Result</th><th>Fail Reason</th><th>Image Location</th></tr>");
		for (Entry<String, List<TestReportEntity>> entry : classReports.entrySet()) {
			for (TestReportEntity testReportEntity : entry.getValue()) {
				emailDataBuilder.append("<tr>").append("<td>").append(testReportEntity.getTestCaseId()).append("</td>")
						.append("<td width='200' wrap='wrap'>").append(testReportEntity.getSummary()).append("</td>")
						.append("<td>").append(testReportEntity.getModelName()).append("_" + testClassName)
						.append("</td>");
				if (testReportEntity.isStatus()) {
					emailDataBuilder.append("<td align='center'>").append("Pass").append("</td>")
							.append("<td>&nbsp;</td>");

				} else {

					emailDataBuilder
							.append("<td align='center'>")
							.append("Fail")
							.append("</td>")
							.append("<td width='300' wrap='wrap'>")
							.append(testReportEntity.getReason() == null ? testReportEntity.getReason()
									: testReportEntity.getReason().replace("\n", "<br>")
											.replace("	", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"))
							.append("</td>");
				}
				emailDataBuilder.append("<td>");
				if (!testReportEntity.getImageUrl().isEmpty()) {
					for (int i = 0; i < testReportEntity.getImageUrl().size(); i++) {
						String imagePath = testReportEntity.getImageUrl().get(i);
//						String[] fileUrls = imagePath.split("\\\\");
//						String relativePath = fileUrls[3].concat("\\").concat(fileUrls[4]);
						String imageName = imagePath.substring(imagePath.lastIndexOf("\\") + 1, imagePath.length());
						String filePath = imagePath.substring(0, imagePath.lastIndexOf("\\"));
					    String relativePath = filePath.substring(filePath.lastIndexOf("\\") +1, filePath.length()).concat("\\").concat(imageName);
						emailDataBuilder.append(
								"<a href='" + relativePath + "' target='_blank'>" + testReportEntity.getTestCaseId() + "_" + (i + 1)
										+ ".jpg").append("</a></br>");
					}
				} else {
					emailDataBuilder.append("&nbsp;");
				}
				emailDataBuilder.append("</td>").append("</tr>");

			}
		}
		emailDataBuilder.append("</table>").append("</div>").append(afterBody);
		emailContentBuilder.append(emailDataBuilder);

		return content = emailContentBuilder.toString();
	}

	public void log(TestMethodEntity testMethod, final boolean status, final String reason,
			TestClassEntity testClassEntity) {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String classAnnotationName = testClassEntity.getTestClassName();
		// if the testReportMap is empty, image folder needs to be created;
		// else set the image url that exists in the map to be the image folder.

		// if (testReportMap.isEmpty()) {
		// folderPath = TestApplicationContextBuilderImpl.getProperty(PathConstant.DATA_Folder)
		// + testClassEntity.getModelName() + "\\";
		// folderPath = folderPath + testClassEntity.getTestClassName() + " " + sdf.format(new
		// Date());
		// } else {
		// Map<String,List<TestReportEntity>> classReports= testReportMap.get(testClassName);
		// TestReportEntity reportEntity = null;
		// for (Entry<String, List<TestReportEntity>> entry :testReportMap.entrySet()) {
		// reportEntity = entry.getValue().get(0);
		// break;
		// }
		// if (reportEntity.getImageUrl().size() != 0) {
		// folderPath = reportEntity.getImageUrl().get(0);
		// folderPath = folderPath.substring(0, folderPath.lastIndexOf("\\"));
		// } else {
		// folderPath = TestApplicationContextBuilderImpl.getProperty(PathConstant.DATA_Folder)
		// + testClassEntity.getModelName() + "\\";
		// folderPath = folderPath + testClassEntity.getTestClassName() + " " + sdf.format(new
		// Date());
		// }
		// }

		// List<TestReportEntity> reportList =
		// testReportMap.getMethodReports(classAnnotationName,testMethod.getTestCaseID());
		//
		// if (reportList == null) {
		// reportList = new ArrayList<TestReportEntity>();
		// TestReportEntity testReportEntity = new TestReportEntity();
		// // testReportEntity.getImageUrl().add(imageUrl);
		// testReportEntity.setModelName(testMethod.getModelName());
		// testReportEntity.setReason(reason);
		// testReportEntity.setStatus(status);
		// testReportEntity.setTestMethodName(testMethod.getTestMethodName());
		// testReportEntity.setSummary(testMethod.getDesc());
		// testReportEntity.setTestCaseId(testMethod.getTestCaseID());
		// reportList.add(testReportEntity);
		// } else {
		// // not the first time execute. add the imge url into the report
		// // entity.
		// if (times < reportList.size()) {
		// TestReportEntity testReportEntity = reportList.get(times);
		// // testReportEntity.getImageUrl().add(imageUrl);
		// testReportEntity.setModelName(testMethod.getModelName());
		// testReportEntity.setReason(reason);
		// testReportEntity.setStatus(status);
		// testReportEntity.setTestMethodName(testMethod.getTestMethodName());
		// testReportEntity.setSummary(testMethod.getDesc());
		// testReportEntity.setTestCaseId(testMethod.getTestCaseID());
		// } else {
		// TestReportEntity testReportEntity = new TestReportEntity();
		// // testReportEntity.getImageUrl().add(imageUrl);
		// testReportEntity.setModelName(testMethod.getModelName());
		// testReportEntity.setReason(reason);
		// testReportEntity.setStatus(status);
		// testReportEntity.setTestMethodName(testMethod.getTestMethodName());
		// testReportEntity.setSummary(testMethod.getDesc());
		// testReportEntity.setTestCaseId(testMethod.getTestCaseID());
		// reportList.add(testReportEntity);
		// }
		// }
		TestReportEntity testReportEntity = new TestReportEntity();
		// testReportEntity.getImageUrl().add(imageUrl);
		testReportEntity.setModelName(testMethod.getModelName());
		testReportEntity.setReason(reason);
		testReportEntity.setStatus(status);
		testReportEntity.setTestMethodName(testMethod.getTestMethodName());
		testReportEntity.setSummary(testClassEntity.getParamValue(testMethod.getTestCaseID(), TestParameterHandler.CASE_SUMMARY));
		testReportEntity.setTestCaseId(testMethod.getTestCaseID());
		testReportMap.addReport(classAnnotationName, testMethod.getTestCaseID(), testReportEntity, times);
	}

	/**
	 * add the url of image which produced in the test case into the report.
	 * 
	 */
	public String appendImage(String testCaseID, String imageName, String testClassName, String modelName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String imageUrl = null;
		// TreeMap<String, List<TestReportEntity>> classReport =
		// testReportMap.getClassReports(testClassName);
		String folderPath = imageUrl = testReportMap.getFolderPath(testClassName);
		// if the testReportMap is empty, image folder needs to be created;
		// else set the image url that exists in the map to be the image folder.
		if (folderPath == null) {
			folderPath = TestApplicationContextBuilderImpl.getProperty(PathConstant.DATA_Folder) + modelName + "\\"
					+ testClassName + " " + sdf.format(new Date());
			testReportMap.putFolderPath(testClassName, folderPath);
		}
		// else {
		// TestReportEntity reportEntity = null;
		// for (Entry<String, List<TestReportEntity>> entry : classReport.entrySet()) {
		// reportEntity = entry.getValue().get(0);
		// break;
		// }
		// imageUrl = reportEntity.getImageUrl().get(0);
		// imageUrl = imageUrl.substring(0, imageUrl.lastIndexOf("\\") + 1);
		// }
		imageUrl = folderPath + "\\" + imageName;
		// if (reportList == null) {
		// reportList = new ArrayList<TestReportEntity>();
		// TestReportEntity testReportEntity = new TestReportEntity();
		// testReportEntity.setTestCaseId(testCaseID);
		// imageUrl = imageUrl + imageName;
		//
		// testReportEntity.getImageUrl().add(imageUrl);
		// reportList.add(testReportEntity);
		// } else {
		// // if the execute time is less than the size of report list,
		// // then the report entity of this time already exists,
		// // add the image url to the report entity.
		// // else the report entity needs to be established.
		// if (times < reportList.size()) {
		// TestReportEntity testReportEntity = reportList.get(times);
		// imageUrl = imageUrl + imageName;
		// testReportEntity.getImageUrl().add(imageUrl);
		// } else {
		// TestReportEntity testReportEntity = new TestReportEntity();
		// testReportEntity.setTestCaseId(testCaseID);
		// imageUrl = imageUrl + imageName;
		// testReportEntity.getImageUrl().add(imageUrl);
		// reportList.add(testReportEntity);
		// }
		// }
		// classReport.put(testCaseID, reportList);
		// testReportMap.putClassReports(testClassName, classReport);
		testReportMap.addImage(testClassName, testCaseID, imageUrl, times);
		return imageUrl;
	}
	@Override
	public void addClassSummary(String classAnnotationName,String key,String value){
		testReportMap.putSummary(classAnnotationName, key, value);
	}

	@Override
	public void writeFile(TestClassEntity testClassEntity) {
		// get the name from the folder name and set it to be the name of html file.
		String testClassName = testClassEntity.getTestClassName();
		String folderPath = testReportMap.getFolderPath(testClassName);
		String fileName =null;

		if (folderPath == null) {
			// no image captured ,so folder is not created
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			String htmlFolder = TestApplicationContextBuilderImpl.getProperty(PathConstant.DATA_Folder)
					+ testClassEntity.getModelName()+ "\\";
			File folder=new File(htmlFolder);
			if(!folder.exists()){
				folder.mkdirs();
			}
			fileName = htmlFolder+testClassEntity.getTestClassName() + " " + sdf.format(new Date())+".html";
		} else{
			fileName=folderPath+".html";
		}
		 
		try {
			FileOutputStream outPut = new FileOutputStream(fileName);
			outPut.write(content.getBytes());
			outPut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	public int timeAdd() {
		return times++;
	}
}
