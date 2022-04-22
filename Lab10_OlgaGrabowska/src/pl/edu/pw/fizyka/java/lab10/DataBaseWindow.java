package pl.edu.pw.fizyka.java.lab10;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class DataBaseWindow extends JFrame{

	JTextField poleTxt;
	JButton guzik;
	JTextArea przestrzenTxt;
	
	
	public DataBaseWindow() throws SQLException {
		//RAMKA--------------------------------------------------
		this.setLayout(new GridLayout(3,1));
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//KOMPONENTY---------------------------------------------
		poleTxt = new JTextField("Napisz tu polecenie");
		guzik = new JButton("Połącz");
		przestrzenTxt = new JTextArea("Tu pojawi się Twoje polecenie");
		
		//GUZIK-----------
		
		 guzik.addActionListener(new ActionListener() {

	            @Override
	            public void actionPerformed(ActionEvent e) 
	            {
	            	Connection conn = null;
	            
	            	try {
	        			conn = DriverManager.getConnection(	"jdbc:h2:./data/nazwabazy", "sa", "");
	        			System.out.println("Połączono z bazą danych"); 
	        			String kwerenda = poleTxt.getText();
	        			przestrzenTxt.setText(kwerenda);
	        			
	        			Statement statement = conn.createStatement();
	        			statement.execute(kwerenda);
	        			
	        			ResultSet rs = statement.getResultSet();
	        			ResultSetMetaData md  = rs.getMetaData();
	        			
	        			for (int ii = 1; ii <= md.getColumnCount(); ii++){
	        				System.out.print(md.getColumnName(ii) + " | ");						
	        				
	        			}
	        			System.out.println();
	        			
	        			while (rs.next()) {
	        				for (int ii = 1; ii <= md.getColumnCount(); ii++){
	        					System.out.print(rs.getObject(ii) + " | ");							
	        				}
	        				System.out.println();
	        			}
	            	} catch (SQLException e1) {
	            		
	            	}
	            }
	         
	      });
		 
		 //poleTxt
		
		 
		
		//DODAWANIE KOMPONENTÓW DO RAMKI ------------------------
		this.add(guzik);
		this.add(poleTxt);
		this.add(przestrzenTxt);
	}

	public static void main(String[] args) throws SQLException {
		DataBaseWindow frame = new DataBaseWindow();
		frame.setVisible(true);

	}

}
