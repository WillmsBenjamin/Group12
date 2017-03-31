<?php 
require_once '/Applications/XAMPP/xamppfiles/htdocs/Group12/WebTAMAS/controller/Controller.php';

session_start();
$_SESSION["errorWrongUser"] = "";

$c = new Controller();

try{
	$_SESSION["index"] = $c->login($_POST['userName'], $_POST['userID']);
	header("Location: mainpage.php");
	exit;
}
catch (Exception $e){
	$_SESSION["errorWrongUser"] = $e->getMessage();
	header("Location: index.php");
	exit;
}


?>
