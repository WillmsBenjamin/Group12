/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;
import java.sql.Date;
import java.util.*;

// line 57 "../../../../TAMASmodel.ump"
public class TAjob extends Job
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TAjob Attributes
  private int minHours;
  private boolean isLab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TAjob(int aMaxHours, int aWage, Date aDeadline, String aRequiredSkills, String aRequiredCourseGPA, String aRequiredCGPA, String aRequiredExperience, Course aCourse, int aMinHours, boolean aIsLab)
  {
    super(aMaxHours, aWage, aDeadline, aRequiredSkills, aRequiredCourseGPA, aRequiredCGPA, aRequiredExperience, aCourse);
    minHours = aMinHours;
    isLab = aIsLab;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMinHours(int aMinHours)
  {
    boolean wasSet = false;
    minHours = aMinHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsLab(boolean aIsLab)
  {
    boolean wasSet = false;
    isLab = aIsLab;
    wasSet = true;
    return wasSet;
  }

  public int getMinHours()
  {
    return minHours;
  }

  public boolean getIsLab()
  {
    return isLab;
  }

  public boolean isIsLab()
  {
    return isLab;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "minHours" + ":" + getMinHours()+ "," +
            "isLab" + ":" + getIsLab()+ "]"
     + outputString;
  }
}