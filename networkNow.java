import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Chloe Dillingham
 * @author Erica Kulash
 * @author Chelsey Bergmann
 * @author Andrew deBerardinis
 * 
 *         This program represents a prototype for a potential application that
 *         connects modern professionals in real time in the same general
 *         vicinity. When is the last time you connected with your 500 LinkedIn
 *         connections? This application intends to create spontaneous,
 *         face-to-face connections for like-minded people.
 * 
 *         For the purpose of this prototype/demo, we have created a txt file
 *         with sample data about example users. We have created a
 *         UserCollection object as a database for all Users in the vicinity.
 *         This program reads in this file and creates the collection of users
 *         before launching our GUI interface. The interface displays a series
 *         of question to learn information about the User in order to more
 *         carefully match them with other nearby Users.
 * 
 *         For the matching algorithm, it is designed to check for matching
 *         relevant keywords. It ignores keywords, plurals, and some suffixes.
 *
 */

public class networkNow extends Application {

	// Private instance variables
	private String name;
	private String pass;
	private String username;
	private String field;
	private String age;
	private String email;
	private String type;
	private String seeking;
	private String skills;
	private int annette_X = 100;
	private int annette_Y = 130;
	private int annetteTextX = 650;
	private int annetteTextY = 200;
	private int blueBoxX = 330;
	private int blueBoxY = 80;
	private String text = "";
	private ArrayList<String> userInputList = new ArrayList<String>();
	private Canvas canvas = new Canvas(1000, 725);
	private GraphicsContext canvasGC = canvas.getGraphicsContext2D();
	private UserCollection all = new UserCollection();
	private ArrayList<Integer> y = new ArrayList<Integer>();

	/**
	 * Purpose: Create the Graphics Context for initial setup of the GUI.
	 * 
	 * @param primaryStage, Stage to set canvas
	 */
	public void start(Stage primaryStage) throws FileNotFoundException, InterruptedException {
		y.add(475);
		initNetwork(all);
		TextArea command = new TextArea();
		GraphicsContext gc = setupStage(primaryStage, 1000, 725, command);
		primaryStage.show();
	}

	/**
	 * Purpose: This method sets up the outline for the GUI.
	 * 
	 * @param primaryStage, Stage for the canvas to be set on
	 * @param canvas_width, width of canvas
	 * @param canvas_height, Height of canvas
	 * @param command, TextArea for user input at bottom of GUI
	 * 
	 * @return canvasGC, Graphics Context canvas which holds everything on the GUI
	 * 
	 * @throws FileNotFoundException, Handles if exception is thrown
	 * @throws InterruptedException,  Handles if exception is thrown
	 */
	public GraphicsContext setupStage(Stage primaryStage, int canvas_width, int canvas_height, TextArea command)
			throws FileNotFoundException, InterruptedException {

		// Border pane will contain canvas for drawing and text area underneath
		BorderPane p = new BorderPane();

		// Canvas(pixels across, pixels down)
		Image image = new Image(new FileInputStream("PublicTestCases/aNet.png"));
		command.setPrefHeight(100);
		command.setEditable(true);

		// Place the canvas and command output areas in pane.
		p.setCenter(canvas);
		p.setBottom(command);

		// Sets colors for different shapes and words
		// Puts all initial graphics on the canvas such as the image and text
		canvasGC.setFill(Color.LIGHTSKYBLUE);
		canvasGC.fillRect(0, 0, 1000, 725);
		canvasGC.setFont(Font.font("Courier New", FontWeight.NORMAL, FontPosture.REGULAR, 50));
		canvasGC.setFill(Color.WHITE);
		canvasGC.fillText("NETWORK", 325, 50);
		canvasGC.setFill(Color.GREENYELLOW);
		canvasGC.fillText("NOW", 535, 50);
		canvasGC.setTextAlign(TextAlignment.CENTER);
		canvasGC.setFill(Color.BLACK);
		canvasGC.setFont(Font.font("Courier New", FontWeight.NORMAL, FontPosture.REGULAR, 20));
		canvasGC.fillText("... Hi! I'm Annette", annetteTextX, annetteTextY);
		canvasGC.fillText("Welcome to NetworkNow", annetteTextX, annetteTextY + 20);
		createProfile(all, command);
		canvasGC.drawImage(image, annette_X, annette_Y, 300, 450);

		// Title the stage and place the pane into the scene into the stage.
		primaryStage.setTitle("NetworkNow");
		primaryStage.setScene(new Scene(p));

		return canvasGC;
	}

	/**
	 * Purpose: Creates the users profile. AI asks the user questions and creates
	 * their profile based on their answers, matches users to the best match.
	 * 
	 * @param all, Collection of all User objects
	 * @param command, TextArea for user input at bottom of GUI
	 */
	public void createProfile(UserCollection all, TextArea command) {
		command.setOnKeyPressed(new EventHandler<KeyEvent>() {

			// Handling for Events
			// Specifically a Key being pressed down
			public void handle(KeyEvent keyEvent) {

				// Checks if certain keys are pressed and specific actions are executed based on
				// the pressed key
				if (keyEvent.getCode() == KeyCode.ESCAPE) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("NetworkNow would like to access your location", annetteTextX, annetteTextY + 30);
					canvasGC.fillText("Please press enter for yes:", annetteTextX, annetteTextY + 45);
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F1) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("What is your full name?", annetteTextX, annetteTextY + 20);
					name = command.getText();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F2) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Please enter your username?", annetteTextX, annetteTextY + 20);
					username = command.getText();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F3) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Please enter a password", annetteTextX, annetteTextY + 20);
					pass = command.getText();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F4) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Please enter your field of study", annetteTextX, annetteTextY + 20);
					field = command.getText();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F5) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Please enter your age", annetteTextX, annetteTextY + 20);
					age = command.getText();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F6) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Please select the type of profile you want to create", annetteTextX,
							annetteTextY + 20);
					canvasGC.fillText("- Employer", annetteTextX - 10, annetteTextY + 40);
					canvasGC.fillText("- Applicant", annetteTextX - 10, annetteTextY + 60);
					canvasGC.fillText("- Networker", annetteTextX - 10, annetteTextY + 80);
					type = command.getText().toUpperCase();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F7) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Please enter your email address", annetteTextX, annetteTextY + 20);
					email = command.getText();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F8) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Tell me a little about what you're looking for!", annetteTextX,
							annetteTextY + 20);
					seeking = command.getText();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F9) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Tell me about yourself! (hint: list your top skills, ", annetteTextX,
							annetteTextY + 20);
					canvasGC.fillText("if/where you work,  what interest you?", annetteTextX, annetteTextY + 40);
					skills = command.getText();
					command.setText("");
				}

				if (keyEvent.getCode() == KeyCode.F10) {
					canvasGC.setFill(Color.LIGHTSKYBLUE);
					canvasGC.fillRect(blueBoxX, blueBoxY, 750, 225);
					canvasGC.setFill(Color.BLACK);
					canvasGC.fillText("Awesome! I'll put together your profile right now", annetteTextX,
							annetteTextY + 20);
					command.setText("");

					User user = new User(name, username, pass, field, age, email, type, seeking, skills);
					System.out.println(user.toString());
					validSearch(user, all);
				}

			}
		});

	}

	public static void main(String[] args) throws Exception {
		// Launches javafx GUI
		launch(args);
	}

	// Gets data for already created profiles from users, adds them to the
	// userCollection
	public void initNetwork(UserCollection all) throws FileNotFoundException {
		Map<String, List<String>> people = new HashMap<String, List<String>>();
		Map<String, String> lookingFor = new HashMap<String, String>();
		Map<String, String> you = new HashMap<String, String>();
		Scanner scanner = new Scanner(new File("PublicTestCases/netnowtester.txt"));

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String name = "";
			if (line.startsWith("Full Name")) {

				// Get user's general data
				String[] fullName = line.split(": ");
				name = fullName[1];
				List<String> data = new ArrayList<>();
				String[] field = scanner.nextLine().split(": ");
				data.add(field[1]);
				String[] username = scanner.nextLine().split(": ");
				data.add(username[1]);
				String[] password = scanner.nextLine().split(": ");
				data.add(password[1]);
				String[] age = scanner.nextLine().split(": ");
				data.add(age[1]);
				String[] email = scanner.nextLine().split(": ");
				data.add(email[1]);
				String[] type = scanner.nextLine().split("Type: ");
				data.add(type[1]);
				people.put(name, data);
			}

			line = scanner.nextLine();
			String looking = "";
			while (line.startsWith("Looking for") || !line.startsWith("Yourself")) {
				if (line.startsWith("Looking for")) {

					// Get looking for first line
					String[] parts = line.split(": ");
					for (String word : parts[1].split(" ")) {
						looking += word + " ";
					}
				} else {

					// Get the remainder of the paragraph
					String[] parts = line.split(" ");
					for (String word : parts) {
						looking += word + " ";
					}
				}
				line = scanner.nextLine();
			}
			lookingFor.put(name, looking.substring(0, looking.length() - 1));

			String yourself = "";
			while (line.startsWith("Yourself") || line.length() > 0) {
				if (line.startsWith("Yourself")) {

					// Get yourself's first line
					String[] parts2 = line.split(": ");
					for (String word : parts2[1].split(" ")) {
						yourself += word + " ";
					}
				} else {

					// Get the remainder of the lines
					String[] parts2 = line.split(" ");
					for (String word : parts2) {
						yourself += word + " ";
					}
				}
				you.put(name, yourself.substring(0, yourself.length() - 1));
				if (scanner.hasNextLine()) {
					line = scanner.nextLine();
				} else {

					// End of file
					break;
				}
			}

			if (line.length() == 0) {
				if (!scanner.hasNextLine()) {

					// End of file
					break;
				}
			}
		}
		makeUserCollection(people, lookingFor, you, all.getUserCollection());
	}

	// Organize all user objects, or users with a profile, into a list
	public void makeUserCollection(Map<String, List<String>> people, Map<String, String> lookingFor,
			Map<String, String> you, List<User> all) {

		// Get user's data to create a new user object
		for (String person : people.keySet()) {
			List data = people.get(person);
			String field = (String) data.get(0);
			String username = (String) data.get(1);
			String password = (String) data.get(2);
			String age = (String) data.get(3);
			String email = (String) data.get(4);
			String type = (String) data.get(5);

			String lookingForStr = lookingFor.get(person);
			String yourselfStr = you.get(person);

			// Create user object
			User user = new User(person, username, password, field, age, email, type, lookingForStr, yourselfStr);

			// Add user to the list
			all.add(user);
		}
	}

	// POTENTIAL FEATURES FOR VERSION 2
	/*
	 * public User login(UserCollection all, Scanner scanner) {
	 * System.out.println("Username? "); String username = scanner.nextLine();
	 * System.out.println("Password? "); String pass = scanner.nextLine();
	 * 
	 * User attempt = all.login(username, pass); if (attempt == null) {
	 * System.out.println("Login failed!"); } else {
	 * System.out.println("Logged in as " + username);
	 * validSearch(all.getUser(username), all); } return attempt; }
	 */

	/**
	 * Purpose: This method searches the UserCollection for the correct type of
	 * person that could be a potential match for the current User. If one is found,
	 * call the findMatches method to find keyword Matches in the user's search
	 * descriptions.
	 * 
	 * @param User           user, user object looking for matches
	 * @param UserCollection all, UserCollection object to search through
	 */
	public void validSearch(User user, UserCollection all) {

		for (User profile : all.getUserCollection()) {

			String userField = user.getField().toUpperCase();
			String profileField = user.getField().toUpperCase();

			if (profileField.equals(userField)) {

				if ((user.getType().toUpperCase().equals("EMPLOYER")
						&& profile.getType().toUpperCase().equals("APPLICANT"))
						|| (profile.getType().toUpperCase().equals("EMPLOYER")
								&& user.getType().toUpperCase().equals("APPLICANT"))
						|| (user.getType().toUpperCase().equals("NETWORKER")
								&& profile.getType().toUpperCase().equals("NETWORKER"))) {

					if (!user.getName().equals(profile.getName())) {
						int val = y.get(0) + 45;
						y.set(0, val);
						findMatches(user, profile, y);
					}

				}
			}
		}
	}

	/**
	 * Purpose: This method analyzes info from a potential matching User and outputs
	 * a matching score for the User to view.
	 * 
	 * @param User user, user object looking for matches
	 * @param User other, a potential match for the user
	 * @param      ArrayList<Integer> y, a y coordinate to ensure the GUI display is
	 *             consistent
	 */
	public void findMatches(User user, User other, ArrayList<Integer> y) {

		System.out.println("got to findMatches method");
		// Creating the array lists
		TreeMap<Float, User> validMatches = new TreeMap<Float, User>();
		String[] lookingFor = user.getSeeking().toUpperCase().split(" ");
		String[] possibleMatch = other.getSkills().toUpperCase().split(" ");

		// create set of irrelevant words
		String connectingWords[] = { "I", "am", "a", "the", "and", "like", "an", "it", "is", "be", "to", "by", "at",
				"around", "if", "because", "how", "their", "our", "my", "for", "looking", "that", "so" };
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < connectingWords.length; i++) {
			set.add(connectingWords[i].toUpperCase());
		}

		// Check to see if it contains any of these words and if
		// not, add it to the new ArrayList
		// also clearing some discrepencies

		ArrayList<String> yourselfList = new ArrayList<String>();
		for (int i = 0; i < possibleMatch.length; i++) {
			if (!set.contains(possibleMatch[i].toUpperCase())) {
				// KEEP ONLY ALPHABETIC CHARACTERS
				String newString = possibleMatch[i].replaceAll("[^a-zA-Z]", "");

				// GET RID OF SUFFIX
				String noIng = newString.replaceAll("ing", "");

				// IGNORE SOME PLURALS
				if (noIng.endsWith("s")) {
					int stop = noIng.length() - 2;
					noIng = noIng.substring(0, stop);
				}

				yourselfList.add(noIng.toUpperCase());
			}
		}

		ArrayList<String> lookingList = new ArrayList<String>();
		for (int i = 0; i < lookingFor.length; i++) {
			if (!set.contains(lookingFor[i])) {
				// KEEP ONLY ALPHABETIC CHARACTERS
				String newString = lookingFor[i].replaceAll("[^a-zA-Z]", "");

				// GET RID OF SUFFIX
				String noIng = newString.replaceAll("ing", "");

				// IGNORE SOME PLURALS
				if (noIng.endsWith("s")) {
					int stop = noIng.length() - 2;
					noIng = noIng.substring(0, stop);
				}

				lookingList.add(noIng.toUpperCase());
			}
		}
		// POINT SYSTEM
		ArrayList<String> matches = new ArrayList<String>();
		for (int i = 0; i < yourselfList.size(); i++) {
			String word = yourselfList.get(i);
			for (int j = 0; j < lookingList.size(); j++) {
				String compare = lookingList.get(j);
				if (compare.equals(word) || compare.startsWith(word)) {
					matches.add(word);
				}
			}
		}

		// find the percentage of matching words
		int originalLength = yourselfList.size();
		int newLength = matches.size();
		final DecimalFormat df = new DecimalFormat("0.00");
		float finalPercent = (float) newLength / originalLength * 100;

		// PRINT SUMMARY
		if (finalPercent > 0) {
			canvasGC.fillText("I have found you a match with " + other.getName(), 575, y.get(0));
			canvasGC.fillText("Match compatability percent: " + df.format(finalPercent) + "%", 575, y.get(0) + 20);
		} else {
			int val = y.get(0) - 45;
			y.set(0, val);
		}

	}

}
