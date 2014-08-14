package com.freeborders.base.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.freeborders.base.api.TestParameterHandler;
import com.freeborders.base.constant.PathConstant;
import com.freeborders.base.context.TestApplicationContextBuilderImpl;
import com.freeborders.base.enumeration.AdministrationPage;
import com.freeborders.base.enumeration.BillingPage;
import com.freeborders.base.enumeration.ModelNameEnum;
import com.freeborders.base.enumeration.ToolsPage;
import com.freeborders.base.utils.excel.ExcelCursor;
import com.freeborders.base.utils.excel.ExcelFactory;

public class TestParameterHandlerImpl implements TestParameterHandler {
	Log log = LogFactory.getLog(TestParameterHandlerImpl.class);
	private Map<ModelNameEnum, String> messages = new HashMap<ModelNameEnum, String>();
	// Left to right:model,testClass(excelSheet),testCaseId,parameters. 3 level map
	private Map<Enum<?>, Map<String, Map<String, String>>> params = new HashMap<Enum<?>, Map<String, Map<String, String>>>();

	@Override
	public boolean parseTestCase(Set<ModelNameEnum> model) {
		ExecutorService service = Executors.newCachedThreadPool();
		int timeout = 30;
		for (ModelNameEnum modelNameEnum : model) {
			parseExcelWorker worker = new parseExcelWorker(modelNameEnum);
			Future<?> future = null;
			try {
				future = service.submit(worker);
				future.get(timeout * 1000, TimeUnit.MILLISECONDS);// finish time
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				future.cancel(true);
			}
		}
		service.shutdown();
		return true;
	}

	synchronized void addPara(Enum<?> model, Map<String, Map<String, String>> testClassParams) {
		params.put(model, testClassParams);
	}

	class parseExcelWorker extends Thread {
		private ModelNameEnum model;

		public parseExcelWorker(ModelNameEnum model) {
			this.model = model;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String message = "true";
			TestParameterHandlerImpl impl = TestParameterHandlerImpl.this;
			try {
				// TODO Auto-generated method stub
				StringBuffer location = new StringBuffer(
						TestApplicationContextBuilderImpl.getProperty(PathConstant.TEST_CASE_FOLDER));
				// StringBuffer location = new
				// StringBuffer("D:\\files\\testcase\\testcase\\selenium");
				if (!(location.toString().endsWith("/") || location.toString().endsWith("\\"))) {
					location.append("/");
				}
				File dir = new File(location.toString());
				if (!dir.exists()) {
					dir.mkdir();
				}
				Class<? extends Enum> pageEnumClass = null;
				switch (model) {
				case CASES:

					break;
				case TOOLS:
					location.append("Test Case - Tools.xlsx");
					pageEnumClass = ToolsPage.class;
					break;
				case BILLING:
					location.append("Test Case - Billing.xlsx");
					pageEnumClass = BillingPage.class;
					break;
				case REPORTS:

					break;
				case ADMINISTRATION:
					location.append("Test Case - Administration.xlsx");
					pageEnumClass = AdministrationPage.class;
					break;
				default:
					break;
				}
				ExcelCursor curs = ExcelFactory.parseExcelFile(location.toString());
				// sheetName,testCase Id, parameters
				Map<String, Map<String, String>> testClassParams = new HashMap<String, Map<String, String>>();
				while (curs.moveNextSheet()) {
					Map<String, String> pageParams = curs.parseSheet("B", new String[] { "D" }, 0, new String[] { "G",
							"H" }, 1);
					String sheetName = curs.currentSheet().getSheetName();
					// Enum<?> classEnum = TestEnumUtils.valueByString(pageEnumClass, sheetName);
					// if (classEnum == null) {
					// try {
					// throw new TestException("Error,Excel sheet name '" + sheetName +
					// "' is not correct");
					// } catch (Exception e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					// continue;
					// }
					testClassParams.put(sheetName, pageParams);
					impl.addPara(model, testClassParams);
				}
				curs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				StringWriter writer = new StringWriter();
//				e.printStackTrace(new PrintWriter(writer));
//				message = writer.getBuffer().toString();
				log.error(e.getMessage());
			}

			impl.putMessage(model, message);
		}
	}

	@Override
	public Map<ModelNameEnum, String> getResult() {
		// TODO Auto-generated method stub
		return messages;
	}

	public synchronized void putMessage(ModelNameEnum model, String messag) {
		messages.put(model, messag);
	}

	public Map<Enum<?>, Map<String, Map<String, String>>> getParams() {
		return this.params;
	}
}
