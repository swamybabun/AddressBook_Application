package com.mycompany.addressbook.controllers.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mycompany.addressbook.configuration.RestURIConstants;
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
@RestController
@RequestMapping(value = RestURIConstants.REST_CONTACTS)
public class RestContactsController {

	@Autowired
	private ContactsService contactsService;

	/**
	 * Retrieves all the contacts from Address book.
	 * 
	 * @return the contacts in {@link ResponseEntity}
	 */
	@GetMapping
	public ResponseEntity<List<Contact>> getAll() {
		ResponseEntity<List<Contact>> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<List<Contact>>(contactsService.getContacts(), HttpStatus.OK);
		} catch (AddressBookException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return responseEntity;
	}

	/**
	 * Saves the contact with given information in to the Address book.
	 * 
	 * @return {@link HttpStatus#CREATED} if contact is created successfully.
	 */
	@PostMapping
	public ResponseEntity<Contact> save(@RequestBody Contact contact) {
		ResponseEntity<Contact> responseEntity = null;
		try {
			contact = contactsService.saveContact(contact);
			responseEntity = new ResponseEntity<Contact>(contact, HttpStatus.CREATED);
		} catch (AddressBookException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		return responseEntity;
	}

	/**
	 * Retrieves the selected contact from Address book.
	 * 
	 * @return the {@link ResponseEntity} with retrieved contact information.
	 */
	@GetMapping(value = RestURIConstants.ID)
	public ResponseEntity<Contact> get(@PathVariable Long id) {
		ResponseEntity<Contact> responseEntity = null;
		try {
			Contact contact = contactsService.getContact(id);
			responseEntity = new ResponseEntity<Contact>(contact, HttpStatus.OK);
		} catch (AddressBookException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return responseEntity;
	}

	/**
	 * Updates the selected contact with the given information.
	 * 
	 * @param contact the {@link Contact} with the latest information
	 * @param id      the identifier of the contact
	 * @return the updated contact in {@link ResponseEntity} with
	 *         {@link HttpStatus#OK} as status code if update is successful.
	 */
	@PutMapping(value = RestURIConstants.ID)
	public ResponseEntity<Contact> update(@RequestBody Contact contact, @PathVariable Long id) {
		ResponseEntity<Contact> responseEntity = null;
		try {
			contact = contactsService.updateContact(contact, id);
			responseEntity = new ResponseEntity<Contact>(contact, HttpStatus.OK);
		} catch (AddressBookException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return responseEntity;
	}

	/**
	 * Deletes the requested contact.
	 * 
	 * @param id the identifier of the contact to be deleted.
	 * @return the {@link ResponseEntity} with true and {@link HttpStatus#OK} if
	 *         deletion is successful.
	 */
	@DeleteMapping(value = RestURIConstants.ID)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		ResponseEntity<Boolean> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<Boolean>(contactsService.deleteContact(id), HttpStatus.OK);
		} catch (AddressBookException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return responseEntity;
	}

	/**
	 * Retrieves the entire address book in key value pairs as contact identifiers
	 * and contacts for convenience.
	 * 
	 * @return the {@link ResponseEntity} with key value pairs of contacts
	 */
	@GetMapping(value = RestURIConstants.CONTACTS_KEYVALUES)
	public ResponseEntity<Map<Long, Contact>> getContactsAsKeyValuePairs() {
		ResponseEntity<Map<Long, Contact>> responseEntity = null;
		try {
			responseEntity = new ResponseEntity<Map<Long, Contact>>(contactsService.getContactsAsKeyValuePairs(),
					HttpStatus.OK);
		} catch (AddressBookException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

		}
		return responseEntity;
	}
}