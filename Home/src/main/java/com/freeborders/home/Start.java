package com.freeborders.home;

import com.freeborders.base.api.TestExecutor;
import com.freeborders.base.context.TestAbstractApplicationContextBuilder;
import com.freeborders.base.context.TestApplicationContextBuilderImpl;
import com.freeborders.base.impl.TestExecutorImpl;

/**
 *
 */
public class Start {

    public static void main(String[] args) {
        //init the applicationContext
        TestAbstractApplicationContextBuilder testApplicationContextBuilder = new TestApplicationContextBuilderImpl();
        testApplicationContextBuilder.build();
        //do
        TestExecutor testExecutor = new TestExecutorImpl();
        testExecutor.test();
       
//        System.setProperty("webdriver.ie.driver", "E:\\IEDriverServer.exe");
//        WebDriver webDriver = new InternetExplorerDriver();
//        webDriver.get("http://localhost:8080/TestModalDialog/index.jsp");
//        webDriver.findElement(By.id("next")).click();
//        String mainWindow = webDriver.getWindowHandle();
//        Set<String> windows = webDriver.getWindowHandles();
//        System.out.println(" mainWindow = " + mainWindow);
//       // WebDriver webDriver = null;
//         System.out.println(" windows.size= " + windows.size());
//        for (String window : windows) {
//            System.out.println(" window = " + window);
//            if (!mainWindow.equals(window)) {
//                webDriver = webDriver.switchTo().window(window);
//                break;
//            }
//        }
//
//        if (webDriver != null) {
//            //注意这里返回值是另外一个window或者flame定位webDriver对象所以要注意
//            webDriver.findElement(By.id("search")).click();
//            String mainWindow1 = webDriver.getWindowHandle();
//            windows.clear();
//            windows = webDriver.getWindowHandles();
//            System.out.println(" mainWindow 11= " + mainWindow);
//             System.out.println(" windows11.size= " + windows.size());
//            for (String window1 : windows) {
//                System.out.println(" window 11= " + window1);
//                if (!mainWindow.equals(window1) && !mainWindow1.equals(window1)) {
//                     webDriver = webDriver.switchTo().window(window1);
//                    webDriver.findElement(By.id("fullname")).sendKeys("hello");
//                }
//            }
//        }
//
////        webDriver.switchTo().window(mainWindow);
////        webDriver.findElement(By.id("search")).click();
//
//        //      webDriver.get("http://www.google.com.hk");
//        //
//        //    /html/head[1]   /html/head[last()]
////        List<WebElement> list = webDriver.findElements(By.xpath("/html/head[last()]"));
////        System.out.println("........");
////        for(WebElement elem : list){
////            System.out.println(elem.getText());
////        }
////        webDriver.quit();
//
//
////        WebDriver webDriver = new FirefoxDriver();
////         webDriver.get("https://developer.mozilla.org/samples/domref/showModalDialog.html");
////         String mainWindow = webDriver.getWindowHandle();
////         Set<String> windows = webDriver.getWindowHandles();
////         System.out.println(windows.size());
////         webDriver.findElement(By.xpath("//input")).click();
////         
////         webDriver.switchTo().alert().sendKeys("http://developer.mozilla.org/samples/domref/showModalDialogBox.html");
////        //   webDriver.switchTo().window(webDriver.getWindowHandle());
////        // webDriver.switchTo().alert();
////      //   Set<String> windows = webDriver.getWindowHandles();
////         for(String window : windows){
////             System.out.println(window);
////             if(!mainWindow.equals(window)){
////              webDriver.switchTo().window(window);
////               webDriver.findElement(By.id("foo")).sendKeys("hello");
////           
////             }
////         }


    }
}