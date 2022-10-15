package TvTracker;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;

public class TvJDBC {

	private static Connection connection = null;
	
	// method for creating connection
	public static void makeConnection() {
		
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream("resource/config.properties"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Attempting to establish a connection to server\n");
		String URL = props.getProperty("url");
		String USERNAME = props.getProperty("username");
		String PASSWORD = props.getProperty("password");
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connected.\n");
		} catch(SQLException e) {
			System.out.println("Could not make connection.");
		}
		
	}
	
	public static Connection getConnection() {
		if(connection == null) {
			makeConnection();
		}
		return connection;
	}
	
	// main is only here so we can test our method above
	public static void main(String[] args) {
		Connection con = TvJDBC.getConnection();
		try {
			con.close();
			System.out.println("Connection closed.");
		} catch(SQLException e) {
			System.out.println("Could not make connection.");
		}
	}
}