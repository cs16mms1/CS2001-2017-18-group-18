<?php
	
    $connect = mysqli_connect('localhost', 'user', 'password');
    
    // Check for database connection error
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
        
            var_dump(mysqli_select_db($connect,'db'));
            
    //Variables posted from Android app
    $username = $_POST["username"];
    $password = $_POST["password"];
    $email = $_POST["email"];
    $phone = $_POST["phone"];
    $type = $_POST["type"];
    
    var_dump($username,$password,$email,$phone,$type);
    
    
     function registerUser() {
        global $connect, $username, $password, $email,$phone,$type;
		 //Create new variable that hashes password
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
            $statement = mysqli_stmt_init($connect);
		 //Prepare SQL statement
    $statement = mysqli_prepare($connect, "INSERT INTO users (username, password, email, phone, type) VALUES (?, ?, ?, ?, ?)");
		 //Bind the variables posted to '?'s in query
    mysqli_stmt_bind_param($statement, "sssss", $username, $passwordHash, $email, $phone, $type);
		 //Exectute query
    mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }
    
    function usernameAvailable() {
        global $connect, $username;
		
		//Create a SQL query
        $statement = mysqli_prepare($connect, "SELECT * FROM users WHERE username = ?"); 
		//Bind variable to '?' in query
        mysqli_stmt_bind_param($statement, "s", $username);
		//Execute statement
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
		//Count number of records returned
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
		//If 0 is returned then username is available, return true
        if ($count < 1){
            return true; 
		//Otherwise return false
        }else {
            return false; 
        }
    }
    
	//Create default JSON response
    $response = array();
    $response["success"] = false;
	
	//Call function to check if username is available, if true call register user function
    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;  
    }
    
	//Send JSON string response
    echo json_encode($response);
?>
