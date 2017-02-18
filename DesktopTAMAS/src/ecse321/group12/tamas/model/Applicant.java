/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;
import java.util.*;

// line 9 "../../../../TAMASmodel.ump"
public class Applicant extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Applicant Attributes
  private String cGPA;
  private String skills;
  private boolean isGraduate;

  //Applicant Associations
  private List<Application> applications;
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Applicant(String aName, String aId, String aCGPA, String aSkills, boolean aIsGraduate)
  {
    super(aName, aId);
    cGPA = aCGPA;
    skills = aSkills;
    isGraduate = aIsGraduate;
    applications = new ArrayList<Application>();
    assignments = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCGPA(String aCGPA)
  {
    boolean wasSet = false;
    cGPA = aCGPA;
    wasSet = true;
    return wasSet;
  }

  public boolean setSkills(String aSkills)
  {
    boolean wasSet = false;
    skills = aSkills;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsGraduate(boolean aIsGraduate)
  {
    boolean wasSet = false;
    isGraduate = aIsGraduate;
    wasSet = true;
    return wasSet;
  }

  public String getCGPA()
  {
    return cGPA;
  }

  public String getSkills()
  {
    return skills;
  }

  public boolean getIsGraduate()
  {
    return isGraduate;
  }

  public boolean isIsGraduate()
  {
    return isGraduate;
  }

  public Application getApplication(int index)
  {
    Application aApplication = applications.get(index);
    return aApplication;
  }

  public List<Application> getApplications()
  {
    List<Application> newApplications = Collections.unmodifiableList(applications);
    return newApplications;
  }

  public int numberOfApplications()
  {
    int number = applications.size();
    return number;
  }

  public boolean hasApplications()
  {
    boolean has = applications.size() > 0;
    return has;
  }

  public int indexOfApplication(Application aApplication)
  {
    int index = applications.indexOf(aApplication);
    return index;
  }

  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }

  public static int minimumNumberOfApplications()
  {
    return 0;
  }

  public static int maximumNumberOfApplications()
  {
    return 3;
  }

  public Application addApplication(boolean aIsAccepted, String aExperience, String aCourseGPA, Job aJob)
  {
    if (numberOfApplications() >= maximumNumberOfApplications())
    {
      return null;
    }
    else
    {
      return new Application(aIsAccepted, aExperience, aCourseGPA, this, aJob);
    }
  }

  public boolean addApplication(Application aApplication)
  {
    boolean wasAdded = false;
    if (applications.contains(aApplication)) { return false; }
    if (numberOfApplications() >= maximumNumberOfApplications())
    {
      return wasAdded;
    }

    Applicant existingApplicant = aApplication.getApplicant();
    boolean isNewApplicant = existingApplicant != null && !this.equals(existingApplicant);
    if (isNewApplicant)
    {
      aApplication.setApplicant(this);
    }
    else
    {
      applications.add(aApplication);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeApplication(Application aApplication)
  {
    boolean wasRemoved = false;
    //Unable to remove aApplication, as it must always have a applicant
    if (!this.equals(aApplication.getApplicant()))
    {
      applications.remove(aApplication);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addApplicationAt(Application aApplication, int index)
  {  
    boolean wasAdded = false;
    if(addApplication(aApplication))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplications()) { index = numberOfApplications() - 1; }
      applications.remove(aApplication);
      applications.add(index, aApplication);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveApplicationAt(Application aApplication, int index)
  {
    boolean wasAdded = false;
    if(applications.contains(aApplication))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfApplications()) { index = numberOfApplications() - 1; }
      applications.remove(aApplication);
      applications.add(index, aApplication);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addApplicationAt(aApplication, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfAssignments()
  {
    return 0;
  }

  public static int maximumNumberOfAssignments()
  {
    return 3;
  }

  public Assignment addAssignment(String aFeedback, Job aJob)
  {
    if (numberOfAssignments() >= maximumNumberOfAssignments())
    {
      return null;
    }
    else
    {
      return new Assignment(aFeedback, this, aJob);
    }
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    if (numberOfAssignments() >= maximumNumberOfAssignments())
    {
      return wasAdded;
    }

    Applicant existingApplicant = aAssignment.getApplicant();
    boolean isNewApplicant = existingApplicant != null && !this.equals(existingApplicant);
    if (isNewApplicant)
    {
      aAssignment.setApplicant(this);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignment, as it must always have a applicant
    if (!this.equals(aAssignment.getApplicant()))
    {
      assignments.remove(aAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (applications.size() > 0)
    {
      Application aApplication = applications.get(applications.size() - 1);
      aApplication.delete();
      applications.remove(aApplication);
    }
    
    while (assignments.size() > 0)
    {
      Assignment aAssignment = assignments.get(assignments.size() - 1);
      aAssignment.delete();
      assignments.remove(aAssignment);
    }
    
    super.delete();
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "cGPA" + ":" + getCGPA()+ "," +
            "skills" + ":" + getSkills()+ "," +
            "isGraduate" + ":" + getIsGraduate()+ "]"
     + outputString;
  }
}