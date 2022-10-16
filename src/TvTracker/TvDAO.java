package TvTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TvDAO implements TvTrackerDaoInterface {

	private Connection connection = TvJDBC.getConnection();

	//done
	@Override
	public void register(String username, String password) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO  TV_user(user_id, user_name, user_password) VALUE(NULL, ?, ?)");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Can not register request. Sorry :(");
		}
	}
	
	
	//done
	@Override
	public boolean login(String username, String inPassword) {
		try {
			
			PreparedStatement pstmt1 = connection.prepareStatement("Select user_name from tv_user where user_name = ? ");
			PreparedStatement pstmt2 = connection.prepareStatement("Select user_password from tv_user where user_password = ?");
			pstmt1.setString(1, username);
			pstmt2.setString(1, inPassword);
			//pstmt1.setString
			ResultSet rs1 = pstmt1.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();
			boolean exists1 = rs1.next();
			boolean exists2 = rs2.next();

			if (exists1==true && exists2==true) {
				System.out.printf("\n\n\nWelcome %s\n", username);
				return true;
			}
			else if (exists1==true) {
				throw new BadLoginCredentialsException();
			} 
			else if (exists2==true) {
				//System.out.println("Cannot find this username");
				throw new BadLoginCredentialsException();
			}
			
		} catch(BadLoginCredentialsException e) {
			e.getMessage();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println("Username/password are incorrect");
		return false;
	}
	
	public int getShowId(String showTitle) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("Select show_id from TV_show where show_name = ?");
			pstmt.setString(1, showTitle);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			int id = rs.getInt("show_id");
			return id;
			}
		} catch (SQLException e) {
			System.out.println("Show " + showTitle + " not found.");
		}
		return -1;
	}
	
	public int getUserId(String username) {
		
		try {
			PreparedStatement pstmt = connection.prepareStatement("Select user_id from TV_user where user_name = ?");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
			int id = rs.getInt("user_id");
			
			return id;
			}
		} catch (SQLException e) {
			System.out.println("User: = " + username + " not found.");
		}
		return -1;
	}

	@Override
	public void setStatus(int x, String showTitle, String username) {
		
		try {

			PreparedStatement pstmt= connection.prepareStatement("update watch_instance set watch_instance.status_id = ? "
					+ "where watch_instance.show_id = ? && watch_instance.user_id = ?");
			pstmt.setInt(1, x);
			pstmt.setInt(2, getShowId(showTitle));
			pstmt.setInt(3, getUserId(username));
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			System.out.println("Can not complete request. Soory:)");

		}
				
	}
@Override
	public String getStatus(String showTitle, String Username) {
		try 
		{
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM TV_status "
					+ "INNER JOIN Watch_instance ON TV_status.status_id = Watch_instance.status_id "
					+ "INNER JOIN TV_show ON Watch_instance.show_id = TV_show.show_id "
					+ "inner join TV_user on TV_user.user_id = Watch_instance.user_id  "
					+ "WHERE show_name= ? && Watch_instance.user_id = ?");

			pstmt.setString(1, showTitle);
			pstmt.setInt(2, getUserId(Username));
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String status = rs.getString("status_name");
				if(!(status.equalsIgnoreCase("null")))
				{	
					return status;
				}	
				else
				{
					throw new ShowNotWatchedException();
				}
			}
		} 
		catch (ShowNotWatchedException e) 
		{
			System.out.println(e.getMessage());
		}
		catch (SQLException e) 
		{
			System.out.println("Show with title = " + showTitle + " not found.");
		} 
		return null;
	}

	@Override
	public Show getShow(String showTitle) {
		
//		try {
//			PreparedStatement pstmt = connection.prepareStatement("Select * from tv_show "
//					+ "inner join watch_instance on tv_show.show_id = watch_instance.show_id"
//					+ "inner join tv_user on tv_user.user_id = watch_instance.user_id");
//			pstmt.setString(1, showTitle);
//			
//			ResultSet rs = pstmt.executeQuery();
//			rs.first();
//			int id = rs.getInt("show_id");
//			String name = rs.getString("show_name");
//			int episodeCount = rs.getInt("episode_count");
//		//	Show show = new Show(id, name, episodeCount);
//			return Show;
//		} catch (SQLException e) {
//			System.out.println("Show with title = " + showTitle + " not found.");
//		}
		return null;
	}
	
	//done
	@Override
	public void getAllStatus(String username) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("Select * from tv_show "
					+ "inner join watch_instance on tv_show.show_id = watch_instance.show_id "
					+ "inner join tv_status on watch_instance.status_id = tv_status.status_id "
					+ "inner join tv_user on tv_user.user_id = watch_instance.user_id "
					+ "where tv_user.user_name = ?");
			
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery(); 
			while(rs.next()) {
				int id = rs.getInt("show_id");
				String name = rs.getString("show_name");
				int episodeCount = rs.getInt("episode_count");
				String status = rs.getString("Status_name");
				

				Show show = new Show(id, name, episodeCount);
				System.out.printf("Show:\t%-16s\tEpisode Count:\t%-4d\tStatus:\t%-7s\n",show.getName(),show.getEc(),status);
			}
						
		} catch (SQLException e) {
			System.out.println("Could not retrieve list of shows from database");
		}
	}

}