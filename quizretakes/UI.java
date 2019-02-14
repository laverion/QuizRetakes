

import java.util.Scanner;

public class UI {
	


	public UI() {
		
	}
	
	public void startUI() {
		String isProfessor = getUserChoice("Are you a professor (y/n): ");
		
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
