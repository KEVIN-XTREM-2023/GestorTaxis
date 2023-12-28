<?php

include '../conexion.php'; 
$nombre = $_POST['nombre'];
$detalle = $_POST['detalle'];
$precio = $_POST['precio'];

$myArray=[];
$sql = "INSERT INTO menu(nombre, detalle, precio) VALUES('$nombre','$detalle,$precio) ";



try {
    if($mysqli->multi_query($sql) === TRUE){
          echo json_encode(array('ok' => true, 'mensaje' => "Registro Correcto")) ;
  
      }else{
        echo json_encode(array('ok'=> false, 'errorMsg' => "Los datos son incorrectos")) ;
        
      }
  } catch (\Throwable $th) {
    
    echo json_encode("Error") ;
  }

$mysqli->close();



?>