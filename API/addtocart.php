<?php 
if ($_SERVER["REQUEST_METHOD"] == 'POST') {
  require "konek.php";
  if ($connection) {
    $id_pelanggan = $_POST['id_pelanggan'];
    $id_produk = $_POST['id_produk'];
    $jumlah = $_POST['jumlah'];
    $intQty = (int)$jumlah;
    
    
    $findHarga = mysqli_query($connection, "SELECT harga_produk FROM produk WHERE id_produk = '$id_produk'");
    $getHarga = mysqli_fetch_assoc($findHarga);
    $harga = $getHarga["harga_produk"];
    $subtotal = $intQty * $harga;
  
    
    $queryInsert = mysqli_query($connection, "INSERT INTO keranjang (id_pelanggan, id_produk, jumlah, subtotal) VALUES ('$id_pelanggan', '$id_produk', '$intQty', '$subtotal')");

    if ($queryInsert) {
      $response = array('pesan'=>'BERHASIL');
    } else if (!$queryInsert) {
      $response = array('pesan'=>'GAGAL');
    }
    // }
  } else {
    $response = array('pesan'=>'NOT CONNECTED');
  }
}
  echo json_encode($response);
  mysqli_close($connection);

?>