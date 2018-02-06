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
  $response["success"] = true;
  echo json_encode($response);
}

 ?>
