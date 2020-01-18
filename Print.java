package codes;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;

public class Print extends JFrame implements ActionListener{
	
	JPanel buttonPanel;
	JTextArea printArea;
	JButton ok,cancel;
	
	
	public Print(){
		
		addingPrintField();
		addingButton();
		showWindow();
		events();
	}
	
	
	public void showWindow(){
		setTitle("Print");
		setSize(500,480);
		setLocation(400, 50);
		setResizable(false);
		setLayout(null);
	
		super.setVisible(true);
	}
	

	public void addingPrintField(){
	
		printArea = new JTextArea();
		printArea.setBounds(0, 0, 500, 400);
		
		addingPrintData();
		printArea.setEditable(false);
	
		super.add(printArea);
		
	}
	
	public void addingButton(){

		
		buttonPanel = new JPanel();
		ok = new JButton("PRINT!!!");
		cancel = new JButton("Exit");
		
		ok.setFont(new Font("TimesRoman", Font.BOLD, 25));
		cancel.setFont(new Font("TimesRoman", Font.BOLD, 25));
		
		buttonPanel.setBounds(0,400,500, 50);
		buttonPanel.setLayout(new GridLayout(1,2));
		
		
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		super.add(buttonPanel);
		
		
	}
	
	public void events(){
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		
	}
	
	
	 public void actionPerformed(ActionEvent click){
		 
		 if(click.getSource() == ok){
			 
			 String path = "Print.txt";
				
				File file = new File(path);
				
				
				
				Desktop desktop = Desktop.getDesktop();
				
				try {
					desktop.print(file);
				} catch (Exception e) {}
			 
			 
		 }
		 
		 else if(click.getSource() == cancel){
			 
			 super.dispose();
			 
		 }
	 }
	
	public void addingPrintData(){
		
		String path = "Print.txt";
		File file = new File(path);
		
		
		try{
			Scanner scan = new Scanner(file);
			String data = "" ;
			
			while(scan.hasNextLine()){
				data = data + scan.nextLine()+"\n";
			}
			
			printArea.setText(data);
			
			scan.close();
		}catch(Exception ex){}
		
	}
	
	
	
}
