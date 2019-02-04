package quizretakes;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class UI {
	
	public static final String COURSE_SELECTION_TITLE="GMU quiz retake scheduler";
	public static final String COURSE_SELECTION_OPTION="Please enter the course ID given to you by your instructor. It is probably the same as the university course ID, with no spaces. \r\n" + 
			"courseID: ";
	public static final String COURSE_SELECCTION_SCHEDULE_TITLE="GMU quiz retake scheduler for class Software testing";
	public static final String COURSE_SELECCTION_SCHEDULE_HEADER=getHeaderWithDate()+"\n"+"Name: ";
	
	/**
	 * Creates the header for selection schedule with proper date
	 * @return Header for selection schedule with proper date
	 */
	public static String getHeaderWithDate() {
		//produce what is below!!
		//You can sign up for quiz retakes within the next two weeks. Enter your name (as it appears on the class roster), then select which date, time, and quiz you wish to retake from the following list. 
		//Today is SUNDAY, FEBRUARY 3

		//Currently scheduling quizzes for the next two weeks, until SUNDAY, FEBRUARY 17
		
		return null;
	}
	
	
	courseBean courseBean; 
	courseReader courseReader;
	quizBean quizBean;
	quizReader quizReader;
	
	String courseID, name;
	
	
	public UI() {
		courseReader = new courseReader();
		
		
		startUI();
	}
	
	
	public void startUI() {
		//sets courseID
		runCourseUI("");
		
		// set Name
		runNameUI();
		
		//getDate Selection
		runSelectionUI();
		
		//store it in the XML
		saveToXML();
	}
	
	/**
	 * Saves everything to XML
	 */
	public void saveToXML() {
		
	}
	
	/**
	 * Sets the times the user has selected
	 */
	public void runSelectionUI() {
		//create a field variable of some data type that holds all dates the user has selected 
	}
	
	/**
	 * Sets the name
	 */
	public void runNameUI() {
		
	}
	
	/**
	 * Sets a valid CourseID from the User if not then it calls itself recursively with error msg. Sets the courseID. 
	 * @param msg
	 */
	public void runCourseUI(String msg) {
		print(msg,true);
		try {
			courseReader.read("course-swe437.xml");
		} catch (IOException | ParserConfigurationException | SAXException e) {
			runCourseUI("Cannot open courseFile");
		}
		//needs implementation 
	}
	
	
	
	/**
	 * Prompts the user to enter an String 
	 * @param options The message that will be displayed 
	 * @return User input string 
	 */
	public String getUserChoice(String options) {
		return null;
	}
	
	
	/**
	 * Prompts the User to input a number that is with in the range (0,range]
	 * @param options The selection choices
	 * @param range The max number the user can select
	 * @return return an array of int within the range
	 */
	public int[] getUserChoice(String options, int range) {
		return null;
	}
	
	/**
	 * Prints to the user 
	 * @param x String to be outputted
	 * @param newLine Creates new line if true 
	 */
	public void print(String x, boolean newLine) {
		if(newLine)
			x+="\n";
		System.out.println(x);
	}
	
	
	
	public static void main(String[] args) {
		

	}
}
