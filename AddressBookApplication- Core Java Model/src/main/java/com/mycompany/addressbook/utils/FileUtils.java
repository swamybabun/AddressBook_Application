package com.mycompany.addressbook.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mycompany.addressbook.configuration.ApplicationConstants;
import com.mycompany.addressbook.exceptions.AddressBookException;

/**
 * Utility for doing CRUD operations on the file with synchronization.
 * 
 * @author Swamy
 * @version 1.0
 */
public class FileUtils {
	/**
	 * Saves the given content to the given file name
	 * 
	 * @param fileName the file Name to save data
	 * @param data     the data to be saved in to file.
	 * @throws AddressBookException
	 */
	public void saveDataToFile(final String fileName, String data) throws AddressBookException {
		try {
			synchronized (FileUtils.class) {
				Files.write(Paths.get(fileName), Arrays.asList(data), StandardCharsets.UTF_8, StandardOpenOption.CREATE,
						StandardOpenOption.APPEND);
			}
		} catch (Exception e) {
			throw new AddressBookException("IO Exception while writing a single contact to the file", e);
		}
	}

	/**
	 * Updates the given file name with the given latest content.
	 * 
	 * @param fileName the file Name to update
	 * @param data     the data to be updated in to file.
	 * @throws AddressBookException
	 */
	public void updateFile(final String fileName, String data) throws AddressBookException {
		try {
			synchronized (FileUtils.class) {
				Files.write(Paths.get(fileName), data.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
			}
		} catch (Exception e) {
			throw new AddressBookException("IO Exception while writing a single contact to the file", e);
		}
	}

	/**
	 * Deletes the given file.
	 * 
	 * @param fileName the file Name to update
	 * @throws AddressBookException
	 */
	public void deleteFile(final String fileName) throws AddressBookException {
		try {
			synchronized (FileUtils.class) {
				Files.deleteIfExists(Paths.get(fileName));
			}
		} catch (Exception e) {
			throw new AddressBookException("Exception while deleting the contact file with name... " + fileName, e);
		}
	}

	/**
	 * Get the file content as {@link List} of {@link String}'s.
	 * 
	 * @param fileName the name of the file.
	 * @return the contents of the file in {@link List} of {@link String}'s.
	 * @throws AddressBookException
	 */
	public List<String> getFileContentAsStrings(final String fileName) throws AddressBookException {
		List<String> fileContent = null;

		// Select based on single or multi file approach
		if (ApplicationConstants.IS_SINGLE_FILE_DATA_STORE) {
			try {
				synchronized (FileUtils.class) {
					fileContent = new ArrayList<>(Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8));
				}
			} catch (IOException e) {
				throw new AddressBookException("IOException in reading file contents", e);
			}
		} else {
			fileContent = readMultipleFiles();
		}
		return fileContent;
	}

	/**
	 * Reads the content from multiple files.
	 * 
	 * @return the contents of files in {@link List}
	 * @throws AddressBookException
	 */
	private List<String> readMultipleFiles() throws AddressBookException {
		System.out.println("Reading files in multi file approach : " + ApplicationConstants.FILES_DIRECTORY);
		List<String> fileContent = new ArrayList<String>();
		try {
			File directoryPath = new File(ApplicationConstants.FILES_DIRECTORY);
			File[] listFiles = directoryPath.listFiles();

			for (File file : listFiles) {
				System.out.println("File Name : " + file.getAbsolutePath());
				synchronized (file) {
					List<String> currentFileContents = new ArrayList<>(
							Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8));
					fileContent.addAll(currentFileContents);
				}
			}
		} catch (IOException e) {
			throw new AddressBookException("IOException in reading file contents in multifile approach...", e);
		}
		return fileContent;
	}

	/**
	 * Retrieves/prepares the file name according to multiple or single file
	 * approach.
	 * 
	 * @param contactId the contact identifier to append to file name.
	 * @return the file Name
	 */
	protected final String getFileName(Long contactId) {
		if (ApplicationConstants.IS_SINGLE_FILE_DATA_STORE) {
			return ApplicationConstants.SINGLE_FILE_NAME + ApplicationConstants.FILE_EXTENSION;
		} else {
			return ApplicationConstants.MULTI_FILE_DIRECTORY_WITH_PREFIX + "-" + contactId
					+ ApplicationConstants.FILE_EXTENSION;
		}
	}
}