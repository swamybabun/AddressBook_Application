package com.mycompany.addressbook.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "application")
@Component
public class ApplicationProperties {

	Boolean writeToSingleFile;
	String singleFileName;
	String multiFileDirectoryWithPrefix;
	String fileExtension;
	String filesDirectory;

	public Boolean writeToSingleFile() {
		return writeToSingleFile;
	}

	public String getSingleFileName() {
		return singleFileName;
	}

	public String getMultiFileDirectoryWithPrefix() {
		return multiFileDirectoryWithPrefix;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public Boolean getWriteToSingleFile() {
		return writeToSingleFile;
	}

	public String getFilesDirectory() {
		return filesDirectory;
	}

	public void setWriteToSingleFile(Boolean writeToSingleFile) {
		this.writeToSingleFile = writeToSingleFile;
	}

	public void setSingleFileName(String singleFileName) {
		this.singleFileName = singleFileName;
	}

	public void setMultiFileDirectoryWithPrefix(String multiFileDirectoryWithPrefix) {
		this.multiFileDirectoryWithPrefix = multiFileDirectoryWithPrefix;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public void setFilesDirectory(String filesDirectory) {
		this.filesDirectory = filesDirectory;
	}

}
