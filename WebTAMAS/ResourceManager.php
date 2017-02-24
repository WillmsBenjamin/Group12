<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class ResourceManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ResourceManager Associations
  private $applicants;
  private $instructors;
  public $department; //until PHP 5.3 (setAccessible)
  public $loggedIn; //until PHP 5.3 (setAccessible)
  private $applications;
  private $jobs;
  private $courses;
  private $assignments;
  private $contactTimes;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->applicants = array();
    $this->instructors = array();
    $this->applications = array();
    $this->jobs = array();
    $this->courses = array();
    $this->assignments = array();
    $this->contactTimes = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new ResourceManager();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getApplicant_index($index)
  {
    $aApplicant = $this->applicants[$index];
    return $aApplicant;
  }

  public function getApplicants()
  {
    $newApplicants = $this->applicants;
    return $newApplicants;
  }

  public function numberOfApplicants()
  {
    $number = count($this->applicants);
    return $number;
  }

  public function hasApplicants()
  {
    $has = $this->numberOfApplicants() > 0;
    return $has;
  }

  public function indexOfApplicant($aApplicant)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->applicants as $applicant)
    {
      if ($applicant->equals($aApplicant))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
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

  public function getDepartment()
  {
    return $this->department;
  }

  public function hasDepartment()
  {
    $has = $this->department != null;
    return $has;
  }

  public function getLoggedIn()
  {
    return $this->loggedIn;
  }

  public function hasLoggedIn()
  {
    $has = $this->loggedIn != null;
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

  public function getAssignment_index($index)
  {
    $aAssignment = $this->assignments[$index];
    return $aAssignment;
  }

  public function getAssignments()
  {
    $newAssignments = $this->assignments;
    return $newAssignments;
  }

  public function numberOfAssignments()
  {
    $number = count($this->assignments);
    return $number;
  }

  public function hasAssignments()
  {
    $has = $this->numberOfAssignments() > 0;
    return $has;
  }

  public function indexOfAssignment($aAssignment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->assignments as $assignment)
    {
      if ($assignment->equals($aAssignment))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

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

  public static function minimumNumberOfApplicants()
  {
    return 0;
  }

  public function addApplicant($aApplicant)
  {
    $wasAdded = false;
    if ($this->indexOfApplicant($aApplicant) !== -1) { return false; }
    $this->applicants[] = $aApplicant;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeApplicant($aApplicant)
  {
    $wasRemoved = false;
    if ($this->indexOfApplicant($aApplicant) != -1)
    {
      unset($this->applicants[$this->indexOfApplicant($aApplicant)]);
      $this->applicants = array_values($this->applicants);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addApplicantAt($aApplicant, $index)
  {  
    $wasAdded = false;
    if($this->addApplicant($aApplicant))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplicants()) { $index = $this->numberOfApplicants() - 1; }
      array_splice($this->applicants, $this->indexOfApplicant($aApplicant), 1);
      array_splice($this->applicants, $index, 0, array($aApplicant));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveApplicantAt($aApplicant, $index)
  {
    $wasAdded = false;
    if($this->indexOfApplicant($aApplicant) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfApplicants()) { $index = $this->numberOfApplicants() - 1; }
      array_splice($this->applicants, $this->indexOfApplicant($aApplicant), 1);
      array_splice($this->applicants, $index, 0, array($aApplicant));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addApplicantAt($aApplicant, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfInstructors()
  {
    return 0;
  }

  public function addInstructor($aInstructor)
  {
    $wasAdded = false;
    if ($this->indexOfInstructor($aInstructor) !== -1) { return false; }
    $this->instructors[] = $aInstructor;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeInstructor($aInstructor)
  {
    $wasRemoved = false;
    if ($this->indexOfInstructor($aInstructor) != -1)
    {
      unset($this->instructors[$this->indexOfInstructor($aInstructor)]);
      $this->instructors = array_values($this->instructors);
      $wasRemoved = true;
    }
    return $wasRemoved;
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

  public function setDepartment($aNewDepartment)
  {
    $wasSet = false;
    $this->department = $aNewDepartment;
    $wasSet = true;
    return $wasSet;
  }

  public function setLoggedIn($aNewLoggedIn)
  {
    $wasSet = false;
    $this->loggedIn = $aNewLoggedIn;
    $wasSet = true;
    return $wasSet;
  }

  public static function minimumNumberOfApplications()
  {
    return 0;
  }

  public function addApplication($aApplication)
  {
    $wasAdded = false;
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    $this->applications[] = $aApplication;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeApplication($aApplication)
  {
    $wasRemoved = false;
    if ($this->indexOfApplication($aApplication) != -1)
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

  public static function minimumNumberOfJobs()
  {
    return 0;
  }

  public function addJob($aJob)
  {
    $wasAdded = false;
    if ($this->indexOfJob($aJob) !== -1) { return false; }
    $this->jobs[] = $aJob;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeJob($aJob)
  {
    $wasRemoved = false;
    if ($this->indexOfJob($aJob) != -1)
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

  public static function minimumNumberOfCourses()
  {
    return 0;
  }

  public function addCourse($aCourse)
  {
    $wasAdded = false;
    if ($this->indexOfCourse($aCourse) !== -1) { return false; }
    $this->courses[] = $aCourse;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeCourse($aCourse)
  {
    $wasRemoved = false;
    if ($this->indexOfCourse($aCourse) != -1)
    {
      unset($this->courses[$this->indexOfCourse($aCourse)]);
      $this->courses = array_values($this->courses);
      $wasRemoved = true;
    }
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

  public static function minimumNumberOfAssignments()
  {
    return 0;
  }

  public function addAssignment($aAssignment)
  {
    $wasAdded = false;
    if ($this->indexOfAssignment($aAssignment) !== -1) { return false; }
    $this->assignments[] = $aAssignment;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAssignment($aAssignment)
  {
    $wasRemoved = false;
    if ($this->indexOfAssignment($aAssignment) != -1)
    {
      unset($this->assignments[$this->indexOfAssignment($aAssignment)]);
      $this->assignments = array_values($this->assignments);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addAssignmentAt($aAssignment, $index)
  {  
    $wasAdded = false;
    if($this->addAssignment($aAssignment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAssignments()) { $index = $this->numberOfAssignments() - 1; }
      array_splice($this->assignments, $this->indexOfAssignment($aAssignment), 1);
      array_splice($this->assignments, $index, 0, array($aAssignment));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveAssignmentAt($aAssignment, $index)
  {
    $wasAdded = false;
    if($this->indexOfAssignment($aAssignment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfAssignments()) { $index = $this->numberOfAssignments() - 1; }
      array_splice($this->assignments, $this->indexOfAssignment($aAssignment), 1);
      array_splice($this->assignments, $index, 0, array($aAssignment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addAssignmentAt($aAssignment, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfContactTimes()
  {
    return 0;
  }

  public function addContactTime($aContactTime)
  {
    $wasAdded = false;
    if ($this->indexOfContactTime($aContactTime) !== -1) { return false; }
    $this->contactTimes[] = $aContactTime;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeContactTime($aContactTime)
  {
    $wasRemoved = false;
    if ($this->indexOfContactTime($aContactTime) != -1)
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

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->applicants = array();
    $this->instructors = array();
    $this->department = null;
    $this->loggedIn = null;
    $this->applications = array();
    $this->jobs = array();
    $this->courses = array();
    $this->assignments = array();
    $this->contactTimes = array();
  }

}
?>