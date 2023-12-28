<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Accept, Authorization");
header("Content-Type: application/json");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");

if (isset($_REQUEST['email_tax']) && isset($_REQUEST['passw_tax'])) {
	
include 'conexion.php';
$email_tax=$_REQUEST['email_tax'];
$passw_tax=$_REQUEST['passw_tax'];

$sentencia=$conexion->prepare("SELECT * FROM taxistas WHERE email_tax=? AND passw_tax=?");
$sentencia->bind_param('ss',$email_tax,$passw_tax);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
    echo json_encode($fila,JSON_FORCE_OBJECT);     
}
mysqli_free_result($resultado);
$sentencia->close();
$conexion->close();
}
?>