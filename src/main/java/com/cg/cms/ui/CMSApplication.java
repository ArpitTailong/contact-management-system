package com.cg.cms.ui;

import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

import com.cg.cms.entity.Contact;
import com.cg.cms.exception.CMSException;
import com.cg.cms.service.ContactServiceImpl;
import com.cg.cms.service.IContactService;

public class CMSApplication {

	public static final IContactService contactService = new ContactServiceImpl();
	public static final Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) { 
		Menu[] menus = Menu.values (); 
		Menu selectedMenu = null;
	while (selectedMenu != Menu.QUIT) { 
		System.out.println("Choice\tOperation");
	for(Menu menu : menus) { 
		System.out.println(menu.ordinal() + "\t" + menu);
	}
	System.out.print("Enter choice: "); 
	int ch = scan.nextInt();
	if (ch >= 0 && ch <= menus.length) { 
		selectedMenu = menus[ch];
		switch(selectedMenu) { 
			case ADD:
				doAdd();
				break; 
			case FIND:
				doFind();
				break;
			case LIST :
				doList();
				break;
			case DELETE:
				doDelete();
				break;
			case QUIT:
				break;
			default:
				break;
		}
		}else {
			selectedMenu = null;
			System.out.println("Invalid Choice");
		}
	}
	scan.close();
	System.out.println("Application Terminated");
	}
	private static void doAdd() {
		Contact contact= new Contact();
		System.out.println("Enter Contact id : ");
		contact.setContactId(scan.nextLong());
		System.out.println("Enter full Name : ");
		contact.setFullName(scan.next());
		System.out.println("Enter dat of Birth (yyyy-MM-dd) : ");
		contact.setDateOfBirth(LocalDate.parse(scan.next()));
		System.out.println("Enter mobile number");
		contact.setMobile(scan.next());
		
		try {
			contactService.add(contact);
			System.out.println("contact added");
		}catch(CMSException excep) {
			System.out.println(excep.getMessage());
		}
				
	}
	
	private static void doFind() {
		try {	
			System.out.println("Enter Contact id you want to find: ");
			long contactId=scan.nextLong();
			
			Contact contact=contactService.getById(contactId);
			
			if(contact == null) {
				System.out.println("No contact with #"+contactId);
			}else {
				System.out.println(contact);
			}
		}catch(CMSException excep) {
			System.out.println(excep.getMessage());
		}
		
	}
	
	public static void doList() {
		try {
			List<Contact> contacts= contactService.getAll();
			if(contacts.isEmpty()) {
				System.out.println("No contacts found");
			}else {
				for(Contact contact : contacts) {
					System.out.println(contact);
				}
			}
		}catch(CMSException excep) {
			System.out.println(excep.getMessage());
		}
	}
	
	private static void doDelete() {
		try {
			System.out.println("Enter contact id: ");
			long contactId=scan.nextLong();
			boolean isDeleted=contactService.deleteById(contactId);
			if(!isDeleted) {
				System.out.println("No such contact found");
			}else {
				System.out.println("contact with #"+ contactId +"is Deleted");
			}
		}catch(CMSException excep) {
			System.out.println(excep.getMessage());
		}
	}
	
}
