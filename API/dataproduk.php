<?php

include_once("konek.php");

$query_insert = "SELECT * FROM produk ORDER BY nama_produk ASC";
$result = mysqli_query($connection, $query_insert);
$cek = mysqli_affected_rows($connection);

if ($cek > 0){
    $response["kode"] = 1;
    $response["pesan"] = "Data tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($result)){
        $F["id_produk"] = $ambil->id_produk;
        $F["nama_produk"] = $ambil->nama_produk;
        $F["harga_produk"] = $ambil->harga_produk;
        $F["foto_produk"] = $ambil->foto_produk;
        
        array_push($response["data"], $F);
    }
    
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Data tidak tersedia";
}

echo json_encode($response);
mysqli_close($connection);

?>