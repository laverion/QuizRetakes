// JO 9-Jan-2019
// Utilities for servlets ... shared methods



import java.io.PrintWriter;



/**
 * @author Jeff Offutt
 *         Date: January, 2019
 * Used by all servlets
 *
 */

public class servletUtils
{

/**
 * Print the header of the HTML pages
 * @param ui PrintWriter
*/
static String getHeader () 
{

   return("Quiz retake scheduler\n------------------------");

}

/**
 * Print the footer of HTML page
 * @param out PrintWriter
 * @throws ServletException
 * @throws IOException
 */
static String getFooter () 
{
   return("Rasika Mohod &amp; Jeff Offutt \nJanurary 2019");  
}

/**
 * Print a form to get the courseID
 * @param out PrintWriter
 * @throws ServletException
 * @throws IOException
*/

} // end servletUtils class
