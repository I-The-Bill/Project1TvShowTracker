package TvTracker;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class TvTrackerDriver {
	static Scanner input = new Scanner(System.in);
	static Scanner input1 = new Scanner(System.in);
	public static TvDAO tvSql = new TvDAO();
	public static String username = "";
	public static String password = "";
	private static boolean entryStatus = false;
	
	public static void main(String[] args) {
		//SetUp
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		System.out.println("\n_________________________________\n"
						+ "         _           _              \n"
						+ "        / \\**       / \\**         \n"
						+ "       / _ \\**     / _ \\**        \n"
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
						+ "      \\ \\_/ /**   \\ \\_/ /**     \n"
						+ "       \\   /**     \\   /**        \n"
						+ "        \\_/**       \\_/**         \n" 
						+ "          **          **            \n"
						+ "_________________________________   \n"
						+ "                                    \n"
						+ "Main Menu                           \n");
		
		boolean active = true;
		while (active == true)
		{
			System.out.println("What would you like to do?\n"
							+"\n1. Log In"
							+"\n2. Check the status of a single show"
							+"\n3. Check the status of all my shows"
							+"\n4. Update the status of a show"
							+"\n5. Log out"
							+"\n6. Create new registery"
							+"\nPress 9 to Quit");


			String showName = "";
			int status = 0;
			try 
			{
				int toDo = input1.nextInt();

				switch(toDo) {
					case 1:
						if(entryStatus==true) {
							System.out.println("\nYou are already logged in");
							break;
						}
						logIn(false, username, password, input, input1, input1);
						break;
						
					case 2:
						if(entryStatus==false) {
							System.out.println("\nPlease login first");
							break;
						}
						System.out.println("Please enter the title of the show you want to check");
						showName = input.nextLine();
						System.out.println("The show "+ showName + "\'s Status is: " + tvSql.getStatus(showName, username));
						break;
					
					case 3:
						if(entryStatus==false) {
							System.out.println("\nPlease login first");
							break;
						}
						System.out.println("\nYour history log:");
						tvSql.getAllStatus(username);
						break;
						
					case 4:
						if(entryStatus==false) {
							System.out.println("\nPlease login first");
							break;
						}
						System.out.println("What show did you want to update?");
						showName = input.nextLine();
						System.out.println("What status would you like to set for " + showName 
								+ "\n1. Complete\n2. In Progress\n3. Not Complete");
						status = input1.nextInt();
						tvSql.setStatus(status,showName,username);
						System.out.println(showName + "\'s status has successfully been updated "
								+ "to " + tvSql.getStatus(showName, username));
						break;
					
					case 5:
						if(entryStatus==false) {
							System.out.println("\nYou are not logged in yet");
							break;
						}
						System.out.printf("\n%s has been logged out\n", username);
						username = password = "";
						break;
						
					case 6:
						if(entryStatus==true) {
							System.out.println("\nPlease logout first");
							break;
						}
						System.out.println("\nCreate new Account\n");
						logIn(true, username, password, input, input1, input1);
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
			catch (NoSuchElementException e) 
			{
				System.out.println("\nPlease enter a vaild option\n");
				input1.next();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		input.close();
		input1.close();
	}
	
	public static void entry(Scanner input2, Scanner input3) {
		try {
			System.out.println("\nPlease enter your user name:");
			username = input2.nextLine();
			System.out.println("Please enter your password:");
			password = input3.nextLine();
			//return username1;
		} 	
		catch (Exception e) 
		{
			System.out.println("Invalid characters\n");
		}
	}
	
	
	public static void logIn(boolean newAC, String username, String password, Scanner input2, Scanner input3, Scanner repeat) {
		
		boolean repeatStatus = true;
		boolean tryAgain = true;
		String Again = "";
		/* Making sure a username is entered 
		 * 
		*/
		while (repeatStatus==true) {
			entry(input2, input3);
			
			//login process. 
			try {
				if (newAC == false) {
					if (tvSql.login(username, password) == true) {
						repeatStatus = false;
						entryStatus = true;
						break;
					} else {
						System.out.println("Would you like to try again: y/n");
						Again=repeat.nextLine();
						if (Again.toUpperCase()=="Y") {
							System.out.println("Please try again");
							repeatStatus = true;
							break;
						} else {
							System.out.println("Main Menu\n");
							repeatStatus = false;
							break;
						} 
					}

				} else if (tvSql.Register(username, password) == true) {
						repeatStatus = false;
						entryStatus = true;
						break;
				}
				else {
					System.out.println("Would you like to try again: y/n");
					Again=repeat.nextLine();
					if (Again.toUpperCase()=="Y") {
						System.out.println("Please try again");
						repeatStatus = true;
						break;
					}
					else {
						System.out.println("Main Menu\n");
						repeatStatus = false;
						break;
					}
				}
				
				
			} catch (NoSuchElementException e) {
				System.out.println("\nPlease enter a vaild option\n");
				repeat.next();
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}
		entryStatus = false;	
	}

}




