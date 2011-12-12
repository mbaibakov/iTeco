package ru.iteco.javacources.task2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.Driver; 

public class DbConnection {
	
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 5432;
	private final static String DBNAME = "postgres";
	private final static String LOGIN = "postgres";
	private final static String PASSWOR = "1234";
		
	public static Connection connection = initConnection();
	
	private static Connection initConnection(){		
		Connection connection = null;	 
		try {
//			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT  + "/" + DBNAME, LOGIN, PASSWOR);
// 		} catch (ClassNotFoundException e) {
//			System.out.println("Can't find Driver!");
//			e.printStackTrace();
//			return null;
		}catch (SQLException e) { 
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return null;
		} 
		return connection;
	}
}
