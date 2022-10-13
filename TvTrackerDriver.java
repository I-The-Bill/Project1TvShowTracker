import java.util.Scanner;
import java.util.InputMismatchException;


public class TvTrackerDriver
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("\n\n\n\n\n\n___________________________" 
								  +  "\n|                         |"
							      +  "\n| TV SHOW STATUS TRACKER  |"
								  +  "\n|_________________________|"
							      +  "\n\n\n\nPlease enter your user name");
		String username = "";
		boolean entryStatus = false;
		//TvTrackerDAO tvSql = new TvTrackerDAO();

		/*making sure a username is entered */
		while (username.equalsIgnoreCase(""))
		{
			try 
			{
				username = input.nextLine();
			} 	catch (Exception e) {
				System.out.println("Please enter a username");
			}
		}
		//login process. Checks if username exist then proceeds to get password for login
		try 
		{
			//if (tvSql.usernameExist(username)==true)
			{
				System.out.println("Welcome");
				//if (password matchs Users row)
				{
					entryStatus = true;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		 
		

	}
}

