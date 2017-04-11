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


class unitTesting extends PHPUnit_Framework_TestCase{
	protected $c;
	protected $ps;
	protected $rm;
	
	protected function setUp(){
		$this->ps = new Persistence();
		$this->c = new Controller();
		
		$this->rm = $this->ps->loadDataFromStore();
		$this->rm->delete();
		$this->ps->writeDataToStore($this->rm);
	}
	
	protected function tearDown()
	{
	}
	
	public function testPostTAjobFromRegister(){
		
		$this->assertEquals(0, $this->rm->numberOfJobs());
		
		$name = "keven";
		$id = "999999999";
		
		$this->c->register($name, $id);
		
		
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
		
			
		$this->c->createTAJob("80", "20", "2017-04-30", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "No");
		
		$this->rm = $this->ps->loadDataFromStore();
		
		$this->assertEquals(0,$this->c->login("keven", "999999999"));
		
		$this->assertEquals(1, $this->rm->numberOfJobs());
		

		$this->assertEquals("80",$this->rm->getJob_index(0)->getMaxHours());
		
		$this->assertEquals("20",$this->rm->getJob_index(0)->getWage());
		
		$this->assertEquals("2017-04-30",$this->rm->getJob_index(0)->getDeadline());
		
		$this->assertEquals("JAVA",$this->rm->getJob_index(0)->getRequiredSkills());
		
		$this->assertEquals("TA",$this->rm->getJob_index(0)->getRequiredExperience());
		
		$this->assertEquals("4.00",$this->rm->getJob_index(0)->getRequiredCourseGPA());
		
		$this->assertEquals("4.00",$this->rm->getJob_index(0)->getRequiredCGPA());
		
		$this->assertEquals($ECSE321,$this->rm->getJob_index(0)->getCourse());
		
		$this->assertEquals("0",$this->rm->getJob_index(0)->getMinHours());
		
		$this->assertEquals("No",$this->rm->getJob_index(0)->getIsLab());
		
			
	}
	
	public function testPostTALabjobFromRegister(){
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$name = "keven";
		$id = "999999999";
	
		$this->c->register($name, $id);
	
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
			
		$this->c->createTALabJob("80", "20", "2017-04-30", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "Yes");
	
		$this->rm = $this->ps->loadDataFromStore();
	
		$this->assertEquals(0,$this->c->login("keven", "999999999"));
	
		$this->assertEquals(1, $this->rm->numberOfJobs());
		

		$this->assertEquals("80",$this->rm->getJob_index(0)->getMaxHours());
		
		$this->assertEquals("20",$this->rm->getJob_index(0)->getWage());
		
		$this->assertEquals("2017-04-30",$this->rm->getJob_index(0)->getDeadline());
		
		$this->assertEquals("JAVA",$this->rm->getJob_index(0)->getRequiredSkills());
		
		$this->assertEquals("TA",$this->rm->getJob_index(0)->getRequiredExperience());
		
		$this->assertEquals("4.00",$this->rm->getJob_index(0)->getRequiredCourseGPA());
		
		$this->assertEquals("4.00",$this->rm->getJob_index(0)->getRequiredCGPA());
		
		$this->assertEquals($ECSE321,$this->rm->getJob_index(0)->getCourse());
		
		$this->assertEquals("0",$this->rm->getJob_index(0)->getMinHours());
		
		$this->assertEquals("Yes",$this->rm->getJob_index(0)->getIsLab());
	
			
	}
	
	public function testPostGraderjobFromRegister(){
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$name = "keven";
		$id = "999999999";
	
		$this->c->register($name, $id);
	
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
			
		$this->c->createGraderJob("100", "15", "2017-04-30", False, "JAVA", "4.00", "4.00", "TA", $ECSE321);
	
		$this->rm = $this->ps->loadDataFromStore();
	
		$this->assertEquals(0,$this->c->login("keven", "999999999"));
	
		$this->assertEquals(1, $this->rm->numberOfJobs());
	
	
		$this->assertEquals("100",$this->rm->getJob_index(0)->getMaxHours());
	
		$this->assertEquals("15",$this->rm->getJob_index(0)->getWage());
	
		$this->assertEquals("2017-04-30",$this->rm->getJob_index(0)->getDeadline());
	
		$this->assertEquals("JAVA",$this->rm->getJob_index(0)->getRequiredSkills());
	
		$this->assertEquals("TA",$this->rm->getJob_index(0)->getRequiredExperience());
	
		$this->assertEquals("4.00",$this->rm->getJob_index(0)->getRequiredCourseGPA());
	
		$this->assertEquals("4.00",$this->rm->getJob_index(0)->getRequiredCGPA());
	
		$this->assertEquals($ECSE321,$this->rm->getJob_index(0)->getCourse());

			
	}
	

	public function testSendFeedbackFromRegister(){

		$this->assertEquals(0, $this->rm->numberOfAssignments());
		
		$name = "keven";
		$id = "999999999";
		
		$this->c->register($name, $id);
		
		
		$applicant = new Applicant("keven", "1", "4.00", "Java", "No");
		
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
		
		$job = new GraderJob("100", "15", "2017-04-30", False, "JAVA", "4.00", "4.00", "TA", $ECSE321);
		
		$a = new Assignment("Good Job!", $applicant, $job);
		
		$this->rm->addAssignment($a);
		
		$this->assertEquals(1, $this->rm->numberOfAssignments());
	}
	
	
	
}
	?>