package com.cg.cms.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.cms.dao.ContactDAOImpl;
import com.cg.cms.dao.IContactDAO;
import com.cg.cms.entity.Contact;
import com.cg.cms.exception.CMSException;

public class ContactServiceImpl implements IContactService {

	
	private IContactDAO contactDAO;
	
	public ContactServiceImpl() {
	contactDAO= new ContactDAOImpl();
	}
	
	
	public boolean isValidContactId(Long contactId) {
		return contactId !=null && contactId > 0;
	}
	
	public boolean isValidFullName(String fullName) {
		return fullName!=null &&(fullName.length()>=3 || fullName.length() <=20);
	}
	
	
	public boolean isValidDateOfBirth(LocalDate dob) {
		return dob !=null && dob.isBefore(LocalDate.now());
	}
	
	public boolean isValidMobile(String mobile) {
			return mobile != null && mobile.matches("[1-9][0-9]{9}");
	}
	
	public boolean isValidContact(Contact contact) throws CMSException{
		
		List<String> errorMesseges= new ArrayList<>();
		boolean isValid= true;
		
		if(contact!=null) {
			if(!isValidContactId(contact.getContactId())) {
				isValid=false;
				errorMesseges.add("contatc id cannot be blank and must be a positive number");
			}
			if(!isValidFullName(contact.getFullName())) {
				isValid=false;
				errorMesseges.add("full name cannot be blank and must be of 3 to 20 characters");
			}
			if(!isValidDateOfBirth(contact.getDateOfBirth())){
				isValid=false;
				errorMesseges.add("Date of Birth cannot be blank and must not be a future date");
			}if(!isValidMobile(contact.getMobile())){
				isValid=false;
				errorMesseges.add("Mobile number cannot be blank and must be of 10 digits");
			}
			if(!errorMesseges.isEmpty()) {
				throw new CMSException("Invalid contact : "+ errorMesseges);
			}
		
		}	else {
				isValid=false;
				throw new CMSException("Contact details are not supplied");
		}
		return isValid;
	}
	
	
	
	
	@Override
	public Contact add(Contact contact) throws CMSException {
		if (isValidContact(contact)) { 
			contactDAO.add(contact);
		}
		return contact;
	}

	@Override
	public Contact save(Contact contact) throws CMSException {
		if(isValidContact(contact)) {
			contactDAO.save(contact);
		}
			return contact;
	}

	@Override
	public boolean deleteById(long contactID) throws CMSException {
		return contactDAO.deleteById(contactID);
	}

	@Override
	public Contact getById(long contactId) throws CMSException {
		return contactDAO.getById(contactId);
	}

	@Override
	public List<Contact> getAll() throws CMSException {
		return contactDAO.getAll();
	}

}
