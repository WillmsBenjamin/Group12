/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;

// line 37 "../../../../TAMASmodel.ump"
public class Application
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application Attributes
  private boolean isAccepted;
  private boolean isOffered;
  private String experience;
  private String courseGPA;

  //Autounique Attributes
  private int id;

  //Application Associations
  private Applicant applicant;
  private Job job;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Application(boolean aIsAccepted, boolean aIsOffered, String aExperience, String aCourseGPA, Applicant aApplicant, Job aJob)
  {
    isAccepted = aIsAccepted;
    isOffered = aIsOffered;
    experience = aExperience;
    courseGPA = aCourseGPA;
    id = nextId++;
    boolean didAddApplicant = setApplicant(aApplicant);
    if (!didAddApplicant)
    {
      throw new RuntimeException("Unable to create application due to applicant");
    }
    boolean didAddJob = setJob(aJob);
    if (!didAddJob)
    {
      throw new RuntimeException("Unable to create application due to job");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsAccepted(boolean aIsAccepted)
  {
    boolean wasSet = false;
    isAccepted = aIsAccepted;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsOffered(boolean aIsOffered)
  {
    boolean wasSet = false;
    isOffered = aIsOffered;
    wasSet = true;
    return wasSet;
  }

  public boolean setExperience(String aExperience)
  {
    boolean wasSet = false;
    experience = aExperience;
    wasSet = true;
    return wasSet;
  }

  public boolean setCourseGPA(String aCourseGPA)
  {
    boolean wasSet = false;
    courseGPA = aCourseGPA;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsAccepted()
  {
    return isAccepted;
  }

  public boolean getIsOffered()
  {
    return isOffered;
  }

  public String getExperience()
  {
    return experience;
  }

  public String getCourseGPA()
  {
    return courseGPA;
  }

  public int getId()
  {
    return id;
  }

  public boolean isIsAccepted()
  {
    return isAccepted;
  }

  public boolean isIsOffered()
  {
    return isOffered;
  }

  public Applicant getApplicant()
  {
    return applicant;
  }

  public Job getJob()
  {
    return job;
  }

  public boolean setApplicant(Applicant aApplicant)
  {
    boolean wasSet = false;
    //Must provide applicant to application
    if (aApplicant == null)
    {
      return wasSet;
    }

    //applicant already at maximum (3)
    if (aApplicant.numberOfApplications() >= Applicant.maximumNumberOfApplications())
    {
      return wasSet;
    }
    
    Applicant existingApplicant = applicant;
    applicant = aApplicant;
    if (existingApplicant != null && !existingApplicant.equals(aApplicant))
    {
      boolean didRemove = existingApplicant.removeApplication(this);
      if (!didRemove)
      {
        applicant = existingApplicant;
        return wasSet;
      }
    }
    applicant.addApplication(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setJob(Job aJob)
  {
    boolean wasSet = false;
    if (aJob == null)
    {
      return wasSet;
    }

    Job existingJob = job;
    job = aJob;
    if (existingJob != null && !existingJob.equals(aJob))
    {
      existingJob.removeApplication(this);
    }
    job.addApplication(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Applicant placeholderApplicant = applicant;
    this.applicant = null;
    placeholderApplicant.removeApplication(this);
    Job placeholderJob = job;
    this.job = null;
    placeholderJob.removeApplication(this);
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "isAccepted" + ":" + getIsAccepted()+ "," +
            "isOffered" + ":" + getIsOffered()+ "," +
            "experience" + ":" + getExperience()+ "," +
            "courseGPA" + ":" + getCourseGPA()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "applicant = "+(getApplicant()!=null?Integer.toHexString(System.identityHashCode(getApplicant())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "job = "+(getJob()!=null?Integer.toHexString(System.identityHashCode(getJob())):"null")
     + outputString;
  }
}