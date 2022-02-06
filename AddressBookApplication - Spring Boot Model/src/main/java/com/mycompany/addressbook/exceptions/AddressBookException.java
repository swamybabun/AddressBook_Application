package com.mycompany.addressbook.exceptions;

/**
 * Customized Exception handler for this application.
 * 
 * @author Swamy
 */
public class AddressBookException extends RuntimeException {

	private static final long serialVersionUID = 8111654635527650227L;

	public AddressBookException() {
		super();
	}

	public AddressBookException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressBookException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressBookException(String message) {
		super(message);
	}

	public AddressBookException(Throwable cause) {
		super(cause);
	}
}