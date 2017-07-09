package com.hit.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;

public class MMUVIEWGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MMUVIEWGUI window = new MMUVIEWGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MMUVIEWGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(12, 180, 78, 25);
		frame.getContentPane().add(btnPlay);
		
		JButton btnPlayAll = new JButton("Play All");
		btnPlayAll.setBounds(104, 180, 86, 25);
		frame.getContentPane().add(btnPlayAll);
		
		JLabel lblProcesses = new JLabel("Processes");
		lblProcesses.setBounds(585, 70, 64, 16);
		frame.getContentPane().add(lblProcesses);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Processes 1", "Processes 2", "Processes 3", "Processes 4", "Processes 5", "Processes 6", "Processes 7"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(585, 99, 78, 141);
		frame.getContentPane().add(list);
		
		JLabel lblNewLabel = new JLabel("Page Fult Amount");
		lblNewLabel.setBounds(457, 13, 101, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Page Replacement Amount");
		lblNewLabel_1.setBounds(457, 42, 154, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(634, 10, 29, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(634, 39, 29, 22);
		frame.getContentPane().add(textField_1);
		
		table = new JTable();
		table.setBounds(12, 13, 433, 154);
		frame.getContentPane().add(table);
		
	}
}
