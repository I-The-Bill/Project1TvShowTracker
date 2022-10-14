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
			String username = "";
			String password = "";
			boolean entryStatus = false;
			TvDAO tvSql = new TvDAO();
	
			/* Making sure a username is entered 
			 * 
			*/
			while (username.equalsIgnoreCase(""))
			{
				try 
				{
					System.out.println("Please enter a username");
					 username = "Bill";//input.nextLine(); //Bill
					 System.out.println("Please enter a password");
					 password = "1325";//input.nextLine(); //1325
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
	
	
				String toDo = input.nextLine();
				int toDo2 = Integer.parseInt(toDo);
				//input.next();
				try 
				{
					switch(toDo2) {
						case 1:
							System.out.println("Please enter the title of the show you want to check");
							
							String theShow = input.nextLine();
							//System.out.println(tvSql.getUserId(username));
							System.out.println("The show "+ theShow + "\'s Status is: " + tvSql.getStatus(theShow, username));
							break;
						case 2:
							System.out.println("\n2");
							break;
						
						case 3:
							System.out.println("\n3");
							break;
						
						case 9:
							System.out.println("\n\nThank you for using the TV Show Status Tracker.\nSee you next time");
							active = false;
							break;
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

