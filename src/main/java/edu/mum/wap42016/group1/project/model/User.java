package edu.mum.wap42016.group1.project.model;

public class User {
String firstName;
String lastName;
String state;
String city;
String street;
String email;
String password;
int birthYear;
int zipCode;
public User(String firstName, String lastName, String state, String city, String street, String email, String password,
		int birthYear, int zipCode) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.state = state;
	this.city = city;
	this.street = street;
	this.email = email;
	this.password = password;
	this.birthYear = birthYear;
	this.zipCode = zipCode;
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
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getBirthYear() {
	return birthYear;
}
public void setBirthYear(int birthYear) {
	this.birthYear = birthYear;
}
public int getZipCode() {
	return zipCode;
}
public void setZipCode(int zipCode) {
	this.zipCode = zipCode;
}


}
