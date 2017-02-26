<!DOCTYPE html>
<html>
	<head >
		<meta charset="UTF-8">
		<title>McGill TAMAS</title>
		<link rel="stylesheet" type="text/css" href="login.css">

	
	</head>
<body>


	<div class="mainpage">
		<div class="mainpage_form">
				<p class = "logo"> McGill TAMAS - Instructor </p>
				<p class = "logo">
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
				
				session_start();
				
				$ps = new Persistence();
				$rm = $ps->loadDataFromStore();
				
				$name = $rm->getInstructor_index($_SESSION["index"])->getName();
				echo "Professor " . $name;
				?>
				</p>
				
		
		</div>
	</div>
	
	

</body>

</html>