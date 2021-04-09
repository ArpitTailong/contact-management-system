package com.cg.cms.dao;

import java.util.List;

import com.cg.cms.entity.Contact;
import com.cg.cms.exception.CMSException;

public interface IContactDAO {

	Contact add(Contact contact) throws CMSException;
	Contact save(Contact contact) throws CMSException;
	
	boolean deleteById(long contactID) throws CMSException;
	Contact getById(long contactId) throws CMSException;
	List<Contact> getAll() throws CMSException;
	
	String INSERT_CONTACT= "insert into contacts (cid,cname,dob,mobile) values(?,?,?,?)";
	String UPDATE_CONTACT="Update contacts set cname=?,dob=?,mobile=? where cid=?";
	String DELETE_CONTACT="Delete From contacts where cid=?";
	String GET_CONTACT_BY_ID="SELECT cid,cname,dob,mobile from contacts where cid=?";
	String GET_ALL_CONTACTS="SELECT cid,cname,dob,mobile from contacts";
	
	
	
	
	
}
