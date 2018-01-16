<?php
  session_start();
  echo("Create teams PHP running");
  echo($_SESSION['id']);
?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>Create a Team</title>
    <link rel="stylesheet" href="stylesheets/registerstyles.css">
  </head>
  <body>
    <div class="header">
        <h2>Create a Team</h2>
      </div>

      <form method="post" action="TeamCreation.php">

        <div class="input-group">
          <label>Team Name</label>
          <input type="text" name="teamname" >
        </div>
        <div class="input-group">
          <label>Postcode</label>
          <input type="text" name="postcode">
        </div>
        <div class="input-group">
          <label>Email</label>
          <input type="text" name="email">
        </div>
        <div class="input-group">
          <label>Phone Number</label>
          <input type="text" name="phone">
        </div>
        <div class="input-group">
          <button type="submit" class="btn" name="reg_user">Create</button>
        </div>
      </form>

  </body>
</html>
