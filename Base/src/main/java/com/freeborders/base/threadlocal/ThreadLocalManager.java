package com.freeborders.base.threadlocal;

/**
 *
 * @author nelson.yang
 */
public final class ThreadLocalManager <TestLoginInfoEntity>{
    private final ThreadLocal<TestLoginInfoEntity> threadLocal =  new ThreadLocal<TestLoginInfoEntity>();
    
    public void openThreadLocal(final TestLoginInfoEntity value){
            this.threadLocal.set(value);
    }
    
    public TestLoginInfoEntity getThreadLocal(){
        return this.threadLocal.get();
    }
    
    public void closeThreadLocal(){
        this.threadLocal.remove();
    }
    
}
