<?php
   $con = mysqli_connect('localhost', 'user', 'password');
           if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

    var_dump(mysqli_select_db($con,'db'));

    $username = $_POST["username"];
    $password = $_POST["password"];
    $type = $_POST["type"];
    
     var_dump($username,$password,$type);
     
    $statement = mysqli_stmt_init($con);
	mysqli_stmt_prepare($statement, "SELECT password FROM users WHERE username = ? AND type = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $type);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colPassword);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password,$colPassword)) {
            $response["success"] = true;  
        }
    }
    echo json_encode($response);
?>