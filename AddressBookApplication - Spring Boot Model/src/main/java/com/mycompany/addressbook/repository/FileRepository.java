package com.mycompany.addressbook.repository;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.mycompany.addressbook.entities.Contact;
import com.mycompany.addressbook.exceptions.AddressBookException;
import com.mycompany.addressbook.utils.JSONFormatFileUtils;

/**
 * The repository as files to persist the data.
 * 
 * @author Swamy
 */
@Repository
@Qualifier("fileRepository")
@Primary
public class FileRepository implements BaseRepository {

	// Just change this class to make it to any type of repository : Database, In
	// memory..etc currently repository considered as file.
	@Autowired
	private JSONFormatFileUtils fileUtils;

	@Override
	public Collection<Contact> getContacts() throws AddressBookException {
		return fileUtils.getAllContactsAsCollection();
	}

	@Override
	public Contact getContact(Long contactId) throws AddressBookException {
		return fileUtils.getContactById(contactId);
	}

	@Override
	public Contact saveContact(Contact contact) throws AddressBookException {
		return fileUtils.saveContactAsJSON(contact);
	}

	@Override
	public Contact updateContact(Contact contact, Long contactId) throws AddressBookException {
		return fileUtils.updateContact(contact, contactId);
	}

	@Override
	public boolean deleteContact(Long contactId) throws AddressBookException {
		return fileUtils.deleteContact(contactId);
	}

	@Override
	public Map<Long, Contact> getContactsAsKeyValuePairs() throws AddressBookException {
		return fileUtils.getAllContactsAsKeyValuePairs();
	}
}