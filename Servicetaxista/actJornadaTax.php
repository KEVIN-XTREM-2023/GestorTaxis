<?php
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Accept, Authorization");
header("Content-Type: application/json");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");

include 'conexion.php';

$id_tax= $_REQUEST['id_tax'];
$jor_tax = $_REQUEST['jor_tax'];

$sqlUpdateCliente = "UPDATE taxistas SET jor_tax='$jor_tax'
                                     WHERE id_tax='$id_tax'";

$conexion->query($sqlUpdateCliente);

$conexion->close();

?>