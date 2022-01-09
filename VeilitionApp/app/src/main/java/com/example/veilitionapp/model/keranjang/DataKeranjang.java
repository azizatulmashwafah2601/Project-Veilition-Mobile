package com.example.veilitionapp.model.keranjang;

import com.google.gson.annotations.SerializedName;

public class DataKeranjang {

	@SerializedName("id_keranjang")
	private String idKeranjang;

	@SerializedName("id_produk")
	private String idProduk;

	@SerializedName("nama_produk")
	private String namaProduk;

	@SerializedName("harga_produk")
	private String hargaProduk;

	@SerializedName("jumlah")
	private String jumlah;

	@SerializedName("subtotal")
	private String subtotal;

	@SerializedName("id_pelanggan")
	private String idPelanggan;

	@SerializedName("foto_produk")
	private String fotoProduk;

	public void setIdKeranjang(String idKeranjang){
		this.idKeranjang = idKeranjang;
	}

	public String getIdKeranjang(){
		return idKeranjang;
	}

	public void setIdProduk(String idProduk){
		this.idProduk = idProduk;
	}

	public String getIdProduk(){
		return idProduk;
	}

	public void setNamaProduk(String namaProduk){
		this.namaProduk = namaProduk;
	}

	public String getNamaProduk(){
		return namaProduk;
	}

	public void setHargaProduk(String hargaProduk){
		this.hargaProduk = hargaProduk;
	}

	public String getHargaProduk(){
		return hargaProduk;
	}

	public void setJumlah(String jumlah){
		this.jumlah = jumlah;
	}

	public String getJumlah(){
		return jumlah;
	}

	public void setSubtotal(String subtotal){
		this.subtotal = subtotal;
	}

	public String getSubtotal(){
		return subtotal;
	}

	public void setIdPelanggan(String idPelanggan){
		this.idPelanggan = idPelanggan;
	}

	public String getIdPelanggan(){
		return idPelanggan;
	}

	public void setFotoProduk(String fotoProduk){
		this.fotoProduk = fotoProduk;
	}

	public String getFotoProduk(){
		return fotoProduk;
	}
}