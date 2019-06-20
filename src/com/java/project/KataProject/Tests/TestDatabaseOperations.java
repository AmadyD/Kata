package com.java.project.KataProject.Tests;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.java.project.KataProject.Models.Account;
import com.java.project.KataProject.Models.Transaction;
import com.java.project.KataProject.Models.User;

public class TestDatabaseOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List <Transaction> liste = getTransactions();
		System.out.println("----Liste des transactions----");
		 for (Transaction transaction : liste) {
        	 System.out.println( transaction );
         }
		 System.out.println("\n");
		 
		 List <User> liste1 = getUsers();
		 System.out.println("----Liste des utilisateurs----");
		 for (User user : liste1) {
        	 System.out.println( user );
         }
		 System.out.println("\n");
		 
		 List <Account> liste2 = getAccounts();
		 System.out.println("----Liste des comptess----");
		 for (Account account : liste2) {
        	 System.out.println( account );
         }
		 System.out.println("\n");
		 
		 System.out.println("------Test Login-------");
		 long i = userLogin(22091996,123456);
		 System.out.println(i);
		 System.out.println("\n");
		 
		 System.out.println("------Test getUser------");
		 User utilisateur = getUser(22091996,123456);
		 System.out.println(utilisateur);
		 System.out.println("\n");
		 
		 System.out.println("------Test getAccount------");
		 Account account = getAccount(utilisateur);
		 System.out.println(account);
		 System.out.println("\n");
		 
		 System.out.println("-----Test liste transactions d'un utilisateur ----");
		 List<Transaction> list = getUserTransactions(utilisateur);
		 for (Transaction transaction : list) {
             System.out.println( transaction );
         }
		 System.out.println("\n");
	}
	
	
	public static List<Transaction> getTransactions(){
		EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        List <Transaction> transactions1;
        try {
       	 entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
            entityManager = entityManagerFactory.createEntityManager();
            
           transactions1 = entityManager.createQuery( "from Transaction order by date desc", Transaction.class )
                    .getResultList();
            
            
	} finally {
        if ( entityManager != null ) entityManager.close();
        if ( entityManagerFactory != null ) entityManagerFactory.close();
    }
        
        return transactions1;

	}
	
	public static List<User> getUsers(){
		EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        List <User> users1;
        try {
       	 entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
            entityManager = entityManagerFactory.createEntityManager();
            
           users1 = entityManager.createQuery( "from User", User.class )
                    .getResultList();
            
            
	} finally {
        if ( entityManager != null ) entityManager.close();
        if ( entityManagerFactory != null ) entityManagerFactory.close();
    }
        
        return users1;

	}


public static List<Account> getAccounts(){
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    List <Account> accounts1;
    try {
   	 entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
        entityManager = entityManagerFactory.createEntityManager();
        
       accounts1 = entityManager.createQuery( "from Account", Account.class )
                .getResultList();
        
        
} finally {
    if ( entityManager != null ) entityManager.close();
    if ( entityManagerFactory != null ) entityManagerFactory.close();
}
    
    return accounts1;

}

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
public void insertTransaction(User user, Account account, String type,String transactionName,double transactionAmount) {
	EntityManagerFactory entityManagerFactory = null;
    EntityManager entityManager = null;
    
	 try {
		 	entityManagerFactory = Persistence.createEntityManagerFactory("KataProject");
	        entityManager = entityManagerFactory.createEntityManager();
	   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  	Date date = new Date();
		  	 EntityTransaction operation = entityManager.getTransaction();
	         operation.begin();
	         Transaction trans = user.makeATransaction(account.getId(), type, transactionName, transactionAmount,dateFormat.format(date));
	         entityManager.persist(trans);
	         
	         operation.commit();

	   } finally {
	       if ( entityManager != null ) entityManager.close();
	       if ( entityManagerFactory != null ) entityManagerFactory.close();
	   }
	 

}


}