package TvTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public boolean usernameExist(String username) {
		try {

			PreparedStatement pstmt = connection.prepareStatement("Select * from tv_user where user_id = ?");
			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();
			boolean exists = rs.next();

			if (exists == true) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Cannot find this username");
		return false;
	}
	//done
	@Override
	public boolean login(String username, String inPassword) {
		try {
			PreparedStatement pstmt1 = connection.prepareStatement("Select user_name from tv_user where user_name = ?");
			PreparedStatement pstmt2 = connection
					.prepareStatement("Select user_password from tv_user where user_password = ?");
			pstmt1.setString(1, username);
			pstmt2.setString(1, inPassword);
			// pstmt1.setString
			ResultSet rs1 = pstmt1.executeQuery();
			ResultSet rs2 = pstmt2.executeQuery();

			// String name = rs1.getString("user_name");
			// String password = rs1.getString("user_password");
			boolean exists1 = rs1.next();
			boolean exists2 = rs2.next();

			if (exists1 == true && exists2 == true) {
				System.out.printf("Welcome %s\n", username);
				return true;
			} else if (exists1 == true) {
				System.out.println("Password entered is wrong");
				return false;
			} else if (exists2 == true) {
				System.out.println("Cannot find this username");
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Username/password are incorrect");
		return false;
	}
	//done
	@Override
	public Show getShow(String showTitle) {

		try {
			PreparedStatement pstmt = connection.prepareStatement("Select * from tv_show where show_name = ?");
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
	//done
	@Override
	public int getUserId(String username) {

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
	//done
	@Override
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
	//done
	@Override
	public void setStatus(String showTitle, int x, String username) {

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

	
//done
	@Override
	public String getStatus(String showTitle, String Username) {
		// System.out.println("asdfasdfasf"+showTitle);
		try {
			// PreparedStatement pstmt2 = connection.prepareStatement("SELECT * FROM
			// TV_status INNER JOIN Watch_instance ON TV_status.status_id =
			// Watch_instance.status_id INNER JOIN TV_show ON Watch_instance.show_id =
			// TV_show.show_id WHERE show_name= ?");
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
				return status;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Show with title = " + showTitle + " not found.");
		}
		return "Not watching";
	}

	@Override
	public List<Show> getAllStatus() {

		try {
			Statement stmt;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from tv_show");
			List<Show> showList = new ArrayList<Show>();

			while (rs.next()) {
				int id = rs.getInt("show_id");
				String name = rs.getString("show_name");
				int episodeCount = rs.getInt("episode_Count");

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