/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-dab6b48 modeling language!*/

package ecse321.group12.tamas.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 20 "../../../../TAMASmodel.ump"
public class Instructor extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Associations
  private List<Hours> contactTimes;
  private List<Course> courses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Instructor(String aName, String aId)
  {
    super(aName, aId);
    contactTimes = new ArrayList<Hours>();
    courses = new ArrayList<Course>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public Course getCourse(int index)
  {
    Course aCourse = courses.get(index);
    return aCourse;
  }

  public List<Course> getCourses()
  {
    List<Course> newCourses = Collections.unmodifiableList(courses);
    return newCourses;
  }

  public int numberOfCourses()
  {
    int number = courses.size();
    return number;
  }

  public boolean hasCourses()
  {
    boolean has = courses.size() > 0;
    return has;
  }

  public int indexOfCourse(Course aCourse)
  {
    int index = courses.indexOf(aCourse);
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
    Instructor existingInstructor = aContactTime.getInstructor();
    boolean isNewInstructor = existingInstructor != null && !this.equals(existingInstructor);
    if (isNewInstructor)
    {
      aContactTime.setInstructor(this);
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
    //Unable to remove aContactTime, as it must always have a instructor
    if (!this.equals(aContactTime.getInstructor()))
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

  public static int minimumNumberOfCourses()
  {
    return 0;
  }

  public boolean addCourse(Course aCourse)
  {
    boolean wasAdded = false;
    if (courses.contains(aCourse)) { return false; }
    courses.add(aCourse);
    if (aCourse.indexOfInstructor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCourse.addInstructor(this);
      if (!wasAdded)
      {
        courses.remove(aCourse);
      }
    }
    return wasAdded;
  }

  public boolean removeCourse(Course aCourse)
  {
    boolean wasRemoved = false;
    if (!courses.contains(aCourse))
    {
      return wasRemoved;
    }

    int oldIndex = courses.indexOf(aCourse);
    courses.remove(oldIndex);
    if (aCourse.indexOfInstructor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCourse.removeInstructor(this);
      if (!wasRemoved)
      {
        courses.add(oldIndex,aCourse);
      }
    }
    return wasRemoved;
  }

  public boolean addCourseAt(Course aCourse, int index)
  {  
    boolean wasAdded = false;
    if(addCourse(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCourseAt(Course aCourse, int index)
  {
    boolean wasAdded = false;
    if(courses.contains(aCourse))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCourses()) { index = numberOfCourses() - 1; }
      courses.remove(aCourse);
      courses.add(index, aCourse);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCourseAt(aCourse, index);
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
    
    ArrayList<Course> copyOfCourses = new ArrayList<Course>(courses);
    courses.clear();
    for(Course aCourse : copyOfCourses)
    {
      aCourse.removeInstructor(this);
    }
    super.delete();
  }

}