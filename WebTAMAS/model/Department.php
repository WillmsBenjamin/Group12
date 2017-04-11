<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

class Department extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Department Attributes
  private $deadline;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aId, $aDeadline)
  {
    parent::__construct($aName, $aId);
    $this->deadline = $aDeadline;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setDeadline($aDeadline)
  {
    $wasSet = false;
    $this->deadline = $aDeadline;
    $wasSet = true;
    return $wasSet;
  }

  public function getDeadline()
  {
    return $this->deadline;
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