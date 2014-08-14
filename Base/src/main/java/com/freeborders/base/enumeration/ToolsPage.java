package com.freeborders.base.enumeration;

public enum ToolsPage {
	LISTS {
		public String toString() {
			return "Lists";
		}
	},

	QUERIES {
		public String toString() {
			return "Queries";
		}
	},
	SETTLEMENT_GROUPS {
		public String toString() {
			return "Settlement Groups";
		}
	},
	PAYMENTS {
		public String toString() {
			return "Payments";
		}
	},
	BULK_DATA_LOAD {
		public String toString() {
			return "Bulk Data Load";
		}
	},
	CHANGE_DATABASE {
		public String toString() {
			return "Change Database";
		}
	}

}
