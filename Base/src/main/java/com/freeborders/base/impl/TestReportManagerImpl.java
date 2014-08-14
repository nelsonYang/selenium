package com.freeborders.base.impl;

import java.util.EnumMap;
import java.util.Map;

import com.freeborders.base.api.TestReportHandler;
import com.freeborders.base.api.TestReportManager;
import com.freeborders.base.enumeration.DocumentType;

/**
 *
 * @author nelson
 */
public final class TestReportManagerImpl implements TestReportManager{
    private static final Map<DocumentType,TestReportHandler> testReportHandlerMap = new EnumMap<DocumentType,TestReportHandler>(DocumentType.class);
   
    static{
        testReportHandlerMap.put(DocumentType.DOC, new TestReportDocHandlerImpl());
        testReportHandlerMap.put(DocumentType.HTML, new TestReportHtmlHandlerImpl());
    }
    @Override
    public TestReportHandler getTestReportHandler(DocumentType docType) {
        return testReportHandlerMap.get(docType);
    }
    
}
