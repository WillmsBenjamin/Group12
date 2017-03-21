/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;
import java.sql.Date;
import java.util.*;

// line 47 "../../../../TAMASmodel.ump"
public class Job
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Job Attributes
  private int maxHours;
  private double wage;
  private Date deadline;
  private boolean isApproved;
  private String requiredSkills;
  private String requiredCourseGPA;
  private String requiredCGPA;
  private String requiredExperience;

  //Job Associations
  private Assignment assignment;
  private List<Application> applications;
  private Course course;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Job(int aMaxHours, double aWage, Date aDeadline, boolean aIsApproved, String aRequiredSkills, String aRequiredCourseGPA, String aRequiredCGPA, String aRequiredExperience, Course aCourse)
  {
    maxHours = aMaxHours;
    wage = aWage;
    deadline = aDeadline;
    isApproved = aIsApproved;
    requiredSkills = aRequiredSkills;
    requiredCourseGPA = aRequiredCourseGPA;
    requiredCGPA = aRequiredCGPA;
    requiredExperience = aRequiredExperience;
    applications = new ArrayList<Application>();
    boolean didAddCourse = setCourse(aCourse);
    if (!didAddCourse)
    {
      throw new RuntimeException("Unable to create job due to course");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMaxHours(int aMaxHours)
  {
    boolean wasSet = false;
    maxHours = aMaxHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setWage(double aWage)
  {
    boolean wasSet = false;
    wage = aWage;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeadline(Date aDeadline)
  {
    boolean wasSet = false;
    deadline = aDeadline;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsApproved(boolean aIsApproved)
  {
    boolean wasSet = false;
    isApproved = aIsApproved;
    wasSet = true;
    return wasSet;
  }

  public boolean setRequiredSkills(String aRequiredSkills)
  {
    boolean wasSet = false;
    requiredSkills = aRequiredSkills;
    wasSet = true;
    return wasSet;
  }

  public boolean setRequiredCourseGPA(String aRequiredCourseGPA)
  {
    boolean wasSet = false;
    requiredCourseGPA = aRequiredCourseGPA;
    wasSet = true;
    return wasSet;
  }

  public boolean setRequiredCGPA(String aRequiredCGPA)
  {
    boolean wasSet = false;
    requiredCGPA = aRequiredCGPA;
    wasSet = true;
    return wasSet;
  }

  public boolean setRequiredExperience(String aRequiredExperience)
  {
    boolean wasSet = false;
    requiredExperience = aRequiredExperience;
    wasSet = true;
    return wasSet;
  }

  public int getMaxHours()
  {
    return maxHours;
  }

  public double getWage()
  {
    return wage;
  }

  public Date getDeadline()
  {
    return deadline;
  }

  public boolean getIsApproved()
  {
    return isApproved;
  }

  public String getRequiredSkills()
  {
    return requiredSkills;
  }

  public String getRequiredCourseGPA()
  {
    return requiredCourseGPA;
  }

  public String getRequiredCGPA()
  {
    return requiredCGPA;
  }

  public String getRequiredExperience()
  {
    return requiredExperience;
  }

  public boolean isIsApproved()
  {
    return isApproved;
  }

  public Assignment getAssignment()
  {
    return assignment;
  }

  public boolean hasAssignment()
  {
    boolean has = assignment != null;
    return has;
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

  public Course getCourse()
  {
    return course;
  }

  public boolean setAssignment(Assignment aNewAssignment)
  {
    boolean wasSet = false;
    if (assignment != null && !assignment.equals(aNewAssignment) && equals(assignment.getJob()))
    {
      //Unable to setAssignment, as existing assignment would become an orphan
      return wasSet;
    }

    assignment = aNewAssignment;
    Job anOldJob = aNewAssignment != null ? aNewAssignment.getJob() : null;

    if (!this.equals(anOldJob))
    {
      if (anOldJob != null)
      {
        anOldJob.assignment = null;
      }
      if (assignment != null)
      {
        assignment.setJob(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public static int minimumNumberOfApplications()
  {
    return 0;
  }

  public Application addApplication(boolean aIsAccepted, boolean aIsOffered, String aExperience, String aCourseGPA, Applicant aApplicant)
  {
    return new Application(aIsAccepted, aIsOffered, aExperience, aCourseGPA, aApplicant, this);
  }

  public boolean addApplication(Application aApplication)
  {
    boolean wasAdded = false;
    if (applications.contains(aApplication)) { return false; }
    if (applications.contains(aApplication)) { return false; }
    if (applications.contains(aApplication)) { return false; }
    Job existingJob = aApplication.getJob();
    boolean isNewJob = existingJob != null && !this.equals(existingJob);
    if (isNewJob)
    {
      aApplication.setJob(this);
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
    //Unable to remove aApplication, as it must always have a job
    if (!this.equals(aApplication.getJob()))
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

  public boolean setCourse(Course aCourse)
  {
    boolean wasSet = false;
    if (aCourse == null)
    {
      return wasSet;
    }

    Course existingCourse = course;
    course = aCourse;
    if (existingCourse != null && !existingCourse.equals(aCourse))
    {
      existingCourse.removeJob(this);
    }
    course.addJob(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Assignment existingAssignment = assignment;
    assignment = null;
    if (existingAssignment != null)
    {
      existingAssignment.delete();
      existingAssignment.setJob(null);
    }
    while (applications.size() > 0)
    {
      Application aApplication = applications.get(applications.size() - 1);
      aApplication.delete();
      applications.remove(aApplication);
    }
    
    Course placeholderCourse = course;
    this.course = null;
    placeholderCourse.removeJob(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "maxHours" + ":" + getMaxHours()+ "," +
            "wage" + ":" + getWage()+ "," +
            "isApproved" + ":" + getIsApproved()+ "," +
            "requiredSkills" + ":" + getRequiredSkills()+ "," +
            "requiredCourseGPA" + ":" + getRequiredCourseGPA()+ "," +
            "requiredCGPA" + ":" + getRequiredCGPA()+ "," +
            "requiredExperience" + ":" + getRequiredExperience()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deadline" + "=" + (getDeadline() != null ? !getDeadline().equals(this)  ? getDeadline().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "assignment = "+(getAssignment()!=null?Integer.toHexString(System.identityHashCode(getAssignment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "course = "+(getCourse()!=null?Integer.toHexString(System.identityHashCode(getCourse())):"null")
     + outputString;
  }
}