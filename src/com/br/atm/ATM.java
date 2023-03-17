package com.br.atm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JFrame;

//Classe principal do ATM
//Representa a automação dá máquina
public class ATM {
	
	private boolean userAuthenticated;
	private int currentAccountNumber;
	private Screen screen;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	private Deposit depositSlot;
	private BankDatabase bankDatabase;
	private int AdminCheck;
	private String userinput = "";
	private int position = 0;
	private static ATM uniqueinstance;
	Iterator Users = BankDatabase.createIterator();
	
	//Constantes correspondente ao menu de opçoes principais
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	public ATM() {
		
		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen();
		
		keypad = new Keypad();
		
		cashDispenser = new CashDispenser();
		depositSlot = new Deposit(AdminCheck, screen, bankDatabase, keypad, depositSlot);
		bankDatabase = new BankDatabase();
	}
	
	//Start ATM
	public void run() {
		
		startlogin();
	}
	
	void startlogin() {
		
		position = 0;
		screen.createlogin();
		userinput = "";
		
		authenticate check = new authenticate();
		screen.Mainframe.revalidate();
		screen.Inputfield2.setText("");
		keypad.setbuttons();
		addkeypadlisteners();
		
		screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
		screen.Mainframe.revalidate();
		keypad.BEnter.addActionListener(check);
		screen.Mainframe.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		screen.Mainframe.setSize( 400, 280 ); // set frame size
		screen.Mainframe.setVisible( true );
		screen.Mainframe.revalidate();
	}
	
	public void authenticateuser(int pin) {
		userAuthenticated = bankDatabase.authenticateUser(pin);
		
		if(userAuthenticated) {
			int accountNumber = bankDatabase.getaccpin(pin);
			AdminCheck = bankDatabase.getadmin(pin);
			if(AdminCheck == 0) {
				currentAccountNumber = accountNumber;
				screen.Mainframe.getContentPane().removeAll();
				screen.Mainframe.revalidate();
				createmenu();
				screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
				screen.Mainframe.revalidate();
			}else
	    		  
	    	  createAdminGUI();
	    	  Iterator UserIterator = BankDatabase.createIterator(); 
	    	  Addcheck check = new Addcheck();
	    	  Deletecheck check2 = new Deletecheck();
	    	  screen.button2.addActionListener(check);
	    	  screen.button3.addActionListener(check2);
		} else
			screen.messageJLabel3.setText("Invalid account number or PIN. Please try again.");
	}
	
	private class authenticate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int PIN = Integer.parseInt(screen.Inputfield2.getText());
			
			authenticateuser(PIN);
		}
	}
	
	private class Addcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			BankDatabase.Adduser();
		}
	}
	
	private class Deletecheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			BankDatabase.Deleteuser(position);
			position = position - 1;
		}
	}
	
	public void createmenu() {
	  screen.setSize( 300, 150 );
  	  balancecheck check1 = new balancecheck();
  	  Depositcheck check2 = new Depositcheck();
  	  Withdrawcheck check3 = new Withdrawcheck();
  	  Exitcheck check4 = new Exitcheck();
  	  screen.Mainframe.getContentPane().removeAll();
  	  screen.Mainframe.revalidate();
  	  
  	  //Add the keypad panel to the GUI
  	  screen.Mainframe.add( keypad.addkeypad(), BorderLayout.CENTER);
  	  screen.createmenu();
  	  Account Account1 = bankDatabase.getAccount(currentAccountNumber);
  	  screen.messageJLabel.setText("Welcome " + Account1.getUsername() + "                                                                                   ");
  	  
  	  keypad.getB1().addActionListener(check1);
  	  keypad.getB2().addActionListener(check3);
  	  keypad.getB3().addActionListener(check2);
  	  keypad.getB4().addActionListener(check4);
  	  screen.Mainframe.revalidate();
	}
	
	private class balancecheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userinput = "";
			performTransaction(1);
		}
	}
	
	private class Withdrawcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userinput = "";
			performTransaction(2);
		}
	}
	
	private class Depositcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userinput = "";
			performTransaction(3);
		}
	}
	
	private class Exitcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			startlogin();
		}
	}
	
	private void performTransaction(int a) {
		
		Transaction currentTransaction = null;
		
		currentTransaction = createTransaction(a);
		keypad.setbuttons();
		addkeypadlisteners();
		
		userinput = "";
		screen.Inputfield2.setText(userinput);
		currentTransaction.execute();
		
		Backcheck Back = new Backcheck();
		screen.Exit.addActionListener(Back);
		screen.Mainframe.revalidate();
	}
	
	public class Backcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createmenu();
			screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
			screen.Mainframe.revalidate();
			userinput = "";
			screen.Inputfield2.setText(userinput);
		}
	}
	
	private Transaction createTransaction(int type) {
		Transaction temp = null;
		screen.getContentPane().removeAll();
		screen.revalidate();
		
		if(type == 1) {
			temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
		}else if(type == 2) {
			temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
		}else if(type == 3) {
			screen.setSize(400, 250);
			temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
		}
		return temp;
	}
	
	public void createAdminGUI() {
		screen.Mainframe.getContentPane().removeAll();
		Nextcheck Ncheck = new Nextcheck();
		Prevcheck Pcheck = new Prevcheck();
		Exitcheck check4 = new Exitcheck();
		screen.Mainframe.revalidate();
		screen.createAdminpage();
		screen.button1.addActionListener(Ncheck);
		screen.button4.addActionListener(Pcheck);
		screen.Exit.addActionListener(check4);
		screen.Mainframe.revalidate();
		
	}
	
	public void addkeypadlisteners() {
		BCheck BC = new BCheck();
		BClear BC1 = new BClear();
		keypad.getB1().addActionListener(BC);
	  	keypad.getB2().addActionListener(BC);
	  	keypad.getB3().addActionListener(BC);
	  	keypad.getB4().addActionListener(BC);
	  	keypad.getB5().addActionListener(BC);
	  	keypad.B6.addActionListener(BC);
	  	keypad.B7.addActionListener(BC);
	  	keypad.B8.addActionListener(BC);
	  	keypad.B9.addActionListener(BC);
	  	keypad.B0.addActionListener(BC);
	  	keypad.BClear.addActionListener(BC1);
	}
	
	public class BCheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String label = b.getLabel();
			userinput = userinput + label;
			
			screen.Inputfield2.setText(userinput);
		}
	}
	
	public class BClear implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userinput = "";
			screen.Inputfield2.setText(userinput);
		}
	}
	
	public class Nextcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			IterateUser(BankDatabase.createIterator());
		}
	}
	
	public class Prevcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			prevIterateUser(BankDatabase.createIterator());
		}
	}
	
	public void IterateUser(Iterator Iterator) {
		if(Iterator.hasNext(position) == true) {
			position = position + 1;
			
			Account AccountItem = (Account)Iterator.next(position);
			screen.messageJLabel2.setText("Username: " + AccountItem.getUsername());
			screen.messageJLabel3.setText("Avaliable Balance: " + AccountItem.getAvailableBalance());
			screen.messageJLabel4.setText("Avaliable Balance: " + AccountItem.getTotalBalance());
		}
	}
	
	public void prevIterateUser(Iterator Iterator) {
		if(Iterator.hasPrev(position) == true) {
			position = position - 1;
			Account AccountItem = (Account)Iterator.next(position);
			screen.messageJLabel2.setText("Username: " + AccountItem.getUsername());
			screen.messageJLabel3.setText("Avaliable Balance: " + AccountItem.getAvailableBalance());
			screen.messageJLabel4.setText("Avaliable Balance: " + AccountItem.getTotalBalance());
		}
	}
	
	public static ATM getinstance() {
		if(uniqueinstance == null) {
			uniqueinstance = new ATM();
		}
		return uniqueinstance;
	}
	
}
