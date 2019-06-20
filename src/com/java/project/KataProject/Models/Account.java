package com.java.project.KataProject.Models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Account {

	@Id @GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column( name="id_account" )
	private int id;
	@Column( name="id_user")
	private int idUser;
	@Column( name="no_account")
	private String noAccount;
	@Column( name="type_of_account")
	private String typeOfAccount;
	private double amount;
	
	public Account() {
		this(0,"","",0);
	}
	
	public Account(int idUser, String noAccount,String typeOfAccount,double amount) {
		
		this.setIdUser(idUser);
		this.setNoAccount(noAccount);
		this.setTypeOfAccount(typeOfAccount);
		this.setAmount(amount);
	}
	

	public String getNoAccount() {
		return noAccount;
	}
	public void setNoAccount(String noAccount) {
		this.noAccount = noAccount;
	}
	public String getTypeOfAccount() {
		return typeOfAccount;
	}
	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", idUser=" + idUser + ", noAccount=" + noAccount + ", typeOfAccount="
				+ typeOfAccount + ", amount=" + amount + "]";
	}

	
}
