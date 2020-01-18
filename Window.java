package codes;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements ActionListener{
	
	JButton viewDatabase, cashMemo, exit;
	JLabel back,upper;
	
	JPanel buttons;
	
	Database database;
	CashMemo cashmemo;
	
	public Window(){
		
		showWindow();
		addingGUI();
		showWindow();
		events();
	}
	
	public void showWindow(){
		
		super.setTitle("Chocolate Mania ^_^");
		super.setSize(800, 600);
		super.setLocation(300, 50);
		super.setResizable(false);	
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setLayout(null);
		
		super.setVisible(true);
	}
	
	
	public void addingGUI(){

		
		ImageIcon icon = new ImageIcon("bg1.jpg");
		back = new JLabel(icon);
		back.setBounds(0, 0, 800, 600);
		
		ImageIcon tp = new ImageIcon("titlep.jpg");
		upper = new JLabel(tp);
		upper.setBounds(125,80,554,152);
		
		buttons = new JPanel();
		buttons.setBounds(200, 280, 400, 20);
		buttons.setLayout(new GridLayout(1,3));
		
		viewDatabase = new JButton("View Database");
		cashMemo = new JButton("CashMemo");
		exit = new JButton("Exit");
	
		buttons.add(viewDatabase);
		buttons.add(cashMemo);
		buttons.add(exit);
		back.add(buttons);
		back.add(upper);
		super.add(back);
		
		
	}

	public void events(){
		viewDatabase.addActionListener(this);
		cashMemo.addActionListener(this);
		exit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent click){
		
		if(click.getSource() == viewDatabase){
			database = new Database();
		}
		else if(click.getSource() == cashMemo){
			cashmemo = new CashMemo();
		}
		else if(click.getSource() == exit){
			super.dispose();
		}
		
	}
	
	
	
	
	
	
	
}
