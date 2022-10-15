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
			System.out.println("Connection Established.\n");
		} catch(SQLException e) {
			System.out.println("Could not make connection. Please check the following:\n"
							 + "-- config.properties file has the right credentials.\n"
							 + "-- config.properties file is in the resource folder\n"
							 + "-- Resource folder is in the top directory of the projec\n"
							 + "-- MySQL server is running\n"
							 + "-- Everything is updated\n\n\n"
							 + "NOW EXITING PROGRAM");
			System.exit(0);
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