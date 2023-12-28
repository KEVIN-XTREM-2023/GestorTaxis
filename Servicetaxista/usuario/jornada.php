<?php

include '../conexion.php'; 
$jor = $_POST['jor'];
$id = $_POST['id'];

$myArray=[];
$sql = "UPDATE taxista SET jor_tax=$jor WHERE id_tax=$id";



try {
    if($mysqli->multi_query($sql) === TRUE){
          echo json_encode(array('ok' => true, 'mensaje' => "Actualizacion Correcto")) ;
  
      }else{
        echo json_encode(array('ok'=> false, 'errorMsg' => $sql)) ;
        
      }
  } catch (\Throwable $th) {
    
    echo json_encode("Error") ;
  }

$mysqli->close();



?>