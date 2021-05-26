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
}
