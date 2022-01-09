package com.example.veilitionapp.model.dataproduk;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Produk{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("kode")
	private int kode;

	@SerializedName("pesan")
	private String pesan;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}

	public void setPesan(String message){
		this.pesan = message;
	}

	public String getPesan(){
		return pesan;
	}
}