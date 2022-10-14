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
		System.out.println("\n\n\n\n__________________________________   \n"
								  + "         _           _              \n"
								  + "        / \\**       / \\**         \n"
								  + "       /   \\**     /   \\**        \n"
								  + "      / /*\\ \\**   / /*\\ \\**     \n"
								  + "     / /** \\ \\** / /** \\ \\**    \n"
								  + "    / /**   \\ \\_/ /**   \\ \\**   \n"
								  + "   / /**     \\___/**     \\ \\**   \n"
								  + "  / /**        ****       \\ \\**   \n"
								  + " | |**                    |  |**    \n"
								  + " | |*_____________________|  |**    \n"
								  + " |                           |**    \n"
								  + " |  TV SHOW STATUS TRACKER   |**    \n"
								  + " |  _______________________  |**    \n"
								  + " | |**                     | |**    \n"
								  + " | |**                     | |**    \n"
								  + "  \\ \\*        ___         / /**   \n"
								  + "   \\ \\*      / _ \\       / /**   \n"
								  + "    \\ \\*    / /*\\ \\     / /**   \n"
								  + "     \\ \\*  / /** \\ \\   / /**    \n"
								  + "      \\ \\*/ /**   \\ \\_/ /**     \n"
								  + "       \\   /**     \\   /**        \n"
								  + "        \\_/**       \\_/**         \n" 
								  + "          **          **            \n"
								  + "_________________________________   \n"
							      + "\n\n\nPlease enter your user name   \n");
		String username = "Bill";
		String password = "1325";
		boolean entryStatus = false;
		TvTrackerDaoInterface tvSql = new TvDAO();

		/* Making sure a username is entered 
		 * 
		*/
		while (username.equalsIgnoreCase(""))
		{
			try 
			{
//				 ____________
				 username = input.nextLine();//___________ Needs to be undone
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
//			if (tvSql.usernameExist(username)==true)
//			{
//				System.out.println("\n\nWelcome " + username +"\n");
				if (tvSql.login(username, password)==true)
				{
					entryStatus = true;
				}
//			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		boolean active = true;
		while (active == true)
		{
			Show x = new Show();
			System.out.println("What would you like to do?\n"
							+"\n1. Check the status of a single show"
							+"\n2. check the status of all my shows"
							+"\n3. Update the status of a show"
							+"\nPress 9 to Quit");


			int toDo = input.nextInt();
			try 
			{
				switch(toDo) {
					case 1:
						System.out.println("\n1");
					
					case 2:
						System.out.println("\n2");
					
					case 3:
						System.out.println("\n3");
					
					case 9:
						System.out.println("\n\nThank you for using the TV Show Status Tracker.\nSee you next time");
						active = false;
					
					default:
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

