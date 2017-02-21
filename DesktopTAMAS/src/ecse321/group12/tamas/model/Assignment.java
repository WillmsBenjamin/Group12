/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;

// line 32 "../../../../TAMASmodel.ump"
public class Assignment
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private String feedback;

  //Autounique Attributes
  private int id;

  //Assignment Associations
  private Applicant applicant;
  private Job job;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Assignment(String aFeedback, Applicant aApplicant, Job aJob)
  {
    feedback = aFeedback;
    id = nextId++;
    boolean didAddApplicant = setApplicant(aApplicant);
    if (!didAddApplicant)
    {
      throw new RuntimeException("Unable to create assignment due to applicant");
    }
    boolean didAddJob = setJob(aJob);
    if (!didAddJob)
    {
      throw new RuntimeException("Unable to create assignment due to job");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFeedback(String aFeedback)
  {
    boolean wasSet = false;
    feedback = aFeedback;
    wasSet = true;
    return wasSet;
  }

  public String getFeedback()
  {
    return feedback;
  }

  public int getId()
  {
    return id;
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
    //Must provide applicant to assignment
    if (aApplicant == null)
    {
      return wasSet;
    }

    //applicant already at maximum (3)
    if (aApplicant.numberOfAssignments() >= Applicant.maximumNumberOfAssignments())
    {
      return wasSet;
    }
    
    Applicant existingApplicant = applicant;
    applicant = aApplicant;
    if (existingApplicant != null && !existingApplicant.equals(aApplicant))
    {
      boolean didRemove = existingApplicant.removeAssignment(this);
      if (!didRemove)
      {
        applicant = existingApplicant;
        return wasSet;
      }
    }
    applicant.addAssignment(this);
    wasSet = true;
    return wasSet;
  }

  public boolean setJob(Job aNewJob)
  {
    boolean wasSet = false;
    if (aNewJob == null)
    {
      //Unable to setJob to null, as assignment must always be associated to a job
      return wasSet;
    }
    
    Assignment existingAssignment = aNewJob.getAssignment();
    if (existingAssignment != null && !equals(existingAssignment))
    {
      //Unable to setJob, the current job already has a assignment, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Job anOldJob = job;
    job = aNewJob;
    job.setAssignment(this);

    if (anOldJob != null)
    {
      anOldJob.setAssignment(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Applicant placeholderApplicant = applicant;
    this.applicant = null;
    placeholderApplicant.removeAssignment(this);
    Job existingJob = job;
    job = null;
    if (existingJob != null)
    {
      existingJob.setAssignment(null);
    }
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "feedback" + ":" + getFeedback()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "applicant = "+(getApplicant()!=null?Integer.toHexString(System.identityHashCode(getApplicant())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "job = "+(getJob()!=null?Integer.toHexString(System.identityHashCode(getJob())):"null")
     + outputString;
  }
}