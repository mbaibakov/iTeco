package ru.iteco.javacources.task2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	
	public static void init(){
		System.out.println("Init DB");
		Connection conn = DbConnection.connection;
		DatabaseMetaData dbm =null;
		ResultSet rs = null;
		Statement stmt = null;
		try{
			dbm = conn.getMetaData();
			rs = dbm.getTables(null, null, "employee", null);
			if (rs.next()) {
				System.out.println("Table employee exists"); 
			} else {
				System.out.println("Create table employee");
				String createSql = "CREATE TABLE Employee ( EMPNO int NOT NULL, ENAME varchar (50) NOT NULL, JOB_TITLE varchar (150) NOT NULL )";
				String insetSql = "INSERT INTO Employee values( 1 , 'Mikhail Baibakov', 'Java Programmer')";  
				stmt = conn.createStatement();
				stmt.executeUpdate(createSql);
				stmt.executeUpdate(insetSql);
			}
			
			rs = dbm.getTables(null, null, "property", null);
			if (rs.next()) {
				System.out.println("Table property exists"); 
			} else {
				System.out.println("Create table property");
				String createSql = "CREATE TABLE Property ( SERIALNO varchar (25) NOT NULL, TITLE varchar (250) NOT NULL, EMPNO int NOT NULL )";
				String insetSql = "INSERT INTO Property values( '12345' , 'iPad2 Wi-Fi 16g', 1)";
				stmt = conn.createStatement();
				stmt.executeUpdate(createSql);
				stmt.executeUpdate(insetSql);
			}
		} catch (SQLException se){
			System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
		}
	}

	public List<Employee> SelectStatemet(){
		
		Connection conn=DbConnection.connection;
		
		Statement stmt=null;
		ResultSet rs=null;
		String sqlQuery = "SELECT * from Employee";
		ArrayList<Employee> employees = null;
		
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery);
			employees = new ArrayList<Employee>();
			while(rs.next()){
				Employee employee = new Employee();
				employee.setNumber(rs.getInt("EMPNO"));
				employee.setName(rs.getString("ENAME"));
				employee.setJobTitle(rs.getString("JOB_TITLE"));
				employees.add(employee);
			}
		} catch (SQLException se) {
			System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try{
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return employees;
		
	}
	
	public void InsertTransaction(Employee employee){
		Connection conn=DbConnection.connection;
		Statement stmt=null;
		try{
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String sql = "INSERT into Employee values(" + 
					employee.getNumber() + ", '" + employee.getName() + "', '" + employee.getJobTitle() + "')";
			String sql2 = "INSERT INTO Property values( '" + employee.hashCode() + "' , 'iPad2 Wi-Fi 16g', " + employee.getNumber() + ")";
			System.out.println(sql);
			System.out.println(sql2);
			stmt.addBatch(sql);
			stmt.addBatch(sql2);
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException se) {
			System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public List<Employee> SelectPreparedStatemet(String jobTitle){
		
		Connection conn=DbConnection.connection;		
		String sqlQuery = "SELECT * from Employee WHERE job_title = ?";
		PreparedStatement stmt = null;
		ResultSet rs=null;
		ArrayList<Employee> employees = null;
		
		try{
			stmt = conn.prepareStatement(sqlQuery);
			stmt.setString(1, jobTitle);
			rs = stmt.executeQuery();
			employees = new ArrayList<Employee>();
			while(rs.next()){
				Employee employee = new Employee();
				employee.setNumber(rs.getInt("EMPNO"));
				employee.setName(rs.getString("ENAME"));
				employee.setJobTitle(rs.getString("JOB_TITLE"));
				employees.add(employee);
			}
		} catch (SQLException se) {
			System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try{
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return employees;
		
	}
	
}
