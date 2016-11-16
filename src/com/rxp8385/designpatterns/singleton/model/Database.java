package com.rxp8385.designpatterns.singleton.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	
	
	private static Database instance = new Database();
	
	private Connection con;
	
	private Database() {
		
	}
	
	public static Database getInstance () {
		return instance;
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public void connect () throws Exception {
		if (con != null){
			return;
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		
		String url = String.format("jdbc:mysql://localhost:%d/patterns", 3306);
		
		con = DriverManager.getConnection(url, "squiffy", "letmein");
	}
	
	public void disconnect () {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e){
				System.out.println("Can't close connection");
			}
		}
		
		con = null;
	}
}
