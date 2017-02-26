<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/



class Instructor extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Instructor Associations
  private $contactTimes;
  private $courses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aId)
  {
    parent::__construct($aName, $aId);
    $this->contactTimes = array();
    $this->courses = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getContactTime_index($index)
  {
    $aContactTime = $this->contactTimes[$index];
    return $aContactTime;
  }

  public function getContactTimes()
  {
    $newContactTimes = $this->contactTimes;
    return $newContactTimes;
  }

  public function numberOfContactTimes()
  {
    $number = count($this->contactTimes);
    return $number;
  }

  public function hasContactTimes()
  {
    $has = $this->numberOfContactTimes() > 0;
    return $has;
  }

  public function indexOfContactTime($aContactTime)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->contactTimes as $contactTime)
    {
      if ($contactTime->equals($aContactTime))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getCourse_index($index)
  {
    $aCourse = $this->courses[$index];
    return $aCourse;
  }

  public function getCourses()
  {
    $newCourses = $this->courses;
    return $newCourses;
  }

  public function numberOfCourses()
  {
    $number = count($this->courses);
    return $number;
  }

  public function hasCourses()
  {
    $has = $this->numberOfCourses() > 0;
    return $has;
  }

  public function indexOfCourse($aCourse)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->courses as $course)
    {
      if ($course->equals($aCourse))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfContactTimes()
  {
    return 0;
  }

  public function addContactTimeVia($aDate, $aStartTime, $aEndTime)
  {
    return new Hours($aDate, $aStartTime, $aEndTime, $this);
  }

  public function addContactTime($aContactTime)
  {
    $wasAdded = false;
    if ($this->indexOfContactTime($aContactTime) !== -1) { return false; }
    $existingInstructor = $aContactTime->getInstructor();
    $isNewInstructor = $existingInstructor != null && $this !== $existingInstructor;
    if ($isNewInstructor)
    {
      $aContactTime->setInstructor($this);
    }
    else
    {
      $this->contactTimes[] = $aContactTime;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeContactTime($aContactTime)
  {
    $wasRemoved = false;
    //Unable to remove aContactTime, as it must always have a instructor
    if ($this !== $aContactTime->getInstructor())
    {
      unset($this->contactTimes[$this->indexOfContactTime($aContactTime)]);
      $this->contactTimes = array_values($this->contactTimes);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addContactTimeAt($aContactTime, $index)
  {  
    $wasAdded = false;
    if($this->addContactTime($aContactTime))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfContactTimes()) { $index = $this->numberOfContactTimes() - 1; }
      array_splice($this->contactTimes, $this->indexOfContactTime($aContactTime), 1);
      array_splice($this->contactTimes, $index, 0, array($aContactTime));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveContactTimeAt($aContactTime, $index)
  {
    $wasAdded = false;
    if($this->indexOfContactTime($aContactTime) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfContactTimes()) { $index = $this->numberOfContactTimes() - 1; }
      array_splice($this->contactTimes, $this->indexOfContactTime($aContactTime), 1);
      array_splice($this->contactTimes, $index, 0, array($aContactTime));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addContactTimeAt($aContactTime, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfCourses()
  {
    return 0;
  }

  public function addCourse($aCourse)
  {
    $wasAdded = false;
    if ($this->indexOfCourse($aCourse) !== -1) { return false; }
    $this->courses[] = $aCourse;
    if ($aCourse->indexOfInstructor($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aCourse->addInstructor($this);
      if (!$wasAdded)
      {
        array_pop($this->courses);
      }
    }
    return $wasAdded;
  }

  public function removeCourse($aCourse)
  {
    $wasRemoved = false;
    if ($this->indexOfCourse($aCourse) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfCourse($aCourse);
    unset($this->courses[$oldIndex]);
    if ($aCourse->indexOfInstructor($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aCourse->removeInstructor($this);
      if (!$wasRemoved)
      {
        $this->courses[$oldIndex] = $aCourse;
        ksort($this->courses);
      }
    }
    $this->courses = array_values($this->courses);
    return $wasRemoved;
  }

  public function addCourseAt($aCourse, $index)
  {  
    $wasAdded = false;
    if($this->addCourse($aCourse))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveCourseAt($aCourse, $index)
  {
    $wasAdded = false;
    if($this->indexOfCourse($aCourse) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfCourses()) { $index = $this->numberOfCourses() - 1; }
      array_splice($this->courses, $this->indexOfCourse($aCourse), 1);
      array_splice($this->courses, $index, 0, array($aCourse));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addCourseAt($aCourse, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->contactTimes) > 0)
    {
      $aContactTime = $this->contactTimes[count($this->contactTimes) - 1];
      $aContactTime->delete();
      unset($this->contactTimes[$this->indexOfContactTime($aContactTime)]);
      $this->contactTimes = array_values($this->contactTimes);
    }
    
    $copyOfCourses = $this->courses;
    $this->courses = array();
    foreach ($copyOfCourses as $aCourse)
    {
      $aCourse->removeInstructor($this);
    }
    parent::delete();
  }

}
?>