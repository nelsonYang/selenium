package com.freeborders.base.enumeration;

public enum BillingPage {
	DEFENSE_COUNSEL_CONTROL_PANEL{@Override   public String toString(){return "DEFENSE COUNSEL CONTROL PANEL";}},
	
	DC_INVOICE_APPROVE{@Override   public String toString(){return "DC Invoice Approve";}},
	DC_INVOICE_VIEW{@Override   public String toString(){return "DC Invoice View";}},
	DC_INVOICE_PAY{@Override   public String toString(){return "DC Invoice Pay";}},
	DC_INVOICE_RECORD_PAYMENT{@Override   public String toString(){return "DC Invoice Record Payment";}},
	
	DC_IMPORT_FEES_EXPENSES{@Override   public String toString(){return "DC Import Fees Expenses";}},
	DCBA_IMPORT_BILLING_HISTORY{@Override   public String toString(){return "DCBA Import Billing History";}},
	
	VENDOR_CONTROL_PANEL{@Override   public String toString(){return "Vendor Control Panel";}},
	VENDOR_NEW_INVOICE{@Override   public String toString(){return "Vendor New Invoice";}},
	VENDOR_INVOICE_REVIEW_APPROVE{@Override   public String toString(){return "Vendor Invoice Review Approve";}},
	VENDOR_INVOICE_PAY{@Override   public String toString(){return "Vendor Invoice Pay";}},
	VENDOR_INVOICE_RECORD_PAYMENT{@Override   public String toString(){return "Vendor Invoice Record Payment";}},
}
