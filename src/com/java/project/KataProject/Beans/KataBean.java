package com.java.project.KataProject.Beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.java.project.KataProject.dbOperations.DatabaseOperations;
import com.java.project.KataProject.Models.*;


@Named 
@SessionScoped
public class KataBean implements Serializable {


    private static final long serialVersionUID = -5433850275008415405L;

    private int login ;
    private int password;
    private User user;
   private Account account;
   private String type;
   private String transactionName;
   private double transactionAmount;
    private long bool;
    private boolean exec = false;
    private int testInput;

	public int getLogin() {
        System.out.println( "in getLogin" );
        return login;
    }

    public void setLogin(int login) {
        System.out.println( "in setLogin with " + login );
        this.login = login;
    }
    
    public int getPassword() {
        System.out.println( "in getPassword" );
        return password;
    }
    
    public void setPassword(int password) {
        System.out.println( "in setPassword with " + password );
        this.password = password;
    }
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
    
    public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
    public long getBool() {
		return bool;
	}

	public void setBool(long bool) {
		this.bool = bool;
	}
	public boolean isExec() {
		return exec;
	}

	public void setExec(boolean exec) {
		this.exec = exec;
	}
	

	public int getTestInput() {
	return testInput;
	}

	public void setTestInput(int testInput) {
	this.testInput = testInput;
	}

	public String returnAction() {
		exec = true;
    	 bool = DatabaseOperations.userLogin(login, password);
    	if(bool == 1) {
    		user = DatabaseOperations.getUser(login, password);
    		account = DatabaseOperations.getAccount(user);
    		return "success";
    	}else {
    		return "failure";
    	}
    }
	

	public String moveToTransactionPage() {
		return "page1";
	}
	
	public String backToHome() {
		return "home";
	}
	public String deconnect() {
		return "exit";
	}
    
    public List<Transaction> listTransaction(){
    	return DatabaseOperations.getUserTransactions(user);
    }

    
   public void addTransaction() {
    	try {
    		DatabaseOperations.insertTransaction(user, account, type, transactionName, transactionAmount);
			   testInput = 1;
		}catch(Exception e) {
			testInput = 2;
		}
    }

}
