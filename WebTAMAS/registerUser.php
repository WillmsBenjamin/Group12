<?php 
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/controller/Controller.php';
session_start();
$_SESSION["errorUserName"] = "";
$_SESSION["errorUserID"] = "";

$c = new Controller();

try{
	$c->register($_POST['registerName'], $_POST['registerID']);
	header("Location: index.php");
	exit;
}
catch (Exception $e){

	$errors = explode("@", $e->getMessage());
	
	
	foreach ($errors as $error){
		if(substr($error, 0, 1) == "1"){
			$_SESSION["errorUserName"] = substr($error,1);
		}
		if(substr($error, 0, 1) == "2"){
			$_SESSION["errorUserID"] = substr($error,1);
		}
	}
	header("Location: register.php");
	exit;

	
}


?>
