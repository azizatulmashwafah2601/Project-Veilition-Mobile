<?php

require("konek.php");



if ($_SERVER['REQUEST_METHOD'] == 'POST'){

  $id = $_POST['id_pelanggan'];
  $nama = $_POST['nama_pelanggan'];
  $email = $_POST['email_pelanggan'];
  //$password = $_POST['password_pelanggan'];
  $password =md5($_POST['password_pelanggan']);
  $alamat = $_POST['alamat_pelanggan'];
  $telepon = $_POST['telepon_pelanggan'];

  $response = [];
    
    $update_query="UPDATE pelanggan SET  email_pelanggan='$email', password_pelanggan='$password', nama_pelanggan='$nama', telepon_pelanggan='$telepon', alamat_pelanggan='$alamat' WHERE id_pelanggan='$id'";
    $result = mysqli_query($connection, $update_query);
    $cek = mysqli_affected_rows($connection);

    if ($cek > 0){
        $response["status"] = true;
        $response["message"] = "Data Berhasil Diubah, Silahkan Login Kembali!";
        $response['data'] = [
            'email_pelanggan' => $email,
            'nama_pelanggan' => $nama, 
            'telepon_pelanggan' => $telepon ,
            'alamat_pelanggan' => $alamat
        ];
    } else {
        $response["status"] = false;
        $response["message"] = "Data Gagal Diubah";
    }
} else {
    $response["status"] = false;
    $response["message"] = "Tidak Ada Post Data";
}

echo json_encode($response);
mysqli_close($connection);

?>