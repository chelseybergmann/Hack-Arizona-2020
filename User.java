
import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String pass;
	private String username;
	private String field;
	private String age;
	private String email;
	private String type;
	private String seeking;
	private String skills;

	public User(String name, String username, String pass, String field, String age, String email, String type, String seeking, String skills) {
		/*
		 * Purpose: constructs a new instance of the User class with the specified name
		 * and password
		 */
		this.name = name;
		this.username = username;
		this.pass = pass;
		this.field  = field;
		this.age = age;
		this.email = email;
		this.type = type;
		this.seeking = seeking;
		this.skills = skills;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeeking() {
		return seeking;
	}

	public void setSeeking(String seeking) {
		this.seeking = seeking;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		/*
		 * Purpose: returns the name of the user
		 */
		return this.name;
	}
	
	public String getUsername() {
		/*
		 * Purpose: returns the name of the user
		 */
		return this.username;
	}
	
	public String getPass() {
		/*
		 * Purpose: returns the password of the user
		 */
		return this.pass;
	}

	public boolean attemptLogin(String pass) {
		/*
		 * Purpose: returns true if the password is valid for this user 
		 * returns false if invalid password
		 */
		return (this.pass == pass);
	}


	public String toString() {
		/*
		 * Purpose: returns a string description of the user in the format: name,
		 * numPlaylists playlists
		 */
		return "Name: " + this.name + "\nAge: " + this.age + "\nField: " + this.field + "\nEmail: " + this.email + "\nSkills: " + this.skills;
	}
}
