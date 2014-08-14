package com.freeborders.base.enumeration;

public enum CasesPage {
	FIND_CASES{@Override   public String toString(){return "Find Cases";}},
	NEW_CASE{@Override   public String toString(){return "New Case";}},
	NEW_BATCH{@Override   public String toString(){return "New Batch";}},
	CREATE_BATCH_FROM_EXISTING_CASE{@Override   public String toString(){return "Create Batch from Existing Case";}},
	BATCHES{@Override   public String toString(){return "Batches";}},
	
	PROVISIONAL_CASES{@Override   public String toString(){return "Provisional Cases";}},
	HOT_CASES{@Override   public String toString(){return "Hot Cases";}},
	DISCOVERY_TRACKER{@Override   public String toString(){return "Discovery Tracker";}},
	DISCOVERY_HISTORY_LOAD{@Override   public String toString(){return "Discovery History Load";}},
	PROPERTY_DAMAGE{@Override   public String toString(){return "Property Damage";}},
}
