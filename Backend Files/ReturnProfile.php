<?php
  include 'dbconnect.php';

  $id = $_POST["id"];
	var_dump($id);

  $query = "SELECT * FROM profile WHERE user_id = '$id'";
  $result = mysqli_query($connect, $query);
  $row = mysqli_fetch_assoc($result);

  $response = array();

  var_dump((int) $row['profile_id']);

  $response['profile_id'] = (int) $row['profile_id'];
  $response['first_name'] = $row['first_name'];
  $response['last_name'] = $row['last_name'];
  $response['bio'] = $row['bio'];
  $response['profile_pic'] = $row['profile_pic'];

  echo json_encode($response);

 ?>
