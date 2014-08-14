package com.freeborders.base.enumeration;

/**
 * toString() is used  for excel sheet name , should not have \ / : * [ ],  but & is good .
 * And the length should less than 32 (31 is good)
 * @author tom.luo
 *
 */
public enum AdministrationPage {
	PLAINTIFF_ATTORNEYS {@Override 	public String toString() {return "Plaintiff Attorneys";}},
	AGREEMENTS {@Override	public String toString() {return "Agreements";}},
	
	DEFENSE_COUNSEL {@Override 	public String toString() {return "Defense Counsel";}},
	NEW_STAFF_APPROVAL{@Override   public String toString(){return "New Staff Approval";}},
	REQUEST_RATE_CHANGE{@Override   public String toString(){return "Request Rate Change";}},
	APPROVE_RATE_CHANGE{@Override   public String toString(){return "Approve Rate Change";}},
	APPROVE_NAME_LEDES_CHANGE{@Override   public String toString(){return "Approve Name & LEDES Change";}},
	VENDORS{@Override   public String toString(){return "Vendors";}},
	
	SPECIAL_PROJECTS{@Override   public String toString(){return "Special Projects";}},
	ACTIVITY_CODES{@Override   public String toString(){return "Activity Codes";}},
	OCCUPATION{@Override   public String toString(){return "Occupation";}},
	OI_STATE{@Override   public String toString(){return "OI State";}},
	LOOKUP_TABLES{@Override   public String toString(){return "Lookup Tables";}},
	SECURITY_ROLES{@Override   public String toString(){return "Security Roles";}},
	USERS{@Override   public String toString(){return "Users";}},
	VIEW_USER_HISTORY{@Override   public String toString(){return "View User History";}},
	BILL_INVOICE_DELETION_HISTORY{@Override   public String toString(){return "Bill & Invoice Deletion History";}},
	LOGINS{@Override   public String toString(){return "Logins";}},
	MASS_CHANGE_LOG{@Override   public String toString(){return "Mass Change Log";}},
	EXPORT_LOG{@Override   public String toString(){return "Export Log";}},
	OPTIONS{@Override   public String toString(){return "Options";}},
	USER_PREFERENCES{@Override   public String toString(){return "User Preferences";}},
	CASE_EXPORTS{@Override   public String toString(){return "Case Exports";}},
	IMPORT_SCANNED_DOCUMENTS{@Override   public String toString(){return "Import Scanned Documents";}},
	FREEZE_STATUS{@Override   public String toString(){return "Freeze Status";}},
	
}
