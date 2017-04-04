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
			
			<form class = "login-form" action = "loginUser.php" method="post">
			
				<input type="text" placeholder="username" name="userName"/>
				
				<input type="password" placeholder="your 9 digits ID" name="userID"/>
				<p class="error">
				<?php 
				
					session_start();

					if(isset($_SESSION['errorWrongUser']) && !empty($_SESSION['errorWrongUser'])){
						echo " * " . $_SESSION["errorWrongUser"];
					}	
					$_SESSION["errorWrongUser"] = "";

					?>
				</p>
				
				<button type = "submit">login</button>  
				
				<p class="message">Not registered?<a href="register.php">Create an account</a></p> 
			</form>
		</div>
	</div>
	
	

</body>

</html>