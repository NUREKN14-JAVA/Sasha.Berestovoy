package com.SashaBerestovoy.usermanagement.db;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import com.SashaBerestovoy.usermanagement.User;

import junit.framework.TestCase;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}
	
	@Test
	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("Sasha");
			user.setLastName("Berestovoy");
			user.setDateOfBirthd(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	public void testDelete(){
		try{
			
		User user = new User();
		user.setFirstName("Sasha");
		user.setLastName("Berestovoy");
		user.setDateOfBirthd(new Date());
		user = dao.create(user);
		Long id = user.getId();
		
		dao.delete(user);
	
		assertNull(dao.find(id));
		}
		catch (DatabaseException e){
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFind(){
		
		try{
			User user = new User ();
			User user1 = new User ();
			
			user.setFirstName("Sasha");
			user.setLastName("Berestovoy");
			user.setDateOfBirthd(new Date());
			user = dao.create(user);
			Long id = user.getId();
			user1 = dao.find(id);
			java.sql.Date date = new java.sql.Date(user.getDateOfBirthd().getTime());
			
			assertEquals(user.getFirstName(), user1.getFirstName());	
			assertEquals(user.getLastName(), user1.getLastName());
			assertEquals(user.getId(), user1.getId());
			assertEquals(date.toString(),user1.getDateOfBirthd().toString());
		}catch (DatabaseException e){
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testUpdate(){
		try{
			User user = new User ();
			User user1 = new User ();
			
			user.setFirstName("Ivan");
			user.setLastName("Kikir");
			user.setDateOfBirthd(new Date());
			user = dao.create(user);
			Long id = user.getId();
			
			
			user1.setFirstName("Markis");
			user1.setLastName("Desad");
			user1.setDateOfBirthd(new Date());
			user1.setId(id);
			
			dao.update(user1);
			user=dao.find(id);
			java.sql.Date date = new java.sql.Date(user.getDateOfBirthd().getTime());
			
			assertEquals(user.getFirstName(), "Markis");
			assertEquals(user.getLastName(), "Desad");
			assertEquals(user.getDateOfBirthd().toString(),date.toString());
		}catch (DatabaseException e){
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null",collection);
			assertEquals("Collection size.",2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}
	
	
	
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement",
				"sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
