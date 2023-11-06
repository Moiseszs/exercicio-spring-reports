package com.spring.exercicioreport.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	public static Connection connect() throws ClassNotFoundException, SQLException{
		String url = "jdbc:jtds:sqlserver://localhost:1433/BdExercicioRelatorios";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url, "dbuser", "pass");
		return connection;
	}
}
