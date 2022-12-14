package TvTracker;


public interface TvTrackerDaoInterface  
{	
		
	public boolean login(String username, String password); //checks if username is in table then checks if the input password matchs the users password. Returns true if both are found else false
	public void getShow(String showTitle); //use showTitle to return the details of the Show.
	public void setStatus(int x, String showTitle, String username); //use x to serve as the status 1. complete 2. in-progress 3. not complete
	public String getStatus(String showTitle, String username); //user enters title, program returns show's status
	public void getAllStatus(String username); //Query all shows with all status given a user
	public void Register(String username, String password); //create new entry for username and password
	public void setShow(int x, String showTitle, String username); //use showTitle to return the details of the Show.
	public void getAllshow();
}
