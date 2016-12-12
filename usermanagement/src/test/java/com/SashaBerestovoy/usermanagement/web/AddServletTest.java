package com.SashaBerestovoy.usermanagement.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.SashaBerestovoy.usermanagement.User;

public class AddServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	public void testAdd() {
		Date date  = new Date();	
		User newUser = new User("Sasha","Berestovoy", date);
		User user = new User(new Long(1000), "Sasha","Berestovoy", date);
		getMockUserDao().expectAndReturn("create", newUser,user);
		
		addRequestParameter("firstName","Sasha");
		addRequestParameter("lastName","Berestovoy");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
	}
	
	public void testAddEmptyFirstName() {
		Date date  = new Date();
		addRequestParameter("lastName","Berestovoy");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	public void testAddEmptyLastName() {
		Date date  = new Date();
		addRequestParameter("firstName","Sasha");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	public void testAddEmptyDate() {
		Date date  = new Date();
		addRequestParameter("firstName","Sasha");
		addRequestParameter("lastName","Berestovoy");
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	public void testAddEmptyDateIncorrect() {
		Date date  = new Date();
		addRequestParameter("firstName","Sasha");
		addRequestParameter("lastName","Berestovoy");
		addRequestParameter("date", "sdsdasdasad");
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}

}
