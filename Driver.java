/*
 * DESCRIPTION:
 * 	This class uses a dictionary to organize users within a social 
 * 	network. It allows the user to create their own personal account which
 *  has access to different functions. Some of the functions include setting 
 * 	a username/password, editing profile, adding friends, etc.
 */

import java.util.Iterator;
import java.util.Scanner;

public  class Driver extends Profile {
	
	public static void main(String[] args)
	{
		LinkedDictionary<String,Object> dictionary = new LinkedDictionary<>();
		Profile obj = new Profile();

		int option = intro();
		while(option != 3) 
		{
			int val = 0; // val holds the value for the 5 given options(within profile)
			
			//Creating a new account
			if(option == 1) {
				
				dictionary = newAccount(dictionary);
				String key = Login(dictionary);
				obj = (Profile) dictionary.getValue(key);

				while((val != 6 || val !=5)) {
					
					val = Interface(dictionary,key,obj.getPost());
					
					switch(val)
						{
						case 1:
							editProfile(dictionary,key);
							break;
						case 2:
							listofFriends(dictionary, key);
							break;	
						case 3: 
							addFriends(dictionary, key);
							break;
						case 6:
							System.out.println("Deleted!");
							dictionary.remove(key);
							break;
						case 5: 
							break;
						case 4:
							obj.setPost(post()); 
							break;
						}
					if(val == 5 || val == 6)
						break;
				}
			}
		// if we already have an account	
			else
			{
				String key = Login(dictionary);
				obj = (Profile) dictionary.getValue(key);
				while((val != 6 || val !=5)) {
					
					val =	Interface(dictionary,key,obj.getPost());
					switch(val)
						{
						case 1: 
							editProfile(dictionary,key);
							break;
						case 2: 
							listofFriends(dictionary, key);
							break;	
						case 3: 
							addFriends(dictionary, key);
							break;
						case 6:							
							System.out.println("Deleted!");
							dictionary.remove(key);
							break;
						case 5: 
							break;
						case 4:
							obj.setPost(post()); 
							break;
						}
						if(val == 5 || val == 6)
							break;	
				}
			}
			option = intro();
		}
		
	}
	
	/* 
	 * This class allows us create and sign in to our account
	 * 1 - creates a new account
	 * 2 - asks user to input username/password
	 * 3 - Exits program
	 */
	public static  int intro()
	{		
		int option = 0;
		
		System.out.println("Welcome the New Social Network");
		System.out.println("Please Select one of the following options:\n");
		System.out.println("\t1.) Create a new Profile");
		System.out.println("\t2.) Sign in to existing account");
		System.out.println("\t3.) Exit");
		System.out.println();
		
		do {
		Scanner input = new Scanner(System.in);
		option = input.nextInt();
		}while(option <= 0 || option > 3 );
		if(option == 3)
		{
			System.out.println("Have a wonderful day!");
			System.exit(0);
		}
		return option;
	}
	/*
	 * This class creates a new account and places it into the dictionary
	 * Asks the user to input information such as:
	 *  - name, lastname, birth year, username , and a password
	 */
	public static LinkedDictionary<String, Object> newAccount(LinkedDictionary<String,Object> obj)
	{		
		Scanner input = new Scanner(System.in);
		Profile profile = new Profile() ;
				
		System.out.println("Please enter the following: \n");
		
		System.out.print("First Name: ");
		profile.setFirstName(input.nextLine()); 
		
		System.out.print("Last Name: ");
		profile.setLastName(input.nextLine());
		
		System.out.print("Birth Year: ");
		profile.setYear(input.nextLine());
		
		// must check that username does not exist
		System.out.print("Username: ");
		String userName = input.nextLine();
		profile.setUserName(userName);
		
		System.out.print("Password: ");
		String password = input.nextLine();
		profile.setPassWord(password);
		
		String key; // will hold the key to get into the account
		key = userName + password;
		profile.setKey(key);
		
		// adding to the dictionary
		obj.add(key, profile);
		return obj;
		
	}
	/*
	 * This class is the "interface" the user with experience when signed in
	 * Options:
	 *  1 - Edit Profile
	 *  2 - View Friends List 
	 *  3 - Add Friends
	 *  4 - Add Post
	 *  5 - Log Out (Returns to the 'Main Menu')
	 *  6 - Delete Account (removes it from the dictionary)
	 */
	public static int Interface(LinkedDictionary<String,Object> dictionary,String key,String post)
	{
		Profile obj = new Profile();
		Scanner input = new Scanner(System.in);
				
		// cast from type 'object' to type 'Profile'
		obj = (Profile) dictionary.getValue(key);
		System.out.println("\n\n*******************************");
		System.out.println("[Profile] ");
		System.out.print("Name: ");
		System.out.println(obj.getFirstName() + " " + obj.getLastName());
		System.out.print("Year Born: ");
		System.out.println(obj.getYear());
		
		System.out.println("---------");
		System.out.println("|       |");
		System.out.println("| image |");
		System.out.println("|       |");
		System.out.println("---------");

		
		System.out.println("\nOptions:");
		System.out.println("\t1) Edit Profile:");
		System.out.println("\t2) View Friends List: ");
		System.out.println("\t3) Add friends:");
		System.out.println("\t4) Add post");
		System.out.println("\t5) Log Out");
		System.out.println("\t6) Delete Account");
		System.out.println("*******************************");

	// if the user inputed a 'post'
		if(post != null) {
		System.out.print("[Post] ");
		System.out.println("-->"+post);
		System.out.println("*******************************");
		}
		
		int value = 0;
		System.out.print("Enter: ");
		value = input.nextInt();
		
		while(value <= 0 || value > 6)
		{
			System.out.println("Must be within 1-6");
			value = input.nextInt();
		}
		return value;
	}
/*
 * This method allows user to input their username and password
 * in order to sign in
 */
	public static String Login(LinkedDictionary<String,Object> dictionary)
	{
		Scanner input = new Scanner(System.in);
		String val; // value to exit program
		
		//Logging in
		System.out.println("\n\nLogin: ");
		
		System.out.print("Username: ");
		String userName = input.nextLine();
		System.out.print("Password: ");
		String passWord = input.nextLine();
		
		// must check userName is in the dictionary
		String key = userName + passWord;
		 
		while(!dictionary.contains(key))
		{
			System.out.println("Exit? 'Press 0' ");
			System.out.print("Click button to continue: ");
			val = input.nextLine();
			if(val.equals("0") ){
				System.out.println("\nHave a wonderful day!");
				System.exit(0);
			}
			System.out.println("\n\nPlease try again:  ");
			System.out.print("Username: ");
			userName = input.nextLine();
			System.out.print("Password: ");
			passWord = input.nextLine();
			
			key = userName + passWord;
		}
		return key;
	} 
/*
 * This method allows user to add friends
 */
	public static void addFriends(LinkedDictionary<String, Object> dictionary, String key)
	{	
		Profile obj = new Profile();     // will hold obj from linked list
		String [] arr = new String [10]; // holds the names of people with an account		
		Iterator<Object> iterator = dictionary.getValueIterator(); // iterates through the dicitonationary
				
		Scanner input = new Scanner(System.in);
		int count = 0;
		String name = " ";
		
		System.out.println("Search the name of the person you would like to add");
		System.out.println("Include First and Last Name(With Space included!):");
		if(dictionary.getSize() > 1) {
			System.out.println("\nHere are some options: ");
		}
		while(iterator.hasNext() && count <= 10)
		{
			obj = (Profile) iterator.next();
			
			//we get the names of people with an account, without including the person signed in
			if(obj != dictionary.getValue(key))
			{
				System.out.print(obj.getFirstName());
				System.out.print(" ");
				System.out.print(obj.getLastName());
				System.out.println();
				

				arr[count] = (obj.getFirstName() + " " +obj.getLastName());
				count++;
			}
		}
			//Searching for a friend!
				obj = (Profile) dictionary.getValue(key);
				System.out.print("Search: ");			
				name  = input.nextLine();
			//Checks whether 'name' inputed is already a friend
				if(obj.checkList(name) == true)
				{
					System.out.println("Already your friend!");
					return;
				}
			// if 'name' is not a friend
				else 
				
				for(int i = 0 ; i < dictionary.getSize(); i++)
				{
					// if the inputed name = one in the list
					// then we add it to the list
					
						if(name.equals(arr[i]))
						{
							obj.addFriend(name);
							System.out.println("Added!");
							return;
						}
						
				}
				System.out.println("Not Found! ");
	}
/*
 * This method allows user to view their friends list
 */
	public static void listofFriends(LinkedDictionary<String, Object> dictionary, String key)
	{
		Profile obj = new Profile();
		obj = (Profile) dictionary.getValue(key);
		
		obj.viewFriendList();
	}
/*
 * This method allows user to make changes to their profile
 */
	public static void editProfile(LinkedDictionary<String, Object> dictionary, String key)
	{
		int value = 0; // holds the option selected
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		Scanner input3 = new Scanner(System.in);

		Profile obj = new Profile();
		obj = (Profile) dictionary.getValue(key);
		
		String name, lastname, year, userName, Password;
		
		System.out.println("What would you like to edit?");
		System.out.println("1.) First Name");
		System.out.println("2.) Last Name");
		System.out.println("3.) Year of Birth");
		
		value = input.nextInt();
		
		switch(value) {
		case 3 :
			System.out.println("Enter your year of birth");
			year = input2.nextLine();
			obj.setYear(year);
			break;
		case 1:
			System.out.println("Enter your name!");
			name = input2.next();
			obj.setFirstName(name);
			break;
		case 2:
			System.out.println("Enter your lastname!");
			lastname = input3.nextLine();
			obj.setLastName(lastname);		
			break;
		}
		
	}
/*
 * This method allows user to 'post' on their account
 */
	public static String post()
	{
		Scanner input = new Scanner(System.in);		
		String words;
		
		System.out.println("What would you like the world to know...");
		
		words = input.nextLine();
		
		return words;
		
	}
}

 

