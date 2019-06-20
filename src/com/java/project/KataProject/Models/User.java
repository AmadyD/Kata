package com.java.project.KataProject.Models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {

@Id @GeneratedValue( strategy=GenerationType.IDENTITY )
@Column( name="id_user" )
private int id;
private int login;
private int password;
private String firstName;
private String lastName;
private String sexe;

public User() {
	this(0,0,"","","");
}


public User(int login, int password, String firstName,String lastName,String sexe) {
	this.setLogin(login);
	this.setPassword(password);
	this.setFirstName(firstName);
	this.setLastName(lastName);
	this.setSexe(sexe);
}

public int getLogin() {
	return login;
}

public void setLogin(int login) {
	this.login = login;
}

public int getPassword() {
	return password;
}

public void setPassword(int password) {
	this.password = password;
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

public int getId() {
	return id;
}
public String getSexe() {
	return sexe;
}

public void setSexe(String sexe) {
	this.sexe = sexe;
}

public Transaction makeATransaction(int idAccount,String type,String transactionName,double transactionAmount,String date) {
	Transaction trans = new Transaction(idAccount,this.id,type,transactionName,transactionAmount,date);
	return trans;
}

@Override
public String toString() {
	return "User [id=" + id + ", login=" + login + ", password=" + password + ", firstName=" + firstName + ", lastName="
			+ lastName + "]";
}


}