<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Hours
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hours Attributes
  private $date;
  private $startTime;
  private $endTime;

  //Hours Associations
  private $instructor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aDate, $aStartTime, $aEndTime, $aInstructor)
  {
    $this->date = $aDate;
    $this->startTime = $aStartTime;
    $this->endTime = $aEndTime;
    $didAddInstructor = $this->setInstructor($aInstructor);
    if (!$didAddInstructor)
    {
      throw new Exception("Unable to create contactTime due to instructor");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setDate($aDate)
  {
    $wasSet = false;
    $this->date = $aDate;
    $wasSet = true;
    return $wasSet;
  }

  public function setStartTime($aStartTime)
  {
    $wasSet = false;
    $this->startTime = $aStartTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setEndTime($aEndTime)
  {
    $wasSet = false;
    $this->endTime = $aEndTime;
    $wasSet = true;
    return $wasSet;
  }

  public function getDate()
  {
    return $this->date;
  }

  public function getStartTime()
  {
    return $this->startTime;
  }

  public function getEndTime()
  {
    return $this->endTime;
  }

  public function getInstructor()
  {
    return $this->instructor;
  }

  public function setInstructor($aInstructor)
  {
    $wasSet = false;
    if ($aInstructor == null)
    {
      return $wasSet;
    }
    
    $existingInstructor = $this->instructor;
    $this->instructor = $aInstructor;
    if ($existingInstructor != null && $existingInstructor != $aInstructor)
    {
      $existingInstructor->removeContactTime($this);
    }
    $this->instructor->addContactTime($this);
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $placeholderInstructor = $this->instructor;
    $this->instructor = null;
    $placeholderInstructor->removeContactTime($this);
  }

}
?>