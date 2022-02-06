package com.mycompany.addressbook.entities;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

/**
 * Contains the Category names and additional properties of categories of the
 * contacts.
 * 
 * @author Swamy
 * @version 1.0
 */
public class ContactCategory implements Serializable {

	private static final long serialVersionUID = -4473718924924769365L;

	@NotEmpty
	private CategoryName categoryName;

	private Map<String, String> categoryProperties;

	public CategoryName getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(CategoryName categoryName) {
		this.categoryName = categoryName;
	}

	public Map<String, String> getCategoryProperties() {
		return categoryProperties;
	}

	public void setCategoryProperties(Map<String, String> categoryProperties) {
		this.categoryProperties = categoryProperties;
	}

	@Override
	public String toString() {
		return "ContactCategory [categoryName=" + categoryName + ", categoryProperties=" + categoryProperties + "]";
	}
}