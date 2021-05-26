package com.qa.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qa.main.utils.DBconfig;

public class DBconnection {
	private PreparedStatement ps;
	private Connection con;
	private ResultSet rs;
	
	public DBconnection() throws SQLException {
		con = DriverManager.getConnection(DBconfig.url, DBconfig.user, DBconfig.pw);
	}
	
	//Read All Customers in the `Customers` table
	public void read() throws SQLException {
		String sql = "SELECT * FROM customer";
		ps= con.prepareStatement(sql);
		rs= ps.executeQuery();
		if (!rs.next())
			System.out.println("No data found");
		else 
			do {
				System.out.println(String.format("ID: %d, Name: %s", 
						rs.getInt("id"), rs.getString("name")));
			} while(rs.next());
	}
	//Create a new Customer
	public void create(String name) throws SQLException {
		String sql = "INSERT INTO customer (name) VALUES (?)";
		ps= con.prepareStatement(sql);
		ps.setString(1,name);
		ps.execute();
		read();
	}
	//Read a specific Customer
	//Delete a specific Customer
	//Update a specific Customer
	public void shutdown() throws SQLException {
		con.close();
	}
}
