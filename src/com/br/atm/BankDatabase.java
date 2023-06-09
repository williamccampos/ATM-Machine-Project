package com.br.atm;

import java.util.ArrayList;

public class BankDatabase {
	
	static ArrayList<Account> accounts = new ArrayList<Account>();
	
	public BankDatabase() {
		Account accounts1 = new Account("Customer1", 12345, 11111, 1000.0, 1200.0, 0);
	    Account accounts2 = new Account("Customer2", 98765, 22222, 200.0, 200.0, 0);
	    Account accounts3 = new Account("Customer3", 19234, 33333, 200.0, 200.0, 0);
	    Account accounts4 = new Account("Manager1", 99999, 00000, 0, 0, 1);
	    accounts.add(accounts1);
	    accounts.add(accounts2);
	    accounts.add(accounts3);
	    accounts.add(accounts4);
	}
	
	public Account getAccount(int accountnumber) {
		for(Account currentAccount : accounts) {
			if(currentAccount.getAccountNumber() == accountnumber)
				return currentAccount;
		}
		
		return null;
	}
	
	private Account getAccountpin(int PIN) {
		for(Account currentAccount : accounts) {
			if(currentAccount.GetPin() == PIN)
				return currentAccount;
		}
		
		return null;
	}
	
	public boolean authenticateUser(int userPIN) {
		Account userAccount = getAccountpin(userPIN);
		
		if(userAccount != null)
			return userAccount.validatePIN(userPIN);
		else
			return false;
	}
	
	public double getAvailableBalance(int userAccountNumber) {
		return getAccount(userAccountNumber).getAvailableBalance();
	}
	
	public double getTotalBalance(int userAccountNumber) {
		return getAccount(userAccountNumber).getTotalBalance();
	}
	
	public void credit(int userAccountNumber, double amount) {
		getAccount(userAccountNumber).credit(amount);
	}
	
	public void debit(int userAccountNumber, double amount) {
		getAccount(userAccountNumber).debit(amount);
	}
	
	public int getadmin(int userAccountNumber) {
		return getAccountpin(userAccountNumber).getIsAdmin();
	}
	
	public static AccountIterator createIterator() {
		return new AccountIterator(accounts);
	}
	
	public int getaccpin(int PIN) {
		for(Account currentAccount : accounts) {
			if(currentAccount.GetPin() == PIN)
				return currentAccount.getAccountNumber();
			else
				return 1;
		}
		return PIN;
	}
	
	public static void Adduser() {
		String name = Screen.Inputfield1.getText();
		int accountnumber = Integer.parseInt( Screen.Inputfield2.getText() );
		int pin = Integer.parseInt( Screen.Inputfield4.getText() );
		int balance = Integer.parseInt( Screen.Inputfield3.getText() );
		  
		Account newaccount = new Account(name, accountnumber, pin, balance, balance, 0);
		accounts.add(newaccount);
		 
		 Screen.Inputfield1.setText("");
		 Screen.Inputfield2.setText("");
		 Screen.Inputfield3.setText("");
		 Screen.Inputfield4.setText("");
	}
	
	public static void Deleteuser(int position) {
		accounts.remove(position);
	}
}
