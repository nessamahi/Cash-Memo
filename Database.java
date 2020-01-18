package codes;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.*;

public class Database extends JFrame{
	
	JPanel back,main;
	JLabel backLabel;
	JLabel title, price, quantity;
	JTable dataTable;
	
	ArrayList list;
	
	public Database(){
		
		readingData();
		addingPanel();
		addingData();
		showWindow();
	}

	public void showWindow(){
		
		super.setTitle("Database");
		super.setSize(830, 630);
		super.setLocation(300, 20);
		super.setResizable(false);	
		super.setLayout(null);
		
		super.setVisible(true);
	}

	public void readingData(){
		
		list = new ArrayList();
		
		String path = "Product.txt";
		File file = new File(path);
		
		
		try{
			Scanner scan = new Scanner(file);
		
			while(scan.hasNextLine()){
				String title = scan.next();
				double price = Double.parseDouble(scan.next());
				double unit = Double.parseDouble(scan.next());
				
				Product temp = new Product(title,price,unit);
				list.add(temp);
				
			}
			
	
			
			scan.close();
		}catch(Exception ex){}
		
	}
	public void addingPanel(){
		
		back = new JPanel();
		back.setBounds(0, 0, 830, 630);
		
		ImageIcon image = new ImageIcon("1.jpg");
		backLabel = new JLabel(image);
		backLabel.setBounds(0, 0,830 , 630);
		
		
		main = new JPanel();
		main.setBounds(50, 50, 730, 510);
		main.setLayout(new GridLayout(1, 1));
		
	    Object[][] data = {{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""}, {"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""},{"" ,"" ,""}};
	    String[] columnNames = {"Title","Price","Quantity"};
	
	    
	    dataTable = new JTable(data, columnNames);
	    dataTable.setRowHeight(50);
	    
	    JScrollPane scrollPane = new JScrollPane(dataTable);
		main.add(scrollPane);
		
	    dataTable.getTableHeader().setFont(new Font("TimesRoman", Font.BOLD, 25));
		dataTable.setFont(new Font("Arial", Font.ITALIC, 25));
		dataTable.setBackground(Color.LIGHT_GRAY);
		
		backLabel.add(main);
		back.add(backLabel);
		super.add(back);
	}

	
	
	public void addingData(){
		
		for(int i=1; i<list.size(); i++){
			
			Product temp = (Product)list.get(i);
			dataTable.getModel().setValueAt(temp.getTitle(), i-1, 0);
			dataTable.getModel().setValueAt(temp.getPrice(), i-1, 1);
			dataTable.getModel().setValueAt(temp.getQuantity(), i-1, 2);	
			
		}
	
	}
	
	

}





