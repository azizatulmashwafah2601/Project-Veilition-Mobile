package com.example.veilitionapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.veilitionapp.API.APIClient;
import com.example.veilitionapp.API.APIInterface;
import com.example.veilitionapp.R;
import com.example.veilitionapp.model.register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword, etNama, etTelepon, etAlamat;
    Button buttonDaftar;
    TextView tvLogin;
    String email, password, nama, telepon, alamat;
    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNama = findViewById(R.id.etNama);
        etTelepon = findViewById(R.id.etNoTelp);
        etAlamat = findViewById(R.id.etAlamat);
        buttonDaftar = findViewById(R.id.btnDaftar);
        tvLogin = findViewById(R.id.tv_loginreg);

        buttonDaftar.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDaftar:
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                nama = etNama.getText().toString();
                telepon = etTelepon.getText().toString();
                alamat = etAlamat.getText().toString();

                if (nama.trim().equals("")) {
                    etNama.setError("Wajib diisi!");
                }else if (telepon.trim().equals("")) {
                    etTelepon.setError("Wajib diisi!");
                }else if (email.trim().equals("")) {
                    etEmail.setError("Wajib diisi!");
                }else if (alamat.trim().equals("")) {
                    etAlamat.setError("Wajib diisi!");
                }else if (password.trim().equals("")) {
                    etPassword.setError("Wajib diisi!");
                }else {
                    daftar(email, password, nama, telepon, alamat);
                }

                break;

            case R.id.tv_loginreg:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
        }
    }

    private void daftar(String email, String password, String nama, String telepon, String alamat) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Register> call = apiInterface.registerResponse(email, password, nama, telepon, alamat);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if (response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}