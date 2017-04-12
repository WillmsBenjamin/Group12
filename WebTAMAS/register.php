<!DOCTYPE html>
<html>
	<head >
		<meta charset="UTF-8">
		<title>McGill TAMAS</title>
		<link rel="stylesheet" type="text/css" href="login.css">

	
	</head>
<body>


	<div class="login_panel">
		<div class="form">
				<p class = "logo"> McGill TAMAS - Instructor </p>
				
			<form action="registerUser.php" method="post">
			
				<input type ="text" placeholder="name" name = "registerName" />
				<p class = "error">
				
					<?php 
					session_start();
					
					if(isset($_SESSION['errorUserName']) && !empty($_SESSION['errorUserName'])){
						echo " * " . $_SESSION["errorUserName"];
					}	
					$_SESSION["errorUserName"] = "";
					?>
				</p>
				
				<input type="password" placeholder="enter 9 digits ID" name = "registerID" />
					<p class = "error">
					<?php 
					if(isset($_SESSION['errorUserID']) && !empty($_SESSION['errorUserID'])){
						echo " * " . $_SESSION["errorUserID"];
					}	
					$_SESSION["errorUserID"] ="";
					?>
				</p>
				
				<button type = "submit">register</button>		
				
				<p class = "message"> Already registered?<a href="index.php">Sign In</a></p>
				
			</form>
		</div>
	</div>
	
	

</body>

</html>