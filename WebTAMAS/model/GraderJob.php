<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

include 'Job.php';

class GraderJob extends Job
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aMaxHours, $aWage, $aDeadline, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $aCourse)
  {
    parent::__construct($aMaxHours, $aWage, $aDeadline, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $aCourse);
  }

  //------------------------
  // INTERFACE
  //------------------------

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