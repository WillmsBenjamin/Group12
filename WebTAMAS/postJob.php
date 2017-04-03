<!DOCTYPE html>
<html>
	<head >
		<meta charset="UTF-8">
		<title>McGill TAMAS</title>
		<link rel="stylesheet" type="text/css" href="login.css">

	
	</head>
<body>


	<div class="postJob">
		<div class="postJob_form">
		
				<p class = "logo"> McGill TAMAS - Instructor </p>
				<hr>
					<form action='publishJob.php' method='post'>
			
			
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
				
				echo "<p>Please fill in the job requirements!</p>";
								
				echo "<p> Select Course <select name='courseSpinner'>";
						for($i = 0; $i < $rm->numberOfCourses(); $i++){
							echo "<option>" . $rm->getCourse_index($i)->getName() . "</option>";
				}
				echo "</select>";		
				echo "</p>";
				
				echo "<p>&nbsp&nbsp Job Type <select name='jobSpinner'>";
				
					echo "<option>" . "TA" . "</option>";
					echo "<option>" . "TALab" . "</option>";
					echo "<option>" . "Grader" . "</option>";
				
				echo "</select>";
				echo "</p>";
				?>								
				
				
				<p>&nbsp&nbsp&nbsp&nbsp&nbsp Max Hours: <input class="normal" type="text"  name="maxHours"/></p>
					<p class = "error">
				
					<?php 
		
					
					if(isset($_SESSION['errorMaxHour']) && !empty($_SESSION['errorMaxHour'])){
						echo " * " . $_SESSION["errorMaxHour"];
					}	
					?>
				</p>
				<p>Wage per hour: <input class="normal" type="text"  name="wage"/></p>
						<p class = "error">
				
					<?php 

					
					if(isset($_SESSION['errorWage']) && !empty($_SESSION['errorWage'])){
						echo " * " . $_SESSION["errorWage"];
					}	
					?>
				</p>
				
				<p> &nbsp&nbsp&nbsp&nbsp&nbsp Apply Deadline: <input class="deadline" type="date"  name="deadline"/></p>
						<p class = "error">
				
					<?php 
	
					
					if(isset($_SESSION['errorDeadline']) && !empty($_SESSION['errorDeadline'])){
						echo " * " . $_SESSION["errorDeadline"];
					}	
					?>
				</p>
				
				<p>Required Course GPA: <input class="normal" type="text"  name="courseGPA" placeholder="x.xx"/></p>
						<p class = "error">
				
					<?php 
			
					
					if(isset($_SESSION['errorCourseGPA']) && !empty($_SESSION['errorCourseGPA'])){
						echo " * " . $_SESSION["errorCourseGPA"];
					}	
					?>
				</p>
				
				<p>Required CGPA: <input class="normal" type="text"  name="CGPA" placeholder="x.xx"/></p>
						<p class = "error">
				
					<?php 
					
					if(isset($_SESSION['errorCGPA']) && !empty($_SESSION['errorCGPA'])){
						echo " * " . $_SESSION["errorCGPA"];
					}	
					?>
				</p>
				
				<p>Required Skills: <input class="skills" type="text"  name="skills"/></p>
				
				<p>Required Experiences: <textarea  class="experience" name="experience" cols="5" rows="4"></textarea></p>
				
				<p class="message">
				<?php 
				if(isset($_SESSION['message']) && !empty($_SESSION['message']))
				echo $_SESSION["message"];
				?>
				</p>
				

				<button type="submit">Post</button>
				</form>
				
				
				<hr>
				
				
				<form class = "logout" action="http://localhost/Group12/WebTAMAS/mainpage.php" >
				<button type="submit">Main Page</button>
				</form>
						
		</div>
	</div>

	
	

</body>

</html>