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
		
		String URL = props.getProperty("url");
		String USERNAME = props.getProperty("username");
		String PASSWORD = props.getProperty("password");
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			//System.out.println("Connected.");
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
}
