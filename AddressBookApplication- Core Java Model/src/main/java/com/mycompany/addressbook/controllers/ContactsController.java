package com.mycompany.addressbook.controllers;

import java.util.List;
import java.util.Map;

import com.mycompany.addressbook.entities.Contact;
import com.mycompany.addressbook.exceptions.AddressBookException;
import com.mycompany.addressbook.service.ContactsService;

/**
 * Controller for managing and processing the contacts and their Category
 * related information.
 * 
 * @author Swamy
 * @version 1.0
 */
public class ContactsController {

	private ContactsService contactsService;

	public void setContactsService(ContactsService contactsService) {
		this.contactsService = contactsService;
	}

	// Get All
	public List<Contact> getAll() throws AddressBookException {
		return contactsService.getContacts();
	}

	// Save
	public Contact save(Contact contact) throws AddressBookException {
		return contactsService.saveContact(contact);
	}

	// Find
	public Contact get(Long id) throws AddressBookException {
		return contactsService.getContact(id);
	}

	// Update
	public Contact update(Contact contact, Long id) throws AddressBookException {
		return contactsService.updateContact(contact, id);
	}

	// Delete
	public boolean delete(Long id) throws AddressBookException {
		return contactsService.deleteContact(id);
	}

	public Map<Long, Contact> getContactsAsKeyValuePairs() throws AddressBookException {
		return contactsService.getContactsAsKeyValuePairs();
	}
}