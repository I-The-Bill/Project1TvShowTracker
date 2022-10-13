package TvTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import TvTracker.Show;

public class TvDAO implements TvTrackerDaoInterface{
	
	private Connection connection = TvJDBC.getConnection();

	@Override
	public boolean usernameExist(String username) {
		try {
			
			PreparedStatement pstmt = connection.prepareStatement("Select * from tv_user while user_id = ?");
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			boolean exists = rs.next();
			 
			if (exists==true) {
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Cannot find this username");
		return false;
	}

	@Override
	public boolean login(String username, String password) {
try {
			PreparedStatement pstmt1 = connection.prepareStatement("Select * from tv_user while user_name = ?");
			PreparedStatement pstmt2 = connection.prepareStatement("Select * from tv_user while user_password = ?");
			pstmt1.setString(1, username);
			pstmt2.setString(1, password);
			
			ResultSet rs1 = pstmt1.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();
			
			String name = rs1.getString("user_name");
			boolean exists1 = rs1.next();
			boolean exists2 = rs2.next();
			 
			if (exists1==true && exists2==true) {
				System.out.printf("Welcome %s/n", name);
				return true;
			}
			else if (exists1==true) {
				System.out.println("Password entered is wrong");
			} 
			else if (exists2==true) {
				System.out.println("Cannot find this username");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Username/password are incorrect");
		return false;
	}

	@Override
	public Show getShow(String showTitle) {
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("Select * from tv_show while show_name = ?");
			pstmt.setString(1, showTitle);
			
			ResultSet rs = pstmt.executeQuery();
			rs.first();
			int id = rs.getInt("show_id");
			String name = rs.getString("show_name");
			int episodeCount = rs.getInt("episodeCount");
			Show show = new Show(id, name, episodeCount);
			return show;
		} catch (SQLException e) {
			System.out.println("Show with title = " + showTitle + " not found.");
		}
		return null;
	}

	@Override
	public void setStatus(int x) {
		
		try {
			String status = "";
			switch(x) {
				case 1:
					status = "not complete";
				case 2:
					status = "in-process";
				case 3:
					status = "complete";
				default: 
					System.out.println("Please select an option between 1 and 3.");
					break;
			}
			PreparedStatement pstmt = connection.prepareStatement("update tv_status set status_name = ? "
					+ "where status_id = ?");
			pstmt.setString(1, status);
			pstmt.setInt(2, x);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}

	@Override
	public String getStatus(String showTitle) {
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("Select * from tv_status while status_name = ?");
			pstmt.setString(1, showTitle);
			
			ResultSet rs = pstmt.executeQuery();
			rs.first();
			String status = rs.getString("status_name");
			return status;
		} catch (SQLException e) {
			System.out.println("Show with title = " + showTitle + " not found.");
		}
		return null;
	}

	@Override
	public List<Show> getAllStatus() {
		
		try {
			Statement stmt;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from tv_show");
			List<Show> showList = new ArrayList<Show>();
			
			while(rs.next()) {
				int id = rs.getInt("show_id");
				String name = rs.getString("show_name");
				int episodeCount = rs.getInt("episodeCount");
				
				Show show = new Show(id, name, episodeCount);
				showList.add(show);
			}
			
			return showList;
			
		} catch (SQLException e) {
			System.out.println("Could not retrieve list of shows from database");
		}
		
		return null;
	}
	
	
}