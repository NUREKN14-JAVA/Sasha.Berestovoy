package com.SashaBerestovoy.usermanagement;

import java.util.Calendar;
import java.util.Date;

public class User {
private Long id;
private String  firstName;
private String lastName;
private Date dateOfBirthd;

public User(String firstName, String lastName, Date date) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateOfBirthd= date;
	
}
public User(Long id, String firstName, String lastName, Date date) {
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.dateOfBirthd= date;
}
public User() {

}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public Date getDateOfBirthd() {
	return dateOfBirthd;
}
public void setDateOfBirthd(Date dateOfBirthd) {
	this.dateOfBirthd = dateOfBirthd;
}
public String getFullName() {
	
	return getLastName() + ","+ getFirstName();
}
public long getAge() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(new Date());
	int currentYear = calendar.get(Calendar.YEAR);
	calendar.setTime(getDateOfBirthd());
	int year = calendar.get(Calendar.YEAR);
	
	long result = currentYear - year;
	
	return result;
}

public boolean equals(Object obj) {
	if (obj == null)
	{
		return false;
	}
	if (this == obj)
	{
		return true;
	}
	if (this.getId() == null && ((User) obj).getId() == null)
	{
		return true;
	}
	return this.getId().equals(((User) obj).getId());
}

public int hashCode() {
	if (this.getId() == null)
			{
				return 0;
			}
	return this.getId().hashCode();
}


}
