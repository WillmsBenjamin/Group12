<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class TAjob extends Job
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TAjob Attributes
  private $minHours;
  private $isLab;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aMaxHours, $aWage, $aDeadline, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $aCourse, $aMinHours, $aIsLab)
  {
    parent::__construct($aMaxHours, $aWage, $aDeadline, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $aCourse);
    $this->minHours = $aMinHours;
    $this->isLab = $aIsLab;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setMinHours($aMinHours)
  {
    $wasSet = false;
    $this->minHours = $aMinHours;
    $wasSet = true;
    return $wasSet;
  }

  public function setIsLab($aIsLab)
  {
    $wasSet = false;
    $this->isLab = $aIsLab;
    $wasSet = true;
    return $wasSet;
  }

  public function getMinHours()
  {
    return $this->minHours;
  }

  public function getIsLab()
  {
    return $this->isLab;
  }

  public function isIsLab()
  {
    return $this->isLab;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    parent::delete();
  }

}
?>