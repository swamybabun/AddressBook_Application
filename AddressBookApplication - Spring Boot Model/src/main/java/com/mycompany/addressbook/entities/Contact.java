package com.mycompany.addressbook.entities;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * The contact POJO for managing all its properties.
 * 
 * @author Swamy
 * @version 1.0
 */
public class Contact implements Serializable {
	private static final long serialVersionUID = 9003758028582922626L;

	@NotEmpty
	@NotBlank(message = "Please enter your id")
	private Long id;

	@NotEmpty
	@NotBlank(message = "Please enter your name")
	private String name;

	@NotEmpty
	@NotBlank(message = "Please enter your surname")
	private String surname;

	@Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "{invalid.phonenumber}")
	private String telephoneNumber;

	@Email
	@NotBlank(message = "Please enter email address")
	private String email;

	private int age;

	private String hairColour;

	private ContactCategory contactCategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHairColour() {
		return hairColour;
	}

	public void setHairColour(String hairColour) {
		this.hairColour = hairColour;
	}

	public ContactCategory getContactCategory() {
		return contactCategory;
	}

	public void setContactCategory(ContactCategory contactCategory) {
		this.contactCategory = contactCategory;
	}

	public Contact(Long id, String name, String surname, String telephoneNumber, String email, int age,
			String hairColour, ContactCategory contactCategory) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.telephoneNumber = telephoneNumber;
		this.email = email;
		this.age = age;
		this.hairColour = hairColour;
		this.contactCategory = contactCategory;
	}

	public Contact() {
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", surname=" + surname + ", telephoneNumber=" + telephoneNumber
				+ ", email=" + email + ", age=" + age + ", hairColour=" + hairColour + ", contactCategory="
				+ contactCategory + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((contactCategory == null) ? 0 : contactCategory.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((hairColour == null) ? 0 : hairColour.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((telephoneNumber == null) ? 0 : telephoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (age != other.age)
			return false;
		if (contactCategory == null) {
			if (other.contactCategory != null)
				return false;
		} else if (!contactCategory.equals(other.contactCategory))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (hairColour == null) {
			if (other.hairColour != null)
				return false;
		} else if (!hairColour.equals(other.hairColour))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (telephoneNumber == null) {
			if (other.telephoneNumber != null)
				return false;
		} else if (!telephoneNumber.equals(other.telephoneNumber))
			return false;
		return true;
	}
}