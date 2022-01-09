package com.example.veilitionapp.model.keranjang;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class KeranjangResponse{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataKeranjang> data;

	@SerializedName("kode")
	private int kode;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataKeranjang> data){
		this.data = data;
	}

	public List<DataKeranjang> getData(){
		return data;
	}

	public void setKode(int kode){
		this.kode = kode;
	}

	public int getKode(){
		return kode;
	}
}