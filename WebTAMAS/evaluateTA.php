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

$_SESSION["error"] = "";
$_SESSION["feedbackMessage"] = "";

$c = new Controller();
$ps = new Persistence();
$rm = $ps->loadDataFromStore();


	$taName = $_POST['TASpinner'];
	
	if($rm->numberOfJobs()!= 0){
		foreach ($rm->getApplicants() as $aApplicant){
					if($aApplicant->getName() == $taName){
						$c->sendFeedback($_POST['feedback'], $aApplicant);
					}
						
		}
	}
	
	?>
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta http-equiv="refresh" content="0; url=/Group12/WebTAMAS/evaluateTAPage.php" />
	</head>
	</html>
	
	

