<?php

	include 'dbconnect.php';
	session_start();
	echo("test");
	echo($_SESSION['id']);

	$user_id = $_SESSION['id'];

	returnTeams();

	function returnTeams(){
		global $connect, $user_id;
		$query = "SELECT * FROM `team` INNER JOIN `users_has_team` ON
							users_has_team.team_team_id = team.team_id WHERE
							users_has_team.users_user_id = '$user_id'";
		$result = mysqli_query($connect, $query);
		$row = mysqli_fetch_assoc($result);

		echo($row['teamname']);
		echo ($row['team_id']);

		$_SESSION['teamname'] = $row['teamname'];
		$_SESSION['team_id'] = $row['team_id'];

	}


?>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>My Teams</title>
	</head>
	<body>
			<a href="CreateTeam.php">Create a Team</a>

	</body>
</html>
