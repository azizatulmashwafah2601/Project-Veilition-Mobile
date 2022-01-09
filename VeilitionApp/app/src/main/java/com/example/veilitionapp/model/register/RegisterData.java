package com.example.veilitionapp.model.register;

import com.google.gson.annotations.SerializedName;

public class RegisterData {

	@SerializedName("nama_pelanggan")
	private String namaPelanggan;

	@SerializedName("email_pelanggan")
	private String emailPelanggan;

	public void setNamaPelanggan(String namaPelanggan){
		this.namaPelanggan = namaPelanggan;
	}

	public String getNamaPelanggan(){
		return namaPelanggan;
	}

	public void setEmailPelanggan(String emailPelanggan){
		this.emailPelanggan = emailPelanggan;
	}

	public String getEmailPelanggan(){
		return emailPelanggan;
	}
}