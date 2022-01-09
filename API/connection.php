<?php

$connection = null;

try{
    //Config
    $host = "localhost";
    $username = "u1694897_c_reg_1";
    $password = "jtipolije";
    $dbname = "u1694897_c_reg_1_db";

    //Connect
    $database = "mysql:dbname=$dbname;host=$host";
    $connection = new PDO($database, $username, $password);
    $connection->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // if($connection){
    //     echo "Koneksi Berhasil";
    // } else {
    //     echo "Gagal gan";
    // }


} catch (PDOException $e){
    echo "Error ! " . $e->getMessage();
    die;
}

?>