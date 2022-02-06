package com.mycompany.addressbook.configuration;

/**
 * Holds the application constants for the entire application.
 * 
 * @author Swamy
 */
public interface ApplicationConstants {
	// Regular Expressions for input validation
	static final String ALPHABET_REGEX = "[a-zA-Z]*$";
	static final String TELEPHONE_REGEX = "\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
	static final String EMAIL_REGEX = "[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

	// File related constants
	static final long STREAM_SIZE = 5;
	static final long RANDOM_NUMBER_ORIGIN = 1;
	static final long RANDOM_NUMBER_BOUND = 100000;

}
