package com.mycompany.addressbook.service;

import java.util.List;
import java.util.Map;

import com.mycompany.addressbook.entities.Contact;
import com.mycompany.addressbook.exceptions.AddressBookException;

/**
 * The service for managing the contacts and their information.
 * 
 * @author Swamy
 */
public interface ContactsService {
	/**
	 * Retrieves all the {@link Contact}'s.
	 * 
	 * @return all the {@link Contact}'s
	 * @throws AddressBookException
	 */
	public List<Contact> getContacts() throws AddressBookException;

	/**
	 * Retrieve the {@link Contact} by its identifier.
	 * 
	 * @param contactId
	 * @return the {@link Contact}
	 * @throws AddressBookException
	 */
	public Contact getContact(Long contactId) throws AddressBookException;

	/**
	 * Saves the {@link Contact} with the given information
	 * 
	 * @param the {@link Contact} to persist
	 * @return the saved {@link Contact}
	 * @throws AddressBookException
	 */
	public Contact saveContact(Contact contact) throws AddressBookException;

	/**
	 * Updates the {@link Contact} with the latest supplied information.
	 * 
	 * @param contact   the {@link Contact} with the latest information
	 * @param contactId the identifier of contact
	 * @return the updated {@link Contact}
	 * @throws AddressBookException
	 */
	public Contact updateContact(Contact contact, Long contactId) throws AddressBookException;

	/**
	 * Deletes the contact.
	 * 
	 * @param contactId the identifier of the contact.
	 * @return true if deleted successfully
	 * @throws AddressBookException
	 */
	public boolean deleteContact(Long contactId) throws AddressBookException;

	/**
	 * Retrieves all the contacts in key value pairs for convenience.
	 * 
	 * @return the contacts as key value pairs (contactId, {@link Contact}) in
	 *         {@link Map}
	 * @throws AddressBookException
	 */
	public Map<Long, Contact> getContactsAsKeyValuePairs() throws AddressBookException;
}