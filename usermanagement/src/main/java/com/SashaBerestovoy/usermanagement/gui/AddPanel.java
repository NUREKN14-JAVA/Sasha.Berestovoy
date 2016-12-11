package com.SashaBerestovoy.usermanagement.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import com.SashaBerestovoy.usermanagement.User;
import com.SashaBerestovoy.usermanagement.db.DatabaseException;
import com.SashaBerestovoy.usermanagement.util.Messages;

public class AddPanel extends JPanel implements ActionListener {
	
	private MainFrame parent;
	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField dateOfBirthField;
	private JTextField lastNameField;
	private JTextField firstNameField;
	private Color bgColor;
	
	public AddPanel (MainFrame parent){
		this.parent = parent;
		initialize();
	}

	private void initialize() {
		this.setName("addPanel");  //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(getFieldPanel(), BorderLayout.NORTH);
		this.add(getButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel getButtonPanel() {
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(getOkButton(), null);
			buttonPanel.add(getCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton getCancelButton() {
		if(cancelButton == null){
			cancelButton = new JButton();
			cancelButton.setName("cancelButton");  //$NON-NLS-1$
			cancelButton.setText(Messages.getString("AddPanel.close"));  //$NON-NLS-1$
			cancelButton.setActionCommand("cancel");  //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	private JButton getOkButton() {
		if(okButton == null){
			okButton = new JButton();
			okButton.setName("okButton");  //$NON-NLS-1$
			okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
			okButton.setActionCommand("ok");  //$NON-NLS-1$
			okButton.addActionListener(this);
		}
		return okButton;
	}

	private JPanel getFieldPanel() {
		if(fieldPanel == null){
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3,2));
			addLabeledField(fieldPanel,Messages.getString("AddPanel.first_name"),getFirstNameField());  //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("AddPanel.last_name"),getLastNameField());  //$NON-NLS-1$
			addLabeledField(fieldPanel,Messages.getString("AddPanel.date_of_birthday"),getDateOfBirthField());  //$NON-NLS-1$
		}
		return fieldPanel;
	}

	private JTextField getDateOfBirthField() {
		if(dateOfBirthField == null){
			dateOfBirthField = new JTextField();
			dateOfBirthField.setName("dateOfBirthField");  //$NON-NLS-1$
		}
		return dateOfBirthField;
	}

	private JTextField getLastNameField() {
		if(lastNameField == null){
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField");  //$NON-NLS-1$
		}
		return lastNameField;
	}

	private void addLabeledField(JPanel panel, String labelText,
			JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
	}

	private JTextField getFirstNameField() {
		if(firstNameField == null){
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField");  //$NON-NLS-1$
		}
		return firstNameField;
	}

	
	public void actionPerformed(ActionEvent e) {
		
		if(Messages.getString("AddPanel.5").equalsIgnoreCase(e.getActionCommand())){  //$NON-NLS-1$
			User user = new User();
			user.setFirstName(getFirstNameField().getText());
			user.setLastName(getLastNameField().getText());
			DateFormat format = DateFormat.getDateInstance();
			
			try {
				
				user.setDateOfBirthd(format.parse(getDateOfBirthField().getText()));
			} catch (ParseException e1) {
				getDateOfBirthField().setBackground(Color.RED);
				return;
			}
			try {
				parent.getDao().create(user);
			} catch (DatabaseException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), Messages.getString(Messages.getString("AddPanel.6")), JOptionPane.ERROR_MESSAGE);  //$NON-NLS-1$
			}
		}
		clearFields();
		this.setVisible(false);
		parent.showBrowsePanel();
	}

	private void clearFields() {
		getFirstNameField().setText(Messages.getString("AddPanel.7"));  //$NON-NLS-1$
		//getFirstNameField().setBackground(bgColor);
		
		getLastNameField().setText(Messages.getString("AddPanel.8"));  //$NON-NLS-1$
		//getLastNameField().setBackground(bgColor);
		
		getDateOfBirthField().setText(Messages.getString("AddPanel.9"));  //$NON-NLS-1$
		//getDateOfBirthField().setBackground(bgColor);
		
	}

}
