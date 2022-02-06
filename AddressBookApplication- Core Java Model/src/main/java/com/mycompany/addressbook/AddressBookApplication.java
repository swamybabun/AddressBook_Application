package com.mycompany.addressbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import com.mycompany.addressbook.configuration.ApplicationConstants;
import com.mycompany.addressbook.controllers.ContactsController;
import com.mycompany.addressbook.entities.CategoryName;
import com.mycompany.addressbook.entities.Contact;
import com.mycompany.addressbook.entities.ContactCategory;
import com.mycompany.addressbook.entities.FamilyRelationships;
import com.mycompany.addressbook.exceptions.AddressBookException;
import com.mycompany.addressbook.repository.FileRepository;
import com.mycompany.addressbook.service.ContactsServiceImpl;
import com.mycompany.addressbook.utils.JSONFormatFileUtils;

/**
 * The Stand Alone point for running the Address book application.
 * 
 * @author Swamy
 * @version 1.0
 */
public class AddressBookApplication {
	/**
	 * The Stand alone Starter for Address Book Application.
	 * 
	 * @param args
	 * @throws AddressBookException
	 */
	public static void main(String[] args) throws AddressBookException {
		// Application STARTER
		AddressBookApplication addressBookApplication = new AddressBookApplication();

		// Initialize the application related dependent objects : Standalone
		ContactsController contactsController = initApplication();

		// Display for options of the Address Book
		while (true) {
			try {
				System.out.println("\n ================ Welcome to the Address Book Application ===================");

				System.out.println("The current configuration of Address Book is on Single File :"
						+ ApplicationConstants.IS_SINGLE_FILE_DATA_STORE);
				System.out.println(
						"Please select anyone of the following options  \n(1): Option 1 - Add a Contact \n(2): Option 2 - Read a Contact \n(3): Option 3 - Update a Contact \n(4): Option 4 - Get All Contacts \n(5): Option 5 - Delete a Contact \n(6): Exit \n");

				Scanner scanner = new Scanner(System.in);
				int option = scanner.nextInt();
				if (option == 6) {
					System.out.println("Exiting from the Application..!");
					System.exit(0);
				}

				// Selecting the requested operation
				switch (option) {
				case 1:
					saveContact(addressBookApplication, contactsController, scanner);
					break;
				case 2:
					getContact(contactsController, scanner);
					break;
				case 3:
					updateContact(addressBookApplication, contactsController, scanner);
					break;
				case 4:
					getTheContacts(contactsController);
					break;
				case 5:
					deleleContact(contactsController, scanner);
					break;
				default:
					break;
				}
			} catch (Exception e) {
				System.err.println("Invalid input... Please rerty the operation by reentering the correct input. ");
			}
		}
	}

	/**
	 * Prepares the {@link Contact} with the user given information for persisting
	 * it to the repository.
	 * 
	 * @param addressBookStandAlone
	 * @param contactsController
	 * @param scanner
	 */
	private static void saveContact(AddressBookApplication addressBookStandAlone, ContactsController contactsController,
			Scanner scanner) {
		System.out.println("You Have Selected for Adding a new contact.. \n");
		Contact contact = addressBookStandAlone.getContactInput(new Contact(), scanner);
		try {
			contact = contactsController.save(contact);
			System.out.println("Your contact is saved successfully and its generated id is " + contact.getId());
		} catch (AddressBookException e) {
			System.err.println("Error while saving the contact.." + e.getMessage());
		}
	}

	/**
	 * Prepares the {@link Contact} with the user given information for retrieval.
	 * 
	 * @param contactsController
	 * @param scanner
	 * @throws AddressBookException
	 */
	private static void getContact(ContactsController contactsController, Scanner scanner) throws AddressBookException {
		Contact contact;
		System.out.println("You Have Selected for Reading the existing contact.. Please Enter the contactId:  \n");
		Long contactId = scanner.nextLong();
		contact = contactsController.get(contactId);

		if (contact == null) {
			System.out.println("The contact with the given ID doesn't exists in Address Book..!");
		} else {
			System.out.println("The contact details are...");
			System.out.println(contact);
		}
	}

	/**
	 * Prepares the {@link Contact} with the user given information for updation.
	 * 
	 * @param addressBookStandAlone
	 * @param contactsController
	 * @param scanner
	 * @throws AddressBookException
	 */
	private static void updateContact(AddressBookApplication addressBookStandAlone,
			ContactsController contactsController, Scanner scanner) throws AddressBookException {
		Contact contact;
		Long contactId;
		System.out
				.println("You Have Selected for Updating the contact.. \n Here are the exising contacts for reference");
		for (Entry<Long, Contact> contactInfo : contactsController.getContactsAsKeyValuePairs().entrySet()) {
			System.out.println(contactInfo.getKey() + " ----> " + contactInfo.getValue());
		}
		System.out.println("Select the Contact by entering the id of it for updation");
		contactId = scanner.nextLong();

		Contact existingContactInfo = contactsController.get(contactId);

		if (existingContactInfo == null) {
			System.out.println("The Selected contact is invalid..");
		} else {
			System.out.println("Enter the New data to be updated when prompted...");
			contact = addressBookStandAlone.getContactInput(existingContactInfo, scanner);
			try {
				contact = contactsController.update(contact, contactId);
				System.out.println("Your contact is updated successfully..");
			} catch (AddressBookException e) {
				System.err.println("Error while updating the contact.." + e.getMessage());
			}
		}
	}

	/**
	 * Receives the {@link Contact} identifier from the user for deletion.
	 * 
	 * @param contactsController
	 * @param scanner
	 * @throws AddressBookException
	 */
	private static void deleleContact(ContactsController contactsController, Scanner scanner)
			throws AddressBookException {
		Long contactId;
		System.out.println("You have selected for deleting the contact");
		List<Contact> allContacts = contactsController.getAll();
		if (allContacts.isEmpty()) {
			System.out.println("The Address book is empty");
		} else {
			System.out.println("enter the contact id to delete");
			contactId = scanner.nextLong();
			boolean deleteContact = contactsController.delete(contactId);
			if (deleteContact)
				System.out.println("The Selected contact has been deleted");
		}
	}

	/**
	 * Input receiver for the get All contacts.
	 * 
	 * @param contactsController
	 * @throws AddressBookException
	 */
	private static void getTheContacts(ContactsController contactsController) throws AddressBookException {
		System.out.println("You Have Selected for Reading All the contacts.. \n");
		List<Contact> allContacts = contactsController.getAll();

		if (allContacts.isEmpty()) {
			System.out.println("The address book is empty..");
		} else {
			System.out.println("The Existing contacts in Address Book are below (Sorted by Surname).... \n");

			System.out.println("");
			for (Contact contactInfo : allContacts) {
				System.out.println(contactInfo.getId() + " ----> " + contactInfo);
			}
		}
	}

	/**
	 * Prepares the Input for {@link Contact} from the user for all operations.
	 * 
	 * @param contact
	 * @param scanner
	 * @return {@link Contact}
	 */
	public Contact getContactInput(Contact contact, Scanner scanner) {
		System.out.println("Please Input the contact details as asked..");

		// Read Name
		System.out.print("Please Enter Name: ");
		String name = scanner.next(ApplicationConstants.ALPHABET_REGEX);
		contact.setName(name);

		// Reading Surname
		System.out.println("Please Enter SurName: ");
		String surname = scanner.next(ApplicationConstants.ALPHABET_REGEX);
		contact.setSurname(surname);

		// Reading Telephone
		System.out.println("Please Enter Telephone number: ");

		String telephoneNumber = scanner.next(ApplicationConstants.TELEPHONE_REGEX);
		contact.setTelephoneNumber(telephoneNumber);

		// Reading Email Address
		System.out.println("Please Enter Email Address: ");

		String email = scanner.next(ApplicationConstants.EMAIL_REGEX);
		contact.setEmail(email);

		// Reading Age as Optional
		System.out.println("Please Enter Age: ");
		scanner.skip("\\R");
		String age = scanner.nextLine();
		if (age != null && !age.isEmpty() && Integer.parseInt(age) != 0) {
			contact.setAge(Integer.parseInt(age));
		}
		// Reading Hair Colour as Optional
		System.out.println("Please Enter Hair Colour: ");
		String hairColour = scanner.nextLine();
		if (!hairColour.isEmpty()) {
			contact.setHairColour(hairColour);
		}
		contact.setHairColour(hairColour);

		System.out.println("Please Select Contact Category from the following available options:  ");
		System.out.println("(1): FAMILY \n(2): FRIENDS \n(3): ACQUAINTANCE \n(4): Exit");

		int contactCategoryOption = scanner.nextInt();

		CategoryName categoryName = null;
		switch (contactCategoryOption) {
		case 1:
			categoryName = CategoryName.FAMILY;
			System.out.println("Please Enter description of family relationship from the following options: ");

			FamilyRelationships[] values = FamilyRelationships.values();

			for (int i = 1; i <= values.length; i++) {
				System.out.println(i + " -> " + values[i - 1] + "\n");
			}
			int familyRelationship = scanner.nextInt();

			if (familyRelationship > values.length) {
				System.err.println("The Selected option is invalid");
				break;
			}

			// Preparing the Relationship related information for FAMILY.
			FamilyRelationships selectedRelationship = values[familyRelationship - 1];

			ContactCategory category = new ContactCategory();
			category.setCategoryName(categoryName);

			HashMap<String, String> properties = new HashMap<String, String>();
			properties.put("relationship", selectedRelationship.toString());
			category.setCategoryProperties(properties);
			contact.setContactCategory(category);
			break;
		case 2:
			categoryName = CategoryName.FRIENDS;
			System.out.println("Enter the friendship in number of years");
			int friendshipInYears = scanner.nextInt();

			category = new ContactCategory();
			category.setCategoryName(categoryName);

			properties = new HashMap<String, String>();
			properties.put("friendshipYears", String.valueOf(friendshipInYears));
			category.setCategoryProperties(properties);
			contact.setContactCategory(category);
			break;
		case 3:
			categoryName = CategoryName.ACQUAINTANCE;
			break;
		default:
			break;
		}
		return contact;
	}

	/**
	 * Initializes the application related objects for making layered approach
	 * works.
	 * 
	 * @return
	 */
	private static ContactsController initApplication() {
		ContactsController contactsController = new ContactsController();
		JSONFormatFileUtils fileUtils = new JSONFormatFileUtils();
		FileRepository repository = new FileRepository();
		repository.setFileUtils(fileUtils);

		ContactsServiceImpl contactsServiceImpl = new ContactsServiceImpl();
		contactsServiceImpl.setRepository(repository);
		contactsController.setContactsService(contactsServiceImpl);
		return contactsController;
	}
}