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
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/controller/Controller.php';
				

session_start();
	
$_SESSION["errorMaxHour"] = "";
$_SESSION["errorWage"] = "";
$_SESSION["errorDeadline"] = "";
$_SESSION["errorCourseGPA"] = "";
$_SESSION["errorCGPA"] = "";


$c = new Controller();	
$ps = new Persistence();
$rm = $ps->loadDataFromStore();

try{
		$courseName = $_POST['courseSpinner'];
		for($i = 0; $i < $rm->numberOfCourses(); $i++){
			if($rm->getCourse_index($i)->getName() == $courseName){
				$course = $rm->getCourse_index($i);
			}
		}	
	if($_POST['jobSpinner'] == "TA"){
		$c->profAddCourse($course);
		$c->createTAJob($_POST['maxHours'], $_POST['wage'], $_POST['deadline'],FALSE, $_POST['skills'], 
				$_POST['courseGPA'], $_POST['CGPA'], $_POST['experience'], $course, 0, FALSE);
	

	}
	else if($_POST['jobSpinner'] == "Grader"){
		$c->profAddCourse($course);
		$c->createGraderJob($_POST['maxHours'], $_POST['wage'], $_POST['deadline'], FALSE,$_POST['skills'], 
				$_POST['courseGPA'], $_POST['CGPA'], $_POST['experience'], $course);
	
	}
}
catch (Exception $e){
	$errors = explode("@", $e->getMessage());
	
	foreach ($errors as $error){
		if(substr($error, 0, 1) == "1"){
			$_SESSION["errorMaxHour"] = substr($error,1);
		}
		if(substr($error, 0, 1) == "2"){
			$_SESSION["errorWage"] = substr($error,1);
		}
		if(substr($error, 0, 1) == "3"){
			$_SESSION["errorDeadline"] = substr($error,1);
		}
		if(substr($error, 0, 1) == "4"){
			$_SESSION["errorCourseGPA"] = substr($error,1);
		}
		if(substr($error, 0, 1) == "5"){
			$_SESSION["errorCGPA"] = substr($error,1);
		}
	}
}

				
?>



<!DOCTYPE html>
<html>
	<head>
			<meta http-equiv="refresh" content="0; url=/Group12/WebTAMAS/postJob.php" />
	</head>	
</html>