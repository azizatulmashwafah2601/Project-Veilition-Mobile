package com.example.veilitionapp.API;

import com.example.veilitionapp.model.dataproduk.Produk;
import com.example.veilitionapp.model.keranjang.KeranjangResponse;
import com.example.veilitionapp.model.login.Login;
import com.example.veilitionapp.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("dataproduk.php")
    Call<Produk> produkResponse();

    @FormUrlEncoded
    @POST("detailproduk.php")
    Call<Produk> detailResponse(
            @Field("id_produk") String id_produk
    );

    @FormUrlEncoded
    @POST("Cart.php")
    Call<KeranjangResponse> keranjangResponse(
            @Field("id_pelanggan") String id_pelanggan
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("email_pelanggan") String email,
            @Field("password_pelanggan") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("email_pelanggan") String email,
            @Field("password_pelanggan") String password,
            @Field("nama_pelanggan") String nama,
            @Field("telepon_pelanggan") String telepon,
            @Field("alamat_pelanggan") String alamat
    );

    @FormUrlEncoded
    @POST("updateakun.php")
    Call<Login> UpdateResponse(
            @Field("id_pelanggan") String id,
            @Field("email_pelanggan") String email,
            @Field("password_pelanggan") String password,
            @Field("nama_pelanggan") String nama,
            @Field("telepon_pelanggan") String telepon,
            @Field("alamat_pelanggan") String alamat
    );




    /**@FormUrlEncoded
    @POST("get.php")
    Call<Login> ardGetData(
            @Field("email_pelanggan") String email
    );**/
}
