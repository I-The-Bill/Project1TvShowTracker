package TvTracker;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class TvTrackerDriver {
	public static void main(String[] args)
	{
		//SetUp

		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		System.out.println("\n\n__________________________________   \n"
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
			    + "\n\n                                \n");
		String username = "John";
		String password = "12345";
		boolean entryStatus = false;
		TvDAO tvSql = new TvDAO();
		Show show = new Show();

		/* Making sure a username is entered 
		 * 
		*/
		while (username.equalsIgnoreCase(""))
		{
			try 
			{
				System.out.println("Please enter your user name:");
				username = input.nextLine();
				System.out.println("Please enter your password:");
				password = input1.nextLine();
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
			if (tvSql.login(username, password)==true) {
				entryStatus = true;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
		boolean active = true;
		while (active == true)
		{
			Show x = new Show();
			System.out.println("\nWhat would you like to do?\n"
							+"\n1. Check the status of a single show"
							+"\n2. check the status of all my shows"
							+"\n3. Update the status of a show"
							+"\nPress 9 to Quit");


			int toDo = input1.nextInt();
			String showName = "";
			int status = 0;
			try 
			{

				switch(toDo) {
					case 1:
						System.out.println("Please enter the title of the show you want to check");
						showName = input.nextLine();
						System.out.println("The show "+ showName + "\'s Status is: " + tvSql.getStatus(showName, username));
						break;
					
					case 2:
						for(Show shows : tvSql.getAllStatus()) {
							System.out.println(shows);
						}
						active=false;
						break;
						
					case 3:
						System.out.println("What show did you want to update?");
						showName = input.nextLine();
						System.out.println("What status would you like to set for " + showName 
								+ "\n1. Complete\n2. In Progress\n3. Complete");
						status = input1.nextInt();
						tvSql.setStatus(status,showName,username);
						System.out.println(showName + "\'s status has successfully been updated "
								+ "to " + tvSql.getStatus(showName, username));
						break;
					

					case 9:
						System.out.println("\nThank you for using the TV Show Status Tracker.\nSee you next time");
						active = false;
						break;
					default:
						System.out.println("\nPlease enter a vaild option\n");
						break;
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




