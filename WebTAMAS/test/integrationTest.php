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
		//$this->rm->delete();
		$this->ps->writeDataToStore($this->rm);
	}
	
	protected function tearDown()
	{
	}
	public function testRegisterName(){
	
		//$this->assertEquals(0,count($this->rm->getInstructors()));
	
		
	
	
	}
	
	
}
	?>