package com.hit.view;

import java.awt.Font;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginView extends Observable implements View {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	@Override
	public void start() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(183, 69, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(183, 115, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(183, 162, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserName.setBounds(53, 75, 94, 16);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(53, 117, 94, 16);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblFileName = new JLabel("File Name");
		lblFileName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFileName.setBounds(53, 164, 94, 16);
		frame.getContentPane().add(lblFileName);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(12, 215, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
		frame.setResizable(false);
		frame.setVisible(true);

	}

}