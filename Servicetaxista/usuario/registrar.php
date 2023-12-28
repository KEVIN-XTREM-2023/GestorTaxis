<?php

include '../conexion.php'; 
$ced = $_POST['ced'];
$nom = $_POST['nom'];
$ape = $_POST['ape'];
$coop = $_POST['coop'];
$email = $_POST['email'];
$passw = $_POST['passw'];

$myArray=[];
$sql = "INSERT INTO taxista(ced_tax, nom_tax, ape_tax,coop_tax, jor_tax,est_tax,email_tax,passw_tax)
 VALUES('$ced','$nom','$ape','$coop',0,0,'$email','$passw') ";



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