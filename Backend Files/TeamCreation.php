<?php

session_start();
include 'dbconnect.php';

$teamname = $_POST["teamname"];
$postcode = $_POST["postcode"];
$email = $_POST["email"];
$phone = $_POST["phone"];

  insertTeam();

      function insertTeam(){
        global $connect, $teamname, $postcode, $email, $phone;

        var_dump($teamname,$postcode,$email,$phone);
        $query = mysqli_query($connect,"INSERT INTO team (teamname, postcode, email, phone)  VALUES '$teamname','$postcode','$email','$phone'");

      }

  ?>
