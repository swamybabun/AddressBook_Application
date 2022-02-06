#### How To Run

- The Address Book application is written in layered approach and made it loosely coupled to the layers and objects. </br>
- This Address book application supports single file or multiple files approach as per configuration we do. </br>
- The Address book TEXT files are written as JSON format for making it easy to manipulate and maintain. </br>
- This Application is written with intension of making it flexible and extensible.

Especially, This Application is written in such a way that it can be worked in two ways.

- As a Stand Alone Application By running the main() method in com.mycompany.addressbook.AddressBookApplication class. </br>
- As a REST API using spring boot application. This can be observed by running com.mycompany.addressbook.SpringBootAddressBookApplication class.

#### Dependencies:
- It runs without any additional library except jackson JSON library when running as stand alone core java application. </br>
- It runs with spring boot web and jackson JSON library when running it as spring boot application. 

#### TODO:Next steps
- Logging with any one of logging framework.
- JUnit and Integration test cases can be authored.

#### The Output sample 
The sample of the output can be seen below

```
 ================ Welcome to the Address Book Application ===================
The current configuration of Address Book is on Single File :true
Please select anyone of the following options  
(1): Option 1 - Add a Contact 
(2): Option 2 - Read a Contact 
(3): Option 3 - Update a Contact 
(4): Option 4 - Get All Contacts 
(5): Option 5 - Delete a Contact 
(6): Exit 

1
You Have Selected for Adding a new contact.. 

Please Input the contact details as asked..
Please Enter Name: John
Please Enter SurName: 
David
Please Enter Telephone number: 
+352987456321
Please Enter Email Address: 
admin@gmail.com
Please Enter Age: 
25
Please Enter Hair Colour: 
black
Please Select Contact Category from the following available options:  
(1): FAMILY 
(2): FRIENDS 
(3): ACQUAINTANCE 
(4): Exit
1
Please Enter description of family relationship from the following options: 
1 -> PARENT

2 -> GRANPARENT

3 -> SON

4 -> DAUGHTER

5 -> AUNT

6 -> UNCLE

3
Your contact is saved successfully and its generated id is 69842

 ================ Welcome to the Address Book Application ===================
The current configuration of Address Book is on Single File :true
Please select anyone of the following options  
(1): Option 1 - Add a Contact 
(2): Option 2 - Read a Contact 
(3): Option 3 - Update a Contact 
(4): Option 4 - Get All Contacts 
(5): Option 5 - Delete a Contact 
(6): Exit 

4
You Have Selected for Reading All the contacts.. 

The Existing contacts in Address Book are below (Sorted by Surname).... 

69842 ----> Contact [id=69842, name=John, surname=David, telephoneNumber=+352987456321, email=admin@gmail.com, age=25, hairColour=black, contactCategory=ContactCategory [categoryName=FAMILY, categoryProperties={relationship=SON}]]


================ Welcome to the Address Book Application ===================

The current configuration of Address Book is on Single File :true
Please select anyone of the following options  
(1): Option 1 - Add a Contact 
(2): Option 2 - Read a Contact 
(3): Option 3 - Update a Contact 
(4): Option 4 - Get All Contacts 
(5): Option 5 - Delete a Contact 
(6): Exit 

2
You Have Selected for Reading the existing contact.. Please Enter the contactId:  

69842
The contact details are...
Contact [id=69842, name=John, surname=David, telephoneNumber=+352987456321, email=admin@gmail.com, age=25, hairColour=black, contactCategory=ContactCategory [categoryName=FAMILY, categoryProperties={relationship=SON}]]

 ================ Welcome to the Address Book Application ===================
The current configuration of Address Book is on Single File :true
Please select anyone of the following options  
(1): Option 1 - Add a Contact 
(2): Option 2 - Read a Contact 
(3): Option 3 - Update a Contact 
(4): Option 4 - Get All Contacts 
(5): Option 5 - Delete a Contact 
(6): Exit 

5
You have selected for deleting the contact
enter the contact id to delete
69842
The Selected contact has been deleted

 ================ Welcome to the Address Book Application ===================
The current configuration of Address Book is on Single File :true
Please select anyone of the following options  
(1): Option 1 - Add a Contact 
(2): Option 2 - Read a Contact 
(3): Option 3 - Update a Contact 
(4): Option 4 - Get All Contacts 
(5): Option 5 - Delete a Contact 
(6): Exit 

4
You Have Selected for Reading All the contacts.. 

The address book is empty..

 ================ Welcome to the Address Book Application ===================
The current configuration of Address Book is on Single File :true
Please select anyone of the following options  
(1): Option 1 - Add a Contact 
(2): Option 2 - Read a Contact 
(3): Option 3 - Update a Contact 
(4): Option 4 - Get All Contacts 
(5): Option 5 - Delete a Contact 
(6): Exit 
```