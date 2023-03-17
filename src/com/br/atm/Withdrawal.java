package com.br.atm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;

public class Withdrawal extends Transaction{
	
	private int amount;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	
	private final static int CANCELED = 6;
	
	public Withdrawal(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
		super(userAccountNumber, atmScreen, atmBankDatabase);
		
		keypad = atmKeypad;
		cashDispenser = atmCashDispenser;
	}
	
	@Override
	public void execute() {
		displayMenuOfAmounts();
	}
	
	public void transaction(int amount) {
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		boolean cashDispensed = false;
		double availableBalance;
		
		availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
		
		if(amount <= availableBalance) {
			if(cashDispenser.isSufficientCashAvailable(amount)) {
				bankDatabase.debit(getAccountNumber(), amount);
			
			cashDispenser.dispenseCash(amount);
			//cashDispenser = true;
			
			screen.messageJLabel7.setText("\nYour cash has been" +
                    " dispensed. Please take your cash now.");
		} else
			screen.messageJLabel7.setText(
                    "\nInsufficient cash available in the ATM." +
                    "\n\nPlease choose a smaller amount.");
		} else {
			screen.messageJLabel7.setText(
	                  "\nInsufficient funds in your account." +
	                  "\n\nPlease choose a smaller amount."); 
		}
	}
	
	public void displayMenuOfAmounts() {
		int userChoice = 0; // local variable to store return value

	      Screen screen = getScreen(); // get screen reference
	      screen.createWithdrawGUI();
	      screen.Mainframe.add( keypad.addkeypad(), BorderLayout.CENTER);
	      withdraw1 check1 = new withdraw1();
	      withdraw2 check2 = new withdraw2();
	      withdraw3 check3 = new withdraw3();
	      withdraw4 check4 = new withdraw4();
	      withdraw5 check5 = new withdraw5();
	      Keypad.getB1().addActionListener(check1);
	      Keypad.getB2().addActionListener(check2);
	      Keypad.getB3().addActionListener(check3);
	      Keypad.getB4().addActionListener(check4);
	      Keypad.getB5().addActionListener(check5);
	      
	      
	      
	      screen.Mainframe.revalidate();
	}
	
	public class withdraw1 implements ActionListener{
		public void actionPerformed(Action e) {
			transaction(20);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class withdraw2 implements ActionListener{
		public void actionPerformed(Action e) {
			transaction(40);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class withdraw3 implements ActionListener{
		public void actionPerformed(Action e) {
			transaction(60);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class withdraw4 implements ActionListener{
		public void actionPerformed(Action e) {
			transaction(100);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class withdraw5 implements ActionListener{
		public void actionPerformed(Action e) {
			transaction(200);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Keypad getKeypad() {
		return keypad;
	}

	public void setKeypad(Keypad keypad) {
		this.keypad = keypad;
	}

	public CashDispenser getCashDispenser() {
		return cashDispenser;
	}

	public void setCashDispenser(CashDispenser cashDispenser) {
		this.cashDispenser = cashDispenser;
	}

	public static int getCanceled() {
		return CANCELED;
	}
	
	

}
