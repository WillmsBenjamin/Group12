<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Application
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Application Attributes
  private $isAccepted;
  private $isOffered;
  private $experience;
  private $courseGPA;

  //Autounique Attributes
  private $id;

  //Application Associations
  private $applicant;
  private $job;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aIsAccepted, $aIsOffered, $aExperience, $aCourseGPA, $aApplicant, $aJob)
  {
    $this->isAccepted = $aIsAccepted;
    $this->isOffered = $aIsOffered;
    $this->experience = $aExperience;
    $this->courseGPA = $aCourseGPA;
    $this->id = self::$nextId++;
    $didAddApplicant = $this->setApplicant($aApplicant);
    if (!$didAddApplicant)
    {
      throw new Exception("Unable to create application due to applicant");
    }
    $didAddJob = $this->setJob($aJob);
    if (!$didAddJob)
    {
      throw new Exception("Unable to create application due to job");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setIsAccepted($aIsAccepted)
  {
    $wasSet = false;
    $this->isAccepted = $aIsAccepted;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsOffered($aIsOffered)
  {
    $wasSet = false;
    $this->isOffered = $aIsOffered;
    $wasSet = true;
    return $wasSet;
  }

  public function setExperience($aExperience)
  {
    $wasSet = false;
    $this->experience = $aExperience;
    $wasSet = true;
    return $wasSet;
  }

  public function setCourseGPA($aCourseGPA)
  {
    $wasSet = false;
    $this->courseGPA = $aCourseGPA;
    $wasSet = true;
    return $wasSet;
  }

  public function getIsAccepted()
  {
    return $this->isAccepted;
  }

  public function getIsOffered()
  {
    return $this->isOffered;
  }

  public function getExperience()
  {
    return $this->experience;
  }

  public function getCourseGPA()
  {
    return $this->courseGPA;
  }

  public function getId()
  {
    return $this->id;
  }

  public function isIsAccepted()
  {
    return $this->isAccepted;
  }

  public function isIsOffered()
  {
    return $this->isOffered;
  }

  public function getApplicant()
  {
    return $this->applicant;
  }

  public function getJob()
  {
    return $this->job;
  }

  public function setApplicant($aApplicant)
  {
    $wasSet = false;
    //Must provide applicant to application
    if ($aApplicant == null)
    {
      return $wasSet;
    }

    //applicant already at maximum (3)
    if ($aApplicant->numberOfApplications() >= Applicant::maximumNumberOfApplications())
    {
      return $wasSet;
    }
    
    $existingApplicant = $this->applicant;
    $this->applicant = $aApplicant;
    if ($existingApplicant != null && $existingApplicant != $aApplicant)
    {
      $didRemove = $existingApplicant->removeApplication($this);
      if (!$didRemove)
      {
        $this->applicant = $existingApplicant;
        return $wasSet;
      }
    }
    $this->applicant->addApplication($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setJob($aJob)
  {
    $wasSet = false;
    if ($aJob == null)
    {
      return $wasSet;
    }
    
    $existingJob = $this->job;
    $this->job = $aJob;
    if ($existingJob != null && $existingJob != $aJob)
    {
      $existingJob->removeApplication($this);
    }
    $this->job->addApplication($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderApplicant = $this->applicant;
    $this->applicant = null;
    $placeholderApplicant->removeApplication($this);
    $placeholderJob = $this->job;
    $this->job = null;
    $placeholderJob->removeApplication($this);
  }

}
?>