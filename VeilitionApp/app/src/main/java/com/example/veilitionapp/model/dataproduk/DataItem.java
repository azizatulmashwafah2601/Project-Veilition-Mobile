package com.example.veilitionapp.model.dataproduk;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("id_produk")
	private String idProduk;

	@SerializedName("nama_produk")
	private String namaProduk;

	@SerializedName("harga_produk")
	private String hargaProduk;

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("berat_produk")
	private String beratProduk;

	@SerializedName("stok_produk")
	private String stokProduk;

	@SerializedName("deskripsi_produk")
	private String deskripsiProduk;

	@SerializedName("foto_produk")
	private String fotoProduk;

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

	public void setIdKategori(String idKategori){
		this.idKategori = idKategori;
	}

	public String getIdKategori(){
		return idKategori;
	}

	public void setBeratProduk(String beratProduk){
		this.beratProduk = beratProduk;
	}

	public String getBeratProduk(){
		return beratProduk;
	}

	public void setStokProduk(String stokProduk){
		this.stokProduk = stokProduk;
	}

	public String getStokProduk(){
		return stokProduk;
	}

	public void setDeskripsiProduk(String deskripsiProduk){
		this.deskripsiProduk = deskripsiProduk;
	}

	public String getDeskripsiProduk(){
		return deskripsiProduk;
	}

	public void setFotoProduk(String fotoProduk){
		this.fotoProduk = fotoProduk;
	}

	public String getFotoProduk(){
		return fotoProduk;
	}
}