package com.java.project.KataProject.dbOperations;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.java.project.KataProject.Models.*;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DatabaseOperations {

///////----------   Fonction permettant de vérifier le login et le mot de passe de l'utilisateur   -------/////////

public static long userLogin(int login, int password) {
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    long result = 0;
    try {
    	entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
        entityManager = entityManagerFactory.createEntityManager();
    	Query query =  entityManager.createQuery( "select count(us) from User us where us.login= :log and us.password= :pass");
        query.setParameter("log", login); 
        query.setParameter("pass", password); 
        result = (long) query.getSingleResult();
           
   } finally {
       if ( entityManager != null ) entityManager.close();
       if ( entityManagerFactory != null ) entityManagerFactory.close();
   }
    
   return result;
}

///////------   Fonction permettant de recupérer un utilisateur    ------///////

public static User getUser(int login, int password) {
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    User us;
    try {
    	entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
        entityManager = entityManagerFactory.createEntityManager();
    	Query query =  entityManager.createQuery( "from User us where us.login= :log and us.password= :pass");
        query.setParameter("log", login); 
        query.setParameter("pass", password); 
        us = (User) query.getSingleResult();
           
   } finally {
       if ( entityManager != null ) entityManager.close();
       if ( entityManagerFactory != null ) entityManagerFactory.close();
   }
    
    return us;
}


//////////--------  Fonction permettant de recupérer le compte d'un utilisateur   -------//////////////

public static Account getAccount(User user) {
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    Account account;
    try {
    	entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
        entityManager = entityManagerFactory.createEntityManager();
    	Query query =  entityManager.createQuery( "from Account account where account.idUser= :idUs");
        query.setParameter("idUs", user.getId()); 
        
        account = (Account) query.getSingleResult();
           
   } finally {
       if ( entityManager != null ) entityManager.close();
       if ( entityManagerFactory != null ) entityManagerFactory.close();
   }
    
    return account;
}

/////-------  Fonction permettant de recupérer la liste des transactions d'un utilisateur  ---------///////

@SuppressWarnings("unchecked")
public static List<Transaction> getUserTransactions(User user){
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
	List<Transaction> liste;
	
	 try {
		 	entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
	        entityManager = entityManagerFactory.createEntityManager();
			Query query = entityManager.createQuery( "from Transaction trans where trans.idUser= :id order by trans.date desc" );
			query.setParameter("id", user.getId()); 
			liste = query.getResultList();
	   } finally {
	       if ( entityManager != null ) entityManager.close();
	       if ( entityManagerFactory != null ) entityManagerFactory.close();
	   }
	
	return liste;
}

//////------  Fonction permettant d'insérer une nouvelle transaction  --------------/////

public static void insertTransaction(User user, Account account, String type,String transactionName,double transactionAmount) {
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    
	 try {
		 	entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
	        entityManager = entityManagerFactory.createEntityManager();
	        double finalAmount ;
	   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  	Date date = new Date();
		  	 EntityTransaction operation = entityManager.getTransaction();
	         operation.begin();
	         Transaction trans = user.makeATransaction(account.getId(), type, transactionName, transactionAmount,dateFormat.format(date));
	         
	         entityManager.persist(trans);
	         
		        if(type.equals("dépôt")) {
		        	finalAmount = account.getAmount() + transactionAmount;
		        	account.setAmount(finalAmount);
		        }else {
		        	finalAmount = account.getAmount() - transactionAmount;
		        	account.setAmount(finalAmount);
		        }
		        
	         entityManager.persist(account);
	         
	         operation.commit();

	   } finally {
	       if ( entityManager != null ) entityManager.close();
	       if ( entityManagerFactory != null ) entityManagerFactory.close();
	   }
	 

}


}