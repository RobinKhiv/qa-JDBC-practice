package com.qa.main;

import java.sql.SQLException;

import com.qa.main.db.DBconnection;

public class Runner {
	public static void main(String[] args) throws SQLException {
		DBconnection db = new DBconnection();
		try {
			db.read();
		} finally {
			db.shutdown();
		}
	}
}
