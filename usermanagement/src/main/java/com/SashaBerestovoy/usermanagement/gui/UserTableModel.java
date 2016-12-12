package com.SashaBerestovoy.usermanagement.gui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.SashaBerestovoy.usermanagement.User;
import com.SashaBerestovoy.usermanagement.util.Messages;

public class UserTableModel extends AbstractTableModel {

	private static final String[] COLUMN_NAMES = {"Id", com.SashaBerestovoy.usermanagement.gui.Messages.getString("UserTableModel.first_name"),com.SashaBerestovoy.usermanagement.gui.Messages.getString("UserTableModel.last_name"), com.SashaBerestovoy.usermanagement.gui.Messages.getString("UserTableModel.date_of_birth")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	private static final Class[] COLUMN_CLASSES = {Long.class,String.class,String.class,Date.class};
	private List users = null;
	
	public UserTableModel(Collection users){
		this.users = new ArrayList(users);
	}
	
	public int getColumnCount() {
		
		return COLUMN_NAMES.length;
	}


	public int getRowCount() {		
		return users.size();
	}

	public Class getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	public String getColumnName(int column) {
		return COLUMN_NAMES[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		User user = (User) users.get(rowIndex);
		switch (columnIndex){
		case 0:
			return user.getId();
		case 1:
			return user.getFirstName();
		case 2:
			return user.getLastName();
		case 3:
			return user.getDateOfBirthd();
		
		}
		
		return null;
	}

}
