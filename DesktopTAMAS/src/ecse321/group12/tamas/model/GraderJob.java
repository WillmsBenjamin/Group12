/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;
import java.sql.Date;
import java.util.*;

// line 66 "../../../../TAMASmodel.ump"
public class GraderJob extends Job
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GraderJob(int aMaxHours, double aWage, Date aDeadline, String aRequiredSkills, String aRequiredCourseGPA, String aRequiredCGPA, String aRequiredExperience, Course aCourse)
  {
    super(aMaxHours, aWage, aDeadline, aRequiredSkills, aRequiredCourseGPA, aRequiredCGPA, aRequiredExperience, aCourse);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}