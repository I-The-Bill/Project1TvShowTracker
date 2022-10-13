package TvTracker;

import java.util.Scanner;
import java.util.InputMismatchException;


public class TvTrackerDriver
{
	public static void main(String[] args)
	{
		/*	Setup
		 * 
		 */
		Scanner input = new Scanner(System.in);
		System.out.println("\n\n\n\n\n\n___________________________" 
								  +  "\n|                         |"
							      +  "\n| TV SHOW STATUS TRACKER  |"
								  +  "\n|_________________________|"
							      +  "\n\n\n\nPlease enter your user name");
		String username = "";
		boolean entryStatus = false;
		//TvTrackerDAO tvSql = new TvTrackerDAO();

		/* making sure a username is entered 
		 * 
		*/
		while (username.equalsIgnoreCase(""))
		{
			try 
			{
				username = input.nextLine();
			} 	
			catch (Exception e) 
			{
				System.out.println("__Please enter a username__\n");
			}
		}
		/*login process. 
		 * Should check username again database to see if user name exist and if it does move to attempting login with password
		*/
		try 
		{
			//if (tvSql.usernameExist(username)==true)
			{
				System.out.println("\n\nWelcome " + username);
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
		
		Show x = new Show();
		System.out.println(x.toString());
		

	}
}

