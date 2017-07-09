package com.hit.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MMUView extends Observable implements View {

	public ArrayList<String> commands;
	public int numProcesses;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	public ArrayList<String> playCommands=new ArrayList<String>();
	public List<String> selectedProcesses= new ArrayList<String>();
	public Integer playPageF=0;
	public Integer playPageR=0;
	@Override
	public void start() {
		numProcesses=Integer.parseInt(commands.get(commands.size()-1));
		commands.remove(commands.size()-1);
		playCommands.addAll(commands);
		frame = new JFrame();
		frame.setBounds(100, 100, 683, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblProcesses = new JLabel("Processes");
		lblProcesses.setBounds(585, 70, 64, 16);
		frame.getContentPane().add(lblProcesses);
		
		JList list = new JList();
		DefaultListModel<String> defultModel = new DefaultListModel<String>();
		for(int i=1;i<=numProcesses;i++)
		{
			defultModel.addElement("Processe "+i);
		}
		list.setModel(defultModel);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setBounds(585, 99, 78, 141);
		list.addListSelectionListener(new ListSelectionListener(){
			 public void valueChanged(ListSelectionEvent e) { 
				 selectedProcesses.clear();
				 selectedProcesses.addAll((List<String>)list.getSelectedValuesList());
				
			}
		});
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
		DefaultTableModel defultTableModel=new DefaultTableModel(6,numProcesses);
		table.setModel(defultTableModel);
		table.setRowHeight(26);
		frame.getContentPane().add(table);
		
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentCom = playCommands.get(0);
				int j = currentCom.indexOf(":");
				String com = currentCom.substring(0,j);
				switch (com) {
				case "PF":
					playPageF++;
					textField.setText(playPageF.toString());
					break;
				case "GP":
					int colNum = Character.getNumericValue(currentCom.charAt(5))-1;
					int prosNum = colNum+1;
					if(selectedProcesses.contains("Processe "+prosNum))
					{
					String str1 =currentCom.substring(7,currentCom.indexOf("[")-1);
					table.getModel().setValueAt(Integer.parseInt(str1), 0, colNum);
					currentCom= currentCom.substring(currentCom.indexOf("[")+1);
					for(int k=1;k<5;k++)
					{
					table.getModel().setValueAt(Integer.parseInt(currentCom.substring(0,currentCom.indexOf(","))), k,colNum);
					currentCom=currentCom.substring(currentCom.indexOf(",")+2);
					}
					table.getModel().setValueAt(Integer.parseInt(currentCom.substring(0,currentCom.indexOf("]"))), 5,colNum);
					}
					break;
				case "PR":
					playPageR++;
					textField_1.setText(playPageR.toString());
					break;
				default:
					break;
				}
				playCommands.remove(0);
				if(playCommands.isEmpty())
				{
					for (int i = 0; i < table.getRowCount(); i++)
					      for(int k = 0; k < table.getColumnCount(); k++) {
					          table.setValueAt("", i, k);
					      }
					playCommands.addAll(commands);
					playPageR=0;
					textField_1.setText(playPageR.toString());
					playPageF=0;
					textField.setText(playPageF.toString());
					JOptionPane.showMessageDialog(frame, "You are out of commands!");
				}
			}
		});
		btnPlay.setBounds(12, 180, 78, 25);
		frame.getContentPane().add(btnPlay);
		
		JButton btnPlayAll = new JButton("Play All");
		btnPlayAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  for (int i = 0; i < table.getRowCount(); i++)
				      for(int j = 0; j < table.getColumnCount(); j++) {
				          table.setValueAt("", i, j);
				      }
			Integer pageF=0;
			Integer pageR=0;
				for(String i : commands)
				{
					int j = i.indexOf(":");
					String com = i.substring(0,j);
					switch (com) {
					case "PF":
						pageF++;
						textField.setText(pageF.toString());
						break;
					case "GP":
						int colNum = Character.getNumericValue(i.charAt(5))-1;
						int prosNum = colNum+1;
						if(selectedProcesses.contains("Processe "+prosNum))
						{
						String str1 =i.substring(7,i.indexOf("[")-1);
						table.getModel().setValueAt(Integer.parseInt(str1), 0, colNum);
						i= i.substring(i.indexOf("[")+1);
						for(int k=1;k<5;k++)
						{
						table.getModel().setValueAt(Integer.parseInt(i.substring(0,i.indexOf(","))), k,colNum);
						i=i.substring(i.indexOf(",")+2);
						}
						table.getModel().setValueAt(Integer.parseInt(i.substring(0,i.indexOf("]"))), 5,colNum);
						}
						break;
					case "PR":
						pageR++;
						textField_1.setText(pageR.toString());
						break;
					default:
						break;
					}
					
					
				}
			}
		});
		btnPlayAll.setBounds(104, 180, 86, 25);
		frame.getContentPane().add(btnPlayAll);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for (int i = 0; i < table.getRowCount(); i++)
				      for(int j = 0; j < table.getColumnCount(); j++) {
				          table.setValueAt("", i, j);
				      }
				playPageR=0;
				textField_1.setText(playPageR.toString());
				playPageF=0;
				textField.setText(playPageF.toString());
			}
		});
		btnClear.setBounds(202, 180, 97, 25);
		frame.getContentPane().add(btnClear);
		
		frame.setResizable(false);
		frame.setVisible(true);
		

	}


	public ArrayList<String> getCommands() {
		return commands;
	}


	public void setCommands(ArrayList<String> commands) {
		this.commands = commands;
	}


	public int getNumProcesses() {
		return numProcesses;
	}


	public void setNumProcesses(int numProcesses) {
		this.numProcesses = numProcesses;
	}


	public List<String> getSelectedProcesses() {
		return selectedProcesses;
	}


	public void setSelectedProcesses(List<String> selectedProcesses) {
		this.selectedProcesses = selectedProcesses;
	}
	
	

}
