package com.freeborders.base.utils.excel.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.freeborders.base.utils.excel.ExcelCursor;
import com.freeborders.base.utils.excel.ExcelFactory;

public class TestCaseParser {
	public static void test2(){
		try {
			ExcelCursor cur=ExcelFactory.parseExcelFile("D:/files/testcase/testcase/selenium/Test Case - Administration.xlsx");
			cur.moveToSheet(1);
			Map<String ,String> params=cur.parseSheetParameters("B", new String[]{"G","H"},1);
			for (Entry<String, String> entry : params.entrySet()) {
				System.out.println(entry);
			}
			System.out.println("B".getBytes()[0]-64);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	


		public static class ScheduledExecutorTest implements Runnable {
			private String jobName = "";

			public ScheduledExecutorTest(String jobName) {
				super();
				this.jobName = jobName;
			}

			@Override
			public void run() {
				System.out.println("execute " + jobName);
			}

			
		}
		
		public static void test3(){
			ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

			long initialDelay1 = 1;
			long period1 = 1;
	        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
			service.scheduleAtFixedRate(
			        new ScheduledExecutorTest("job1"), initialDelay1,
					period1, TimeUnit.SECONDS);

			long initialDelay2 = 1;
			long delay2 = 2;
	        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
			service.scheduleWithFixedDelay(
			        new ScheduledExecutorTest("job2"), initialDelay2,
					delay2, TimeUnit.SECONDS);
		}
		/**
		输出结果:
		execute job1
		execute job1
		execute job2
		execute job1
		execute job1
		execute job2
		*/
		public static void main(String[] args) {
			test2();
		}
}
