<?php
include 'dbconnect.php';

echo ($_GET['username']);
echo ($_POST['trainingplan']);

$username = $_GET['username'];
$tptext = $_POST['trainingplan'];
$user_id;

var_dump($username,$tptext);

getUserId();
insertTrainingPlan();

function getUserId(){
  global $username, $user_id,$connect;
  $query = "SELECT user_id FROM users WHERE username = '$username'";
  $result = mysqli_query($connect, $query);
  $row = mysqli_fetch_assoc($result);
  $user_id = (int) $row['user_id'];

  echo($user_id);

}

function insertTrainingPlan(){
global $username, $user_id, $connect, $tptext;

$query = mysqli_query($connect,"INSERT INTO trainingplans(tptext, users_user_id) VALUES ('$tptext','$user_id')");
}
?>
