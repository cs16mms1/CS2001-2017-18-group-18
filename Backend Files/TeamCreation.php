<?php

session_start();
include 'dbconnect.php';

$teamname = $_POST["teamname"];
$postcode = $_POST["postcode"];
$email = $_POST["email"];
$phone = $_POST["phone"];

var_dump($teamname,$postcode,$email,$phone);

  ?>
