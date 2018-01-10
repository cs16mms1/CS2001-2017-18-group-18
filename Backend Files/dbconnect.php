<?php
$connect = mysqli_connect('localhost', 'squadlinkuser', 'squadLinkPass1!','squadlinkdb');

// Check for database connection error
    if (mysqli_connect_errno()) {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

        var_dump(mysqli_select_db($connect,'squadlinkdb'));
?>
