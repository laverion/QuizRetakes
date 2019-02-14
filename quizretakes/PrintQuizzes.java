import java.io.*;
import java.util.*;

public class PrintQuizzes{
	public PrintQuizzes(UI ui){
	
		//retrieves the CourseID from professor and creates a filename of
		//"quiz-appts-[COURSE_ID].txt
	    String courseID = ui.getUserChoice("Please enter the courseID: ");
	    String filename = "quiz-appts-" + courseID + ".txt";
	    
	    File quizReschedule = new File(filename);
	    
	    //Scans the opened file and prints out the student name and quizes each student
	    //wants to retake.
	    //HOW IT WOKRS:
	    //     Reads from the text file line by line. Each line from the file gets saved 
	    //     to the variable current. Then the string current get's looped through 
	    //     backwards a character at a time. Once the first comma is found going 
	    //     backwards, the index of that comma is saved. This comma is the indicator 
	    //     between the Quiz IDs and the students name. 
	    try{
	    	Scanner readFile = new Scanner(quizReschedule);
	    	while(readFile.hasNextLine()){
			String current = readFile.nextLine();
			int index = 0;
			for(int i = (current.length()-1); i > -1; i--){
				if(current.charAt(i) == ','){
					index = i;
					break;
				}
			}
			System.out.printf("\nStudent Name: %s\n", current.substring(index+1));
			System.out.printf("Want's to Retake Quiz ID(s): %s\n", current.substring(0, index));
			}
			System.out.printf("\n");
		}
		catch(Exception e){
			System.out.printf("File not found. Did not find %s.\n", filename);
			System.exit(1);
		}
	}
}