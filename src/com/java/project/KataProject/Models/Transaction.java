package com.java.project.KataProject.Models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {
	
	@Id @GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column( name="id_transaction" )
	private int idTransaction;
	@Column( name="id_account" )
	private int idAccount;
	@Column( name="id_user" )
	private int idUser;
	private String type;
	@Column( name="transaction_name" )
	private String transactionName;
	@Column( name="transaction_amount" )
	private double transactionAmount;
	private String date ;
	
	public Transaction() {
		this(0,0,"","",0,"");
	}
	


	public Transaction(int idAccount, int idUser, String type, String transactionName,
			double transactionAmount, String date) {
		this.setIdAccount(idAccount);
		this.setIdUser(idUser);
		this.setType(type);
		this.setTransactionName(transactionName);
		this.setTransactionAmount(transactionAmount);
		this.setDate(date);
	}
	


	public int getIdTransaction() {
		return idTransaction;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Transaction [idTransaction=" + idTransaction + ", idAccount=" + idAccount + ", idUser=" + idUser
				+ ", type=" + type + ", transactionName=" + transactionName + ", transactionAmount=" + transactionAmount
				+ ", date=" + date + "]";
	}


		

}