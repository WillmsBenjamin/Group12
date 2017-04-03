<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Job
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Job Attributes
  private $maxHours;
  private $wage;
  private $deadline;
  private $isApproved;
  private $requiredSkills;
  private $requiredCourseGPA;
  private $requiredCGPA;
  private $requiredExperience;

  //Job Associations
  public $assignment; //until PHP 5.3 (setAccessible)
  private $applications;
  private $course;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aMaxHours, $aWage, $aDeadline, $aIsApproved, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $aCourse)
  {
    $this->maxHours = $aMaxHours;
    $this->wage = $aWage;
    $this->deadline = $aDeadline;
    $this->isApproved = $aIsApproved;
    $this->requiredSkills = $aRequiredSkills;
    $this->requiredCourseGPA = $aRequiredCourseGPA;
    $this->requiredCGPA = $aRequiredCGPA;
    $this->requiredExperience = $aRequiredExperience;
    $this->applications = array();
    $didAddCourse = $this->setCourse($aCourse);
    if (!$didAddCourse)
    {
      throw new Exception("Unable to create job due to course");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setMaxHours($aMaxHours)
  {
    $wasSet = false;
    $this->maxHours = $aMaxHours;
    $wasSet = true;
    return $wasSet;
  }

  public function setWage($aWage)
  {
    $wasSet = false;
    $this->wage = $aWage;
    $wasSet = true;
    return $wasSet;
  }

  public function setDeadline($aDeadline)
  {
    $wasSet = false;
    $this->deadline = $aDeadline;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsApproved($aIsApproved)
  {
    $wasSet = false;
    $this->isApproved = $aIsApproved;
    $wasSet = true;
    return $wasSet;
  }

  public function setRequiredSkills($aRequiredSkills)
  {
    $wasSet = false;
    $this->requiredSkills = $aRequiredSkills;
    $wasSet = true;
    return $wasSet;
  }

  public function setRequiredCourseGPA($aRequiredCourseGPA)
  {
    $wasSet = false;
    $this->requiredCourseGPA = $aRequiredCourseGPA;
    $wasSet = true;
    return $wasSet;
  }

  public function setRequiredCGPA($aRequiredCGPA)
  {
    $wasSet = false;
    $this->requiredCGPA = $aRequiredCGPA;
    $wasSet = true;
    return $wasSet;
  }

  public function setRequiredExperience($aRequiredExperience)
  {
    $wasSet = false;
    $this->requiredExperience = $aRequiredExperience;
    $wasSet = true;
    return $wasSet;
  }

  public function getMaxHours()
  {
    return $this->maxHours;
  }

  public function getWage()
  {
    return $this->wage;
  }

  public function getDeadline()
  {
    return $this->deadline;
  }

  public function getIsApproved()
  {
    return $this->isApproved;
  }

  public function getRequiredSkills()
  {
    return $this->requiredSkills;
  }

  public function getRequiredCourseGPA()
  {
    return $this->requiredCourseGPA;
  }

  public function getRequiredCGPA()
  {
    return $this->requiredCGPA;
  }

  public function getRequiredExperience()
  {
    return $this->requiredExperience;
  }

  public function isIsApproved()
  {
    return $this->isApproved;
  }

  public function getAssignment()
  {
    return $this->assignment;
  }

  public function hasAssignment()
  {
    $has = $this->assignment != null;
    return $has;
  }

  public function getApplication_index($index)
  {
    $aApplication = $this->applications[$index];
    return $aApplication;
  }

  public function getApplications()
  {
    $newApplications = $this->applications;
    return $newApplications;
  }

  public function numberOfApplications()
  {
    $number = count($this->applications);
    return $number;
  }

  public function hasApplications()
  {
    $has = $this->numberOfApplications() > 0;
    return $has;
  }

  public function indexOfApplication($aApplication)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->applications as $application)
    {
      if ($application->equals($aApplication))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getCourse()
  {
    return $this->course;
  }

  public function setAssignment($aNewAssignment)
  {
    $wasSet = false;
    if ($this->assignment != null && $this->assignment != $aNewAssignment && $this == $this->assignment->getJob())
    {
      //Unable to setAssignment, as existing assignment would become an orphan
      return $wasSet;
    }
    
    $this->assignment = $aNewAssignment;
    $anOldJob = $aNewAssignment != null ? $aNewAssignment->getJob() : null;
    
    if ($this != $anOldJob)
    {
      if ($anOldJob != null)
      {
        $anOldJob->assignment = null;
      }
      if ($this->assignment != null)
      {
        $this->assignment->setJob($this);
      }
    }
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfApplications()
  {
    return 0;
  }

  public function addApplicationVia($aIsAccepted, $aIsOffered, $aExperience, $aCourseGPA, $aApplicant)
  {
    return new Application($aIsAccepted, $aIsOffered, $aExperience, $aCourseGPA, $aApplicant, $this);
  }

  public function addApplication($aApplication)
  {
    $wasAdded = false;
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    $existingJob = $aApplication->getJob();
    $isNewJob = $existingJob != null && $this !== $existingJob;
    if ($isNewJob)
    {
      $aApplication->setJob($this);
    }
    else
    {
      $this->applications[] = $aApplication;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeApplication($aApplication)
  {
    $wasRemoved = false;
    //Unable to remove aApplication, as it must always have a job
    if ($this !== $aApplication->getJob())
    {
      unset($this->applications[$this->indexOfApplication($aApplication)]);
      $this->applications = array_values($this->applications);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addApplicationAt($aApplication, $index)
  {  
    $wasAdded = false;
    if($this->addApplication($aApplication))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplications()) { $index = $this->numberOfApplications() - 1; }
      array_splice($this->applications, $this->indexOfApplication($aApplication), 1);
      array_splice($this->applications, $index, 0, array($aApplication));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveApplicationAt($aApplication, $index)
  {
    $wasAdded = false;
    if($this->indexOfApplication($aApplication) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplications()) { $index = $this->numberOfApplications() - 1; }
      array_splice($this->applications, $this->indexOfApplication($aApplication), 1);
      array_splice($this->applications, $index, 0, array($aApplication));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addApplicationAt($aApplication, $index);
    }
    return $wasAdded;
  }

  public function setCourse($aCourse)
  {
    $wasSet = false;
    if ($aCourse == null)
    {
      return $wasSet;
    }
    
    $existingCourse = $this->course;
    $this->course = $aCourse;
    if ($existingCourse != null && $existingCourse != $aCourse)
    {
      $existingCourse->removeJob($this);
    }
    $this->course->addJob($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    
    if ($this->assignment != null)
    {
        $this->assignment->delete();
        $this->assignment = null;
    }
    
    while (count($this->applications) > 0)
    {
      $aApplication = $this->applications[count($this->applications) - 1];
      $aApplication->delete();
      unset($this->applications[$this->indexOfApplication($aApplication)]);
      $this->applications = array_values($this->applications);
    }
    
    $placeholderCourse = $this->course;
    $this->course = null;
    $placeholderCourse->removeJob($this);
  }

}
?>