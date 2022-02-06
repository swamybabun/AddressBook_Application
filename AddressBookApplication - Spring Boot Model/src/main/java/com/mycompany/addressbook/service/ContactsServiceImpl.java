package com.mycompany.addressbook.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.addressbook.configuration.ApplicationConstants;
import com.mycompany.addressbook.entities.Contact;
import com.mycompany.addressbook.exceptions.AddressBookException;
import com.mycompany.addressbook.repository.BaseRepository;

/**
 * Service implementation for the contacts operations.
 * 
 * @author Swamy
 * @version 1.0
 */
@Service
public class ContactsServiceImpl implements ContactsService {

	@Autowired
	@Qualifier("fileRepository")
	private BaseRepository repository;

	@Override
	public List<Contact> getContacts() throws AddressBookException {
		// Sort the contacts by Surname and return the sorted collection
		List<Contact> contactsSorted = repository.getContacts().parallelStream()
				.sorted(Comparator.comparing(Contact::getSurname)).collect(Collectors.toList());
		return contactsSorted;
	}

	@Override
	public Contact getContact(Long contactId) throws AddressBookException {
		return repository.getContact(contactId);
	}

	@Override
	public Contact saveContact(Contact contact) throws AddressBookException {
		// Generating the Random ID
		LongStream longs = new Random().longs(ApplicationConstants.STREAM_SIZE,
				ApplicationConstants.RANDOM_NUMBER_ORIGIN, ApplicationConstants.RANDOM_NUMBER_BOUND);
		long randomId = longs.findFirst().getAsLong();
		contact.setId(randomId);
		return repository.saveContact(contact);
	}

	@Override
	public Contact updateContact(Contact contact, Long contactId) throws AddressBookException {
		contact.setId(contactId);
		return repository.updateContact(contact, contactId);
	}

	@Override
	public boolean deleteContact(Long contactId) throws AddressBookException {
		return repository.deleteContact(contactId);
	}

	@Override
	public Map<Long, Contact> getContactsAsKeyValuePairs() throws AddressBookException {
		return repository.getContactsAsKeyValuePairs();
	}

	@Qualifier("fileRepository")
	public void setRepository(BaseRepository repository) {
		this.repository = repository;
	}
}