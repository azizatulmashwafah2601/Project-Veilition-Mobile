<?php

include 'connection.php';

if($_POST){

    //POST DATA
    $nama = $_POST['nama_pelanggan'];
    $email = $_POST['email_pelanggan'];
    $password = $_POST['password_pelanggan'];
    $alamat = $_POST['alamat_pelanggan'];
    $telepon = $_POST['telepon_pelanggan'];


    $response = [];

    //Cek username didalam databse
    $userQuery = $connection->prepare("SELECT * FROM pelanggan where email_pelanggan = ?");
    $userQuery->execute(array($email));

    // Cek username apakah ada tau tidak
    if($userQuery->rowCount() != 0){
        // Beri Response
        $response['status']= false;
        $response['message']='Akun sudah digunakan';
    } else {
        $insertAccount = 'INSERT INTO pelanggan(email_pelanggan, password_pelanggan, nama_pelanggan, telepon_pelanggan, alamat_pelanggan) VALUES(:email_pelanggan, :password_pelanggan, :nama_pelanggan, :telepon_pelanggan, :alamat_pelanggan)';
        $statement = $connection->prepare($insertAccount);

        try{
            //Eksekusi statement db
            $statement->execute([
                ':email_pelanggan' => $email,
                ':password_pelanggan' => md5($password), 
                ':nama_pelanggan' => $nama, 
                ':telepon_pelanggan' => $telepon ,
                ':alamat_pelanggan' => $alamat

            ]);

            //Beri response
            $response['status']= true;
            $response['message']='Akun berhasil didaftar';
            $response['data'] = [
                'email_pelanggan' => $email,
                'nama_pelanggan' => $nama
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