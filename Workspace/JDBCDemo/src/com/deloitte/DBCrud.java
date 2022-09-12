package com.deloitte;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBCrud {

	private String url = "jdbc:mysql://localhost:3306/syskan1";
	private String username = "root";
	private String password = "root";
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DBCrud crud = new DBCrud();
		List<Employee> employees = crud.findAll();
		System.out.println(employees);
		Employee emp = crud.findById(3);
		emp.setEmail("updated");
		emp.setName("updated");

		crud.update(3, emp);

		System.out.println(crud.findById(3));

		Employee employee = new Employee();

		employee.setId(5);
		employee.setName("Test");
		employee.setEmail("Test@gmail.com");
		employee.setMobile(9089098990l);

		crud.save(employee);

		System.out.println(crud.findAll());

		crud.delete(5);

		System.out.println(crud.findAll());

	}

	public List<Employee> findAll() throws SQLException {
		List<Employee> employees = new ArrayList<Employee>();
		DBCrud crud = new DBCrud();
		conn = crud.getConnection(url, username, password);
		String selectAllQuery = "select * from employee";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(selectAllQuery);
		while (rs.next()) {
			Employee emp = new Employee();
			emp.setId(rs.getInt("employee_id"));
			emp.setName(rs.getString(2));
			emp.setEmail(rs.getString(3));
			emp.setMobile(rs.getLong(4));
			emp.setDob(rs.getDate("dob"));
			employees.add(emp);
		}
		return employees;
	}

	public Employee findById(int id) throws SQLException {
		Employee emp = new Employee();
		DBCrud crud = new DBCrud();
		conn = crud.getConnection(url, username, password);
		String selectAllQuery = "select * from employee where employee_id=" + id;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(selectAllQuery);
		if (rs.next()) {
			emp.setId(rs.getInt("employee_id"));
			emp.setName(rs.getString(2));
			emp.setEmail(rs.getString(3));
			emp.setMobile(rs.getLong(4));
			emp.setDob(rs.getDate("dob"));
		}
		return emp;
	}

	public void save(Employee emp) throws SQLException {
		DBCrud crud = new DBCrud();
		conn = crud.getConnection(url, username, password);
		String insertQuery = "insert into employee (employee_id,employee_name,email,mobile,dob) values (?,?,?,?,?)";
		pstmt = conn.prepareStatement(insertQuery);
		pstmt.setInt(1, emp.getId());
		pstmt.setString(2, emp.getName());
		pstmt.setString(3, emp.getEmail());
		pstmt.setLong(4, emp.getMobile());
		pstmt.setDate(5, emp.getDob());
		int insertStatus = 0;
		insertStatus = pstmt.executeUpdate();

		if (insertStatus > 0)
			System.out.println("1 Record Inserted Successfully!!!");
	}

	public void update(int id, Employee emp) throws SQLException {
		DBCrud crud = new DBCrud();
		conn = crud.getConnection(url, username, password);
		String updateQuery = "update  employee set employee_name=?,email=?,mobile=?,dob=? where employee_id= ?";
		pstmt = conn.prepareStatement(updateQuery);
		pstmt.setInt(5, emp.getId());
		pstmt.setString(1, emp.getName());
		pstmt.setString(2, emp.getEmail());
		pstmt.setLong(3, emp.getMobile());
		pstmt.setDate(4, emp.getDob());
		int updateStatus = 0;
		updateStatus = pstmt.executeUpdate();

		if (updateStatus > 0)
			System.out.println("1 Record Updated Successfully!!!");
	}

	public void delete(int id) throws SQLException {
		DBCrud crud = new DBCrud();
		conn = crud.getConnection(url, username, password);
		String deleteQuery = "delete from employee where employee_id=" + id;
		stmt = conn.createStatement();
		int deleteStatus = 0;
		deleteStatus = stmt.executeUpdate(deleteQuery);

		if (deleteStatus > 0)
			System.out.println("1 Record Deleted Successfully!!!");
	}

	public Connection getConnection(String url, String username, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void closeResource() throws SQLException {
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (rs != null)
			rs.close();
		if (conn != null)
			conn.close();
	}
}
