/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;
import java.sql.Date;

// line 25 "../../../../TAMASmodel.ump"
public class Department extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Department Attributes
  private Date deadline;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Department(String aName, String aId, Date aDeadline)
  {
    super(aName, aId);
    deadline = aDeadline;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDeadline(Date aDeadline)
  {
    boolean wasSet = false;
    deadline = aDeadline;
    wasSet = true;
    return wasSet;
  }

  public Date getDeadline()
  {
    return deadline;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deadline" + "=" + (getDeadline() != null ? !getDeadline().equals(this)  ? getDeadline().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}