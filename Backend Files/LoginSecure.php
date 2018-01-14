<?php
	session_start();
   include 'dbconnect.php';

	//Create variables based on variables posted from Android
    $username = $_POST["username"];
    $password = $_POST["password"];
    $type = $_POST["type"];


    $statement = mysqli_stmt_init($connect);
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
            getUserId();

            $_SESSION['id'] = 54;
            header("Location: MyTeams.php");
        }
    }

    function getUserId(){
			global $username, $id,$connect,$response;
			$query = "SELECT user_id FROM users WHERE username = '$username'";
			$result = mysqli_query($connect, $query);
			$row = mysqli_fetch_assoc($result);
			$id = (int) $row['user_id'];
      $response["id"] = $id;
		}

	//Send response back to app
    echo json_encode($response);
?>
