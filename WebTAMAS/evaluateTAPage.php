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
				<hr>
				<form action='evaluateTA.php' method='post'>
					
				<p class = "logo">
			
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

				
				$ps = new Persistence();
				$rm = $ps->loadDataFromStore();
				$c = new Controller();
				$c->loadCourse();
				
				$name = $rm->getInstructor_index($_SESSION["index"])->getName();
	
				echo "Hi Professor " . $name . "<br>";
				
				$ps = new Persistence();
				$rm = $ps->loadDataFromStore();
				
				if($rm->numberOfApplicants() == 0){
					$Tom = new Applicant("Tom", "1", "4.00", "Java", "Yes");
					$Jack = new Applicant("Jack", "2", "3.80", "Java, PHP", "No");
					
					$rm->addApplicant($Tom);
					$rm->addApplicant($Jack);
	
					

					$ps->writeDataToStore($rm);
				}
				
			
	
	
				
				echo "<p> Select TA <select name='TASpinner'>";
				if($rm->numberOfJobs()!= 0){
				foreach ($rm-> getApplicants() as $applicant){
					echo "<option>" . $applicant->getName() . "</option>";
			
					}
				}
					
				echo "</select>";
				echo "</p>";

								
				?>
				</p>
				<p>Feedback <br> <textarea  class="experience" name="feedback" cols="5" rows="10"></textarea></p>
				
				
				
				<p class="message">
				<?php 
				
				if(isset($_SESSION['feedbackMessage']) && !empty($_SESSION['feedbackMessage']))
				echo $_SESSION["feedbackMessage"] . "<br>";
				if(isset($_SESSION['error']) && !empty($_SESSION['error']))
				echo $_SESSION["error"];
				
				$_SESSION["error"] = "";
				$_SESSION["feedbackMessage"] = "";
				
				?>
				</p>
				
				<button type="submit">SEND</button>
			
			
				
				</form>
				
				<hr>
				
				<form class = "logout" action="http://localhost/Group12/WebTAMAS/mainpage.php" >
				<button type="submit">Main Page</button>
				</form>	
							
				</div>
				</div>
				
			</body>
			
	</html>
				
					
				