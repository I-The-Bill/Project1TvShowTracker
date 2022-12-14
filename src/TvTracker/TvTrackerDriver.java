package TvTracker;

import java.util.Scanner;
import java.util.InputMismatchException;

public class TvTrackerDriver {
	public static void main(String[] args) {
		// SetUp
		final int MAXATTEMPTS = 3;
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		System.out.println("\n\n__________________________________   \n" + "         _           _              \n"
				+ "        / \\**       / \\**         \n" + "       /   \\**     /   \\**        \n"
				+ "      / /*\\ \\**   / /*\\ \\**     \n" + "     / /** \\ \\** / /** \\ \\**    \n"
				+ "    / /**   \\ \\_/ /**   \\ \\**   \n" + "   / /**     \\___/**     \\ \\**   \n"
				+ "  / /**        ****       \\ \\**   \n" + " | |**                    |  |**    \n"
				+ " | |*_____________________|  |**    \n" + " |                           |**    \n"
				+ " |  TV SHOW STATUS TRACKER   |**    \n" + " |  _______________________  |**    \n"
				+ " | |**                     | |**    \n" + " | |**                     | |**    \n"
				+ "  \\ \\*        ___         / /**   \n" + "   \\ \\*      / _ \\       / /**   \n"
				+ "    \\ \\*    / /*\\ \\     / /**   \n" + "     \\ \\*  / /** \\ \\   / /**    \n"
				+ "      \\ \\*/ /**   \\ \\_/ /**     \n" + "       \\   /**     \\   /**        \n"
				+ "        \\_/**       \\_/**         \n" + "          **          **            \n"
				+ "_________________________________   \n" + "\n\n                                \n");
		String username = ""; // Bill
		String password = ""; // 1325

		boolean entryStatus = false;
		TvDAO tvSql = new TvDAO();
		int logInAttenpts = 0;

		boolean active = false;
		/*
		 * Making sure a username is entered
		 */
		while (active == false)
		{	
			System.out.println("Are you logging in or a New user");
			try
			{
				System.out.println("\nWhat would you like to do?\n" 
								 + "\n1. Login"
								 + "\n2. Register" 
								 + "\n3. Quit the program");
				int choice = Integer.parseInt(input.nextLine());
				if (choice == 1)
				{
					active = true;
				}
				else if (choice == 2)
				{
					System.out.println("Please enter your new user name:");
					String inName = input.nextLine();
					System.out.println("Please enter your new password:");
					String inPass = input1.nextLine();
					tvSql.Register(inName, inPass);
					active = true;
				}
				else if (choice ==3)
				{
					System.out.println("Goodbye");
					System.exit(0);
				}
			}
			catch (InputMismatchException e)
			{
				System.out.println("\nPlease enter a vaild option\n");
			}
			catch (Exception e)
			{
				System.out.println("\nPlease enter a vaild option\n");
			}	
		}
		
		while (logInAttenpts < MAXATTEMPTS && entryStatus == false && username.equalsIgnoreCase("") && active == true) {
			try {
				System.out.println("Please enter your user name:");
				username = input.nextLine();
				System.out.println("Please enter your password:");
				password = input1.nextLine();
			} catch (Exception e) {
				System.out.println("__Please enter a username__\n");
			}

			/*
			 * login process.
			 */
			try {
				if (tvSql.login(username, password) == true) {
					entryStatus = true;
				} else {
					username = "";
					password = "";
					logInAttenpts += 1;
					throw new BadLoginCredentialsException();
				}
			} catch (BadLoginCredentialsException e) {
				System.out.println("\n" + e.getMessage() + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (logInAttenpts >= MAXATTEMPTS) {
			System.out.println("\n\nMAX login attmepts reached\n\nPlease register or enter \"9\" in the username to quit");
			System.out.println("Please enter your new user name:");
			String inUser = input.nextLine();
			if(!inUser.equals("9"))
			{	
				System.out.println("Please enter your new password:");
				String inPass = input1.nextLine();
				tvSql.Register(inUser, inPass);
				entryStatus = true;
			}
			else
			{
				System.out.println("Goodbye");
				System.exit(0);
			}
		}

		while (entryStatus == true)
		{
			System.out.println("\nWhat would you like to do?\n"
							 + "\n1. Check the status of a single show"
							 + "\n2. check the status of all my shows" 
							 + "\n3. Update the status of a show"
							 + "\n4. Get a single show information"
							 + "\n5. Add a show to my list."  
							 + "\n6. Print all the show"
							 + "\nPress 9 to Quit");

			String showName = "";
			int status = 0;
			try {
				int toDo2 = input1.nextInt();
				switch (toDo2) {
				case 1:
					System.out.println("Please enter the title of the show you want to check");
					showName = input.nextLine();
					if (tvSql.getStatus(showName, username) != null) {
						String stat = tvSql.getStatus(showName, username);
						System.out.println("The show " + showName + "\'s Status is: " + stat);
					} else {
						throw new ShowNotWatchedException();
					}
					break;

				case 2:
					System.out.println("\nYour history log:");
					tvSql.getAllStatus(username);

					break;

				case 3:
					System.out.println("What show did you want to update?");
					showName = input.nextLine();
					System.out.println("What status would you like to set for " + showName
							+ "\n1. Complete\n2. In Progress\n3. Not Complete");

					status = input1.nextInt();
					tvSql.setStatus(status, showName, username);
					if (tvSql.getStatus(showName, username) != null) {
						System.out.println(showName + "\'s status has successfully been updated " + "to "
								+ tvSql.getStatus(showName, username));
					} else {
						throw new ShowNotWatchedException();
					}
					break;
				
				case 4:
					System.out.println("Enter the show name for the information.");
					showName = input.nextLine();
					tvSql.getShow(showName);
					break;
				case 5:
					System.out.println("What show did you want to add to the list?");
					showName = input.nextLine();
					System.out.println("What status would you like to set for " + showName
							+ "\n1. Complete\n2. In Progress\n3. Not Complete");
					status = input1.nextInt();
					tvSql.setShow(status, showName, username);
					if (tvSql.getStatus(showName, username) != null) {
						System.out.println(showName + "\'s status has successfully been updated " + "to "
								+ tvSql.getStatus(showName, username));
					} 
					break;
				case 6:
					System.out.println("Loading all the show available.");
					tvSql.getAllshow();
					break;
					
				case 9:
					System.out.println("\nThank you for using the TV Show Status Tracker.\nSee you next time");
					entryStatus = false;
					break;
				default:
					System.out.println("\nPlease enter a vaild option\n");
					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("\nPlease enter a vaild option\n");
				input1.next();
			} catch (ShowNotWatchedException e) {
				System.out.println(e.getMessage());
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}

		input.close();
		input1.close();
	}
}
