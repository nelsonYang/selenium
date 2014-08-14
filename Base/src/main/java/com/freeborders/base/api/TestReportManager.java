
package com.freeborders.base.api;

import com.freeborders.base.enumeration.DocumentType;

/**
 *
 * @author nelson
 */
public interface TestReportManager {
    
    public TestReportHandler getTestReportHandler(DocumentType docType);
    
}
