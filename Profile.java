/*
 * NAME: Samir Macias
 */
public class Profile
	{
		int count = 0; 
		int size = 100; // max one hundred friends
		private String firstName;
		private String lastName; 
		private String year;
		private String userName;
		private String passWord;
		private String key;
		private String post ;
		private String [] friendList =  new String [size];
		
	
	Profile()
	{
		firstName = " "; 
		lastName = " ";
		year = " ";
		userName = " ";
		passWord = " ";
	}
		// checks whether friend is already in list
		public boolean checkList(String name)
		{
			if(friendList.length == 0)
			{
				return false;
			}
			for(int i = 0; i < count; i++)
			{
				if(name.equals(friendList[i]))
				{
					return true;
				}
			}
			return false;
		}
		// adds friend to profile
		public void addFriend(String name)
		{
			friendList[count] = name;
			count++;
		}
		// allows users to view all the friends they added
		void viewFriendList()
		{
			if(count == 0)
			{
				System.out.println("You have not added your friends!");
			}
			else {
			System.out.println("Friends List: ");
			for(int i = 0; i < count; i++)
			{
				System.out.print((i+1) + ".) ");
				System.out.println(friendList[i]);
			}
			}
		}
		
		String getKey()
		{
			return key;
		}
		void setKey(String keys)
		{
			key = keys;
		}
		String getFirstName()
		{
			return firstName;
		}
		void setFirstName(String name)
		{
			firstName = name;
		}
		String getLastName()
		{
			return lastName;
		}
		void setLastName(String name)
		{
			lastName = name;
		}
		String getYear()
		{
			return year;
		}
		void setYear(String name)
		{
			year = name;
		}
		String getUserName()
		{
			return userName;
		}
		void setUserName(String name)
		{
			userName = name;
		}
		String getPassWord()
		{
			return passWord;
		}
		void setPassWord(String pass)
		{
			passWord = pass;
		}
		void setPost(String status)
		{
			post = status;
		}
		String getPost()
		{
			return post;
		}
		
	}