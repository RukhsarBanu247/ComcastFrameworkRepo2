package com.comcast.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtility
{
	Connection conn=null;
	public void getDatabaseConnection(String url,String username,String password) throws SQLException
	{
		try {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
	    conn=DriverManager.getConnection(url,username,password);
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	//hard-coded database connection--available in our system
	public void getDatabaseConnection() throws SQLException
	{
		try {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
	    conn=DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm","root", "root");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	public void closeDatabaseConnection() throws Throwable
	{
		try {
			conn.close();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public ResultSet executeNonSelectQuery(String query) throws Throwable
	{
		ResultSet result=null;
		try {
		Statement state=conn.createStatement();
	    result=state.executeQuery(query);
		
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;//return entire table
	}
	
	public int executeSelectQuery(String query) throws Throwable
	{
		int result=0;
		try {
		Statement state=conn.createStatement();
	    result=state.executeUpdate(query);
		
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;// return +1 or-1 or 0
	}
}
