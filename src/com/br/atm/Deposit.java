package com.br.atm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends Transaction{
	
	private double amount;
	private Keypad keypad;
	private Deposit deposit;
	private final static int CANCELED = 0;
	
	public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, Deposit atmDepositSlot) {
		super(userAccountNumber, atmScreen, atmBankDatabase);
		
		keypad = atmKeypad;
		deposit = atmDepositSlot;
	}


	@Override
	public void execute() {
		promptForDepositAmount();
	}
	
	public void makedeposit(double amount) {
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		if(amount != CANCELED) {
			screen.messageJLabel2.setText( "\nPlease insert a deposit envelope containing " + amount);
			
			boolean envelopeReceived = deposit.isEnvelopeReceived();
			
			if(envelopeReceived) {
				screen.messageJLabel2.setText("\nYour envelope has been " + "received.\nNOTE: The money just deposited will not ");
			    screen.messageJLabel3.setText("be available until we verify the amount of any " + "enclosed cash and your checks clear.");
			    bankDatabase.credit(getAccountNumber(), amount); 
			} else {
				screen.messageJLabel2.setText("\nYou did not insert an " + "envelope, so the ATM has canceled your transaction.");
			}
		} else {
			screen.messageJLabel2.setText("\nCanceling transaction...");
		}
	}
	
	private boolean isEnvelopeReceived() {
		// TODO Auto-generated method stub
		return false;
	}


	private void promptForDepositAmount() {
		Screen screen = getScreen(); // get reference to screen
	    screen.CreateDepositGUI(); // receive input of deposit amount
	    screen.Mainframe.add( keypad.addkeypad(), BorderLayout.CENTER);
	    Depositcheck check1 = new Depositcheck();  
	    keypad.BEnter.addActionListener( check1 );
	    screen.Mainframe.revalidate();
	}
	
	private class Depositcheck implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double input = Integer.parseInt(screen.Inputfield2.getText());
			double amount = input / 100;
			
			makedeposit(amount);
		}
	}
	

}
