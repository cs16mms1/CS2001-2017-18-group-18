<?php

    $connect = mysqli_connect('localhost', 'squadlinkuser', 'squadLinkPass1!','squadlinkdb');

    // Check for database connection error
        if (mysqli_connect_errno()) {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }

            var_dump(mysqli_select_db($connect,'squadlinkdb'));


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

		function getUserId(){
			global $username, $user_id,$connect;
			$query = "SELECT user_id FROM users WHERE username = '$username'";
			$result = mysqli_query($connect, $query);
			$row = mysqli_fetch_assoc($result);
			$user_id = $row['user_id'];
			var_dump($user_id);
		}

		function createDefaultProfile(){
			global $user_id,$connect;
			$query = mysqli_query($connect,"INSERT INTO profile (first_name,user_id) VALUES ('Tester',$user_id)");
		}

    $response = array();
    $response["success"] = false;
    if (usernameAvailable()){
        registerUser();
				getUserId();
				createDefaultProfile();
        $response["success"] = true;
    }

    echo json_encode($response);

?>
