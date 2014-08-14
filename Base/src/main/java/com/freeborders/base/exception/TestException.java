package com.freeborders.base.exception;

/**
 *
 * @author nelson.yang
 */
public class TestException  extends RuntimeException{
    private String expection;
    private String result;
    public TestException(String message,String expection,String result){
        super(message);
        this.expection = expection;
        this.result = result;
    }
    
   public TestException(String message){
       super(message);
   }
}
