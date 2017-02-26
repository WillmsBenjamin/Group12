<?php
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/Applicant.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/Application.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/Assignment.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/Course.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/Department.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/GraderJob.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/Hours.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/Instructor.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/Job.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/ResourceManager.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/TAjob.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/model/User.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/controller/InputValidator.php';
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/persistence/Persistence.php';

class Controller
{
	public function __construct(){
		
	}
	
	public function register($userName, $userID){
		$name = InputValidator::validate_input($userName);
		$ID = InputValidator::validate_input($userID);
		$error = "";
		
		if($name == null || strlen($name) == 0){
			$error .= "@1User name cannot be empty! ";
		
		}
	    if(strlen($ID) != 9 || ctype_digit($ID) == FALSE){
			$error .= "@2User ID must be 9 digits! ";

		}
		if(ctype_digit($name) == TRUE){
			$error .= "@1User name must contain letters!";
		}
		
		if(strlen(trim($error)) == 0){
			$ps = new Persistence();
			$rm = $ps->loadDataFromStore();
			
			$instructor = new Instructor($name, $ID);
			$rm->addInstructor($instructor);
			
			$ps->writeDataToStore($rm);						
		}
		else{
			throw new Exception(trim($error));
		}
		
	}
	
	public function login($userName, $userID){
		$name = InputValidator::validate_input($userName);
		$ID = InputValidator::validate_input($userID);
		$error = "";
		
		$tempInstructor = new Instructor($name, $ID);
		
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
		

		
		for($i = 0; $i < $rm->numberOfInstructors(); $i++){
		if($rm->getInstructor_index($i)->equals($tempInstructor)){
				
				return $i;
				
			}
		}
		$error = "User not found!";
		throw new Exception(trim($error));
				
	}
}

?>
