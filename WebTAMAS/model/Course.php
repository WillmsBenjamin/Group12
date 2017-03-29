<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Course
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Course Attributes
  private $name;
  private $numTutorialSections;
  private $numLabSections;
  private $numStudents;
  private $budget;

  //Course Associations
  private $instructors;
  private $jobs;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aNumTutorialSections, $aNumLabSections, $aNumStudents, $aBudget)
  {
    $this->name = $aName;
    $this->numTutorialSections = $aNumTutorialSections;
    $this->numLabSections = $aNumLabSections;
    $this->numStudents = $aNumStudents;
    $this->budget = $aBudget;
    $this->instructors = array();
    $this->jobs = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumTutorialSections($aNumTutorialSections)
  {
    $wasSet = false;
    $this->numTutorialSections = $aNumTutorialSections;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumLabSections($aNumLabSections)
  {
    $wasSet = false;
    $this->numLabSections = $aNumLabSections;
    $wasSet = true;
    return $wasSet;
  }

  public function setNumStudents($aNumStudents)
  {
    $wasSet = false;
    $this->numStudents = $aNumStudents;
    $wasSet = true;
    return $wasSet;
  }

  public function setBudget($aBudget)
  {
    $wasSet = false;
    $this->budget = $aBudget;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getNumTutorialSections()
  {
    return $this->numTutorialSections;
  }

  public function getNumLabSections()
  {
    return $this->numLabSections;
  }

  public function getNumStudents()
  {
    return $this->numStudents;
  }

  public function getBudget()
  {
    return $this->budget;
  }

  public function getInstructor_index($index)
  {
    $aInstructor = $this->instructors[$index];
    return $aInstructor;
  }

  public function getInstructors()
  {
    $newInstructors = $this->instructors;
    return $newInstructors;
  }

  public function numberOfInstructors()
  {
    $number = count($this->instructors);
    return $number;
  }

  public function hasInstructors()
  {
    $has = $this->numberOfInstructors() > 0;
    return $has;
  }

  public function indexOfInstructor($aInstructor)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->instructors as $instructor)
    {
      if ($instructor->equals($aInstructor))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getJob_index($index)
  {
    $aJob = $this->jobs[$index];
    return $aJob;
  }

  public function getJobs()
  {
    $newJobs = $this->jobs;
    return $newJobs;
  }

  public function numberOfJobs()
  {
    $number = count($this->jobs);
    return $number;
  }

  public function hasJobs()
  {
    $has = $this->numberOfJobs() > 0;
    return $has;
  }

  public function indexOfJob($aJob)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->jobs as $job)
    {
      if ($job->equals($aJob))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfInstructors()
  {
    return 0;
  }

  public static function maximumNumberOfInstructors()
  {
    return 4;
  }

  public function addInstructor($aInstructor)
  {
    $wasAdded = false;
    if ($this->indexOfInstructor($aInstructor) !== -1) { return false; }
    if ($this->numberOfInstructors() >= self::maximumNumberOfInstructors())
    {
      return $wasAdded;
    }

    $this->instructors[] = $aInstructor;
    if ($aInstructor->indexOfCourse($this) != -1)
    {
      $wasAdded = true;
    }
    else
    {
      $wasAdded = $aInstructor->addCourse($this);
      if (!$wasAdded)
      {
        array_pop($this->instructors);
      }
    }
    return $wasAdded;
  }

  public function removeInstructor($aInstructor)
  {
    $wasRemoved = false;
    if ($this->indexOfInstructor($aInstructor) == -1)
    {
      return $wasRemoved;
    }

    $oldIndex = $this->indexOfInstructor($aInstructor);
    unset($this->instructors[$oldIndex]);
    if ($aInstructor->indexOfCourse($this) == -1)
    {
      $wasRemoved = true;
    }
    else
    {
      $wasRemoved = $aInstructor->removeCourse($this);
      if (!$wasRemoved)
      {
        $this->instructors[$oldIndex] = $aInstructor;
        ksort($this->instructors);
      }
    }
    $this->instructors = array_values($this->instructors);
    return $wasRemoved;
  }

  public function setInstructors($newInstructors)
  {
    $wasSet = false;
    $verifiedInstructors = array();
    foreach ($newInstructors as $aInstructor)
    {
      if (array_search($aInstructor,$verifiedInstructors) !== false)
      {
        continue;
      }
      $verifiedInstructors[] = $aInstructor;
    }

    if (count($verifiedInstructors) != count($newInstructors) || count($verifiedInstructors) > self::maximumNumberOfInstructors())
    {
      return $wasSet;
    }

    $oldInstructors = $this->instructors;
    $this->instructors = array();
    foreach ($verifiedInstructors as $aNewInstructor)
    {
      $this->instructors[] = $aNewInstructor;
      $removeIndex = array_search($aNewInstructor,$oldInstructors);
      if ($removeIndex !== false)
      {
        unset($oldInstructors[$removeIndex]);
        $oldInstructors = array_values($oldInstructors);
      }
      else
      {
        $aNewInstructor->addCourse($this);
      }
    }

    foreach ($oldInstructors as $anOldInstructor)
    {
      $anOldInstructor->removeCourse($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function addInstructorAt($aInstructor, $index)
  {  
    $wasAdded = false;
    if($this->addInstructor($aInstructor))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfInstructors()) { $index = $this->numberOfInstructors() - 1; }
      array_splice($this->instructors, $this->indexOfInstructor($aInstructor), 1);
      array_splice($this->instructors, $index, 0, array($aInstructor));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveInstructorAt($aInstructor, $index)
  {
    $wasAdded = false;
    if($this->indexOfInstructor($aInstructor) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfInstructors()) { $index = $this->numberOfInstructors() - 1; }
      array_splice($this->instructors, $this->indexOfInstructor($aInstructor), 1);
      array_splice($this->instructors, $index, 0, array($aInstructor));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addInstructorAt($aInstructor, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfJobs()
  {
    return 0;
  }

  public function addJobVia($aMaxHours, $aWage, $aDeadline, $aIsApproved, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience)
  {
    return new Job($aMaxHours, $aWage, $aDeadline, $aIsApproved, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $this);
  }

  public function addJob($aJob)
  {
    $wasAdded = false;
    if ($this->indexOfJob($aJob) !== -1) { return false; }
    $existingCourse = $aJob->getCourse();
    $isNewCourse = $existingCourse != null && $this !== $existingCourse;
    if ($isNewCourse)
    {
      $aJob->setCourse($this);
    }
    else
    {
      $this->jobs[] = $aJob;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeJob($aJob)
  {
    $wasRemoved = false;
    //Unable to remove aJob, as it must always have a course
    if ($this !== $aJob->getCourse())
    {
      unset($this->jobs[$this->indexOfJob($aJob)]);
      $this->jobs = array_values($this->jobs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addJobAt($aJob, $index)
  {  
    $wasAdded = false;
    if($this->addJob($aJob))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobs()) { $index = $this->numberOfJobs() - 1; }
      array_splice($this->jobs, $this->indexOfJob($aJob), 1);
      array_splice($this->jobs, $index, 0, array($aJob));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveJobAt($aJob, $index)
  {
    $wasAdded = false;
    if($this->indexOfJob($aJob) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfJobs()) { $index = $this->numberOfJobs() - 1; }
      array_splice($this->jobs, $this->indexOfJob($aJob), 1);
      array_splice($this->jobs, $index, 0, array($aJob));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addJobAt($aJob, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $copyOfInstructors = $this->instructors;
    $this->instructors = array();
    foreach ($copyOfInstructors as $aInstructor)
    {
      $aInstructor->removeCourse($this);
    }
    while (count($this->jobs) > 0)
    {
      $aJob = $this->jobs[count($this->jobs) - 1];
      $aJob->delete();
      unset($this->jobs[$this->indexOfJob($aJob)]);
      $this->jobs = array_values($this->jobs);
    }
    
  }

}
?>