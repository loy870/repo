/*
 * This version shows the use of parameters when creating a result set.
 * See that we can scroll forward and backward through the result set,
 * only IF we set that parameter ResultSet.TYPE_SCROLL_SENSITIVE.
 * Also demonstrates accessing MetaData. 
 * */
package com.fdmgroup.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JDBC_Demo2 
{

	// For loading properties from file
	Properties properties;
	String url, username, password;
	
	// For connecting to the database
	Connection conn;

	
	public JDBC_Demo2(){
	
		properties = PropertiesLoader.getProperties("real.properties");
	
		if (properties != null) {
			
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
		}
	}
	
	
	public void printAllRecordsInTable(){	
		
		try {
			
			// First, make sure the driver jar is in your build path 
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	        
			// Get a connection object -- this could fail!
			conn = DriverManager.getConnection(url,username,password);
		    
			// Get a statement object
	        Statement statement = conn.createStatement(
	        		ResultSet.TYPE_SCROLL_SENSITIVE,
	        	    ResultSet.CONCUR_UPDATABLE);
	       
	        String query = "SELECT test_ID,FIRST_NAME,LAST_NAME,AGE FROM test1";
	        
	        // Get a ResultSet from executing SELECT query
	        ResultSet rs = statement.executeQuery(query);
	        
	        //Accessing MetaData
	        DatabaseMetaData dbmd = conn.getMetaData();
	        System.out.println("ResultSet type is: "+rs.getType());	      
	        System.out.println("Others updates are visible?: "+conn.getMetaData().othersUpdatesAreVisible(ResultSet.TYPE_SCROLL_SENSITIVE));
	        System.out.println("Driver version: "+dbmd.getDriverVersion());	        
	        System.out.println("DB URL: "+dbmd.getURL());
	        
	          
	        
	        // Must call rs.next() to get first row! 
	        // While loop gets this done
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	       
	        System.out.println("\nSelect 'next','previous' or 'update'");
	        
	        while(rs.isLast() == false) {
	        	String entry;

	        	try {
	                 entry = br.readLine();
	                 if (entry.equals("next"))
	                 {
	                	 rs.next();
	                	 rs.refreshRow();   //Refreshes current row with most recent changes in database. 
	                	 System.out.println(
	                			rs.getInt("test_ID")+" "+
	         	        		rs.getString("FIRST_NAME")+" "+
	         	        		rs.getString("LAST_NAME"));
	                 }
	                 else if (entry.equals("previous"))
	                 {
	                	 if (!rs.isFirst()) //can't see the previous row, if we're already at the first row
	                		 rs.previous();
	                	 rs.refreshRow();
	                	 System.out.println(
	                			rs.getInt("test_ID")+" "+
	         	        		rs.getString("FIRST_NAME")+" "+
	         	        		rs.getString("LAST_NAME"));
	                 }  
	                 else if (entry.equals("update"))
	                 {
	                	 //read new data from user
	                	 System.out.println("First Name: ");
	                	 String fname = br.readLine();
	                	 System.out.println("Last Name: ");
	                	 String lname = br.readLine();
	                	 System.out.println("ID: ");
	                	 String id = br.readLine();
	                	 
	                	 //create update query
	                	 String query2 = "UPDATE test1 " +
	                	 				 "SET first_name = '"+fname+"',"+
	                			             "last_name = '"+lname+"'"+
	                	 		         " WHERE test_ID='"+id+"'";
	                	 System.out.println(query2);
	               
	           
	                	 //Use a different Statement object to do 
	                	 //the update.	                	 
	                	 Statement statement2 = conn.createStatement(
	         	        		ResultSet.TYPE_SCROLL_SENSITIVE,
	         	        	    ResultSet.CONCUR_UPDATABLE);
	                	 statement2.executeUpdate(query2);	                	
	                	
	                 }
	              } catch (IOException ioe) {
	                 System.out.println("IO error trying to read your entry");
	                 System.exit(1);
	              }
	            
	        }
	        
	        
	        
		} catch (SQLException sqle) {
			sqle.printStackTrace(); 
		}
		finally {
		    
			// Close the connection
		    if(conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		new JDBC_Demo2().printAllRecordsInTable();

	}
}
