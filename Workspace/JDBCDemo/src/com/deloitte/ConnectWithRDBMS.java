package com.deloitte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectWithRDBMS {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Step 1 : Loading and Registering the Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Class.forName("oracle.jdbc.driver.OracleDriver");

		String username = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost:3306/syskan1";
		//String oracleUrl ="jdbc:oracle:thin:@localhost:1521:xe";
		// Step 2 : Establishing the connection
		Connection conn = DriverManager.getConnection(url, username, password);

		// Step 3: Creating & Executing the Query
		String query = "select * from employee";
		Statement stmt = conn.createStatement();

		// Step 4 : Storing & Processing the Result
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString("email") + " "
					+ rs.getLong("mobile") + " " + rs.getDate("dob"));
		}
		
		//Step 5: Closing Resources
		if (rs!=null)
			rs.close();
		if (stmt!=null)
			stmt.close();
		if (conn!=null)
			conn.close();
		
		
	}

}
