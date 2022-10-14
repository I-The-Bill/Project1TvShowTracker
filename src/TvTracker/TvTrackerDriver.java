package TvTracker;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class TvTrackerDriver {
	public static void main(String[] args)
	{
		/*	Setup
		 * 
		 */
		TvTrackerDaoInterface showDAO = new TvDAO();
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);

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
				 //username = input.nextLine();//___________ Needs to be undone
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
							+"\n2. check the status of all the shows"
							+"\n3. Update the status of a show"
							+"\n4. Get detailed information of a show"
							+"\nPress 9 to Quit");


			int toDo = input.nextInt();
			
			try{
				switch(toDo) {
					case 1:
						
						System.out.println("\nPlease enter the show Title: ");
						String single = input2.nextLine();
						
						if(!single.isEmpty()) {
						System.out.println(showDAO.getStatus(single));
						}
						
						break;
					case 2:
						System.out.println("Loading all the shows");
						for(Show show : showDAO.getAllStatus()) {
							System.out.println(show);
						}// while is stopping for the show print
						break;
					case 3:
						System.out.println("\n3");
						break;
					case 4:
						System.out.println("\nPlease enter the show Title: ");
						String single2 = input2.nextLine();
						if(!single2.isEmpty()) {
							System.out.println(showDAO.getShow(single2));
							}
						break;
					case 9:
						System.out.println("\n\nThank you for using the TV Show Status Tracker.\nSee you next time");
						break;
					default:
						System.out.println("\nPlease enter a vaild option\n");
						break;
				}active = false;
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

}}
