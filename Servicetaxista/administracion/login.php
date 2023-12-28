<?php

include '../conexion.php'; 
$ema = $_POST['ema'];
$pas = $_POST['pas'];

$myArray=[];
$sql = "SELECT * FROM taxista WHERE email_tax='$ema' AND passw_tax='$pas' LIMIT 1 ";



if ($result = $mysqli->query($sql)){
    
   
  while($row = $result->fetch_assoc()) {
      
    $myArray[] = $row;
  }
 
  echo json_encode($myArray);
}
else{
  echo json_encode(array('mens'=>'No hay datos'));
}

$mysqli->close();



?>