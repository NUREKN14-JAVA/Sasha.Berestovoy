package com.SashaBerestovoy.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import com.SashaBerestovoy.usermanagement.User;

class HsqldbUserDao implements UserDao {
	private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
	private static final String UPDATE_QUERY = "UPDATE users SET  firstname=?, lastname=?,  dateofbirth=? WHERE id=?";;
	private static final String FIND_BY_ID = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id=?";
	private static final String SELECT_ALL_QUERY = "SELECT id,firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users(firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
private ConnectionFactory ConnectionFactory;
	
	public HsqldbUserDao(){		
	};
	
	public HsqldbUserDao(ConnectionFactory connectionFactory){
		this.ConnectionFactory = connectionFactory;
	};
	
	public ConnectionFactory getConnectionFactory() {
		return ConnectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		ConnectionFactory = connectionFactory;
		//this.connectionFactory = connectionFactory;
	}

	@Override
	public User create(User user) throws DatabaseException {
		try{
		Connection connection = ConnectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());		
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			int n = statement.executeUpdate();
			if (n != 1 ) {
				throw new DatabaseException("Number of the inserted rows: " + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				user.setId(new Long (keys.getLong(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		}
		
	@Override
	public void update(User user) throws DatabaseException {
		try {
			Connection connection = ConnectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirthd().getTime()));
			statement.setLong(4,user.getId());
			int n = statement.executeUpdate();
			if (n != 1){
				throw new DatabaseException("Number of updated rows :" + n);
			}
			statement.close();
			connection.close();
		}catch (DatabaseException e) {
			throw e;
		}catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return;
		}

	@Override
	public void delete(User user) throws DatabaseException {
		try{
			Connection connection = ConnectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(DELETE_USER);
			statement.setLong(1,user.getId());
			int n = statement.executeUpdate();
			if (n != 1){
				throw new DatabaseException("Number of deleted rows :" + n);
			}
			statement.close();
			connection.close();
		}
		catch (DatabaseException e) {
			throw e;
		}catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return;
	}

	@Override
	public User find(Long id) throws DatabaseException {
		User user = new User();
		
		try {
			Connection connection = ConnectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(FIND_BY_ID);
			statement.setLong(1, id) ;
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
			user.setId(new Long(resultSet.getLong(1)));
			user.setFirstName(resultSet.getString(2));
			user.setLastName(resultSet.getString(3));
			user.setDateOfBirthd(resultSet.getDate(4));}
			else user=null;
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch (DatabaseException e) {
			throw e;
		} 
		catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
		return user;
	}


	@Override
	public Collection findAll() throws DatabaseException {
		Collection result = new LinkedList();
		
		try {
			Connection connection = ConnectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()){
				User user = new User();
				user.setId( new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirthd(resultSet.getDate(4));
				result.add(user);
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return result;
	}

}