package codes;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CashMemo extends JFrame implements ActionListener,ItemListener{
	
	
	JLabel itemTitle,price,quantity,blank1,blank2,blank3;
	JLabel blank[];
	
	JPanel itemDetails;
	JPanel totalDetails;
	
	JTable totalDetailsTable;
	JComboBox items;
	
	JTextField priceBox,totalBox;
	JSpinner quantityBox;
	
	JButton exit,total,print;
	JButton add;
	
	Print printRecipt;
	
	ArrayList list ;
	
	public  int row = 0;
	
	public CashMemo(){

		productData();
		addingitemDetailsPanel();
		addingTotalDetailsPanel();
		
		font();
		showWindow();
		actions();
		
	}
	
	
	
	public void showWindow(){
		setTitle("Memo");
		setSize(1000,500);
		setLocation(200, 70);
		setResizable(false);
		setLayout(new GridLayout(1, 2, 20, 0));	
		
		
		
		super.setVisible(true);
	}
	
	
	public void comboItems(){
		
		for(int i =0; i<list.size();i++){
			
			Product temp = (Product)list.get(i);
			items.addItem(temp.getTitle());
		}
	}
	
	
	public void addingitemDetailsPanel(){
		
		itemDetails = new JPanel();
		itemDetails.setBackground(Color.LIGHT_GRAY);
		
		items = new JComboBox();
		priceBox = new JTextField();
		totalBox = new JTextField();
		quantityBox = new JSpinner();
		
		exit = new JButton("Exit");
		total = new JButton("Total");
		print = new JButton("Print");
		add = new JButton("Add Item");
			
		itemTitle = new JLabel("           Item Title");
		price = new JLabel("               Price");
		quantity = new JLabel("            Quantity");

		blank = new JLabel[6];
		blank1 = new JLabel();
		blank2 = new JLabel();
		blank3 = new JLabel();
		
		itemDetails.setLayout(new GridLayout(12,2,10,5));
		
		itemDetails.add(blank1);
		itemDetails.add(blank2);
		comboItems();
		itemDetails.add(itemTitle);
		
		itemDetails.add(items);
		
		itemDetails.add(price);
		itemDetails.add(priceBox);
		
		itemDetails.add(quantity);
		itemDetails.add(quantityBox);
		
		itemDetails.add(blank3);
		itemDetails.add(add);
		
		for(int i = 0; i<blank.length; i++){
			blank[i] = new JLabel("");
			itemDetails.add(blank[i]);
		}
		
		itemDetails.add(total);
		itemDetails.add(totalBox);
		itemDetails.add(print);
		itemDetails.add(exit);
		
		add(itemDetails);
		
	}
	
	public void addingTotalDetailsPanel(){
		
		totalDetails = new JPanel();
		totalDetails.setLayout(new GridLayout(1, 1));
		totalDetails.setBounds(500, 30, 500, 320);
		totalDetails.setBackground(Color.LIGHT_GRAY);	
		
		Object[][] data = {{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""},{"" ,"" ,"" ,""}};
	    String[] columnNames = {"Title","Price","Quantity","SubTotal"};
		
		totalDetailsTable = new JTable(data,columnNames);
		JScrollPane scrollPane = new JScrollPane(totalDetailsTable);
		totalDetailsTable.setRowHeight(30);
		totalDetails.add(scrollPane);	
		
		super.add(totalDetails);
	
	}
	
	public void actions(){
		exit.addActionListener(this);
		print.addActionListener(this);
		add.addActionListener(this);
		total.addActionListener(this);
		items.addItemListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == add){
			
			
			String title = items.getSelectedItem().toString();
			String price = priceBox.getText();
			String quantity = quantityBox.getValue().toString();
			double total = Double.parseDouble(priceBox.getText()) * Double.parseDouble(quantityBox.getValue().toString());
			
			for(int i =0; i<list.size();i++){
				
				Product temp = (Product)list.get(i);
				
				if(title.equals(temp.getTitle()) == true){
					double previous = temp.getQuantity();
					double older = Double.parseDouble(quantity);
					
					if(previous-older>=0){
						temp.setQuantity(previous-older);
						totalDetailsTable.getModel().setValueAt(title, row, 0);
						totalDetailsTable.getModel().setValueAt(price, row, 1);
						totalDetailsTable.getModel().setValueAt(quantity, row, 2);
						totalDetailsTable.getModel().setValueAt(total, row, 3);
						row++;
						quantityBox.setValue(0);
					}
					else{
						JOptionPane.showMessageDialog(null,""+temp.getQuantity()+" available. Reduce Quantity");
					}
						
				}
			}
			  
			productEdit();
	
		}
		else if(e.getSource() == print){
			
			productPrint();
			printRecipt = new Print();
		}
		else if(e.getSource() == total){
			
			double totalprice = 0.0;
			
			for(int i = 0; i<row; i++){
				totalprice = totalprice + (double)totalDetailsTable.getModel().getValueAt(i, 3);
			}
			
			totalBox.setText(totalprice+"");
		}
		else if(e.getSource() == exit){
			
			super.dispose();
			
		}
	}
	
	public void itemStateChanged(ItemEvent e) {
		
		
		String title = items.getSelectedItem().toString();
		
		for(int i =0; i<list.size();i++){
			
			Product temp = (Product)list.get(i);
			if(title.equals(temp.getTitle()) == true){
				priceBox.setText(temp.getPrice()+"");
			}
		}
			
	}
	
	public void font(){
		itemTitle.setFont(new Font("TimesRoman", Font.BOLD, 25));
		items.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		price.setFont(new Font("TimesRoman", Font.BOLD, 25));

		priceBox.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		quantity.setFont(new Font("TimesRoman", Font.BOLD, 25));
		priceBox.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		quantityBox.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		exit.setFont(new Font("TimesRoman", Font.BOLD, 25));
		print.setFont(new Font("TimesRoman", Font.BOLD, 25));
		total.setFont(new Font("TimesRoman", Font.BOLD, 25));
		totalBox.setFont(new Font("TimesRoman", Font.BOLD, 25));
		add.setFont(new Font("TimesRoman", Font.BOLD, 25));
		totalDetailsTable.setFont(new Font("TimesRoman", Font.PLAIN, 18));
		totalDetailsTable.getTableHeader().setFont(new Font("TimesRoman", Font.BOLD, 25));
	}


	public void productData(){
		
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
	
	
	public void productEdit(){
				
		String path = "Product.txt";
		
		
		try{
			
			FileWriter fw = new FileWriter(path,false);
			
			for(int i=0; i<list.size();i++){
				Product temp = (Product)list.get(i);
				fw.write(temp.getTitle()+" "+temp.getPrice()+" "+temp.getQuantity()+"\n");
			}
			
			fw.close();
		}catch(Exception ex){}
				
	}
	
	public void productPrint(){
		
		String path = "Print.txt";
		
		
		try{
			
			FileWriter fw = new FileWriter(path,false);
			String text = "" ;
			
			
			for(int i = 0; i<row; i++){
				
						
				text = text +"\t"+ (String) totalDetailsTable.getModel().getValueAt(i, 0).toString() +"\t"+(String) totalDetailsTable.getModel().getValueAt(i, 1).toString() +"\t"+(String) totalDetailsTable.getModel().getValueAt(i, 2).toString() +"\t"+(String) totalDetailsTable.getModel().getValueAt(i, 3).toString() +"\n" ;
			}
			
			fw.write("\t\t       Super Store\n\n\n");
			fw.write("\tTitle\t" + "Price\t" + "Quantity\t" + "SubTotal\t" + "\n");
			fw.write(text);
			fw.write("\n\n\n\n\n\n\tTotal :::: "+totalBox.getText());
			
			fw.close();
		}catch(Exception ex){}
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
}

