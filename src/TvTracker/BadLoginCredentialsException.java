package TvTracker;

public class BadLoginCredentialsException extends Exception
{
	private static final long serialVersionUID = 001;

	public BadLoginCredentialsException()
	{
		super("The Username or password you have entered is incorrect");
	}
}
