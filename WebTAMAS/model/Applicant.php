<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

include 'User.php';

class Applicant extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Applicant Attributes
  private $cGPA;
  private $skills;
  private $isGraduate;

  //Applicant Associations
  private $applications;
  private $assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aId, $aCGPA, $aSkills, $aIsGraduate)
  {
    parent::__construct($aName, $aId);
    $this->cGPA = $aCGPA;
    $this->skills = $aSkills;
    $this->isGraduate = $aIsGraduate;
    $this->applications = array();
    $this->assignments = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setCGPA($aCGPA)
  {
    $wasSet = false;
    $this->cGPA = $aCGPA;
    $wasSet = true;
    return $wasSet;
  }

  public function setSkills($aSkills)
  {
    $wasSet = false;
    $this->skills = $aSkills;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsGraduate($aIsGraduate)
  {
    $wasSet = false;
    $this->isGraduate = $aIsGraduate;
    $wasSet = true;
    return $wasSet;
  }

  public function getCGPA()
  {
    return $this->cGPA;
  }

  public function getSkills()
  {
    return $this->skills;
  }

  public function getIsGraduate()
  {
    return $this->isGraduate;
  }

  public function isIsGraduate()
  {
    return $this->isGraduate;
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

  public static function minimumNumberOfApplications()
  {
    return 0;
  }

  public static function maximumNumberOfApplications()
  {
    return 3;
  }

  public function addApplicationVia($aIsAccepted, $aExperience, $aCourseGPA, $aJob)
  {
    if ($this->numberOfApplications() >= self::maximumNumberOfApplications())
    {
      return null;
    }
    else
    {
      return new Application($aIsAccepted, $aExperience, $aCourseGPA, $this, $aJob);
    }
  }

  public function addApplication($aApplication)
  {
    $wasAdded = false;
    if ($this->indexOfApplication($aApplication) !== -1) { return false; }
    if ($this->numberOfApplications() >= self::maximumNumberOfApplications())
    {
      return $wasAdded;
    }

    $existingApplicant = $aApplication->getApplicant();
    $isNewApplicant = $existingApplicant != null && $this !== $existingApplicant;
    if ($isNewApplicant)
    {
      $aApplication->setApplicant($this);
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
    //Unable to remove aApplication, as it must always have a applicant
    if ($this !== $aApplication->getApplicant())
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

  public static function minimumNumberOfAssignments()
  {
    return 0;
  }

  public static function maximumNumberOfAssignments()
  {
    return 3;
  }

  public function addAssignmentVia($aFeedback, $aJob)
  {
    if ($this->numberOfAssignments() >= self::maximumNumberOfAssignments())
    {
      return null;
    }
    else
    {
      return new Assignment($aFeedback, $this, $aJob);
    }
  }

  public function addAssignment($aAssignment)
  {
    $wasAdded = false;
    if ($this->indexOfAssignment($aAssignment) !== -1) { return false; }
    if ($this->numberOfAssignments() >= self::maximumNumberOfAssignments())
    {
      return $wasAdded;
    }

    $existingApplicant = $aAssignment->getApplicant();
    $isNewApplicant = $existingApplicant != null && $this !== $existingApplicant;
    if ($isNewApplicant)
    {
      $aAssignment->setApplicant($this);
    }
    else
    {
      $this->assignments[] = $aAssignment;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeAssignment($aAssignment)
  {
    $wasRemoved = false;
    //Unable to remove aAssignment, as it must always have a applicant
    if ($this !== $aAssignment->getApplicant())
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

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    while (count($this->applications) > 0)
    {
      $aApplication = $this->applications[count($this->applications) - 1];
      $aApplication->delete();
      unset($this->applications[$this->indexOfApplication($aApplication)]);
      $this->applications = array_values($this->applications);
    }
    
    while (count($this->assignments) > 0)
    {
      $aAssignment = $this->assignments[count($this->assignments) - 1];
      $aAssignment->delete();
      unset($this->assignments[$this->indexOfAssignment($aAssignment)]);
      $this->assignments = array_values($this->assignments);
    }
    
    parent::delete();
  }

}
?>