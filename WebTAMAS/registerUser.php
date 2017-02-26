<?php 
require_once '/Users/KevenLiu/Documents/workspace/TAMAS_Web/Group12/WebTAMAS/controller/Controller.php';

session_start();
$_SESSION["errorUserName"] = "";
$_SESSION["errorUserID"] = "";

$c = new Controller();

try{
	$c->register($_POST['registerName'], $_POST['registerID']);
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

	
}


?>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="refresh" content="0; url=/TAMAS_Web/Group12/WebTAMAS/register.php" />
</head>
</html>