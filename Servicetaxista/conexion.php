<?php

$servername = "localhost";
$user = "root";
$password = "";
$dbname = "db_viajes_taxis";

$conexion = mysqli_connect($servername,$user,$password,$dbname);
//$conexion = new mysqli($servername,$user,$password,$dbname);

if (!$conexion) {
    die("Error en la conexion".mysqli_connect_error());
} else {
    //echo "Conexion exitosa";
}

?>