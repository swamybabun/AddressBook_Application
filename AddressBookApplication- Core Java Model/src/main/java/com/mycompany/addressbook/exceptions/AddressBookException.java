package com.mycompany.addressbook.exceptions;

/**
 * Customized Exception handler for this application.
 * 
 * @author Swamy
 */
public class AddressBookException extends Exception {

	private static final long serialVersionUID = 8111654635527650227L;

	public AddressBookException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressBookException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AddressBookException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AddressBookException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AddressBookException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}