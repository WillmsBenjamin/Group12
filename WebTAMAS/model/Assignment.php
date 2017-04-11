<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Assignment
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Assignment Attributes
  private $feedback;

  //Autounique Attributes
  private $id;

  //Assignment Associations
  private $applicant;
  private $job;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aFeedback, $aApplicant, $aJob)
  {
    $this->feedback = $aFeedback;
    $this->id = self::$nextId++;
    $didAddApplicant = $this->setApplicant($aApplicant);
    if (!$didAddApplicant)
    {
      throw new Exception("Unable to create assignment due to applicant");
    }
    $didAddJob = $this->setJob($aJob);
    if (!$didAddJob)
    {
      throw new Exception("Unable to create assignment due to job");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setFeedback($aFeedback)
  {
    $wasSet = false;
    $this->feedback = $aFeedback;
    $wasSet = true;
    return $wasSet;
  }

  public function getFeedback()
  {
    return $this->feedback;
  }

  public function getId()
  {
    return $this->id;
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
    //Must provide applicant to assignment
    if ($aApplicant == null)
    {
      return $wasSet;
    }

    //applicant already at maximum (3)
    if ($aApplicant->numberOfAssignments() >= Applicant::maximumNumberOfAssignments())
    {
      return $wasSet;
    }
    
    $existingApplicant = $this->applicant;
    $this->applicant = $aApplicant;
    if ($existingApplicant != null && $existingApplicant != $aApplicant)
    {
      $didRemove = $existingApplicant->removeAssignment($this);
      if (!$didRemove)
      {
        $this->applicant = $existingApplicant;
        return $wasSet;
      }
    }
    $this->applicant->addAssignment($this);
    $wasSet = true;
    return $wasSet;
  }

  public function setJob($aNewJob)
  {
    $wasSet = false;
    if ($aNewJob == null)
    {
      //Unable to setJob to null, as assignment must always be associated to a job
      return $wasSet;
    }
    
    $existingAssignment = $aNewJob->getAssignment();
    if ($existingAssignment != null && $this != $existingAssignment)
    {
      //Unable to setJob, the current job already has a assignment, which would be orphaned if it were re-assigned
      return $wasSet;
    }
    
    $anOldJob = $this->job;
    $this->job = $aNewJob;
    $this->job->setAssignment($this);
    
    if ($anOldJob != null)
    {
      $anOldJob->setAssignment(null);
    }
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
    $placeholderApplicant->removeAssignment($this);
    $existingJob = $this->job;
    $this->job = null;
    if ($existingJob != null)
    {
      $existingJob->setAssignment(null);
    }
  }

}
?>