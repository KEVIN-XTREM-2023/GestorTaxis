<?php
#SERVICIO PARA LISTAR LOS VIAJES QUE HAN SIDO ASINADOS A TAXISTAS
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: Origin, X-Requested-with, Content-type, Accept, Authorization");
header("Content-Type: application/json");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
	
if (isset($_REQUEST['id_tax_per'])) {

include 'conexion.php';

$id_tax_per=$_REQUEST['id_tax_per'];


$sqlListaClientes = "SELECT A.*, V.* FROM viajes V, asignaciones A
 WHERE V.id_via=A.id_via_per AND A.id_tax_per=$id_tax_per";

$resp = $conexion->query($sqlListaClientes);

$result = array();

if ($resp->num_rows > 0) {
    while ($row = $resp->fetch_assoc()) {
        array_push($result, $row);
    }
    } else {
        $result =0;
    }
    echo json_encode($result);
$conexion->close();
}
?>

|
