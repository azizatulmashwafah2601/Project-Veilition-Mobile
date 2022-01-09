<?php

include_once("konek.php");

$idpd = $_POST['id_produk'];


$query_insert = "SELECT * FROM produk WHERE id_produk = $idpd";
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
        $F["berat_produk"] = $ambil->berat_produk;
        $F["foto_produk"] = $ambil->foto_produk;
        $F["deskripsi_produk"] = $ambil->deskripsi_produk;
        $F["stok_produk"] = $ambil->stok_produk;
        
        array_push($response["data"], $F);
    }
    
} else {
    $response["kode"] = 0;
    $response["pesan"] = "Data tidak tersedia";
}

echo json_encode($response);
mysqli_close($connection);

?>