package com.SashaBerestovoy.usermanagement.web;

import java.util.Properties;

import javax.xml.datatype.DatatypeFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.SashaBerestovoy.usermanagement.db.DaoFactory;
import com.SashaBerestovoy.usermanagement.db.MockDaoFactory;
import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;
	
	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao( ((MockDaoFactory)DaoFactory.getInstance()).getMockUserDao() );
	}
	
	protected void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}
	
	private void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;	
	}

	
	public Mock getMockUserDao() {
		return mockUserDao;
	}


	
	public void test() {
		
	}

}