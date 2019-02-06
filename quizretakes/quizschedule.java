import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

// JO 3-Jan-2019


/**
 * @author Jeff Offutt
 *         Date: January, 2019
 *
 * Wiring the pieces together:
 *    quizschedule.java -- Servlet entry point for students to schedule quizzes
 *    quizReader.java -- reads XML file and stores in quizzes.
                             Used by quizschedule.java
 *    quizzes.java -- A list of quizzes from the XML file
 *                    Used by quizschedule.java
 *    quizBean.java -- A simple quiz bean
 *                      Used by quizzes.java and readQuizzesXML.java
 *    retakesReader.java -- reads XML file and stores in retakes.
                             Used by quizschedule.java
 *    retakes.java -- A list of retakes from the XML file
 *                    Used by quizschedule.java
 *    retakeBean.java -- A simple retake bean
 *                      Used by retakes.java and readRetakesXML.java
 *    apptBean.java -- A bean to hold appointments

 *    quizzes.xml -- Data file of when quizzes were given
 *    retakes.xml -- Data file of when retakes are given
 */



public class quizschedule 
{
	
	
	
   // Data files
   // location maps to /webapps/offutt/WEB-INF/data/ from a terminal window.
   // These names show up in all servlets
   private static final String dataLocation    = "";
   static private final String separator = ",";
   private static final String courseBase   = "course";
   private static final String quizzesBase = "quiz-orig";
   private static final String retakesBase = "quiz-retakes";
   private static final String apptsBase   = "quiz-appts";

   // Filenames to be built from above and the courseID parameter
   private String courseFileName;
   private String quizzesFileName;
   private String retakesFileName;
   private String apptsFileName;

   // Passed as parameter and stored in course.xml file (format: "swe437")
   private String courseID;
   // Stored in course.xml file, default 14
   // Number of days a retake is offered after the quiz is given
   private int daysAvailable = 14;

   // To be set by getRequestURL()
   private String thisServlet = "";


// doGet() : Prints the form to schedule a retake

protected void doGet(UI ui)
{	
	
	//prints the header and the footer
	ui.println(servletUtils.getHeader());
	ui.println(servletUtils.getFooter());
	ui.println("");
   // CourseID must be a parameter (also in course XML file, but we need to know which course XML file ...)
   courseID = ui.getUserChoice("Please enter the courseID: ");
   if (courseID != null && !courseID.isEmpty())
   {  // If not, ask for one.
      courseBean course;
      courseReader cr = new courseReader();
      courseFileName = dataLocation + courseBase + "-" + courseID + ".xml";
      try {
         course = cr.read(courseFileName);
      } catch (Exception e) {
         String message = "Can't find the data files for course ID " + courseID + ". You can try again.";
         ui.print(message, true);
         ui.print(servletUtils.getFooter(), (true));
         doGet(ui);
         return;
      }
      daysAvailable = Integer.parseInt(course.getRetakeDuration());

      // Filenames to be built from above and the courseID
      String quizzesFileName = dataLocation + quizzesBase + "-" + courseID + ".xml";
      String retakesFileName = dataLocation + retakesBase + "-" + courseID + ".xml";
      String apptsFileName   = dataLocation + apptsBase   + "-" + courseID + ".txt";

      // Load the quizzes and the retake times from disk
      quizzes quizList    = new quizzes();
      retakes retakesList = new retakes();
      quizReader    qr = new quizReader();
      retakesReader rr = new retakesReader();

      try { // Read the files and print the form
         quizList    = qr.read (quizzesFileName);
         retakesList = rr.read (retakesFileName);
         printQuizScheduleForm (ui, quizList, retakesList, course);
      } catch (Exception e)
      {
         String message = "Can't find the data files for course ID " + courseID + ". You can try again.";
         ui.println(message);
         doGet(ui);
         return;
      }
   }
  
   
}

 //doPost saves an appointment in a file and prints an acknowledgement

protected void doPost (String courseID, String name, String inputArray[],UI ui)
               
{
   // No saving if IOException
   boolean IOerrFlag = false;
   String IOerrMessage = "";

   // Filename to be built from above and the courseID

   String apptsFileName   = dataLocation + apptsBase + "-" + courseID + ".txt";

   // Get name and list of retake requests from parameters
   String studentName = name;
   String[] allIDs    = inputArray;


   ui.println("");
   
   if(allIDs != null && studentName != null && studentName.length() > 0)
   {
      // Append the new appointment to the file
      try {
         File file = new File(apptsFileName);
         synchronized(file)
         { // Only one student should touch this file at a time.
            if (!file.exists())
            {
               file.createNewFile();
            }
            FileWriter     fw = new FileWriter(file.getAbsoluteFile(), true); //append mode
            BufferedWriter bw = new BufferedWriter(fw);

            for(String oneIDPair : allIDs)
            {
               bw.write(oneIDPair + separator + studentName + "\n");
            }

            bw.flush();
            bw.close();
         } // end synchronize block
      } catch (IOException e) {
         IOerrFlag = true;
         IOerrMessage = "I failed and could not save your appointment." + e;
      }

      // Respond to the student
      if (IOerrFlag)
      {
         ui.println (IOerrMessage);
      } else {
  
         if (allIDs.length == 1)
            ui.println (studentName + ", your appointment has been scheduled.");
         else
            ui.println (studentName + ", your appointments have been scheduled.");
         ui.println ("Please arrive in time to finish the quiz before the end of the retake period.");
         ui.println ("If you cannot make it, please cancel by sending email to your professor.");
      }

   } else { // allIDs == null or name is null
      
      if(allIDs == null)
         ui.println ("You didn't choose any quizzes to retake.");
      if(studentName == null || studentName.length() == 0)
         ui.println ("You didn't give a name ... no anonymous quiz retakes.");

      ui.println("You can try again if you like");
      //starts all over again
      doGet(ui);
   }
}

/**
 * Print the body of HTML
 * @param ui PrintWriter
 * @throws ServletException
 * @throws IOException
*/
private void printQuizScheduleForm (UI ui, quizzes quizList, retakes retakesList, courseBean course)
{
   // Check for a week to skip
	
   String name, inputArray[];
   boolean skip = false;
   LocalDate startSkip = course.getStartSkip();
   LocalDate endSkip   = course.getEndSkip();

   boolean retakePrinted = false;

   ui.println("\nGMU quiz retake scheduler for class " + course.getCourseTitle() );

   // print 'the main form
   ui.print( "\nYou can sign up for quiz retakes within the next two weeks. ", true);   
   ui.print("Enter your name (as it appears on the class roster", false);
   ui.println("then select which date, time, and quiz you wish to retake from the following list.");

   LocalDate today  = LocalDate.now();
   LocalDate endDay = today.plusDays(new Long(daysAvailable));
   LocalDate origEndDay = endDay;
   // if endDay is between startSkip and endSkip, add 7 to endDay
   if (!endDay.isBefore(startSkip) && !endDay.isAfter(endSkip))
   {  // endDay is in a skip week, add 7 to endDay
      endDay = endDay.plusDays(new Long(7));
      skip = true;
   }

   ui.print  ("\nToday is ",false);
   ui.print((today.getDayOfWeek()) + ", " + today.getMonth() + " " + today.getDayOfMonth(), false );
   ui.print (" Currently scheduling quizzes for the next two weeks, until ", false);
   ui.println((endDay.getDayOfWeek()) + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth() );

   name = ui.getUserChoice("\nEnter your name: ");

   for(retakeBean r: retakesList)
   {
	   ui.println("");
	   LocalDate retakeDay = r.getDate();
      if (!(retakeDay.isBefore (today)) && !(retakeDay.isAfter (endDay)))
      {
         // if skip && retakeDay is after the skip week, print a white bg message
         if (skip && retakeDay.isAfter(origEndDay))
         {  // A "skip" week such as spring break.
            ui.println (" Skipping a week, no quiz or retakes.");
            // Just print for the FIRST retake day after the skip week
            skip = false;
         }
         retakePrinted = true;
         // format: Friday, January 12, at 10:00am in EB 4430
         ui.println ( retakeDay.getDayOfWeek() + ", " +
                      retakeDay.getMonth() + " " +
                      retakeDay.getDayOfMonth() + ", at " +
                      r.timeAsString() + " in " +
                      r.getLocation());
         
         for(quizBean q: quizList)
         {
            LocalDate quizDay = q.getDate();
            LocalDate lastAvailableDay = quizDay.plusDays(new Long(daysAvailable));
            // To retake a quiz on a given retake day, the retake day must be within two ranges:
            // quizDay <= retakeDay <= lastAvailableDay --> (!quizDay > retakeDay) && !(retakeDay > lastAvailableDay)
            // today <= retakeDay <= endDay --> !(today > retakeDay) && !(retakeDay > endDay)

            if (!quizDay.isAfter(retakeDay) && !retakeDay.isAfter(lastAvailableDay) &&
                !today.isAfter(retakeDay) && !retakeDay.isAfter(endDay))
            {
               ui.println ( q.getID()+separator  + r.getID() +"  -- Quiz" + q.getID() + " from " + quizDay.getDayOfWeek() + ", " + quizDay.getMonth() + " " + quizDay.getDayOfMonth() );
            }
         }
         
      }

    
   	}
   
   //gets the user dates 
   String input = ui.getUserChoice("\nPlease Select the Quizes you wish to retake. And seperate then using one space only. ex 1"+separator+"1\n: ");
   inputArray= input.split(" ");
   
   //calls the do post method to do the saving 
   doPost(input, name, inputArray, ui);
	}
}

