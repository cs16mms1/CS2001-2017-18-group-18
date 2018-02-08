<?php

include 'dbconnect.php';

$user_id = $_POST["user_id"];
$team_id = $_POST["team_id"];

addTeamMember();

function addTeamMember(){
  global $team_id, $user_id, $connect;

  $query = mysqli_stmt_init($connect);
  $query = mysqli_prepare($connect,"INSERT INTO users_has_team(users_user_id,team_team_id) VALUES (?,?)");
  mysqli_stmt_bind_param($query,"ss",$user_id,$team_id);
  mysqli_stmt_execute($query);
  mysqli_stmt_close($query);

  $response = array();

  if(checkAdded()){
    $response['success'] = true;
  }
  else{
    $response['success'] = false;
  }

  echo json_encode($response);

}

function checkAdded(){
    global $team_id, $user_id, $connect;

    $statement = mysqli_prepare($connect, "SELECT * FROM users_has_team WHERE users_user_id = '$user_id' AND team_team_id = '$team_id'");
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    $count = mysqli_stmt_num_rows($statement);
    mysqli_stmt_close($statement);
    if ($count >= 1){
        return true;
    }else {
        return false;
    }
}

 ?>
