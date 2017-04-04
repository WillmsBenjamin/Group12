<?php
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Applicant.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Application.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Assignment.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Course.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Department.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/GraderJob.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Instructor.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/Job.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/ResourceManager.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/TAjob.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/model/User.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/controller/InputValidator.php';
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/persistence/Persistence.php';

class Controller
{
	public function __construct(){
		
	}
	
	public function register($userName, $userID){
		$name = InputValidator::validate_input($userName);
		$ID = InputValidator::validate_input($userID);
		$error = "";
		
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
		foreach ($rm->getInstructors() as $instructor){
			if(strcmp($instructor->getName(), $userName) == 0){
				$error .= "@2User already exists! ";
			}
		}
		
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
		
		
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
		

		//return the index of the Instructor
		for($i = 0; $i < $rm->numberOfInstructors(); $i++){
		if($rm->getInstructor_index($i)->getName() == $userName && $rm->getInstructor_index($i)->getId() == $userID){
				
				return $i;
				
			}
		}
		$error = "User not found!";
		throw new Exception(trim($error));
				
	}

	
	//Since we don't implement Integration yet, the course list are added automatically here
	public function loadCourse(){
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
	
		
		if($rm->numberOfCourses() == 0){
			$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
			$ECSE421 = new Course("ECSE421", 1, 2, 100,2000);
			$ECSE521 = NEW Course("ECSE521", 1, 4, 60,30000);
			$ECSE600 = new Course("ECSE600", 1, 4, 40,40000);
			$COMP350 = new Course("COMP350", 2, 0, 150,5000);
			$FACC200 = new Course("FACC200", 0, 0, 300,2000);
			
			$rm->addCourse($ECSE321);
			$rm->addCourse($ECSE421);
			$rm->addCourse($ECSE521);
			$rm->addCourse($ECSE600);
			$rm->addCourse($COMP350);		
			$rm->addCourse($FACC200);
		}
		$ps->writeDataToStore($rm);
	}
	
	public function loadApplications(Job $aJob){
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
	
		if($rm->numberOfApplications() == 0){
			$Tom = new Applicant("Tom", "1", "4.00", "Java", "Yes");
			$Jack = new Applicant("Jack", "2", "3.80", "Java, PHP", "No");
			
			foreach ($rm->getJobs() as $job){
				if($aJob == $job){
			$Tom->addApplication(new Application(True, True, "None", "4.00", $Tom, $job));
			$Jack->addApplication(new Application(True, True, "TAed ECSE 321", "4.00", $Jack, $job));
			
			$rm->addApplication(new Application(True, True, "None", "4.00", $Tom, $job));
			$rm->addApplication(new Application(True, True, "TAed ECSE 321", "4.00", $Jack, $job));


				}
			}
		}
		$ps->writeDataToStore($rm);
	
	
	}

	
	public function createTAJob($aMaxHours, $aWage, $aDeadline, $aIsApproved,$aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, Course $aCourse, $aMinHours, $aIsLab){
		$maxHour = InputValidator::validate_input($aMaxHours);
		$wage = InputValidator::validate_input($aWage);
		$deadline = InputValidator::validate_input($aDeadline);
		$courseGPA = InputValidator::validate_input($aRequiredCourseGPA);
		$cGPA = InputValidator::validate_input($aRequiredCGPA);
		$skills = InputValidator::validate_input($aRequiredSkills);
		$experience = InputValidator::validate_input($aRequiredExperience);
		
		$error = "";

		
		if(ctype_digit($maxHour) == FALSE || $maxHour > 180){
			$error .= "@1Max hour must be digits and smaller than 180! ";
		}
		if(ctype_digit($wage) == FALSE){
			$error .= "@2Wage per hour must be digits! ";
		}
		if(!strtotime($deadline)){
			$error .= "@3Deadline date must be specified correctly (YYYY-MM-DD)! ";
		}
		if(ctype_digit($courseGPA[0]) == FALSE || $courseGPA[1] != "." || ctype_digit(substr($courseGPA, 2)) == FALSE){
			$error .= "@4GPA must be specified correctly (x.xx)! ";
		}
		if(ctype_digit($cGPA[0]) == FALSE || $cGPA[1] != "." || ctype_digit(substr($cGPA, 2)) == FALSE){
			$error .= "@5GPA must be specified correctly (x.xx)! ";
		}
		
		if(strlen(trim($error)) == 0){
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
		
		
		$taJob = new TAjob($aMaxHours, $aWage, $aDeadline, $aIsApproved, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $aCourse, $aMinHours, "No");
		$rm->addJob($taJob);
		for($i = 0; $i < $rm->numberOfCourses(); $i++){
	
			if($rm->getCourse_index($i)->getName() == $aCourse->getName()){
				//$rm->getInstructor_index($_SESSION["index"])->getCourse_index($i)->addJob($taJob);
				$rm->getCourse_index($i)->addJob($taJob);		
			}			
		}
		
		//$this->loadApplications($taJob);
		
		$_SESSION["message"] = "Job posted!";
	
		$ps->writeDataToStore($rm);
		}
		else{
			throw new Exception(trim($error));
		}
	}
	
	public function createTALabJob($aMaxHours, $aWage, $aDeadline, $aIsApproved,$aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, Course $aCourse, $aMinHours, $aIsLab){
		$maxHour = InputValidator::validate_input($aMaxHours);
		$wage = InputValidator::validate_input($aWage);
		$deadline = InputValidator::validate_input($aDeadline);
		$courseGPA = InputValidator::validate_input($aRequiredCourseGPA);
		$cGPA = InputValidator::validate_input($aRequiredCGPA);
		$skills = InputValidator::validate_input($aRequiredSkills);
		$experience = InputValidator::validate_input($aRequiredExperience);
	
		$error = "";
	
	
		if(ctype_digit($maxHour) == FALSE || $maxHour > 180){
			$error .= "@1Max hour must be digits and smaller than 180! ";
		}
		if(ctype_digit($wage) == FALSE){
			$error .= "@2Wage per hour must be digits! ";
		}
		if(!strtotime($deadline)){
			$error .= "@3Deadline date must be specified correctly (YYYY-MM-DD)! ";
		}
		if(ctype_digit($courseGPA[0]) == FALSE || $courseGPA[1] != "." || ctype_digit(substr($courseGPA, 2)) == FALSE){
			$error .= "@4GPA must be specified correctly (x.xx)! ";
		}
		if(ctype_digit($cGPA[0]) == FALSE || $cGPA[1] != "." || ctype_digit(substr($cGPA, 2)) == FALSE){
			$error .= "@5GPA must be specified correctly (x.xx)! ";
		}
	
		if(strlen(trim($error)) == 0){
			$ps = new Persistence();
			$rm = $ps->loadDataFromStore();
	
	
			$taLabJob = new TAjob($aMaxHours, $aWage, $aDeadline, $aIsApproved, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $aCourse, $aMinHours, "Yes");
			$rm->addJob($taLabJob);
			for($i = 0; $i < $rm->numberOfCourses(); $i++){
			
				if($rm->getCourse_index($i)->getName() == $aCourse->getName()){
					//$rm->getInstructor_index($_SESSION["index"])->getCourse_index($i)->addJob($taLabJob);
					$rm->getCourse_index($i)->addJob($taLabJob);
			
				}
					
			}
				
			//$this->loadApplications($taLabJob);

			
			$_SESSION["message"] = "Job posted!";
			$ps->writeDataToStore($rm);
		}
		else{
			throw new Exception(trim($error));
		}
	}
	
	public  function createGraderJob($aMaxHours, $aWage, $aDeadline, $aIsApproved,$aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, Course $aCourse){
		$maxHour = InputValidator::validate_input($aMaxHours);
		$wage = InputValidator::validate_input($aWage);
		$deadline = InputValidator::validate_input($aDeadline);
		$courseGPA = InputValidator::validate_input($aRequiredCourseGPA);
		$cGPA = InputValidator::validate_input($aRequiredCGPA);
		$skills = InputValidator::validate_input($aRequiredSkills);
		$experience = InputValidator::validate_input($aRequiredExperience);
		
		$error = "";

		
		if(ctype_digit($maxHour) == FALSE || $maxHour > 180){
			$error .= "@1Max hour must be digits and smaller than 180! ";
		}
		if(ctype_digit($wage) == FALSE){
			$error .= "@2Wage per hour must be digits! ";
		}
		if(!strtotime($deadline)){
			$error .= "@3Deadline date must be specified correctly (YYYY-MM-DD)! ";
		}
		if(ctype_digit($courseGPA[0]) == FALSE || $courseGPA[1] != "." || ctype_digit(substr($courseGPA, 2)) == FALSE){
			$error .= "@4GPA must be specified correctly (x.xx)! ";
		}
		if(ctype_digit($cGPA[0]) == FALSE || $cGPA[1] != "." || ctype_digit(substr($cGPA, 2)) == FALSE){
			$error .= "@5GPA must be specified correctly (x.xx)! ";
		}
		
		
		
		if(strlen(trim($error)) == 0){
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
		
		$graderJob = new GraderJob($aMaxHours, $aWage, $aDeadline,$aIsApproved, $aRequiredSkills, $aRequiredCourseGPA, $aRequiredCGPA, $aRequiredExperience, $aCourse);
		$rm->addJob($graderJob);
		
		$this->profAddCourse($aCourse);
		for($i = 0; $i < $rm->numberOfCourses(); $i++){
		
			if($rm->getCourse_index($i)->getName() == $aCourse->getName()){
				//$rm->getInstructor_index($_SESSION["index"])->getCourse_index($i)->addJob($graderJob);
				$rm->getCourse_index($i)->addJob($graderJob);
		
			}
				
		}
		//$this->loadApplications($graderJob);

		
		$_SESSION["message"] = "Job posted!";
		
		$ps->writeDataToStore($rm);
		}
		else{
			throw new Exception(trim($error));
		}
	}
	
	//Once the instructor posted a job, it is assumed that he has registered for the course corresponding to that job
	public function profAddCourse(Course $course){
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
		$hasCourse = FALSE;
		
		if($rm->getInstructor_index($_SESSION["index"])->numberOfCourses() == 0){
			$rm->getInstructor_index($_SESSION["index"])->addCourse($course);
	
		}
		else{
		for($i = 0; $i < $rm->getInstructor_index($_SESSION["index"])->numberOfCourses(); $i++){
			if($rm->getInstructor_index($_SESSION["index"])->getCourse_index($i)->getName() == $course->getName()){				
				$hasCourse = TRUE;
				}			
			}
			if($hasCourse == FALSE){
				$rm->getInstructor_index($_SESSION["index"])->addCourse($course);
			}
		}

		$ps->writeDataToStore($rm);
	}
	
	
	//Sent feedback method
	public function sendFeedback($aFeedback, Applicant $aApplicant){
		$feedback = InputValidator::validate_input($aFeedback);
		
		$ps = new Persistence();
		$rm = $ps->loadDataFromStore();
		
		$error = "";
	


		foreach ($rm->getApplicants() as $applicant){
			if($applicant->getName() == $aApplicant->getName()){
				
			for($i = 0; $i < $rm->numberOfJobs(); $i++){
				

				
				if($rm->getJob_index($i)->hasAssignment() == FALSE){				
				$a = new Assignment($feedback, $applicant, $rm->getJob_index($i));
				$rm->addAssignment($a);
				
				$_SESSION["feedbackMessage"] = "Feedback Sent!";
					}
					
				else if($rm->getJob_index($i)->getAssignment()->getApplicant()->getName() == $applicant->getName()){
						$_SESSION["feedbackMessage"] = "Failed!";
						$error = "Feedback already sent for this TA!";
						throw new Exception(trim($error));
					}
			

				}
			}
		
		}
		
	
		
		$ps->writeDataToStore($rm);
		
		
	}
	

}

?>
