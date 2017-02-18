/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 69 "../../../../TAMASmodel.ump"
public class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private String name;
  private int numTutorialSections;
  private int numLabSections;

  //Course Associations
  private List<Hours> contactTimes;
  private List<Instructor> instructors;
  private List<Job> jobs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Course(String aName, int aNumTutorialSections, int aNumLabSections)
  {
    name = aName;
    numTutorialSections = aNumTutorialSections;
    numLabSections = aNumLabSections;
    contactTimes = new ArrayList<Hours>();
    instructors = new ArrayList<Instructor>();
    jobs = new ArrayList<Job>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumTutorialSections(int aNumTutorialSections)
  {
    boolean wasSet = false;
    numTutorialSections = aNumTutorialSections;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumLabSections(int aNumLabSections)
  {
    boolean wasSet = false;
    numLabSections = aNumLabSections;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getNumTutorialSections()
  {
    return numTutorialSections;
  }

  public int getNumLabSections()
  {
    return numLabSections;
  }

  public Hours getContactTime(int index)
  {
    Hours aContactTime = contactTimes.get(index);
    return aContactTime;
  }

  public List<Hours> getContactTimes()
  {
    List<Hours> newContactTimes = Collections.unmodifiableList(contactTimes);
    return newContactTimes;
  }

  public int numberOfContactTimes()
  {
    int number = contactTimes.size();
    return number;
  }

  public boolean hasContactTimes()
  {
    boolean has = contactTimes.size() > 0;
    return has;
  }

  public int indexOfContactTime(Hours aContactTime)
  {
    int index = contactTimes.indexOf(aContactTime);
    return index;
  }

  public Instructor getInstructor(int index)
  {
    Instructor aInstructor = instructors.get(index);
    return aInstructor;
  }

  public List<Instructor> getInstructors()
  {
    List<Instructor> newInstructors = Collections.unmodifiableList(instructors);
    return newInstructors;
  }

  public int numberOfInstructors()
  {
    int number = instructors.size();
    return number;
  }

  public boolean hasInstructors()
  {
    boolean has = instructors.size() > 0;
    return has;
  }

  public int indexOfInstructor(Instructor aInstructor)
  {
    int index = instructors.indexOf(aInstructor);
    return index;
  }

  public Job getJob(int index)
  {
    Job aJob = jobs.get(index);
    return aJob;
  }

  public List<Job> getJobs()
  {
    List<Job> newJobs = Collections.unmodifiableList(jobs);
    return newJobs;
  }

  public int numberOfJobs()
  {
    int number = jobs.size();
    return number;
  }

  public boolean hasJobs()
  {
    boolean has = jobs.size() > 0;
    return has;
  }

  public int indexOfJob(Job aJob)
  {
    int index = jobs.indexOf(aJob);
    return index;
  }

  public static int minimumNumberOfContactTimes()
  {
    return 0;
  }

  public Hours addContactTime(Date aDate, Time aStartTime, Time aEndTime)
  {
    return new Hours(aDate, aStartTime, aEndTime, this);
  }

  public boolean addContactTime(Hours aContactTime)
  {
    boolean wasAdded = false;
    if (contactTimes.contains(aContactTime)) { return false; }
    Course existingCourse = aContactTime.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aContactTime.setCourse(this);
    }
    else
    {
      contactTimes.add(aContactTime);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeContactTime(Hours aContactTime)
  {
    boolean wasRemoved = false;
    //Unable to remove aContactTime, as it must always have a course
    if (!this.equals(aContactTime.getCourse()))
    {
      contactTimes.remove(aContactTime);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addContactTimeAt(Hours aContactTime, int index)
  {  
    boolean wasAdded = false;
    if(addContactTime(aContactTime))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContactTimes()) { index = numberOfContactTimes() - 1; }
      contactTimes.remove(aContactTime);
      contactTimes.add(index, aContactTime);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContactTimeAt(Hours aContactTime, int index)
  {
    boolean wasAdded = false;
    if(contactTimes.contains(aContactTime))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContactTimes()) { index = numberOfContactTimes() - 1; }
      contactTimes.remove(aContactTime);
      contactTimes.add(index, aContactTime);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContactTimeAt(aContactTime, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfInstructors()
  {
    return 0;
  }

  public static int maximumNumberOfInstructors()
  {
    return 4;
  }

  public boolean addInstructor(Instructor aInstructor)
  {
    boolean wasAdded = false;
    if (instructors.contains(aInstructor)) { return false; }
    if (numberOfInstructors() >= maximumNumberOfInstructors())
    {
      return wasAdded;
    }

    instructors.add(aInstructor);
    if (aInstructor.indexOfCourse(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aInstructor.addCourse(this);
      if (!wasAdded)
      {
        instructors.remove(aInstructor);
      }
    }
    return wasAdded;
  }

  public boolean removeInstructor(Instructor aInstructor)
  {
    boolean wasRemoved = false;
    if (!instructors.contains(aInstructor))
    {
      return wasRemoved;
    }

    int oldIndex = instructors.indexOf(aInstructor);
    instructors.remove(oldIndex);
    if (aInstructor.indexOfCourse(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aInstructor.removeCourse(this);
      if (!wasRemoved)
      {
        instructors.add(oldIndex,aInstructor);
      }
    }
    return wasRemoved;
  }

  public boolean setInstructors(Instructor... newInstructors)
  {
    boolean wasSet = false;
    ArrayList<Instructor> verifiedInstructors = new ArrayList<Instructor>();
    for (Instructor aInstructor : newInstructors)
    {
      if (verifiedInstructors.contains(aInstructor))
      {
        continue;
      }
      verifiedInstructors.add(aInstructor);
    }

    if (verifiedInstructors.size() != newInstructors.length || verifiedInstructors.size() > maximumNumberOfInstructors())
    {
      return wasSet;
    }

    ArrayList<Instructor> oldInstructors = new ArrayList<Instructor>(instructors);
    instructors.clear();
    for (Instructor aNewInstructor : verifiedInstructors)
    {
      instructors.add(aNewInstructor);
      if (oldInstructors.contains(aNewInstructor))
      {
        oldInstructors.remove(aNewInstructor);
      }
      else
      {
        aNewInstructor.addCourse(this);
      }
    }

    for (Instructor anOldInstructor : oldInstructors)
    {
      anOldInstructor.removeCourse(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addInstructorAt(Instructor aInstructor, int index)
  {  
    boolean wasAdded = false;
    if(addInstructor(aInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInstructors()) { index = numberOfInstructors() - 1; }
      instructors.remove(aInstructor);
      instructors.add(index, aInstructor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveInstructorAt(Instructor aInstructor, int index)
  {
    boolean wasAdded = false;
    if(instructors.contains(aInstructor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfInstructors()) { index = numberOfInstructors() - 1; }
      instructors.remove(aInstructor);
      instructors.add(index, aInstructor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addInstructorAt(aInstructor, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfJobs()
  {
    return 0;
  }

  public Job addJob(int aMaxHours, int aWage, Date aDeadline, String aRequiredSkills, String aRequiredCourseGPA, String aRequiredCGPA, String aRequiredExperience)
  {
    return new Job(aMaxHours, aWage, aDeadline, aRequiredSkills, aRequiredCourseGPA, aRequiredCGPA, aRequiredExperience, this);
  }

  public boolean addJob(Job aJob)
  {
    boolean wasAdded = false;
    if (jobs.contains(aJob)) { return false; }
    Course existingCourse = aJob.getCourse();
    boolean isNewCourse = existingCourse != null && !this.equals(existingCourse);
    if (isNewCourse)
    {
      aJob.setCourse(this);
    }
    else
    {
      jobs.add(aJob);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeJob(Job aJob)
  {
    boolean wasRemoved = false;
    //Unable to remove aJob, as it must always have a course
    if (!this.equals(aJob.getCourse()))
    {
      jobs.remove(aJob);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addJobAt(Job aJob, int index)
  {  
    boolean wasAdded = false;
    if(addJob(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveJobAt(Job aJob, int index)
  {
    boolean wasAdded = false;
    if(jobs.contains(aJob))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfJobs()) { index = numberOfJobs() - 1; }
      jobs.remove(aJob);
      jobs.add(index, aJob);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addJobAt(aJob, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (contactTimes.size() > 0)
    {
      Hours aContactTime = contactTimes.get(contactTimes.size() - 1);
      aContactTime.delete();
      contactTimes.remove(aContactTime);
    }
    
    ArrayList<Instructor> copyOfInstructors = new ArrayList<Instructor>(instructors);
    instructors.clear();
    for(Instructor aInstructor : copyOfInstructors)
    {
      aInstructor.removeCourse(this);
    }
    while (jobs.size() > 0)
    {
      Job aJob = jobs.get(jobs.size() - 1);
      aJob.delete();
      jobs.remove(aJob);
    }
    
  }


  public String toString()
  {
    String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "numTutorialSections" + ":" + getNumTutorialSections()+ "," +
            "numLabSections" + ":" + getNumLabSections()+ "]"
     + outputString;
  }
}