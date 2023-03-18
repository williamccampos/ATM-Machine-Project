package com.br.atm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Keypad {
	
	private Scanner input;
	private String userinput;
	private static JButton B1;
	private static JButton B2;
	private static JButton B3;
	private static JButton B4;
	private static JButton B5;
	public JButton B6;
	public JButton B7;
	public JButton B8;
	public JButton B9;
	public JButton B0;
	public JButton BClear;
	public JButton BEnter;
	
	public Keypad() {
		input = new Scanner(System.in);
	}
	
	public int getInput() {
		return input.nextInt();
	}
	
	public void setbuttons() {
	   B1 = new JButton("1");
	   B1.setText("1");
	   B2 = new JButton("2");
	   B3 = new JButton("3");
	   B4 = new JButton("4");
	   B5 = new JButton("5");
	   B6 = new JButton("6");
	   B7 = new JButton("7");
	   B8 = new JButton("8");
	   B9 = new JButton("9");
	   B0 = new JButton("0");
	   BClear = new JButton("Clear");
	   BEnter = new JButton("Enter");
	}
	
	public JPanel addkeypad() {
		   JPanel panel = new JPanel();
		   panel.setPreferredSize(new Dimension(260, 180));
		   panel.setBackground(Color.gray);
		   panel.setLayout(new FlowLayout()); 
		   panel.add(B1);
		   panel.add(B2);
		   panel.add(B3);
		   panel.add(B4);
		   panel.add(B5);
		   panel.add(B6);
		   panel.add(B7);
		   panel.add(B8);
		   panel.add(B9);
		   panel.add(BClear);
		   panel.add(B0);
		   panel.add(BEnter);
		   
		   return panel;
	}
	
	public String userinput() {
		   return userinput();
	}
	   
	public void resetuserinput() {
		   userinput = "";
	}

	public String getUserinput() {
		return userinput;
	}

	public void setUserinput(String userinput) {
		this.userinput = userinput;
	}

	public static JButton getB1() {
		return B1;
	}

	public static void setB1(JButton b1) {
		B1 = b1;
	}

	public static JButton getB2() {
		return B2;
	}

	public static void setB2(JButton b2) {
		B2 = b2;
	}

	public static JButton getB3() {
		return B3;
	}

	public static void setB3(JButton b3) {
		B3 = b3;
	}

	public static JButton getB4() {
		return B4;
	}

	public static void setB4(JButton b4) {
		B4 = b4;
	}

	public static JButton getB5() {
		return B5;
	}

	public static void setB5(JButton b5) {
		B5 = b5;
	}

	public JButton getB6() {
		return B6;
	}

	public void setB6(JButton b6) {
		B6 = b6;
	}

	public JButton getB7() {
		return B7;
	}

	public void setB7(JButton b7) {
		B7 = b7;
	}

	public JButton getB8() {
		return B8;
	}

	public void setB8(JButton b8) {
		B8 = b8;
	}

	public JButton getB9() {
		return B9;
	}

	public void setB9(JButton b9) {
		B9 = b9;
	}

	public JButton getB0() {
		return B0;
	}

	public void setB0(JButton b0) {
		B0 = b0;
	}

	public JButton getBClear() {
		return BClear;
	}

	public void setBClear(JButton bClear) {
		BClear = bClear;
	}

	public JButton getBEnter() {
		return BEnter;
	}

	public void setBEnter(JButton bEnter) {
		BEnter = bEnter;
	}

	public void setInput(Scanner input) {
		this.input = input;
	}
	
	

}
