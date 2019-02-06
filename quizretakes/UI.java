

import java.util.Scanner;

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
	
//	
//	courseBean courseBean; 
//	courseReader courseReader;
//	quizBean quizBean;
//	quizReader quizReader;
//	
//	String courseID, name;
//	
	
	public UI() {
		quizschedule quizschedule = new quizschedule();
		quizschedule.doGet(this);
		
	}
	
	
//	public void startUI() {
//		//sets courseID
//		runCourseUI("");
//		
//		// set Name
//		runNameUI();
//		
//		//getDate Selection
//		runSelectionUI();
//		
//		//store it in the XML
//		saveToXML();
//	}
//	
//	/**
//	 * Saves everything to XML
//	 */
//	public void saveToXML() {
//		
//		
//	}
//	
//	/**
//	 * Sets the times the user has selected
//	 */
//	public void runSelectionUI() {
//		//create a field variable of some data type that holds all dates the user has selected 
//		//EDEN
//	}
//	
//	/**
//	 * Sets the name
//	 */
//	public void runNameUI() {
//		
//	}
//	
//	/**
//	 * Sets a valid CourseID from the User if not then it calls itself recursively with error msg. Sets the courseID. 
//	 * @param msg
//	 */
//	public void runCourseUI(String msg) {
//		//Mrinmoy
//		print(msg,true);
//		try {
//			courseBean= courseReader.read("course-swe437.xml");
//		} catch (IOException | ParserConfigurationException | SAXException e) {
//			runCourseUI("Cannot open courseFile");
//		}
//		
//		courseID= getUserChoice("UserID: ");
//		
//		if(!courseID.equals(courseBean.getCourseID())) {
//			runCourseUI("");
//		}
//		
//		//needs implementation 
//	}
//	
//	
	
	/**
	 * Prompts the user to enter an String 
	 * @param options The message that will be displayed 
	 * @return User input string 
	 */
	public String getUserChoice(String options) {
		print(options, false);
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		
		return str;
	}
	
	
	/**
	 * Prompts the User to input a number that is with in the range (0,range]
	 * @param options The selection choices
	 * @param range The max number the user can select
	 * @return return an array of int within the range
	 */
	public int[] getUserChoice(String options, int range) {
		//Eden
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
		System.out.print(x);
	}
	
	/**
	 * Prints to the user 
	 * @param x String to be outputted
	 */
	public void println(String x) {
		print(x, true);
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("Testfjkfldslfkj");
		quizschedule Quizschedule = new quizschedule();
		
		UI ui = new UI();

	}
}
