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
		String username = "Bill";
		boolean entryStatus = false;
		//TvTrackerDAO tvSql = new TvTrackerDAO();

		/* Making sure a username is entered 
		 * 
		*/
		while (username.equalsIgnoreCase(""))
		{
			try 
			{
				// ____________username = input.nextLine();___________ Needs to be undone
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
				System.out.println("\n\nWelcome " + username +"\n");
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
		boolean active = true;
		while (active == true)
		{
			//Show x = new Show();
			System.out.println("What would you like to do?\n"
							+"\n1. Check the status of a single show"
							+"\n2. check the status of all my shows"
							+"\n3. Update the status of a show"
							+"\nPress 9 to Quit");


			int toDo = input.nextInt();
			try 
			{
				if (toDo == 1)
				{
					System.out.println("\n1");
				}
				else if (toDo == 2)
				{
					System.out.println("\n2");
				}
				else if (toDo == 3)
				{
					System.out.println("\n3");
				}
				else if (toDo == 9)
				{
					System.out.println("\n\nThank you for using the TV Show Status Tracker.\nSee you next time");
					active = false;
				}
				else
				{
					System.out.println("\nPlease enter a vaild option\n");
				}
			} 
			catch (InputMismatchException e) 
			{
				System.out.println("\nPlease enter a vaild option\n");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		

		

	}
}

