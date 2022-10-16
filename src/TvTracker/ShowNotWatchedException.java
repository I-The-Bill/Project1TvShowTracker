package TvTracker;

public class ShowNotWatchedException extends Exception
{
	private static final long serialVersionUID = 002;

	public ShowNotWatchedException()
	{
		super("This show is not in your list");
		//System.out.println("Error Code: "+ serialVersionUID +" - This show is not in your list");
	}
	
}
