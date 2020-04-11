
import java.util.ArrayList;
import java.util.List;

public class UserCollection {
	private List<User> userCollection;

	public UserCollection() {
		/* 
		 * Purpose: constructs a new instance of the UserCollection class
		 */
		this.userCollection = new ArrayList<User>();
	}
	
	public User getUser(String username) {
		for (User user : this.userCollection) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> getUserCollection() {
		return this.userCollection;
	}

	public boolean userExists(String name) {
		/* 
		 * Purpose: returns true if a user with the specified username exists
		 * returns false if the username does not exist
		 */
		for (int i = 0; i < this.userCollection.size(); i++) {
			if (this.userCollection.get(i).getName() == name) {
				return true;
			}
		}
		return false;
	}

	public User login(String username, String pass) {
		/* 
		 * Purpose: returns the User associated with the login credentials
		 * if it was a valid login
		 * returns null if the login was invalid
		 */
		for (int i = 0; i < this.userCollection.size(); i++) {
			boolean namesMatch = this.userCollection.get(i).getUsername().equals(username);
			boolean passwordsMatch = this.userCollection.get(i).getPass().equals(pass);
			// checks the login information is correct
			if (namesMatch && passwordsMatch) {
				return this.userCollection.get(i);
			}

		}

		return null;
	}

	public void addUser(User toAdd) {
		/* 
		 * Purpose: adds this user to the collection of all users
		 */
		this.userCollection.add(toAdd);

	}

	public String toString() {
		/* 
		 * Purpose: returns a string description of all users in the format:
		 * { user: user, playlist count 'playlists, '}
		 */
		String result = "{ ";

		for (int i = 0; i < this.userCollection.size(); i++) {
			String user = this.userCollection.get(i).getName();
			String playlistCount = this.userCollection.get(i).toString();
			result += user + ": " + user + ", " + playlistCount + "playlists, ";
		}
		return (result += "}");
	}
}
