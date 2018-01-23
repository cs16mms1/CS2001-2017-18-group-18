<?php

session_start();
include 'dbconnect.php';

$teamname = $_POST["teamname"];
$postcode = $_POST["postcode"];
$email = $_POST["email"];
$phone = $_POST["phone"];
$id = $_SESSION['id'];


  insertTeam();
  getTeamId();
      function insertTeam(){
        global $connect, $teamname, $postcode, $email, $phone;
              var_dump($connect,$teamname,$postcode,$email,$phone);
        $query = mysqli_query($connect,"INSERT INTO team (teamname, postcode, email, phone)  VALUES ('$teamname','$postcode','$email','$phone')");

      }

      function getTeamId(){
        global $connect, $teamname;
        $result = mysqli_query($connect, "SELECT team_id FROM team WHERE teamname = '$teamname' ");
        $row = mysqli_fetch_assoc($result);
  			$teamid = (int) $row['team_id'];
        var_dump($teamid);
      }


  ?>
