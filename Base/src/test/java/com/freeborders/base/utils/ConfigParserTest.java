/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.freeborders.base.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author nelson.yang
 */
public class ConfigParserTest extends TestCase {
    
    public ConfigParserTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of findClassInJar method, of class ClassParser.
     */
    public void testFindClassInJar() {
        
              /*    System.out.println("findClassInJar");
                  List<Class> clazzList = new ArrayList<Class>();
                  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                 
               //   File file = new File("E://maven-model-3.0.4.jar");*/
                 // File file = new File("E:/test/");
                  String [] packageName = new String[]{"com.freeborders"};
                  ClassParser instance = new ClassParser();
                  instance.parse(packageName);
                  //instance.findClassInJar(clazzList, classLoader,file);
                  //instance.findClass(clazzList, classLoader, file);
           
            //  String str = "E:/test/test/com/ehr/Site.class";
             // System.out.println(str.substring(0,str.length()-6));
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            Enumeration<URL> eUrl;
//        try {
//            eUrl = classLoader.getResources("com/freeborders");
//            while(eUrl.hasMoreElements()){
//                URL url = eUrl.nextElement();
//                System.out.println(url.getFile());
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(ConfigParserTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
       
        
    }

    
}
