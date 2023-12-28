<?php

include '../conexion.php'; 
$est = $_POST['est'];
$id = $_POST['id'];

$myArray=[];
$sql = "UPDATE taxista SET est_tax=$est WHERE id_tax=$id";



try {
    if($mysqli->multi_query($sql) === TRUE){
          echo json_encode(array('ok' => true, 'mensaje' => "Actualizacion Correcto")) ;
  
      }else{
        echo json_encode(array('ok'=> false, 'errorMsg' => "Los datos son incorrectos")) ;
        
      }
  } catch (\Throwable $th) {
    
    echo json_encode("Error") ;
  }

$mysqli->close();



?>