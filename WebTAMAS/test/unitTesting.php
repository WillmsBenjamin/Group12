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
	
	public function testRegisterName(){
		
		$this->assertEquals(0,count($this->rm->getInstructors()));
		
		$name = "keven";
		$id = "999999999";
		
		try {
			$this->c->register($name, $id);
		} catch (Exception $e) {
			$this->fail();
		}
		
		$this->rm = $this->ps->loadDataFromStore();
		$this->assertEquals(1,count($this->rm->getInstructors()));
		$this->assertEquals($name,$this->rm->getInstructor_index(0)->getName());
	
	}
	
	public function testRegisterWrongName(){
		$this->assertEquals(0,count($this->rm->getInstructors()));
		
		$name = "123";
		$id = "999999999";
		$error = "";
		try {
			$this->c->register($name, $id);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		$this->assertEquals("@1User name must contain letters!",$error);
		$this->rm = $this->ps->loadDataFromStore();
		$this->assertEquals(0,count($this->rm->getInstructors()));
		
	}
	
	public function testRegisterNull(){
		$this->assertEquals(0,count($this->rm->getInstructors()));
	
		$name = null;
		$id = "999999999";
		$error = "";
		try {
			$this->c->register($name, $id);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals("@1User name cannot be empty!",$error);
		$this->rm = $this->ps->loadDataFromStore();
		$this->assertEquals(0,count($this->rm->getInstructors()));
	}
	
	public function testRegisterWrongID(){
		$this->assertEquals(0,count($this->rm->getInstructors()));
	
		$name = "keven";
		$id = "123";
		$error = "";
		try {
			$this->c->register($name, $id);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals("@2User ID must be 9 digits!",$error);
		$this->rm = $this->ps->loadDataFromStore();
		$this->assertEquals(0,count($this->rm->getInstructors()));
	}
	
	public function testRegisterUserExisted(){
		$this->assertEquals(0,count($this->rm->getInstructors()));
	
		$name = "keven";
		$id = "999999999";
		$error = "";
		try {
			$this->c->register($name, $id);
			$this->c->register("keven", "999999999");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals("@2User already exists!",$error);
		$this->rm = $this->ps->loadDataFromStore();
		$this->assertEquals(1,count($this->rm->getInstructors()));
	}

	
	public function testLogin(){
		
		$this->assertEquals(0,count($this->rm->getInstructors()));
		
		$name = "keven";
		$id = "999999999";
		
		$this->c->register($name, $id);
		$this->rm = $this->ps->loadDataFromStore();
		
		$this->assertEquals(1,count($this->rm->getInstructors()));
		
		$this->assertEquals(0,$this->c->login("keven", "999999999"));
	}
	
	public function testLoginFailed(){
	
		$this->assertEquals(0,count($this->rm->getInstructors()));
	
		$name = "keven";
		$id = "999999999";
		$error = "";
	
		$this->c->register($name, $id);
		$this->rm = $this->ps->loadDataFromStore();
	
		$this->assertEquals(1,count($this->rm->getInstructors()));
	
		try {
			$this->assertEquals(0,$this->c->login("jack", "999999999"));
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		$this->assertEquals("User not found!", $error);
		
	}
	
	public function testLoadCourse(){
		$this->assertEquals(0,$this->rm->numberOfCourses());
		
		$this->c->loadCourse();
		
		$this->rm = $this->ps->loadDataFromStore();
		
		$this->assertEquals(6,$this->rm->numberOfCourses());
		
		$this->assertEquals("ECSE321",$this->rm->getCourse_index(0)->getName());
		
		$this->assertEquals("ECSE421",$this->rm->getCourse_index(1)->getName());
		
		$this->assertEquals("ECSE521",$this->rm->getCourse_index(2)->getName());
		
		$this->assertEquals("ECSE600",$this->rm->getCourse_index(3)->getName());
		
		$this->assertEquals("COMP350",$this->rm->getCourse_index(4)->getName());
		
		$this->assertEquals("FACC200",$this->rm->getCourse_index(5)->getName());
		
		
	}
	
	public function testCreateTAjob(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
		
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);


	
		$this->c->createTAJob("80", "20", "2017-04-30", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "No");
		
		$this->rm = $this->ps->loadDataFromStore();
		
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
	
	public function testCreateTAjobWrongMaxHour(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
		
		$error = "";
		
		try {
			$this->c->createTAJob("200", "20", "2017-04-30", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "No");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@1Max hour must be digits and smaller than 180!");
		
	}
	
	public function testCreateTAjobWrongWage(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTAJob("20", "abc", "2017-04-30", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "No");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@2Wage per hour must be digits!");
	
	}
	
	public function testCreateTAjobWrongDeadline(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTAJob("20", "20", "abs", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "No");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@3Deadline date must be specified correctly (YYYY-MM-DD)!");
	
	}
	
	public function testCreateTAjobWrongCourseGPA(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTAJob("20", "20", "2017-04-30", FALSE, "JAVA", "400", "4.00", "TA", $ECSE321, "0", "No");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@4GPA must be specified correctly (x.xx)!");
	
	}
	
	public function testCreateTAjobWrongCGPA(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTAJob("20", "20", "2017-04-30", FALSE, "JAVA", "4.00", "400", "TA", $ECSE321, "0", "No");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		
		
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@5GPA must be specified correctly (x.xx)!");
	
	}
	
	public function testCreateGraderjob(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
	
	
		$this->c->createGraderJob("100", "15", "2017-04-30", False, "JAVA", "4.00", "4.00", "TA", $ECSE321);
	
		$this->rm = $this->ps->loadDataFromStore();
	
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

	public function testCreateGraderjobWrongMaxHour(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createGraderJob("200", "15", "2017-04-30", False, "JAVA", "4.00", "4.00", "TA", $ECSE321);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@1Max hour must be digits and smaller than 180!");
	
	}
	
	public function testCreateGraderjobWrongWage(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createGraderJob("100", "abc", "2017-04-30", False, "JAVA", "4.00", "4.00", "TA", $ECSE321);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@2Wage per hour must be digits!");
	
	}
	
	public function testCreateGraderjobWrongDeadline(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createGraderJob("100", "15", "2017acs", False, "JAVA", "4.00", "4.00", "TA", $ECSE321);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@3Deadline date must be specified correctly (YYYY-MM-DD)!");
	
	}
	
	public function testCreateGraderjobWrongCourseGPA(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createGraderJob("100", "15", "2017-04-30", False, "JAVA", "400", "4.00", "TA", $ECSE321);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@4GPA must be specified correctly (x.xx)!");
	
	}
	
	public function testCreateGraderjobWrongCGPA(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createGraderJob("100", "15", "2017-04-30", False, "JAVA", "4.00", "400", "TA", $ECSE321);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@5GPA must be specified correctly (x.xx)!");
	
	}
	
	public function testCreateTALabjob(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
	
	
		$this->c->createTALabJob("80", "20", "2017-04-30", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "Yes");
	
		$this->rm = $this->ps->loadDataFromStore();
	
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
	
	public function testCreateTALabjobWrongMaxHour(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTALabJob("200", "20", "2017-04-30", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "Yes");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@1Max hour must be digits and smaller than 180!");
	
	}
	
	public function testCreateTALabjobWrongWage(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTALabJob("20", "abc", "2017-04-30", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "Yes");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@2Wage per hour must be digits!");
	
	}
	
	public function testCreateTALabjobWrongDeadline(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTALabJob("20", "20", "abs", FALSE, "JAVA", "4.00", "4.00", "TA", $ECSE321, "0", "Yes");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@3Deadline date must be specified correctly (YYYY-MM-DD)!");
	
	}
	
	public function testCreateTALabjobWrongCourseGPA(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTALabJob("20", "20", "2017-04-30", FALSE, "JAVA", "400", "4.00", "TA", $ECSE321, "0", "Yes");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@4GPA must be specified correctly (x.xx)!");
	
	}
	
	public function testCreateTALabjobWrongCGPA(){
		$this->assertEquals(0, $this->rm->numberOfJobs());
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$error = "";
	
		try {
			$this->c->createTALabJob("20", "20", "2017-04-30", FALSE, "JAVA", "4.00", "400", "TA", $ECSE321, "0", "Yes");
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
	
		$this->assertEquals(0, $this->rm->numberOfJobs());
		$this->assertEquals($error,"@5GPA must be specified correctly (x.xx)!");
	
	}
	public function testSendFeedback(){
		$this->assertEquals(0, $this->rm->numberOfAssignments());
	
		$applicant = new Applicant("keven", "1", "4.00", "Java", "No");
	
		$ECSE321 = new Course("ECSE321", 2, 0, 200,1000);
	
		$job = new GraderJob("100", "15", "2017-04-30", False, "JAVA", "4.00", "4.00", "TA", $ECSE321);
	
		$a = new Assignment("Good Job!", $applicant, $job);
		
		$this->rm->addAssignment($a);
		
		$this->assertEquals(1, $this->rm->numberOfAssignments());
		
		
	}
	
	

	



	
}
?>