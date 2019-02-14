

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
		
	}
	
	public void startUI() {
	
		//ensures that the user either enters y or n asking if a person is a professor 
		//or not
		String isProfessor = getUserChoice("Are you a professor (y/n): ");
		while(isProfessor.toLowerCase().charAt(0) != 'y' && isProfessor.toLowerCase().charAt(0) != 'n'){
			System.out.println("Please enter a valid choice like Y, y, N, or n.");
			isProfessor = getUserChoice("Are you a professor (y/n): ");
		}
		
		//prints out the students who want to do a retake if y to the professor question
		//or schedules an appointment for the student if n was selected
		if(isProfessor.toLowerCase().charAt(0) == 'y') {
			new PrintQuizzes(this); 
		}
		else {
			quizschedule quizschedule = new quizschedule();
			quizschedule.doGet(this);
		}
	}
	
	
	
	
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
		
		UI ui = new UI();
		ui.startUI();

	}
}
