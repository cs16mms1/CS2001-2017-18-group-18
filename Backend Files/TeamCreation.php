<?php

session_start();
include 'dbconnect.php';

$teamname = $_POST["teamname"];
$postcode = $_POST["postcode"];
$email = $_POST["email"];
$phone = $_POST["phone"];
$website = $_POST["website"];
$id = $_SESSION['id'];


  insertTeam();

      function insertTeam(){
        global $connect, $teamname, $postcode, $email, $phone, $website;
              var_dump($connect,$teamname,$postcode,$email,$phone);
        $query = mysqli_query($connect,"INSERT INTO team (teamname, postcode, email, phone, website)  VALUES ('$teamname','$postcode','$email','$phone','$website')");
        getTeamId();
        insertIntoLink();
      }

      function getTeamId(){
        global $connect, $teamname, $teamid;
        $result = mysqli_query($connect, "SELECT team_id FROM team WHERE teamname = '$teamname' ");
        $row = mysqli_fetch_assoc($result);
  			$teamid = (int) $row['team_id'];
        var_dump($teamid);
      }

      function insertIntoLink(){
        global $connect, $id, $teamid;
        $query = mysqli_query($connect,"INSERT INTO users_has_team (users_user_id, team_team_id) VALUES ('$id','$teamid')");
      }

  ?>
