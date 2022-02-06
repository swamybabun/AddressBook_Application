package com.mycompany.addressbook.repository;

import java.util.Collection;
import java.util.Map;

import com.mycompany.addressbook.entities.Contact;
import com.mycompany.addressbook.exceptions.AddressBookException;

/**
 * The repository to persist in databases. Writing this class as a place holder
 * for persisting it to a different repository.
 * 
 * @author Swamy
 */
public class ContactsRepository implements BaseRepository {

	@Override
	public Collection<Contact> getContacts() throws AddressBookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact getContact(Long contactId) throws AddressBookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact saveContact(Contact contact) throws AddressBookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact updateContact(Contact contact, Long contactId) throws AddressBookException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteContact(Long contactId) throws AddressBookException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<Long, Contact> getContactsAsKeyValuePairs() throws AddressBookException {
		// TODO Auto-generated method stub
		return null;
	}
}