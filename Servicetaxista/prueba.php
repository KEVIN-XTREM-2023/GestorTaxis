<?php

include 'conexion.php'; 

$myArray=[];
$sql = "SELECT * FROM taxistas ";



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