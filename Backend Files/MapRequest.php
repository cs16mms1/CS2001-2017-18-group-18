<?php
include 'dbconnect.php';

$teamname = $_POST["teamname"];

teamNameSearch();

function teamNameSearch(){
  global $teamname, $connect;

$query = "SELECT postcode FROM team WHERE teamname = '$teamname'";
$result = mysqli_query($connect, $query);
$row = mysqli_fetch_assoc($result);

$response['postcode'] = $row['postcode'];

echo json_encode($response);

}

 ?>
