package TvTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStatus(String showTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Show> getAllStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}