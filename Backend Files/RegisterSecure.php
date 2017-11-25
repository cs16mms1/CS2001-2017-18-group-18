<?php
	
    $connect = mysqli_connect('localhost', 'user', 'password');
    
    // Check for database connection error
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
        
            var_dump(mysqli_select_db($connect,'db'));
            
        
    $username = $_POST["username"];
    $password = $_POST["password"];
    $email = $_POST["email"];
    $phone = $_POST["phone"];
    $type = $_POST["type"];
    
    var_dump($username,$password,$email,$phone,$type);
    
    
     function registerUser() {
        global $connect, $username, $password, $email,$phone,$type;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
            $statement = mysqli_stmt_init($connect);
    $statement = mysqli_prepare($connect, "INSERT INTO users (username, password, email, phone, type) VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssss", $username, $passwordHash, $email, $phone, $type);
    mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
    
    function usernameAvailable() {
        global $connect, $username;
        $statement = mysqli_prepare($connect, "SELECT * FROM users WHERE username = ?"); 
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }
    
    $response = array();
    $response["success"] = false;  
    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
    echo json_encode($response);
?>