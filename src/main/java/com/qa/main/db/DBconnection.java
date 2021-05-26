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

		System.out.println("Current Data: ");
		
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
	public void readById(int id) throws SQLException {
		String sql = "SELECT * FROM customer WHERE id = ?";
		ps= con.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		
		if (!rs.next())
			System.out.println("ID#" + id + " NOT FOUND");
		else 
			System.out.println(String.format("ID: %d, Name: %s", 
					rs.getInt("id"), rs.getString("name")));
	}
	//Delete a specific Customer
	public void delete(int id) throws SQLException {
		String sql = "DELETE FROM customer WHERE id = ?";
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.execute();
		System.out.println("ID " + id + " was DELETED");
	}
	//Update a specific Customer
	public void update(int id, String name) throws SQLException {
		String sql = "UPDATE customer Set name = ? WHERE id = ?";
		ps= con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, id);
		ps.execute();
		read();
	}
	public void shutdown() throws SQLException {
		con.close();
	}
}
