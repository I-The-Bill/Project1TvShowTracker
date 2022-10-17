package TvTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class TvDAO implements TvTrackerDaoInterface {

	private Connection connection = TvJDBC.getConnection();

	@Override
	public void Register(String username, String password) {
		try {
			PreparedStatement pstmt = connection
					.prepareStatement("Insert Tv_user(user_id, user_name, user_password)" + "value(NULL, ?, ?)");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			System.out.printf("\nWelcome %s\n", username);

		} catch (Exception e) {
			System.out.println("Something went wrong, cannot create new account");
		}
	}

	// done
	@Override
	public boolean login(String username, String inPassword) {
		try {

			PreparedStatement pstmt1 = connection
					.prepareStatement("Select user_name, user_password from TV_user where user_name = ? && user_password = ?");
			pstmt1.setString(1, username);
			pstmt1.setString(2, inPassword);
		
			ResultSet rs1 = pstmt1.executeQuery();
			
			boolean exists1 = rs1.next();
			
			if (exists1 == true)
			{
				System.out.printf("\n\n\nWelcome %s\n", username);
				return true;
			}
			else
			{
				throw new BadLoginCredentialsException();
			}			
		} 
		catch (BadLoginCredentialsException e) 
		{
			e.getMessage();
		}	
		catch (SQLSyntaxErrorException e) 
		{
			e.getMessage();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		// System.out.println("Username/password are incorrect");
		return false;
	}

	private int getShowId(String showTitle) {
		try {
			PreparedStatement pstmt = connection.prepareStatement("Select show_id from TV_show where show_name = ?");
			pstmt.setString(1, showTitle);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("show_id");
				return id;
			}
		} catch (SQLException e) {
			System.out.println("Show " + showTitle + " not found.");
		}
		return -1;
	}

	private int getUserId(String username) {

		try {
			PreparedStatement pstmt = connection.prepareStatement("Select user_id from TV_user where user_name = ?");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
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

			PreparedStatement pstmt = connection
					.prepareStatement("update watch_instance set watch_instance.status_id = ? "
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
		try {
			PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM TV_status "
					+ "INNER JOIN Watch_instance ON TV_status.status_id = Watch_instance.status_id "
					+ "INNER JOIN TV_show ON Watch_instance.show_id = TV_show.show_id "
					+ "inner join TV_user on TV_user.user_id = Watch_instance.user_id  "
					+ "WHERE show_name= ? && Watch_instance.user_id = ?");

			pstmt.setString(1, showTitle);
			pstmt.setInt(2, getUserId(Username));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String status = rs.getString("status_name");
				if (!(status.equalsIgnoreCase("null"))) {
					return status;
				} else {
					throw new ShowNotWatchedException();
				}
			}
		} catch (ShowNotWatchedException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("Show with title = " + showTitle + " not found.");
		}
		return null;
	}

	@Override
	public void getShow(String showTitle) {

		try {
			PreparedStatement pstmt = connection.prepareStatement("select * from TV_show where show_name = ?;");
			pstmt.setString(1, showTitle);

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) 
			{
				int id = rs.getInt("show_id");
				String name = rs.getString("show_name");
				int episodeCount = rs.getInt("episode_count");
				Show show = new Show(id, name, episodeCount);
				System.out.println(show);
				return;
			}
			throw new SQLException();
			
			
		} catch (SQLException e) {
			System.out.println("Show with title \"" + showTitle + "\" not found.");
		}
		catch (Exception e) {
			System.out.println("Show with title \"" + showTitle + "\" not found.");
		}

	}

	// done
	@Override
	public void getAllStatus(String username) {
		try {
			PreparedStatement pstmt = connection.prepareStatement(
					"Select * from tv_show " + "inner join watch_instance on tv_show.show_id = watch_instance.show_id "
							+ "inner join tv_status on watch_instance.status_id = tv_status.status_id "
							+ "inner join tv_user on tv_user.user_id = watch_instance.user_id "
							+ "where tv_user.user_name = ?");

			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("show_id");
				String name = rs.getString("show_name");
				int episodeCount = rs.getInt("episode_count");
				String status = rs.getString("Status_name");

				Show show = new Show(id, name, episodeCount);
				System.out.printf("Show:\t%-16s\tEpisode Count:\t%-4d\tStatus:\t%-7s\n", show.getName(), show.getEc(),
						status);
			}

		} catch (SQLException e) {
			System.out.println("Could not retrieve list of shows from database");
		}
	}

	@Override
	public void setShow(int x, String showTitle, String username) {
		// use showTitle to return the details of the Show.
		try {

			PreparedStatement pstmt = connection.prepareStatement(
					"INSERT INTO Watch_instance(instance_id, user_id, show_id, status_id) VALUES (NULL, ?, ?, ?)");
			pstmt.setInt(1, getUserId(username));
			pstmt.setInt(2, getShowId(showTitle));
			pstmt.setInt(3, x);
			System.out.println("1");
			pstmt.executeUpdate();
			System.out.println("2");
			System.out.println("The show" + showTitle + " has been added to the list.");
			
		} 
		catch (SQLException e) {
			System.out.println("Can not add the show to the list. Sorry:)");

		}
		catch (Exception e) {
			System.out.println("Can not add the show to the list. Sorry:)");

		}
	}
	@Override
	public void getAllshow() {

		try {
			PreparedStatement pstmt = connection.prepareStatement("select * from TV_show");

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("show_id");
				String name = rs.getString("show_name");
				int episodeCount = rs.getInt("episode_count");
				Show show = new Show(id, name, episodeCount);
				System.out.printf("Show:\t%-25s\tEpisode Count:\t%-4d\tShow ID:%4d\n", show.getName(), show.getEc(),show.getId());

			}
		} catch (SQLException e) {
			System.out.println("service not availble at this time.");
		}

	}

}