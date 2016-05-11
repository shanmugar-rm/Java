package se.bank.src;

import java.sql.*;

public class dbconnection {
	public void modify(String q)
	{
	    try
	         {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	             
	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank","root","root");
	         Statement st=con.createStatement();
	         st.execute(q);
	         }
	         catch(Exception ex)
	         {
	        	 System.out.println(ex.toString());          
	         }

	}

	public  ResultSet selectdata(String q)
	{
	    ResultSet rs=null;
	    try
	         {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	             
	        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank","root","root");
	         Statement st=con.createStatement();
	         rs=st.executeQuery(q);
	         System.out.println("Success2");
	         
	         }
	         catch(Exception ex)
	         {
	          
	         }
	       return rs;
	 }


}