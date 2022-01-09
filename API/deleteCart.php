<?php

require("konek.php");

$response = array();

if ($_SERVER['REQUEST_METHOD'] == 'POST'){

    $id = $_POST["id_keranjang"];
    
    $query_insert = "DELETE FROM keranjang WHERE id_keranjang = '$id'";
    $result = mysqli_query($connection, $query_insert);
    $cek = mysqli_affected_rows($connection);

    if ($cek > 0){
        $response["kode"] = 1;
        $response["pesan"] = "Data berhasil dihapus!";
    } else {
        $response["kode"] = 0;
        $response["pesan"] = "Hapus data gagal!";
    }
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Tidak ada post data!";
}

echo json_encode($response);
mysqli_close($connection);

?>