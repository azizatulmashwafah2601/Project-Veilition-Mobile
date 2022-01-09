<?php

include 'connection.php';

if($_POST){

    //Data
    $email = $_POST['email_pelanggan'] ?? '';
    $password = $_POST['password_pelanggan'] ?? '';

    $response = []; //Data Response

    //Cek email didalam databse
    $userQuery = $connection->prepare("SELECT * FROM pelanggan where email_pelanggan = ?");
    $userQuery->execute(array($email));
    $query = $userQuery->fetch();

    if($userQuery->rowCount() == 0){
        $response['status'] = false;
        $response['message'] = "Email Tidak Terdaftar";
    } else {
        // Ambil password di db

        $passwordDB = $query['password_pelanggan'];

        if(strcmp(md5($password),$passwordDB) === 0){
            $response['status'] = true;
            $response['message'] = "Login Berhasil";
            $response['data'] = [
                'user_id' => $query['id_pelanggan'],
                'email' => $query['email_pelanggan'],
                'name' => $query['nama_pelanggan'],
                'telepon' => $query['telepon_pelanggan'],
                'alamat' => $query['alamat_pelanggan']

            ];
        } else {
            $response['status'] = false;
            $response['message'] = "Password anda salah";
        }
    }

    //Jadikan data JSON
    $json = json_encode($response, JSON_PRETTY_PRINT);

    //Print
    echo $json;

}