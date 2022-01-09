<?php

include 'connection.php';

if($_POST){

    //POST DATA
    $id = $_POST['id_pelanggan'];
    $nama = $_POST['nama_pelanggan'];
    $email = $_POST['email_pelanggan'];
    $alamat = $_POST['alamat_pelanggan'];
    $telepon = $_POST['telepon_pelanggan'];
    $password =md5($_POST['password_pelanggan']);


    $response = [];

    //Cek username didalam databse
    $userQuery = $connection->prepare("SELECT * FROM pelanggan where email_pelanggan = ?");
    $userQuery->execute(array($email));

    // Cek username apakah ada tau tidak
    if($userQuery->rowCount() != 0){
        // Beri Response
        $response['status']= false;
        $response['message']= 'Email sudah digunakan, pastikan email belum terdaftar dan coba lagi!';
    } else {
        $update_query="UPDATE pelanggan SET email_pelanggan='$email', password_pelanggan='$password', nama_pelanggan='$nama', telepon_pelanggan='$telepon', alamat_pelanggan='$alamat' WHERE id_pelanggan='$id'";
        $statement = $connection->prepare($update_query);

        try{
            //Eksekusi statement db
            $statement->execute([]);

            //Beri response
            $response['status']= true;
            $response['message']='Akun berhasil diupdate, Silahkan login kembali!';
            $response['data'] = [
                'email_pelanggan' => $email,
                'nama_pelanggan' => $nama, 
                'telepon_pelanggan' => $telepon ,
                'alamat_pelanggan' => $alamat
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }

    }
    
    //Jadikan data JSON
    $json = json_encode($response, JSON_PRETTY_PRINT);

    //Print JSON
    echo $json;
}