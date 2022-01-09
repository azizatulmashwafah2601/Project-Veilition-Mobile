
<?php 
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  require 'konek.php';

  if ($connection) {
    $id_pelanggan = $_POST["id_pelanggan"];

    $queryGET = mysqli_query($connection, "SELECT * FROM keranjang WHERE id_pelanggan='$id_pelanggan'");
    
    if (mysqli_num_rows($queryGET) > 0) {
      $response["kode"] = 1;
      $response["pesan"] = "Data tersedia";
      $response["data"] = array();

      while($ambil = mysqli_fetch_object($queryGET)){
        $F["id_keranjang"] = $ambil->id_keranjang;
        $F["id_pelanggan"] = $ambil->id_pelanggan;
        $F["id_produk"] = $ambil->id_produk;
        $F["jumlah"] = $ambil->jumlah;
        $F["subtotal"] = $ambil->subtotal;
        $idp = $F['id_produk'];
        $qy = $connection->query("SELECT * FROM produk WHERE id_produk = '$idp'");
        while($getX = mysqli_fetch_assoc($qy)) {
            $F['nama_produk'] = $getX['nama_produk'];
            $F['harga_produk'] = $getX['harga_produk'];
            $F['foto_produk'] = $getX['foto_produk'];
        }
        
        array_push($response["data"], $F);
      }
    } else {
      $response["kode"] = 0;
      $response["pesan"] = "Data tidak tersedia";
    }

  } else {
    $response["kode"] = 404;
    $response["pesan"] = "Not Connected";
  }
  echo json_encode($response);
  mysqli_close($connection);
}
?>