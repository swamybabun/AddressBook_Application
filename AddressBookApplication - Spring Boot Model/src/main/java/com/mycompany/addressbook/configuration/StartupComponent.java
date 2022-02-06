package com.mycompany.addressbook.configuration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupComponent implements CommandLineRunner {

	@Autowired
	private ApplicationProperties applicationProperties;

	public StartupComponent(ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Override
	public void run(String... args) throws Exception {
		String filesDirectory = applicationProperties.getFilesDirectory();

		// Create directory if not exists
		if (!Files.exists(Paths.get(filesDirectory))) {
			System.out.println("The Folder Not exists so creating");
			Files.createDirectory(Paths.get(filesDirectory));
		}

		String singleFileName = applicationProperties.getFilesDirectory() + File.separator
				+ applicationProperties.getSingleFileName() + applicationProperties.getFileExtension();
		// Create contacts file if not exists
		if (!Files.exists(Paths.get(singleFileName))) {
			System.out.println("The file Not exists so creating");
			Files.createFile(Paths.get(singleFileName));
		}

	}
}