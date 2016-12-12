package com.SashaBerestovoy.usermanagement.web;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.SashaBerestovoy.usermanagement.User;

public class EditServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}
	
	public void testEdit() {
		Date date  = new Date();
		User user = new User(new Long(1000), "Sasha","Berestovoy", date);
		getMockUserDao().expect("update", user);
		
		addRequestParameter("id","1000");
		addRequestParameter("firstName","Sasha");
		addRequestParameter("lastName","Berestovoy");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
	}
	
	public void testEditEmptyFirstName() {
		Date date  = new Date();
		addRequestParameter("id","1000");
		addRequestParameter("lastName","Berestovoy");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	public void testEditEmptyLastName() {
		Date date  = new Date();
		addRequestParameter("id","1000");
		addRequestParameter("firstName","Sasha");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	public void testEditEmptyDate() {
		Date date  = new Date();
		addRequestParameter("id","1000");
		addRequestParameter("firstName","Sasha");
		addRequestParameter("lastName","Berestovoy");
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
	public void testEditEmptyDateIncorrect() {
		Date date  = new Date();
		addRequestParameter("id","1000");
		addRequestParameter("firstName","Sasha");
		addRequestParameter("lastName","Berestovoy");
		addRequestParameter("date", "sdsdasdasad");
		addRequestParameter("okButton","Ok");
		doPost();
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		assertNotNull("Could not find error message in session scope", errorMessage);
	}
	
}
