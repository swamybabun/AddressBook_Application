package com.mycompany.addressbook.repository;

import java.util.Collection;
import java.util.Map;

import com.mycompany.addressbook.entities.Contact;
import com.mycompany.addressbook.exceptions.AddressBookException;

/**
 * Base repository for persistence and management of contacts.
 * 
 * @author Swamy
 */
public interface BaseRepository {

	/**
	 * Retrieves all the contacts from address book.
	 * 
	 * @return the contacts
	 * @throws AddressBookException
	 */
	Collection<Contact> getContacts() throws AddressBookException;

	/**
	 * Gets a contact with the given contact identifier.
	 * 
	 * @param contactId the contact identifier
	 * @return the retrieved contact information
	 * @throws AddressBookException
	 */
	Contact getContact(Long contactId) throws AddressBookException;

	/**
	 * Saves the contact with the given information.
	 * 
	 * @param contact the contact information to be saved
	 * @return the saved contact
	 * @throws AddressBookException
	 */
	Contact saveContact(Contact contact) throws AddressBookException;

	/**
	 * Updates the contact with the given latest information.
	 * 
	 * @param contact   the contact with latest information
	 * @param contactId the identifier of the contact
	 * @return the updated contact
	 * @throws AddressBookException
	 */
	Contact updateContact(Contact contact, Long contactId) throws AddressBookException;

	/**
	 * Deletes the selected contact.
	 * 
	 * @param contactId the identifier of the contact to be deleted.
	 * @return true if contact is deleted successfully
	 * @throws AddressBookException
	 */
	boolean deleteContact(Long contactId) throws AddressBookException;

	/**
	 * Retrieves the contacts information as key value pairs
	 * 
	 * @return the contacts as key value pairs with identifiers
	 * @throws AddressBookException
	 */
	Map<Long, Contact> getContactsAsKeyValuePairs() throws AddressBookException;
}
