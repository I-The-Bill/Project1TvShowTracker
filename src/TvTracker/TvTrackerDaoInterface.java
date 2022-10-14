package TvTracker;
import java.util.List;

public interface TvTrackerDaoInterface  
{	
		
	public boolean usernameExist(String username); //Checks if username is in table, Return true if found
	public boolean login(String username, String password); //checks if username is in table then checks if the input password matchs the users password. Returns true if both are found else false
	public Show getShow(String showTitle); //use showTitle to return the details of the Show.
	public void setStatus(int x, String showTitle, String username); //use x to serve as the status 1. complete 2. in-progress 3. not complete
	public String getStatus(String showTitle, String username); //user enters title, program returns show's status
	public List <Show> getAllStatus(); //Query all shows with all status
	

}
