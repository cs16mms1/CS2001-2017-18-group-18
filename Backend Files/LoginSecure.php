<?php
   $con = mysqli_connect('localhost', 'user', 'password');
           if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

    var_dump(mysqli_select_db($con,'db'));

	//Create variables based on variables posted from Android
    $username = $_POST["username"];
    $password = $_POST["password"];
    $type = $_POST["type"];
    
     var_dump($username,$password,$type);
     
    $statement = mysqli_stmt_init($con);
	//Prepare SQL query
	mysqli_stmt_prepare($statement, "SELECT password FROM users WHERE username = ? AND type = ?");
	//Bind inputted variables to '?'s in query
    mysqli_stmt_bind_param($statement, "ss", $username, $type);
	//Execute the query
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
	//Bind result to new variable
    mysqli_stmt_bind_result($statement, $colPassword);
    
	//Create a default response
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
		//If inputted password is equivalent to hashed stored password then return true
        if (password_verify($password,$colPassword)) {
            $response["success"] = true;  
        }
    }

	//Send response back to app
    echo json_encode($response);
?>
