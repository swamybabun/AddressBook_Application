package com.mycompany.addressbook.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.addressbook.configuration.ApplicationConstants;
import com.mycompany.addressbook.entities.Contact;
import com.mycompany.addressbook.exceptions.AddressBookException;

/**
 * The Utility for processing the files with JSON format for faster and easier
 * Maintenance.
 * 
 * @author Swamy
 */
public class JSONFormatFileUtils extends FileUtils {
	/**
	 * Converts the given object information to JSON string format.
	 * 
	 * @param data the object to be converted to JSON format
	 * @return the JSON String format
	 * @throws AddressBookException
	 */
	public String getAsJSON(Object data) throws AddressBookException {
		String jsonData = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			jsonData = objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			throw new AddressBookException("JSON Processing exception while converting an object to JSON format", e);
		}
		return jsonData;
	}

	/**
	 * Saves the {@link Contact} to the given file.
	 * 
	 * @param contact  the {@link Contact} to be saved in file
	 * @return the saved contact with auto generated identifier
	 * @throws AddressBookException
	 */
	public Contact saveContactAsJSON(Contact contact) throws AddressBookException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<Long, Contact> data = new HashMap<Long, Contact>();
		data.put(contact.getId(), contact);
		try {
			String fileName = getFileName(contact.getId());
			synchronized (JSONFormatFileUtils.class) {
				this.saveDataToFile(fileName, objectMapper.writeValueAsString(data));
			}
		} catch (JsonProcessingException e) {
			throw new AddressBookException("JSON Processing exception in saving a contact to file as JSON", e);
		}
		return contact;
	}

	/**
	 * Get all the contacts in key value pairs.
	 * 
	 * @param fileName the name of the file
	 * @return the content of the file in key value pairs.
	 * @throws AddressBookException
	 */
	public Map<Long, Contact> getAllContacts(final String fileName) throws AddressBookException {
		ObjectMapper objectMapper = new ObjectMapper();

		// read JSON file content and convert to contact objects
		Map<Long, Contact> idsContactMap = new LinkedHashMap<Long, Contact>();

		// Read Each Line and Convert to JSON Object
		List<String> fileContent = getFileContentAsStrings(fileName);

		for (String data : fileContent) {
			try {
				Map<Long, Contact> eachLine = objectMapper.readValue(data, new TypeReference<Map<Long, Contact>>() {
				});
				idsContactMap.putAll(eachLine);
			} catch (IOException e) {
				throw new AddressBookException("Internal Exception in reading all contacts from file", e);
			}
		}
		return idsContactMap;
	}

	/**
	 * Get the {@link Contact} by its identifier.
	 * 
	 * @param contactId the {@link Contact} identifier
	 * @return the retrieved {@link Contact}
	 * @throws AddressBookException
	 */
	public Contact getContactById(Long contactId) throws AddressBookException {
		String fileName = getFileName(contactId);
		Map<Long, Contact> allContacts = getAllContacts(fileName);
		return allContacts.get(contactId);
	}

	/**
	 * Get the file contents as collection
	 * 
	 * @return the collection of {@link Contact}'s.
	 * @throws AddressBookException
	 */
	public Collection<Contact> getAllContactsAsCollection() throws AddressBookException {
		String fileName = getFileName(null);
		return getAllContacts(fileName).values();
	}

	/**
	 * Get the file content in key value pairs.
	 * 
	 * @return the key value pairs of {@link Contact} information
	 * @throws AddressBookException
	 */
	public Map<Long, Contact> getAllContactsAsKeyValuePairs() throws AddressBookException {
		String fileName = getFileName(null);
		return this.getAllContacts(fileName);
	}

	/**
	 * Updated the given {@link Contact} with the supplied information.
	 * 
	 * @param fileName  the name of the file
	 * @param contact   the {@link Contact} data
	 * @param contactId the identifier of the {@link Contact}
	 * @return the updated {@link Contact} information
	 * @throws AddressBookException
	 */
	public Contact updateContact(Contact contact, Long contactId) throws AddressBookException {
		// Read data from file
		String fileName = getFileName(contactId);
		Map<Long, Contact> allContactsFromFile = this.getAllContacts(fileName);

		// Validate Address Book
		validateContacts(allContactsFromFile, null);

		// Update the contact with latest information
		allContactsFromFile.put(contactId, contact);

		// Save to the file
		updateFile(fileName, getAsJSON(allContactsFromFile));

		return contact;
	}

	/**
	 * Validates the contacts existence.
	 * 
	 * @param allContacts
	 * @param contactId
	 * @throws AddressBookException
	 */
	private void validateContacts(Map<Long, Contact> allContacts, Long contactId) throws AddressBookException {
		// If Address Book is empty
		if (allContacts != null && allContacts.isEmpty()) {
			System.out.println("The Address Book is empty..!");
			throw new AddressBookException("The Address Book is empty.!");
		}

		// If Requested Contact doesn't exists in address book
		if (contactId != null && !allContacts.containsKey(contactId)) {
			System.out.println("The selected contact ID doesn't exists in Address Book.! ");
			throw new AddressBookException("The selected contact ID doesn't exists in Address Book.! ");
		}
	}

	/**
	 * Deletes the selected {@link Contact} from the given file contents.
	 * 
	 * @param contactId the identifier of the {@link Contact}
	 * @return true if the {@link Contact} information is deleted successfully
	 * @throws AddressBookException
	 */
	public boolean deleteContact(Long contactId) throws AddressBookException {
		// Read Contacts from file
		String fileName = getFileName(contactId);
		Map<Long, Contact> allContactsFromFile = this.getAllContacts(fileName);

		// Validate the contact existence
		validateContacts(allContactsFromFile, contactId);

		// delete the selected contact by contactId
		allContactsFromFile.remove(contactId);

		// Processing As per Single or multi file approach
		if (ApplicationConstants.IS_SINGLE_FILE_DATA_STORE) {
			// Update the file with the latest information.
			if (allContactsFromFile.isEmpty()) {
				updateFile(fileName, new String());
			} else {
				updateFile(fileName, getAsJSON(allContactsFromFile));
			}
		} else {
			// If Multifile Data store then deleting the file after deleting the contact
			deleteFile(fileName);
		}
		return true;
	}
}