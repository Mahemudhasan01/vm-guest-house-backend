package com.vm.guesthouse.enums;

public class Enums {
	public enum BookingStatus {
	    RESERVED,
	    CHECKED_IN,
	    CHECKED_OUT,
	    CANCELLED
	}
	
	public enum BookingSource {
	    WALK_IN,
	    ONLINE,
	    CORPORATE,
	    PHONE,
	    AGENT
	}
	
	public enum DocumentType {
	    AADHAAR,
	    PAN,
	    PASSPORT,
	    DRIVING_LICENSE,
	    VOTER_ID,
	    OTHER
	}
	
	public enum RoomStatus {
	    VACANT,
	    OCCUPIED,
	    CLEANING,
	    MAINTENANCE,
	    RESERVED
	}
	
	public enum InvoiceStatus {
	    PENDING,
	    PARTIAL,
	    PAID,
	    CANCELLED
	}
	
	public enum InvoiceItemType {
	    ROOM,
	    FOOD,
	    LAUNDRY,
	    EXTRA_PERSON,
	    SERVICE,
	    TAX,
	    DISCOUNT,
	    OTHER
	}
	
	public enum PaymentMethod {
	    CASH,
	    UPI,
	    CARD,
	    BANK_TRANSFER,
	    ONLINE
	}
	
	public enum PaymentStatus {
	    SUCCESS,
	    FAILED,
	    REFUNDED,
	    PENDING
	}
	
	public enum UserRole {
	    SUPER_ADMIN,
	    ADMIN,
	    MANAGER,
	    RECEPTIONIST,
	    ACCOUNTANT
	}
	
	public enum AuditActionType {
	    CREATE,
	    UPDATE,
	    DELETE,
	    LOGIN,
	    LOGOUT,
	    CHECK_IN,
	    CHECK_OUT,
	    PAYMENT,
	    CANCEL
	}
}
