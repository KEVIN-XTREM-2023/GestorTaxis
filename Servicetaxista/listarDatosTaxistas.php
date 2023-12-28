<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Accept, Authorization");
header("Content-Type: application/json");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");

if (isset($_RExQUEST['id_tax'])) {
	
include 'conexion.php';
$id_tax=$_REQUEST['id_tax'];

$sentencia=$conexion->prepare("SELECT * FROM taxistas WHERE id_tax=$id_tax");
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